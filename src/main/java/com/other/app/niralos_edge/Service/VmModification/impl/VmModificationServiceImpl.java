package com.other.app.niralos_edge.Service.VmModification.impl;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.VMManagement.VmMangementService;
import com.other.app.niralos_edge.Service.VmModification.VmModificationService;
import com.other.app.niralos_edge.dto.AddInterfaceDto;
import com.other.app.niralos_edge.dto.PorxmoxInterfaceBody;
import com.other.app.niralos_edge.dto.ProxmoxToken;
import com.other.app.niralos_edge.dto.VmResourceExtensionDto;
import com.other.app.niralos_edge.dto.VmUpdateDto;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class VmModificationServiceImpl implements VmModificationService{
	
	private static final Logger logger=LoggerFactory.getLogger("EDGE:VmModificationServiceImpl");
	
	@Autowired
	InternalDataRepositorys internalDataRepository;
	
	@Autowired
	VmMangementService vmMangementService;
	private final Object lock = new Object();

	
	@Override
	public ResponseEntity<String> updateVm(VmUpdateDto payload,String edgeClientId) {
		VmModificationServiceImpl ti = new VmModificationServiceImpl();
		synchronized (ti) {
		InternalDataModels temp=internalDataRepository.getData(edgeClientId);
		try {
				
			SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
		            //.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataRepository.getCoreDetails().getHypervisorToken())
		            .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort())
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
			
			Map<String, String> credential = new HashMap<>();
			credential.put("username", temp.getUserName());
			credential.put("password", temp.getPassword());
			 Mono<Map<String, Object>>  responseMono = proxmoxClient.post().uri("/api2/json/access/ticket")
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(credential)
			.retrieve()
			 .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
			 .timeout(Duration.ofSeconds(1));
			 
			 responseMono.subscribe(responseBody -> {
//				 logger.info(responseBody);
				    synchronized (lock) {
				        lock.notify();
				    }
				}, throwable -> {
					
					logger.debug("Error: " + throwable.getMessage() + " 'Vm Needs Reboot' ");
					
				    synchronized (lock) {
				        lock.notify(); // Notify even in case of error
				    }
				});
		
			 Map<String, Object> responset = responseMono.block();
		 // Accessing the nested data
	     Map<String, Object> data = (Map<String, Object>) responset.get("data");
	     
	     String ticket = (String) data.get("ticket");
	     String csrfToken = (String) data.get("CSRFPreventionToken");
	     ProxmoxToken proxmoxToken = new ProxmoxToken(ticket,csrfToken);
			
	     logger.info( proxmoxToken.getTicket() );
	     logger.info( proxmoxToken.getCSRFPreventionToken() );

	        
	    	SslContext sslContext;
		
			WebClient.Builder webClientBuilder = WebClient.builder()
		                .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort())
		                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		                .defaultHeader(HttpHeaders.COOKIE, "PVEAuthCookie=" + proxmoxToken.getTicket())
		                .clientConnector(new ReactorClientHttpConnector(httpClient))
		                .defaultHeader("CSRFPreventionToken", proxmoxToken.getCSRFPreventionToken());

			  List<PorxmoxInterfaceBody> interfaceBody =	payload.getDeleteInterfaces();
			 MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
			for (PorxmoxInterfaceBody porxmoxInterfaceBody : interfaceBody) {
			        formData.add("delete",porxmoxInterfaceBody.getNetworkDeviceDeletion());
			} 
			
		
				 // Notify the waiting thread (ti2)
			
		     WebClient webClient = webClientBuilder.build();
		     			webClient.put()
			                .uri("/api2/json/nodes/"+"pve"+"/qemu/"+payload.getVmId()+"/config")
			                .body(BodyInserters.fromFormData(formData))
			                .retrieve()
			                .bodyToMono(String.class)
			                .timeout(Duration.ofSeconds(1))
			                .subscribe(responseBody -> {
			                    synchronized (lock) {
			                        lock.notify();
			                    }
			                }, throwable -> {
			                	
			                	logger.debug("Error: " + throwable.getMessage() + " 'Vm Needs Reboot' ");
			                    synchronized (lock) {
			                        lock.notify(); // Notify even in case of error
			                    }
			                });
		     			
			}catch (Exception e) {
				
				logger.error("Need to check proxmox or api end points: " + e );
				
				 Thread.currentThread().interrupt();
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : "+ e);
			  
			}
		
		
		}
	
			
		
		synchronized (lock) {
            try {
            	
            	lock.wait();
            	InternalDataModels model=internalDataRepository.getData(edgeClientId);
            	
            	SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
		            .defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+model.getHypervisorToken())
		            .baseUrl("https://"+model.getHypervisorIp()+":"+model.getHypervisorPort()+"/api2/json")
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
    			
            	vmMangementService.stopVm(payload.getVmId(),edgeClientId);
    			Thread.sleep(10000);
    			VmResourceExtensionDto vmResourecConfig = new VmResourceExtensionDto(payload.getMemory(), payload.getSockets(), payload.getCores());
    			
    			proxmoxClient.post()
				.uri("/nodes/pve/qemu/"+payload.getVmId()+"/config")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(vmResourecConfig)
		        .retrieve()
		        .bodyToMono(String.class)
		        .timeout(Duration.ofSeconds(1))
		        .block();
    			Thread.sleep(5000);
    			vmMangementService.startVm(payload.getVmId(),edgeClientId);
//    			
        	} catch (Exception e) {
        		
        		logger.error("Error Ocurred While Extending VM Resources" + e);
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e);
    		}
		}
//	}
		
		
		if(payload.getAddInterfaces().isEmpty()){
//			return "Addition of Interface is not possible";
			logger.info("No interfaces available for addition.");
		}else {
			
			logger.info("Addition of Interface is possible");
			
			InternalDataModels temp=internalDataRepository.getData(edgeClientId);
			try {
//				if(payload.getInterfaces() !=null) {
					
				SslContext context = SslContextBuilder.forClient()
						    .trustManager(InsecureTrustManagerFactory.INSTANCE)
						    .build();
						                
					HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
				
					WebClient proxmoxClient = WebClient.builder()
			            //.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataRepository.getCoreDetails().getHypervisorToken())
			            .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort())
			            .clientConnector(new ReactorClientHttpConnector(httpClient))
					    .build();
				
				Map<String, String> credential = new HashMap<>();
				credential.put("username", temp.getUserName());
				credential.put("password", temp.getPassword());
				 Mono<Map<String, Object>>  responseMono = proxmoxClient.post().uri("/api2/json/access/ticket")
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(credential)
				.retrieve()
				 .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
				 .timeout(Duration.ofSeconds(1));
				 
				 responseMono.subscribe(responseBody -> {
					    
					    logger.debug(""+responseBody);
					    
					    synchronized (lock) {
					        lock.notify();
					    }
					}, throwable -> {
						logger.debug("Error: " + throwable.getMessage() + " 'Vm Needs Reboot' ");
					    synchronized (lock) {
					        lock.notify(); // Notify even in case of error
					    }
					});
			
				 Map<String, Object> responset = responseMono.block();
			 // Accessing the nested data
		     Map<String, Object> data = (Map<String, Object>) responset.get("data");
		     
		     String ticket = (String) data.get("ticket");
		     String csrfToken = (String) data.get("CSRFPreventionToken");
		     ProxmoxToken proxmoxToken = new ProxmoxToken(ticket,csrfToken);
				
		     logger.info( proxmoxToken.getTicket() );
		     logger.info( proxmoxToken.getCSRFPreventionToken() );

		        
		    	SslContext sslContext;
			
			

				  WebClient.Builder webClientBuilder = WebClient.builder()
			                .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort())
			                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
			                .defaultHeader(HttpHeaders.COOKIE, "PVEAuthCookie=" + proxmoxToken.getTicket())
			                .clientConnector(new ReactorClientHttpConnector(httpClient))
			                .defaultHeader("CSRFPreventionToken", proxmoxToken.getCSRFPreventionToken());
				  
				  
				  
		        WebClient webClient = webClientBuilder.build();
		        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		        
		        for (AddInterfaceDto Interface : payload.getAddInterfaces()) {
					
				
		        String bridge = "virtio,bridge=" + Interface.getSubnet();
		        switch (Interface.getFirewall()) {
		            case 0:
		                formData.add(Interface.getInterface_Name(), bridge);
		                break;
		            case 1:
		                formData.add(Interface.getInterface_Name(), bridge + ",firewall=1");
		                break;
		            default:
		                throw new IllegalArgumentException("Unexpected value: " + Interface.getFirewall());
		        }

		        webClient.put()
		                .uri("/api2/json/nodes/pve/qemu/"+payload.getVmId()+"/config")
		                .body(BodyInserters.fromFormData(formData))
		                .retrieve()
		                .bodyToMono(String.class)
		                .timeout(Duration.ofSeconds(1))
		                .doOnError(WebClientResponseException.class, ex -> {
		                	
		                	logger.info("Error response code: " + ex.getMessage());
		              })
		                .subscribe(response -> {
		                    // Handle response
		                	logger.debug(" "+response);
		                });
		        }
			}catch (Exception e) {
				logger.error("Error processing request: {}", e.getMessage(), e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : "+ e);
			}
		}

		 	return ResponseEntity.status(HttpStatus.OK).body("VM Modified Successfully");
    			}

}


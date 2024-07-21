package com.other.app.niralos_edge.Service.VmCreation.ServiceImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.VmProvisionStatus;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.VmCreation.SpicePortGenService;
import com.other.app.niralos_edge.Service.VmCreation.VmCreation;
import com.other.app.niralos_edge.dto.AddInterfaceDto;
import com.other.app.niralos_edge.dto.CreateVmConfigDto;
import com.other.app.niralos_edge.dto.hypervisorcookiedto.RootHypervisorCookieDto;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Service
public class VmCreationImpl implements VmCreation{

	private static final Logger logger=LoggerFactory.getLogger(VmCreationImpl.class);
	
	@Autowired
	SpicePortGenService genService;
	
	@Autowired
	InternalDataRepositorys internalDataRepository;
	
	
	@Override
	public void createVm(CreateVmConfigDto payload) {
		
		try {
	    	InternalDataModels temp=internalDataRepository.getData(payload.getEdgeClientId());
	    	
			if (payload.getProvisionStatus().equals("false") ) {
				
				Integer spicePort = genService.generateUniqueNumber();
				String spicePortStr = spicePort.toString();
				
				MultiValueMap<String, String> vmresvals = new LinkedMultiValueMap<>();
				
				vmresvals.add("node", "pve");
				vmresvals.add("vmid", payload.getVmid().toString());
				vmresvals.add("name", payload.getName());
				vmresvals.add("ide2", payload.getIso()+",media=cdrom");
				vmresvals.add("machine","q35");
				vmresvals.add("sockets", payload.getSockets());
				vmresvals.add("cores", payload.getCores());
				vmresvals.add("memory", payload.getMemory());
				vmresvals.add("net0", "virtio,bridge=vmbr0,firewall=1");
				vmresvals.add("ostype", "l26");
				vmresvals.add("scsi0", "local-lvm:"+payload.getStorageSpace()+",backup=0,replicate=no,ssd=on,iothread=on,aio=threads");
				vmresvals.add("agent", "1");
				vmresvals.add("numa", "0");
				vmresvals.add("scsihw", "virtio-scsi-single");
				vmresvals.add("start", "1");
				vmresvals.add("onboot", "0");
				vmresvals.add("bootdisk", "scsi0");
				vmresvals.add("vga", "qxl,memory=34");
				
				
				
				vmresvals.add("args", "-spice port="+spicePortStr+",addr=0.0.0.0,disable-ticketing=on");
				
				vmresvals.add("cpu", "host");
				
				logger.info("vm details", vmresvals);;
				
				
				try {
					
					SslContext context = SslContextBuilder.forClient()
	  					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
	  					    .build();
	  					                
	  				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
	  			
	  				WebClient proxmoxClient = WebClient.builder()
	  		            .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort()+"/api2/json")
	  		            .clientConnector(new ReactorClientHttpConnector(httpClient))
	  				    .build();
	  				
					MultiValueMap<String, String> hypervisorCreds = new LinkedMultiValueMap<>();
					hypervisorCreds.add("username", temp.getUserName());
					hypervisorCreds.add("password", temp.getPassword());
//					sdnWebClient
					// getting the cookie
					RootHypervisorCookieDto cookieVal = proxmoxClient.post()
							.uri("/access/ticket")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.bodyValue(hypervisorCreds)
							.retrieve()
							.bodyToMono(RootHypervisorCookieDto.class)
							.block();
					
					logger.info("CSRF Token", cookieVal.getData().getCsrftoken().toString());
					logger.info("Cookie", cookieVal.getData().getTicket().toString());
					
					try {
						SslContext context1 = SslContextBuilder.forClient()
		  					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
		  					    .build();
		  					                
		  				HttpClient httpClient1 = HttpClient.create().secure(t -> t.sslContext(context1));
		  			
		  				WebClient proxmoxClient1 = WebClient.builder()
		  		            .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort()+"/api2/json")
		  		            .defaultHeader(HttpHeaders.COOKIE,"PVEAuthCookie="+cookieVal.getData().getTicket())
		  		            .defaultHeader("CSRFPreventionToken", cookieVal.getData().getCsrftoken())
		  		            .clientConnector(new ReactorClientHttpConnector(httpClient1))
		  				    .build();
						
		  				proxmoxClient1.post()
						.uri("/nodes/pve/qemu")
				        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
				        .body(BodyInserters.fromFormData(vmresvals))
				        .retrieve()
				        .bodyToMono(String.class)
				        .block();
		  				
					} catch (Exception e) {
						
						logger.error("Unable to create vm in hypervisor"+e);
					}
					
	  								
					VmProvisionStatus updateProvisionStatus = new VmProvisionStatus(payload.getVmid(), true);
					
                    logger.info("New VM Created Successfully");
					
					 WebClient.Builder webClientBuilder = WebClient.builder()
				                .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort())
				                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				                .defaultHeader(HttpHeaders.COOKIE, "PVEAuthCookie=" + cookieVal.getData().getTicket())
				                .clientConnector(new ReactorClientHttpConnector(httpClient))
				                .defaultHeader("CSRFPreventionToken", cookieVal.getData().getCsrftoken());

				        WebClient webClient = webClientBuilder.build();
				        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
				        for (AddInterfaceDto Interface : payload.getInterfaces()) {
							
						
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
				                .uri("/api2/json/nodes/pve/qemu/"+payload.getVmid()+"/config")
				                .body(BodyInserters.fromFormData(formData))
				                .retrieve()
				                .bodyToMono(String.class)
				                .doOnError(WebClientResponseException.class, ex -> {
				                  System.err.println("Error response code: " + ex.getMessage());
				              })
				                .subscribe(response -> {
				                    // Handle response
				                	logger.debug(""+response);
				                });
				        }
				} 
				catch (Exception e) {
						VmProvisionStatus updateProvisionStatus = new VmProvisionStatus(payload.getVmid(), false);
					}
					
				}     		
		} catch (Exception e) {
			
			logger.error("Error Ocurred While Provisioning VM"+e);
		}
		
	}

}

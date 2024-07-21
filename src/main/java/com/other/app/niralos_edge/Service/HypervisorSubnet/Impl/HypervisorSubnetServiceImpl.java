package com.other.app.niralos_edge.Service.HypervisorSubnet.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.HypervisorSubnet.HypervisorSubnetService;
import com.other.app.niralos_edge.dto.HypervisorNetworkIntFaceCreationDto;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Service
public class HypervisorSubnetServiceImpl implements HypervisorSubnetService{
	
	private static final Logger logger=LoggerFactory.getLogger(HypervisorSubnetServiceImpl.class);
	
	@Autowired
	InternalDataRepositorys dataRepositorys;

	@Override
	public String createInterface(String edgeClientId,HypervisorNetworkIntFaceCreationDto dto) {
		
		try {
			if(dto.getIface().equals("vmbr0"))
			{
				return "Interface Already Exists";
			}else {
				HypervisorNetworkIntFaceCreationDto model=new HypervisorNetworkIntFaceCreationDto();
				
				model.setAutostart("1");
				model.setCidr(dto.getCidr());
				model.setIface(dto.getIface());
				model.setType(dto.getType());
				
				InternalDataModels models=dataRepositorys.getData(edgeClientId);
				
				SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
					.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+models.getHypervisorToken())
			        .baseUrl("https://"+models.getHypervisorIp()+":"+models.getHypervisorPort()+"/api2/extjs")
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
				
				proxmoxClient.post()
				.uri("/nodes/pve/network")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(model)
		        .retrieve()
		        .bodyToMono(String.class)
		        .block();
				
				Thread.sleep(5000);
				
				applyConfiguration(edgeClientId);
				logger.info("Interface Creation Initiated");
			}
			
			
		} catch (Exception e) {
			
			logger.error("Hypervisor not reachable");
		}
		return "Interface Created Successfully";
	}
	
	public String applyConfiguration(String edgeClientId) {
		
		try {
			
			InternalDataModels models=dataRepositorys.getData(edgeClientId);
			
			SslContext context = SslContextBuilder.forClient()
				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				    .build();
				                
			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
			WebClient proxmoxClient1 = WebClient.builder()
//					.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+models.getHypervisorToken())
					.baseUrl("https://"+models.getHypervisorIp()+":"+models.getHypervisorPort())
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
			
			
			proxmoxClient1.put()
			.uri("/api2/extjs/nodes/pve/network")
			.headers(h->h.setBearerAuth("PVEAPIToken="+models.getHypervisorToken()))
	        .contentLength(0)
	        .retrieve()
	        .bodyToMono(String.class)
	        .subscribe(
	                response -> {
	                    // Handle successful response
	                	logger.debug("Response: " + response);
	                },
	                error -> {
	                    // Handle error
	                    error.printStackTrace();
	                });
	        
			
			
			
		} catch (Exception e) {
           logger.error(""+e);
		}
		
		
		return "Apply Configuration Done";
	}

}

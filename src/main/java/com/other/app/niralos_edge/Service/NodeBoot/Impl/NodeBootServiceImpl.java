package com.other.app.niralos_edge.Service.NodeBoot.Impl;

import java.time.Duration;

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

import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.NodeUpdateDto;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.NodeBoot.NodeBootInterface;
import com.other.app.niralos_edge.Service.VMManagement.VmMangementService;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Service
public class NodeBootServiceImpl implements NodeBootInterface{
	
	private static final Logger logger=LoggerFactory.getLogger("EDGE:NodeBootServiceImpl");

	@Autowired
	InternalDataRepositorys internalDataRepository;
	
	@Autowired
	VmMangementService vmMangementService;

	@Override
	public void nodeRebootandShutdown(NodeUpdateDto payload,String edgeClientId) {
		
		InternalDataModels temp=internalDataRepository.getData(edgeClientId);
		try {
			
			SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
	    	  
		
			  WebClient.Builder webClientBuilder = WebClient.builder()
		                .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort())
		                .defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+temp.getHypervisorToken())
		                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		                .clientConnector(new ReactorClientHttpConnector(httpClient));
			  
			 MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
			        formData.add("command",	payload.getCommand());
		
				 // Notify the waiting thread (ti2)
		     WebClient webClient = webClientBuilder.build();
		     			webClient.post()
			                .uri("/api2/json/nodes/"+"pve"+"/status")
			                .body(BodyInserters.fromFormData(formData))
			                .retrieve()
			                .bodyToMono(String.class)
			                .timeout(Duration.ofSeconds(1))
			                .subscribe(responseBody -> {
			                	logger.debug(responseBody + "Vm boot on progress");
			                }, throwable -> {
			                	logger.error("Error :" + throwable.getMessage() + " 'node Needs Reboot' ");
			                });
			}catch (Exception e) {
				
				logger.error("Need to check proxmox or api end points: " + e);
			  
			}
		
	}

	


	

}

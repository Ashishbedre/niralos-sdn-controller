package com.other.app.niralos_edge.Service.VMManagement.ServiceImpl;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.niralos_edge.Model.DeleteVmStatusModel;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.AlertVmRepository;
import com.other.app.niralos_edge.Repository.DeleteVmRepository;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Repository.VmStatsRepo;
import com.other.app.niralos_edge.Repository.graph.CpuGraphRepository;
import com.other.app.niralos_edge.Repository.graph.MemoryGraphRepository;
import com.other.app.niralos_edge.Service.VMManagement.VmMangementService;
import com.other.app.niralos_edge.dto.HypervisorQemuInfoDto;
import com.other.app.niralos_edge.dto.HypervisorQemuInfoRootDto;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;


@Service
@EnableScheduling
public class VmManagementServiceImpl implements VmMangementService{
	
	private static final Logger logger =LoggerFactory.getLogger("EDGE:VmManagementServiceImpl");
	@Autowired
	VmStatsRepo vmStatsRepo;
	
	@Autowired
	InternalDataRepositorys internalDataRepository;
	
	@Autowired
	DeleteVmRepository deleteVmRepository; 
	
	@Autowired
	AlertVmRepository alertVmRepository;
	
	@Autowired
	CpuGraphRepository cpuGraphRepository;
	
	@Autowired
	MemoryGraphRepository memoryGraphRepository;
	
	@Value("${edge.delete.time}")
	private String deleteTime;

	@Override
	public void startVm(Long vmId,String edgeClientId) {
		try {
			
			InternalDataModels temp=internalDataRepository.getData(edgeClientId);
			SslContext context = SslContextBuilder.forClient()
				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				    .build();
				                
			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
		
			WebClient proxmoxClient = WebClient.builder()
				.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+temp.getHypervisorToken())
		        .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort()+"/api2/json")
	            .clientConnector(new ReactorClientHttpConnector(httpClient))
			    .build();
			
			     proxmoxClient.post()
				.uri("/nodes/pve/qemu/"+vmId+"/status/start")
				.contentLength(0)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
				
			     logger.info("Starting VM  "+vmId);
			
		} catch (Exception e) {
			
			logger.error("Unable To Start VM  "+vmId+""+e);
		}
	}

	@Override
	public void stopVm(Long vmId,String edgeClientId) {
		try {
			InternalDataModels temp=internalDataRepository.getData(edgeClientId);
			SslContext context = SslContextBuilder.forClient()
				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				    .build();
				                
			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
		
			WebClient proxmoxClient = WebClient.builder()
				.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+temp.getHypervisorToken())
		        .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort()+"/api2/json")
	            .clientConnector(new ReactorClientHttpConnector(httpClient))
			    .build();
			
			         proxmoxClient.post()
					.uri("/nodes/pve/qemu/"+vmId+"/status/stop")
					.contentLength(0)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(String.class)
					.block();
			         
			         logger.info("Stopping VM  "+vmId);
			
		} catch (Exception e) {
			
			logger.error("Unable To Stop VM  "+vmId+""+e);
		}
	}
	
	@Override
	public void restartVm(Long vmId,String edgeClientId) {
		try {
			
			InternalDataModels temp=internalDataRepository.getData(edgeClientId);
			SslContext context = SslContextBuilder.forClient()
				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				    .build();
				                
			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
		
			WebClient proxmoxClient = WebClient.builder()
				.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+temp.getHypervisorToken())
		        .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort()+"/api2/json")
	            .clientConnector(new ReactorClientHttpConnector(httpClient))
			    .build();
			
			     proxmoxClient.post()
				.uri("/nodes/pve/qemu/"+vmId+"/status/reboot")
				.contentLength(0)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
			     
			     vmStatsRepo.UpdateVmData("reboot", vmId, edgeClientId);
			     
			     logger.info("Restarting VM  "+vmId);
			
		} catch (Exception e) {
			
			logger.error("Unable To Restart VM  "+vmId+""+e);
		}
	}

	@Override
	public void deleteVm(Long vmId,String edgeClientId) {
		try {
			
			InternalDataModels temp=internalDataRepository.getData(edgeClientId);
			SslContext context = SslContextBuilder.forClient()
				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				    .build();
				                
			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
		
			WebClient proxmoxClient = WebClient.builder()
				.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+temp.getHypervisorToken())
		        .baseUrl("https://"+temp.getHypervisorIp()+":"+temp.getHypervisorPort()+"/api2/json")
	            .clientConnector(new ReactorClientHttpConnector(httpClient))
			    .build();
			
				stopVm(vmId,edgeClientId);
				Thread.sleep(10000);
				 proxmoxClient.delete()
				.uri("/nodes/pve/qemu/"+vmId)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
				
				vmStatsRepo.deleteById(vmId,edgeClientId);
				deleteVmRepository.deleteById(vmId,edgeClientId);
				alertVmRepository.deleteDataOfVm(edgeClientId, vmId);
			
		} catch (Exception e) {
			
			logger.error("Unable To Delete VM  "+vmId+""+e);
		}
	}

	@Override
	@Scheduled(fixedRateString = "${edge.delete.time}")
	public void checkDeletedVm() {
		
        try {
			
            List<InternalDataModels> list=internalDataRepository.findAll();
    	    
    	    for (InternalDataModels internalDataModel : list) {
    	    	
    	    	List<Long> temp=vmStatsRepo.getVm(internalDataModel.getEdgeClientId());
    	    	
    	    	for (Long qwert : temp)
    	    	{
    	    		if(deleteVmRepository.countVmId(qwert,internalDataModel.getEdgeClientId())==0)
    	    		{
    	    			DeleteVmStatusModel vmStatusModel=new DeleteVmStatusModel();
        	    		
        	    		vmStatusModel.setVmId(qwert);
        	    		vmStatusModel.setStatus(false);
        	    		vmStatusModel.setEdgeClientId(internalDataModel.getEdgeClientId());
        	    		
        	    		deleteVmRepository.save(vmStatusModel);
						logger.info("VM ID '{}' not found for Edge Client ID '{}'. Status set to 'false'.", qwert, internalDataModel.getEdgeClientId());}else {
    	    			
    	    			deleteVmRepository.updateData(false,qwert,internalDataModel.getEdgeClientId());

    	    		}
					logger.info("VM with ID '{}' found for Edge Client ID '{}'. Status updated to 'false'.", qwert, internalDataModel.getEdgeClientId());
    	    	}
    	    	
    	    	SslContext context = SslContextBuilder.forClient()
    				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
    				    .build();
    				                
    			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
    			
    			
    			WebClient proxmoxClient = WebClient.builder()
    				.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
    		        .baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
    	            .clientConnector(new ReactorClientHttpConnector(httpClient))
    			    .build();
    			
    			HypervisorQemuInfoRootDto getStatsFromHypervisor = proxmoxClient.get()
    						.uri("/nodes/pve/qemu/")
    				        .retrieve()
    				        .bodyToMono(HypervisorQemuInfoRootDto.class)
    				        .block();
    		    			
    			ArrayList<HypervisorQemuInfoDto> getStatFromHypervisor = getStatsFromHypervisor.getData();
    			for (HypervisorQemuInfoDto hypervisorQemuInfoDto : getStatFromHypervisor)
    			{
    				if(hypervisorQemuInfoDto.getVmid().equals(null))
    				{
    					vmStatsRepo.deleteVMStatsTable(internalDataModel.getEdgeClientId());
    					deleteVmRepository.deleteEdgeId(internalDataModel.getEdgeClientId());
    					alertVmRepository.deleteEdgeOfVm(internalDataModel.getEdgeClientId());
						logger.info("VM ID is null; cleaned up for Edge Client ID '{}'.", internalDataModel.getEdgeClientId());
    				}else {
        				deleteVmRepository.updateDeleteStatusData(true, hypervisorQemuInfoDto.getVmid(), internalDataModel.getEdgeClientId());
						logger.info("VM ID '{}' updated to 'true' for Edge Client ID '{}'.", hypervisorQemuInfoDto.getVmid(), internalDataModel.getEdgeClientId());}
    				
    			}
    			
    			List<Long> data=deleteVmRepository.getStatus();
                for (Long model : data) {
					
                	vmStatsRepo.deleteById(model,internalDataModel.getEdgeClientId());
                	deleteVmRepository.deleteById(model, internalDataModel.getEdgeClientId());
                	alertVmRepository.deleteDataOfVm(internalDataModel.getEdgeClientId(), model);
				}
    			
    	    }
		} catch (Exception e) {
			
			logger.error("Proxmox deletion failed: "+e);
		}
		
	}

	@Override
	@Scheduled(fixedRate = 10000)
	public void deleteGraphCpuAndMemory() {

		if(cpuGraphRepository.getDataExist()>10 && memoryGraphRepository.getDataExist()>10)
		{
			cpuGraphRepository.deleteData();
			memoryGraphRepository.deleteData();
			logger.info("Deleted CPU and memory graph data.");
		}
		
	}

}

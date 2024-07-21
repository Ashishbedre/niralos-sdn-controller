package com.other.app.niralos_edge.Service.VMDetails.Impl;
 
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLException;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.niralos_edge.Model.HypervisorNetworkStatsModel;
import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.VmNetworkStatsModel;
import com.other.app.niralos_edge.Model.VmStatsModel;
import com.other.app.niralos_edge.Repository.HypervisorNetworkStatsRepo;
import com.other.app.niralos_edge.Repository.HypervisorStatsRepo;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Repository.VmNetworkInterfaceRepo;
import com.other.app.niralos_edge.Repository.VmStatsRepo;
import com.other.app.niralos_edge.Service.VMDetails.VmStatsService;
import com.other.app.niralos_edge.Service.VmCreation.SpicePortGenService;
import com.other.app.niralos_edge.dto.EdgeFrontendDto;
import com.other.app.niralos_edge.dto.GetVmData;
import com.other.app.niralos_edge.dto.HypervisorCreateVmDto;
import com.other.app.niralos_edge.dto.HypervisorQemuInfoDto;
import com.other.app.niralos_edge.dto.HypervisorQemuInfoRootDto;
import com.other.app.niralos_edge.dto.VmIdreturn;
import com.other.app.niralos_edge.dto.VmNetworkStatsDto;
import com.other.app.niralos_edge.dto.VmUpdateDto;
import com.other.app.niralos_edge.dto.hypervisorcookiedto.RootHypervisorCookieDto;
import com.other.app.niralos_edge.dto.vmmodificationcheckdto.RootVmModificationCheckDto;
import com.other.app.niralos_edge.dto.vmnetworkstatsdto.VmNetworkStatsRootDto;
import com.other.app.niralos_edge.dto.vmstatsdto.Data;
import com.other.app.niralos_edge.dto.vmstatsdto.VmStatsRootDto;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;
 
@Service
@EnableScheduling
public class VmStatsServiceImpl implements VmStatsService{
 
	private static final Logger logger=LoggerFactory.getLogger(VmStatsServiceImpl.class);
	
	@Autowired
	VmStatsRepo vmStatsRepo;
	
	@Autowired
	InternalDataRepositorys dataRepository;
	
	@Autowired
	VmNetworkInterfaceRepo interfaceRepo;
	
	@Autowired
	InternalDataRepository internalDataRepository;
	
	@Autowired
    HypervisorStatsRepo hypervisorStatsRepo;
	
	@Autowired
	HypervisorNetworkStatsRepo hypervisorNetworkStatsRepo;
	
	@Autowired
	SpicePortGenService genService;
	
	
	@Value("${edge.vm.time}")
	private String vmTime;
	
	
	@Override
	@Scheduled(fixedRateString = "${edge.vm.time}")
	public void getVmStats() {
		
		try {
	    	  
    	    List<InternalDataModels> list=dataRepository.findAll();
    	    
    	    for (InternalDataModels internalDataModel : list) {
    	    	
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
    				for (HypervisorQemuInfoDto hypervisorQemuInfoDto : getStatFromHypervisor) {
        				
    					
        				try {
							
        					logger.info("Receiving the Data");
        					
        					VmStatsRootDto getStatsFromVm = proxmoxClient.get()
        							.uri("/nodes/pve/qemu/"+hypervisorQemuInfoDto.getVmid()+"/status/current")
        					        .retrieve()
        					        .bodyToMono(VmStatsRootDto.class)
        					        .block();
        		
        					Data dumpVmStats = getStatsFromVm.getData();
        					Double cpuUsage =dumpVmStats.getCpu();
        		        	Double calCpuUsage = cpuUsage*100;
        		        	Double calMemUsageInUnits = dumpVmStats.getMem()/1048576;
        		        	Double calMemUsage = dumpVmStats.getMem()/dumpVmStats.getMaxmem()*100;
        		        	Long calTotalDiskSpaceAvailable = dumpVmStats.getMaxdisk()/1073741824;
        		        	Long calTotalMemAvailable = dumpVmStats.getMaxmem()/1073741824;
        		        	
        					RootVmModificationCheckDto getVmModificationConfig = proxmoxClient.get()
        							.uri("/nodes/pve/qemu/"+hypervisorQemuInfoDto.getVmid()+"/config")
        					        .retrieve()
        					        .bodyToMono(RootVmModificationCheckDto.class)
        					        .block();
        					
        					String input = getVmModificationConfig.getData().getArgs();
        					
        					Pattern pattern = Pattern.compile("port=(\\d+)");
 
        					Matcher matcher = pattern.matcher(input);
 
        				    // Extract and print the port number if found, otherwise print a message
        				    String spicePort = matcher.find() ? matcher.group(1) : "Spice Port not found";
        					
        		        	Long varCores = getVmModificationConfig.getData().getCores();
        		        	Long varSockets = getVmModificationConfig.getData().getSockets();
 
        					if(vmStatsRepo.getDataExist(dumpVmStats.getVmid(), internalDataModel.getEdgeClientId())==0)	
        					{
        						VmStatsModel dumpStatsToDb=new VmStatsModel();
        						
        						dumpStatsToDb.setCores(varCores);
        						dumpStatsToDb.setEdgeClientId(internalDataModel.getEdgeClientId());
        						dumpStatsToDb.setSockets(varSockets);
        						dumpStatsToDb.setSpicePort(spicePort);
        						dumpStatsToDb.setVmAgent(dumpVmStats.getAgent());
        						dumpStatsToDb.setVmCurrentCpuUsage(calCpuUsage);
        						dumpStatsToDb.setVmCurrentMemUsage(calMemUsage);
        						dumpStatsToDb.setVmCurrentMemUsageInUnits(calMemUsageInUnits);
        						dumpStatsToDb.setVmId(dumpVmStats.getVmid());
        						dumpStatsToDb.setVmName(dumpVmStats.getName());
        						dumpStatsToDb.setVmNetin(dumpVmStats.getNetin());
        						dumpStatsToDb.setVmNetout(dumpVmStats.getNetout());
        						dumpStatsToDb.setVmStatus(dumpVmStats.getStatus());
        						dumpStatsToDb.setVmTotalCpusAvailable(dumpVmStats.getCpus());
        						dumpStatsToDb.setVmTotalDiskSpace(calTotalDiskSpaceAvailable);
        						dumpStatsToDb.setVmTotalMemAvailable(calTotalMemAvailable);
        						dumpStatsToDb.setVmUptime(dumpVmStats.getUptime());
        						
        						vmStatsRepo.save(dumpStatsToDb);
        					}else {
        						
        						vmStatsRepo.UpdateData(dumpVmStats.getName(), dumpVmStats.getStatus(), calCpuUsage, dumpVmStats.getCpus(), calMemUsage, calMemUsageInUnits, calTotalMemAvailable, calTotalDiskSpaceAvailable, dumpVmStats.getNetin(), dumpVmStats.getNetout(), dumpVmStats.getUptime(), dumpVmStats.getAgent(), varCores, varSockets, spicePort, dumpVmStats.getVmid(), internalDataModel.getEdgeClientId());
        					}
        					
        					
						} catch (Exception e) {
							
							try {
								
								Integer spicePort = genService.generateUniqueNumber();
								String spicePortStr = spicePort.toString();
								String spiceConfig ="-spice port="+spicePortStr+",addr=0.0.0.0,disable-ticketing=on";
								
								MultiValueMap<String, String> hypervisorCreds = new LinkedMultiValueMap<>();
								hypervisorCreds.add("username", internalDataModel.getUserName());
								hypervisorCreds.add("password", internalDataModel.getPassword());
								
								WebClient proxmoxClient2 = WebClient.builder()
					  		            .baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
					  		            .clientConnector(new ReactorClientHttpConnector(httpClient))
					  				    .build();
								
								RootHypervisorCookieDto cookieVal = proxmoxClient2.post()
										.uri("/access/ticket")
										.contentType(MediaType.APPLICATION_FORM_URLENCODED)
										.bodyValue(hypervisorCreds)
										.retrieve()
										.bodyToMono(RootHypervisorCookieDto.class)
										.block();
								
								SslContext context1 = SslContextBuilder.forClient()
				  					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				  					    .build();
				  					                
				  				HttpClient httpClient1 = HttpClient.create().secure(t -> t.sslContext(context1));
				  			
				  				WebClient proxmoxClient1 = WebClient.builder()
				  		            .baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
				  		            .defaultHeader(HttpHeaders.COOKIE,"PVEAuthCookie="+cookieVal.getData().getTicket())
				  		            .defaultHeader("CSRFPreventionToken", cookieVal.getData().getCsrftoken())
				  		            .clientConnector(new ReactorClientHttpConnector(httpClient1))
				  				    .build();
								
				  				MultiValueMap<String, String> vmUpdate = new LinkedMultiValueMap<>();
				  				vmUpdate.add("args", spiceConfig);
				  				
								proxmoxClient1.post()
								.uri("/nodes/pve/qemu/"+hypervisorQemuInfoDto.getVmid()+"/config")
						        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
						        .body(BodyInserters.fromFormData(vmUpdate))
						        .retrieve()
						        .bodyToMono(String.class)
						        .block();
						        
								logger.info("VM Updated Successfully");
 
							} catch (Exception e2) {
								
								logger.error("Unable to update Args in VM"+e2);
							}
							
						}
        					
        						
        			}
    				
			}
			
			
		} catch (Exception e) {
			
			logger.error("Unable To Get VM Statistics. Check If VM Or Hypervisor Is Running."+e);
		}
		
		
		}
 
 
	@Override
	@Scheduled(fixedRateString = "${edge.vm.time}")
	public void saveVmNetworkStats() {
		try {
			
			List<InternalDataModels> list1=dataRepository.findAll();
			
			for (InternalDataModels internalDataModel : list1) {
				
				SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
					.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
			        .baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
				
			 	List<VmStatsModel> getVmIdFromRepo = vmStatsRepo.findData(internalDataModel.getEdgeClientId());
				for (VmStatsModel vmDataModel : getVmIdFromRepo) {
 
				        	try {
				        		VmNetworkStatsRootDto getNetworkInterfaceData = proxmoxClient.get()
		    							.uri("/nodes/pve/qemu/"+vmDataModel.getVmId()+"/config")
		    					        .retrieve()
		    					        .bodyToMono(VmNetworkStatsRootDto.class)
		    					        .block();
					        	com.other.app.niralos_edge.dto.vmnetworkstatsdto.Data result = getNetworkInterfaceData.getData();
									
									if(interfaceRepo.checkIfInterfaceNameExists(vmDataModel.getVmId(),internalDataModel.getEdgeClientId())==0) {
										VmNetworkStatsModel dumpVmNetworkInterface = new VmNetworkStatsModel();
										dumpVmNetworkInterface.setEdgeClientId(internalDataModel.getEdgeClientId());
										dumpVmNetworkInterface.setNet0(result.getNet0());
										dumpVmNetworkInterface.setNet1(result.getNet1());
										dumpVmNetworkInterface.setNet10(result.getNet10());
										dumpVmNetworkInterface.setNet2(result.getNet2());
										dumpVmNetworkInterface.setNet3(result.getNet3());
										dumpVmNetworkInterface.setNet4(result.getNet4());
										dumpVmNetworkInterface.setNet5(result.getNet5());
										dumpVmNetworkInterface.setNet6(result.getNet6());
										dumpVmNetworkInterface.setNet7(result.getNet7());
										dumpVmNetworkInterface.setNet8(result.getNet8());
										dumpVmNetworkInterface.setNet9(result.getNet9());
										dumpVmNetworkInterface.setVmId(vmDataModel.getVmId());
										dumpVmNetworkInterface.setVmName(result.getName());
 
										interfaceRepo.save(dumpVmNetworkInterface);
									}
									else {
										interfaceRepo.updateVmNetworkInterface(result.getNet0(), result.getNet1(), result.getNet2(), result.getNet3(),result.getNet4(),result.getNet5(),result.getNet6(),result.getNet7(),result.getNet8(),result.getNet9(),result.getNet10(), vmDataModel.getVmId(),internalDataModel.getEdgeClientId(),result.getName());
									}
									 
							} catch (Exception e) {
								
								logger.error("Unable To Get Network Interface Details VM Maybe Not Running Or Qemu Agent Not Installed on VM: "+e);
								
							}
				        	
							
					}
					
				}
			
		} catch (Exception e) {
			
			logger.error("Error Occurred. Unable To Get VM Network Interface Details. Check If Hypervisor Is Running."+e);
		}
	}
 
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
    //Getting All VM AND Network Interface Details
	@Override
	public ArrayList<EdgeFrontendDto> getVmDetailsParticularSite(String edgeClientId) {
		 SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String deploymentId = deployedItem.getDeploymentId();
		
		ArrayList<EdgeFrontendDto> edgeFrontDto = new ArrayList<>();
 
		List<VmStatsModel> vmModelsList = vmStatsRepo.findData(edgeClientId);
 
		try {
			
			for (VmStatsModel vmModel : vmModelsList) {
 
				EdgeFrontendDto edgeFrontendDto = new EdgeFrontendDto();
				edgeFrontendDto.setVmId(vmModel.getVmId());
				edgeFrontendDto.setEdgeClientId(vmModel.getEdgeClientId());
				edgeFrontendDto.setVmName(vmModel.getVmName());
				edgeFrontendDto.setVmStatus(vmModel.getVmStatus());
				edgeFrontendDto.setVmCurrentCpuUsage(vmModel.getVmCurrentCpuUsage());
				edgeFrontendDto.setVmTotalCpusAvailable(vmModel.getVmTotalCpusAvailable());
				edgeFrontendDto.setVmCurrentMemUsage(vmModel.getVmCurrentMemUsage());
				edgeFrontendDto.setVmCurrentMemUsageInUnits(vmModel.getVmCurrentMemUsageInUnits());
				edgeFrontendDto.setVmTotalMemAvailable(vmModel.getVmTotalMemAvailable());
				edgeFrontendDto.setVmTotalDiskSpace(vmModel.getVmTotalDiskSpace());
				edgeFrontendDto.setVmNetin(vmModel.getVmNetin());
				edgeFrontendDto.setVmNetout(vmModel.getVmNetout());
				edgeFrontendDto.setVmUptime(vmModel.getVmUptime());
				edgeFrontendDto.setVmAgent(vmModel.getVmAgent());
 
				List<VmNetworkStatsModel> vmNetworkInterfaces = interfaceRepo.findByVmId(vmModel.getVmId(),vmModel.getEdgeClientId());
				List<VmNetworkStatsDto> list = new ArrayList<>();
				for (VmNetworkStatsModel interfaceobj : vmNetworkInterfaces) {
 
					VmNetworkStatsDto obj = new VmNetworkStatsDto();
 
					obj.setVmId(interfaceobj.getVmId());
					obj.setVmName(interfaceobj.getVmName());
					obj.setEdgeClientId(interfaceobj.getEdgeClientId());
					obj.setNet0(interfaceobj.getNet0());
					obj.setNet1(interfaceobj.getNet1());
					obj.setNet2(interfaceobj.getNet2());
					obj.setNet3(interfaceobj.getNet3());
					obj.setNet4(interfaceobj.getNet4());
					obj.setNet5(interfaceobj.getNet5());
					obj.setNet6(interfaceobj.getNet6());
					obj.setNet7(interfaceobj.getNet7());
					obj.setNet8(interfaceobj.getNet8());
					obj.setNet9(interfaceobj.getNet9());
					obj.setNet10(interfaceobj.getNet10());
					obj.setTenantId(deployedItem.getTenantName());
					obj.setSiteId(deployedItem.getSiteName());
					
					list.add(obj);
				}
 
				edgeFrontendDto.setVmNetworkInterfaces(list);
				edgeFrontDto.add(edgeFrontendDto);
 
			}
 
		} catch (Exception e) {
			logger.error("Exception At Specific EdgeFrontend"+e);
		}
 
		return edgeFrontDto;
	}
 
 
	@Override
	public EdgeFrontendDto getParticularVm(Long vmId,String edgeClientId) {
		 SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String deploymentId = deployedItem.getDeploymentId();
        VmStatsModel model=vmStatsRepo.findDataForVm(vmId,edgeClientId);
		
		EdgeFrontendDto data=new EdgeFrontendDto();
		data.setEdgeClientId(edgeClientId);
		data.setVmAgent(model.getVmAgent());
		data.setVmCurrentCpuUsage(model.getVmCurrentCpuUsage());
		data.setVmCurrentMemUsage(model.getVmCurrentMemUsage());
		data.setVmCurrentMemUsageInUnits(model.getVmCurrentMemUsageInUnits());
		data.setVmId(model.getVmId());
		data.setVmName(model.getVmName());
		data.setVmNetin(model.getVmNetin());
		data.setVmNetout(model.getVmNetout());
		data.setVmStatus(model.getVmStatus());
		data.setVmTotalCpusAvailable(model.getVmTotalCpusAvailable());
		data.setVmTotalDiskSpace(model.getVmTotalDiskSpace());
		data.setVmTotalMemAvailable(model.getVmTotalMemAvailable());
		data.setVmUptime(model.getVmUptime());
		
		List<VmNetworkStatsDto> temp=new ArrayList<>();
		List<VmNetworkStatsModel> model1=interfaceRepo.findByVmId(vmId,data.getEdgeClientId());
		
		for (VmNetworkStatsModel vmNetworkStatsModel : model1) {
			
			VmNetworkStatsDto mm=new VmNetworkStatsDto();
			
			mm.setEdgeClientId(deploymentId);
			
			
		
			
			String net0 = vmNetworkStatsModel.getNet0();
			if (net0 != null) {
			String neeedto[]  = net0.split("bridge=", 0);
			String finaldata[] = neeedto[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,finaldata[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : hypervisorNetworkStatsModels) {
			mm.setNet0(vmNetworkStatsModel.getNet0()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
			
			String net1 = vmNetworkStatsModel.getNet1();
			if (net1 != null) {
			String net1data[]  = net1.split("bridge=", 0);
			String net1Final[] = net1data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net1hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net1Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net1hypervisorNetworkStatsModels) {
				mm.setNet1(vmNetworkStatsModel.getNet1()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
//			try {
			String net2 = vmNetworkStatsModel.getNet2();
			if (net2 != null) {
			String net2data[]  = net2.split("bridge=", 0);
			String net2Final[] = net2data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net2hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net2Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net2hypervisorNetworkStatsModels) {
				mm.setNet2(vmNetworkStatsModel.getNet2()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
			String net3 = vmNetworkStatsModel.getNet3();
			if (net3 != null) {
			String net3data[]  = net3.split("bridge=", 0);
			String net3Final[] = net3data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net3hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net3Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net3hypervisorNetworkStatsModels) {
				mm.setNet3(vmNetworkStatsModel.getNet3()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
//			
//			
			String net4 = vmNetworkStatsModel.getNet4();
			if (net4 != null) {
			String net4data[]  = net4.split("bridge=", 0);
			String net4Final[] = net4data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net4hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net4Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net4hypervisorNetworkStatsModels) {
				mm.setNet4(vmNetworkStatsModel.getNet4()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
			String net5 = vmNetworkStatsModel.getNet5();
			if (net5 != null) {
			String net5data[]  = net5.split("bridge=", 0);
			String net5Final[] = net5data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net5hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net5Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net5hypervisorNetworkStatsModels) {
				mm.setNet5(vmNetworkStatsModel.getNet5()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
			String net6 = vmNetworkStatsModel.getNet6();
			if (net6 != null) {
			String net6data[]  = net6.split("bridge=", 0);
			String net6Final[] = net6data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net6hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net6Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net6hypervisorNetworkStatsModels) {
				mm.setNet6(vmNetworkStatsModel.getNet6()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
			String net7 = vmNetworkStatsModel.getNet7();
			if (net7 != null) {
			String net7data[]  = net7.split("bridge=", 0);
			String net7Final[] = net7data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net7hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net7Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net7hypervisorNetworkStatsModels) {
				mm.setNet7(vmNetworkStatsModel.getNet7()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			String net8 = vmNetworkStatsModel.getNet8();
			if (net8 != null) {
			String net8data[]  = net8.split("bridge=", 0);
			String net8Final[] = net8data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net8hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net8Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net8hypervisorNetworkStatsModels) {
				mm.setNet8(vmNetworkStatsModel.getNet8()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
			String net9 = vmNetworkStatsModel.getNet9();
			if (net9 != null) {
			String net9data[]  = net9.split("bridge=", 0);
			String net9Final[] = net9data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net9hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net9Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net9hypervisorNetworkStatsModels) {
				mm.setNet9(vmNetworkStatsModel.getNet9()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
 
			String net10 = vmNetworkStatsModel.getNet10();
			if (net10 != null) {
			String net10data[]  = net10.split("bridge=", 0);
			String net10Final[] = net10data[1].split(",",0);
			
			List<HypervisorNetworkStatsModel>net10hypervisorNetworkStatsModels = hypervisorNetworkStatsRepo.returnAllsubnetsreletedtoInterfaces(edgeClientId,net10Final[0]);
			for (HypervisorNetworkStatsModel hypervisorNetworkStatsModel : net10hypervisorNetworkStatsModels) {
				mm.setNet10(vmNetworkStatsModel.getNet10()+",subnet="+hypervisorNetworkStatsModel.getCidr());
			}
			}
			
//			mm.setNet10(vmNetworkStatsModel.getNet10());
			mm.setVmName(vmNetworkStatsModel.getVmName());
			mm.setSiteId(deployedItem.getSiteName());
			mm.setTenantId(deployedItem.getTenantName());
			mm.setVmId(vmId);
			
			temp.add(mm);
 
			}
		data.setVmNetworkInterfaces(temp);
		return data;
	}
 
 
	@Override
	public VmUpdateDto getDetails(String edgeClientId) {
		
        VmUpdateDto data=new VmUpdateDto();
		
		data.setMemory(""+1024L*hypervisorStatsRepo.getTotalMemory(edgeClientId));
		data.setCores(""+hypervisorStatsRepo.getCpuCores(edgeClientId));
		data.setSockets(""+hypervisorStatsRepo.getCpuSockets(edgeClientId));
		
		return data;
	}
 
 
	@Override
	public HypervisorCreateVmDto getVm(String edgeClientId) {
        
		
		InternalDataModels internalDataModels=dataRepository.getData(edgeClientId);
    	
    	SslContext context;
		try {
			context = SslContextBuilder.forClient()
				    .trustManager(InsecureTrustManagerFactory.INSTANCE)
				    .build();
		                
		HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
	
		WebClient proxmoxClient = WebClient.builder()
            .defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModels.getHypervisorToken())
            .baseUrl("https://"+internalDataModels.getHypervisorIp()+":"+internalDataModels.getHypervisorPort())
            .clientConnector(new ReactorClientHttpConnector(httpClient))
		    .build();
		
		
		VmIdreturn data=proxmoxClient.get()
		.uri("/api2/json/cluster/nextid")
        .retrieve()
        .bodyToMono(VmIdreturn.class)
        .timeout(Duration.ofSeconds(1))
        .block();
 
		HypervisorStatsModel model=hypervisorStatsRepo.getData(edgeClientId);
		
		HypervisorCreateVmDto dto=new HypervisorCreateVmDto();
		
		dto.setVmId(Long.parseLong(data.getData()));
		dto.setAvailableDiskspace(model.getFreeDiskSpace());
		dto.setAvailableMemory(model.getAvailableRamSpace());
		dto.setEdgeClientId(edgeClientId);
		dto.setTotalCores(model.getCpuCores());
		dto.setTotalSockets(model.getCpuSockets());
		
		return dto;
		
		} catch (SSLException e) {
			// TODO Auto-generated catch block
			logger.error(""+e);
			
		}
		return null;
			
	}
 
 
	@Override
	public GetVmData getData(Long vmId, String edgeClientId) {
		
		GetVmData temp=new GetVmData();
		temp.setCores(vmStatsRepo.getCores(vmId, edgeClientId));
		temp.setMemory(vmStatsRepo.getMemory(vmId, edgeClientId));
		temp.setSockets(vmStatsRepo.getSockets(vmId, edgeClientId));
		temp.setVmId(vmId);
		
		return temp;
	}
}

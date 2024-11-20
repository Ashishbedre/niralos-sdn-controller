package com.other.app.niralos_edge.Service.HypervisorStat.Backend.impl;
 
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.niralos_edge.Model.HypervisorLogModel;
import com.other.app.niralos_edge.Model.HypervisorNetworkStatsModel;
import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.HypervisorTaskLogModel;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.ListOfIsoModel;
import com.other.app.niralos_edge.Model.graph.CpuGraphModel;
import com.other.app.niralos_edge.Model.graph.MemoryGraphModel;
import com.other.app.niralos_edge.Repository.HypervisorLogRepo;
import com.other.app.niralos_edge.Repository.HypervisorNetworkStatsRepo;
import com.other.app.niralos_edge.Repository.HypervisorStatsRepo;
import com.other.app.niralos_edge.Repository.HypervisorTaskLogRepository;
import com.other.app.niralos_edge.Repository.graph.CpuGraphRepository;
import com.other.app.niralos_edge.Repository.graph.MemoryGraphRepository;
import com.other.app.niralos_edge.Service.HypervisorStat.Backend.HypervisorStatsService;
import com.other.app.niralos_edge.dto.HypervisorLogDto;
import com.other.app.niralos_edge.dto.HypervisorStatsDto;
import com.other.app.niralos_edge.dto.hypervisorTaskLog.HypervisorTaskLogDto;
import com.other.app.niralos_edge.dto.hypervisorlogdto.HypervisorLogRootDto;
import com.other.app.niralos_edge.dto.hypervisornetworkstatsdto.HypervisorNetworkStatsDto;
import com.other.app.niralos_edge.dto.hypervisornetworkstatsdto.InData;
import com.other.app.niralos_edge.dto.hypervisorstatsdto.Data;
import com.other.app.niralos_edge.dto.hypervisorstatsdto.HypervisorStatsRootDto;
import com.other.app.niralos_edge.dto.listofisodto.Datum;
import com.other.app.niralos_edge.dto.listofisodto.ListOfIsoRootDto;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;
 
@Configuration
@EnableScheduling
@Service
public class HypervisorStatsServiceImpl implements HypervisorStatsService {

	private static final Logger logger =LoggerFactory.getLogger("EDGE:HypervisorStatsServiceImpl");
	
	@Autowired
	HypervisorStatsRepo hypervisorStatsRepo;
	
	@Autowired
	HypervisorNetworkStatsRepo hypervisorNetworkStatsRepo;
	
	@Autowired
	InternalDataRepository internalDataRepository;
	
	@Autowired
	CpuGraphRepository cpuGraphRepository;
	 
	@Autowired
	MemoryGraphRepository memoryGraphRepository;
	
	@Autowired
	HypervisorLogRepo hypervisorLogRepo;
	
	@Autowired
	com.other.app.niralos_edge.Repository.InternalDataRepositorys repository;
	
	@Autowired
	HypervisorTaskLogRepository hypervisorTaskLogRepository;
	
	@Value("${edge.hyerpvisor.time}")
	private String hypervisorTime;
	
	@Value("${edge.log.time}")
	private String hypervisorLogTime;
	
	@Value("${edge.task.log.time}")
	private String hypervisorTaskLogTime;
	
	//Function to save hypervisor stats
	@Override
	@Scheduled(fixedRateString = "${edge.hyerpvisor.time}")
	public void saveHypervisorStats() {
		try {
			logger.info("Processing the Hypervisor Stats.");
			List<InternalDataModels> data=repository.findAll();
			logger.info("Fetched {} internal data models from the repository.", data.size());
			for (InternalDataModels internalDataModel : data) {
				
				try {
					SslContext context = SslContextBuilder.forClient()
						    .trustManager(InsecureTrustManagerFactory.INSTANCE)
						    .build();
						                
					HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
				
					WebClient proxmoxClient = WebClient.builder()
			            //.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken=root@pam!Fm9TBVQW=c50eb962-7278-4d07-9047-20cee33eebc7")
			            .defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
			            .baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
			            .clientConnector(new ReactorClientHttpConnector(httpClient))
					    .build();
					
					HypervisorStatsRootDto getHypervisorStats = proxmoxClient.get()
							.uri("/nodes/pve/status/")
					        .retrieve()
					        .bodyToMono(HypervisorStatsRootDto.class)
					        .block();
					logger.info("Successfully retrieved hypervisor stats for VM ID '{}'.", internalDataModel.getEdgeClientId());
					//Process the extracted data
					Data extractHypervisorStats = getHypervisorStats.getData();
					Long TotalDiskSpace = extractHypervisorStats.getRootfs().getTotal();
					Long CalcTotalDiskSpace = TotalDiskSpace/1073741824;
					Long UsedDiskSpace = extractHypervisorStats.getRootfs().getUsed();
					Long CalcUsedDiskSpace = UsedDiskSpace/1073741824;
					Long FreeDiskSpace = extractHypervisorStats.getRootfs().getFree();
					Long CalcFreeDiskSpace = FreeDiskSpace/1073741824;
					Long TotalRamSpace = extractHypervisorStats.getMemory().getTotal();
					Double CalcTotalRamSpace = TotalRamSpace/1073741824.0;
					Long CurrentRamUsage = extractHypervisorStats.getMemory().getUsed();
					Long CalcCurrentRamUsage = CurrentRamUsage/1073741824;
					Double CurrentRamusage=Double.valueOf(CurrentRamUsage/1000000000.0);
					Long AvailableRamSpace = extractHypervisorStats.getMemory().getFree();
					Long CalcAvailableRamSpace = AvailableRamSpace/1073741824;
					Double CpuUsage = extractHypervisorStats.getCpu()*100;
					DecimalFormat df_obj = new DecimalFormat("#.##");
					String ConvCpuUsage = df_obj.format(CpuUsage);
					Double CalcCpuUsage = Double.parseDouble(ConvCpuUsage);
					
//					HypervisorStatsModel dumpHypervisorStats = new HypervisorStatsModel(CalcTotalDiskSpace, CalcUsedDiskSpace, CalcFreeDiskSpace, CalcTotalRamSpace, CalcCurrentRamUsage, CalcAvailableRamSpace, extractHypervisorStats.getCpuinfo().getModel(), CalcCpuUsage, extractHypervisorStats.getCpuinfo().getSockets(), extractHypervisorStats.getCpuinfo().getCpus(), extractHypervisorStats.getCpuinfo().getCores(), extractHypervisorStats.getUptime(), internalDataModel.getHypervisorNodeName(),internalDataModel.getEdgeClientId(),internalDataModel.getEdgeClientId());
					if(hypervisorStatsRepo.checkIfEdgeClientExist(internalDataModel.getEdgeClientId())==0)
					{
						HypervisorStatsModel dumpHypervisorStats=new HypervisorStatsModel();
						dumpHypervisorStats.setAvailableRamSpace(AvailableRamSpace);
						dumpHypervisorStats.setCpuCores(extractHypervisorStats.getCpuinfo().getCores());
						dumpHypervisorStats.setCpuModel(extractHypervisorStats.getCpuinfo().getModel());
						dumpHypervisorStats.setCpuSockets(extractHypervisorStats.getCpuinfo().getSockets());
						dumpHypervisorStats.setCpuUsage(CalcCpuUsage);
						dumpHypervisorStats.setCurrentRamUsage(CalcCurrentRamUsage);
						dumpHypervisorStats.setEdgeClientId(internalDataModel.getEdgeClientId());
						dumpHypervisorStats.setFreeDiskSpace(CalcFreeDiskSpace);
						dumpHypervisorStats.setNodeName(internalDataModel.getHypervisorNodeName());
						dumpHypervisorStats.setTotalCpus(extractHypervisorStats.getCpuinfo().getCpus());
						dumpHypervisorStats.setTotalDiskspace(CalcTotalDiskSpace);
						dumpHypervisorStats.setUpTime(extractHypervisorStats.getUptime());
						dumpHypervisorStats.setUsedDiskSpace(CalcUsedDiskSpace);
						dumpHypervisorStats.setTotalRamSpace(CalcTotalRamSpace);
						dumpHypervisorStats.setNodeStatus("Active");

						hypervisorStatsRepo.save(dumpHypervisorStats);
						logger.info("Saved new hypervisor stats for Edge Client ID '{}'.", internalDataModel.getEdgeClientId());
					}else {
						hypervisorStatsRepo.updateHypervisorStats(CalcTotalDiskSpace, CalcUsedDiskSpace, CalcFreeDiskSpace, CalcTotalRamSpace, CalcCurrentRamUsage, CalcAvailableRamSpace, extractHypervisorStats.getCpuinfo().getModel(), CalcCpuUsage, extractHypervisorStats.getCpuinfo().getSockets(), extractHypervisorStats.getCpuinfo().getCpus(), extractHypervisorStats.getCpuinfo().getCores(), extractHypervisorStats.getUptime(), internalDataModel.getHypervisorNodeName(), internalDataModel.getEdgeClientId(),"Active");
						logger.info("Updated hypervisor stats for Edge Client ID '{}'.", internalDataModel.getEdgeClientId());
					}
					
					
					CpuGraphModel model=new CpuGraphModel();
		    		model.setEdgeClientid(internalDataModel.getEdgeClientId());
		    		model.setCpuValue(CpuUsage);
		    		model.setDate(LocalDateTime.now());
		    		cpuGraphRepository.save(model);
		    		
		    		MemoryGraphModel data1=new MemoryGraphModel();
		    		data1.setMemoryValue(CurrentRamusage);
		    		data1.setDate(LocalDateTime.now());
		    		data1.setEdgeClientId(internalDataModel.getEdgeClientId());
		    		memoryGraphRepository.save(data1);
					
					//Dump the processed data to Db
					logger.info("Saved memory usage data for Edge Client ID '{}' with value '{}'.", internalDataModel.getEdgeClientId(), CurrentRamusage);
				} catch (Exception e) {
					hypervisorStatsRepo.updateHypervisorStatus("Inactive", internalDataModel.getEdgeClientId());
					
					logger.error("Unable to connect with proxmox"+e);
				}
				
			}
						
		} catch (Exception e) {
			logger.error("Error Occurred. Unable To Get Hypervisor Statistics"+e);
		}
		
	}
	
	//Function to save hypervisor network stats
	@Override
	@Scheduled(fixedRate = 3600) //3 second sync
	public void saveHypervisorNetworkStats() {
		
		try {
			
			List<InternalDataModels> temp=repository.findAll();
			
			for (InternalDataModels internalDataModel : temp) {
				try {

					SslContext context = SslContextBuilder.forClient()
							.trustManager(InsecureTrustManagerFactory.INSTANCE)
							.build();

					HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

					WebClient proxmoxClient = WebClient.builder()
						//.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken=root@pam!Fm9TBVQW=c50eb962-7278-4d07-9047-20cee33eebc7")
						.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
						.baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
						.clientConnector(new ReactorClientHttpConnector(httpClient))
						.build();


					HypervisorNetworkStatsDto getHypervisorNetworkStats = proxmoxClient.get()
							.uri("/nodes/pve/network/")
							.retrieve()
							.bodyToMono(HypervisorNetworkStatsDto.class)
							.block();

					List<InData> extractHypervisorNetworkStats = getHypervisorNetworkStats.getData();

					for (InData inData : extractHypervisorNetworkStats) {


						if(hypervisorNetworkStatsRepo.checkIfInterfaceNameExistsInHypervisor(inData.getIface(),internalDataModel.getEdgeClientId())==0) {

						HypervisorNetworkStatsModel hypervisorNetworkStats = new HypervisorNetworkStatsModel(inData.getType(), inData.getIface(), inData.getAutostart(), inData.getPriority(), inData.getActive(), inData.getAddress(), inData.getBridge_ports(), inData.getGateway(), inData.getCidr(), inData.getNetmask(),internalDataModel.getEdgeClientId());

						hypervisorNetworkStatsRepo.save(hypervisorNetworkStats);
						}
						else {
							hypervisorNetworkStatsRepo.updateHypervisorNetworkStats(inData.getType(), inData.getAutostart(), inData.getPriority(), inData.getActive(), inData.getAddress(), inData.getBridge_ports(), inData.getGateway(), inData.getCidr(), inData.getNetmask(), inData.getIface(),internalDataModel.getEdgeClientId());
						}

					}
				} catch (Exception e) {
					logger.error("Failed to retrieve or process network stats for Hypervisor IP: " +
							internalDataModel.getHypervisorIp() + ". Error: " + e.getMessage());
				}
			}
			logger.info("Successfully Processed Hypervisor Network Statistics");
 
		} catch (Exception e) {
			logger.error("Error Occurred. Unable To Get Hypervisor Network Statistics"+e.getMessage());
		}
		
	}
 
	@Override
	public List<ListOfIsoModel> getListOfIso(String edgeClientId) {
		
		List<ListOfIsoModel>  temp=new ArrayList<>(); 
		
		try {
			logger.info("Get All available list of ISO");
			InternalDataModels internalDataModel=repository.getData(edgeClientId);
			
				SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
					.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
			        .baseUrl("https://"+internalDataModel.getHypervisorIp()+":8006/api2/json")
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
				
				
				ListOfIsoRootDto getListOfIso =proxmoxClient.get()
						.uri("/nodes/pve/storage/local/content?content=iso")
				        .retrieve()
				        .bodyToMono(ListOfIsoRootDto.class)
				        .block();
				
				List<Datum> getListOfIsos = getListOfIso.getData();
				for (Datum datum : getListOfIsos) {
					
					ListOfIsoModel model=new ListOfIsoModel();
					
					model.setCreatedTime(datum.getCtime());
					model.setFormat(datum.getFormat());
					model.setSize(datum.getSize());
					model.setVolumeId(datum.getVolid());
					
					temp.add(model);
				}
			
			
		} catch (Exception e) {
			logger.error("Unable To Get List Of ISOs From Hypervisor"+e);
		}
			
		return temp;
	}
 
	@Override
	public List<HypervisorStatsModel> getAllHypervisor() {
 
		return hypervisorStatsRepo.findAll();
	}
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	@Override
	public ArrayList<com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto> getHypervisorNetworkStats(String edgeClientId) {
		//Ashish remove block
		 SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation();
		
        List<HypervisorNetworkStatsModel> networkStats=hypervisorNetworkStatsRepo.findByEdgeClientId(edgeClientId);
		
		ArrayList<com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto> data=new ArrayList<>();
		logger.info("Fetching the Hypervisor Network Statistics");
		for (HypervisorNetworkStatsModel list : networkStats) {
			
			com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto model=new com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto();
			  
			  model.setActive(list.getActive());
			  model.setAddress(list.getAddress());
			  model.setAutostart(list.getAutostart());
			  model.setBridge_ports(list.getBridge_ports());
			  model.setCidr(list.getCidr());
			  model.setGateway(list.getGateway());
			  model.setIface(list.getIface());
			  model.setNetmask(list.getNetmask());
			  model.setPriority(list.getPriority());
			  model.setType(list.getType());
			  model.setEdgeClientId(edgeClientId);
			  model.setSiteId(deployedItem.getSiteName());
			  model.setTenantId(deployedItem.getTenantName());
			  
			  data.add(model);
			
		}
		return data;
	}
 
	@Override
	public ArrayList<HypervisorStatsDto> getAllHypervisorForSite(String edgeClientId) {
 
		DecimalFormat df = new DecimalFormat("#.##");
		
		ArrayList<HypervisorStatsDto> hypervisorStatsDto=new ArrayList<>();
		
		List<HypervisorStatsModel> hypervisorStatsModelsobj=hypervisorStatsRepo.getDataForId(edgeClientId);
		logger.info("Getting the all Hypervisor Exists Site");
		for (HypervisorStatsModel hypervisorStatsModel : hypervisorStatsModelsobj) {
			
			HypervisorStatsDto hypervisorStatsModellss=new HypervisorStatsDto();
			
			hypervisorStatsModellss.setEdgeClientId(edgeClientId);
			hypervisorStatsModellss.setTotalDiskspace(hypervisorStatsModel.getTotalDiskspace());
			hypervisorStatsModellss.setUsedDiskSpace(hypervisorStatsModel.getUsedDiskSpace());
			hypervisorStatsModellss.setFreeDiskSpace(hypervisorStatsModel.getFreeDiskSpace());
			hypervisorStatsModellss.setDiskPercentage(Double.parseDouble(df.format((Double.parseDouble(""+hypervisorStatsModel.getUsedDiskSpace()+"")/Double.parseDouble(""+hypervisorStatsModel.getTotalDiskspace()+""))*100D)));
			//Ashish
			hypervisorStatsModellss.setTotalRamSpace(Double.parseDouble(String.format("%.2f", hypervisorStatsModel.getTotalRamSpace())));
			hypervisorStatsModellss.setCurrentRamUsage(hypervisorStatsModel.getCurrentRamUsage());
			hypervisorStatsModellss.setAvailableRamSpace(hypervisorStatsModel.getAvailableRamSpace());
			hypervisorStatsModellss.setRamPercentage(Double.parseDouble(df.format((Double.parseDouble(""+hypervisorStatsModel.getCurrentRamUsage()+"")/Double.parseDouble(""+hypervisorStatsModel.getTotalRamSpace()+""))*100D)));
			hypervisorStatsModellss.setCpuModel(hypervisorStatsModel.getCpuModel());
			hypervisorStatsModellss.setCpuUsage(hypervisorStatsModel.getCpuUsage());
			hypervisorStatsModellss.setCpuSockets(hypervisorStatsModel.getCpuSockets());
			hypervisorStatsModellss.setTotalCpus(hypervisorStatsModel.getTotalCpus());
			hypervisorStatsModellss.setCpuCores(hypervisorStatsModel.getCpuCores());
			hypervisorStatsModellss.setCpuPercentage(Double.parseDouble(df.format((Double.parseDouble(""+hypervisorStatsModel.getCpuCores()+"")/Double.parseDouble(""+hypervisorStatsModel.getTotalCpus()+""))*100D)));
			hypervisorStatsModellss.setUpTime(hypervisorStatsModel.getUpTime());
			hypervisorStatsModellss.setNodeName(hypervisorStatsModel.getNodeName());
			hypervisorStatsModellss.setNodeStatus(hypervisorStatsModel.getNodeStatus());
			
			List<CpuGraphModel> list=cpuGraphRepository.getGraphValue(edgeClientId);
			for (CpuGraphModel model : list) {
				Double originalCpuValue = model.getCpuValue();
				Double modifiedCpuValue = Double.parseDouble(String.format("%.2f", originalCpuValue)); // Round to 2 decimal places
				model.setCpuValue(modifiedCpuValue);
			}
			hypervisorStatsModellss.setCpuGraphModel(list);

			List<MemoryGraphModel> list1=memoryGraphRepository.getGraphValue(edgeClientId);
			for (MemoryGraphModel model : list1) {
				Double originalCpuValue = model.getMemoryValue();
				Double modifiedCpuValue = Double.parseDouble(String.format("%.2f", originalCpuValue)); // Round to 2 decimal places
				model.setMemoryValue(modifiedCpuValue);
			}
			
			hypervisorStatsModellss.setMemoryGraphModel(list1);
			hypervisorStatsDto.add(hypervisorStatsModellss);
			
			
		}
		return hypervisorStatsDto;
	}
 
	//saving the hypervisor log
	@Override
	@Scheduled(fixedRateString = "${edge.log.time}")
	public void saveHypervisorLog() {
 
		try {
			List<InternalDataModels> temp=repository.findAll();
			
			for (InternalDataModels internalDataModel : temp) {
				
				SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
		            //.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken=root@pam!Fm9TBVQW=c50eb962-7278-4d07-9047-20cee33eebc7")
		            .defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
		            .baseUrl("https://"+internalDataModel.getHypervisorIp()+":"+internalDataModel.getHypervisorPort()+"/api2/json")
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
				
				HypervisorLogRootDto getHypervisorStats = proxmoxClient.get()
						.uri("/nodes/pve/syslog/")
				        .retrieve()
				        .bodyToMono(HypervisorLogRootDto.class)
				        .block();
				
				List<com.other.app.niralos_edge.dto.hypervisorlogdto.Data> getData = getHypervisorStats.getData();
				for (com.other.app.niralos_edge.dto.hypervisorlogdto.Data data : getData) {
					HypervisorLogModel hypervisorLogModel = new HypervisorLogModel(data.getT(),internalDataModel.getEdgeClientId());
					hypervisorLogRepo.save(hypervisorLogModel);
				}
			}
			logger.info("Hypervisor Logs saved.");
			
		} catch (Exception e) {
			
			logger.error("Error Occurred. Unable To Get Hypervisor Logs"+e);
 
		}
	}
 
	@Override
	public List<HypervisorLogDto> getHypervisorLog(String edgeClientId) {
		//Ashish remove block
		SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation();
		List<HypervisorLogDto> temp=new ArrayList<>();
		List<HypervisorLogModel> model=hypervisorLogRepo.getLogData(edgeClientId);
		
		for (HypervisorLogModel hypervisorLogModel : model) {
			
			HypervisorLogDto logDto=new HypervisorLogDto();
			
			logDto.setLog(hypervisorLogModel.getLog());
			logDto.setEdgeClientId(edgeClientId);
			logDto.setSiteId(deployedItem.getSiteName());
			logDto.setTenantId(deployedItem.getTenantName());
			
			temp.add(logDto);
		}
		logger.info("Logs Saved Successfully");
		return temp;
	}
	
	@Override
	public List<com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto> getHypervisorBridgeNetworks(
			String edgeClientId) {
		//Ashish remove block
		SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation();
		   List<HypervisorNetworkStatsModel> networkStats=hypervisorNetworkStatsRepo.findNetworksEdgeClientId(edgeClientId);
		   
			ArrayList<com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto> data=new ArrayList<>();
			
			for (HypervisorNetworkStatsModel list : networkStats) {
				com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto model=new com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto();
				  
				  model.setActive(list.getActive());
				  model.setAddress(list.getAddress());
				  model.setAutostart(list.getAutostart());
				  model.setBridge_ports(list.getBridge_ports());
				  model.setCidr(list.getCidr());
				  model.setGateway(list.getGateway());
				  model.setIface(list.getIface());
				  model.setNetmask(list.getNetmask());
				  model.setPriority(list.getPriority());
				  model.setType(list.getType());
				  model.setEdgeClientId(edgeClientId);
				  model.setSiteId(deployedItem.getSiteName());
				  model.setTenantId(deployedItem.getTenantName());
				  
				  data.add(model);
				
			}
			logger.info(" Hypervisor bridge networks data Saved Successfully.");
			return data;
	}
 
	@Override
	@Scheduled(fixedRateString = "${edge.task.log.time}")
	public void getLogData() {
		
		List<InternalDataModels> data=repository.findAll();
		
		try {
			for (InternalDataModels internalDataModel : data) {
				
				
				SslContext context = SslContextBuilder.forClient()
					    .trustManager(InsecureTrustManagerFactory.INSTANCE)
					    .build();
					                
				HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
			
				WebClient proxmoxClient = WebClient.builder()
					.defaultHeader(HttpHeaders.AUTHORIZATION, "PVEAPIToken="+internalDataModel.getHypervisorToken())
			        .baseUrl("https://"+internalDataModel.getHypervisorIp()+":8006/api2/json")
		            .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .build();
				
			HypervisorTaskLogDto client=proxmoxClient.get()
				.uri("/cluster/tasks")
		        .retrieve()
		        .bodyToMono(HypervisorTaskLogDto.class)
		        .block();
			
			ArrayList<HypervisorTaskLogModel> temp=client.getData();
			
			for (HypervisorTaskLogModel dataModel : temp) {
				
				if(hypervisorTaskLogRepository.getDataExist(dataModel.getStarttime(),dataModel.getEndtime())==0)
				{
					HypervisorTaskLogModel cmd=new HypervisorTaskLogModel();
					
					cmd.setEndtime(dataModel.getEndtime());
					cmd.setId(dataModel.getId());
					cmd.setNode(dataModel.getNode());
					cmd.setSaved(dataModel.getSaved());
					cmd.setStarttime(dataModel.getStarttime());
					cmd.setStatus(dataModel.getStatus());
					cmd.setTokenid(dataModel.getTokenid());
					cmd.setType(dataModel.getType());
					cmd.setUpid(dataModel.getUpid());
					cmd.setUser(dataModel.getUser());
					cmd.setEdgeClientId(internalDataModel.getEdgeClientId());
					
					hypervisorTaskLogRepository.save(cmd);
					
				}else {
					
					hypervisorTaskLogRepository.UpdateData(dataModel.getUser(), dataModel.getStarttime(), dataModel.getEndtime(), dataModel.getStatus(), dataModel.getType(), dataModel.getTokenid(), dataModel.getUpid(), dataModel.getNode(), dataModel.getSaved(), dataModel.getId(),internalDataModel.getEdgeClientId());
				}
				
			}
			
			logger.info("Data Saved Successfully");
			
			}
			
		} catch (Exception e) {
             
			 logger.error("Unable to save Data Log"+e);
		}
	}
 
	@Override
	public ArrayList<HypervisorTaskLogModel> getHypervisorLogData(String edgeClientId) {
 
		ArrayList<HypervisorTaskLogModel> model=new ArrayList<>();
		try {
			logger.info("Fetching Log data From Hypervisor...");
			model=hypervisorTaskLogRepository.getLogData(edgeClientId);
			
		} catch (Exception e) {
			
			logger.error("Unable to Fetch Hypervisor Log"+e);
		}
		return model;
	}
 
}

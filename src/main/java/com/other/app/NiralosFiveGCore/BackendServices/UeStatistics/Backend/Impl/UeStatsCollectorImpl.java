package com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Backend.Impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Backend.UeStatsCollector;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbListDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbUeListDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.UeListDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.FileListNameDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UeFileListDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UeStacDataDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UeStacDataList;
import com.other.app.NiralosFiveGCore.Repository.GnbStatistics.GnbStatsRepository;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.UeStatsRepository1;
import com.other.app.NiralosFiveGCore.model.DeletingFiles;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

//@Component
@Service
@EnableScheduling
//@Configuration
public class UeStatsCollectorImpl implements UeStatsCollector {
	@Autowired
	UeStatsRepository1 ueStatsRepository1;
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;

	@Autowired
	GnbStatsRepository gnbStatsRepository;
	@Autowired
    WebClient.Builder customWebClientBuilder;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(UeStatsCollectorImpl.class);

	//@Scheduled(fixedRate = 5000)
	@Override
	public void ueStatsParsing() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
				String LocalSdnId = deployedItem.getDeploymentId();
				String tenantId = deployedItem.getTenantName();
				String siteId = deployedItem.getSiteName();

		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
		  final int size = 1024 * 1024 * 1024;
		    final ExchangeStrategies strategies = ExchangeStrategies.builder()
		        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
		        .build();
		
		String smfIp = internalDataService.getSmfIp();
		String smfPort = internalDataService.getSmfPort();
		WebClient smfClient = WebClient.builder().exchangeStrategies(strategies)
                .build();
		WebClient amfClient = WebClient.builder().build();
		if (siteId != null && tenantId!=null) {
		for(int count = 1;count<=Integer.parseInt(smfIp);count++) {
		String nfNameofSmf=nfServiceImpl.smfName+count;
		try {
			String SmfbaseUrl = uriProtocol.getFivegcoreProtocol()+nfNameofSmf+ ":" + smfPort;
			UeFileListDto fileList = smfClient.get().uri(SmfbaseUrl+ uriProtocol.getFivegcoreUeHistoryandsessionfileListUri())
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(UeFileListDto.class)
					.timeout(Duration.ofSeconds(1))
					.block();
			
			List<FileListNameDto> fileLists = fileList.getFileList();

			if (fileLists != null) {

				for (FileListNameDto iteratorOfFileNames : fileList.getFileList()) {

					UeStacDataList ueStacDataList = smfClient.get()
							.uri(SmfbaseUrl+uriProtocol.getFivegcoreSessionfileJsonUri() + iteratorOfFileNames.getFileName())
							.retrieve()
							.bodyToMono(UeStacDataList.class)
							.timeout(Duration.ofSeconds(1))
							.block();

					ArrayList<UeStacDataDto> arrayList =  ueStacDataList.getFile();
					
					if(arrayList==null)
					{
//						Used to Delete the Respective file after the file is parsed
						DeletingFiles deleteFile = smfClient.get().uri(SmfbaseUrl+uriProtocol.getDeletedtatfilejson() + iteratorOfFileNames.getFileName()).retrieve()
								.bodyToMono(DeletingFiles.class).block();
						
						logger.info("LOG File " + deleteFile.getStatus());
					}else {
					

					for (UeStacDataDto ueStatDataDto : arrayList) {

						UeStatsModel1 UeStatModel = new UeStatsModel1();
			
                          Boolean status = false;
                  		for(int count1 = 1;count1<=Integer.parseInt(amfIp);count1++) {
              			String nfNameofAmf=nfServiceImpl.amfName+count1;
              			
              		String AmfbaseUrl=uriProtocol.getFivegcoreProtocol()+nfNameofAmf+":"+amfPort;
						GnbUeListDto gnbUeListDto = amfClient.get().uri(AmfbaseUrl+uriProtocol.getFivegcoreGnbStatsCollector())
								.accept(MediaType.APPLICATION_JSON)
								.retrieve().bodyToMono(GnbUeListDto.class)
								.timeout(Duration.ofSeconds(1))
								.block();
						for (GnbListDto gnbListDtoss : gnbUeListDto.getGnbList()) {
							for (UeListDto ueListDto : gnbListDtoss.getUeList()) {
								if (ueListDto.getImsi().equals(ueStatDataDto.getImsi())) {
							status = true;
								}
								else if (ueStatDataDto.getSessionStatus().contains("Removed")) {
									status = false;
								} else if (ueStatDataDto.getSessionStatus().contains("Failed")) {
									status = false;
								}
							}
							}
                  		}
						if (ueStatsRepository1.checkIfExist(ueStatDataDto.getImsi(), ueStatDataDto.getDnn(),
								ueStatDataDto.getPdi()) == 0) {
							UeStatModel.setNfDomain(ueStatDataDto.getNfDomain());
							UeStatModel.setSessionStatus(ueStatDataDto.getSessionStatus());
							UeStatModel.setImsi(ueStatDataDto.getImsi());//
							UeStatModel.setCgi(ueStatDataDto.getCgi());
							UeStatModel.setAmfId(ueStatDataDto.getAmfId());
							UeStatModel.setNetworkName(ueStatDataDto.getNetworkName());
							UeStatModel.setDnn(ueStatDataDto.getDnn());//
							UeStatModel.setPdi(ueStatDataDto.getPdi());//
							UeStatModel.setSessionIpv4(ueStatDataDto.getSessionIpv4());
							UeStatModel.setSessionIpv6(ueStatDataDto.getSessionIpv6());
							UeStatModel.setDestinationIp(ueStatDataDto.getDestinationIp());
							UeStatModel.setSourcePort(ueStatDataDto.getSourcePort());
							UeStatModel.setDestinationPort(ueStatDataDto.getDestinationPort());
							UeStatModel.setProtocol(ueStatDataDto.getProtocol());
							UeStatModel.setSst(ueStatDataDto.getSst());
							UeStatModel.setSd(ueStatDataDto.getSd());
							UeStatModel.setUplinkPacket(ueStatDataDto.getUplinkPacket());
							UeStatModel.setUplinkBytes(ueStatDataDto.getUplinkBytes());
							UeStatModel.setDownlinkPacket(ueStatDataDto.getDownlinkPacket());
							UeStatModel.setDownlinkBytes(ueStatDataDto.getDownlinkBytes());
							UeStatModel.setSessionStartTime(ueStatDataDto.getSessionStartTime());
							UeStatModel.setSessionStopTime(ueStatDataDto.getSessionStopTime());
							UeStatModel.setDuration(ueStatDataDto.getDuration());
							UeStatModel.setSessionFailReason(ueStatDataDto.getSessionFailReason());
							UeStatModel.setStatus(status);
							
							UeStatModel.setAgentId(LocalSdnId);
							UeStatModel.setTenentId(tenantId);
							UeStatModel.setSiteId(siteId);
							
							UeStatModel.setNfName(nfNameofSmf);
							UeStatModel.setNfType(nfServiceImpl.nfTypeofSmf);
							

							
							ueStatsRepository1.save(UeStatModel);
							logger.info("Local SDN fetching Ue data from 5GCore");
							
						
						} else {
							ueStatsRepository1.updateUeStac(ueStatDataDto.getNfDomain(),
									ueStatDataDto.getSessionStatus(), ueStatDataDto.getCgi(), ueStatDataDto.getAmfId(),
									ueStatDataDto.getNetworkName(), ueStatDataDto.getSessionIpv4(),
									ueStatDataDto.getSessionIpv6(), ueStatDataDto.getDestinationIp(),
									ueStatDataDto.getSourcePort(), ueStatDataDto.getDestinationPort(),
									ueStatDataDto.getProtocol(), ueStatDataDto.getSst(), ueStatDataDto.getSd(),
									ueStatDataDto.getUplinkPacket(), ueStatDataDto.getUplinkBytes(),
									ueStatDataDto.getDownlinkPacket(), ueStatDataDto.getDownlinkBytes(),
									ueStatDataDto.getSessionStartTime(), ueStatDataDto.getSessionStopTime(),
									ueStatDataDto.getDuration(), ueStatDataDto.getSessionFailReason(),status,LocalSdnId,tenantId,siteId,
									ueStatDataDto.getImsi(), ueStatDataDto.getDnn(), ueStatDataDto.getPdi(),nfNameofSmf,nfServiceImpl.nfTypeofSmf);
							logger.info("Local SDN UeStat data is updating in database");
						}

					}


//					Used to Delete the Respective file after the file is parsed
					DeletingFiles deleteFile = smfClient.get().uri(SmfbaseUrl+uriProtocol.getDeletedtatfilejson() + iteratorOfFileNames.getFileName()).retrieve()
							.bodyToMono(DeletingFiles.class).block();
					
					logger.info("LOG File " + deleteFile.getStatus());
				}}
			} else {
				logger.info("LOG  No Files found to be scrapped ");
			}

		} catch (Exception e) {
			logger.error("LOG Failed to read UE Statistics from Niral 5G Core " + e );
		}
		
 		for(int count2 = 1;count2<=Integer.parseInt(amfIp);count2++) {
  			String nfNameofAmf=nfServiceImpl.amfName+count2;
		try {
//			This Code Fetches all Gnb and Imsi from the String and will help us fill GnbId, AmfName in UE Stats
	   		String AmfbaseUrl=uriProtocol.getFivegcoreProtocol()+nfNameofAmf+":"+amfPort;
			GnbUeListDto gnbUeListDto = amfClient.get().uri(AmfbaseUrl+uriProtocol.getFivegcoreGnbStatsCollector())
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(GnbUeListDto.class)
					.timeout(Duration.ofSeconds(1))
					.block();

			for (GnbListDto gnbListDtos : gnbUeListDto.getGnbList()) {
				for (UeListDto ueListDtos : gnbListDtos.getUeList()) {
					List<String> imsis = ueStatsRepository1.getAllDistinctImsi();
					for (String imsi : imsis) {
						if (ueListDtos.getImsi().equals(imsi)) {
							ueStatsRepository1.updateDtatUsingLiveStats(gnbListDtos.getGnbId(),
									gnbUeListDto.getAmfName(), imsi,nfNameofSmf,nfServiceImpl.nfTypeofSmf);
						}
					}
				}
			}

		} catch (Exception e) {
		}
	}
	}}
}}

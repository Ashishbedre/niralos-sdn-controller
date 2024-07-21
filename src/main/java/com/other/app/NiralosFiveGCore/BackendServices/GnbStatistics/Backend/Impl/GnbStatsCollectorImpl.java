package com.other.app.NiralosFiveGCore.BackendServices.GnbStatistics.Backend.Impl;



import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.GnbStatistics.Backend.GnBStatsCollectorService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbListDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbUeListDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.GnbStatistics.GnbStatsRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.GnbFrontendRepository;
import com.other.app.NiralosFiveGCore.model.GnbStatsModel;
import com.other.app.NiralosFiveGCore.model.Topology.GnBModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class GnbStatsCollectorImpl implements GnBStatsCollectorService {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	 @Autowired
	 WebClient.Builder customWebClientBuilder;

	@Autowired
	GnbStatsRepository gnbStatsRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(GnbStatsCollectorImpl.class);
	@Autowired
	GnbFrontendRepository gnbRepository;
	
	@Override
	public void liveDataParse() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
		String LocalSdnId  = deployedItem.getDeploymentId();
		String tenantId = deployedItem.getTenantName();
		String siteId = deployedItem.getSiteName();
		
		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
		WebClient amfClient = WebClient.builder().build();
		if (siteId != null && tenantId != null) {
		for(int count = 1;count<=Integer.parseInt(amfIp);count++) {
		String networkFunctionName=nfServiceImpl.amfName+count;
		try {
			String baseUrl = uriProtocol.getFivegcoreProtocol()+networkFunctionName+":"+amfPort;
			GnbUeListDto gnbUeListDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbStatsCollector()).accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(GnbUeListDto.class)
					.timeout(Duration.ofSeconds(1))
					.block();
			
			GnbStatsModel gnbStatsModel = new GnbStatsModel();
				gnbStatsModel.setAmfName(gnbUeListDto.getAmfName());
				gnbStatsModel.setLifeTimeAmfSession(gnbUeListDto.getLifeTimeAmfSession());
				gnbStatsModel.setPlmn(gnbUeListDto.getPlmn());
				gnbStatsModel.setActiveAmfSession(gnbUeListDto.getActiveAmfSession());
				
				if (gnbUeListDto.getGnbList().isEmpty()) {
					try {
						gnbRepository.updateGnbStatusAllInctive(networkFunctionName,nfServiceImpl.nfTypeofAmf);
						gnbStatsRepository.updateGnbStatusAllInctive(networkFunctionName,nfServiceImpl.nfTypeofAmf);
					} catch (Exception e) {
						logger.info("LOG Gnbs not available to be marked as INACTIVE");
					}
				} else {
					for (GnbListDto gnbListDto : gnbUeListDto.getGnbList()) {
						gnbStatsRepository.updateCgiInGnbStats(gnbListDto.getGnbId(),networkFunctionName,nfServiceImpl.nfTypeofAmf);
						if (gnbStatsRepository.checkIfGnbExist(gnbListDto.getGnbId()) == 0) {
							gnbStatsModel.setGnbId(gnbListDto.getGnbId());
							gnbStatsModel.setIp(gnbListDto.getIp());
							gnbStatsModel.setTac(gnbListDto.getTac());
							gnbStatsModel.setTotalGnbSession(gnbListDto.getTotalGnbSession());
							gnbStatsModel.setActiveGnbSession(gnbListDto.getActiveGnbSession());
							gnbStatsModel.setStatus(true);
							
							gnbStatsModel.setAgentId(LocalSdnId );
							gnbStatsModel.setTenentId(tenantId);
							gnbStatsModel.setSiteId(siteId);
							
							gnbStatsModel.setNfName(networkFunctionName);
							gnbStatsModel.setNfType(nfServiceImpl.nfTypeofAmf);
							
							
							gnbStatsRepository.save(gnbStatsModel);
						} else {
							gnbStatsRepository.updateExistingGnbListAndAmf(gnbListDto.getIp(), gnbListDto.getTac(), gnbListDto.getTotalGnbSession(), gnbListDto.getActiveGnbSession(),gnbUeListDto.getPlmn(), gnbUeListDto.getLifeTimeAmfSession(),
									gnbUeListDto.getActiveAmfSession(), gnbUeListDto.getAmfName(), gnbListDto.getGnbId(),LocalSdnId ,tenantId,siteId,networkFunctionName,nfServiceImpl.nfTypeofAmf);
							logger.info("LOG Success Reading GnB Statistics from Niral 5G Core");
						}
						
						if(gnbRepository.findGnbExisting(Integer.parseInt(gnbListDto.getGnbId().toString()),LocalSdnId , siteId,tenantId,networkFunctionName,nfServiceImpl.nfTypeofAmf) == 0) {
//							Saving Data Into Gnb Device Table
							GnBModel gnBModel = new GnBModel();
							gnBModel.setAgentId(LocalSdnId );
							gnBModel.setGnbId(Integer.parseInt(gnbListDto.getGnbId().toString()));
							gnBModel.setGnbName("Gnb " + gnbListDto.getGnbId());
							gnBModel.setSiteId(siteId);
							gnBModel.setTenentId(tenantId);
							gnBModel.setStatus(true);
							gnBModel.setDeviceId(null);
							gnBModel.setNfName(networkFunctionName);
							gnBModel.setNfType(nfServiceImpl.nfTypeofAmf);
							gnbRepository.save(gnBModel);
						}
						else {
							gnbRepository.UpdateExistingGnbModel(true,Integer.parseInt(gnbListDto.getGnbId().toString()), siteId, LocalSdnId ,
									tenantId,networkFunctionName,nfServiceImpl.nfTypeofAmf);
						}
					}
				}

		} catch (Exception e) {
			logger.info("LOG Failed to read GnB Statistics from Niral 5G Core");
		}

	}
		}
	}
}

package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.Impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.LivenessCheckerService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.Topology.DeploymentModelService;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbListDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbUeListDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.GnbStatistics.GnbStatsRepository;
import com.other.app.NiralosFiveGCore.Repository.KeycloakTokenConfiguration.AccessTokenRepo;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeviceRepository;
import com.other.app.NiralosFiveGCore.model.GnbStatsModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class LivenessCheckerServiceImpl implements LivenessCheckerService {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	GnbStatsRepository gnbStatsRepository;

	@Autowired
	DeploymentModelService deploymentModelService;

	@Autowired
	AccessTokenRepo accessTokenRepo;
	
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:LivenessCheckerServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(LivenessCheckerServiceImpl.class);

	private String protocolforSyncDataToController;

	@Value("${protocol.SyncDataTo.Controller}")
	public void setprotocolforSyncDataToController(String protocolforSyncDataToController) {
		this.protocolforSyncDataToController = protocolforSyncDataToController;
	}

	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();

	@Override
	public void coreLivenessChecker() {
//		  internalDataFrontendService.fetchDeployedItemInformation().block();
		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
//		String LocalSdnId  = deployedItem.getDeploymentId();
		
		for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
			String networkFunctionName = nfServiceImpl.amfName + count;
			try {
				WebClient amfClient = customWebClientBuilder.baseUrl("http://" + networkFunctionName + ":" + amfPort)
						.build();
				
				
				WebClient.ResponseSpec amfResponseSpec = amfClient.get().retrieve();
				HttpStatus amfHttpStatus = amfResponseSpec.toBodilessEntity().block().getStatusCode();
				String amfResponseCode = amfHttpStatus.toString();

				if (amfResponseCode.equals("200 OK")) {
					try {
						deviceRepository.updateDeviceStatusForCTL(true,networkFunctionName,nfServiceImpl.amfName);
						deviceRepository.updateDeviceStatusForUPG(true ,networkFunctionName,nfServiceImpl.amfName);
						loggerTypeA.info("coreLivenessChecker:Updating Niral Controller that Niral 5G Core is Active");
					} catch (Exception e) {
						loggerTypeA.info("coreLivenessChecker: liveness data in progress to sync to controller " + e);
					}
				} else {
					try {
						deviceRepository.updateDeviceStatusForCTL(false,networkFunctionName,nfServiceImpl.amfName);
						deviceRepository.updateDeviceStatusForUPG(false,networkFunctionName,nfServiceImpl.amfName);
						loggerTypeA.info("coreLivenessChecker: Updating Niral Controller that Niral 5G Core is In-Active");

//				Marking Gnb also as inactive becasue core itself is down.
						gnbStatsRepository.updateGnbStatusAllInctive(networkFunctionName, nfServiceImpl.nfTypeofAmf);
					} catch (Exception e) {
						loggerTypeA.info("coreLivenessChecker: liveness data in progress to sync to controller " + e);
					}
				}

			} catch (Exception e) {
//			Connecting with Niral Controller For Marking CTL InActive.
				try {
					deviceRepository.updateDeviceStatusForCTL(false ,networkFunctionName,nfServiceImpl.amfName);
					deviceRepository.updateDeviceStatusForUPG(false,networkFunctionName,nfServiceImpl.amfName);
					loggerTypeA.info("coreLivenessChecker: Updating Niral Controller that Niral 5G Core is In-Active" + e);

//			Marking Gnb also as inactive becasue core itself is down.
					gnbStatsRepository.updateGnbStatusAllInctive(networkFunctionName, nfServiceImpl.nfTypeofAmf);
				} catch (Exception r) {
					// TODO: handle exception
					loggerTypeA.info("coreLivenessChecker: liveness data in progress to sync to controller " + e);
				}
			}
			
		}

	}

	@Override
//	@Scheduled(fixedRate = 4000)
	public void gngAndUeLivenessChecker() {
		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
		WebClient amfClient = WebClient.builder().build();
		for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
			String networkFunctionName = nfServiceImpl.amfName + count;
			loggerTypeA.info("gngAndUeLivenessChecker: Processing network function");
			try {
						String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + amfPort;
				GnbUeListDto gnbUeListDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbStatsCollector())
						.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(GnbUeListDto.class)
						.timeout(Duration.ofSeconds(1)).block();

//			Matching if DB GnB ID exists in GnB API Data
				List<GnbStatsModel> gnbStatsModels = gnbStatsRepository.findAll();
				loggerTypeA.info("Retrieved GNB stats from the database");
				for (GnbStatsModel gnbStat : gnbStatsModels) {
					loggerTypeA.info("gngAndUeLivenessChecker: gngAndUeLivenessChecker: ");
					Integer loopCounter = 0;
					for (GnbListDto gnbListDto : gnbUeListDto.getGnbList()) {
						if (gnbStat.getGnbId().equals(gnbListDto.getGnbId())) {
							loopCounter++;
						}
					}
					if (loopCounter > 0) {
//					MARK ACTIVE
						gnbStatsRepository.updateGnbStatusActive(gnbStat.getGnbId(), networkFunctionName,
								nfServiceImpl.nfTypeofAmf);
					} else {
//					MARK INACTIVE
						gnbStatsRepository.updateGnbStatusInactive(gnbStat.getGnbId(), networkFunctionName,
								nfServiceImpl.nfTypeofAmf);
					}
				}

			} catch (Exception e) {
				loggerTypeA.error("gngAndUeLivenessChecker: LOG Failed to Check GnB and UE Statistics from Niral 5GCore");
			}
		}
	}

	
//	@Scheduled(fixedRate = 4000)
	@Override
	public void basicInformation() {
//		try {
//		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
//		String niralControllerIp = internalDataService.getNiralControllerIp();
//		//updated agent need to add
//		String zone = deployedItem.getCountry();
//		String subzoneName = deployedItem.getState();
//		String tenantId = deployedItem.getTenantName();
//		String siteId = deployedItem.getSiteName();
//		if (siteId != null && tenantId != null) {
//			try {
//				deploymentModelService.zoneDetails(niralControllerIp, zone, subzoneName, tenantId, siteId);
//				logger.info("Sending zone Information to niral Controller");
//			} catch (Exception r) {
//				logger.error("issue Sending zone Information to niral Controller " + r);
//			}
//	
//		}else {
//			logger.error("Tenant Data is null on Internal Database");
//}
//		}catch (Exception e) {
//			logger.error("Check Updater Agent for Further Information");
//		}
	
	}
}

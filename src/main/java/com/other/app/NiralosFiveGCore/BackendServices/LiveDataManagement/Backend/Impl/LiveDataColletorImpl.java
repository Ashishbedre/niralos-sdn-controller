package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.Impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.LiveDataCollector;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbListDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.GnbUeListDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.LiveStatsDto;
import com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto.UeListDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.LiveDataRepository;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@Configuration
@EnableScheduling
public class LiveDataColletorImpl implements LiveDataCollector {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	 @Autowired
	 WebClient.Builder customWebClientBuilder;
	@Autowired
	LiveDataRepository liveDataRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	Integer totalamf = 0;
	Integer totalAmfSession = 0;
	Integer totalGnb = 0;
	Integer totalGnbSession = 0;
	Integer totalUe = 0;
	Integer totalUeSession = 0;
	CommonServiceImpl nfServiceImpl= new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(LiveDataColletorImpl.class);

	@Override
//	@Scheduled(fixedRate = 4000)
	public void liveDataFetcher() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
		String LocalSdnId  = deployedItem.getDeploymentId();
		String tenantId = deployedItem.getTenantName();
		String siteId = deployedItem.getSiteName();
		
		Integer activeamf = 0;
		Integer activeAmfSession = 0;
		Integer activeGnb = 0;
		Integer activeGnbSession = 0;
		Integer activeUe = 0;
		Integer activeUeSession = 0;
		String amfName = "";
		WebClient amfClient = WebClient.builder().build();
		if (siteId != null && tenantId != null) {
		for(int count = 1;count<=Integer.parseInt(amfIp);count++) {
		String networkFunctionName=nfServiceImpl.amfName+count;
		try {
//			*************************************************************************
//			Pinging Fetch_Gnb_UE_List
//			*************************************************************************

			String baseUrl = uriProtocol.getFivegcoreProtocol()+networkFunctionName+":"+amfPort;
			GnbUeListDto gnbUeListDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbStatsCollector()).accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(GnbUeListDto.class)
					.timeout(Duration.ofSeconds(1)).block();
			
			liveDataRepository.updateTenentandSite(LocalSdnId ,tenantId, siteId, networkFunctionName, nfServiceImpl.nfTypeofAmf);
			LiveDataModel liveDataModel = liveDataRepository.findLiveDataModel(networkFunctionName, nfServiceImpl.nfTypeofAmf);

//			For Counting Active AMF
			if (!amfName.equals(gnbUeListDto.getAmfName())) {
				amfName = gnbUeListDto.getAmfName();
//				For Counting Active AMF Session
				activeAmfSession = Integer.parseInt(gnbUeListDto.getActiveAmfSession());
//				For Counting Total AMF Session
				if(Integer.parseInt(gnbUeListDto.getLifeTimeAmfSession()) > activeAmfSession) {
					totalAmfSession = Integer.parseInt(gnbUeListDto.getLifeTimeAmfSession());
					
				}
				else {
				totalAmfSession = Integer.parseInt(gnbUeListDto.getLifeTimeAmfSession());
				}
				activeamf++;
			}
				

//				For Couting Total AMF
				if (Integer.parseInt(liveDataModel.getTotalamf()) < activeamf) {
					totalamf = activeamf;
				}
				

				
//				*************************************************************************
//				Fetching UE Active Session and UE Total Session
//				*************************************************************************
//				For Acitve UE Session
					
				if (Integer.parseInt(liveDataModel.getActiveGnbSession()) == 0) {
					activeUeSession = 0;
				} else {
					for (GnbListDto gnbListDtos : gnbUeListDto.getGnbList()) {
						for (UeListDto ueListDto : gnbListDtos.getUeList()) {
//							activeUeSession = 0;
							activeUeSession =  activeUeSession + Integer.parseInt(ueListDto.getActiveUeSession());
						}
						
					}}

				
				//need to focus again
//				For Total UE Session
				for (GnbListDto gnbListDtoss : gnbUeListDto.getGnbList()) {
					for (@SuppressWarnings("unused") UeListDto ueListDto : gnbListDtoss.getUeList()) {
				if (Integer.parseInt(liveDataModel.getTotalUeSession()) < activeUeSession) {
					totalUeSession = activeUeSession;
				}
					}
					}
//			}
			
//			*************************************************************************
//			Pinging Total_Gnb_UE_Count
//			*************************************************************************


//				WebClient amfClient = WebClient.builder().baseUrl("http://"+networkFunctionName+":"+amfPort).build();
//			WebClient webClient2 = WebClient.builder().baseUrl("http://" + amfIp + ":" + amfPort)
//					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

			 LiveStatsDto liveStatsDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbUeCountLiveData()).accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(LiveStatsDto.class).block();
			 activeGnbSession=0;
//				For Counting Active Gnb Session
			 for (GnbListDto gnbListDtoss : gnbUeListDto.getGnbList()) {
				 activeGnbSession = activeGnbSession+ Integer.parseInt(gnbListDtoss.getActiveGnbSession());
						
			 }
			activeGnb = Integer.parseInt(liveStatsDto.getGnbCount());
//			For Couting Total GnB
			if (Integer.parseInt(liveDataModel.getTotalGnb()) < activeGnb) {
				totalGnb = activeGnb;
			}

//			For Count Active UE
			if (Integer.parseInt(liveStatsDto.getGnbCount()) == 0) {
				activeUe = 0;
			} else {
				activeUe = Integer.parseInt(liveStatsDto.getUeCount());
			}

//			For Total UE
			if (Integer.parseInt(liveDataModel.getTotalUe()) < activeUe) {
				totalUe = activeUe;
			}
			
//			activeGnbSession =gnbListDtoss.;
//			For Counting Active Gnb Session
//			activeGnbSession = activeUeSession;
			
//			For Counting Total Gnb Session
			if(activeGnbSession > Integer.parseInt(liveDataModel.getTotalGnbSession())) {
				totalGnbSession = activeGnbSession;
			}

//			*************************************************************************
//			Saving or Updating all data to DB
//			*************************************************************************

			liveDataRepository.updateLiveData(internalDataService.getNiralControllerClientId(),
					activeAmfSession.toString(), totalAmfSession.toString(), activeamf.toString(), totalamf.toString(),
					activeUe.toString(), totalUe.toString(), activeUeSession.toString(), totalUeSession.toString(), 
					activeGnb.toString(), totalGnb.toString(), activeGnbSession.toString(), totalGnbSession.toString(),siteId,tenantId,nfServiceImpl.nfTypeofAmf,networkFunctionName);
		} catch (Exception e) {

//			Changing all live data to zero
			liveDataRepository.updateLiveData(internalDataService.getNiralControllerClientId(),
					activeAmfSession.toString(), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",siteId,tenantId,nfServiceImpl.nfTypeofAmf,networkFunctionName);
			LocalDateTime localDateTime = null;

			@SuppressWarnings("static-access")
			String timeStamp = (localDateTime.now()).toString();
			logger.info(timeStamp + " Failed to connect to 5G Core for Live Data");
		}

	}
	}
	}
}

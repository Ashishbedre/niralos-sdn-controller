package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.Impl;
 
import java.time.Duration;
import java.time.LocalDateTime;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
 
import reactor.core.publisher.Mono;
 
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
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:LiveDataColletorImpl");
	//final Logger logger = LoggerFactory.getLogger(LiveDataColletorImpl.class);
 
	@Override
//	@Scheduled(fixedRate = 4000)
	public Mono<Object> liveDataFetcher() {
		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
		
		Integer activeamf = 0;
		Integer activeAmfSession = 0;
		Integer activeGnb = 0;
		Integer activeGnbSession = 0;
		Integer activeUe = 0;
		Integer activeUeSession = 0;
		String amfName = "";
		
		WebClient amfClient = WebClient.builder().build();
		for(int count = 1;count<=Integer.parseInt(amfIp);count++) {
		String networkFunctionName=nfServiceImpl.amfName+count;
		try {
		
//			*************************************************************************
//			Pinging Fetch_Gnb_UE_List
//			*************************************************************************
 
			String baseUrl = uriProtocol.getFivegcoreProtocol()+networkFunctionName+":"+amfPort;
			
//			GnbUeListDto gnbUeListDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbStatsCollector()).accept(MediaType.APPLICATION_JSON)
//					.retrieve().bodyToMono(GnbUeListDto.class)
//					.timeout(Duration.ofSeconds(1)).block();
			GnbUeListDto gnbUeListDto = amfClient.get()
				    .uri(baseUrl + uriProtocol.getFivegcoreGnbStatsCollector())
				    .accept(MediaType.APPLICATION_JSON)
				    .retrieve()
				    .onStatus(HttpStatus::isError, response -> {
				        return response.bodyToMono(String.class)
				                       .flatMap(errorBody -> Mono.error(new RuntimeException("Error: " + errorBody)));
				    })
				    .bodyToMono(GnbUeListDto.class)
				    .timeout(Duration.ofSeconds(5))
				    .block();
			LiveDataModel liveDataModel;
			try {
			    liveDataModel = liveDataRepository.findLiveDataModel(networkFunctionName, nfServiceImpl.nfTypeofAmf);
			    if (liveDataModel == null) {
			        throw new RuntimeException("No LiveDataModel found for the given parameters.");
			    }
			} catch (Exception e) {
				loggerTypeA.error("liveDataFetcher: Error querying the database for network function");
				throw new RuntimeException("Error querying the database: " + e.getMessage(), e);
			}
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
				loggerTypeA.info("liveDataFetcher: Active AMF incremented. Current active AMF count");
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
 
			 LiveStatsDto liveStatsDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbUeCountLiveData()).accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(LiveStatsDto.class).block();
			 System.out.println("33333333333333333333333333333");
			loggerTypeA.info("liveDataFetcher: Successfully fetched GNB and UE live statistics for network function: {}", networkFunctionName);
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
			LiveDataModel liveDataModelData = new LiveDataModel(activeAmfSession.toString(), totalAmfSession.toString(), 
					activeamf.toString(), totalamf.toString(),
					activeUe.toString(), totalUe.toString(), 
					activeUeSession.toString(), totalUeSession.toString(), 
					activeGnb.toString(), 
					totalGnb.toString(), activeGnbSession.toString(),
					totalGnbSession.toString()
					,nfServiceImpl.nfTypeofAmf,
					networkFunctionName,LocalDateTime.now());
			
			liveDataRepository.save(liveDataModelData);
//			liveDataRepository.updateLiveData(
//					activeAmfSession.toString(), totalAmfSession.toString(), activeamf.toString(), totalamf.toString(),
//					activeUe.toString(), totalUe.toString(), activeUeSession.toString(), totalUeSession.toString(), 
//					activeGnb.toString(), totalGnb.toString(), activeGnbSession.toString(), totalGnbSession.toString(),nfServiceImpl.nfTypeofAmf,networkFunctionName);
		} catch (Exception e) {
 
			LiveDataModel liveDataModelData = new LiveDataModel(activeAmfSession.toString(), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",nfServiceImpl.nfTypeofAmf,networkFunctionName,LocalDateTime.now());
			liveDataRepository.save(liveDataModelData);
//			Changing all live data to zero
//			liveDataRepository.updateLiveData(
//					);
			LocalDateTime localDateTime = null;
 
			@SuppressWarnings("static-access")
			String timeStamp = (localDateTime.now()).toString();
			loggerTypeA.error(timeStamp + " Failed to connect to 5G Core for Live Data");
		}
 
	}
		return null;
	}
	
}
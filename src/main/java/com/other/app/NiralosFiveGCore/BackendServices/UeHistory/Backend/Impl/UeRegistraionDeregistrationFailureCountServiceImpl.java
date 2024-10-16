package com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.UeRegistraionDeregistrationFailureCountService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeInfo;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeInfoDTO;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduEstInfo;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduReleaseInfo;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeRegistraionDeregistrationFailureCountRepository;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeRegistraionDeregistrationFailureCount;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class UeRegistraionDeregistrationFailureCountServiceImpl
		implements UeRegistraionDeregistrationFailureCountService {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	@Autowired
	UeRegistraionDeregistrationFailureCountRepository ueRegistraionDeregistrationFailureCountRepository;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	//final Logger logger = LoggerFactory.getLogger(UeRegistraionDeregistrationFailureCountServiceImpl.class);
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:UeRegistraionDeregistrationFailureCountServiceImpl");

	@Override
	public void fetchDataAndSaveToDatabase() {
//		internalDataFrontendService.fetchDeployedItemInformation().block();
			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			WebClient amfClient = WebClient.builder().build();
				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					loggerTypeA.info("fetchDataAndSaveToDatabase: Processing for Network Function");

					try {

						
							String baseUrl= uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + amfPort;
//            WebClient amfClient = WebClient.builder()
//                    .baseUrl("http://" + amfIp + ":" + amfPort)
//                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .build();

						UeInfoDTO responseMono = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreUePduCauseInfoUri())
								.retrieve().bodyToMono(UeInfoDTO.class).block();

						if (responseMono != null) {
							// Fetch the existing entity by siteId
							UeRegistraionDeregistrationFailureCount existingUeCountdown = ueRegistraionDeregistrationFailureCountRepository
									.findByNfNameAndNfType(networkFunctionName,nfServiceImpl.nfTypeofAmf);
							for (UeInfo response : responseMono.getUeInfo()) {
								for (PduEstInfo response1 : responseMono.getPduEstInfo()) {
									for (PduReleaseInfo response2 : responseMono.getPduReleaseInfo()) {
										loggerTypeA.info("Processing PduEstInfo and PduReleaseInfo for UeInfo.");
										if (existingUeCountdown != null) {
											loggerTypeA.warn("Existing UeRegistraionDeregistrationFailureCount found");

											// Update specific fields with the new data
											existingUeCountdown.setNoOfDeregistered(response.getNoOfDeregistered());
											existingUeCountdown.setNoOfFailure(response.getNoOfFailure());
											existingUeCountdown.setNoOfpduEstFailure(response1.getNoOfpduEstFailure());
											existingUeCountdown.setNoOfpduEstFailure(response1.getNoOfpduEstFailure());
											existingUeCountdown.setNoOfpduEstReq(response1.getNoOfpduEstReq());
											existingUeCountdown
													.setNoOfpduReleaseSuccess(response2.getNoOfpduReleaseSuccess());
											existingUeCountdown.setNoOfpduReleaseReq(response2.getNoOfpduReleaseReq());
											existingUeCountdown.setNoOfRegistered(response.getNoOfRegistered());
											existingUeCountdown.setNoOfpduEstSuccess(response1.getNoOfpduEstSuccess());
											existingUeCountdown.setTotalNumberOfRegAttempts(
													response.getTotalNumberOfRegAttempts());

											existingUeCountdown.setNfName(networkFunctionName);
											existingUeCountdown.setNfType(nfServiceImpl.nfTypeofAmf);
											// Save the updated entity back to the database
											ueRegistraionDeregistrationFailureCountRepository.save(existingUeCountdown);
										} else {
											// Create a new entity and set the new data
											UeRegistraionDeregistrationFailureCount ueCountdown = new UeRegistraionDeregistrationFailureCount();
											ueCountdown.setNoOfDeregistered(response.getNoOfDeregistered());
											ueCountdown.setNoOfFailure(response.getNoOfFailure());
											ueCountdown.setNoOfpduEstFailure(response1.getNoOfpduEstFailure());
											ueCountdown.setNoOfpduEstReq(response1.getNoOfpduEstReq());
											ueCountdown.setNoOfpduReleaseSuccess(response2.getNoOfpduReleaseSuccess());
											ueCountdown.setNoOfpduReleaseReq(response2.getNoOfpduReleaseReq());
											ueCountdown.setNoOfRegistered(response.getNoOfRegistered());
											ueCountdown.setNfName(networkFunctionName);
											ueCountdown.setNfType(nfServiceImpl.nfTypeofAmf);
											// ueCountdown.setNoOfpduEstSuccess(response.getNoOfpduEstSuccess());
											ueCountdown.setTotalNumberOfRegAttempts(
													response.getTotalNumberOfRegAttempts());

											// Save the new entity to the database
											ueRegistraionDeregistrationFailureCountRepository.save(ueCountdown);
										}
									}
								}
							}
						}
					} catch (Exception e) {
						loggerTypeA.error("fetchDataAndSaveToDatabase: Ue History not able to fetch");
					}
			}
	}
}

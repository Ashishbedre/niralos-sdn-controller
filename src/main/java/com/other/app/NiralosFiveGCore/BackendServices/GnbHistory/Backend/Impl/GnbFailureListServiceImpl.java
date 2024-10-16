package com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.Impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbFailureListService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbInfoRootDTO;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbFailureListRepository;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbFailureList;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

import reactor.core.publisher.Mono;

@Service
@EnableScheduling
@Configuration
public class GnbFailureListServiceImpl implements GnbFailureListService {
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	GnbFailureListRepository gnbFailureListRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	//final Logger logger = LoggerFactory.getLogger(GnbFailureListServiceImpl.class);
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:GnbFailureListServiceImpl");

//	 @Scheduled(fixedRate = 3000)
	public synchronized void fetchDataAndSaveToDatabase() {
//			   internalDataFrontendService.fetchDeployedItemInformation().block();
			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			WebClient amfClient = WebClient.builder().build();

				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					try {
						loggerTypeA.info("fetchDataAndSaveToDatabase: Fetching GnbInfo for network function {}", networkFunctionName);
						String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + amfPort;
						Mono<GnbInfoRootDTO> responseMono = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbInfo())
								.retrieve().bodyToMono(GnbInfoRootDTO.class).timeout(Duration.ofSeconds(1));

						responseMono.subscribe(response -> {
							if (response != null && response.getGnbInfo() != null) {
								response.getGnbInfo().forEach(gnbInfo -> {
									if (gnbInfo.getGnbList() != null) {
										gnbInfo.getGnbList().forEach(gnbList -> {
											if (gnbList.getFailureList() != null) {
												gnbList.getFailureList().forEach(failureList -> {
													GnbFailureList existingFailureList = gnbFailureListRepository
															.findByGnbIdAndFailureReason(gnbList.getGnbId(),
																	 failureList.getFailureReason());
													if (existingFailureList != null) {
														existingFailureList
																.setFailureCount(failureList.getFailureCount());
														existingFailureList
																.setFailureReason(failureList.getFailureReason());
														existingFailureList.setNfName(networkFunctionName);
														existingFailureList.setNfType(nfServiceImpl.nfTypeofAmf);
														gnbFailureListRepository.save(existingFailureList);
														loggerTypeA.info("fetchDataAndSaveToDatabase: Updated existing GnbFailureList");
													} else {
														GnbFailureList newFailureList = new GnbFailureList();
														newFailureList.setGnbId(gnbList.getGnbId());
														newFailureList.setFailureReason(failureList.getFailureReason());
														newFailureList.setFailureCount(failureList.getFailureCount());
														newFailureList.setNfName(networkFunctionName);
														newFailureList.setNfType(nfServiceImpl.nfTypeofAmf);
														gnbFailureListRepository.save(newFailureList);
														loggerTypeA.info("fetchDataAndSaveToDatabase: Created new GnbFailureList for GnbId with failure reason ");
													}
												});
											}
										});
									}
								});
							}
						});
					} catch (Exception e) {
						loggerTypeA.error("fetchDataAndSaveToDatabase: Unable to fetch GnbFailure Information from 5GCore " + e);
					}
				}
			}
	//	});

}

package com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.Impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbFailureListService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbInfoRootDTO;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
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
	final Logger logger = LoggerFactory.getLogger(GnbFailureListServiceImpl.class);

	// @Scheduled(fixedRate = 3000)
	public void fetchDataAndSaveToDatabase() {
			  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
				String LocalSdnId = deployedItem.getDeploymentId();
				System.out.println("Agent ID: " + LocalSdnId );

				// Extract tenantId and siteId from the fetched deployed item
				String tenantId = deployedItem.getTenantName();
				String siteId = deployedItem.getSiteName();
			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			WebClient amfClient = WebClient.builder().build();

			if (siteId != null && tenantId != null) {
				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					try {
						
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
															.findByGnbIdAndSiteIdAndFailureReason(gnbList.getGnbId(),
																	siteId, failureList.getFailureReason());
													if (existingFailureList != null) {
														existingFailureList
																.setFailureCount(failureList.getFailureCount());
														existingFailureList
																.setFailureReason(failureList.getFailureReason());
														existingFailureList.setNfName(networkFunctionName);
														existingFailureList.setNfType(nfServiceImpl.nfTypeofAmf);
														gnbFailureListRepository.save(existingFailureList);
													} else {
														GnbFailureList newFailureList = new GnbFailureList();
														newFailureList.setGnbId(gnbList.getGnbId());
														newFailureList.setSiteId(siteId);
														newFailureList.setAgentId(LocalSdnId);
														newFailureList.setTenentId(tenantId);
														newFailureList.setFailureReason(failureList.getFailureReason());
														newFailureList.setFailureCount(failureList.getFailureCount());
														newFailureList.setNfName(networkFunctionName);
														newFailureList.setNfType(nfServiceImpl.nfTypeofAmf);
														gnbFailureListRepository.save(newFailureList);
													}
												});
											}
										});
									}
								});
							}
						});
					} catch (Exception e) {
						logger.info("Unable to fetch GnbFailure Information from 5GCore " + e);
					}
				}
			}
	//	});
	}

}

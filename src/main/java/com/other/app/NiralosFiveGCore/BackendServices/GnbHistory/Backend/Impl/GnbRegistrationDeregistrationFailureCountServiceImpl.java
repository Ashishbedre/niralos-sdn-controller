package com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.Impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbRegistrationDeregistrationFailureCountService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbInfoDTO;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbInfoRootDTO;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbRegistrationDeregistrationFailureCountRepository;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class GnbRegistrationDeregistrationFailureCountServiceImpl
		implements GnbRegistrationDeregistrationFailureCountService {
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	GnbRegistrationDeregistrationFailureCountRepository gnbInfoRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	final Logger logger = LoggerFactory.getLogger(GnbRegistrationDeregistrationFailureCountServiceImpl.class);

	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();

	@Override
	public void fetchDataAndSaveToDatabase() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
		String LocalSdnId  = deployedItem.getDeploymentId();
		String tenantId = deployedItem.getTenantName();
		String siteId = deployedItem.getSiteName();

		String amfIp = internalDataService.getAmfIp();
		String amfPort = internalDataService.getAmfPort();
		WebClient amfClient = WebClient.builder().build();
		if (siteId != null && tenantId != null) {
		for (int count1 = 1; count1 <= Integer.parseInt(amfIp); count1++) {
			String networkFunctionName = nfServiceImpl.amfName + count1;
			try {
				String baseUrl = uriProtocol.getFivegcoreProtocol()+networkFunctionName+":"+amfPort;

				GnbInfoRootDTO responseMono = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreGnbInfo()).retrieve()
						.bodyToMono(GnbInfoRootDTO.class).timeout(Duration.ofSeconds(1)).block();

				if (responseMono != null) {
					// Fetch the existing entity by siteId
					GnbRegistrationDeregistrationFailureCount existingInfo = gnbInfoRepository.findBySiteId(siteId);
					for (GnbInfoDTO response : responseMono.getGnbInfo()) {

						if (existingInfo != null) {

							// Update specific fields with the new data

							existingInfo.setTotalNoOfFailure(response.getTotalNoOfFailure());
							existingInfo.setTotalnoOfRegistered(response.getTotalnoOfRegistered());
							existingInfo.setTotalNumberOfAttempts(response.getTotalNumberOfAttempts());
							existingInfo.setNoOfSCTP_Deregistered(response.getNoOfSCTP_Deregistered());
							existingInfo.setNoOfDeregistered(response.getNoOfDeregistered());
							existingInfo.setNfName(networkFunctionName);
							existingInfo.setNfType(nfServiceImpl.nfTypeofAmf);
							// Save the updated entity back to the database
							gnbInfoRepository.save(existingInfo);

						} else {
							// Create a new entity and set the new data
							GnbRegistrationDeregistrationFailureCount gnbInfo = new GnbRegistrationDeregistrationFailureCount();
							gnbInfo.setAgentId(LocalSdnId );
							gnbInfo.setTenentId(tenantId);
							gnbInfo.setSiteId(siteId);
							gnbInfo.setNoOfDeregistered(response.getNoOfDeregistered());
							gnbInfo.setNoOfSCTP_Deregistered(response.getNoOfSCTP_Deregistered());
							gnbInfo.setTotalNoOfFailure(response.getTotalNoOfFailure());
							gnbInfo.setTotalnoOfRegistered(response.getTotalnoOfRegistered());
							gnbInfo.setTotalNumberOfAttempts(response.getTotalNumberOfAttempts());
							gnbInfo.setNfName(networkFunctionName);
							gnbInfo.setNfType(nfServiceImpl.nfTypeofAmf);

							// Save the new entity to the database
							gnbInfoRepository.save(gnbInfo);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Unable to Fetch Gnb Count Information from 5Gcore "  + e);
			}
		}
	}
		}
}

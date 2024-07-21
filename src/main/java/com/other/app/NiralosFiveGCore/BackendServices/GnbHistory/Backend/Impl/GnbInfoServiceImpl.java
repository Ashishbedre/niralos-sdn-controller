package com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.Impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbInfoService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbInfoDTO;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbInfoRootDTO;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend.GnbListDTO;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbInfoRepository;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbInfoModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class GnbInfoServiceImpl implements GnbInfoService {
	@Autowired
	 WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
    @Autowired
    InternalDataService internalDataService;
    @Autowired
    GnbInfoRepository gnbListRepository;
    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(GnbInfoServiceImpl.class);

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
    	for(int count1 = 1;count1<=Integer.parseInt(amfIp);count1++) {
    		String networkFunctionName=nfServiceImpl.amfName+count1;
        try {


			String baseUrl = uriProtocol.getFivegcoreProtocol()+networkFunctionName+":"+amfPort;
            GnbInfoRootDTO responseMono = amfClient.get()
                    .uri(baseUrl+uriProtocol.getFivegcoreGnbInfo())
                    .retrieve()
                    .bodyToMono(GnbInfoRootDTO.class)
                    .timeout(Duration.ofSeconds(1))
                    .block();


            if (responseMono != null && responseMono.getGnbInfo() != null) {
                // Iterate over the response items
                for (GnbInfoDTO response : responseMono.getGnbInfo()) {
                    if (response.getGnbList() != null) {
                        for (GnbListDTO g1 : response.getGnbList()) {
                            // Check if the entity already exists in the database

                            
                            // Check if the count is 0 based on your custom query
                            int count = gnbListRepository.countByGnbIdAndGnbUpTimeAndGnbDownTime(g1.getGnbId(), g1.getGnbUpTime(), g1.getGnbDownTime());
                            
                            if (count == 0) {

                                // Create a new entity and set the new data
                                GnbInfoModel newGnb = new GnbInfoModel();

                                newGnb.setAgentId(LocalSdnId );
                                newGnb.setTenentId(tenantId);
                                newGnb.setSiteId(siteId);
                                newGnb.setGnbId(g1.getGnbId());
                                newGnb.setGnbDownTime(g1.getGnbDownTime());
                                newGnb.setGnbUpTime(g1.getGnbUpTime());
                                newGnb.setRegistrationStatus(g1.isRegistrationStatus());
                                newGnb.setNfName(networkFunctionName);
                                newGnb.setNfType(nfServiceImpl.nfTypeofAmf);

                                // Save the new entity to the database
                                gnbListRepository.save(newGnb);

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        	logger.error("Unable to fetch Gnb Information from 5GCore");
        }
    }
        }
}}

package com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
public class InternalDataFrontendServiceImpl implements InternalDataFrontendService {

    @Autowired
    InternalDataRepository internalDataRepository;

    @Autowired
    UriProtocol uriProtocol;
    private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:InternalDataFrontendServiceImpl");
    //private final Logger logger = LoggerFactory.getLogger(GnbFailureListServiceImpl.class);

    @Override
    public SiteInformationDto fetchDeployedItemInformation() {
//        String apiUrl = "http://" + uriProtocol.getUpdatedAgentIp() + ":" + uriProtocol.getUpdatedAgenPortNo() + "/api/v1/siteAndContactInfo";
//        WebClient webClient = WebClient.builder().baseUrl(apiUrl).build();
//
//        return webClient.get()
//                .retrieve()
//                .bodyToMono(SiteInformationDto.class)
//                .doOnError(e -> {
//                    if (e instanceof WebClientRequestException) {
//                        loggerTypeB.error("fetchDeployedItemInformation: Port 8089 is not running or the server is unreachable");
//                    } else {
//                        loggerTypeB.error("fetchDeployedItemInformation: Error occurred while fetching deployed item information");
//                    }
//                })
//                .onErrorResume(e -> {
//                    loggerTypeB.error("fetchDeployedItemInformation: Exception handled: updated agent is not running");
//                    return Mono.empty();
//                });
    	SiteInformationDto siteInformationDto = new SiteInformationDto();
    	siteInformationDto.setLocalSdnVersion("3.0.1");
    	siteInformationDto.setCountry("India");
    	return siteInformationDto;
    }
}

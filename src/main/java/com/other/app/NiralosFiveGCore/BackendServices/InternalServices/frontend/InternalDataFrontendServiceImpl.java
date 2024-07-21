package com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.Impl.GnbFailureListServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

import reactor.core.publisher.Mono;

@Service
public class InternalDataFrontendServiceImpl implements InternalDataFrontendService {

    @Autowired
    InternalDataRepository internalDataRepository;

    @Autowired
    UriProtocol uriProtocol;

    private final Logger logger = LoggerFactory.getLogger(GnbFailureListServiceImpl.class);

    @Override
    public Mono<SiteInformationDto> fetchDeployedItemInformation() {
        String apiUrl = "http://" + uriProtocol.getUpdatedAgentIp() + ":" + uriProtocol.getUpdatedAgenPortNo() + "/api/v1/siteAndContactInfo";
        WebClient webClient = WebClient.builder().baseUrl(apiUrl).build();

        return webClient.get()
                .retrieve()
                .bodyToMono(SiteInformationDto.class)
                .doOnError(e -> {
                    if (e instanceof WebClientRequestException) {
                        logger.error("Port 8089 is not running or the server is unreachable");
                    } else {
                        logger.error("Error occurred while fetching deployed item information");
                    }
                })
                .onErrorResume(e -> {
                    logger.error("Exception handled: updated agent is not running");
                    return Mono.empty();
                });
    }
}

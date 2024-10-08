package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.dto.HardwaraVM.TokenDetails;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.*;

@Service
public class EdgeVmHardwareListServiceImp {


    @Autowired
    private EdgeAuthService edgeAuthService;

    @Autowired
    InternalDataRepositorys dataRepository;

    @Autowired
    EdgeHelperService edgeHelperService;

    @Value("${proxmox.api.url}")
    private String apiUrl;
    private WebClient webClient;

    public String getVmScsi(String nodeName, String vmId,String edgeClientId) throws SSLException, JsonProcessingException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);


        Mono<Map<String, Object>> vmConfigDTOMono = createWebClient().get()
                .uri(apiUrl + "/nodes/" + nodeName + "/qemu/" + vmId + "/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (Map<String, Object>) response.get("data"));

        return edgeHelperService.extractNextScsiNumber(edgeHelperService.vmDataDTOResponce(vmConfigDTOMono));
    }

    public String getVmIde(String nodeName, String vmId,String edgeClientId) throws SSLException, JsonProcessingException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);


        Mono<Map<String, Object>> vmConfigDTOMono = createWebClient().get()
                .uri(apiUrl + "/nodes/" + nodeName + "/qemu/" + vmId + "/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (Map<String, Object>) response.get("data"));

        return edgeHelperService.extractNextIdeNumber(edgeHelperService.vmDataDTOResponce(vmConfigDTOMono));
    }


    private boolean areTokensValid(String ticket, String csrfToken){
        if (ticket == null || csrfToken == null) {
            return false;
        }
        return true;
    }


    private WebClient createWebClient() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiUrl)
                .build();
    }


}

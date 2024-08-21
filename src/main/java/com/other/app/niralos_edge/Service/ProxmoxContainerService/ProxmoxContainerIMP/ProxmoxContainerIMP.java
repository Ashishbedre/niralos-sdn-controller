package com.other.app.niralos_edge.Service.ProxmoxContainerService.ProxmoxContainerIMP;



import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeAuthService;
import com.other.app.niralos_edge.dto.ContainerStatus;
import com.other.app.niralos_edge.dto.TokenDetails;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.Map;

@Service
public class ProxmoxContainerIMP {


    private  WebClient webClient;

    @Autowired
    private EdgeAuthService edgeAuthService;

    @Autowired
    InternalDataRepositorys internalDataRepository;

    @Value("${proxmox.api.url}")
    private String apiUrl;
//    private static final Logger logger = LoggerFactory.getLogger(ProxmoxContainerIMP.class);

    public ResponseEntity<String> createContainer(Map<String, Object> containerConfig,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
        String ticket = tokenData.getTicket();
        String csrfToken = tokenData.getCsrfToken();

        if (ticket == null || csrfToken == null) {
            return new ResponseEntity<String>("Authentication tokens are not available.", HttpStatus.NOT_ACCEPTABLE);
        }
//        SslContext sslContext = SslContextBuilder.forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE)
//                .build();
//
//        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
//
//        this.webClient = WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .baseUrl(apiUrl)
//                .build();


        return createWebClient().post()
                .uri("/nodes/pve/lxc")
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .bodyValue(containerConfig)
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    public Mono<ResponseEntity<String>> startContainer( String edgeClientId,String vmid) throws SSLException {

        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
        String ticket = tokenData.getTicket();
        String csrfToken = tokenData.getCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.just(new ResponseEntity<>("Authentication tokens are not available.", HttpStatus.NOT_ACCEPTABLE));
        }
//        SslContext sslContext = SslContextBuilder.forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE)
//                .build();
//
//        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
//
//        this.webClient = WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .baseUrl(apiUrl)
//                .build();


        return webClient.post()
                .uri("/nodes/pve/lxc/{vmid}/start",vmid)
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .header("Content-Length", "0")
                .retrieve()
                .toEntity(String.class);


//        return webClient.post()
//                .uri("/nodes/pve/lxc/127/status/start")
//                .header("Cookie", "PVEAuthCookie=" + ticket)
//                .header("CSRFPreventionToken", csrfToken)
//                .header("Content-Length", "0")
//                .retrieve()
//                .toEntity(String.class);
//                .block();

    }



    public Mono<ResponseEntity<String>> stopContainer(String vmid ,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
        String ticket = tokenData.getTicket();
        String csrfToken = tokenData.getCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.just(new ResponseEntity<>("Authentication tokens are not available.", HttpStatus.NOT_ACCEPTABLE));
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiUrl)
                .build();


        return webClient.post()
                .uri("/nodes/pve/lxc/{vmid}/stop",vmid)
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .header("Content-Length", "0")
                .retrieve()
                .toEntity(String.class);

//        return webClient.post()
//                .uri("/nodes/{node}/lxc/{vmid}/status/stop", vmid)
//                .header("CSRFPreventionToken", csrfToken)
//                .cookie("PVEAuthCookie",ticket)
//                .retrieve()
//                .toEntity(String.class)
//                .block();
    }

    public Mono<ResponseEntity<String>> deleteContainer( String vmid ,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
        String ticket = tokenData.getTicket();
        String csrfToken = tokenData.getCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.just(new ResponseEntity<>("Authentication tokens are not available.", HttpStatus.NOT_ACCEPTABLE));
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiUrl)
                .build();


        return webClient.post()
                .uri("/nodes/{node}/lxc/{vmid}",vmid)
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .header("Content-Length", "0")
                .retrieve()
                .toEntity(String.class);

//        return webClient.delete()
//                .uri("/nodes/{node}/lxc/{vmid}", node, vmid)
//                .header("CSRFPreventionToken", csrfToken)
//                .cookie("PVEAuthCookie",ticket)
//                .retrieve()
//                .toEntity(String.class)
//                .block();
    }

    public Mono<ResponseEntity<String>> getContainerStatus( String vmid ,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
        String ticket = tokenData.getTicket();
        String csrfToken = tokenData.getCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.just(new ResponseEntity<>("Authentication tokens are not available.", HttpStatus.NOT_ACCEPTABLE));
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiUrl)
                .build();


        return webClient.post()
                .uri("/nodes/{node}/lxc/{vmid}/status/current",vmid)
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .header("Content-Length", "0")
                .retrieve()
                .toEntity(String.class);

//        return webClient.get()
//                .uri("/nodes/{node}/lxc/{vmid}/status/current", node, vmid)
//                .header("CSRFPreventionToken", csrfToken)
//                .cookie("PVEAuthCookie",ticket)
//                .retrieve()
//                .toEntity(String.class)
//                .block();
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

//    public  TokenDetails tokenDetails(String edgeClientId) throws SSLException {
//        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
//        String ticket = tokenData.getTicket();
//        String csrfToken = tokenData.getCsrfToken();
//
//        if (ticket == null || csrfToken == null) {
//            return Mono.just(new ResponseEntity<>("Authentication tokens are not available.", HttpStatus.NOT_ACCEPTABLE));
//        }
//        SslContext sslContext = SslContextBuilder.forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE)
//                .build();
//
//        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
//
//        this.webClient = WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .baseUrl(apiUrl)
//                .build();
//    }

}

package com.other.app.niralos_edge.Service.ProxmoxContainerService.ProxmoxContainerIMP;



import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeAuthService;
import com.other.app.niralos_edge.Service.ProxmoxContainerService.ProxmoxContainer;
import com.other.app.niralos_edge.dto.TokenDetails;
import com.other.app.niralos_edge.dto.container.Containerdto;
import com.other.app.niralos_edge.dto.container.StatusContainer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import javax.net.ssl.SSLException;
import java.util.Map;

@Service
public class ProxmoxContainerIMP implements ProxmoxContainer {


    private  WebClient webClient;

    @Autowired
    private EdgeAuthService edgeAuthService;

    @Value("${proxmox.api.url}")
    private String apiUrl;

    public ResponseEntity<Containerdto> createContainer(Map<String, Object> containerConfig,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return new ResponseEntity<>(new Containerdto("Authentication tokens are not available."), HttpStatus.NOT_ACCEPTABLE);
        }

        ResponseEntity<Containerdto> containerdto;
        try {
            containerdto = createWebClient().post()
                    .uri("/nodes/pve/lxc")
                    .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                    .header("CSRFPreventionToken", tokenData.getCsrfToken())
                    .bodyValue(containerConfig)
                    .retrieve()
                    .toEntity(Containerdto.class)
                    .block();
        }catch (Exception e) {
            String errorMessage = "An unexpected error occurred: " + e.getMessage();
            Containerdto errorContainerDTO = new Containerdto(errorMessage); // Populate this with error info if needed
            return new ResponseEntity<>(errorContainerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return containerdto;

    }

    public ResponseEntity<Containerdto> startContainer( String edgeClientId,String vmid) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(), tokenData.getCsrfToken())) {
            return new ResponseEntity<>(new Containerdto("Authentication tokens are not available."), HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            return createWebClient().post()
                    .uri("/nodes/pve/lxc/{vmid}/status/start", vmid)
                    .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                    .header("CSRFPreventionToken", tokenData.getCsrfToken())
                    .header("Content-Length", "0")
                    .retrieve()
                    .toEntity(Containerdto.class)
                    .block();
        } catch (Exception e) {
            String errorMessage = "An unexpected error occurred: " + e.getMessage();
            Containerdto errorContainerDTO = new Containerdto(errorMessage); // Populate this with error info if needed
            return new ResponseEntity<>(errorContainerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    public ResponseEntity<Containerdto> stopContainer(String vmid ,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(), tokenData.getCsrfToken())) {
            return new ResponseEntity<>(new Containerdto("Authentication tokens are not available."), HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            return createWebClient().post()
                    .uri("/nodes/pve/lxc/{vmid}/status/stop", vmid)
                    .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                    .header("CSRFPreventionToken", tokenData.getCsrfToken())
                    .header("Content-Length", "0")
                    .retrieve()
                    .toEntity(Containerdto.class)
                    .block();
        } catch (Exception e) {
            String errorMessage = "An unexpected error occurred: " + e.getMessage();
            Containerdto errorContainerDTO = new Containerdto(errorMessage); // Populate this with error info if needed
            return new ResponseEntity<>(errorContainerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Containerdto> deleteContainer( String vmid ,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return new ResponseEntity<>(new Containerdto("Authentication tokens are not available."), HttpStatus.NOT_ACCEPTABLE);
        }

        try{
            return createWebClient().delete()
                    .uri("/nodes/pve/lxc/{vmid}",vmid)
                    .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                    .header("CSRFPreventionToken", tokenData.getCsrfToken())
                    .header("Content-Length", "0")
                    .retrieve()
                    .toEntity(Containerdto.class)
                    .block();
        } catch (Exception e) {
                String errorMessage = "An unexpected error occurred: " + e.getMessage();
                Containerdto errorContainerDTO = new Containerdto(errorMessage); // Populate this with error info if needed
                return new ResponseEntity<>(errorContainerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<StatusContainer> getContainerStatus( String vmid ,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            StatusContainer statusContainer = new StatusContainer();
            statusContainer.setData(null);
            return new ResponseEntity<>(statusContainer, HttpStatus.NOT_ACCEPTABLE);
        }

        return createWebClient().get()
                .uri("/nodes/pve/lxc/{vmid}/status/current",vmid)
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .header("Content-Length", "0")
                .retrieve()
                .toEntity(StatusContainer.class)
                .block();
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

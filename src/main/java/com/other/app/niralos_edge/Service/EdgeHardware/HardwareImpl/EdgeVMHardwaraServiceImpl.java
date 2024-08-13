package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.EdgeHardware.EdgeVMHardwaraService;
import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeAuthService;
//import com.other.app.niralos_edge.dto.VMUpdateRequest;
import com.other.app.niralos_edge.dto.StorageData;
import com.other.app.niralos_edge.dto.StorageResponse;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EdgeVMHardwaraServiceImpl implements EdgeVMHardwaraService {

    @Autowired
    private EdgeAuthService edgeAuthService;

    @Autowired
    InternalDataRepositorys dataRepository;

    @Value("${proxmox.api.url}")
    private String apiUrl;
    private WebClient webClient;

    public Mono<String> getVmConfig(String nodeName, String vmId,String edgeClientId) throws SSLException {
        edgeAuthService.authenticateAndStore(edgeClientId);
        String ticket = edgeAuthService.getStoredTicket();
        String csrfToken = edgeAuthService.getStoredCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.error(new RuntimeException("Authentication tokens are not available."));
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        return webClient.get()
                .uri(apiUrl + "/nodes/" + nodeName + "/qemu/" + vmId + "/config")
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Void> updateVMConfig( Map<String, Object> request,Long vmId,String edgeClientId) throws SSLException {
        edgeAuthService.authenticateAndStore(edgeClientId);
        String ticket = edgeAuthService.getStoredTicket();
        String csrfToken = edgeAuthService.getStoredCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.error(new RuntimeException("Authentication tokens are not available."));
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://192.168.0.8:8006/api2/json")
                .build();

        return webClient.put()
                .uri("/nodes/pve/qemu/"+vmId+"/config")
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }
    public ResponseEntity<StorageResponse>  getStorageData(String edgeClientId) throws SSLException {
        edgeAuthService.authenticateAndStore(edgeClientId);
        String ticket = edgeAuthService.getStoredTicket();
        String csrfToken = edgeAuthService.getStoredCsrfToken();

        if (ticket == null || csrfToken == null) {
            throw new RuntimeException("Authentication tokens are not available.");
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://192.168.0.8:8006/api2/json")
                .build();

        StorageResponse response = webClient.get()
                .uri("/nodes/pve/storage")
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .retrieve()
                .bodyToMono(StorageResponse.class)
                .block(); // Blocking for simplicity, consider using reactive programming in a real application

        // Filter the data as needed
        List<StorageData> filteredData = response.getData().stream()
                .filter(data -> "local".equals(data.getStorage())) // Filter ba // Example: filter out storages with used_fraction > 0.5
                .collect(Collectors.toList());

        response.setData(filteredData);

        return ResponseEntity.ok(response);
    }

    public Mono<String> updateVmHardware(String vmId, String edgeClientId,String diskId) throws SSLException {
        String url = apiUrl + "/nodes/pve/qemu/" + vmId + "/config";
        edgeAuthService.authenticateAndStore(edgeClientId);
        String ticket = edgeAuthService.getStoredTicket();
        String csrfToken = edgeAuthService.getStoredCsrfToken();

        if (ticket == null || csrfToken == null) {
            return Mono.error(new RuntimeException("Authentication tokens are not available."));
        }
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        // Create a JSON body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("delete", diskId);

        return webClient.put()
                .uri(url)
                .header("Cookie", "PVEAuthCookie=" + ticket)
                .header("CSRFPreventionToken", csrfToken)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(e -> System.err.println("Error updating VM hardware: " + e.getMessage()));
    }
}
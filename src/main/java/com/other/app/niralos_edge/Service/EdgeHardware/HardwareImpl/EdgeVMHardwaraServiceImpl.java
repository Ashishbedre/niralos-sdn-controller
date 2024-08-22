package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.EdgeHardware.EdgeVMHardwaraService;
import com.other.app.niralos_edge.dto.StorageData;
import com.other.app.niralos_edge.dto.StorageResponse;
import com.other.app.niralos_edge.dto.TokenDetails;
import com.other.app.niralos_edge.dto.container.CpuModelsResponseDTO;
import com.other.app.niralos_edge.dto.container.MachineTypeResponse;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return Mono.just("Authentication tokens are not available.");
        }

        return createWebClient().get()
                .uri(apiUrl + "/nodes/" + nodeName + "/qemu/" + vmId + "/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Void> updateVMConfig( Map<String, Object> request,Long vmId,String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {;
            return Mono.error(new RuntimeException("Invalid tokens"));
        }

        return createWebClient().put()
                .uri("/nodes/pve/qemu/"+vmId+"/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);

    }

    public ResponseEntity<StorageResponse>  getStorageData(String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            StorageResponse storageResponse = new StorageResponse();
            storageResponse.setData(null);
            return new ResponseEntity<>(storageResponse ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StorageResponse response = createWebClient().get()
                .uri("/nodes/pve/storage")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
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

    public Mono<String> removeVmHardware(String vmId, String edgeClientId,String diskId) throws SSLException {
        String url = apiUrl + "/nodes/pve/qemu/" + vmId + "/config";
        TokenDetails tokenData  = edgeAuthService.getTokenForEdgeClientId(edgeClientId);
        String ticket = tokenData.getTicket();
        String csrfToken =  tokenData.getCsrfToken();

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




    public ResponseEntity<CpuModelsResponseDTO>  getVmOSTypes(String node, String vmId, String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            CpuModelsResponseDTO cpuModelsResponseDTO = new CpuModelsResponseDTO();
            cpuModelsResponseDTO.setData(null);
            return new ResponseEntity<>(cpuModelsResponseDTO ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CpuModelsResponseDTO cpuModelsResponseDTO =  createWebClient().get()
                .uri("/nodes/pve/capabilities/qemu/cpu")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(CpuModelsResponseDTO.class)
                .block();


        return new ResponseEntity<>(cpuModelsResponseDTO,HttpStatus.OK);
    }



    public ResponseEntity<MachineTypeResponse>  getVmMachineTypes(String node, String edgeClientId) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            MachineTypeResponse machineTypeResponse = new MachineTypeResponse();
            machineTypeResponse.setData(null);
            return new ResponseEntity<>(machineTypeResponse ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        MachineTypeResponse machineTypeResponse =  createWebClient().get()
                .uri("/nodes/pve/capabilities/qemu/machines")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(MachineTypeResponse.class)
                .block();


        return new ResponseEntity<>(machineTypeResponse,HttpStatus.OK);
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
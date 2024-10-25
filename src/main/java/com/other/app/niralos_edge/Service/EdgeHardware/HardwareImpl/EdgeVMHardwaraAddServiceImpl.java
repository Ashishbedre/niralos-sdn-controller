package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.other.app.niralos_edge.Model.InternalDataModels;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EdgeVMHardwaraAddServiceImpl {

    @Autowired
    private EdgeAuthService edgeAuthService;

    @Autowired
    InternalDataRepositorys dataRepository;

    @Autowired
    EdgeVmHardwareListServiceImp edgeVmHardwareListServiceImp;

//    @Value("${proxmox.api.url}")
//    private String apiUrl;
    private WebClient webClient;

    public Mono<Void> addHardDisk(Map<String, Object> request, Long vmId, String edgeClientId, String id) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return Mono.error(new RuntimeException("Invalid tokens"));
        }

        String requestBodyString = request.entrySet()
                .stream()
                .map(entry -> {
                    // Conditional formatting
                    if (entry.getKey().startsWith("local")) {
                        return entry.getKey() + ":" + entry.getValue();
                    } else {
                        return entry.getKey() + "=" + entry.getValue();
                    }
                })
                .collect(Collectors.joining(","));

        //      body = scsi1: local-lvm:32,iothread=on
        //net1: virtio,bridge=vmbr0,firewall=1
//        ide2: local:iso/ubuntu-22.04.4-desktop-amd64.iso,media=cdrom
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put(id, requestBodyString);
        InternalDataModels data =  dataRepository.getData(edgeClientId);
        return createWebClient("https://"+data.getHypervisorIp()+":"+data.getHypervisorPort()+"/api2/json").put()
                .uri("/nodes/pve/qemu/"+vmId+"/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Void.class);

    }

    public Mono<Void> addCdOrDvd(Map<String, Object> request, Long vmId, String edgeClientId, String id) throws SSLException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return Mono.error(new RuntimeException("Invalid tokens"));
        }

        String requestBodyString = request.entrySet()
                .stream()
                .map(entry -> {
                    // Conditional formatting
                    if (entry.getKey().startsWith("local")) {
                        return entry.getKey() + ":iso/" + entry.getValue();
                    } else if (entry.getKey().startsWith("other")) {
                        return "" + entry.getValue();
                    } else {
                        return entry.getKey() + "=" + entry.getValue();
                    }
                })
                .collect(Collectors.joining(","));

        //      body = scsi1: local-lvm:32,iothread=on
        //net1: virtio,bridge=vmbr0,firewall=1
//        ide2: local:iso/ubuntu-22.04.4-desktop-amd64.iso,media=cdrom
        requestBodyString = requestBodyString+",media=cdrom";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put(id, requestBodyString);
        InternalDataModels data =  dataRepository.getData(edgeClientId);
        return createWebClient("https://"+data.getHypervisorIp()+":"+data.getHypervisorPort()+"/api2/json").put()
                .uri("/nodes/pve/qemu/"+vmId+"/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Void.class);

    }
    public Mono<Void> addNetworkDevice(Map<String, Object> request, Long vmId, String edgeClientId) throws SSLException, JsonProcessingException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return Mono.error(new RuntimeException("Invalid tokens"));
        }

        String requestBodyString = request.entrySet()
                .stream()
                .map(entry -> {
                    // Conditional formatting
                    if (entry.getKey().startsWith("model")) {
                        return ""+entry.getValue();
                    } else {
                        return entry.getKey() + "=" + entry.getValue();
                    }
                })
                .collect(Collectors.joining(","));

        //      body = scsi1: local-lvm:32,iothread=on
        //net1: virtio,bridge=vmbr0,firewall=1
//        ide2: local:iso/ubuntu-22.04.4-desktop-amd64.iso,media=cdrom
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put(edgeVmHardwareListServiceImp.getVmNet("pve",vmId,edgeClientId), requestBodyString);
        InternalDataModels data =  dataRepository.getData(edgeClientId);
        return createWebClient("https://"+data.getHypervisorIp()+":"+data.getHypervisorPort()+"/api2/json").put()
                .uri("/nodes/pve/qemu/"+vmId+"/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Void.class);

    }

    public Mono<Void> addPciDevice(Map<String, Object> request, Long vmId, String edgeClientId) throws SSLException, JsonProcessingException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            return Mono.error(new RuntimeException("Invalid tokens"));
        }

        String requestBodyString = request.entrySet()
                .stream()
                .map(entry -> {
                    // Conditional formatting
                    if (entry.getKey().startsWith("hostpci")) {
                        return ""+entry.getValue();
                    } else {
                        return entry.getKey() + "=" + entry.getValue();
                    }
                })
                .collect(Collectors.joining(","));

        //      body = scsi1: local-lvm:32,iothread=on
        //net1: virtio,bridge=vmbr0,firewall=1
//        ide2: local:iso/ubuntu-22.04.4-desktop-amd64.iso,media=cdrom
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put(edgeVmHardwareListServiceImp.getPci("pve",vmId,edgeClientId), requestBodyString);
        InternalDataModels data =  dataRepository.getData(edgeClientId);
        return createWebClient("https://"+data.getHypervisorIp()+":"+data.getHypervisorPort()+"/api2/json").put()
                .uri("/nodes/pve/qemu/"+vmId+"/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Void.class);

    }

    private boolean areTokensValid(String ticket, String csrfToken){
        if (ticket == null || csrfToken == null) {
            return false;
        }
        return true;
    }



    private WebClient createWebClient(String baseUrl) throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .build();
    }
}

package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.EdgeHardware.EdgeVMHardwaraService;
import com.other.app.niralos_edge.dto.HardwaraVM.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class EdgeVMHardwaraServiceImpl implements EdgeVMHardwaraService {

    @Autowired
    private EdgeAuthService edgeAuthService;

    @Autowired
    InternalDataRepositorys dataRepository;

    @Value("${proxmox.api.url}")
    private String apiUrl;
    private WebClient webClient;

    public VmDataDTOResponce getVmConfig(String nodeName, String vmId,String edgeClientId) throws SSLException, JsonProcessingException {
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            VmDataDTOResponce storageResponse = new VmDataDTOResponce();
            return storageResponse;
        }

        Mono<Map<String, Object>> vmConfigDTOMono = createWebClient().get()
                .uri(apiUrl + "/nodes/" + nodeName + "/qemu/" + vmId + "/config")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (Map<String, Object>) response.get("data"));

        return vmDataDTOResponce(vmConfigDTOMono);
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

    public List<Map<String, Object>> getNetwork(String nodeName,String edgeClientId) throws SSLException{
        TokenDetails tokenData = edgeAuthService.getTokenForEdgeClientId(edgeClientId);

        if (!areTokensValid(tokenData.getTicket(),tokenData.getCsrfToken())) {
            List<Map<String, Object>> storageResponse = new ArrayList<>();
            return storageResponse;
        }

        Mono<List<Map<String, Object>>> vmConfigDTOMono = createWebClient().get()
                .uri(apiUrl + "/nodes/" + nodeName + "/network")
                .header("Cookie", "PVEAuthCookie=" + tokenData.getTicket())
                .header("CSRFPreventionToken", tokenData.getCsrfToken())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (List<Map<String, Object>>) response.get("data"))
                .map(dataList -> dataList.stream()
                        .filter(item -> item.containsKey("iface") && item.containsKey("active"))
                        .filter(item -> item.get("iface").toString().startsWith("vmbr"))
                        .map(item -> Map.of(
                                "iface", item.get("iface"),
                                "active", item.get("active")
                        ))
                        .collect(Collectors.toList())
                );
        return vmConfigDTOMono.block();
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

    public VmDataDTOResponce vmDataDTOResponce(Mono<Map<String, Object>> vmConfigDTOMono) throws JsonProcessingException {
        VmDataDTOResponce vmDataDTOResponce = new VmDataDTOResponce();

        Map<String, Object> hashMapResponce  = vmConfigDTOMono.block();

        vmDataDTOResponce.setCores((int) hashMapResponce.get("cores"));
        vmDataDTOResponce.setCpu((String) hashMapResponce.get("cpu"));
        vmDataDTOResponce.setVmgenid((String) hashMapResponce.get("vmgenid"));
        vmDataDTOResponce.setBoot((String) hashMapResponce.get("boot"));
        vmDataDTOResponce.setArgs((String) hashMapResponce.get("args"));
        vmDataDTOResponce.setAgent((String) hashMapResponce.get("agent"));
        vmDataDTOResponce.setVga((String) hashMapResponce.get("vga"));
        vmDataDTOResponce.setScsihw((String) hashMapResponce.get("scsihw"));
        vmDataDTOResponce.setBalloon((int) hashMapResponce.get("balloon"));
        vmDataDTOResponce.setMachine((String) hashMapResponce.get("machine"));
        vmDataDTOResponce.setSockets((int) hashMapResponce.get("sockets"));
        vmDataDTOResponce.setOstype((String) hashMapResponce.get("ostype"));
        vmDataDTOResponce.setName((String) hashMapResponce.get("name"));
        vmDataDTOResponce.setNuma((int) hashMapResponce.get("numa"));
        vmDataDTOResponce.setBios((String) hashMapResponce.get("bios"));
        vmDataDTOResponce.setOnboot((int) hashMapResponce.get("onboot"));
        vmDataDTOResponce.setMemory((int) hashMapResponce.get("memory"));


        // Initialize lists for network, cdAndDvd, and harddisk
        List<Map<String, String>> network = new ArrayList<>();
        List<Map<String, String>> cdAndDvd = new ArrayList<>();
        List<Map<String, String>> harddisk = new ArrayList<>();

        // Processing the map for additional fields
        for (Map.Entry<String, Object> entry : hashMapResponce.entrySet()) {
            String key = entry.getKey();
//            String value = (String) entry.getValue(); // Cast to String
            Object valueObj = entry.getValue(); // Use Object type for value
            String value = (valueObj != null) ? valueObj.toString() : null;

            if (value != null || value.equals(null)) {
                if (key.startsWith("net")) {
                    network.add(parseKeyValuePair(key, value));
                }
                if (value.contains("media=cdrom")) {
                    cdAndDvd.add(parseKeyValuePair(key, value));
                } else if (value.contains("local-lvm")) {
                    harddisk.add(parseKeyValuePair(key, value));
                }
            }
        }

        // Set the lists in the response DTO if they exist
        if (!network.isEmpty()) {
            vmDataDTOResponce.setNetwork(network);
        }
        if (!cdAndDvd.isEmpty()) {
            vmDataDTOResponce.setCdAndDvd(cdAndDvd);
        }
        if (!harddisk.isEmpty()) {
            vmDataDTOResponce.setHarddisk(harddisk);
        }

        return vmDataDTOResponce;
    }

    private Map<String, String> parseKeyValuePair(String key, String value) {
        Map<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("key", key);

        // Split the value into key-value pairs and add to the map
        String[] keyValuePairs = value.split(",");
        for (String pair : keyValuePairs) {
            String[] splitPair = pair.split("=", 2);
            if (splitPair.length == 2) {
                keyValueMap.put(splitPair[0], splitPair[1]);
            } else {
                keyValueMap.put(splitPair[0], ""); // Handle cases where there's no '='
            }
        }
        return keyValueMap;
    }

//    @Scheduled(fixedRate = 10000)
    public String extractNextScsiNumber(VmDataDTOResponce vmConfigDTOMono) {
    // Extract 'cdAndDvd' and 'harddisk' fields
        List<Map<String, String>> cdAndDvdList = vmConfigDTOMono.getCdAndDvd();
        List<Map<String, String>> harddiskList = vmConfigDTOMono.getHarddisk();
        Set<Integer> usedScsiNumbers = new HashSet<>();

        // Process 'cdAndDvd' list for SCSI numbers
        if (cdAndDvdList != null) {
            for (Map<String, String> cdAndDvd : cdAndDvdList) {
                String key = cdAndDvd.get("key");
                if (key != null && key.startsWith("scsi")) {
                    try {
                        int number = Integer.parseInt(key.substring(4));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Process 'harddisk' list for SCSI numbers
        if (harddiskList != null) {
            for (Map<String, String> disk : harddiskList) {
                String key = disk.get("key");
                if (key != null && key.startsWith("scsi")) {
                    try {
                        int number = Integer.parseInt(key.substring(4));
                        usedScsiNumbers.add(number);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Find the smallest unused scsiX number
        int nextScsiNumber = 0;
        while (usedScsiNumbers.contains(nextScsiNumber)) {
            nextScsiNumber++;
        }

        // Return the next available SCSI number
        return "scsi" + nextScsiNumber;
    }
}
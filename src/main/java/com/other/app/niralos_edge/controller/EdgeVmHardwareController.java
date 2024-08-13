package com.other.app.niralos_edge.controller;

//import com.other.app.niralos_edge.dto.VMUpdateRequest;
import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeVMHardwaraServiceImpl;
import com.other.app.niralos_edge.dto.StorageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.util.Map;

@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeVmHardwareController {

    @Autowired
    EdgeVMHardwaraServiceImpl edgeVMHardwaraServiceImpl;

    @GetMapping("/config/{nodeName}/{vmId}/{edgeClientId}")
    public Mono<String> getVmConfig(@PathVariable String nodeName, @PathVariable String vmId,@PathVariable String edgeClientId) {
        try {
            return edgeVMHardwaraServiceImpl.getVmConfig(nodeName, vmId,edgeClientId);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/storage/{edgeClientId}")
    public ResponseEntity<StorageResponse> getStorageData(@PathVariable String edgeClientId) {
        try {
            return edgeVMHardwaraServiceImpl.getStorageData(edgeClientId);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/updateVmHardware/vm_id={vm_id}/edge_client_id={edge_client_id}")
    public Mono<Void> updateVM(@RequestBody Map<String, Object> request, @PathVariable ("vm_id") Long vmId,
                               @PathVariable ("edge_client_id") String edgeClientId ) {
        try {
            return edgeVMHardwaraServiceImpl.updateVMConfig(request,vmId,edgeClientId);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/removeVmHardware/vm_id={vmId}/edge_client_id={edgeClientId}/delete_Command={deleteCommand}")
    public Mono<String> updateVmHardware(
            @PathVariable String vmId,
            @PathVariable String edgeClientId,
            @PathVariable String deleteCommand) {
//        String deleteCommand = body.replace("delete:", "").trim();

        try {
            return edgeVMHardwaraServiceImpl.updateVmHardware(vmId,edgeClientId, deleteCommand);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }
}
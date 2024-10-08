package com.other.app.niralos_edge.controller;

import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeVMHardwaraAddServiceImpl;
import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeVMHardwaraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.util.Map;


@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeVmHardwareAddController {

    @Autowired
    EdgeVMHardwaraAddServiceImpl edgeVMHardwaraAddService;

    @PutMapping("/addHardDisk/vm_id={vm_id}/id={id}/edge_client_id={edge_client_id}")
    public Mono<Void> addHardDisk(@RequestBody Map<String, Object> request, @PathVariable("vm_id") Long vmId,
                               @PathVariable ("edge_client_id") String edgeClientId ,
                                  @PathVariable ("id") String id) {
        try {
            return edgeVMHardwaraAddService.addHardDisk(request,vmId,edgeClientId,id);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/addCdOrDvd/vm_id={vm_id}/id={id}/edge_client_id={edge_client_id}")
    public Mono<Void> addCdOrDvd(@RequestBody Map<String, Object> request, @PathVariable("vm_id") Long vmId,
                               @PathVariable ("edge_client_id") String edgeClientId,
                                 @PathVariable ("id") String id) {
        try {
            return edgeVMHardwaraAddService.addCdOrDvd(request,vmId,edgeClientId,id);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/addNetworkDevice/vm_id={vm_id}/id={id}/edge_client_id={edge_client_id}")
    public Mono<Void> addNetworkDevice(@RequestBody Map<String, Object> request, @PathVariable("vm_id") Long vmId,
                               @PathVariable ("edge_client_id") String edgeClientId,
                                       @PathVariable ("id") String id) {
        try {
            return edgeVMHardwaraAddService.addNetworkDevice(request,vmId,edgeClientId,id);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }


}

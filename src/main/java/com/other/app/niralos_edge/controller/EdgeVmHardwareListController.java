package com.other.app.niralos_edge.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl.EdgeVmHardwareListServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLException;

@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeVmHardwareListController {

    @Autowired
    EdgeVmHardwareListServiceImp edgeVmHardwareListServiceImp;

    @GetMapping("/config/getVmScsi/{nodeName}/{vmId}/{edgeClientId}")
    public String  getVmScsi(@PathVariable String nodeName, @PathVariable String vmId, @PathVariable String edgeClientId) {
        try {
            return edgeVmHardwareListServiceImp.getVmScsi(nodeName, vmId,edgeClientId);
        } catch (SSLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/config/getVmIde/{nodeName}/{vmId}/{edgeClientId}")
    public String  getVmIde(@PathVariable String nodeName, @PathVariable String vmId, @PathVariable String edgeClientId) {
        try {
            return edgeVmHardwareListServiceImp.getVmIde(nodeName, vmId,edgeClientId);
        } catch (SSLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

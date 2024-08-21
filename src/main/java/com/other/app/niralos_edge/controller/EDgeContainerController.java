package com.other.app.niralos_edge.controller;


import com.other.app.niralos_edge.Service.ProxmoxContainerService.ProxmoxContainerIMP.ProxmoxContainerIMP;
import com.other.app.niralos_edge.dto.ContainerStatus;
import com.other.app.niralos_edge.dto.container.Containerdto;
import com.other.app.niralos_edge.dto.container.StatusContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.util.Map;

@RestController
@RequestMapping("/api/proxmox")
public class EDgeContainerController {

    @Autowired
    private ProxmoxContainerIMP proxmoxContainerIMP;


    @PostMapping("/create-container/edge_client_id={edge_client_id}")
    public ResponseEntity<Containerdto>createContainer(@RequestBody Map<String, Object> requestBody, @PathVariable ("edge_client_id") String edgeClientId ) throws SSLException {
            return proxmoxContainerIMP.createContainer(requestBody,edgeClientId);
    }

    @PostMapping("/{vmid}/start-container/edge_client_id={edge_client_id}")
    public ResponseEntity<Containerdto> startContainer(@PathVariable("edge_client_id") String edgeClientId, @PathVariable( "vmid") String vmid) throws Exception {
        return proxmoxContainerIMP.startContainer(edgeClientId,vmid);
//        return proxmoxContainerIMP.startContainer(node, containerId, pveAuthCookie, csrfPreventionToken);
    }

    @PostMapping("/{vmid}/stop/edge_client_id={edge_client_id}")
    public ResponseEntity<Containerdto> stopContainer(@PathVariable String vmid,@PathVariable ("edge_client_id") String edgeClientId ) throws SSLException {
        return proxmoxContainerIMP.stopContainer( vmid,edgeClientId);
    }

    @DeleteMapping("/{vmid}/edge_client_id={edge_client_id}")
    public ResponseEntity<Containerdto> deleteContainer( @PathVariable String vmid,@PathVariable ("edge_client_id") String edgeClientId ) throws SSLException {
        return proxmoxContainerIMP.deleteContainer(vmid,edgeClientId);
    }

    @GetMapping("/{vmid}/status/edge_client_id={edge_client_id}")
    public ResponseEntity<StatusContainer> getContainerStatus(@PathVariable String vmid, @PathVariable ("edge_client_id") String edgeClientId ) throws SSLException {
        return proxmoxContainerIMP.getContainerStatus(vmid,edgeClientId);
    }
}
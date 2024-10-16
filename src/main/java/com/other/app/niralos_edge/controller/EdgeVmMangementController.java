package com.other.app.niralos_edge.controller;

import com.other.app.niralos_edge.Service.VMManagement.VmMangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeVmMangementController {
    @Autowired
    VmMangementService vmMangementService;
    //	VM Start, Shutdown and Restart Commands.
    @PostMapping("/node/vm/shutdown_vm/edge_client_id={edge_client_id}/vmid={vmid}")
    public void shutdownVm(@PathVariable("edge_client_id") String edgeClientId , @PathVariable("vmid")Long vmId) {
        vmMangementService.stopVm(vmId,edgeClientId);
        System.out.println("VMID: "+vmId+" has been shutdown.");
    }

    @PostMapping("/node/vm/start_vm/edge_client_id={edge_client_id}/vmid={vmid}")
    public void startVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId) {
        vmMangementService.startVm(vmId,edgeClientId);
        System.out.println("VMID: "+vmId+" has been started.");
    }

    @PostMapping("/node/vm/restart_vm/edge_client_id={edge_client_id}/vmid={vmid}")
    public void restartVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId) {
        vmMangementService.restartVm(vmId,edgeClientId);
        System.out.println("VMID: "+vmId+" has been restarted.");
    }

    @DeleteMapping("/node/vm/delete_vm/edge_client_id={edge_client_id}/vmid={vmid}")
    public void deleteVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId)
    {
        vmMangementService.deleteVm(vmId,edgeClientId);
        System.out.println("VMID:"+vmId+"has been deleted");

    }
}

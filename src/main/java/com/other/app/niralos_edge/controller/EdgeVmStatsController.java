package com.other.app.niralos_edge.controller;

import com.other.app.niralos_edge.Service.VMDetails.VmStatsService;
import com.other.app.niralos_edge.dto.EdgeFrontendDto;
import com.other.app.niralos_edge.dto.GetVmData;
import com.other.app.niralos_edge.dto.HypervisorCreateVmDto;
import com.other.app.niralos_edge.dto.VmUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeVmStatsController {


    @Autowired
    VmStatsService vmStatsService;

    //Getting the details of all vm for a particular site
    @GetMapping("/node/vm/get_edge_frontend_data/edge_client_id={edge_client_id}")
    public ArrayList<EdgeFrontendDto> getVmDetailsOfParticularSite(@PathVariable("edge_client_id") String edgeClientId)
    {

        return vmStatsService.getVmDetailsParticularSite(edgeClientId);
    }

    //Modified vm
    //Getting the details of a particular vm for a particular site
    @GetMapping("/node/vm/get_edge_frontend_vm/edge_client_id={edge_client_id}/vmid={vmid}")
    public EdgeFrontendDto getParticularVM(@PathVariable("edge_client_id") String edgeClientId,@PathVariable("vmid") Long vmId)
    {
        return vmStatsService.getParticularVm(vmId,edgeClientId);
    }


    //Getting the cores and sockets and memory for a particular vm and edgeClientId
    @GetMapping("/node/vm/get_vm_details_update/edge_client_id={edge_client_id}")
    public VmUpdateDto getDetails(@PathVariable("edge_client_id") String edgeClientId)
    {
        return vmStatsService.getDetails(edgeClientId);
    }

    //Checking the vm details
    /////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/node/hypervisor/vm_check/edge_client_id={edge_client_id}")
    public HypervisorCreateVmDto getVMUpdate(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return vmStatsService.getVm(edgeClientId);
    }

    //Getting the core,socket,memory of a particular vm
    @GetMapping("/node/get_vm_data/edge_client_id={edge_client_id}/vm_id={vm_id}")
    public GetVmData getVmData(@PathVariable ("edge_client_id") String edgeClientId, @PathVariable ("vm_id") Long vmId)
    {
        return vmStatsService.getData(vmId, edgeClientId);
    }
}

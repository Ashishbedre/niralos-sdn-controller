package com.other.app.niralos_edge.controller;


import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.HypervisorTaskLogModel;
import com.other.app.niralos_edge.Model.ListOfIsoModel;
import com.other.app.niralos_edge.Service.HypervisorStat.Backend.HypervisorStatsService;
import com.other.app.niralos_edge.dto.HypervisorLogDto;
import com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto;
import com.other.app.niralos_edge.dto.HypervisorStatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeHypervisorController {

    @Autowired
    HypervisorStatsService hypervisorStatsService;

    //Getting the Hypervisor data
    @GetMapping("/node/get_hypervisor")
    public List<HypervisorStatsModel> getAllHypervisorData()
    {
        return hypervisorStatsService.getAllHypervisor();
    }

    //Getting hypervisor network stats from database and sending it to frontend
    @GetMapping("/node/get_all_hypervisor_network_stats/edge_client_id={edge_client_id}")
    public ArrayList<HypervisorNetworkStatsDto> getHypervisorNetworkStats(@PathVariable("edge_client_id") String edgeClientId)
    {
        return hypervisorStatsService.getHypervisorNetworkStats(edgeClientId);
    }

    //Getting all hypervisor stats along with cpu and memory usage
    @GetMapping("/node/get_all_hypervisor_stats/edge_client_id={edge_client_id}")
    public ArrayList<HypervisorStatsDto> getHypervisorForSite(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return hypervisorStatsService.getAllHypervisorForSite(edgeClientId);
    }


    //Getting the hypervisor log data
    @GetMapping("/node/hypervisor/get_hypervisor_log/edge_client_id={edge_client_id}")
    public List<HypervisorLogDto> getHypervisorData(@PathVariable("edge_client_id") String edgeClientId)
    {
        return hypervisorStatsService.getHypervisorLog(edgeClientId);
    }


    //Getting the list of ISO available in the hypervisor
    @GetMapping("/node/iso/getting_iso_available/edge_client_id={edge_client_id}")
    public List<ListOfIsoModel> getIso(@PathVariable ("edge_client_id") String edgeClientId)
    {

        return hypervisorStatsService.getListOfIso(edgeClientId);
    }

    //List of internal data in the database
    @GetMapping("/return_bridge_networks/edge_client_id={edge_client_id}")
    public List<HypervisorNetworkStatsDto> getreturnBridgeNetworks(@PathVariable ("edge_client_id") String edgeClientId){
        return hypervisorStatsService.getHypervisorBridgeNetworks(edgeClientId);
    }

    //Getting the hypervisor task log
    @GetMapping("/node/hypervisor/task_log/edge_client_id={edge_client_id}")
    public ArrayList<HypervisorTaskLogModel> getTaskLog(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return hypervisorStatsService.getHypervisorLogData(edgeClientId);
    }
}

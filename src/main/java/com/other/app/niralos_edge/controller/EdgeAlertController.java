package com.other.app.niralos_edge.controller;


import com.other.app.niralos_edge.Service.AlertManagement.AlertService;
import com.other.app.niralos_edge.dto.AlertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeAlertController {

    @Autowired
    AlertService alertService;



    //Getting the hypervisor alert from database and sending it to UI
    @GetMapping("/node/alert/edge_alert/edge_client_id={edge_client_id}")
    public AlertDto getListOfAlert(@PathVariable("edge_client_id") String edgeClientId)
    {
        return alertService.getListOfAlert(edgeClientId);
    }

    @GetMapping("/node/alert/edge_alert/resolved/edge_client_id={edge_client_id}")
    public AlertDto getALert(@PathVariable("edge_client_id") String edgeClientId)
    {
        return alertService.getListOfAlertResolved(edgeClientId);
    }

    @GetMapping("/node/alert/edge_alert/unresolved/edge_client_id={edge_client_id}")
    public AlertDto getUnALert(@PathVariable("edge_client_id") String edgeClientId)
    {
        return alertService.getListOfAlertUnResolved(edgeClientId);
    }

    @PostMapping("/node/alert/delete_hypervisor_alert/edge_client_id={edge_client_id}/alert_id={alert_id}")
    public String deleteHypervisorAlert(@PathVariable ("edge_client_id") String edgeClientId,@PathVariable ("alert_id") Long alertId)
    {
        return alertService.deleteHypervisorAlert(edgeClientId, alertId);
    }

    @PostMapping("/node/alert/delete_vm_alert/edge_client_id={edge_client_id}/vmid={vmid}/alert_id={alert_id}")
    public String deleteHypervisorVmAlert(@PathVariable ("edge_client_id") String edgeClientId,@PathVariable ("vmid") Long vmid,@PathVariable ("alert_id") Long alertId)
    {
        return alertService.deleteVmAlert(edgeClientId,vmid,alertId);
    }

    @PostMapping("/node/alert/hypervisor/mark_all_resolved/edge_client_id={edge_client_id}")
    public String markAllResolved(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return alertService.allAlertResolved(edgeClientId);
    }

    @PostMapping("/node/alert/vm/mark_all_resolved/edge_client_id={edge_client_id}")
    public String markAllVmResolved(@PathVariable ("edge_client_id") String edgeClientId)
    {
        return alertService.allVmAlertResolved(edgeClientId);
    }

}

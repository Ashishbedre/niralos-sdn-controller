package com.other.app.niralos_edge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.HypervisorTaskLogModel;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.ListOfIsoModel;
import com.other.app.niralos_edge.Model.NodeUpdateDto;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Service.AlertManagement.AlertService;
import com.other.app.niralos_edge.Service.HypervisorStat.Backend.HypervisorStatsService;
import com.other.app.niralos_edge.Service.HypervisorSubnet.HypervisorSubnetService;
import com.other.app.niralos_edge.Service.NodeBoot.NodeBootInterface;
import com.other.app.niralos_edge.Service.SavingInternalData.InternalService;
import com.other.app.niralos_edge.Service.VMDetails.VmStatsService;
import com.other.app.niralos_edge.Service.VMManagement.VmMangementService;
import com.other.app.niralos_edge.Service.VmCreation.SpicePortGenService;
import com.other.app.niralos_edge.Service.VmCreation.VmCreation;
import com.other.app.niralos_edge.Service.VmModification.VmModificationService;
import com.other.app.niralos_edge.dto.AlertDto;
import com.other.app.niralos_edge.dto.EdgeFrontendDto;
import com.other.app.niralos_edge.dto.GetVmData;
import com.other.app.niralos_edge.dto.HypervisorCreateVmDto;
import com.other.app.niralos_edge.dto.HypervisorLogDto;
import com.other.app.niralos_edge.dto.HypervisorNetworkIntFaceCreationDto;
import com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto;
import com.other.app.niralos_edge.dto.HypervisorStatsDto;
import com.other.app.niralos_edge.dto.InternalDataDto;
import com.other.app.niralos_edge.dto.SpiceDataDto;
import com.other.app.niralos_edge.dto.VmCreationStatusDto;
import com.other.app.niralos_edge.dto.VmUpdateDto;

@RestController
@RequestMapping("/edge")
@CrossOrigin("*")
public class EdgeController {

	@Autowired
	HypervisorStatsService hypervisorStatsService;
	
//	@Autowired
//	VmMangementService vmMangementService;
	
//	@Autowired
//	VmStatsService vmStatsService;
	
	@Autowired
	VmCreation createService;
	
//	@Autowired
//	AlertService alertService;
	
	@Autowired
	VmModificationService modificationService;
	
//	@Autowired
//	InternalService internalService;
//
//	@Autowired
//	InternalDataRepositorys internalDataRepository;
	
	@Autowired
	HypervisorSubnetService subnetService;
	
	@Autowired
	SpicePortGenService spicePortGenService;
	
	@Autowired
	NodeBootInterface nodeBootInterface;
	
//
//	//Getting the Hypervisor data
//	@GetMapping("/node/get_hypervisor")
//	public List<HypervisorStatsModel> getAllHypervisorData()
//	{
//			return hypervisorStatsService.getAllHypervisor();
//	}
//
//	//Getting hypervisor network stats from database and sending it to frontend
//	@GetMapping("/node/get_all_hypervisor_network_stats/edge_client_id={edge_client_id}")
//	public ArrayList<HypervisorNetworkStatsDto> getHypervisorNetworkStats(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//	    return hypervisorStatsService.getHypervisorNetworkStats(edgeClientId);
//	}
//
//	//Getting all hypervisor stats along with cpu and memory usage
//	   @GetMapping("/node/get_all_hypervisor_stats/edge_client_id={edge_client_id}")
//	   public ArrayList<HypervisorStatsDto> getHypervisorForSite(@PathVariable ("edge_client_id") String edgeClientId)
//	    {
//		   return hypervisorStatsService.getAllHypervisorForSite(edgeClientId);
//	    }
	   
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   
////	VM Start, Shutdown and Restart Commands.
//	@PostMapping("/node/vm/shutdown_vm/edge_client_id={edge_client_id}/vmid={vmid}")
//	public void shutdownVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId) {
//		vmMangementService.stopVm(vmId,edgeClientId);
//		System.out.println("VMID: "+vmId+" has been shutdown.");
//	}
//
//	@PostMapping("/node/vm/start_vm/edge_client_id={edge_client_id}/vmid={vmid}")
//	public void startVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId) {
//		vmMangementService.startVm(vmId,edgeClientId);
//		System.out.println("VMID: "+vmId+" has been started.");
//	}
//
//	@PostMapping("/node/vm/restart_vm/edge_client_id={edge_client_id}/vmid={vmid}")
//	public void restartVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId) {
//		vmMangementService.restartVm(vmId,edgeClientId);
//		System.out.println("VMID: "+vmId+" has been restarted.");
//	}
//
//	@DeleteMapping("/node/vm/delete_vm/edge_client_id={edge_client_id}/vmid={vmid}")
//	public void deleteVm(@PathVariable("edge_client_id") String edgeClientId ,@PathVariable("vmid")Long vmId)
//	{
//		vmMangementService.deleteVm(vmId,edgeClientId);
//		System.out.println("VMID:"+vmId+"has been deleted");
//
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	//Getting the details of all vm for a particular site
//	@GetMapping("/node/vm/get_edge_frontend_data/edge_client_id={edge_client_id}")
//	public ArrayList<EdgeFrontendDto> getVmDetailsOfParticularSite(@PathVariable("edge_client_id") String edgeClientId)
//	{
//
//		return vmStatsService.getVmDetailsParticularSite(edgeClientId);
//	}
//
//	//Modified vm
//	//Getting the details of a particular vm for a particular site
//	@GetMapping("/node/vm/get_edge_frontend_vm/edge_client_id={edge_client_id}/vmid={vmid}")
//	public EdgeFrontendDto getParticularVM(@PathVariable("edge_client_id") String edgeClientId,@PathVariable("vmid") Long vmId)
//	{
//		return vmStatsService.getParticularVm(vmId,edgeClientId);
//	}
//
//
//	//Getting the cores and sockets and memory for a particular vm and edgeClientId
//	@GetMapping("/node/vm/get_vm_details_update/edge_client_id={edge_client_id}")
//	public VmUpdateDto getDetails(@PathVariable("edge_client_id") String edgeClientId)
//	{
//		return vmStatsService.getDetails(edgeClientId);
//	}
//
//	//Getting the hypervisor log data
//	@GetMapping("/node/hypervisor/get_hypervisor_log/edge_client_id={edge_client_id}")
//	public List<HypervisorLogDto> getHypervisorData(@PathVariable("edge_client_id") String edgeClientId)
//	{
//			return hypervisorStatsService.getHypervisorLog(edgeClientId);
//	}
		
	//Create VM
	@PostMapping("/node/vm/create_vm")
	public VmCreationStatusDto createVmStatus(@RequestBody com.other.app.niralos_edge.dto.CreateVmConfigDto config) throws InterruptedException {
		VmCreationStatusDto Status = null;
		try {
			
			createService.createVm(config);
			System.out.println("VM Provisioning Initiated");
				 
				
		} catch (Exception e) {
				
			System.out.println("Error Occurred While Provisioning VM");
			VmCreationStatusDto failStats = new VmCreationStatusDto(config.getVmid(), false);
			return failStats;
		}
		return Status;
	}
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	//Getting the hypervisor alert from database and sending it to UI
//	@GetMapping("/node/alert/edge_alert/edge_client_id={edge_client_id}")
//	public AlertDto getListOfAlert(@PathVariable("edge_client_id") String edgeClientId)
//	{
//		return alertService.getListOfAlert(edgeClientId);
//	}
//
//	@GetMapping("/node/alert/edge_alert/resolved/edge_client_id={edge_client_id}")
//	public AlertDto getALert(@PathVariable("edge_client_id") String edgeClientId)
//	{
//		return alertService.getListOfAlertResolved(edgeClientId);
//	}
//
//	@GetMapping("/node/alert/edge_alert/unresolved/edge_client_id={edge_client_id}")
//	public AlertDto getUnALert(@PathVariable("edge_client_id") String edgeClientId)
//	{
//		return alertService.getListOfAlertUnResolved(edgeClientId);
//	}
//
//	@PostMapping("/node/alert/delete_hypervisor_alert/edge_client_id={edge_client_id}/alert_id={alert_id}")
//	public String deleteHypervisorAlert(@PathVariable ("edge_client_id") String edgeClientId,@PathVariable ("alert_id") Long alertId)
//	{
//		return alertService.deleteHypervisorAlert(edgeClientId, alertId);
//	}
//
//	@PostMapping("/node/alert/delete_vm_alert/edge_client_id={edge_client_id}/vmid={vmid}/alert_id={alert_id}")
//	public String deleteHypervisorVmAlert(@PathVariable ("edge_client_id") String edgeClientId,@PathVariable ("vmid") Long vmid,@PathVariable ("alert_id") Long alertId)
//	{
//		return alertService.deleteVmAlert(edgeClientId,vmid,alertId);
//	}
//
//	@PostMapping("/node/alert/hypervisor/mark_all_resolved/edge_client_id={edge_client_id}")
//	public String markAllResolved(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return alertService.allAlertResolved(edgeClientId);
//	}
//
//	@PostMapping("/node/alert/vm/mark_all_resolved/edge_client_id={edge_client_id}")
//	public String markAllVmResolved(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return alertService.allVmAlertResolved(edgeClientId);
//	}
//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	//Getting the list of ISO available in the hypervisor
//	@GetMapping("/node/iso/getting_iso_available/edge_client_id={edge_client_id}")
//	public List<ListOfIsoModel> getIso(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//
//		return hypervisorStatsService.getListOfIso(edgeClientId);
//	}
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	//Post api for saving internal data
//	@PostMapping("/node/save_internal_data")
//	public String savingInternalData(@RequestBody InternalDataDto dto)
//	{
//		return internalService.saveInternalData(dto);
//	}
//
//	//Updating the data in the database
//	@PutMapping("/node/update_data/edge_client_id={edge_client_id}")
//	public String updatingInternalData(@RequestBody InternalDataDto dto,@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return internalService.updateInternalData(dto,edgeClientId);
//	}
//
//	//Deleting Internal Data from the database
//	@DeleteMapping("/node/delete_data/edge_client_id={edge_client_id}")
//	public String deleteInternalData(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return internalService.deleteInternalData(edgeClientId);
//	}
//
//	//List of internal data in the database
//	@GetMapping("/node/get_internal_data")
//	public List<InternalDataModels> getInternalData()
//	{
//		return internalDataRepository.findAll();
//	}
//
//	@GetMapping("/node/get_specific_internal_data/edge_client_id={edge_client_id}")
//	public InternalDataModels getSpecificInternalData(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return internalDataRepository.getData(edgeClientId);
//	}
//
	///////////////////////////////////////////////////////////////////////////////////////
		
	@PostMapping("/node/hypervisor/create_interface/edge_client_id={edge_client_id}")
	public String createInterface(@RequestBody HypervisorNetworkIntFaceCreationDto dto,@PathVariable ("edge_client_id") String edgeClientId)
	{
		return subnetService.createInterface(edgeClientId, dto);
	}
		
	//Post api to modify the vm details in the proxmox
	@PostMapping("/node/vm/vm_update/edge_client_id={edge_client_id}")
	public ResponseEntity<String> modifyVm(@RequestBody VmUpdateDto dto,@PathVariable("edge_client_id") String edgeClientId)
	{
		return modificationService.updateVm(dto,edgeClientId);
	}
				
//	//List of internal data in the database
//	@GetMapping("/return_bridge_networks/edge_client_id={edge_client_id}")
//	public List<HypervisorNetworkStatsDto> getreturnBridgeNetworks(@PathVariable ("edge_client_id") String edgeClientId){
//			return hypervisorStatsService.getHypervisorBridgeNetworks(edgeClientId);
//	}
		
//	//Checking the vm details
//	/////////////////////////////////////////////////////////////////////////////////
//	@GetMapping("/node/hypervisor/vm_check/edge_client_id={edge_client_id}")
//	public HypervisorCreateVmDto getVMUpdate(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return vmStatsService.getVm(edgeClientId);
//	}
		
	//////////////////////////////////////////////////////////////////////////////
		
	@GetMapping("/node/spice_data/edge_client_id={edge_client_id}/vm_id={vm_id}")
	public SpiceDataDto getSpiceData(@PathVariable ("edge_client_id") String edgeClientId,@PathVariable ("vm_id") Long vmId){
			return spicePortGenService.getSpiceData(edgeClientId, vmId);
		}
		
		
	/////////////////////////////////////////////////////////////////////////////////
		
//	//Getting the core,socket,memory of a particular vm
//	@GetMapping("/node/get_vm_data/edge_client_id={edge_client_id}/vm_id={vm_id}")
//	public GetVmData getVmData(@PathVariable ("edge_client_id") String edgeClientId,@PathVariable ("vm_id") Long vmId)
//	{
//		return vmStatsService.getData(vmId, edgeClientId);
//	}
		
    /////////////////////////////////////////////////////////////////////////////////
		
	@PostMapping("/node/boot/edge_client_id={edge_client_id}")
	public void getVmData(@RequestBody NodeUpdateDto payload, @PathVariable ("edge_client_id") String edgeClientId)
	{
		 nodeBootInterface.nodeRebootandShutdown(payload,edgeClientId);
	}
		
	///////////////////////////////////////////////////////////////////////////////////
//	//Getting the hypervisor task log
//	@GetMapping("/node/hypervisor/task_log/edge_client_id={edge_client_id}")
//	public ArrayList<HypervisorTaskLogModel> getTaskLog(@PathVariable ("edge_client_id") String edgeClientId)
//	{
//		return hypervisorStatsService.getHypervisorLogData(edgeClientId);
//	}
	  
}

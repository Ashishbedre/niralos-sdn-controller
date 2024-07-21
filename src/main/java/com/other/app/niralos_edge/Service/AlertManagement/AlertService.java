package com.other.app.niralos_edge.Service.AlertManagement;


import com.other.app.niralos_edge.dto.AlertDto;


public interface AlertService {

	public void savingHypervisorAndVmAlert();
	
	public AlertDto getListOfAlert(String edgeClientId);
	
	public AlertDto getListOfAlertResolved(String edgeClientId);
	
	public String allAlertResolved(String edgeClientId);
	
	public String allVmAlertResolved(String edgeClientId);
	
	public AlertDto getListOfAlertUnResolved(String edgeClientId);
	
	public String deleteHypervisorAlert(String edgeClientId,Long alertId);
	
	public String deleteVmAlert(String edgeClientId,Long vmid,Long alert);
}

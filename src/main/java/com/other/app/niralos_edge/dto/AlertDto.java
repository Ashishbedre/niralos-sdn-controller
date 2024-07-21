package com.other.app.niralos_edge.dto;


import java.util.ArrayList;

import com.other.app.niralos_edge.Model.EdgeHypervisorAlertModel;
import com.other.app.niralos_edge.Model.EdgeVmAlertModel;


public class AlertDto {

	
    private String hypervisorId;
	
	private ArrayList<EdgeHypervisorAlertModel> hypervisorAlert;
	
	private ArrayList<EdgeVmAlertModel> hypervisorVmAlert;

	public String getHypervisorId() {
		return hypervisorId;
	}

	public void setHypervisorId(String hypervisorId) {
		this.hypervisorId = hypervisorId;
	}

	public ArrayList<EdgeHypervisorAlertModel> getHypervisorAlert() {
		return hypervisorAlert;
	}

	public void setHypervisorAlert(ArrayList<EdgeHypervisorAlertModel> hypervisorAlert) {
		this.hypervisorAlert = hypervisorAlert;
	}

	public ArrayList<EdgeVmAlertModel> getHypervisorVmAlert() {
		return hypervisorVmAlert;
	}

	public void setHypervisorVmAlert(ArrayList<EdgeVmAlertModel> hypervisorVmAlert) {
		this.hypervisorVmAlert = hypervisorVmAlert;
	}
	
	
}

package com.other.app.niralos_edge.dto.KafkaSender;

import java.util.List;

import com.other.app.niralos_edge.Model.HypervisorNetworkStatsModel;
import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.ListOfIsoModel;
import com.other.app.niralos_edge.Model.VmNetworkStatsModel;
import com.other.app.niralos_edge.Model.VmStatsModel;

public class KafkaMessageSenderDto {

	private String deploymentId;
	private String tenantName;
	private List<InternalDataModels> internalDataModels;
	private List<HypervisorStatsModel> hypervisorStatsModels;
	private List<HypervisorNetworkStatsModel> hypervisorNetworkStatsModels;
	private List<VmStatsModel> vmStatsModels;
	private List<VmNetworkStatsModel> vmNetworkStatsModels;
	private List<ListOfIsoModel> listOfIsoModels;
	public List<ListOfIsoModel> getListOfIsoModels() {
		return listOfIsoModels;
	}
	public void setListOfIsoModels(List<ListOfIsoModel> listOfIsoModels) {
		this.listOfIsoModels = listOfIsoModels;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public List<InternalDataModels> getInternalDataModels() {
		return internalDataModels;
	}
	public void setInternalDataModels(List<InternalDataModels> internalDataModels) {
		this.internalDataModels = internalDataModels;
	}
	public List<HypervisorStatsModel> getHypervisorStatsModels() {
		return hypervisorStatsModels;
	}
	public void setHypervisorStatsModels(List<HypervisorStatsModel> hypervisorStatsModels) {
		this.hypervisorStatsModels = hypervisorStatsModels;
	}
	public List<HypervisorNetworkStatsModel> getHypervisorNetworkStatsModels() {
		return hypervisorNetworkStatsModels;
	}
	public void setHypervisorNetworkStatsModels(List<HypervisorNetworkStatsModel> hypervisorNetworkStatsModels) {
		this.hypervisorNetworkStatsModels = hypervisorNetworkStatsModels;
	}
	public List<VmStatsModel> getVmStatsModels() {
		return vmStatsModels;
	}
	public void setVmStatsModels(List<VmStatsModel> vmStatsModels) {
		this.vmStatsModels = vmStatsModels;
	}
	public List<VmNetworkStatsModel> getVmNetworkStatsModels() {
		return vmNetworkStatsModels;
	}
	public void setVmNetworkStatsModels(List<VmNetworkStatsModel> vmNetworkStatsModels) {
		this.vmNetworkStatsModels = vmNetworkStatsModels;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
	
	
	
	
	
}

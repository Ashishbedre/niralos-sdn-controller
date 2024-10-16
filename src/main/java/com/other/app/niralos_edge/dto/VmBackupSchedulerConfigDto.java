package com.other.app.niralos_edge.dto;

public class VmBackupSchedulerConfigDto {
	
	private String edgeClientId;
	
	private String schedule;
	
	private String vmid;
	
	

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}
	
	

}

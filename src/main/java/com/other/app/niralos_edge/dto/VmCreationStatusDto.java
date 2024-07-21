package com.other.app.niralos_edge.dto;


public class VmCreationStatusDto {

	public Long vmid;
	
	public Boolean provisionStatus;

	public Long getVmid() {
		return vmid;
	}

	public void setVmid(Long vmid) {
		this.vmid = vmid;
	}

	public Boolean getProvisionStatus() {
		return provisionStatus;
	}

	public void setProvisionStatus(Boolean provisionStatus) {
		this.provisionStatus = provisionStatus;
	}

	public VmCreationStatusDto(Long vmid, Boolean provisionStatus) {
		super();
		this.vmid = vmid;
		this.provisionStatus = provisionStatus;
	}

	public VmCreationStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

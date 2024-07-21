package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vm_provison_status")
public class VmProvisionStatus {
	
	@Id
	@Column(name="id")
	public Long vmid;
	
	@Column(name="provision_status")
	public Boolean provisionStatus;
	
	public VmProvisionStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmProvisionStatus(Long vmid, Boolean provisionStatus) {
		super();
		this.vmid = vmid;
		this.provisionStatus = provisionStatus;
	}

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
	
	
	

}

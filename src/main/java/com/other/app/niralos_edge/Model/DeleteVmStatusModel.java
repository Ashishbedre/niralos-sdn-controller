package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="delete_vm_status")
public class DeleteVmStatusModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	@Column(name="vm_id")
	private Long vmId;
	@Column(name="status")
	private Boolean status;
	@Column(name="edge_client_id")
	private String edgeClientId;
	
	public Long getVmId() {
		return vmId;
	}
	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getEdgeClientId() {
		return edgeClientId;
	}
	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
	
	public DeleteVmStatusModel(Long vmId, Boolean status, String edgeClientId) {
		super();
		this.vmId = vmId;
		this.status = status;
		this.edgeClientId = edgeClientId;
	}
	
	public DeleteVmStatusModel() {
		super();
	}
	
	
}

package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vm_creation_check")
public class VmCreationCheckModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int id;
	
	@Column(name = "totalSockets")
	private Long totalSockets;
	
	@Column(name = "totalCores")
	private Long totalCores;
	
	@Column(name = "availableMemory")
	private Long availableMemory;
	
	@Column(name = "availableDiskSpace")
	private Long availableDiskSpace;
	
	@Column(name = "vmid")
	private Long vmid;
	
	@Column(name = "tenant_id")
	private String tenantId;
	
	@Column(name = "site_id")
	private String siteId;
	
	@Column(name = "edge_client_id")
	private String edgeClientId;

	
	public VmCreationCheckModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public VmCreationCheckModel(int id, Long totalSockets, Long totalCores, Long availableMemory, Long availableDiskSpace,
			Long vmid, String tenantId, String siteId, String edgeClientId) {
		super();
		this.id = id;
		this.totalSockets = totalSockets;
		this.totalCores = totalCores;
		this.availableMemory = availableMemory;
		this.availableDiskSpace = availableDiskSpace;
		this.vmid = vmid;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.edgeClientId = edgeClientId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Long getTotalSockets() {
		return totalSockets;
	}


	public void setTotalSockets(Long totalSockets) {
		this.totalSockets = totalSockets;
	}


	public Long getTotalCores() {
		return totalCores;
	}


	public void setTotalCores(Long totalCores) {
		this.totalCores = totalCores;
	}


	public Long getAvailableMemory() {
		return availableMemory;
	}


	public void setAvailableMemory(Long availableMemory) {
		this.availableMemory = availableMemory;
	}


	public Long getAvailableDiskSpace() {
		return availableDiskSpace;
	}


	public void setAvailableDiskSpace(Long availableDiskSpace) {
		this.availableDiskSpace = availableDiskSpace;
	}


	public Long getVmid() {
		return vmid;
	}


	public void setVmid(Long vmid) {
		this.vmid = vmid;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}


	public String getEdgeClientId() {
		return edgeClientId;
	}


	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
	
	
	
}

package com.other.app.niralos_edge.dto;

public class HypervisorCreateVmDto {

	private Long vmId;
	private Long totalSockets;
	private Long totalCores;
	private Long availableMemory;
	private Long availableDiskspace;
	private String edgeClientId;
	
	public Long getVmId() {
		return vmId;
	}
	public void setVmId(Long vmId) {
		this.vmId = vmId;
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
	public Long getAvailableDiskspace() {
		return availableDiskspace;
	}
	public void setAvailableDiskspace(Long availableDiskspace) {
		this.availableDiskspace = availableDiskspace;
	}
	public String getEdgeClientId() {
		return edgeClientId;
	}
	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
}

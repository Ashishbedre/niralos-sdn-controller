package com.other.app.niralos_edge.dto;

public class GetVmData {

	private String memory;
    private String cores;
    private String sockets;
    private Long vmId;
    
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getSockets() {
		return sockets;
	}
	public void setSockets(String sockets) {
		this.sockets = sockets;
	}
	public Long getVmId() {
		return vmId;
	}
	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}
    
    
}

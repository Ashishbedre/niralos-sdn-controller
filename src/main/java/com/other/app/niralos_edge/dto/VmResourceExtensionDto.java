package com.other.app.niralos_edge.dto;

public class VmResourceExtensionDto {
	
	private String memory;
	
	private String sockets;
	
	private String cores;

	public VmResourceExtensionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmResourceExtensionDto(String memory, String sockets, String cores) {
		super();
		this.memory = memory;
		this.sockets = sockets;
		this.cores = cores;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getSockets() {
		return sockets;
	}

	public void setSockets(String sockets) {
		this.sockets = sockets;
	}

	public String getCores() {
		return cores;
	}

	public void setCores(String cores) {
		this.cores = cores;
	}
	
	

}

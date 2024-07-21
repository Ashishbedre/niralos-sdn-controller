package com.other.app.niralos_edge.dto;

public class RestoreVmDto {
	
	private String vmid;
	
	private String archive;

	public RestoreVmDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestoreVmDto(String vmid, String archive) {
		super();
		this.vmid = vmid;
		this.archive = archive;
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}


	
	

}

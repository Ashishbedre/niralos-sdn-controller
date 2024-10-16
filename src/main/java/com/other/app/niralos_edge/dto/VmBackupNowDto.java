package com.other.app.niralos_edge.dto;

public class VmBackupNowDto {
	
	private String storage;
	private String vmid;
	private String mode;
	private String remove;
	private String compress;
	
	public VmBackupNowDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmBackupNowDto(String storage, String vmid, String mode, String remove, String compress) {
		super();
		this.storage = storage;
		this.vmid = vmid;
		this.mode = mode;
		this.remove = remove;
		this.compress = compress;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getRemove() {
		return remove;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}

	public String getCompress() {
		return compress;
	}

	public void setCompress(String compress) {
		this.compress = compress;
	}
	
	

}

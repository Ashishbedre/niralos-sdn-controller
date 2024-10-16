package com.other.app.niralos_edge.dto;

public class VmBackupSchedulerDto {
	
	private String storage;
    private String schedule;
    private String compress;
    private String mode;
    private String enabled;
    private String vmid;
	
    public VmBackupSchedulerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmBackupSchedulerDto(String storage, String schedule, String compress, String mode, String enabled,
			String vmid) {
		super();
		this.storage = storage;
		this.schedule = schedule;
		this.compress = compress;
		this.mode = mode;
		this.enabled = enabled;
		this.vmid = vmid;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getCompress() {
		return compress;
	}

	public void setCompress(String compress) {
		this.compress = compress;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}
    
    

}

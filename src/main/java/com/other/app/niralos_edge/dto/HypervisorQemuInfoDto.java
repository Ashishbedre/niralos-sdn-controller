package com.other.app.niralos_edge.dto;


public class HypervisorQemuInfoDto {
	
	 
	  private Long vmid;
	  
	  private Long cpus;

	  private Long netout;

	  private Long diskwrite;

	  private String name;

	  private Long disk;

	  private Long diskread;

	  private Long cpu;

	  private Long maxdisk;

	  private Long maxmem;

	  private Long mem;

	  private String status;

	  private Long netin;

	  private Long uptime;

	

	
	public Long getVmid() {
		return vmid;
	}

	public void setVmid(Long vmid) {
		this.vmid = vmid;
	}

	public Long getCpus() {
		return cpus;
	}

	public void setCpus(Long cpus) {
		this.cpus = cpus;
	}

	public Long getNetout() {
		return netout;
	}

	public void setNetout(Long netout) {
		this.netout = netout;
	}

	public Long getDiskwrite() {
		return diskwrite;
	}

	public void setDiskwrite(Long diskwrite) {
		this.diskwrite = diskwrite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDisk() {
		return disk;
	}

	public void setDisk(Long disk) {
		this.disk = disk;
	}

	public Long getDiskread() {
		return diskread;
	}

	public void setDiskread(Long diskread) {
		this.diskread = diskread;
	}

	public Long getCpu() {
		return cpu;
	}

	public void setCpu(Long cpu) {
		this.cpu = cpu;
	}

	public Long getMaxdisk() {
		return maxdisk;
	}

	public void setMaxdisk(Long maxdisk) {
		this.maxdisk = maxdisk;
	}

	public Long getMaxmem() {
		return maxmem;
	}

	public void setMaxmem(Long maxmem) {
		this.maxmem = maxmem;
	}

	public Long getMem() {
		return mem;
	}

	public void setMem(Long mem) {
		this.mem = mem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getNetin() {
		return netin;
	}

	public void setNetin(Long netin) {
		this.netin = netin;
	}

	public Long getUptime() {
		return uptime;
	}

	public void setUptime(Long uptime) {
		this.uptime = uptime;
	}

	
}

package com.other.app.niralos_edge.dto.hypervisorstatsdto;

import java.util.List;

public class Data {
	
	private Rootfs rootfs;

    private double cpu;

    private Long uptime;

    private Long idle;

    private Ksm ksm;

    private Cpuinfo cpuinfo;

    private double wait;

    private String kversion;

    private String pveversion;

    private List<String> loadavg;

    private Memory memory;

    private Swap swap;

	public Rootfs getRootfs() {
		return rootfs;
	}

	public void setRootfs(Rootfs rootfs) {
		this.rootfs = rootfs;
	}

	public double getCpu() {
		return cpu;
	}

	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	public Long getUptime() {
		return uptime;
	}

	public void setUptime(Long uptime) {
		this.uptime = uptime;
	}

	public Long getIdle() {
		return idle;
	}

	public void setIdle(Long idle) {
		this.idle = idle;
	}

	public Ksm getKsm() {
		return ksm;
	}

	public void setKsm(Ksm ksm) {
		this.ksm = ksm;
	}

	public Cpuinfo getCpuinfo() {
		return cpuinfo;
	}

	public void setCpuinfo(Cpuinfo cpuinfo) {
		this.cpuinfo = cpuinfo;
	}

	public double getWait() {
		return wait;
	}

	public void setWait(double wait) {
		this.wait = wait;
	}

	public String getKversion() {
		return kversion;
	}

	public void setKversion(String kversion) {
		this.kversion = kversion;
	}

	public String getPveversion() {
		return pveversion;
	}

	public void setPveversion(String pveversion) {
		this.pveversion = pveversion;
	}

	public List<String> getLoadavg() {
		return loadavg;
	}

	public void setLoadavg(List<String> loadavg) {
		this.loadavg = loadavg;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public Swap getSwap() {
		return swap;
	}

	public void setSwap(Swap swap) {
		this.swap = swap;
	}
    
    

}

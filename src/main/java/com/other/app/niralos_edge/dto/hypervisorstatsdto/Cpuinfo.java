package com.other.app.niralos_edge.dto.hypervisorstatsdto;

public class Cpuinfo {
	
	private Long cores;

    private Long sockets;

    private Long user_hz;

    private Double mhz;

    private String model;

    private String hvm;

    private Long cpus;

    private String flags;

	public Long getCores() {
		return cores;
	}

	public void setCores(Long cores) {
		this.cores = cores;
	}

	public Long getSockets() {
		return sockets;
	}

	public void setSockets(Long sockets) {
		this.sockets = sockets;
	}

	public Long getUser_hz() {
		return user_hz;
	}

	public void setUser_hz(Long user_hz) {
		this.user_hz = user_hz;
	}

	public Double getMhz() {
		return mhz;
	}

	public void setMhz(Double mhz) {
		this.mhz = mhz;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getHvm() {
		return hvm;
	}

	public void setHvm(String hvm) {
		this.hvm = hvm;
	}

	public Long getCpus() {
		return cpus;
	}

	public void setCpus(Long cpus) {
		this.cpus = cpus;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}
    
    

}

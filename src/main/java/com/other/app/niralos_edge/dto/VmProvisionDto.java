package com.other.app.niralos_edge.dto;

public class VmProvisionDto {
	
	private String node;
	
	private Long vmid;
	
	private String name;
	
	private String ide2;
	
	private String sockets;
	
	private String cores;
	
	private String memory;
	
	private String net0;
	
	private String ostype;
	
	private String scsi0;
	
	private String agent;
	
	private String scsihw;
	
	private String onboot;
	
	private String start;
	
	private String args;
	
	private String vga;

	public VmProvisionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmProvisionDto(String node, Long vmid, String name, String ide2, String sockets, String cores, String memory,
			String net0, String ostype, String scsi0, String agent, String scsihw, String onboot, String start,
			String args, String vga) {
		super();
		this.node = node;
		this.vmid = vmid;
		this.name = name;
		this.ide2 = ide2;
		this.sockets = sockets;
		this.cores = cores;
		this.memory = memory;
		this.net0 = net0;
		this.ostype = ostype;
		this.scsi0 = scsi0;
		this.agent = agent;
		this.scsihw = scsihw;
		this.onboot = onboot;
		this.start = start;
		this.args = args;
		this.vga = vga;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public Long getVmid() {
		return vmid;
	}

	public void setVmid(Long vmid) {
		this.vmid = vmid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIde2() {
		return ide2;
	}

	public void setIde2(String ide2) {
		this.ide2 = ide2;
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

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getNet0() {
		return net0;
	}

	public void setNet0(String net0) {
		this.net0 = net0;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getScsi0() {
		return scsi0;
	}

	public void setScsi0(String scsi0) {
		this.scsi0 = scsi0;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getScsihw() {
		return scsihw;
	}

	public void setScsihw(String scsihw) {
		this.scsihw = scsihw;
	}

	public String getOnboot() {
		return onboot;
	}

	public void setOnboot(String onboot) {
		this.onboot = onboot;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getVga() {
		return vga;
	}

	public void setVga(String vga) {
		this.vga = vga;
	}

	
	
	

}

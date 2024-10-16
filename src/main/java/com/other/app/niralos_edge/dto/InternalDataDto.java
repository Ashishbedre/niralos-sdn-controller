package com.other.app.niralos_edge.dto;

public class InternalDataDto {

	private String hypervisorIp;
	private String hypervisorToken;
	private String nodeName;
	private String userName;
	private String password;
	private String hypervisorPort;
	
	public String getHypervisorIp() {
		return hypervisorIp;
	}
	public void setHypervisorIp(String hypervisorIp) {
		this.hypervisorIp = hypervisorIp;
	}
	public String getHypervisorToken() {
		return hypervisorToken;
	}
	public void setHypervisorToken(String hypervisorToken) {
		this.hypervisorToken = hypervisorToken;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHypervisorPort() {
		return hypervisorPort;
	}
	public void setHypervisorPort(String hypervisorPort) {
		this.hypervisorPort = hypervisorPort;
	}
	
	public InternalDataDto(String hypervisorIp, String hypervisorToken, String nodeName, String userName,
			String password, String hypervisorPort) {
		super();
		this.hypervisorIp = hypervisorIp;
		this.hypervisorToken = hypervisorToken;
		this.nodeName = nodeName;
		this.userName = userName;
		this.password = password;
		this.hypervisorPort = hypervisorPort;
	}
	
	
	public InternalDataDto() {
		super();
	}
	
	
	
	
}

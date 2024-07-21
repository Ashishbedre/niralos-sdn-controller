package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "edge_internal_data")
public class InternalDataModels {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "edge_client_id")
	private String edgeClientId;
	@Column(name = "hypervisor_ip")
	private String hypervisorIp;
	@Column(name = "hypervisor_token")
	private String hypervisorToken;
	@Column(name="hypervisor_node_name")
	private String hypervisorNodeName;
	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="hypervisorPort")
	private String hypervisorPort;
	
	public String getEdgeClientId() {
		return edgeClientId;
	}
	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
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
	public String getHypervisorNodeName() {
		return hypervisorNodeName;
	}
	public void setHypervisorNodeName(String hypervisorNodeName) {
		this.hypervisorNodeName = hypervisorNodeName;
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
	
	
	public InternalDataModels(String edgeClientId, String hypervisorIp, String hypervisorToken,
			String hypervisorNodeName, String userName, String password, String hypervisorPort) {
		super();
		this.edgeClientId = edgeClientId;
		this.hypervisorIp = hypervisorIp;
		this.hypervisorToken = hypervisorToken;
		this.hypervisorNodeName = hypervisorNodeName;
		this.userName = userName;
		this.password = password;
		this.hypervisorPort = hypervisorPort;
	}
	
	
	public InternalDataModels() {
		super();
	}
	
	
	
	
	
	
	
	
	



}

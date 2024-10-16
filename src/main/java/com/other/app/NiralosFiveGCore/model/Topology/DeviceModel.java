package com.other.app.NiralosFiveGCore.model.Topology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeviceModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "device_id")
	long DeviceId;
	@Column(name = "agent_id")
	String agentId;
	@Column(name = "device_name")
	String DeviceName;
	@Column(name = "deployment_id")
	Long deploymentId;
	@Column(name = "status")
	Boolean status;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	
	public DeviceModel(long deviceId, String agentId, String deviceName, Long deploymentId, Boolean status,
			 String nfType, String nfName) {
		super();
		DeviceId = deviceId;
		this.agentId = agentId;
		DeviceName = deviceName;
		this.deploymentId = deploymentId;
		this.status = status;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public DeviceModel() {
		super();
	}
	public long getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(long deviceId) {
		DeviceId = deviceId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	public Long getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(Long deploymentId) {
		this.deploymentId = deploymentId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNfType() {
		return nfType;
	}
	public void setNfType(String nfType) {
		this.nfType = nfType;
	}
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	

	

}

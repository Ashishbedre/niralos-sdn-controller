package com.other.app.NiralosFiveGCore.model.Topology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeploymentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="deployment_id")
	long deploymentId;
	@Column(name="deployment_name")
	String DeploymentName;
	@Column(name="subzone_id")
	Long subZoneId;
	@Column(name="status")
	Boolean status;
	@Column(name="tenent_name")
	String tenentName;
	@Column(name="subzone_name")
	String subZoneName;
	

	public DeploymentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(long deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getDeploymentName() {
		return DeploymentName;
	}
	public void setDeploymentName(String deploymentName) {
		DeploymentName = deploymentName;
	}
	public Long getSubZoneId() {
		return subZoneId;
	}
	public void setSubZoneId(Long subZoneId) {
		this.subZoneId = subZoneId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getTenentName() {
		return tenentName;
	}
	public void setTenentName(String tenentName) {
		this.tenentName = tenentName;
	}
	public String getSubZoneName() {
		return subZoneName;
	}
	public void setSubZoneName(String subZoneName) {
		this.subZoneName = subZoneName;
	}
	public DeploymentModel(long deploymentId, String deploymentName, Long subZoneId, Boolean status, String tenentName,
			String subZoneName) {
		super();
		this.deploymentId = deploymentId;
		DeploymentName = deploymentName;
		this.subZoneId = subZoneId;
		this.status = status;
		this.tenentName = tenentName;
		this.subZoneName = subZoneName;
	}
	
	

	


}

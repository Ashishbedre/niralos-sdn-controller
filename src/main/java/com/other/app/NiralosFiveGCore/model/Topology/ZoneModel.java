package com.other.app.NiralosFiveGCore.model.Topology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZoneModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="zone_id")
	Long zoneId;
	@Column(name="zone_name")
	String zoneName;
	@Column(name="status")
	Boolean status;
	@Column(name="tenent_name")
	String tenentName;
	public ZoneModel(Long zoneId, String zoneName, Boolean status, String tenentName) {
		super();
		this.zoneId = zoneId;
		this.zoneName = zoneName;
		this.status = status;
		this.tenentName = tenentName;
	}
	public ZoneModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getZoneId() {
		return zoneId;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
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
	
	


	

}

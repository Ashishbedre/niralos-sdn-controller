package com.other.app.NiralosFiveGCore.model.Topology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubZoneModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="subzone_id")
	long subZoneId;
	@Column(name="subzone_name")
	String subZoneName;
	@Column(name="zone_id")
	Long zoneId;
	@Column(name="status")
	Boolean status;
	@Column(name = "tenent_id")
    private String tenentId;

	
	public SubZoneModel(long subZoneId, String subZoneName, Long zoneId, Boolean status, String tenentId) {
		super();
		this.subZoneId = subZoneId;
		this.subZoneName = subZoneName;
		this.zoneId = zoneId;
		this.status = status;
		this.tenentId = tenentId;
	}

	public String getTenentId() {
		return tenentId;
	}

	public void setTenentId(String tenentId) {
		this.tenentId = tenentId;
	}

	public SubZoneModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getSubZoneId() {
		return subZoneId;
	}

	public void setSubZoneId(long subZoneId) {
		this.subZoneId = subZoneId;
	}

	public String getSubZoneName() {
		return subZoneName;
	}

	public void setSubZoneName(String subZoneName) {
		this.subZoneName = subZoneName;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}

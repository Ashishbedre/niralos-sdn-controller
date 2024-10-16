package com.other.app.NiralosFiveGCore.model.Topology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gnb_model")
public class GnBModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="gnb_sl_no")
	Long gnbSlNo;
	@Column(name="gnb_id")
	Integer gnbId;
	@Column(name="gnb_name")
	String gnbName;
	@Column(name="device_id")
	Integer deviceId;
	@Column(name="status")
	Boolean status;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	
	
	public GnBModel(Integer gnbId, String gnbName, Integer deviceId, Boolean status, String nfType, String nfName) {
		super();
		this.gnbId = gnbId;
		this.gnbName = gnbName;
		this.deviceId = deviceId;
		this.status = status;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public GnBModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getGnbId() {
		return gnbId;
	}
	public void setGnbId(Integer gnbId) {
		this.gnbId = gnbId;
	}
	public String getGnbName() {
		return gnbName;
	}
	public void setGnbName(String gnbName) {
		this.gnbName = gnbName;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
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

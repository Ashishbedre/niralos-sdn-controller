package com.other.app.NiralosFiveGCore.model.UeHisotry;


import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ue_history") 

public class UeHistoryModel {
	
	@Id
	@Field("_id")
	private ObjectId id;
	
	private String nfDomain;
    private String ueStatus;
    private String time;
    private String gnbId;
    private String imsi;
    private String pduStatus;
    private String sst;
    private String sd;
    private String dnn;
    private String psi;
	String nfType;
	String nfName;
	
	
		

	public UeHistoryModel(ObjectId id, String nfDomain, String ueStatus, String time, String gnbId, String imsi,
			String pduStatus, String sst, String sd, String dnn, String psi,
			 String nfType, String nfName) {
		super();
		this.id = id;
		this.nfDomain = nfDomain;
		this.ueStatus = ueStatus;
		this.time = time;
		this.gnbId = gnbId;
		this.imsi = imsi;
		this.pduStatus = pduStatus;
		this.sst = sst;
		this.sd = sd;
		this.dnn = dnn;
		this.psi = psi;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
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
	public String getNfDomain() {
		return nfDomain;
	}
	public void setNfDomain(String nfDomain) {
		this.nfDomain = nfDomain;
	}
	public String getUeStatus() {
		return ueStatus;
	}
	public void setUeStatus(String ueStatus) {
		this.ueStatus = ueStatus;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGnbId() {
		return gnbId;
	}
	public void setGnbId(String gnbId) {
		this.gnbId = gnbId;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getPduStatus() {
		return pduStatus;
	}
	public void setPduStatus(String pduStatus) {
		this.pduStatus = pduStatus;
	}
	public String getSst() {
		return sst;
	}
	public void setSst(String sst) {
		this.sst = sst;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getDnn() {
		return dnn;
	}
	public void setDnn(String dnn) {
		this.dnn = dnn;
	}
	public String getPsi() {
		return psi;
	}
	public void setPsi(String psi) {
		this.psi = psi;
	}
	
	
	
	public UeHistoryModel() {
		super();
	}
	
	
	
	
	
	
	

}

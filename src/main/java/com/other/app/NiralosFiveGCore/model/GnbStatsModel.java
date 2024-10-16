package com.other.app.NiralosFiveGCore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gnb_stats_model")
public class GnbStatsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "log_id")
	private Long logId;
	@Column(name = "gnb_id")
	private String gnbId;
	@Column(name = "amf_name")
	private String amfName;
	@Column(name = "plmn")
	private String plmn;
	@Column(name = "active_amf_session")
	private String activeAmfSession;
	@Column(name = "life_time_amf_session")
	private String lifeTimeAmfSession;
	@Column(name = "ip")
	private String ip;
	@Column(name = "tac")
	private String tac;
	@Column(name = "total_gnb_session")
	private String totalGnbSession;
	@Column(name = "active_gnb_session")
	private String activeGnbSession;
	@Column(name = "cell_global_identity")
	private String cellGlobalIdentity;
	@Column(name = "status")
	private Boolean status;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;

	
	
	
	
public GnbStatsModel(String gnbId, String amfName, String plmn, String activeAmfSession, String lifeTimeAmfSession,
			String ip, String tac, String totalGnbSession, String activeGnbSession, String cellGlobalIdentity,
			 Boolean status, String nfType, String nfName) {
		super();
		this.gnbId = gnbId;
		this.amfName = amfName;
		this.plmn = plmn;
		this.activeAmfSession = activeAmfSession;
		this.lifeTimeAmfSession = lifeTimeAmfSession;
		this.ip = ip;
		this.tac = tac;
		this.totalGnbSession = totalGnbSession;
		this.activeGnbSession = activeGnbSession;
		this.cellGlobalIdentity = cellGlobalIdentity;
		this.status = status;
		this.nfType = nfType;
		this.nfName = nfName;
	}
//	public GnbStatsModel(String gnbId, String amfName, String plmn, String activeAmfSession, String lifeTimeAmfSession,
//			String ip, String tac, String totalGnbSession, String activeGnbSession, String cellGlobalIdentity,
//			String agentId, String siteId, String tenentId, Boolean status) {
//		super();
//		this.gnbId = gnbId;
//		this.amfName = amfName;
//		this.plmn = plmn;
//		this.activeAmfSession = activeAmfSession;
//		this.lifeTimeAmfSession = lifeTimeAmfSession;
//		this.ip = ip;
//		this.tac = tac;
//		this.totalGnbSession = totalGnbSession;
//		this.activeGnbSession = activeGnbSession;
//		this.cellGlobalIdentity = cellGlobalIdentity;
//		this.agentId = agentId;
//		this.siteId = siteId;
//		this.tenentId = tenentId;
//		this.status = status;
//	}





//	public GnbStatsModel(String gnbId, String amfName, String plmn, String activeAmfSession, String lifeTimeAmfSession,
//			String ip, String tac, String totalGnbSession, String activeGnbSession, String cellGlobalIdentity,
//			Boolean status) {
//		super();
//		this.gnbId = gnbId;
//		this.amfName = amfName;
//		this.plmn = plmn;
//		this.activeAmfSession = activeAmfSession;
//		this.lifeTimeAmfSession = lifeTimeAmfSession;
//		this.ip = ip;
//		this.tac = tac;
//		this.totalGnbSession = totalGnbSession;
//		this.activeGnbSession = activeGnbSession;
//		this.cellGlobalIdentity = cellGlobalIdentity;
//		this.status = status;
//	}
//	
	
	
	

//	Updation of GnB Stats
//	public GnbStatsModel(String amfName, String plmn, String activeAmfSession, String lifeTimeAmfSession, String ip,
//			String tac, String totalGnbSession, String activeGnbSession, String cellGlobalIdentity, Boolean status) {
//		super();
//		this.amfName = amfName;
//		this.plmn = plmn;
//		this.activeAmfSession = activeAmfSession;
//		this.lifeTimeAmfSession = lifeTimeAmfSession;
//		this.ip = ip;
//		this.tac = tac;
//		this.totalGnbSession = totalGnbSession;
//		this.activeGnbSession = activeGnbSession;
//		this.cellGlobalIdentity = cellGlobalIdentity;
//		this.status = status;
//	}
	
	
	
	
	

	public Long getLogId() {
		return logId;
	}
	public GnbStatsModel(String amfName, String plmn, String activeAmfSession, String lifeTimeAmfSession, String ip,
		String tac, String totalGnbSession, String activeGnbSession, String cellGlobalIdentity,  Boolean status) {
	super();
	this.amfName = amfName;
	this.plmn = plmn;
	this.activeAmfSession = activeAmfSession;
	this.lifeTimeAmfSession = lifeTimeAmfSession;
	this.ip = ip;
	this.tac = tac;
	this.totalGnbSession = totalGnbSession;
	this.activeGnbSession = activeGnbSession;
	this.cellGlobalIdentity = cellGlobalIdentity;
	this.status = status;
}





	public void setLogId(Long logId) {
		this.logId = logId;
	}


	public GnbStatsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGnbId() {
		return gnbId;
	}

	public void setGnbId(String gnbId) {
		this.gnbId = gnbId;
	}

	public String getAmfName() {
		return amfName;
	}

	public void setAmfName(String amfName) {
		this.amfName = amfName;
	}

	public String getPlmn() {
		return plmn;
	}

	public void setPlmn(String plmn) {
		this.plmn = plmn;
	}

	public String getActiveAmfSession() {
		return activeAmfSession;
	}

	public void setActiveAmfSession(String activeAmfSession) {
		this.activeAmfSession = activeAmfSession;
	}


	
	
	

	public String getLifeTimeAmfSession() {
		return lifeTimeAmfSession;
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





	public void setLifeTimeAmfSession(String lifeTimeAmfSession) {
		this.lifeTimeAmfSession = lifeTimeAmfSession;
	}





	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getTotalGnbSession() {
		return totalGnbSession;
	}

	public void setTotalGnbSession(String totalGnbSession) {
		this.totalGnbSession = totalGnbSession;
	}

	public String getActiveGnbSession() {
		return activeGnbSession;
	}

	public void setActiveGnbSession(String activeGnbSession) {
		this.activeGnbSession = activeGnbSession;
	}

	public String getCellGlobalIdentity() {
		return cellGlobalIdentity;
	}

	public void setCellGlobalIdentity(String cellGlobalIdentity) {
		this.cellGlobalIdentity = cellGlobalIdentity;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}

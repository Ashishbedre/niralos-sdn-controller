package com.other.app.NiralosFiveGCore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "live_data")
public class LiveDataModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	Long Id;
	@Column(name = "controller_client_id")
	String controllerClientId;
	@Column(name = "active_amf_session")
	String activeAmfSession;
	@Column(name = "total_amf_session")
	String totalAmfSession;
	@Column(name = "active_amf")
	String activeamf;
	@Column(name = "total_amf")
	String totalamf;
	@Column(name = "active_ue")
	String activeUe;
	@Column(name = "total_ue")
	String totalUe;
	@Column(name = "active_ue_session")
	String activeUeSession;
	@Column(name = "total_ue_session")
	String totalUeSession;
	@Column(name = "active_gnb")
	String activeGnb;
	@Column(name = "total_gnb")
	String totalGnb;
	@Column(name = "active_gnb_session")
	String activeGnbSession;
	@Column(name = "total_gnb_session")
	String totalGnbSession;
	@Column(name = "site_id")
	private String siteId;
	@Column(name = "tenent_id")
	private String tenentId;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;

	
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

	public String getControllerClientId() {
		return controllerClientId;
	}

	public void setControllerClientId(String controllerClientId) {
		this.controllerClientId = controllerClientId;
	}

	public String getActiveAmfSession() {
		return activeAmfSession;
	}

	public void setActiveAmfSession(String activeAmfSession) {
		this.activeAmfSession = activeAmfSession;
	}

	public String getTotalAmfSession() {
		return totalAmfSession;
	}

	public void setTotalAmfSession(String totalAmfSession) {
		this.totalAmfSession = totalAmfSession;
	}

	public String getActiveamf() {
		return activeamf;
	}

	public void setActiveamf(String activeamf) {
		this.activeamf = activeamf;
	}

	public String getTotalamf() {
		return totalamf;
	}

	public void setTotalamf(String totalamf) {
		this.totalamf = totalamf;
	}

	public String getActiveUe() {
		return activeUe;
	}

	public void setActiveUe(String activeUe) {
		this.activeUe = activeUe;
	}

	public String getTotalUe() {
		return totalUe;
	}

	public void setTotalUe(String totalUe) {
		this.totalUe = totalUe;
	}

	public String getActiveUeSession() {
		return activeUeSession;
	}

	public void setActiveUeSession(String activeUeSession) {
		this.activeUeSession = activeUeSession;
	}

	public String getTotalUeSession() {
		return totalUeSession;
	}

	public void setTotalUeSession(String totalUeSession) {
		this.totalUeSession = totalUeSession;
	}

	public String getActiveGnb() {
		return activeGnb;
	}

	public void setActiveGnb(String activeGnb) {
		this.activeGnb = activeGnb;
	}

	public String getTotalGnb() {
		return totalGnb;
	}

	public void setTotalGnb(String totalGnb) {
		this.totalGnb = totalGnb;
	}

	public String getActiveGnbSession() {
		return activeGnbSession;
	}

	public void setActiveGnbSession(String activeGnbSession) {
		this.activeGnbSession = activeGnbSession;
	}

	public String getTotalGnbSession() {
		return totalGnbSession;
	}

	public void setTotalGnbSession(String totalGnbSession) {
		this.totalGnbSession = totalGnbSession;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getTenentId() {
		return tenentId;
	}

	public void setTenentId(String tenentId) {
		this.tenentId = tenentId;
	}



	public LiveDataModel(String controllerClientId, String activeAmfSession, String totalAmfSession, String activeamf,
			String totalamf, String activeUe, String totalUe, String activeUeSession, String totalUeSession,
			String activeGnb, String totalGnb, String activeGnbSession, String totalGnbSession, String siteId,
			String tenentId, String nfType, String nfName) {
		super();
		this.controllerClientId = controllerClientId;
		this.activeAmfSession = activeAmfSession;
		this.totalAmfSession = totalAmfSession;
		this.activeamf = activeamf;
		this.totalamf = totalamf;
		this.activeUe = activeUe;
		this.totalUe = totalUe;
		this.activeUeSession = activeUeSession;
		this.totalUeSession = totalUeSession;
		this.activeGnb = activeGnb;
		this.totalGnb = totalGnb;
		this.activeGnbSession = activeGnbSession;
		this.totalGnbSession = totalGnbSession;
		this.siteId = siteId;
		this.tenentId = tenentId;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	
	

//	public LiveDataModel(String controllerClientId, String activeAmfSession, String totalAmfSession, String activeamf,
//			String totalamf, String activeUe, String totalUe, String activeUeSession, String totalUeSession,
//			String activeGnb, String totalGnb, String activeGnbSession, String totalGnbSession, String siteId,
//			String tenentId) {
//		super();
//		this.controllerClientId = controllerClientId;
//		this.activeAmfSession = activeAmfSession;
//		this.totalAmfSession = totalAmfSession;
//		this.activeamf = activeamf;
//		this.totalamf = totalamf;
//		this.activeUe = activeUe;
//		this.totalUe = totalUe;
//		this.activeUeSession = activeUeSession;
//		this.totalUeSession = totalUeSession;
//		this.activeGnb = activeGnb;
//		this.totalGnb = totalGnb;
//		this.activeGnbSession = activeGnbSession;
//		this.totalGnbSession = totalGnbSession;
//		this.siteId = siteId;
//		this.tenentId = tenentId;
//	}

	public LiveDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}

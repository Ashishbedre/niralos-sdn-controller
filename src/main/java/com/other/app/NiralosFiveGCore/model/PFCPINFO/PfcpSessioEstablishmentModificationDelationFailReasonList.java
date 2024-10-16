package com.other.app.NiralosFiveGCore.model.PFCPINFO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pfcp_FailReason1")
public class PfcpSessioEstablishmentModificationDelationFailReasonList {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String sessionEstablishmentFailReasonList;
	private String sessionModificationFailreasonList;
	private String sessionDeletionFailreasonList;
	private int repeatData;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	
	public Long getId() {
		return id;
	}
	public int getRepeatData() {
		return repeatData;
	}
	public void setRepeatData(int repeatData) {
		this.repeatData = repeatData;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSessionEstablishmentFailReasonList() {
		return sessionEstablishmentFailReasonList;
	}
	public void setSessionEstablishmentFailReasonList(String sessionEstablishmentFailReasonList) {
		this.sessionEstablishmentFailReasonList = sessionEstablishmentFailReasonList;
	}
	public String getSessionModificationFailreasonList() {
		return sessionModificationFailreasonList;
	}
	public void setSessionModificationFailreasonList(String sessionModificationFailreasonList) {
		this.sessionModificationFailreasonList = sessionModificationFailreasonList;
	}
	public String getSessionDeletionFailreasonList() {
		return sessionDeletionFailreasonList;
	}
	public void setSessionDeletionFailreasonList(String sessionDeletionFailreasonList) {
		this.sessionDeletionFailreasonList = sessionDeletionFailreasonList;
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

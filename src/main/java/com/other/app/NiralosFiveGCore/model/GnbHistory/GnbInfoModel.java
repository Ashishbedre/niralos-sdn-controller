package com.other.app.NiralosFiveGCore.model.GnbHistory;

import javax.persistence.Column;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "gnbInfo")

public class GnbInfoModel {
	@Id
	@Field("_id")
	 private ObjectId  id;
	    private String gnbId;
	    private boolean registrationStatus;
	    private String gnbUpTime;
	    private String gnbDownTime;
	    private String tenentId;
		private String agentId;
		private String siteId;
		@Column(name = "nf_type")
		String nfType;
		@Column(name = "nf_name")
		String nfName;
		
		
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
	
		
		
		public String getGnbId() {
			return gnbId;
		}
		public void setGnbId(String gnbId) {
			this.gnbId = gnbId;
		}
		public boolean isRegistrationStatus() {
			return registrationStatus;
		}
		public void setRegistrationStatus(boolean registrationStatus) {
			this.registrationStatus = registrationStatus;
		}
		public String getGnbUpTime() {
			return gnbUpTime;
		}
		public void setGnbUpTime(String gnbUpTime) {
			this.gnbUpTime = gnbUpTime;
		}
		public String getGnbDownTime() {
			return gnbDownTime;
		}
		public void setGnbDownTime(String gnbDownTime) {
			this.gnbDownTime = gnbDownTime;
		}
		public String getTenentId() {
			return tenentId;
		}
		public void setTenentId(String tenentId) {
			this.tenentId = tenentId;
		}
		public String getAgentId() {
			return agentId;
		}
		public void setAgentId(String agentId) {
			this.agentId = agentId;
		}
		public String getSiteId() {
			return siteId;
		}
		public void setSiteId(String siteId) {
			this.siteId = siteId;
		}
		
		
	
		public GnbInfoModel(ObjectId id, String gnbId, boolean registrationStatus, String gnbUpTime, String gnbDownTime,
				String tenentId, String agentId, String siteId, String nfType, String nfName) {
			super();
			this.id = id;
			this.gnbId = gnbId;
			this.registrationStatus = registrationStatus;
			this.gnbUpTime = gnbUpTime;
			this.gnbDownTime = gnbDownTime;
			this.tenentId = tenentId;
			this.agentId = agentId;
			this.siteId = siteId;
			this.nfType = nfType;
			this.nfName = nfName;
		}
		public GnbInfoModel() {
			super();
		}
		
		
	    
}

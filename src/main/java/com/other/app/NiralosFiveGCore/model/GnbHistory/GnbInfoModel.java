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
		
		
		
	
		
	    
}

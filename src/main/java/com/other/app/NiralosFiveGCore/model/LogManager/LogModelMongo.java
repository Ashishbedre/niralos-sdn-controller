package com.other.app.NiralosFiveGCore.model.LogManager;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logModelMongo")
public class LogModelMongo {
		@Id
	    private String logid;
	    private String module;
	    private String logLevel;
	    private String protocol;
	    private String logDescription;
	    private String dateTime;
	    private String agentId;
	    private String siteId;
	    private String tenentId;
	    private String nfType;
	    private String nfName;
	//
//		public Long getId() {
//			return id;
//		}
//		public void setId(Long id) {
//			this.id = id;
//		}
		public String getModule() {
			return module;
		}
		public void setModule(String module) {
			this.module = module;
		}
		public String getLogLevel() {
			return logLevel;
		}
		public void setLogLevel(String logLevel) {
			this.logLevel = logLevel;
		}
		public String getProtocol() {
			return protocol;
		}
		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		public String getLogDescription() {
			return logDescription;
		}
		public void setLogDescription(String logDescription) {
			this.logDescription = logDescription;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
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
		public String getTenentId() {
			return tenentId;
		}
		public void setTenentId(String tenentId) {
			this.tenentId = tenentId;
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
		public LogModelMongo(String module, String logLevel, String protocol, String logDescription, String dateTime,
				String agentId, String siteId, String tenentId, String nfType, String nfName) {
			super();
			this.module = module;
			this.logLevel = logLevel;
			this.protocol = protocol;
			this.logDescription = logDescription;
			this.dateTime = dateTime;
			this.agentId = agentId;
			this.siteId = siteId;
			this.tenentId = tenentId;
			this.nfType = nfType;
			this.nfName = nfName;
		}
		public LogModelMongo() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
	
}

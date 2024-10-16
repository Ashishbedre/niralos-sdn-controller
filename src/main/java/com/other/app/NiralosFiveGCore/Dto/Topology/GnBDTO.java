package com.other.app.NiralosFiveGCore.Dto.Topology;

public class GnBDTO {
	
	 private String name;
	    private int value;
	    private String level;
	    private int status;
	    private String dpName;
	    private String tenentId;
		private String siteId;
		
		public String getTenentId() {
			return tenentId;
		}
		public void setTenentId(String tenentId) {
			this.tenentId = tenentId;
		}
		public String getSiteId() {
			return siteId;
		}
		public void setSiteId(String siteId) {
			this.siteId = siteId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getDpName() {
			return dpName;
		}
		public void setDpName(String dpName) {
			this.dpName = dpName;
		}

	

}

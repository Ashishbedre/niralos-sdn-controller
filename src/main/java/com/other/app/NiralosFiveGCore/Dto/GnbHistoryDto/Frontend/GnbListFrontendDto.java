package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend;

import java.util.ArrayList;
import java.util.List;


public class GnbListFrontendDto {
	 private String gnbId;
	    private boolean registrationStatus;
	    private String gnbUpTime;
	    private String gnbDownTime;
	    private String tenentId;
		private String agentId;
		private String siteId;
		
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
	private List<FailureListFrontendDto> failureList = new ArrayList<FailureListFrontendDto>();
	
	public List<FailureListFrontendDto> getFailureList() {
		return failureList;
	}
	public void setFailureList(List<FailureListFrontendDto> failureList) {
		this.failureList = failureList;
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

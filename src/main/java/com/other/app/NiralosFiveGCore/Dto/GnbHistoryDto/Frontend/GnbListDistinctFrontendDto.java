package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend;

public class GnbListDistinctFrontendDto {
	private String gnbId;
    private boolean registrationStatus;
    private String gnbUpTime;
    private String gnbDownTime;
    private String tenentId;
	private String agentId;
	private String siteId;
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
	
}

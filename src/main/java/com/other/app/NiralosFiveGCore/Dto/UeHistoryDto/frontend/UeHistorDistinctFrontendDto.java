package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend;

public class UeHistorDistinctFrontendDto {
	private String ueStatus;
    private String gnbId;
    private String imsi;
    private String pduStatus;
	public String getUeStatus() {
		return ueStatus;
	}
	public void setUeStatus(String ueStatus) {
		this.ueStatus = ueStatus;
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
	public UeHistorDistinctFrontendDto(String ueStatus, String gnbId, String imsi, String pduStatus) {
		super();
		this.ueStatus = ueStatus;
		this.gnbId = gnbId;
		this.imsi = imsi;
		this.pduStatus = pduStatus;
	}
	
}

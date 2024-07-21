package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend;



import java.util.ArrayList;
import java.util.List;

public class GnbListDTO {
	 private String gnbId;
	    private boolean registrationStatus;
	    private String gnbUpTime;
	    private String gnbDownTime;
    private List<FailureListDTO> failureList = new ArrayList<FailureListDTO>();
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
	public List<FailureListDTO> getFailureList() {
		return failureList;
	}
	public void setFailureList(List<FailureListDTO> failureList) {
		this.failureList = failureList;
	}
    
}

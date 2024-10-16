package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend;

import java.util.ArrayList;
import java.util.List;

public class N3iwfListDTO {
	private String n3iwfId;
	private boolean registrationStatus;
	public String getN3iwfId() {
		return n3iwfId;
	}
	public void setN3iwfId(String n3iwfId) {
		this.n3iwfId = n3iwfId;
	}
	public boolean isRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(boolean registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getN3iwfUpTime() {
		return n3iwfUpTime;
	}
	public void setN3iwfUpTime(String n3iwfUpTime) {
		this.n3iwfUpTime = n3iwfUpTime;
	}
	public boolean isN3iwfDownTime() {
		return n3iwfDownTime;
	}
	public void setN3iwfDownTime(boolean n3iwfDownTime) {
		this.n3iwfDownTime = n3iwfDownTime;
	}
	public List<FailureListDTO> getFailureList() {
		return failureList;
	}
	public void setFailureList(List<FailureListDTO> failureList) {
		this.failureList = failureList;
	}
	private String n3iwfUpTime;
	private boolean n3iwfDownTime;
	private List<FailureListDTO> failureList = new ArrayList<FailureListDTO>();

}

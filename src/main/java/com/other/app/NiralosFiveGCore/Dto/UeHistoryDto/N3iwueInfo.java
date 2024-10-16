package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto;

import java.util.List;

public class N3iwueInfo {
	
	private Integer totalNumberOfRegAttempts;
	private Integer	noOfRegistered;
	private Integer noOfDeregistered;
	private Integer noOfFailure;
	private List<Cause>causeList;
	public Integer getTotalNumberOfRegAttempts() {
		return totalNumberOfRegAttempts;
	}
	public void setTotalNumberOfRegAttempts(Integer totalNumberOfRegAttempts) {
		this.totalNumberOfRegAttempts = totalNumberOfRegAttempts;
	}
	public Integer getNoOfRegistered() {
		return noOfRegistered;
	}
	public void setNoOfRegistered(Integer noOfRegistered) {
		this.noOfRegistered = noOfRegistered;
	}
	public Integer getNoOfDeregistered() {
		return noOfDeregistered;
	}
	public void setNoOfDeregistered(Integer noOfDeregistered) {
		this.noOfDeregistered = noOfDeregistered;
	}
	public Integer getNoOfFailure() {
		return noOfFailure;
	}
	public void setNoOfFailure(Integer noOfFailure) {
		this.noOfFailure = noOfFailure;
	}
	public List<Cause> getCauseList() {
		return causeList;
	}
	public void setCauseList(List<Cause> causeList) {
		this.causeList = causeList;
	}
	


}

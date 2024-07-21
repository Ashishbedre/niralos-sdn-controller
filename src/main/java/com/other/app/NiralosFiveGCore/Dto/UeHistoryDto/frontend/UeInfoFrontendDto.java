package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend;

import java.util.List;


public class UeInfoFrontendDto {
	private int totalNumberOfRegAttempts;
    private int noOfRegistered;
    private int noOfDeregistered;
    private int noOfFailure;
    private List<CauseFrontendDto> causeList;
	public int getTotalNumberOfRegAttempts() {
		return totalNumberOfRegAttempts;
	}
	public void setTotalNumberOfRegAttempts(int totalNumberOfRegAttempts) {
		this.totalNumberOfRegAttempts = totalNumberOfRegAttempts;
	}
	public int getNoOfRegistered() {
		return noOfRegistered;
	}
	public void setNoOfRegistered(int noOfRegistered) {
		this.noOfRegistered = noOfRegistered;
	}
	public int getNoOfDeregistered() {
		return noOfDeregistered;
	}
	public void setNoOfDeregistered(int noOfDeregistered) {
		this.noOfDeregistered = noOfDeregistered;
	}
	public int getNoOfFailure() {
		return noOfFailure;
	}
	public void setNoOfFailure(int noOfFailure) {
		this.noOfFailure = noOfFailure;
	}
	public List<CauseFrontendDto> getCauseList() {
		return causeList;
	}
	public void setCauseList(List<CauseFrontendDto> causeList) {
		this.causeList = causeList;
	}
	
    
}

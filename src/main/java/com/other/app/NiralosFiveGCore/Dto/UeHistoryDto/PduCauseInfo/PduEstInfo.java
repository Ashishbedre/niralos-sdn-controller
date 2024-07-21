package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.Cause;

public class PduEstInfo {
	private int noOfpduEstReq;
    private int noOfpduEstSuccess;
    private int noOfpduEstFailure;
    private List<Cause> pduCauseList;
	public int getNoOfpduEstReq() {
		return noOfpduEstReq;
	}
	public void setNoOfpduEstReq(int noOfpduEstReq) {
		this.noOfpduEstReq = noOfpduEstReq;
	}
	public int getNoOfpduEstSuccess() {
		return noOfpduEstSuccess;
	}
	public void setNoOfpduEstSuccess(int noOfpduEstSuccess) {
		this.noOfpduEstSuccess = noOfpduEstSuccess;
	}
	public int getNoOfpduEstFailure() {
		return noOfpduEstFailure;
	}
	public void setNoOfpduEstFailure(int noOfpduEstFailure) {
		this.noOfpduEstFailure = noOfpduEstFailure;
	}
	public List<Cause> getPduCauseList() {
		return pduCauseList;
	}
	public void setPduCauseList(List<Cause> pduCauseList) {
		this.pduCauseList = pduCauseList;
	}
    
}

package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto;

import java.util.List;

public class N3iwuepduEstInfo {
	 private Integer noOfpduEstReq;
	 private Integer noOfpduEstSuccess;
	 private Integer noOfpduEstFailure;
	 private List<Cause>n3iwuepduCauseList;
	 
	public Integer getNoOfpduEstReq() {
		return noOfpduEstReq;
	}
	public void setNoOfpduEstReq(Integer noOfpduEstReq) {
		this.noOfpduEstReq = noOfpduEstReq;
	}
	public Integer getNoOfpduEstSuccess() {
		return noOfpduEstSuccess;
	}
	public void setNoOfpduEstSuccess(Integer noOfpduEstSuccess) {
		this.noOfpduEstSuccess = noOfpduEstSuccess;
	}
	public Integer getNoOfpduEstFailure() {
		return noOfpduEstFailure;
	}
	public void setNoOfpduEstFailure(Integer noOfpduEstFailure) {
		this.noOfpduEstFailure = noOfpduEstFailure;
	}
	public List<Cause> getN3iwuepduCauseList() {
		return n3iwuepduCauseList;
	}
	public void setN3iwuepduCauseList(List<Cause> n3iwuepduCauseList) {
		this.n3iwuepduCauseList = n3iwuepduCauseList;
	}
	

}

package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend;

import java.util.ArrayList;
import java.util.List;

public class N3iwfInfoListDTO {
	private  String totalNumberOfAttempts;
	private String totalnoOfRegistered;
	private String noOfSCTP_Deregistered;
	private String noOfDeregistered;
	private String totalNoOfFailure;
	private List<N3iwfListDTO>n3iwfList= new ArrayList<N3iwfListDTO>();
	
	public String getTotalNumberOfAttempts() {
		return totalNumberOfAttempts;
	}
	public void setTotalNumberOfAttempts(String totalNumberOfAttempts) {
		this.totalNumberOfAttempts = totalNumberOfAttempts;
	}
	public String getTotalnoOfRegistered() {
		return totalnoOfRegistered;
	}
	public void setTotalnoOfRegistered(String totalnoOfRegistered) {
		this.totalnoOfRegistered = totalnoOfRegistered;
	}
	public String getNoOfSCTP_Deregistered() {
		return noOfSCTP_Deregistered;
	}
	public void setNoOfSCTP_Deregistered(String noOfSCTP_Deregistered) {
		this.noOfSCTP_Deregistered = noOfSCTP_Deregistered;
	}
	public String getNoOfDeregistered() {
		return noOfDeregistered;
	}
	public void setNoOfDeregistered(String noOfDeregistered) {
		this.noOfDeregistered = noOfDeregistered;
	}
	public String getTotalNoOfFailure() {
		return totalNoOfFailure;
	}
	public void setTotalNoOfFailure(String totalNoOfFailure) {
		this.totalNoOfFailure = totalNoOfFailure;
	}
	public List<N3iwfListDTO> getN3iwfList() {
		return n3iwfList;
	}
	public void setN3iwfList(List<N3iwfListDTO> n3iwfList) {
		this.n3iwfList = n3iwfList;
	}
	

}

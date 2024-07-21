package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend;



import java.util.ArrayList;
import java.util.List;

public class GnbInfoDTO {
	private String totalNumberOfAttempts;
    private String totalnoOfRegistered;
    private String noOfSCTP_Deregistered;
    private String noOfDeregistered;
    private String totalNoOfFailure;
    
    private List<GnbListDTO> gnbList = new ArrayList<GnbListDTO>();
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
	public List<GnbListDTO> getGnbList() {
		return gnbList;
	}
	public void setGnbList(List<GnbListDTO> gnbList) {
		this.gnbList = gnbList;
	}
	
	public GnbInfoDTO(String totalNumberOfAttempts, String totalnoOfRegistered, String noOfSCTP_Deregistered,
			String noOfDeregistered, String totalNoOfFailure, List<GnbListDTO> gnbList) {
		super();
		this.totalNumberOfAttempts = totalNumberOfAttempts;
		this.totalnoOfRegistered = totalnoOfRegistered;
		this.noOfSCTP_Deregistered = noOfSCTP_Deregistered;
		this.noOfDeregistered = noOfDeregistered;
		this.totalNoOfFailure = totalNoOfFailure;
		this.gnbList = gnbList;
	}
	public GnbInfoDTO() {
		super();
	}
	
    
}

package com.other.app.NiralosFiveGCore.model.GnbHistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="gnb_reg_dereg_failure_count")
public class GnbRegistrationDeregistrationFailureCount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String totalNumberOfAttempts;
    private String totalnoOfRegistered;
    private String noOfSCTP_Deregistered;
    private String noOfDeregistered;
    private String totalNoOfFailure;
	
	@Column(name = "nf_type")
	String nfType;
	@Column(name = "nf_name")
	String nfName;
	
	
	
	public String getNfType() {
		return nfType;
	}
	public void setNfType(String nfType) {
		this.nfType = nfType;
	}
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	
	
}

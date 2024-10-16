package com.other.app.NiralosFiveGCore.model.UeHisotry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ue_reg_dereg_failure_count")
public class UeRegistraionDeregistrationFailureCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	    private int totalNumberOfRegAttempts;
	    private int noOfRegistered;
	    private int noOfDeregistered;
	    private int noOfFailure;
	    private int noOfpduEstReq;
	    private int noOfpduEstSuccess;
	    private int noOfpduEstFailure;
	    private int noOfpduReleaseReq;
	    private int noOfpduReleaseSuccess;
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
		public int getNoOfpduReleaseReq() {
			return noOfpduReleaseReq;
		}
		public void setNoOfpduReleaseReq(int noOfpduReleaseReq) {
			this.noOfpduReleaseReq = noOfpduReleaseReq;
		}
		public int getNoOfpduReleaseSuccess() {
			return noOfpduReleaseSuccess;
		}
		public void setNoOfpduReleaseSuccess(int noOfpduReleaseSuccess) {
			this.noOfpduReleaseSuccess = noOfpduReleaseSuccess;
		}
		
}

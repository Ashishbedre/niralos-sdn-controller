package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto;
import java.util.List;

public class UeInfo {
	 private int totalNumberOfRegAttempts;
	    private int noOfRegistered;
	    private int noOfDeregistered;
	    private int noOfFailure;
	    private List<Cause> causeList;
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
		public List<Cause> getCauseList() {
			return causeList;
		}
		public void setCauseList(List<Cause> causeList) {
			this.causeList = causeList;
		}
	    
}

package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend;

import java.util.List;


public class PduEstInfoFrontendDto {
	 private int noOfpduEstReq;
	    private int noOfpduEstSuccess;
	    private int noOfpduEstFailure;
	    private List<CauseFrontendDto> pduCauseList;
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
		public List<CauseFrontendDto> getPduCauseList() {
			return pduCauseList;
		}
		public void setPduCauseList(List<CauseFrontendDto> pduCauseList) {
			this.pduCauseList = pduCauseList;
		}
	    
}

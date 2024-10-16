package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend;

public class FailureListFrontendDto {
	private String failureReason;
    private String failureCount;
	    private String gnbId;


		
		public String getGnbId() {
			return gnbId;
		}
		public void setGnbId(String gnbId) {
			this.gnbId = gnbId;
		}

	public String getFailureReason() {
        return failureReason;
    }
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    public String getFailureCount() {
        return failureCount;
    }
    public void setFailureCount(String failureCount) {
        this.failureCount = failureCount;
    }
}

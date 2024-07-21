package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend;

public class FailureListFrontendDto {
	private String failureReason;
    private String failureCount;
    private String tenentId;
  		private String siteId;
	    private String gnbId;

    public String getTenentId() {
			return tenentId;
		}
		public void setTenentId(String tenentId) {
			this.tenentId = tenentId;
		}
		
		public String getGnbId() {
			return gnbId;
		}
		public void setGnbId(String gnbId) {
			this.gnbId = gnbId;
		}
		public String getSiteId() {
			return siteId;
		}
		public void setSiteId(String siteId) {
			this.siteId = siteId;
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

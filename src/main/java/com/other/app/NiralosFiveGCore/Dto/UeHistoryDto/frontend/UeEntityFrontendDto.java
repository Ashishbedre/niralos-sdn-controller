package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend;

import java.util.List;




public class UeEntityFrontendDto {
	 private List<UeInfoFrontendDto> ueInfo;
	    private List<PduEstInfoFrontendDto> pduEstInfo;
	    private List<PduReleaseInfoFrontendDto> pduReleaseInfo;
		public List<UeInfoFrontendDto> getUeInfo() {
			return ueInfo;
		}
		public void setUeInfo(List<UeInfoFrontendDto> ueInfo) {
			this.ueInfo = ueInfo;
		}
		public List<PduEstInfoFrontendDto> getPduEstInfo() {
			return pduEstInfo;
		}
		public void setPduEstInfo(List<PduEstInfoFrontendDto> pduEstInfo) {
			this.pduEstInfo = pduEstInfo;
		}
		public List<PduReleaseInfoFrontendDto> getPduReleaseInfo() {
			return pduReleaseInfo;
		}
		public void setPduReleaseInfo(List<PduReleaseInfoFrontendDto> pduReleaseInfo) {
			this.pduReleaseInfo = pduReleaseInfo;
		}
		
	    
}

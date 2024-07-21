package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduEstInfo;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduReleaseInfo;

public class UeInfoDTO {
	private List<UeInfo> ueInfo;
    private List<PduEstInfo> pduEstInfo;
    private List<PduReleaseInfo> pduReleaseInfo;
   


	public List<UeInfo> getUeInfo() {
		return ueInfo;
	}
	public void setUeInfo(List<UeInfo> ueInfo) {
		this.ueInfo = ueInfo;
	}
	public List<PduEstInfo> getPduEstInfo() {
		return pduEstInfo;
	}
	public void setPduEstInfo(List<PduEstInfo> pduEstInfo) {
		this.pduEstInfo = pduEstInfo;
	}
	public List<PduReleaseInfo> getPduReleaseInfo() {
		return pduReleaseInfo;
	}
	public void setPduReleaseInfo(List<PduReleaseInfo> pduReleaseInfo) {
		this.pduReleaseInfo = pduReleaseInfo;
	}
    

}

package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduEstInfo;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduReleaseInfo;

public class UeInfoDTO {
	
	private List<UeInfo> ueInfo;
    private List<PduEstInfo> pduEstInfo;
    private List<PduReleaseInfo> pduReleaseInfo;
    private List<N3iwueInfo>n3iwueInfo;
    private List<N3iwuepduEstInfo>n3iwuepduEstInfo;
    private List<N3iwuepduReleaseInfo>n3iwuepduReleaseInfo;
    
    public List<N3iwueInfo> getN3iwueInfo() {
		return n3iwueInfo;
	}
	public void setN3iwueInfo(List<N3iwueInfo> n3iwueInfo) {
		this.n3iwueInfo = n3iwueInfo;
	}
	public List<N3iwuepduEstInfo> getN3iwuepduEstInfo() {
		return n3iwuepduEstInfo;
	}
	public void setN3iwuepduEstInfo(List<N3iwuepduEstInfo> n3iwuepduEstInfo) {
		this.n3iwuepduEstInfo = n3iwuepduEstInfo;
	}
	public List<N3iwuepduReleaseInfo> getN3iwuepduReleaseInfo() {
		return n3iwuepduReleaseInfo;
	}
	public void setN3iwuepduReleaseInfo(List<N3iwuepduReleaseInfo> n3iwuepduReleaseInfo) {
		this.n3iwuepduReleaseInfo = n3iwuepduReleaseInfo;
	}
	
   


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

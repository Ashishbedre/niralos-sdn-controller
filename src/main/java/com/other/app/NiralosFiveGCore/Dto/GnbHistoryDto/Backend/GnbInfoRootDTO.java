package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend;



import java.util.ArrayList;
import java.util.List;

public class GnbInfoRootDTO {
    private List<GnbInfoDTO> gnbInfo = new ArrayList<GnbInfoDTO>();

	public List<GnbInfoDTO> getGnbInfo() {
		return gnbInfo;
	}

	public void setGnbInfo(List<GnbInfoDTO> gnbInfo) {
		this.gnbInfo = gnbInfo;
	}
    

}

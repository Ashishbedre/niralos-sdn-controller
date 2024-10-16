package com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Backend;



import java.util.ArrayList;
import java.util.List;

public class GnbInfoRootDTO {
    private List<GnbInfoDTO> gnbInfo = new ArrayList<GnbInfoDTO>();
    private List<N3iwfInfoListDTO> n3iwfInfo = new ArrayList<N3iwfInfoListDTO>();
    

	public List<N3iwfInfoListDTO> getN3iwfInfo() {
		return n3iwfInfo;
	}

	public void setN3iwfInfo(List<N3iwfInfoListDTO> n3iwfInfo) {
		this.n3iwfInfo = n3iwfInfo;
	}

	public List<GnbInfoDTO> getGnbInfo() {
		return gnbInfo;
	}

	public void setGnbInfo(List<GnbInfoDTO> gnbInfo) {
		this.gnbInfo = gnbInfo;
	}
    

}

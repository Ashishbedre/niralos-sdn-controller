package com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto;

import java.util.ArrayList;


public class GnbListDto {

	  private String gnbId;
	    private String ip;
	    private String tac;
	    private String totalGnbSession;
	    private ArrayList<UeListDto> ueList = new ArrayList<UeListDto>();
	    private String activeGnbSession;
	    
		public String getGnbId() {
			return gnbId;
		}
		public void setGnbId(String gnbId) {
			this.gnbId = gnbId;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getTac() {
			return tac;
		}
		public void setTac(String tac) {
			this.tac = tac;
		}
		public String getTotalGnbSession() {
			return totalGnbSession;
		}
		public void setTotalGnbSession(String totalGnbSession) {
			this.totalGnbSession = totalGnbSession;
		}
		public ArrayList<UeListDto> getUeList() {
			return ueList;
		}
		public void setUeList(ArrayList<UeListDto> ueList) {
			this.ueList = ueList;
		}
		public String getActiveGnbSession() {
			return activeGnbSession;
		}
		public void setActiveGnbSession(String activeGnbSession) {
			this.activeGnbSession = activeGnbSession;
		}
	    
	    

	    
	    
	
}

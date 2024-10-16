package com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto;

import java.util.ArrayList;


public class GnbUeListDto {

	private String amfName;
    private String plmn;
    private String lifeTimeAmfSession;
    private ArrayList<GnbListDto> gnbList; 
    private String activeAmfSession;
	private ArrayList<n3iwfListDto>n3iwfList;

	public ArrayList<n3iwfListDto> getN3iwfList() {
		return n3iwfList;
	}

	public void setN3iwfList(ArrayList<n3iwfListDto> n3iwfList) {
		this.n3iwfList = n3iwfList;
	}

	public String getAmfName() {
		return amfName;
	}
	public void setAmfName(String amfName) {
		this.amfName = amfName;
	}
	public String getPlmn() {
		return plmn;
	}
	public void setPlmn(String plmn) {
		this.plmn = plmn;
	}
	public String getLifeTimeAmfSession() {
		return lifeTimeAmfSession;
	}
	public void setLifeTimeAmfSession(String lifeTimeAmfSession) {
		this.lifeTimeAmfSession = lifeTimeAmfSession;
	}
	public ArrayList<GnbListDto> getGnbList() {
		return gnbList;
	}
	public void setGnbList(ArrayList<GnbListDto> gnbList) {
		this.gnbList = gnbList;
	}
	public String getActiveAmfSession() {
		return activeAmfSession;
	}
	public void setActiveAmfSession(String activeAmfSession) {
		this.activeAmfSession = activeAmfSession;
	}
    
    
    
	
    
}

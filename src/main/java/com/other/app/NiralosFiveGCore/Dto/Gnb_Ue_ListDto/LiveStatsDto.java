package com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto;

public class LiveStatsDto {
	private String gnbCount;
	private String ueCount;
	private String n3iwfCount;
	private String n3iwueCount;

	public String getN3iwfCount() {
		return n3iwfCount;
	}

	public void setN3iwfCount(String n3iwfCount) {
		this.n3iwfCount = n3iwfCount;
	}

	public String getN3iwueCount() {
		return n3iwueCount;
	}

	public void setN3iwueCount(String n3iwueCount) {
		this.n3iwueCount = n3iwueCount;
	}

	public String getGnbCount() {
		return gnbCount;
	}
	public void setGnbCount(String gnbCount) {
		this.gnbCount = gnbCount;
	}
	public String getUeCount() {
		return ueCount;
	}
	public void setUeCount(String ueCount) {
		this.ueCount = ueCount;
	}

}

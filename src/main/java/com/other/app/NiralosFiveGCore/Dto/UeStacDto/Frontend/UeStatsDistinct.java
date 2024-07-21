package com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend;

public class UeStatsDistinct {
	
	String imsi;
	String totalUplink;
	String totalDownlink;
	String numberOfSessions;

	public UeStatsDistinct(String imsi, String totalUplink, String totalDownlink, String numberOfSessions) {
		super();
		this.imsi = imsi;
		this.totalUplink = totalUplink;
		this.totalDownlink = totalDownlink;
		this.numberOfSessions = numberOfSessions;
	}

	public UeStatsDistinct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getTotalUplink() {
		return totalUplink;
	}

	public void setTotalUplink(String totalUplink) {
		this.totalUplink = totalUplink;
	}

	public String getTotalDownlink() {
		return totalDownlink;
	}

	public void setTotalDownlink(String totalDownlink) {
		this.totalDownlink = totalDownlink;
	}

	public String getNumberOfSessions() {
		return numberOfSessions;
	}

	public void setNumberOfSessions(String numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}


}

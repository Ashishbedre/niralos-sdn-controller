package com.other.app.NiralosFiveGCore.model.Graph;

import java.time.LocalDateTime;

public class CombineGraphData {

	
	private String activeamf;
	private String activeGnb;
	private String activeUe;
	private String activeUeSession;
	private LocalDateTime localDateTime;
	public String getActiveamf() {
		return activeamf;
	}
	public void setActiveamf(String activeamf) {
		this.activeamf = activeamf;
	}
	public String getActiveUe() {
		return activeUe;
	}
	public void setActiveUe(String activeUe) {
		this.activeUe = activeUe;
	}
	public String getActiveUeSession() {
		return activeUeSession;
	}
	public void setActiveUeSession(String activeUeSession) {
		this.activeUeSession = activeUeSession;
	}
	public String getActiveGnb() {
		return activeGnb;
	}
	public void setActiveGnb(String activeGnb) {
		this.activeGnb = activeGnb;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public CombineGraphData(String activeamf, String activeGnb, String activeUe, String activeUeSession,
			LocalDateTime localDateTime) {
		super();
		this.activeamf = activeamf;
		this.activeGnb = activeGnb;
		this.activeUe = activeUe;
		this.activeUeSession = activeUeSession;
		this.localDateTime = localDateTime;
	}
	public CombineGraphData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}

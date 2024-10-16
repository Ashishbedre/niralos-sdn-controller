package com.other.app.niralos_edge.dto;

public class ProxmoxToken {

	public String ticket;
	public String CSRFPreventionToken;
	public ProxmoxToken(String ticket, String cSRFPreventionToken) {
		super();
		this.ticket = ticket;
		CSRFPreventionToken = cSRFPreventionToken;
	}
	public ProxmoxToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getCSRFPreventionToken() {
		return CSRFPreventionToken;
	}
	public void setCSRFPreventionToken(String cSRFPreventionToken) {
		CSRFPreventionToken = cSRFPreventionToken;
	}
	
	
	
	

}

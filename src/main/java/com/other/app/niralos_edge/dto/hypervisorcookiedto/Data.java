package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	
	@JsonProperty("CSRFPreventionToken")
	private String csrftoken;
	@JsonProperty("ticket")
	private String ticket;
	@JsonProperty("username")
	private String username;
	@JsonProperty("cap")
	private Cap cap;
	
	public String getCsrftoken() {
		return csrftoken;
	}
	public void setCsrftoken(String csrftoken) {
		this.csrftoken = csrftoken;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Cap getCap() {
		return cap;
	}
	public void setCap(Cap cap) {
		this.cap = cap;
	}

	
    
	 
	 

}

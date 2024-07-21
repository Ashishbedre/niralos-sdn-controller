package com.other.app.NiralosFiveGCore.Dto.Subscriber;

import java.util.ArrayList;

public class Slice {

	Integer sst;
	String sd;
	Boolean default_indicator;
	ArrayList<Session> session;

	public Integer getSst() {
		return sst;
	}

	public void setSst(Integer sst) {
		this.sst = sst;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public Boolean getDefault_indicator() {
		return default_indicator;
	}

	public void setDefault_indicator(Boolean default_indicator) {
		this.default_indicator = default_indicator;
	}

	public ArrayList<Session> getSession() {
		return session;
	}

	public void setSession(ArrayList<Session> session) {
		this.session = session;
	}

}

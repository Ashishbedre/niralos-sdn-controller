package com.other.app.NiralosFiveGCore.Dto;

import java.util.List;

public class GnbAndUeLivenessDTO {

	String gnbId;
	String amfName;
	List<String> imsiList;

	public String getGnbId() {
		return gnbId;
	}

	public void setGnbId(String gnbId) {
		this.gnbId = gnbId;
	}

	public String getAmfName() {
		return amfName;
	}

	public void setAmfName(String amfName) {
		this.amfName = amfName;
	}

	public List<String> getImsiList() {
		return imsiList;
	}

	public void setImsiList(List<String> imsiList) {
		this.imsiList = imsiList;
	}

	public GnbAndUeLivenessDTO(String gnbId, String amfName, List<String> imsiList) {
		super();
		this.gnbId = gnbId;
		this.amfName = amfName;
		this.imsiList = imsiList;
	}

	public GnbAndUeLivenessDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}

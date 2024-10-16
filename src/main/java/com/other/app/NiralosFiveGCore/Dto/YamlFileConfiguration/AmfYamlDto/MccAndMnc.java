package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class MccAndMnc {

	public String  mcc;
	public String  mnc;
	public MccAndMnc(String mcc, String mnc) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}
	public MccAndMnc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	@Override
	public String toString() {
		return "MccAndMnc [mcc=" + mcc + ", mnc=" + mnc + "]";
	}
		
}

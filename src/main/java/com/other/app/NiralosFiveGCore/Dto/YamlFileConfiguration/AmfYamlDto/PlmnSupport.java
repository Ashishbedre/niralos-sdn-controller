package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

import java.util.List;

public class PlmnSupport {

	public MccAndMnc plmn_id;
	public List<Sst> s_nssai;
	public PlmnSupport(MccAndMnc plmn_id, List<Sst> s_nssai) {
		super();
		this.plmn_id = plmn_id;
		this.s_nssai = s_nssai;
	}
	public PlmnSupport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MccAndMnc getPlmn_id() {
		return plmn_id;
	}
	public void setPlmn_id(MccAndMnc plmn_id) {
		this.plmn_id = plmn_id;
	}
	public List<Sst> getS_nssai() {
		return s_nssai;
	}
	public void setS_nssai(List<Sst> s_nssai) {
		this.s_nssai = s_nssai;
	}
	@Override
	public String toString() {
		return "PlmnSupport [plmn_id=" + plmn_id + ", s_nssai=" + s_nssai + "]";
	}
	
	
	
	
}

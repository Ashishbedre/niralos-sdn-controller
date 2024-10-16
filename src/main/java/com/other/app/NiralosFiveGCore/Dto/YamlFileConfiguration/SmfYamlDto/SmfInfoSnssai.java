package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto;

import java.util.List;

public class SmfInfoSnssai {

	public List<SmfInfoSliceConfiguration> s_nssai;

	public SmfInfoSnssai(List<SmfInfoSliceConfiguration> s_nssai) {
		super();
		this.s_nssai = s_nssai;
	}

	public SmfInfoSnssai() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<SmfInfoSliceConfiguration> getS_nssai() {
		return s_nssai;
	}

	public void setS_nssai(List<SmfInfoSliceConfiguration> s_nssai) {
		this.s_nssai = s_nssai;
	}

	@Override
	public String toString() {
		return "SmfInfoSnssai [s_nssai=" + s_nssai + "]";
	}
	

}

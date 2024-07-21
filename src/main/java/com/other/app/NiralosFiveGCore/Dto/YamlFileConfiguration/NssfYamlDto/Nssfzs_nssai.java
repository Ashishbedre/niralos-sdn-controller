package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto;

public class Nssfzs_nssai {

	public Integer sst;
	public String sd;
	public Integer dynamic;
	public Nssfzs_nssai(Integer sst, String sd, Integer dynamic) {
		super();
		this.sst = sst;
		this.sd = sd;
		this.dynamic = dynamic;
	}
	public Nssfzs_nssai() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public Integer getDynamic() {
		return dynamic;
	}
	public void setDynamic(Integer dynamic) {
		this.dynamic = dynamic;
	}
	@Override
	public String toString() {
		return "Nssfzs_nssai [sst=" + sst + ", sd=" + sd + ", dynamic=" + dynamic + "]";
	}
	
	

	

}


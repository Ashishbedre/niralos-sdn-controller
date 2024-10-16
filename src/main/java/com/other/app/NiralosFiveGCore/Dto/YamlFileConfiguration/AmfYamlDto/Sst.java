package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class Sst {

	public Integer sst;
	public String sd;
	public Integer dynamic;
	public Sst(Integer sst, String sd, Integer dynamic) {
		super();
		this.sst = sst;
		this.sd = sd;
		this.dynamic = dynamic;
	}
	public Sst() {
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
		return "Sst [sst=" + sst + ", sd=" + sd + ", dynamic=" + dynamic + "]";
	}
	
	

	
	

}

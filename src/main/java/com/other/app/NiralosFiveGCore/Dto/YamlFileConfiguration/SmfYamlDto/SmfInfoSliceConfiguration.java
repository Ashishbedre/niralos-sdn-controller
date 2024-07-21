package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto;

import java.util.List;

public class SmfInfoSliceConfiguration {

	public Integer sst;
	public String sd;
	public List<String> dnn;
	public Integer dynamic;
	public SmfInfoSliceConfiguration(Integer sst, String sd, List<String> dnn, Integer dynamic) {
		super();
		this.sst = sst;
		this.sd = sd;
		this.dnn = dnn;
		this.dynamic = dynamic;
	}
	public SmfInfoSliceConfiguration() {
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
	public List<String> getDnn() {
		return dnn;
	}
	public void setDnn(List<String> dnn) {
		this.dnn = dnn;
	}
	public Integer getDynamic() {
		return dynamic;
	}
	public void setDynamic(Integer dynamic) {
		this.dynamic = dynamic;
	}
	@Override
	public String toString() {
		return "SmfInfoSliceConfiguration [sst=" + sst + ", sd=" + sd + ", dnn=" + dnn + ", dynamic=" + dynamic + "]";
	}
	
	

}

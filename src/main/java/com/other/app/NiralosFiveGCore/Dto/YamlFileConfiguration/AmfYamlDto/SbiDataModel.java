package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

import java.util.List;

public class SbiDataModel {

	public List<Sbi> sbi;

	public SbiDataModel(List<Sbi> sbi) {
		super();
		this.sbi = sbi;
	}

	public SbiDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Sbi> getSbi() {
		return sbi;
	}

	public void setSbi(List<Sbi> sbi) {
		this.sbi = sbi;
	}

	@Override
	public String toString() {
		return "SbiDataModel [sbi=" + sbi + "]";
	}

	
	
	
	

}

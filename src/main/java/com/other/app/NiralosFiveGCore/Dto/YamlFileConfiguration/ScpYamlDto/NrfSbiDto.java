package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto;

import java.util.List;

public class NrfSbiDto {


	public List<SbiNrfDto> sbi;

	public NrfSbiDto(List<SbiNrfDto> sbi) {
		super();
		this.sbi = sbi;
	}

	public NrfSbiDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<SbiNrfDto> getSbi() {
		return sbi;
	}

	public void setSbi(List<SbiNrfDto> sbi) {
		this.sbi = sbi;
	}

	@Override
	public String toString() {
		return "NrfSbiDto [sbi=" + sbi + "]";
	}
	
	
	
	
	

}

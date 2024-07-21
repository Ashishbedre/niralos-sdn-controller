package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto;

import java.util.List;

public class UpfPfcpDto {

	public List<SbiNrfDto>pfcp;

	public UpfPfcpDto(List<SbiNrfDto> pfcp) {
		super();
		this.pfcp = pfcp;
	}

	public UpfPfcpDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<SbiNrfDto> getPfcp() {
		return pfcp;
	}

	public void setPfcp(List<SbiNrfDto> pfcp) {
		this.pfcp = pfcp;
	}

	@Override
	public String toString() {
		return "UpfPfcpDto [pfcp=" + pfcp + "]";
	}
	
	

}

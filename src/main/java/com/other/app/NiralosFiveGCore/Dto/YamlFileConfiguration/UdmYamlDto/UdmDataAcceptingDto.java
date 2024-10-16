package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdmYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;

public class UdmDataAcceptingDto {
	public SbiData sbi;
	public UdmHnetIdKey udm;
	public NrfSbiDto scp;
	public String parameter;
	public String max;
	public String time;
	public UdmDataAcceptingDto(SbiData sbi, UdmHnetIdKey udm, NrfSbiDto scp, String parameter, String max,
			String time) {
		super();
		this.sbi = sbi;
		this.udm = udm;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public UdmDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public UdmHnetIdKey getUdm() {
		return udm;
	}
	public void setUdm(UdmHnetIdKey udm) {
		this.udm = udm;
	}
	public NrfSbiDto getScp() {
		return scp;
	}
	public void setScp(NrfSbiDto scp) {
		this.scp = scp;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "UdmDataAcceptingDto [sbi=" + sbi + ", udm=" + udm + ", scp=" + scp + ", parameter=" + parameter
				+ ", max=" + max + ", time=" + time + "]";
	}
	
	
}

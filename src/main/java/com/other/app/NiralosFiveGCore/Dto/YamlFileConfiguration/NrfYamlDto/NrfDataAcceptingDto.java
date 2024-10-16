package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NrfYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;

public class NrfDataAcceptingDto {

	private SbiData sbi;
	private SbiDataNrfDto nrf;
	private NrfSbiDto scp;
	private String parameter;
	private String max;
	private String time;
	public NrfDataAcceptingDto(SbiData sbi, SbiDataNrfDto nrf, NrfSbiDto scp, String parameter, String max,
			String time) {
		super();
		this.sbi = sbi;
		this.nrf = nrf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public NrfDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public SbiDataNrfDto getNrf() {
		return nrf;
	}
	public void setNrf(SbiDataNrfDto nrf) {
		this.nrf = nrf;
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
		return "NrfDataAcceptingDto [sbi=" + sbi + ", nrf=" + nrf + ", scp=" + scp + ", parameter=" + parameter
				+ ", max=" + max + ", time=" + time + "]";
	}
	
	

}

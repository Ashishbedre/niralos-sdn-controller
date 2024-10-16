package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;

public class NssfDataAcceptingDto {
	
	private SbiData sbi;
	private NssfSbiDataDto nssf;
	private NrfSbiDto scp;
	private String parameter;
	private String max;
	private String time;
	public NssfDataAcceptingDto(SbiData sbi, NssfSbiDataDto nssf, NrfSbiDto scp, String parameter, String max,
			String time) {
		super();
		this.sbi = sbi;
		this.nssf = nssf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public NssfDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public NssfSbiDataDto getNssf() {
		return nssf;
	}
	public void setNssf(NssfSbiDataDto nssf) {
		this.nssf = nssf;
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
		return "NssfDataAcceptingDto [sbi=" + sbi + ", nssf=" + nssf + ", scp=" + scp + ", parameter=" + parameter
				+ ", max=" + max + ", time=" + time + "]";
	}
	

	
}

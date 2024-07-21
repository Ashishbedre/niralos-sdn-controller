package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AusfYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.t3512;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;

public class AusfDataAcceptingDto {

	private SbiData sbi;
	private AusfDataScpDto ausf;
	private NrfSbiDto scp;
	private String parameter;
	private String max;
	private t3512 time;
	public AusfDataAcceptingDto(SbiData sbi, AusfDataScpDto ausf, NrfSbiDto scp, String parameter, String max,
			t3512 time) {
		super();
		this.sbi = sbi;
		this.ausf = ausf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public AusfDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public AusfDataScpDto getAusf() {
		return ausf;
	}
	public void setAusf(AusfDataScpDto ausf) {
		this.ausf = ausf;
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
	public t3512 getTime() {
		return time;
	}
	public void setTime(t3512 time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "AusfDataAcceptingDto [sbi=" + sbi + ", ausf=" + ausf + ", scp=" + scp + ", parameter=" + parameter
				+ ", max=" + max + ", time=" + time + "]";
	}
	
	
	
	

}

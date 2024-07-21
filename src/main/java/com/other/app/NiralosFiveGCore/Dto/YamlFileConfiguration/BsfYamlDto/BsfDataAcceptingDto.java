package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.BsfYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.t3512;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AusfYamlDto.AusfDataScpDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;

public class BsfDataAcceptingDto {

	private SbiData sbi;
	private AusfDataScpDto bsf;
	private NrfSbiDto scp;
	private String parameter;
	private String max;
	private t3512 time;
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public AusfDataScpDto getBsf() {
		return bsf;
	}
	public void setBsf(AusfDataScpDto bsf) {
		this.bsf = bsf;
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
	public BsfDataAcceptingDto(SbiData sbi, AusfDataScpDto bsf, NrfSbiDto scp, String parameter, String max,
			t3512 time) {
		super();
		this.sbi = sbi;
		this.bsf = bsf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public BsfDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BsfDataAcceptingDto [sbi=" + sbi + ", bsf=" + bsf + ", scp=" + scp + ", parameter=" + parameter
				+ ", max=" + max + ", time=" + time + "]";
	}
	
	

}

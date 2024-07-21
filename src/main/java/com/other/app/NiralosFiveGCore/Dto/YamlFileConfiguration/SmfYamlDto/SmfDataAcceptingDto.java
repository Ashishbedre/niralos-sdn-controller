package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.file_stat;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.UpfPfcpDto;

public class SmfDataAcceptingDto {

	private file_stat logger;
	private SbiData sbi;
	private SmfAttributeDateModel smf;
	private NrfSbiDto scp;
	private UpfPfcpDto upf;
	private String parameter;
	private String max;
	private String time;
	public SmfDataAcceptingDto(file_stat logger, SbiData sbi, SmfAttributeDateModel smf, NrfSbiDto scp, UpfPfcpDto upf,
			String parameter, String max, String time) {
		super();
		this.logger = logger;
		this.sbi = sbi;
		this.smf = smf;
		this.scp = scp;
		this.upf = upf;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public SmfDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public file_stat getLogger() {
		return logger;
	}
	public void setLogger(file_stat logger) {
		this.logger = logger;
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public SmfAttributeDateModel getSmf() {
		return smf;
	}
	public void setSmf(SmfAttributeDateModel smf) {
		this.smf = smf;
	}
	public NrfSbiDto getScp() {
		return scp;
	}
	public void setScp(NrfSbiDto scp) {
		this.scp = scp;
	}
	public UpfPfcpDto getUpf() {
		return upf;
	}
	public void setUpf(UpfPfcpDto upf) {
		this.upf = upf;
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
		return "SmfDataAcceptingDto [logger=" + logger + ", sbi=" + sbi + ", smf=" + smf + ", scp=" + scp + ", upf="
				+ upf + ", parameter=" + parameter + ", max=" + max + ", time=" + time + "]";
	}
	
	
	
	

}

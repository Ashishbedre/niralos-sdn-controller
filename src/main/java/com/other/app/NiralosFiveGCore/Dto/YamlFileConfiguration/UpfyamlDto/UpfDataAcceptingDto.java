package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.file_stat;

public class UpfDataAcceptingDto {

	private file_stat logger;
	private UpfSbiDataScpDto upf;
	private String smf;
	private String parameter;
	private String max;
	private String time;
	public UpfDataAcceptingDto(file_stat logger, UpfSbiDataScpDto upf, String smf, String parameter, String max,
			String time) {
		super();
		this.logger = logger;
		this.upf = upf;
		this.smf = smf;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public UpfDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public file_stat getLogger() {
		return logger;
	}
	public void setLogger(file_stat logger) {
		this.logger = logger;
	}
	public UpfSbiDataScpDto getUpf() {
		return upf;
	}
	public void setUpf(UpfSbiDataScpDto upf) {
		this.upf = upf;
	}
	public String getSmf() {
		return smf;
	}
	public void setSmf(String smf) {
		this.smf = smf;
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
		return "UpfDataAcceptingDto [logger=" + logger + ", upf=" + upf + ", smf=" + smf + ", parameter=" + parameter
				+ ", max=" + max + ", time=" + time + "]";
	}
	
	
	

}

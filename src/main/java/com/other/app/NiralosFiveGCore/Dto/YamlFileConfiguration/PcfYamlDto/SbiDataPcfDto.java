package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.PcfYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.SbiDevAAndPort;

public class SbiDataPcfDto {

	public SbiDevAAndPort sbi;
	public SbiDevAAndPort metrics;
	public String service_id;
	public SbiDataPcfDto(SbiDevAAndPort sbi, SbiDevAAndPort metrics, String service_id) {
		super();
		this.sbi = sbi;
		this.metrics = metrics;
		this.service_id = service_id;
	}
	public SbiDataPcfDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SbiDevAAndPort getSbi() {
		return sbi;
	}
	public void setSbi(SbiDevAAndPort sbi) {
		this.sbi = sbi;
	}
	public SbiDevAAndPort getMetrics() {
		return metrics;
	}
	public void setMetrics(SbiDevAAndPort metrics) {
		this.metrics = metrics;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	@Override
	public String toString() {
		return "SbiDataPcfDto [sbi=" + sbi + ", metrics=" + metrics + ", service_id=" + service_id + "]";
	}
	




	
	
	

}

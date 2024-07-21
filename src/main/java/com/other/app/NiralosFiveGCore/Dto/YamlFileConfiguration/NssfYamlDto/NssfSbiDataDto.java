package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.SbiDevAAndPort;

public class NssfSbiDataDto {

	public SbiDevAAndPort sbi;
	public SbiDevAAndPort metrics;
	public String service_id;
	public List<NsiNamePortSnssai> nsi;
	
	public NssfSbiDataDto(SbiDevAAndPort sbi, SbiDevAAndPort metrics, String service_id, List<NsiNamePortSnssai> nsi) {
		super();
		this.sbi = sbi;
		this.metrics = metrics;
		this.service_id = service_id;
		this.nsi = nsi;
	}
	public NssfSbiDataDto() {
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
	public List<NsiNamePortSnssai> getNsi() {
		return nsi;
	}
	public void setNsi(List<NsiNamePortSnssai> nsi) {
		this.nsi = nsi;
	}
	@Override
	public String toString() {
		return "NssfSbiDataDto [sbi=" + sbi + ", metrics=" + metrics + ", service_id=" + service_id + ", nsi=" + nsi
				+ "]";
	}

	
}

package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdmYamlDto;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.SbiDevAAndPort;

public class UdmHnetIdKey {

	public List<HnetData> hnet;
	public SbiDevAAndPort sbi;
	public String service_id;
	public UdmHnetIdKey(List<HnetData> hnet, SbiDevAAndPort sbi, String service_id) {
		super();
		this.hnet = hnet;
		this.sbi = sbi;
		this.service_id = service_id;
	}
	public UdmHnetIdKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<HnetData> getHnet() {
		return hnet;
	}
	public void setHnet(List<HnetData> hnet) {
		this.hnet = hnet;
	}
	public SbiDevAAndPort getSbi() {
		return sbi;
	}
	public void setSbi(SbiDevAAndPort sbi) {
		this.sbi = sbi;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	
	
	
	

	
	
}

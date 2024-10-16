package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto;

public class SbiDataScpDto {

	public SbiDevAAndPort sbi;
	public String service_id;
	public SbiDataScpDto(SbiDevAAndPort sbi, String service_id) {
		super();
		this.sbi = sbi;
		this.service_id = service_id;
	}
	public SbiDataScpDto() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "SbiDataScpDto [sbi=" + sbi + ", service_id=" + service_id + "]";
	}


	

	
	
	

}

package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto;

public class SbiNrfDto {
	
	public String name;
	public Integer port;
	public SbiNrfDto(String name, Integer port) {
		super();
		this.name = name;
		this.port = port;
	}
	public SbiNrfDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "SbiNrfDto [name=" + name + ", port=" + port + "]";
	}
	
	
	
	

	

}

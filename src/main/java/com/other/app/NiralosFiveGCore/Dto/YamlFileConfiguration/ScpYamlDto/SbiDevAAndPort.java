package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto;


public class SbiDevAAndPort {

	public String dev;
	public Integer port;
	public SbiDevAAndPort(String dev, Integer port) {
		super();
		this.dev = dev;
		this.port = port;
	}
	public SbiDevAAndPort() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDev() {
		return dev;
	}
	public void setDev(String dev) {
		this.dev = dev;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "SbiDevAAndPort [dev=" + dev + ", port=" + port + "]";
	}
	
	

}

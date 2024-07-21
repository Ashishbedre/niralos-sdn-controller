package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class DevAndPort {

	public String dev;
	public Integer port;
	public DevAndPort(String dev, Integer port) {
		super();
		this.dev = dev;
		this.port = port;
	}
	public DevAndPort() {
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
		return "DevAndPort [dev=" + dev + ", port=" + port + "]";
	}
	
	
	
}

package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class Sbi {

	public String addr;
	public Integer port;
	public Sbi(String addr, Integer port) {
		super();
		this.addr = addr;
		this.port = port;
	}
	public Sbi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "Sbi [addr=" + addr + ", port=" + port + "]";
	}
	
	

}

package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto;

public class UpfSubnet {

	public String addr;

	public UpfSubnet(String addr) {
		super();
		this.addr = addr;
	}

	public UpfSubnet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "UpfSubnet [addr=" + addr + "]";
	}
	
	

}

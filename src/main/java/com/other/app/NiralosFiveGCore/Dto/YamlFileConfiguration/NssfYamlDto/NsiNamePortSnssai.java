package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto;

public class NsiNamePortSnssai {

 public String name;
 public Integer port;
 public Nssfzs_nssai s_nssai;
public NsiNamePortSnssai(String name, Integer port, Nssfzs_nssai s_nssai) {
	super();
	this.name = name;
	this.port = port;
	this.s_nssai = s_nssai;
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
public Nssfzs_nssai getS_nssai() {
	return s_nssai;
}
public void setS_nssai(Nssfzs_nssai s_nssai) {
	this.s_nssai = s_nssai;
}
public NsiNamePortSnssai() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "NsiNamePortSnssai [name=" + name + ", port=" + port + ", s_nssai=" + s_nssai + "]";
}

 

 

}

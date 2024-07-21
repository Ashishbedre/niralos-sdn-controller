package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class ServerAndClientModel {

	public Boolean no_tls;
	public String cacert;
	public String key;
	public String cert;
	public ServerAndClientModel(Boolean no_tls, String cacert, String key, String cert) {
		super();
		this.no_tls = no_tls;
		this.cacert = cacert;
		this.key = key;
		this.cert = cert;
	}
	public ServerAndClientModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Boolean getNo_tls() {
		return no_tls;
	}
	public void setNo_tls(Boolean no_tls) {
		this.no_tls = no_tls;
	}
	public String getCacert() {
		return cacert;
	}
	public void setCacert(String cacert) {
		this.cacert = cacert;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	@Override
	public String toString() {
		return "ServerAndClientModel [no_tls=" + no_tls + ", cacert=" + cacert + ", key=" + key + ", cert=" + cert
				+ "]";
	}
	
	

}

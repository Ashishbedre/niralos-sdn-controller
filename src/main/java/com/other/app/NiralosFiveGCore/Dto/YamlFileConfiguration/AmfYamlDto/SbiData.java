package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class SbiData {
	public ServerAndClientModel server;
	public ServerAndClientModel client;
	public SbiData(ServerAndClientModel server, ServerAndClientModel client) {
		super();
		this.server = server;
		this.client = client;
	}
	public SbiData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServerAndClientModel getServer() {
		return server;
	}
	public void setServer(ServerAndClientModel server) {
		this.server = server;
	}
	public ServerAndClientModel getClient() {
		return client;
	}
	public void setClient(ServerAndClientModel client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "SbiData [server=" + server + ", client=" + client + "]";
	}
	
	

	

}

package com.other.app.NiralosFiveGCore.BackendServices.YamlConfiguration.Backend;


public interface ymlConfigService {
	
	public void amfConfigurationRead();
	public void amfConfigurationWrite();
	
	public void DockerComposeUp(String networking);
	public void DockerComposeDown();
	
	
	public void smfConfigurationRead();
	public void smfConfigurationWrite();
	
	public void upfConfigurationRead();
	public void upfConfigurationWrite();
	
	public void nssfConfigurationRead();
	public void nssfConfigurationWrite();
	
	public void scpConfigurationRead();
	public void scpConfigurationWrite();
	
	public void pcfConfigurationRead();
	public void pcfConfigurationWrite();
	
	public void ausfConfigurationRead();
	public void ausfConfigurationWrite();
	
	public void nrfConfigurationRead();
	public void nrfConfigurationWrite();
	
	public void udrConfigurationRead();
	public void udrConfigurationWrite();
	
	public void udmConfigurationRead();
	public void udmConfigurationWrite();
	
	public void bsfConfigurationRead();
	public void bsfConfigurationWrite();
	
}

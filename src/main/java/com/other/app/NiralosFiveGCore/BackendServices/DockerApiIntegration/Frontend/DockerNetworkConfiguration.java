package com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Frontend;
 
import java.util.List;

import com.other.app.NiralosFiveGCore.model.NetworkConfiguration;
 
 
public interface DockerNetworkConfiguration {
 
	public List<NetworkConfiguration> returnnetworkConfiguration();
	public void storenetworkConfiguration(NetworkConfiguration networkConfiguration, String networking);
	public void confiurationofDockerIp(String dockern2Ip,String dockern3Ip,String gatewayIp,String dockerVersionofFivegcore);
	
}

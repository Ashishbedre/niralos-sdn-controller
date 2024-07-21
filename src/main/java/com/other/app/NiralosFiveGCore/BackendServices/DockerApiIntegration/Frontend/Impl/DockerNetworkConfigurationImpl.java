package com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Frontend.Impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Frontend.DockerNetworkConfiguration;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.Repository.DockerNetworkConfiguration.DockerNetworkConfigurationrepo;
import com.other.app.NiralosFiveGCore.model.NetworkConfiguration;
 
@Service
public class DockerNetworkConfigurationImpl implements DockerNetworkConfiguration {
 
	@Autowired
	DockerNetworkConfigurationrepo dockerNetworkConfigurationrepo;
	
	@Autowired
	InternalDataService internalDataService;
	
 
	 @Autowired
	 ServiceofNetworkfunctionCompose serviceofNetworkfunctionCompose;
	
	@Override
	public void storenetworkConfiguration(NetworkConfiguration networkConfiguration, String networking) {
		serviceofNetworkfunctionCompose.niralosDeletionofUpfContainer();
		serviceofNetworkfunctionCompose.niralosDeletionofAmfContainer();
		serviceofNetworkfunctionCompose.niralosDeletionofSmfContainer();
		switch (networking) {
		case "0": {
			dockerNetworkConfigurationrepo.updateIpwithoutNetworking(networkConfiguration.getN2Ip(), networkConfiguration.getN3Ip(), networkConfiguration.getLocalControllerClientId());
			break;
		}
		case "1": {
			dockerNetworkConfigurationrepo.updateIpWithNetworking(networkConfiguration.getN2Ip(),networkConfiguration.getN3Ip(),networkConfiguration.getGatewayIp(),networkConfiguration.getLocalControllerClientId());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + networking);
		}
		 serviceofNetworkfunctionCompose.niralosAmf();
		serviceofNetworkfunctionCompose.niralosSmf();
		 serviceofNetworkfunctionCompose.niralosUpf(networking);
		 dockerNetworkConfigurationrepo.updateNetworkingInDatabase(networking,networkConfiguration.getLocalControllerClientId());
		}
 
	@Override
	public List<NetworkConfiguration> returnnetworkConfiguration() {
	return	dockerNetworkConfigurationrepo.findAll();
		
	}
	//Initial Service
	@Override
	public void confiurationofDockerIp(String dockern2Ip,String dockern3Ip,String gatewayIp, String dockerversionofFivegcore)
	{
		if(dockerNetworkConfigurationrepo.count()==0) {	
			dockerNetworkConfigurationrepo.save(new NetworkConfiguration(dockern2Ip,dockern3Ip,gatewayIp,dockerversionofFivegcore,internalDataService.getNiralControllerClientId()) );
		}else {
			//No need of update service here 
//			dockerNetworkConfigurationrepo.updateIpWithNetworking(dockern2Ip,dockern3Ip,gatewayIp,internalDataService.getNiralControllerClientId());
		System.out.println("No need of update : data is there for N2 and N3");
		}
	}
 
	
 
	
 
}
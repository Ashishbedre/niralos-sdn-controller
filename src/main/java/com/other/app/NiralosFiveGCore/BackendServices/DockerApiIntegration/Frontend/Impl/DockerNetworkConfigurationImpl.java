package com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Frontend.Impl;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.Impl.ServiceofNetworkfunctionComposeimpl;
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

	// Logger for DockerNetworkConfigurationImpl class
	 //final Logger logger = LoggerFactory.getLogger(ServiceofNetworkfunctionComposeimpl.class);
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:DockerNetworkConfigurationImpl");
	
	@Override
	public void storenetworkConfiguration(NetworkConfiguration networkConfiguration, String networking) {
		// Perform container deletions
		serviceofNetworkfunctionCompose.niralosDeletionofUpfContainer();
		serviceofNetworkfunctionCompose.niralosDeletionofAmfContainer();
		serviceofNetworkfunctionCompose.niralosDeletionofSmfContainer();
		switch (networking) {
		case "0": {
			dockerNetworkConfigurationrepo.updateIpwithoutNetworking(networkConfiguration.getN2Ip(), networkConfiguration.getN3Ip(),1l);
			break;
		}
		case "1": {
			dockerNetworkConfigurationrepo.updateIpWithNetworking(networkConfiguration.getN2Ip(),networkConfiguration.getN3Ip(),networkConfiguration.getGatewayIp(),1l);
			break;
		}
		default:
			loggerTypeB.error("Unexpected networking mode: {}", networking);
			throw new IllegalArgumentException("Unexpected value: " + networking);
		}
		 serviceofNetworkfunctionCompose.niralosAmf();
		serviceofNetworkfunctionCompose.niralosSmf();
		 serviceofNetworkfunctionCompose.niralosUpf(networking);
		 dockerNetworkConfigurationrepo.updateNetworkingInDatabase(networking,1L);
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
			dockerNetworkConfigurationrepo.save(new NetworkConfiguration(dockern2Ip,dockern3Ip,gatewayIp,dockerversionofFivegcore) );
		}else {
			//No need of update service here 
//			dockerNetworkConfigurationrepo.updateIpWithNetworking(dockern2Ip,dockern3Ip,gatewayIp,internalDataService.getNiralControllerClientId());
			loggerTypeB.info("confiurationofDockerIp: No need of update : data is there for N2 and N3");
		}
	}
 
	
 
	
 
}
package com.other.app.NiralosFiveGCore.Controller.Frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
import com.other.app.NiralosFiveGCore.Repository.DockerNetworkConfiguration.DockerNetworkConfigurationrepo;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;

@RestController
@RequestMapping("/v2/5gcoredeployment")
public class Deployment5gcore {
	 	@Autowired
	 	ServiceofNetworkfunctionCompose composeimpl;
		@Autowired
		DockerNetworkConfigurationrepo dockerNetworkConfigurationrepo;
		@Autowired
		InternalDataRepository internalDataRepository;
	 
	@GetMapping("/deploy_fivegcore/version={version}")
	public void deploy5gcore(@PathVariable String version){
		composeimpl.functionversion(version);
		 String networking = "0";
		 composeimpl.pullImageofMongo();
		 composeimpl.niralosNrf();
		 composeimpl.niralosScp();
		 composeimpl.niralosUdr();
		 composeimpl.niralosUdm();
		 composeimpl.niralosAusf();
		 composeimpl.niralosPcf();
		 composeimpl.niralosBsf();
		 composeimpl.niralosNssf();
		 composeimpl.niralosAmf();
		 composeimpl.niralosSmf();//
		 composeimpl.niralosUpf(networking);
		 dockerNetworkConfigurationrepo.updateNetworkingInDatabase(networking,1l);
	}
	
	@DeleteMapping("/deploy_fivegcore/version={version}")
	public void remove5gcore(@PathVariable String version)
	{
		composeimpl.functionversion(version);
		composeimpl.niralosDeletionofContainer();
	}
}

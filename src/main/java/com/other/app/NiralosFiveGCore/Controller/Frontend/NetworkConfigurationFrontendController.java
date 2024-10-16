package com.other.app.NiralosFiveGCore.Controller.Frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Frontend.DockerNetworkConfiguration;
import com.other.app.NiralosFiveGCore.model.NetworkConfiguration;

@RestController
@CrossOrigin
@RequestMapping("/v2/5gcore")
public class NetworkConfigurationFrontendController {

	@Autowired
	DockerNetworkConfiguration  configuration;
	
	@GetMapping("/network_configuration")
	public List<NetworkConfiguration> returnAllNetworkConfiguration() {
		return configuration.returnnetworkConfiguration();
	}
	
	@PostMapping("/network_configuration/netwoking={networking}")
	public void changeAllNetworkConfiguration(@RequestBody NetworkConfiguration networkConfiguration,@PathVariable String networking) {
		configuration.storenetworkConfiguration(networkConfiguration,networking);
	}
	 

}

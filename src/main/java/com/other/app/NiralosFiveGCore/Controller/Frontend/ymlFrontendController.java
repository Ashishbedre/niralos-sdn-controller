package com.other.app.NiralosFiveGCore.Controller.Frontend;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
import com.other.app.NiralosFiveGCore.BackendServices.YamlConfiguration.Frontend.ymlFrontendService;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.FrontendData.ListofNf;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.FrontendData.ReadDataReturn;
import com.other.app.NiralosFiveGCore.Repository.DockerNetworkConfiguration.DockerNetworkConfigurationrepo;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AmfYmlModelForMogoDb;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AusfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.BsfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NrfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NssfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.PcfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.ScpDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdmDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdrDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UpfDataMongoModel;

@CrossOrigin
@RestController
@RequestMapping("/5gcore")
public class ymlFrontendController {
	final Logger logger = LoggerFactory.getLogger(ymlFrontendController.class);

	@Autowired
	ymlFrontendService ymlService;
	 @Autowired
	 ServiceofNetworkfunctionCompose serviceofNetworkfunctionCompose;
	 @Autowired
	 DockerNetworkConfigurationrepo dockerNetworkConfigurationrepo;
	 
	 public String deploymentoffivegcore()
	 {
		 String networking = dockerNetworkConfigurationrepo.fetchInternetNetworkingofFivegcore();;
		return networking;
	
	 }

	
	@GetMapping("/listofnf")
	public ListofNf returnListofNf() {
		return ymlService.ListofNfName();
	}
	
	@GetMapping("/listofnf/nfName={nfName}")
	public ReadDataReturn returnListofNfReadData(@PathVariable("nfName") String nfName) {
		return ymlService.ListofNfNameDataRead(nfName);
	}

	
	@PostMapping("/listofnf/AmfnfName={nfName}")
	public String postAmfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody AmfYmlModelForMogoDb amfYmlModelForMogoDb) {
		serviceofNetworkfunctionCompose.niralosDeletionofAmfContainer();
		 serviceofNetworkfunctionCompose.niralosAmf();
		
		 ymlService.postDataofYamlFile(nfName,amfYmlModelForMogoDb);
		 return "Successfully Data Write of Amf";
	}
	
	
	@PostMapping("/listofnf/UpfnfName={nfName}")
	public String postUpfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody UpfDataMongoModel upfDataMongoModel ) {
		
		
		
		serviceofNetworkfunctionCompose.niralosDeletionofUpfContainer();
		
		 serviceofNetworkfunctionCompose.niralosUpf(this.deploymentoffivegcore());
		
		
		
		 ymlService.postDataofYamlFileInUpf(nfName,upfDataMongoModel);
		 return "Successfully Data Write of Upf";
	}
	
	@PostMapping("/listofnf/NssfnfName={nfName}")
	public String postNssfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody NssfDataMongoModel nssfDataMongoModel    ) {
		serviceofNetworkfunctionCompose.niralosDeletionofNssfContainer();
		 serviceofNetworkfunctionCompose.niralosNssf();
		 
		 
		 
		 ymlService.postDataofYamlFileInNssf(nfName,nssfDataMongoModel);
		 return "Successfully Data Write of Nssf";
	}
	
	@PostMapping("/listofnf/SmfnfName={nfName}")
	public String postSmfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody SmfDataMongoModel smfDataMongoModel ) {

		serviceofNetworkfunctionCompose.niralosDeletionofSmfContainer();
		serviceofNetworkfunctionCompose.niralosSmf();
		
		
		
		 ymlService.postDataofYamlFileInSmf(nfName,smfDataMongoModel);
		 return "Successfully Data Write of Smf";
	}
	
	@PostMapping("/listofnf/ScpnfName={nfName}")
	public String postScpDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody ScpDataMongoModel scpDataMongoModel ) {
		
		 ymlService.postDataofYamlFileInScp(nfName,scpDataMongoModel);
		 return "Successfully Data Write of Scp";
	}
	
	@PostMapping("/listofnf/PcfnfName={nfName}")
	public String postPcfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody PcfDataMongoModel pcfDataMongoModel ) {
		
		 ymlService.postDataofYamlFileInpcf(nfName,pcfDataMongoModel);
		 return "Successfully Data Write of Pcf";
	}
	
	@PostMapping("/listofnf/AusfnfName={nfName}")
	public String postAusfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody AusfDataMongoModel ausfDataMongoModel  ) {
		
		 ymlService.postDataofYamlFileInAusf(nfName,ausfDataMongoModel);
		 return "Successfully Data Write of ausf";
	}
	
	@PostMapping("/listofnf/BsfnfName={nfName}")
	public String postBsfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody BsfDataMongoModel bsfDataMongoModel) {
		
		 ymlService.postDataofYamlFileInBsf(nfName,bsfDataMongoModel);
		 return "Successfully Data Write of bsf";
	}
	
	@PostMapping("/listofnf/UdrnfName={nfName}")
	public String postUdrDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody UdrDataMongoModel udrDataMongoModel) {
		
		 ymlService.postDataofYamlFileInUdr(nfName,udrDataMongoModel);
		 return "Successfully Data Write of Udr";
	}
	
	
	@PostMapping("/listofnf/UdmnfName={nfName}")
	public String postUdmDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody UdmDataMongoModel udmDataMongoModel   ) {
		
		 ymlService.postDataofYamlFileInUdm(nfName,udmDataMongoModel);
		 return "Successfully Data Write of Udm";
	}
	@PostMapping("/listofnf/NrfnfName={nfName}")
	public String postNrfDataofYamlFile(
			@PathVariable("nfName") String nfName,
			@RequestBody NrfDataMongoModel nrfDataMongoModel   ) {
		
		 ymlService.postDataofYamlFileInNrf(nfName,nrfDataMongoModel);
		 return "Successfully Data Write of Nrf";
	}


}

//@PostMapping("/listofnf/AllnfName={nfName}")
//public String postAllDataofYamlFile(
//		@PathVariable("nfName") String nfName,
//		@RequestBody AcceptingAllYamlDataCombined acceptingAllYamlDataCombined) {
//	
//	logger.info(acceptingAllYamlDataCombined.getAmf().toString());
//	 
//	 ymlService.postDataofYamlFile("amf",acceptingAllYamlDataCombined.getAmf());
//	 ymlService.postDataofYamlFileInSmf("smf",acceptingAllYamlDataCombined.getSmf());
//	 ymlService.postDataofYamlFileInUpf("upf",acceptingAllYamlDataCombined.getUpf());
//	 ymlService.postDataofYamlFileInNssf("nssf",acceptingAllYamlDataCombined.getNssf());
//	
//	 return "Successfully Data Write of Amf";
//}
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
@RequestMapping("/v2/5gcore")
public class ymlFrontendController {
	final Logger logger = LoggerFactory.getLogger(ymlFrontendController.class);

	@Autowired
	ymlFrontendService ymlService;
	 @Autowired
	 ServiceofNetworkfunctionCompose serviceofNetworkfunctionCompose;
	 @Autowired
	 DockerNetworkConfigurationrepo dockerNetworkConfigurationrepo;
	 
	 public String deploymentoffivegcore(){
		 String networking = dockerNetworkConfigurationrepo.fetchInternetNetworkingofFivegcore();;
		return networking;
	
	 }

	
	@GetMapping("/config/listofnf")
	public ListofNf returnListofNf() {
		return ymlService.ListofNfName();
	}
	
	@GetMapping("/nf_config/nf_instance_name={nf_instance_name}")
	public ReadDataReturn returnListofNfReadData(@PathVariable("nf_instance_name") String nfName) {
		return ymlService.ListofNfNameDataRead(nfName);
	}

	
	@PostMapping("/amf_config/amf_instance_name={amf_instance_name}")
	public String postAmfDataofYamlFile(
			@PathVariable("amf_instance_name") String nfName,
			@RequestBody AmfYmlModelForMogoDb amfYmlModelForMogoDb) {
		serviceofNetworkfunctionCompose.niralosDeletionofAmfContainer();
		 serviceofNetworkfunctionCompose.niralosAmf();
		
		 ymlService.postDataofYamlFile(nfName,amfYmlModelForMogoDb);
		 return "Successfully Data Write of Amf";
	}
	
	
	@PostMapping("/upf_config/upf_instance_name={upf_instance_name}")
	public String postUpfDataofYamlFile(
			@PathVariable("upf_instance_name") String nfName,
			@RequestBody UpfDataMongoModel upfDataMongoModel ) {
		serviceofNetworkfunctionCompose.niralosDeletionofUpfContainer();	
		 serviceofNetworkfunctionCompose.niralosUpf(this.deploymentoffivegcore());
		
		
		
		 ymlService.postDataofYamlFileInUpf(nfName,upfDataMongoModel);
		 return "Successfully Data Write of Upf";
	}
	
	@PostMapping("/nssf_config/nssf_instance_name={nssf_instance_name}")
	public String postNssfDataofYamlFile(
			@PathVariable("nssf_instance_name") String nfName,
			@RequestBody NssfDataMongoModel nssfDataMongoModel    ) {
		 serviceofNetworkfunctionCompose.niralosDeletionofNssfContainer();
		 serviceofNetworkfunctionCompose.niralosNssf();
		 ymlService.postDataofYamlFileInNssf(nfName,nssfDataMongoModel);
		 return "Successfully Data Write of Nssf";
	}
	
	@PostMapping("/smf_config/smf_instance_name={smf_instance_name}")
	public String postSmfDataofYamlFile(
			@PathVariable("smf_instance_name") String nfName,
			@RequestBody SmfDataMongoModel smfDataMongoModel ) {

		 serviceofNetworkfunctionCompose.niralosDeletionofSmfContainer();
		 serviceofNetworkfunctionCompose.niralosSmf();
		 ymlService.postDataofYamlFileInSmf(nfName,smfDataMongoModel);
		 return "Successfully Data Write of Smf";
	}
	
	@PostMapping("/scp_config/scp_instance_name={scp_instance_name}")
	public String postScpDataofYamlFile(
			@PathVariable("scp_instance_name") String nfName,
			@RequestBody ScpDataMongoModel scpDataMongoModel ) {
		
		 ymlService.postDataofYamlFileInScp(nfName,scpDataMongoModel);
		 return "Successfully Data Write of Scp";
	}
	
	@PostMapping("/pcf_config/pcf_instance_name={pcf_instance_name}")
	public String postPcfDataofYamlFile(
			@PathVariable("pcf_instance_name") String nfName,
			@RequestBody PcfDataMongoModel pcfDataMongoModel ) {
		
		 ymlService.postDataofYamlFileInpcf(nfName,pcfDataMongoModel);
		 return "Successfully Data Write of Pcf";
	}
	
	@PostMapping("/ausf_config/ausf_instance_name={ausf_instance_name}")
	public String postAusfDataofYamlFile(
			@PathVariable("ausf_instance_name") String nfName,
			@RequestBody AusfDataMongoModel ausfDataMongoModel  ) {
		
		 ymlService.postDataofYamlFileInAusf(nfName,ausfDataMongoModel);
		 return "Successfully Data Write of ausf";
	}
	
	@PostMapping("/bsf_config/bsf_instance_name={bsf_instance_name}")
	public String postBsfDataofYamlFile(
			@PathVariable("bsf_instance_name") String nfName,
			@RequestBody BsfDataMongoModel bsfDataMongoModel) {
		
		 ymlService.postDataofYamlFileInBsf(nfName,bsfDataMongoModel);
		 return "Successfully Data Write of bsf";
	}
	
	@PostMapping("/udr_config/udr_instance_name={udr_instance_name}")
	public String postUdrDataofYamlFile(
			@PathVariable("udr_instance_name") String nfName,
			@RequestBody UdrDataMongoModel udrDataMongoModel) {
		
		 ymlService.postDataofYamlFileInUdr(nfName,udrDataMongoModel);
		 return "Successfully Data Write of Udr";
	}
	
	
	@PostMapping("/udm_config/udm_instance_name={udm_instance_name}")
	public String postUdmDataofYamlFile(
			@PathVariable("udm_instance_name") String nfName,
			@RequestBody UdmDataMongoModel udmDataMongoModel) {
		
		 ymlService.postDataofYamlFileInUdm(nfName,udmDataMongoModel);
		 return "Successfully Data Write of Udm";
	}
	@PostMapping("/nrf_config/nrf_instance_name={nrf_instance_name}")
	public String postNrfDataofYamlFile(
			@PathVariable("nrf_instance_name") String nfName,
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
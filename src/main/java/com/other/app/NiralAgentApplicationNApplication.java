package com.other.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.other.app.JwtAuthentication.Model.JwtRequest;
import com.other.app.JwtAuthentication.Repository.UserPassReposiatry;
import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.UpgErrorService;
import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.UpgService;
import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Frontend.DockerNetworkConfiguration;
import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Backend.GnbRegistrationDeregistrationFailureCountService;
import com.other.app.NiralosFiveGCore.BackendServices.GnbStatistics.Backend.GnBStatsCollectorService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InitializationService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.LiveDataCollector;
import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend.LivenessCheckerService;
import com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Backend.LogDataCollectorService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.CommonServices;
import com.other.app.NiralosFiveGCore.BackendServices.Throughput.Backend.ThroughputService;
import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Backend.UeStatsCollector;
import com.other.app.NiralosFiveGCore.BackendServices.YamlConfiguration.Backend.ymlConfigService;
import com.other.app.NiralosFiveGCore.Repository.DockerNetworkConfiguration.DockerNetworkConfigurationrepo;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.niralos_edge.Service.UpdaterAgent.UpdaterService;
import com.other.app.niralos_edge.Service.VmCreation.SpicePortGenService;


@SpringBootApplication
public class NiralAgentApplicationNApplication implements CommandLineRunner{
	@Autowired
	private UserPassReposiatry repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	InitializationService initializationService;
	@Autowired
	InternalDataRepository internalDataRepository;
	
	@Autowired
	ThroughputService throughputService;
	@Autowired
	LiveDataCollector liveDataCollector;
	@Autowired
	UeStatsCollector ueStatsCollector;
	@Autowired
	GnBStatsCollectorService gnBStatsCollectorService;
	@Autowired
	LivenessCheckerService livenessCheckerService;
	@Autowired
	UpgService upgService;
	@Autowired
	UpgErrorService upgErrorService;
//	@Autowired
//	AlertDataCollectorService alertDataCollectorService;
	@Autowired
	LogDataCollectorService logDataCollectorService;
//	@Autowired
//	LogLevelDataUpdateService logLevelDataUpdateService;
    @Autowired
    ymlConfigService ymlConfigService;
	
	@Autowired
	InternalDataService internalDataService;
	
	@Autowired
	GnbRegistrationDeregistrationFailureCountService registrationDeregistrationFailureCountService;
	
	@Autowired
	CommonServices commonServices;
	
	 @Autowired
	 ServiceofNetworkfunctionCompose serviceofNetworkfunctionCompose;
	 
	 @Autowired
	 DockerNetworkConfigurationrepo dockerNetworkConfigurationrepo;
	 
	 @Autowired
	 DockerNetworkConfiguration dockerNetworkConfiguration;
	 
	 @Autowired
	 SpicePortGenService spicePortGenService;
	 
	 @Autowired
	 UpdaterService updaterService;
	
		final Logger logger = LoggerFactory.getLogger(NiralAgentApplicationNApplication.class);
	 
	 private String dockern2Ip;
		@Value("${docker.n2ip}")
		public void setdockern2Ip(String dockern2Ip) {
			this.dockern2Ip = dockern2Ip;
		}
		 private String dockern3Ip;
			@Value("${docker.n3ip}")
			public void setdockern3Ip(String dockern3Ip) {
				this.dockern3Ip = dockern3Ip;
			}
			 private String gatewayIp;
				@Value("${docker.gatewayip}")
				public void setgatewayIp(String gatewayIp) {
					this.gatewayIp = gatewayIp;
				}
				@Value("${docker.versionof.fivegcore}")
				 public String dockerversionFivegcore;
				public void setDockerVersionFivegcore(String dockerversionFivegcore) {
					this.dockerversionFivegcore = dockerversionFivegcore;
				}
				
		

	public static void main(String[] args) {
		SpringApplication.run(NiralAgentApplicationNApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		spicePortGenService.generateUniqueNumber();
		initializationService.initialize();
		updaterService.settingPartition();
		if(repository.findUserInitialService()==0) {
		JwtRequest model = new JwtRequest();
		model.setUsername("admin");
		model.setPassword(this.bCryptPasswordEncoder.encode("admin@1234"));
		model.setRole("ROLE_NORMAL");
		model.setEmail("admin@adminxyzmail.com");
		this.repository.save(model);


		}
		
//		try {
		//Putting By default Data in Ip,port Configuration table
//		String TenantName = internalDataRepository.searchTheTenantId();
//		String siteName = internalDataRepository.searchThesiteId();
//		String controllerIp = internalDataRepository.searchThecontrollerIp();
//		String zoneName=internalDataService.getzone();
//		String subzoneName= internalDataService.getsubZone();
		

//		internalDataRepository.updateInernalData(1L, "localhost", "1", "1", "1", "1", "1", "1",
//				"1", "9090", "9090", "9090", "9090", "9090", "9090", "9090", "TCIL", "5G-Lab", "India","Institute",
//				"1","9090",
//				"1","9090",
//				"1","9090",
//				"1","9090",
//				"1","9090",
//				hypervisorIp,
//				hypervisorToken);
		
		
//		}catch (Exception e) {
//			logger.error("Tenent and Site Name is Null" + e);
//		}
		try {
		dockerNetworkConfiguration.confiurationofDockerIp(dockern2Ip,dockern3Ip,gatewayIp,dockerversionFivegcore);
		initializationService.intializeLiveData(); // Used to generate Zero Value for Live Data
	
	
		//overall Throughput Data
		 throughputService.ThroughputData();
		 
//		 String networking = "0";
		 
//         try {
//        	 String networking = "0";
//        	 ymlConfigServiceImpl  configServiceImpl = new ymlConfigServiceImpl();
//        	 configServiceImpl.DockerComposeUp(networking);
        	 
        	 
//		 serviceofNetworkfunctionCompose.pullImageofMongo();
//		 serviceofNetworkfunctionCompose.niralosNrf();
//		 serviceofNetworkfunctionCompose.niralosScp();
//		 serviceofNetworkfunctionCompose.niralosUdr();
//		 serviceofNetworkfunctionCompose.niralosUdm();
//		 serviceofNetworkfunctionCompose.niralosAusf();
//		 serviceofNetworkfunctionCompose.niralosPcf();
//		 serviceofNetworkfunctionCompose.niralosBsf();
//		 serviceofNetworkfunctionCompose.niralosNssf();
//		 serviceofNetworkfunctionCompose.niralosAmf();
//		 serviceofNetworkfunctionCompose.niralosSmf();//
//		 serviceofNetworkfunctionCompose.niralosUpf(networking);
//         }catch (Exception e) {
//
//        	 System.out.println("Docker api has some issues please check once.");
//         }
		 
		 
         
//		 serviceofNetworkfunctionCompose.niralosDeletionofContainer();
         
         //Alert Data
//         alertDataCollectorService.AlertDataParse();
     	//LiveData
         liveDataCollector.liveDataFetcher();
     	 //Ue Statstics
     	 ueStatsCollector.ueStatsParsing();
     	 //Gnb Statstics
     	 gnBStatsCollectorService.liveDataParse();
    	 livenessCheckerService.coreLivenessChecker();
    	 livenessCheckerService.gngAndUeLivenessChecker();
		}catch (Exception e) {
			logger.error("Error : " + e);
		}

		
    	//Upg Data
//    	 try {
//    	 upgService.saveUpgData();
//    	 upgErrorService.saveUpgErrorData();
//    	 }catch (Exception e) {
//			System.out.println("000000000000000000000000000000000000000000000");
//		} //log Data
//    	 logDataCollectorService.CollectingAmfLogData();
//    	 logDataCollectorService.CollectingNrfLogData();
//    	 logDataCollectorService.CollectingNssfLogData();
//    	 logDataCollectorService.CollectingSmfLogData();
//    	 logDataCollectorService.CollectingUdrLogData();
//    	 logDataCollectorService.CollectingUpfLogData();
    	// logLevelDataUpdateService.updateLogLevelDatabase();
//    	 registrationDeregistrationFailureCountService.fetchDataAndSaveToDatabase();
//    	 
//    	   	commonServices.udmIpWebclient();//remain
//    		commonServices.ausfIpWebclient();//remain
//        	commonServices.bsfIpWebclient();//remain
//        	commonServices.scpIpWebclient();//remain
//        	
    	 
//        	try {
//        	ymlConfigService.amfConfigurationRead();
//        	ymlConfigService.smfConfigurationRead();
//        	ymlConfigService.nrfConfigurationRead();
//        	ymlConfigService.nssfConfigurationRead();
//        	ymlConfigService.upfConfigurationRead();
//        	ymlConfigService.udmConfigurationRead();
//        	ymlConfigService.udrConfigurationRead();
//        	ymlConfigService.scpConfigurationRead();
//        	ymlConfigService.pcfConfigurationRead();
//        	ymlConfigService.bsfConfigurationRead();
//        	ymlConfigService.ausfConfigurationRead();
        		
        		
        		
//        	}catch (Exception e) {
//        		System.out.println("Exception of 5gcore Yaml :"+ e);			}
//    	 
//}
	}

}

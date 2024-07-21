package com.other.app.NiralosFiveGCore.BackendServices.YamlConfiguration.Backend.Impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.other.app.NiralosFiveGCore.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.YamlConfiguration.Backend.ymlConfigService;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.AmfAttributeDateModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.AmfYmlModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.DevAndPort;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.MccAndMnc;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.NetworkName;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.Nfsupport;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.PlmnSupport;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.Sbi;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiDataModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.Security;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.ServerAndClientModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.Sst;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.Tai;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.file_stat;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.guamiDataModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.t3512;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AusfYamlDto.AusfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AusfYamlDto.AusfDataScpDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.BsfYamlDto.BsfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NrfYamlDto.NrfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NrfYamlDto.SbiDataNrfDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto.NssfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto.NssfSbiDataDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.PcfYamlDto.PcfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.PcfYamlDto.SbiDataPcfDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.SbiDataScpDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.ScpDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.UpfPfcpDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto.SmfAttributeDateModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto.SmfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdmYamlDto.UdmDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdryamlDto.SbiDataUdrDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdryamlDto.UdrDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto.UpfDataAcceptingDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto.UpfSbiDataScpDto;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.AmfRepoModelDataRepo;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.AusfDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.BsfDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.NrfDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.NssfDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.PcfDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.ScpDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.SmfDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.UdmDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.UdrDataModelRepository;
import com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend.UpfDataModelRepository;
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


@Service
@EnableScheduling
public class ymlConfigServiceImpl implements ymlConfigService {
	final Logger logger = LoggerFactory.getLogger(ymlConfigServiceImpl.class);
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	AmfRepoModelDataRepo amfRepoModelDataRepo;
	@Autowired
	AusfDataModelRepository ausfDataModelRepository;
	@Autowired
	ScpDataModelRepository scpDataModelRepository;
	@Autowired
	NssfDataModelRepository nssfDataModelRepository;
	@Autowired
	SmfDataModelRepository smfDataModelRepository;
	@Autowired
	UdrDataModelRepository udrDataModelRepository;
	@Autowired
	PcfDataModelRepository pcfDataModelRepository;
	@Autowired
	BsfDataModelRepository bsfDataModelRepository;
	@Autowired
	NrfDataModelRepository nrfDataModelRepository;
	@Autowired
	UdmDataModelRepository udmDataModelRepository;
	@Autowired
	UpfDataModelRepository upfDataModelRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	private String filelistpath;

	@Value("${filelistpath}")
	public void setFilelistpath(String filelistpath) {
		this.filelistpath = filelistpath;
	}

	private String filepathofReadYaml;

	@Value("${filelistpath.for.readYaml}")
	public void setFilepathofReadYaml(String filepathofReadYaml) {
		this.filepathofReadYaml = filepathofReadYaml;
	}

//	@Autowired
//	@Qualifier("firstMongoTemplate")
//	 MongoTemplate firstMongoTemplate;
//	@Scheduled(fixedRate = 20000)
	@Override
	public void amfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
			try {

				if (tenantId != null && siteId != null) {
					System.out.println("Both tenentName and siteName are not null");

					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					System.out.println("initially object is here and file path" + filelistpath);

					File files = new File(filelistpath);
					logger.info("filepath is fine");
					String[] fileList = files.list();
					logger.info("file has something.....");
					if (fileList != null) {
						logger.info("Number of files in the directory: " + fileList.length);

						for (String nfName : fileList) {
							if (nfName.startsWith("amf")) {
								logger.info("Processing file: " + nfName);

								String[] nfNameConncate = nfName.split("\\.");

								// Add debug statements to inspect nfNameConncate
								logger.info("nfNameConncate length: " + nfNameConncate.length);

								for (int i = 0; i < nfNameConncate.length; i++) {
									logger.info("loop start with this length: " + nfNameConncate.length
											+ "now hit the yaml  :  " + filepathofReadYaml + nfNameConncate[0]
											+ ".yaml");

									AmfYmlModel order = mapper.readValue(
											new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
											AmfYmlModel.class);

									System.out.println(
											"now hit the yaml  :  " + filepathofReadYaml + nfNameConncate[0] + ".yaml");
									if (amfRepoModelDataRepo
											.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0])
											.isEmpty()) {
										AmfYmlModelForMogoDb amfYmlModelForMogoDb = new AmfYmlModelForMogoDb();
										amfYmlModelForMogoDb.setLogger(order.getLogger());
										amfYmlModelForMogoDb.setSbi(order.getSbi());
										amfYmlModelForMogoDb.setAmf(order.getAmf());
										amfYmlModelForMogoDb.setScp(order.getScp());
										amfYmlModelForMogoDb.setParameter(order.getParameter());
										amfYmlModelForMogoDb.setMax(order.getMax());
										amfYmlModelForMogoDb.setUsrsctp(order.getUsrsctp());
										amfYmlModelForMogoDb.setTime(order.getTime());
										amfYmlModelForMogoDb.setAgentId(LocalSdnId);
										amfYmlModelForMogoDb.setTenantId(tenantId);
										amfYmlModelForMogoDb.setSiteId(siteId);
										amfYmlModelForMogoDb.setNfName(nfNameConncate[0]);
										amfRepoModelDataRepo.save(amfYmlModelForMogoDb);
										logger.info("data is save : ");
									} else {

										List<AmfYmlModelForMogoDb> amf = amfRepoModelDataRepo
												.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
										for (AmfYmlModelForMogoDb amfYmlModelForMogoDb : amf) {
											amfYmlModelForMogoDb.setLogger(order.getLogger());
											amfYmlModelForMogoDb.setSbi(order.getSbi());
											amfYmlModelForMogoDb.setAmf(order.getAmf());
											amfYmlModelForMogoDb.setScp(order.getScp());
											amfYmlModelForMogoDb.setParameter(order.getParameter());
											amfYmlModelForMogoDb.setMax(order.getMax());
											amfYmlModelForMogoDb.setUsrsctp(order.getUsrsctp());
											amfYmlModelForMogoDb.setTime(order.getTime());
											amfYmlModelForMogoDb.setAgentId(LocalSdnId);
											amfYmlModelForMogoDb.setTenantId(tenantId);
											amfYmlModelForMogoDb.setSiteId(siteId);
											amfYmlModelForMogoDb.setNfName(nfNameConncate[0]);
											amfRepoModelDataRepo.save(amfYmlModelForMogoDb);
										}
									}

								}
							}
						}
//			DockerComposeUp();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("error is here : " + e);
			}
		});
	}
	// Sync this data in a same model to controller and controller able to see this
	// data

//	@Scheduled(fixedRate = 20000)
	@Override
	public void amfConfigurationWrite() {
		// DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
			File files = new File(filelistpath);
			String[] fileList = files.list();

			for (String nfName : fileList) {
				if (nfName.startsWith("amf")) {
					String[] nfNameConncate = nfName.split("\\.");

					for (int i = 0; i < nfNameConncate.length; i++) {
						List<AmfYmlModelForMogoDb> amf1 = amfRepoModelDataRepo
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);

						for (AmfYmlModelForMogoDb amfYmlModelForMogoDb : amf1) {
							ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
							mapper.findAndRegisterModules();
							file_stat file_stat = amfYmlModelForMogoDb.getLogger();
							file_stat.setFile_stat(file_stat.getFile_stat());

							SbiData sbiDatas = amfYmlModelForMogoDb.getSbi();
							ServerAndClientModel client = sbiDatas.getClient();
							ServerAndClientModel server = sbiDatas.getServer();
							sbiDatas.setClient(client);
							sbiDatas.setServer(server);

							AmfAttributeDateModel amf = amfYmlModelForMogoDb.getAmf();
							DevAndPort sbi = amf.getSbi();
							DevAndPort ngap = amf.getNgap();
							DevAndPort metrics = amf.getMetrics();
							List<guamiDataModel> guami = amf.getGuami();
							List<Tai> tai = amf.getTai();
							List<PlmnSupport> plmn_support = amf.getPlmn_support();

							for (PlmnSupport string : plmn_support) {
								MccAndMnc mccAndMnc = string.getPlmn_id();
								List<Sst> ssts = string.getS_nssai();
							}

							Security security = amf.getSecurity();
							List<String> integrity_order = security.getIntegrity_order();
							List<String> ciphering_order = security.getCiphering_order();
							security.setCiphering_order(ciphering_order);
							security.setIntegrity_order(integrity_order);

							NetworkName network_name = amf.getNetwork_name();
							String amf_name = amf.getAmf_name();
							String service_id = amf.getService_id();
							Nfsupport nfsupport = amf.getNf_support();
							AmfAttributeDateModel amfAttributeDateModel = new AmfAttributeDateModel(sbi, ngap, metrics,
									guami, tai, plmn_support, security, network_name, amf_name, service_id, nfsupport);

							SbiDataModel scp = amfYmlModelForMogoDb.getScp();
							List<Sbi> sbii = scp.sbi;
							scp.setSbi(sbii);
							t3512 time = amfYmlModelForMogoDb.getTime();

							AmfYmlModel amfYmlModel = new AmfYmlModel();
							amfYmlModel.setLogger(file_stat);
							amfYmlModel.setSbi(sbiDatas);
							amfYmlModel.setAmf(amfAttributeDateModel);
							amfYmlModel.setScp(scp);
							amfYmlModel.setParameter(amfYmlModelForMogoDb.getParameter());
							amfYmlModel.setMax(amfYmlModelForMogoDb.getMax());
							amfYmlModel.setUsrsctp(amfYmlModelForMogoDb.getUsrsctp());
							amfYmlModel.setTime(time);

							try {
								mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
										amfYmlModel);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void scpConfigurationRead() {

		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
			if (tenantId != null && siteId != null) {
				ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
				mapper = new ObjectMapper(new YAMLFactory());
				mapper.findAndRegisterModules();
				File files = new File(filelistpath);
				String[] fileList = files.list();
				for (String nfName : fileList) {
					if (nfName.startsWith("scp")) {
						String[] nfNameConncate = nfName.split("\\.");
						try {
							ScpDataAcceptingDto scpDataAcceptingDto = mapper.readValue(
									new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
									ScpDataAcceptingDto.class);
							if (scpDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
								ScpDataMongoModel scpDataMongoModel = new ScpDataMongoModel();
								scpDataMongoModel.setDb_uri(scpDataAcceptingDto.getDb_uri());
								scpDataMongoModel.setSbi(scpDataAcceptingDto.getSbi());
								scpDataMongoModel.setScp(scpDataAcceptingDto.getScp());
								scpDataMongoModel.setNrf(scpDataAcceptingDto.getNrf());
								scpDataMongoModel.setParameter(scpDataAcceptingDto.getParameter());
								scpDataMongoModel.setMax(scpDataAcceptingDto.getMax());
								scpDataMongoModel.setTenantId(tenantId);
								scpDataMongoModel.setSiteId(siteId);
								scpDataMongoModel.setAgentId(LocalSdnId);
								scpDataMongoModel.setNfName(nfNameConncate[0]);
								scpDataModelRepository.save(scpDataMongoModel);
								logger.info("222222222222" + nfNameConncate[0]);

							} else {
								List<ScpDataMongoModel> scpDataMongoModels = scpDataModelRepository
										.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
								for (ScpDataMongoModel scpDataofMongo : scpDataMongoModels) {
									scpDataofMongo.setDb_uri(scpDataAcceptingDto.getDb_uri());
									scpDataofMongo.setSbi(scpDataAcceptingDto.getSbi());
									scpDataofMongo.setScp(scpDataAcceptingDto.getScp());
									scpDataofMongo.setNrf(scpDataAcceptingDto.getNrf());
									scpDataofMongo.setParameter(scpDataAcceptingDto.getParameter());
									scpDataofMongo.setMax(scpDataAcceptingDto.getMax());

									scpDataofMongo.setTenantId(tenantId);
									scpDataofMongo.setSiteId(siteId);
									scpDataofMongo.setAgentId(LocalSdnId);
									scpDataofMongo.setNfName(nfNameConncate[0]);
									scpDataModelRepository.save(scpDataofMongo);

									logger.info("33333333333333333");
									;
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void scpConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if(nfName.startsWith("scp")) {
			String[] nfNameConncate=nfName.split("\\.");		
			
			List<ScpDataMongoModel> scpDataMongoModels=	scpDataModelRepository.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				
				for (ScpDataMongoModel scpDataMongoModel : scpDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					
					SbiData sbiData = scpDataMongoModel.getSbi();
					sbiData.setClient(sbiData.getClient());
					sbiData.setServer(sbiData.getServer());
					SbiDataScpDto sbiDataScpDto =scpDataMongoModel.getScp();
					NrfSbiDto nrfSbiDto =scpDataMongoModel.getNrf();
					
					
					ScpDataAcceptingDto acceptingDto = new ScpDataAcceptingDto();
					acceptingDto.setDb_uri(scpDataMongoModel.getDb_uri());
					acceptingDto.setSbi(sbiData);
					acceptingDto.setScp(sbiDataScpDto);
					acceptingDto.setNrf(nrfSbiDto);
					acceptingDto.setParameter(scpDataMongoModel.getParameter());
					acceptingDto.setMax(scpDataMongoModel.getMax());
					acceptingDto.setTime(scpDataMongoModel.getTime());
					try {
						mapper.writeValue(new File(filepathofReadYaml+nfNameConncate[0]+".yaml"), acceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				
				}
			
			}	
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void pcfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("pcf")) {
					String[] nfNameConncate = nfName.split("\\.");

					try {
						PcfDataAcceptingDto pcfDataAcceptingDtos = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), PcfDataAcceptingDto.class);

						if (pcfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							PcfDataMongoModel pcfDataMongoModel = new PcfDataMongoModel();
							pcfDataMongoModel.setDb_uri(pcfDataAcceptingDtos.getDb_uri());
							pcfDataMongoModel.setSbi(pcfDataAcceptingDtos.getSbi());
							pcfDataMongoModel.setPcf(pcfDataAcceptingDtos.getPcf());
							pcfDataMongoModel.setScp(pcfDataAcceptingDtos.getScp());
							pcfDataMongoModel.setParameter(pcfDataAcceptingDtos.getParameter());
							pcfDataMongoModel.setMax(pcfDataAcceptingDtos.getMax());
							pcfDataMongoModel.setTime(pcfDataAcceptingDtos.getTime());

							pcfDataMongoModel.setTenantId(tenantId);
							pcfDataMongoModel.setSiteId(siteId);
							pcfDataMongoModel.setAgentId(LocalSdnId);
							pcfDataMongoModel.setNfName(nfNameConncate[0]);

							pcfDataModelRepository.save(pcfDataMongoModel);
						} else {
							List<PcfDataMongoModel> pcfDataMongoModels = pcfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (PcfDataMongoModel PcfDataMongoModelDataUpdationList : pcfDataMongoModels) {
								PcfDataMongoModelDataUpdationList.setDb_uri(pcfDataAcceptingDtos.getDb_uri());
								PcfDataMongoModelDataUpdationList.setSbi(pcfDataAcceptingDtos.getSbi());
								PcfDataMongoModelDataUpdationList.setPcf(pcfDataAcceptingDtos.getPcf());
								PcfDataMongoModelDataUpdationList.setScp(pcfDataAcceptingDtos.getScp());
								PcfDataMongoModelDataUpdationList.setParameter(pcfDataAcceptingDtos.getParameter());
								PcfDataMongoModelDataUpdationList.setMax(pcfDataAcceptingDtos.getMax());
								PcfDataMongoModelDataUpdationList.setTime(pcfDataAcceptingDtos.getTime());

								PcfDataMongoModelDataUpdationList.setTenantId(tenantId);
								PcfDataMongoModelDataUpdationList.setSiteId(siteId);
								PcfDataMongoModelDataUpdationList.setAgentId(LocalSdnId);
								PcfDataMongoModelDataUpdationList.setNfName(nfNameConncate[0]);
								pcfDataModelRepository.save(PcfDataMongoModelDataUpdationList);
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void pcfConfigurationWrite() {
		
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("pcf")) {
				String[] nfNameConncate = nfName.split("\\.");
				List<PcfDataMongoModel> pcfDataMongoModels = pcfDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (PcfDataMongoModel PcfDataMongoModelDataUpdationList : pcfDataMongoModels) {

					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					SbiData sbiData = PcfDataMongoModelDataUpdationList.getSbi();
					sbiData.setClient(sbiData.getClient());
					sbiData.setServer(sbiData.getServer());
					SbiDataPcfDto sbiDataPcfDto = PcfDataMongoModelDataUpdationList.getPcf();
					NrfSbiDto nrfSbiDto = PcfDataMongoModelDataUpdationList.getScp();

					PcfDataAcceptingDto pcfDataAcceptingDto = new PcfDataAcceptingDto();
					pcfDataAcceptingDto.setDb_uri(PcfDataMongoModelDataUpdationList.getDb_uri());
					pcfDataAcceptingDto.setSbi(sbiData);
					pcfDataAcceptingDto.setPcf(sbiDataPcfDto);
					pcfDataAcceptingDto.setScp(nrfSbiDto);
					pcfDataAcceptingDto.setParameter(PcfDataMongoModelDataUpdationList.getParameter());
					pcfDataAcceptingDto.setMax(PcfDataMongoModelDataUpdationList.getMax());
					pcfDataAcceptingDto.setTime(PcfDataMongoModelDataUpdationList.getTime());
					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								pcfDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void ausfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("ausf")) {
					String[] nfNameConncate = nfName.split("\\.");

					try {
						AusfDataAcceptingDto ausfDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), AusfDataAcceptingDto.class);
						if (ausfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							AusfDataMongoModel ausfDataMongoModel = new AusfDataMongoModel();
							ausfDataMongoModel.setSbi(ausfDataAcceptingDto.getSbi());
							ausfDataMongoModel.setAusf(ausfDataAcceptingDto.getAusf());
							ausfDataMongoModel.setScp(ausfDataAcceptingDto.getScp());
							ausfDataMongoModel.setParameter(ausfDataAcceptingDto.getParameter());
							ausfDataMongoModel.setMax(ausfDataAcceptingDto.getMax());
							ausfDataMongoModel.setTime(ausfDataAcceptingDto.getTime());
							ausfDataMongoModel.setTenantId(tenantId);
							ausfDataMongoModel.setSiteId(siteId);
							ausfDataMongoModel.setAgentId(LocalSdnId);
							ausfDataMongoModel.setNfName(nfNameConncate[0]);
							ausfDataModelRepository.save(ausfDataMongoModel);
						} else {
							List<AusfDataMongoModel> ausfDataMongoModels = ausfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (AusfDataMongoModel ausfDataMongoModel : ausfDataMongoModels) {
								ausfDataMongoModel.setSbi(ausfDataAcceptingDto.getSbi());
								ausfDataMongoModel.setAusf(ausfDataAcceptingDto.getAusf());
								ausfDataMongoModel.setScp(ausfDataAcceptingDto.getScp());
								ausfDataMongoModel.setParameter(ausfDataAcceptingDto.getParameter());
								ausfDataMongoModel.setMax(ausfDataAcceptingDto.getMax());
								ausfDataMongoModel.setTime(ausfDataAcceptingDto.getTime());
								ausfDataMongoModel.setTenantId(tenantId);
								ausfDataMongoModel.setSiteId(siteId);
								ausfDataMongoModel.setAgentId(LocalSdnId);
								ausfDataMongoModel.setNfName(nfNameConncate[0]);
								ausfDataModelRepository.save(ausfDataMongoModel);
							}
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void ausfConfigurationWrite() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		;
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("ausf")) {
				String[] nfNameConncate = nfName.split("\\.");

				List<AusfDataMongoModel> ausfDataMongoModels = ausfDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (AusfDataMongoModel ausfDataMongoModel : ausfDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					SbiData sbiData = ausfDataMongoModel.getSbi();
					AusfDataScpDto ausfDataScpDto = ausfDataMongoModel.getAusf();
					NrfSbiDto nrfSbiDto = ausfDataMongoModel.getScp();

					AusfDataAcceptingDto ausfDataAcceptingDto = new AusfDataAcceptingDto();
					ausfDataAcceptingDto.setSbi(sbiData);
					ausfDataAcceptingDto.setAusf(ausfDataScpDto);
					ausfDataAcceptingDto.setScp(nrfSbiDto);
					ausfDataAcceptingDto.setParameter(ausfDataMongoModel.getParameter());
					ausfDataAcceptingDto.setMax(ausfDataMongoModel.getMax());
					ausfDataAcceptingDto.setTime(ausfDataMongoModel.getTime());

					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								ausfDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void bsfConfigurationRead() {
		
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("bsf")) {
					String[] nfNameConncate = nfName.split("\\.");
					try {
						BsfDataAcceptingDto bsfDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), BsfDataAcceptingDto.class);
						if (bsfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							BsfDataMongoModel bsfDataMongoModel = new BsfDataMongoModel();
							bsfDataMongoModel.setSbi(bsfDataAcceptingDto.getSbi());
							bsfDataMongoModel.setBsf(bsfDataAcceptingDto.getBsf());
							bsfDataMongoModel.setScp(bsfDataAcceptingDto.getScp());
							bsfDataMongoModel.setParameter(bsfDataAcceptingDto.getParameter());
							bsfDataMongoModel.setMax(bsfDataAcceptingDto.getMax());
							bsfDataMongoModel.setTime(bsfDataAcceptingDto.getTime());
							bsfDataMongoModel.setTenantId(tenantId);
							bsfDataMongoModel.setSiteId(siteId);
							bsfDataMongoModel.setAgentId(LocalSdnId);
							bsfDataMongoModel.setNfName(nfNameConncate[0]);
							bsfDataModelRepository.save(bsfDataMongoModel);
						} else {
							List<BsfDataMongoModel> bsfDataMongoModels = bsfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (BsfDataMongoModel bsfDataMongoModel : bsfDataMongoModels) {
								bsfDataMongoModel.setSbi(bsfDataAcceptingDto.getSbi());
								bsfDataMongoModel.setBsf(bsfDataAcceptingDto.getBsf());
								bsfDataMongoModel.setScp(bsfDataAcceptingDto.getScp());
								bsfDataMongoModel.setParameter(bsfDataAcceptingDto.getParameter());
								bsfDataMongoModel.setMax(bsfDataAcceptingDto.getMax());
								bsfDataMongoModel.setTime(bsfDataAcceptingDto.getTime());
								bsfDataMongoModel.setTenantId(tenantId);
								bsfDataMongoModel.setSiteId(siteId);
								bsfDataMongoModel.setAgentId(LocalSdnId);
								bsfDataMongoModel.setNfName(nfNameConncate[0]);
								bsfDataModelRepository.save(bsfDataMongoModel);
							}

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void bsfConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("bsf")) {
				String[] nfNameConncate = nfName.split("\\.");
				List<BsfDataMongoModel> bsfDataMongoModels = bsfDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (BsfDataMongoModel bsfDataMongoModel : bsfDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();

					SbiData sbiData = bsfDataMongoModel.getSbi();
					AusfDataScpDto ausfDataScpDto = bsfDataMongoModel.getBsf();
					NrfSbiDto nrfSbiDto = bsfDataMongoModel.getScp();

					BsfDataAcceptingDto bsfDataAcceptingDto = new BsfDataAcceptingDto();
					bsfDataAcceptingDto.setSbi(sbiData);
					bsfDataAcceptingDto.setBsf(ausfDataScpDto);
					bsfDataAcceptingDto.setScp(nrfSbiDto);
					bsfDataAcceptingDto.setParameter(bsfDataMongoModel.getParameter());
					bsfDataAcceptingDto.setMax(bsfDataMongoModel.getMax());
					bsfDataAcceptingDto.setTime(bsfDataMongoModel.getTime());

					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								bsfDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void udrConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("udr")) {
					String[] nfNameConncate = nfName.split("\\.");

					try {
						UdrDataAcceptingDto udrDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), UdrDataAcceptingDto.class);
						if (udrDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							UdrDataMongoModel udrDataMongoModel = new UdrDataMongoModel();
							udrDataMongoModel.setDb_uri(udrDataAcceptingDto.getDb_uri());
							udrDataMongoModel.setSbi(udrDataAcceptingDto.getSbi());
							udrDataMongoModel.setUdr(udrDataAcceptingDto.getUdr());
							udrDataMongoModel.setScp(udrDataAcceptingDto.getScp());
							udrDataMongoModel.setParameter(udrDataAcceptingDto.getParameter());
							udrDataMongoModel.setMax(udrDataAcceptingDto.getMax());
							udrDataMongoModel.setTime(udrDataAcceptingDto.getTime());
							udrDataMongoModel.setTenantId(tenantId);
							udrDataMongoModel.setSiteId(siteId);
							udrDataMongoModel.setAgentId(LocalSdnId);
							udrDataMongoModel.setNfName(nfNameConncate[0]);
							udrDataModelRepository.save(udrDataMongoModel);
						} else {
							List<UdrDataMongoModel> udrDataMongoModels = udrDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (UdrDataMongoModel udrDataMongoModel : udrDataMongoModels) {
								udrDataMongoModel.setDb_uri(udrDataAcceptingDto.getDb_uri());
								udrDataMongoModel.setSbi(udrDataAcceptingDto.getSbi());
								udrDataMongoModel.setUdr(udrDataAcceptingDto.getUdr());
								udrDataMongoModel.setScp(udrDataAcceptingDto.getScp());
								udrDataMongoModel.setParameter(udrDataAcceptingDto.getParameter());
								udrDataMongoModel.setMax(udrDataAcceptingDto.getMax());
								udrDataMongoModel.setTime(udrDataAcceptingDto.getTime());
								udrDataMongoModel.setTenantId(tenantId);
								udrDataMongoModel.setSiteId(siteId);
								udrDataMongoModel.setAgentId(LocalSdnId);
								udrDataMongoModel.setNfName(nfNameConncate[0]);
								udrDataModelRepository.save(udrDataMongoModel);
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void udrConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("udr")) {
				String[] nfNameConncate = nfName.split("\\.");

				List<UdrDataMongoModel> udrDataMongoModels = udrDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (UdrDataMongoModel udrDataMongoModel : udrDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					SbiData sbiData = udrDataMongoModel.getSbi();
					SbiDataUdrDto dataScpDto = udrDataMongoModel.getUdr();
					NrfSbiDto nrfSbiDto = udrDataMongoModel.getScp();

					UdrDataAcceptingDto udrDataAcceptingDto = new UdrDataAcceptingDto();
					udrDataAcceptingDto.setDb_uri(udrDataMongoModel.getDb_uri());
					udrDataAcceptingDto.setSbi(sbiData);
					udrDataAcceptingDto.setUdr(dataScpDto);
					udrDataAcceptingDto.setScp(nrfSbiDto);
					udrDataAcceptingDto.setParameter(udrDataMongoModel.getParameter());
					udrDataAcceptingDto.setMax(udrDataMongoModel.getMax());
					udrDataAcceptingDto.setTime(udrDataMongoModel.getTime());
					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								udrDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void nrfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("nrf")) {
					String[] nfNameConncate = nfName.split("\\.");

					try {
						NrfDataAcceptingDto nrfDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), NrfDataAcceptingDto.class);
						if (nrfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							NrfDataMongoModel nrfDataMongoModel = new NrfDataMongoModel();
							nrfDataMongoModel.setSbi(nrfDataAcceptingDto.getSbi());
							nrfDataMongoModel.setNrf(nrfDataAcceptingDto.getNrf());
							nrfDataMongoModel.setScp(nrfDataAcceptingDto.getScp());
							nrfDataMongoModel.setParameter(nrfDataAcceptingDto.getParameter());
							nrfDataMongoModel.setMax(nrfDataAcceptingDto.getMax());
							nrfDataMongoModel.setTime(nrfDataAcceptingDto.getTime());
							nrfDataMongoModel.setTenantId(tenantId);
							nrfDataMongoModel.setSiteId(siteId);
							nrfDataMongoModel.setAgentId(LocalSdnId);
							nrfDataMongoModel.setNfName(nfNameConncate[0]);
							nrfDataModelRepository.save(nrfDataMongoModel);
						} else {
							List<NrfDataMongoModel> nrfDataMongoModels = nrfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (NrfDataMongoModel nrfDataMongoModel : nrfDataMongoModels) {
								nrfDataMongoModel.setSbi(nrfDataAcceptingDto.getSbi());
								nrfDataMongoModel.setNrf(nrfDataAcceptingDto.getNrf());
								nrfDataMongoModel.setScp(nrfDataAcceptingDto.getScp());
								nrfDataMongoModel.setParameter(nrfDataAcceptingDto.getParameter());
								nrfDataMongoModel.setMax(nrfDataAcceptingDto.getMax());
								nrfDataMongoModel.setTime(nrfDataAcceptingDto.getTime());
								nrfDataMongoModel.setTenantId(tenantId);
								nrfDataMongoModel.setSiteId(siteId);
								nrfDataMongoModel.setAgentId(LocalSdnId);
								nrfDataMongoModel.setNfName(nfNameConncate[0]);
								nrfDataModelRepository.save(nrfDataMongoModel);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		});

	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void nrfConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("nrf")) {
				String[] nfNameConncate = nfName.split("\\.");
				List<NrfDataMongoModel> nrfDataMongoModels = nrfDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (NrfDataMongoModel nrfDataMongoModel : nrfDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();

					SbiData sbiData = nrfDataMongoModel.getSbi();
					SbiDataNrfDto sbiDataNrfDto = nrfDataMongoModel.getNrf();
					NrfSbiDto nrfSbiDto = nrfDataMongoModel.getScp();

					NrfDataAcceptingDto nrfDataAcceptingDto = new NrfDataAcceptingDto();
					nrfDataAcceptingDto.setSbi(sbiData);
					nrfDataAcceptingDto.setNrf(sbiDataNrfDto);
					nrfDataAcceptingDto.setScp(nrfSbiDto);
					nrfDataAcceptingDto.setParameter(nrfDataMongoModel.getParameter());
					nrfDataAcceptingDto.setMax(nrfDataMongoModel.getMax());
					nrfDataAcceptingDto.setTime(nrfDataMongoModel.getTime());
					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								nrfDataAcceptingDto);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void udmConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("udm")) {
					String[] nfNameConncate = nfName.split("\\.");
					try {
						UdmDataAcceptingDto udmDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), UdmDataAcceptingDto.class);
						if (udmDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							UdmDataMongoModel udmDataMongoModel = new UdmDataMongoModel();
							udmDataMongoModel.setSbi(udmDataAcceptingDto.getSbi());
							udmDataMongoModel.setUdm(udmDataAcceptingDto.getUdm());
							udmDataMongoModel.setScp(udmDataAcceptingDto.getScp());
							udmDataMongoModel.setParameter(udmDataAcceptingDto.getParameter());
							udmDataMongoModel.setMax(udmDataAcceptingDto.getMax());
							udmDataMongoModel.setTime(udmDataAcceptingDto.getTime());
							udmDataMongoModel.setTenantId(tenantId);
							udmDataMongoModel.setSiteId(siteId);
							udmDataMongoModel.setAgentId(LocalSdnId);
							udmDataMongoModel.setNfName(nfNameConncate[0]);
							udmDataModelRepository.save(udmDataMongoModel);
						} else {
							List<UdmDataMongoModel> udmDataMongoModels = udmDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (UdmDataMongoModel udmDataMongoModel : udmDataMongoModels) {
								udmDataMongoModel.setSbi(udmDataAcceptingDto.getSbi());
								udmDataMongoModel.setUdm(udmDataAcceptingDto.getUdm());
								udmDataMongoModel.setScp(udmDataAcceptingDto.getScp());
								udmDataMongoModel.setParameter(udmDataAcceptingDto.getParameter());
								udmDataMongoModel.setMax(udmDataAcceptingDto.getMax());
								udmDataMongoModel.setTime(udmDataAcceptingDto.getTime());
								udmDataMongoModel.setTenantId(tenantId);
								udmDataMongoModel.setSiteId(siteId);
								udmDataMongoModel.setAgentId(LocalSdnId);
								udmDataMongoModel.setNfName(nfNameConncate[0]);
								udmDataModelRepository.save(udmDataMongoModel);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void udmConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("udm")) {
				String[] nfNameConncate = nfName.split("\\.");

				List<UdmDataMongoModel> udmDataMongoModels = udmDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (UdmDataMongoModel udmDataMongoModel : udmDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();

					UdmDataAcceptingDto udmDataAcceptingDto = new UdmDataAcceptingDto();
					udmDataAcceptingDto.setSbi(udmDataMongoModel.getSbi());
					udmDataAcceptingDto.setUdm(udmDataMongoModel.getUdm());
					udmDataAcceptingDto.setScp(udmDataMongoModel.getScp());
					udmDataAcceptingDto.setParameter(udmDataMongoModel.getParameter());
					udmDataAcceptingDto.setMax(udmDataMongoModel.getMax());
					udmDataAcceptingDto.setTime(udmDataMongoModel.getTime());
					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								udmDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void upfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
	
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("upf")) {
					String[] nfNameConncate = nfName.split("\\.");
					try {
						UpfDataAcceptingDto upfDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), UpfDataAcceptingDto.class);
						if (upfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							UpfDataMongoModel upfDataMongoModel = new UpfDataMongoModel();
							upfDataMongoModel.setLogger(upfDataAcceptingDto.getLogger());
							upfDataMongoModel.setUpf(upfDataAcceptingDto.getUpf());
							upfDataMongoModel.setSmf(upfDataAcceptingDto.getSmf());
							upfDataMongoModel.setParameter(upfDataAcceptingDto.getParameter());
							upfDataMongoModel.setMax(upfDataAcceptingDto.getMax());
							upfDataMongoModel.setTime(upfDataAcceptingDto.getTime());
							upfDataMongoModel.setTenantId(tenantId);
							upfDataMongoModel.setSiteId(siteId);
							upfDataMongoModel.setAgentId(LocalSdnId);
							upfDataMongoModel.setNfName(nfNameConncate[0]);
							upfDataModelRepository.save(upfDataMongoModel);
						} else {
							List<UpfDataMongoModel> upfDataMongoModels = upfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (UpfDataMongoModel upfDataMongoModel : upfDataMongoModels) {
								upfDataMongoModel.setLogger(upfDataAcceptingDto.getLogger());
								upfDataMongoModel.setUpf(upfDataAcceptingDto.getUpf());
								upfDataMongoModel.setSmf(upfDataAcceptingDto.getSmf());
								upfDataMongoModel.setParameter(upfDataAcceptingDto.getParameter());
								upfDataMongoModel.setMax(upfDataAcceptingDto.getMax());
								upfDataMongoModel.setTime(upfDataAcceptingDto.getTime());
								upfDataMongoModel.setTenantId(tenantId);
								upfDataMongoModel.setSiteId(siteId);
								upfDataMongoModel.setAgentId(LocalSdnId);
								upfDataMongoModel.setNfName(nfNameConncate[0]);
								upfDataModelRepository.save(upfDataMongoModel);

							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void upfConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("upf")) {
				String[] nfNameConncate = nfName.split("\\.");

				List<UpfDataMongoModel> upfDataMongoModels = upfDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (UpfDataMongoModel upfDataMongoModel : upfDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					UpfSbiDataScpDto upfSbiDataScpDto = upfDataMongoModel.getUpf();
					UpfDataAcceptingDto upfDataAcceptingDto = new UpfDataAcceptingDto();
					upfDataAcceptingDto.setLogger(upfDataMongoModel.getLogger());
					upfDataAcceptingDto.setUpf(upfSbiDataScpDto);
					upfDataAcceptingDto.setSmf(upfDataMongoModel.getSmf());
					upfDataAcceptingDto.setParameter(upfDataMongoModel.getParameter());
					upfDataAcceptingDto.setMax(upfDataMongoModel.getMax());
					upfDataAcceptingDto.setTime(upfDataMongoModel.getTime());

					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								upfDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void smfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("smf")) {
					String[] nfNameConncate = nfName.split("\\.");

					try {
						SmfDataAcceptingDto smfDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), SmfDataAcceptingDto.class);
						if (smfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {
							SmfDataMongoModel smfDataMongoModel = new SmfDataMongoModel();
							smfDataMongoModel.setLogger(smfDataAcceptingDto.getLogger());
							smfDataMongoModel.setSbi(smfDataAcceptingDto.getSbi());
							smfDataMongoModel.setSmf(smfDataAcceptingDto.getSmf());
							smfDataMongoModel.setScp(smfDataAcceptingDto.getScp());
							smfDataMongoModel.setUpf(smfDataAcceptingDto.getUpf());
							smfDataMongoModel.setParameter(smfDataAcceptingDto.getParameter());
							smfDataMongoModel.setMax(smfDataAcceptingDto.getMax());
							smfDataMongoModel.setTime(smfDataAcceptingDto.getTime());
							smfDataMongoModel.setTenantId(tenantId);
							smfDataMongoModel.setSiteId(siteId);
							smfDataMongoModel.setAgentId(LocalSdnId);
							smfDataMongoModel.setNfName(nfNameConncate[0]);
							smfDataModelRepository.save(smfDataMongoModel);

						} else {
							List<SmfDataMongoModel> smfDataMongoModels = smfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (SmfDataMongoModel smfDataMongoModel : smfDataMongoModels) {
								smfDataMongoModel.setLogger(smfDataAcceptingDto.getLogger());
								smfDataMongoModel.setSbi(smfDataAcceptingDto.getSbi());
								smfDataMongoModel.setSmf(smfDataAcceptingDto.getSmf());
								smfDataMongoModel.setScp(smfDataAcceptingDto.getScp());
								smfDataMongoModel.setUpf(smfDataAcceptingDto.getUpf());
								smfDataMongoModel.setParameter(smfDataAcceptingDto.getParameter());
								smfDataMongoModel.setMax(smfDataAcceptingDto.getMax());
								smfDataMongoModel.setTime(smfDataAcceptingDto.getTime());
								smfDataMongoModel.setTenantId(tenantId);
								smfDataMongoModel.setSiteId(siteId);
								smfDataMongoModel.setAgentId(LocalSdnId);
								smfDataMongoModel.setNfName(nfNameConncate[0]);
								smfDataModelRepository.save(smfDataMongoModel);
							}

						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		});
	}

//	@Scheduled(fixedRate = 20000)
	@Override
	public void smfConfigurationWrite() {
//		DockerComposeDown();
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		File files = new File(filelistpath);
		String[] fileList = files.list();
		for (String nfName : fileList) {
			if (nfName.startsWith("smf")) {
				String[] nfNameConncate = nfName.split("\\.");
				List<SmfDataMongoModel> smfDataMongoModels = smfDataModelRepository
						.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
				for (SmfDataMongoModel smfDataMongoModel : smfDataMongoModels) {
					ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
					mapper.findAndRegisterModules();
					file_stat file_stat = smfDataMongoModel.getLogger();
					SbiData sbiData = smfDataMongoModel.getSbi();
					SmfAttributeDateModel smfAttributeDateModel = smfDataMongoModel.getSmf();
					NrfSbiDto sbiDataModel = smfDataMongoModel.getScp();
					UpfPfcpDto smfSbiDataModel = smfDataMongoModel.getUpf();

					SmfDataAcceptingDto smfDataAcceptingDto = new SmfDataAcceptingDto();
					smfDataAcceptingDto.setLogger(file_stat);
					smfDataAcceptingDto.setSbi(sbiData);
					smfDataAcceptingDto.setSmf(smfAttributeDateModel);
					smfDataAcceptingDto.setScp(sbiDataModel);
					smfDataAcceptingDto.setUpf(smfSbiDataModel);
					smfDataAcceptingDto.setParameter(smfDataMongoModel.getParameter());
					smfDataAcceptingDto.setMax(smfDataMongoModel.getMax());
					smfDataAcceptingDto.setTime(smfDataMongoModel.getTime());
					try {
						mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
								smfDataAcceptingDto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		});
//			DockerComposeUp();
	}

//	@Scheduled(fixedRate = 10000)
	@Override
	public void nssfConfigurationRead() {
		internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
			String LocalSdnId = deployedItem.getDeploymentId();
			// System.out.println("Agent ID: " + LocalSdnId );
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		if (tenantId != null && siteId != null) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			mapper = new ObjectMapper(new YAMLFactory());
			mapper.findAndRegisterModules();
			File files = new File(filelistpath);
			String[] fileList = files.list();
			for (String nfName : fileList) {
				if (nfName.startsWith("nssf")) {
					String[] nfNameConncate = nfName.split("\\.");

					try {
						NssfDataAcceptingDto nssfDataAcceptingDto = mapper.readValue(
								new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"), NssfDataAcceptingDto.class);
						if (nssfDataModelRepository
								.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]).isEmpty()) {

							NssfDataMongoModel nssfDataMongoModel = new NssfDataMongoModel();
							nssfDataMongoModel.setSbi(nssfDataAcceptingDto.getSbi());
							nssfDataMongoModel.setNssf(nssfDataAcceptingDto.getNssf());
							nssfDataMongoModel.setScp(nssfDataAcceptingDto.getScp());
							nssfDataMongoModel.setParameter(nssfDataAcceptingDto.getParameter());
							nssfDataMongoModel.setMax(nssfDataAcceptingDto.getMax());
							nssfDataMongoModel.setTime(nssfDataAcceptingDto.getTime());
							nssfDataMongoModel.setTenantId(tenantId);
							nssfDataMongoModel.setSiteId(siteId);
							nssfDataMongoModel.setAgentId(LocalSdnId);
							nssfDataMongoModel.setNfName(nfNameConncate[0]);
							nssfDataModelRepository.save(nssfDataMongoModel);
						} else {
							List<NssfDataMongoModel> nssfDataMongoModels = nssfDataModelRepository
									.findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
							for (NssfDataMongoModel nssfDataMongoModel : nssfDataMongoModels) {
								nssfDataMongoModel.setSbi(nssfDataAcceptingDto.getSbi());
								nssfDataMongoModel.setNssf(nssfDataAcceptingDto.getNssf());
								nssfDataMongoModel.setScp(nssfDataAcceptingDto.getScp());
								nssfDataMongoModel.setParameter(nssfDataAcceptingDto.getParameter());
								nssfDataMongoModel.setMax(nssfDataAcceptingDto.getMax());
								nssfDataMongoModel.setTime(nssfDataAcceptingDto.getTime());
								nssfDataMongoModel.setTenantId(tenantId);
								nssfDataMongoModel.setSiteId(siteId);
								nssfDataMongoModel.setAgentId(LocalSdnId);
								nssfDataMongoModel.setNfName(nfNameConncate[0]);
								nssfDataModelRepository.save(nssfDataMongoModel);
							}
						}

					} catch (Exception e) {
					}
				}
			}
		}
		});
	}

	@Override
	public void nssfConfigurationWrite() {
	    internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
	        // System.out.println("Agent ID: " + LocalSdnId );
	        String tenantId = deployedItem.getTenantName();
	        String siteId = deployedItem.getSiteName();
	        
	        File files = new File(filelistpath);
	        String[] fileList = files.list();
	        
	        for (String nfName : fileList) {
	            if (nfName.startsWith("nssf")) {
	                String[] nfNameConncate = nfName.split("\\.");
	                List<NssfDataMongoModel> nssfDataMongoModels = nssfDataModelRepository
	                        .findByTenantIdAndSiteIdAndNfName(tenantId, siteId, nfNameConncate[0]);
	                
	                for (NssfDataMongoModel nssfDataMongoModel : nssfDataMongoModels) {
	                    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	                    mapper.findAndRegisterModules();
	                    SbiData sbiData = nssfDataMongoModel.getSbi();
	                    NssfSbiDataDto nssfSbiDataDto = nssfDataMongoModel.getNssf();
	                    NrfSbiDto nrfSbiDto = nssfDataMongoModel.getScp();
	                    NssfDataAcceptingDto nssfDataAcceptingDto = new NssfDataAcceptingDto();
	                    nssfDataAcceptingDto.setSbi(sbiData);
	                    nssfDataAcceptingDto.setNssf(nssfSbiDataDto);
	                    nssfDataAcceptingDto.setScp(nrfSbiDto);
	                    nssfDataAcceptingDto.setParameter(nssfDataMongoModel.getParameter());
	                    nssfDataAcceptingDto.setMax(nssfDataMongoModel.getMax());
	                    nssfDataAcceptingDto.setTime(nssfDataMongoModel.getTime());

	                    try {
	                        mapper.writeValue(new File(filepathofReadYaml + nfNameConncate[0] + ".yaml"),
	                                nssfDataAcceptingDto);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    });
	}


	@Autowired
	ServiceofNetworkfunctionCompose serviceofNetworkfunctionCompose;

	@Override
	public void DockerComposeUp(String networking) {
		serviceofNetworkfunctionCompose.pullImageofMongo();
		serviceofNetworkfunctionCompose.niralosNrf();
		serviceofNetworkfunctionCompose.niralosScp();
		serviceofNetworkfunctionCompose.niralosUdr();
		serviceofNetworkfunctionCompose.niralosUdm();
		serviceofNetworkfunctionCompose.niralosAusf();
		serviceofNetworkfunctionCompose.niralosPcf();
		serviceofNetworkfunctionCompose.niralosBsf();
		serviceofNetworkfunctionCompose.niralosNssf();
		serviceofNetworkfunctionCompose.niralosAmf();
		serviceofNetworkfunctionCompose.niralosSmf();//
		serviceofNetworkfunctionCompose.niralosUpf(networking);

	}

	@Override
	public void DockerComposeDown() {
		serviceofNetworkfunctionCompose.niralosDeletionofContainer();

	}

}

//package com.other.app.Service.BackendServices.YamlConfiguration.Frontend.Impl;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.other.app.Dto.InternalData.SiteInformationDto;
//import com.other.app.Repository.YamlConfiguration.Frontend.AmfFrontendRepoModelDataRepo;
//import com.other.app.Repository.YamlConfiguration.Frontend.AusfFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.BsfFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.NrfFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.NssfFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.PcfFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.ScpFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.SmfFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.UdmFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.UdrFrontendDataModelRepository;
//import com.other.app.Repository.YamlConfiguration.Frontend.UpfFrontendDataModelRepository;
//import com.other.app.Service.BackendServices.InternalServices.Backend.InternalDataService;
//import com.other.app.Service.BackendServices.InternalServices.frontend.InternalDataFrontendService;
//import com.other.app.Service.BackendServices.YamlConfiguration.Frontend.ymlFrontendKafkaService;
//import com.other.app.YamlConfiguration.YmlModel.ListofNf;
//import com.other.app.YamlConfiguration.YmlModel.ReadDataReturn;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.AmfAttributeDateModel;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.AmfYmlModel;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.AmfYmlModelForMogoDb;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.DevAndPort;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.MccAndMnc;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.NetworkName;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.Nfsupport;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.PlmnSupport;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.Sbi;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.SbiData;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.SbiDataModel;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.Security;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.ServerAndClientModel;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.Sst;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.Tai;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.file_stat;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.guamiDataModel;
//import com.other.app.YamlConfiguration.YmlModel.AmfModels.t3512;
//import com.other.app.YamlConfiguration.YmlModel.AusfModel.AusfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.AusfModel.AusfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.AusfModel.AusfDataScpDto;
//import com.other.app.YamlConfiguration.YmlModel.BsfModel.BsfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.BsfModel.BsfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.NrfModel.NrfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.NrfModel.NrfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.NrfModel.SbiDataNrfDto;
//import com.other.app.YamlConfiguration.YmlModel.NssfModel.NssfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.NssfModel.NssfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.NssfModel.NssfSbiDataDto;
//import com.other.app.YamlConfiguration.YmlModel.ScpModel.NrfSbiDto;
//import com.other.app.YamlConfiguration.YmlModel.ScpModel.SbiDataScpDto;
//import com.other.app.YamlConfiguration.YmlModel.ScpModel.ScpDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.ScpModel.ScpDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.ScpModel.UpfPfcpDto;
//import com.other.app.YamlConfiguration.YmlModel.SmfModel.SmfAttributeDateModel;
//import com.other.app.YamlConfiguration.YmlModel.SmfModel.SmfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.SmfModel.SmfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.UdmModel.UdmDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.UdmModel.UdmDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.UdrModel.SbiDataUdrDto;
//import com.other.app.YamlConfiguration.YmlModel.UdrModel.UdrDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.UdrModel.UdrDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.pcfModel.PcfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.pcfModel.PcfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.pcfModel.SbiDataPcfDto;
//import com.other.app.YamlConfiguration.YmlModel.upfModel.UpfDataAcceptingDto;
//import com.other.app.YamlConfiguration.YmlModel.upfModel.UpfDataMongoModel;
//import com.other.app.YamlConfiguration.YmlModel.upfModel.UpfSbiDataScpDto;
//
//@Service
//public class ymlServiceKafkaImpl implements ymlFrontendKafkaService {
//	final Logger logger = LoggerFactory.getLogger(ymlServiceKafkaImpl.class);
//
//	@Autowired
//	AmfFrontendRepoModelDataRepo amfRepoModelDataRepo;
//	@Autowired
//	ScpFrontendDataModelRepository scpDataModelRepository;
//	@Autowired
//	PcfFrontendDataModelRepository pcfDataModelRepository;
//	@Autowired
//	AusfFrontendDataModelRepository ausfDataModelRepository;
//	@Autowired
//	UdrFrontendDataModelRepository udrDataModelRepository;
//	@Autowired
//	BsfFrontendDataModelRepository bsfDataModelRepository;
//	@Autowired
//	UdmFrontendDataModelRepository udmDataModelRepository;
//	@Autowired
//	SmfFrontendDataModelRepository smfDataModelRepository;
//	@Autowired
//	NssfFrontendDataModelRepository nssfDataModelRepository;
//	@Autowired
//	NrfFrontendDataModelRepository nrfDataModelRepository;
//	@Autowired
//	UpfFrontendDataModelRepository upfDataModelRepository;
//
//	private String filelistpath;
//
//	@Value("${filelistpath}")
//	public void setFilelistpath(String filelistpath) {
//		this.filelistpath = filelistpath;
//	}
//
////	@Autowired
////	@Qualifier("firstMongoTemplate") // Use @Qualifier to specify the bean name
////	private MongoTemplate firstMongoTemplate;
//	@Override
//	public ListofNf ListofNfName() {
//		ListofNf listofNfA = new ListofNf();
//		Map<String, List<String>> fileMap = new HashMap<>();
//
//		List<String> list = new ArrayList<>();
//		File files = new File(filelistpath);
//		String[] fileList = files.list();
//		if (fileList != null) {
//			for (String name : fileList) {
//				String[] nfNameConncate = name.split("\\.");
//				String prefix = nfNameConncate[0];
//				String fileType = (prefix.startsWith("amf") ? "amf"
//						: prefix.startsWith("smf") ? "smf"
//								: prefix.startsWith("nrf") ? "nrf"
//										: prefix.startsWith("nssf") ? "nssf"
//												: prefix.startsWith("udm") ? "udm"
//														: prefix.startsWith("upf") ? "upf"
//																: prefix.startsWith("scp") ? "scp"
//																		: prefix.startsWith("pcf") ? "pcf"
//																				: prefix.startsWith("ausf") ? "ausf"
//																						: prefix.startsWith("udr")
//																								? "udr"
//																								: prefix.startsWith(
//																										"bsf") ? "bsf"
//																												: "other");
//				// Add the file to the corresponding list in the map
//				fileMap.computeIfAbsent(fileType, k -> new ArrayList<>()).add(prefix);
//			}
//		}
//		// Set the categorized file names in the ListofNf object
//		listofNfA.setAmfName(fileMap.getOrDefault("amf", new ArrayList<>()));
//		listofNfA.setSmfName(fileMap.getOrDefault("smf", new ArrayList<>()));
//		listofNfA.setNrfName(fileMap.getOrDefault("nrf", new ArrayList<>()));
//		listofNfA.setNssfName(fileMap.getOrDefault("nssf", new ArrayList<>()));
//		listofNfA.setUdmName(fileMap.getOrDefault("udm", new ArrayList<>()));
//		listofNfA.setUpfName(fileMap.getOrDefault("upf", new ArrayList<>()));
//		listofNfA.setScpName(fileMap.getOrDefault("scp", new ArrayList<>()));
//		listofNfA.setPcfName(fileMap.getOrDefault("pcf", new ArrayList<>()));
//		listofNfA.setAusfName(fileMap.getOrDefault("ausf", new ArrayList<>()));
//		listofNfA.setUdrName(fileMap.getOrDefault("udr", new ArrayList<>()));
//		listofNfA.setBsfName(fileMap.getOrDefault("bsf", new ArrayList<>()));
//		return listofNfA;
//	}
//
//	@Autowired
//	InternalDataService internalDataService;
//	private String filepathofReadYaml;
//
//	@Value("${filelistpath.for.readYaml}")
//	public void setFilepathofReadYaml(String filepathofReadYaml) {
//		this.filepathofReadYaml = filepathofReadYaml;
//	}
//
//	@Autowired
//	InternalDataFrontendService internalDataFrontendService;
//	@Autowired
//	private KafkaTemplate<String, Object> kafkaTemplate;
//
//	private static final String RESPONSE_TOPIC = "response_topic";
//	private static final String REQUEST_TOPIC = "my_topic";
//	private static final int STATIC_PARTITION_ID1 = 0;
//	private static final String REQUEST_TOPIC1 = "my_topic1";
//	private static final String REQUEST_TOPIC2 = "my_topic2";
//	private static final String REQUEST_TOPIC3 = "my_topic3";
//	private static final String REQUEST_TOPIC4 = "my_topic4";
//	private static final String REQUEST_TOPIC5 = "my_topic5";
//	private static final String REQUEST_TOPIC6 = "my_topic6";
//	private static final String REQUEST_TOPIC7 = "my_topic7";
//	private static final String REQUEST_TOPIC8 = "my_topic8";
//	private static final String REQUEST_TOPIC9 = "my_topic9";
//	private static final String REQUEST_TOPIC10 = "my_topic10";
//	private static final String REQUEST_TOPIC13 = "my_topic13";
//
//	@Override
////	@KafkaListener(topics = REQUEST_TOPIC1, groupId = "my_group_id")
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC1, partitions = {
//			"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//	public void ListofNfNameDataRead(String nfName) {
//		System.out.println("Received message from Kafka: " + nfName);
//
//		SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
//
//		// Extract tenantId and siteId from the fetched deployed item
//		String agentId = "nua-jnmtmh2isegutiefssrvsd";
//		String tenentName = "niral";
//		String siteName = "IIITB";
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
////		List<AmfYmlModelForMogoDb> amfList = new ArrayList<>();	
////		List<ScpDataMongoModel> scpList = new ArrayList<>();
//		List<ScpDataMongoModel> scpList = new ArrayList<>();
//		List<AmfYmlModelForMogoDb> amfList = new ArrayList<>();
//		List<PcfDataMongoModel> pcfList = new ArrayList<>();
//		List<AusfDataMongoModel> ausfList = new ArrayList<>();
//		List<UdrDataMongoModel> udrList = new ArrayList<>();
//		List<BsfDataMongoModel> bsfList = new ArrayList<>();
//		List<UpfDataMongoModel> upfList = new ArrayList<>();
//		List<UdmDataMongoModel> udmList = new ArrayList<>();
//		List<SmfDataMongoModel> smfList = new ArrayList<>();
//		List<NssfDataMongoModel> nssfList = new ArrayList<>();
//		List<NrfDataMongoModel> nrfList = new ArrayList<>();
//
//		if (nfName.startsWith("amf")) {
//			try {
//
//				if (tenentName != null && siteName != null) {
//				}
//
//				AmfYmlModel order = mapper.readValue(new File(filepathofReadYaml + nfName + ".yaml"),
//						AmfYmlModel.class);
//				AmfYmlModelForMogoDb amfYmlModelForMogoDb = new AmfYmlModelForMogoDb();
//				amfYmlModelForMogoDb.setLogger(order.getLogger());
//				amfYmlModelForMogoDb.setSbi(order.getSbi());
//				amfYmlModelForMogoDb.setAmf(order.getAmf());
//				amfYmlModelForMogoDb.setScp(order.getScp());
//				amfYmlModelForMogoDb.setParameter(order.getParameter());
//				amfYmlModelForMogoDb.setMax(order.getMax());
//				amfYmlModelForMogoDb.setUsrsctp(order.getUsrsctp());
//				amfYmlModelForMogoDb.setTime(order.getTime());
//				amfYmlModelForMogoDb.setAgentId(agentId);
//				amfYmlModelForMogoDb.setTenantId(tenentName);
//				amfYmlModelForMogoDb.setSiteId(siteName);
//				amfYmlModelForMogoDb.setNfName(nfName);
//				amfList.add(amfYmlModelForMogoDb);
//			} catch (IOException e) {
//				e.printStackTrace();
//				logger.info("error is here : " + e);
//			}
//		}
//
//		else if (nfName.startsWith("scp")) {
//
//			if (tenentName != null && siteName != null) {
//				try {
//					ScpDataAcceptingDto scpDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), ScpDataAcceptingDto.class);
//					ScpDataMongoModel scpDataMongoModel = new ScpDataMongoModel();
//					scpDataMongoModel.setDb_uri(scpDataAcceptingDto.getDb_uri());
//					scpDataMongoModel.setSbi(scpDataAcceptingDto.getSbi());
//					scpDataMongoModel.setScp(scpDataAcceptingDto.getScp());
//					scpDataMongoModel.setNrf(scpDataAcceptingDto.getNrf());
//					scpDataMongoModel.setParameter(scpDataAcceptingDto.getParameter());
//					scpDataMongoModel.setMax(scpDataAcceptingDto.getMax());
//					scpDataMongoModel.setTenantId(tenentName);
//					scpDataMongoModel.setSiteId(siteName);
//					scpDataMongoModel.setAgentId(agentId);
//					scpDataMongoModel.setNfName(nfName);
//					scpList.add(scpDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//
//		} else if (nfName.startsWith("pcf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					PcfDataAcceptingDto pcfDataAcceptingDtos = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), PcfDataAcceptingDto.class);
//					PcfDataMongoModel pcfDataMongoModel = new PcfDataMongoModel();
//					pcfDataMongoModel.setDb_uri(pcfDataAcceptingDtos.getDb_uri());
//					pcfDataMongoModel.setSbi(pcfDataAcceptingDtos.getSbi());
//					pcfDataMongoModel.setPcf(pcfDataAcceptingDtos.getPcf());
//					pcfDataMongoModel.setScp(pcfDataAcceptingDtos.getScp());
//					pcfDataMongoModel.setParameter(pcfDataAcceptingDtos.getParameter());
//					pcfDataMongoModel.setMax(pcfDataAcceptingDtos.getMax());
//					pcfDataMongoModel.setTime(pcfDataAcceptingDtos.getTime());
//
//					pcfDataMongoModel.setTenantId(tenentName);
//					pcfDataMongoModel.setSiteId(siteName);
//					pcfDataMongoModel.setAgentId(agentId);
//					pcfDataMongoModel.setNfName(nfName);
//					pcfList.add(pcfDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("ausf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					AusfDataAcceptingDto ausfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), AusfDataAcceptingDto.class);
//					AusfDataMongoModel ausfDataMongoModel = new AusfDataMongoModel();
//					ausfDataMongoModel.setSbi(ausfDataAcceptingDto.getSbi());
//					ausfDataMongoModel.setAusf(ausfDataAcceptingDto.getAusf());
//					ausfDataMongoModel.setScp(ausfDataAcceptingDto.getScp());
//					ausfDataMongoModel.setParameter(ausfDataAcceptingDto.getParameter());
//					ausfDataMongoModel.setMax(ausfDataAcceptingDto.getMax());
//					ausfDataMongoModel.setTime(ausfDataAcceptingDto.getTime());
//					ausfDataMongoModel.setTenantId(tenentName);
//					ausfDataMongoModel.setSiteId(siteName);
//					ausfDataMongoModel.setAgentId(agentId);
//					ausfDataMongoModel.setNfName(nfName);
//					ausfList.add(ausfDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("bsf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					BsfDataAcceptingDto bsfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), BsfDataAcceptingDto.class);
//					BsfDataMongoModel bsfDataMongoModel = new BsfDataMongoModel();
//					bsfDataMongoModel.setSbi(bsfDataAcceptingDto.getSbi());
//					bsfDataMongoModel.setBsf(bsfDataAcceptingDto.getBsf());
//					bsfDataMongoModel.setScp(bsfDataAcceptingDto.getScp());
//					bsfDataMongoModel.setParameter(bsfDataAcceptingDto.getParameter());
//					bsfDataMongoModel.setMax(bsfDataAcceptingDto.getMax());
//					bsfDataMongoModel.setTime(bsfDataAcceptingDto.getTime());
//					bsfDataMongoModel.setTenantId(tenentName);
//					bsfDataMongoModel.setSiteId(siteName);
//					bsfDataMongoModel.setAgentId(agentId);
//					bsfDataMongoModel.setNfName(nfName);
//					bsfList.add(bsfDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("udr")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					UdrDataAcceptingDto udrDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), UdrDataAcceptingDto.class);
//					UdrDataMongoModel udrDataMongoModel = new UdrDataMongoModel();
//					udrDataMongoModel.setDb_uri(udrDataAcceptingDto.getDb_uri());
//					udrDataMongoModel.setSbi(udrDataAcceptingDto.getSbi());
//					udrDataMongoModel.setUdr(udrDataAcceptingDto.getUdr());
//					udrDataMongoModel.setScp(udrDataAcceptingDto.getScp());
//					udrDataMongoModel.setParameter(udrDataAcceptingDto.getParameter());
//					udrDataMongoModel.setMax(udrDataAcceptingDto.getMax());
//					udrDataMongoModel.setTime(udrDataAcceptingDto.getTime());
//					udrDataMongoModel.setTenantId(tenentName);
//					udrDataMongoModel.setSiteId(siteName);
//					udrDataMongoModel.setAgentId(agentId);
//					udrDataMongoModel.setNfName(nfName);
//					udrList.add(udrDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("nrf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					NrfDataAcceptingDto nrfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), NrfDataAcceptingDto.class);
//					NrfDataMongoModel nrfDataMongoModel = new NrfDataMongoModel();
//					nrfDataMongoModel.setSbi(nrfDataAcceptingDto.getSbi());
//					nrfDataMongoModel.setNrf(nrfDataAcceptingDto.getNrf());
//					nrfDataMongoModel.setScp(nrfDataAcceptingDto.getScp());
//					nrfDataMongoModel.setParameter(nrfDataAcceptingDto.getParameter());
//					nrfDataMongoModel.setMax(nrfDataAcceptingDto.getMax());
//					nrfDataMongoModel.setTime(nrfDataAcceptingDto.getTime());
//					nrfDataMongoModel.setTenantId(tenentName);
//					nrfDataMongoModel.setSiteId(siteName);
//					nrfDataMongoModel.setAgentId(agentId);
//					nrfDataMongoModel.setNfName(nfName);
//					nrfList.add(nrfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}
//
//		else if (nfName.startsWith("udm")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					UdmDataAcceptingDto udmDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), UdmDataAcceptingDto.class);
//					UdmDataMongoModel udmDataMongoModel = new UdmDataMongoModel();
//					udmDataMongoModel.setSbi(udmDataAcceptingDto.getSbi());
//					udmDataMongoModel.setUdm(udmDataAcceptingDto.getUdm());
//					udmDataMongoModel.setScp(udmDataAcceptingDto.getScp());
//					udmDataMongoModel.setParameter(udmDataAcceptingDto.getParameter());
//					udmDataMongoModel.setMax(udmDataAcceptingDto.getMax());
//					udmDataMongoModel.setTime(udmDataAcceptingDto.getTime());
//					udmDataMongoModel.setTenantId(tenentName);
//					udmDataMongoModel.setSiteId(siteName);
//					udmDataMongoModel.setAgentId(agentId);
//					udmDataMongoModel.setNfName(nfName);
//					udmList.add(udmDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} else if (nfName.startsWith("upf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					UpfDataAcceptingDto upfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), UpfDataAcceptingDto.class);
//					UpfDataMongoModel upfDataMongoModel = new UpfDataMongoModel();
//					upfDataMongoModel.setLogger(upfDataAcceptingDto.getLogger());
//					upfDataMongoModel.setUpf(upfDataAcceptingDto.getUpf());
//					upfDataMongoModel.setSmf(upfDataAcceptingDto.getSmf());
//					upfDataMongoModel.setParameter(upfDataAcceptingDto.getParameter());
//					upfDataMongoModel.setMax(upfDataAcceptingDto.getMax());
//					upfDataMongoModel.setTime(upfDataAcceptingDto.getTime());
//					upfDataMongoModel.setTenantId(tenentName);
//					upfDataMongoModel.setSiteId(siteName);
//					upfDataMongoModel.setAgentId(agentId);
//					upfDataMongoModel.setNfName(nfName);
//					upfList.add(upfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} else if (nfName.startsWith("smf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					SmfDataAcceptingDto smfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), SmfDataAcceptingDto.class);
//					SmfDataMongoModel smfDataMongoModel = new SmfDataMongoModel();
//					smfDataMongoModel.setLogger(smfDataAcceptingDto.getLogger());
//					smfDataMongoModel.setSbi(smfDataAcceptingDto.getSbi());
//					smfDataMongoModel.setSmf(smfDataAcceptingDto.getSmf());
//					smfDataMongoModel.setScp(smfDataAcceptingDto.getScp());
//					smfDataMongoModel.setUpf(smfDataAcceptingDto.getUpf());
//					smfDataMongoModel.setParameter(smfDataAcceptingDto.getParameter());
//					smfDataMongoModel.setMax(smfDataAcceptingDto.getMax());
//					smfDataMongoModel.setTime(smfDataAcceptingDto.getTime());
//					smfDataMongoModel.setTenantId(tenentName);
//					smfDataMongoModel.setSiteId(siteName);
//					smfDataMongoModel.setAgentId(agentId);
//					smfDataMongoModel.setNfName(nfName);
//					smfList.add(smfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} else if (nfName.startsWith("nssf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					NssfDataAcceptingDto nssfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), NssfDataAcceptingDto.class);
//
//					NssfDataMongoModel nssfDataMongoModel = new NssfDataMongoModel();
//					nssfDataMongoModel.setSbi(nssfDataAcceptingDto.getSbi());
//					nssfDataMongoModel.setNssf(nssfDataAcceptingDto.getNssf());
//					nssfDataMongoModel.setScp(nssfDataAcceptingDto.getScp());
//					nssfDataMongoModel.setParameter(nssfDataAcceptingDto.getParameter());
//					nssfDataMongoModel.setMax(nssfDataAcceptingDto.getMax());
//					nssfDataMongoModel.setTime(nssfDataAcceptingDto.getTime());
//					nssfDataMongoModel.setTenantId(tenentName);
//					nssfDataMongoModel.setSiteId(siteName);
//					nssfDataMongoModel.setAgentId(agentId);
//					nssfDataMongoModel.setNfName(nfName);
//					nssfList.add(nssfDataMongoModel);
//
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}
//
//		ReadDataReturn readDataReturn = new ReadDataReturn(amfList, scpList, pcfList, ausfList, udrList, bsfList,
//				upfList, udmList, smfList, nssfList, nrfList);
//		ObjectMapper objectMapper = new ObjectMapper();
//		String responseMessage;
//		try {
//			responseMessage = objectMapper.writeValueAsString(readDataReturn);
//			System.out.println("Sending message to Kafka...");
//			kafkaTemplate.send(RESPONSE_TOPIC,STATIC_PARTITION_ID1,null, responseMessage).addCallback(
//					success -> System.out.println("Message sent successfully to Kafka: " + responseMessage),
//					failure -> System.err.println("Failed to send message to Kafka: " + failure.getMessage()));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Send the message to Kafka
//
//	}
//
//	@Override
//	public ReadDataReturn ListofNfNameDataRead1(String nfName) {
//		SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
//
//		// Extract tenantId and siteId from the fetched deployed item
//		String agentId = deployedItem.getDeploymentId();
//		String tenentName = deployedItem.getTenantName();
//		String siteName = deployedItem.getSiteName();
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
////		List<AmfYmlModelForMogoDb> amfList = new ArrayList<>();	
////		List<ScpDataMongoModel> scpList = new ArrayList<>();
//		List<ScpDataMongoModel> scpList = new ArrayList<>();
//		List<AmfYmlModelForMogoDb> amfList = new ArrayList<>();
//		List<PcfDataMongoModel> pcfList = new ArrayList<>();
//		List<AusfDataMongoModel> ausfList = new ArrayList<>();
//		List<UdrDataMongoModel> udrList = new ArrayList<>();
//		List<BsfDataMongoModel> bsfList = new ArrayList<>();
//		List<UpfDataMongoModel> upfList = new ArrayList<>();
//		List<UdmDataMongoModel> udmList = new ArrayList<>();
//		List<SmfDataMongoModel> smfList = new ArrayList<>();
//		List<NssfDataMongoModel> nssfList = new ArrayList<>();
//		List<NrfDataMongoModel> nrfList = new ArrayList<>();
//
//		if (nfName.startsWith("amf")) {
//			try {
//
//				if (tenentName != null && siteName != null) {
//				}
//
//				AmfYmlModel order = mapper.readValue(new File(filepathofReadYaml + nfName + ".yaml"),
//						AmfYmlModel.class);
//				AmfYmlModelForMogoDb amfYmlModelForMogoDb = new AmfYmlModelForMogoDb();
//				amfYmlModelForMogoDb.setLogger(order.getLogger());
//				amfYmlModelForMogoDb.setSbi(order.getSbi());
//				amfYmlModelForMogoDb.setAmf(order.getAmf());
//				amfYmlModelForMogoDb.setScp(order.getScp());
//				amfYmlModelForMogoDb.setParameter(order.getParameter());
//				amfYmlModelForMogoDb.setMax(order.getMax());
//				amfYmlModelForMogoDb.setUsrsctp(order.getUsrsctp());
//				amfYmlModelForMogoDb.setTime(order.getTime());
//				amfYmlModelForMogoDb.setAgentId(agentId);
//				amfYmlModelForMogoDb.setTenantId(tenentName);
//				amfYmlModelForMogoDb.setSiteId(siteName);
//				amfYmlModelForMogoDb.setNfName(nfName);
//				amfList.add(amfYmlModelForMogoDb);
//			} catch (IOException e) {
//				e.printStackTrace();
//				logger.info("error is here : " + e);
//			}
//		}
//
//		else if (nfName.startsWith("scp")) {
//
//			if (tenentName != null && siteName != null) {
//				try {
//					ScpDataAcceptingDto scpDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), ScpDataAcceptingDto.class);
//					ScpDataMongoModel scpDataMongoModel = new ScpDataMongoModel();
//					scpDataMongoModel.setDb_uri(scpDataAcceptingDto.getDb_uri());
//					scpDataMongoModel.setSbi(scpDataAcceptingDto.getSbi());
//					scpDataMongoModel.setScp(scpDataAcceptingDto.getScp());
//					scpDataMongoModel.setNrf(scpDataAcceptingDto.getNrf());
//					scpDataMongoModel.setParameter(scpDataAcceptingDto.getParameter());
//					scpDataMongoModel.setMax(scpDataAcceptingDto.getMax());
//					scpDataMongoModel.setTenantId(tenentName);
//					scpDataMongoModel.setSiteId(siteName);
//					scpDataMongoModel.setAgentId(agentId);
//					scpDataMongoModel.setNfName(nfName);
//					scpList.add(scpDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//
//		} else if (nfName.startsWith("pcf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					PcfDataAcceptingDto pcfDataAcceptingDtos = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), PcfDataAcceptingDto.class);
//					PcfDataMongoModel pcfDataMongoModel = new PcfDataMongoModel();
//					pcfDataMongoModel.setDb_uri(pcfDataAcceptingDtos.getDb_uri());
//					pcfDataMongoModel.setSbi(pcfDataAcceptingDtos.getSbi());
//					pcfDataMongoModel.setPcf(pcfDataAcceptingDtos.getPcf());
//					pcfDataMongoModel.setScp(pcfDataAcceptingDtos.getScp());
//					pcfDataMongoModel.setParameter(pcfDataAcceptingDtos.getParameter());
//					pcfDataMongoModel.setMax(pcfDataAcceptingDtos.getMax());
//					pcfDataMongoModel.setTime(pcfDataAcceptingDtos.getTime());
//
//					pcfDataMongoModel.setTenantId(tenentName);
//					pcfDataMongoModel.setSiteId(siteName);
//					pcfDataMongoModel.setAgentId(agentId);
//					pcfDataMongoModel.setNfName(nfName);
//					pcfList.add(pcfDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("ausf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					AusfDataAcceptingDto ausfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), AusfDataAcceptingDto.class);
//					AusfDataMongoModel ausfDataMongoModel = new AusfDataMongoModel();
//					ausfDataMongoModel.setSbi(ausfDataAcceptingDto.getSbi());
//					ausfDataMongoModel.setAusf(ausfDataAcceptingDto.getAusf());
//					ausfDataMongoModel.setScp(ausfDataAcceptingDto.getScp());
//					ausfDataMongoModel.setParameter(ausfDataAcceptingDto.getParameter());
//					ausfDataMongoModel.setMax(ausfDataAcceptingDto.getMax());
//					ausfDataMongoModel.setTime(ausfDataAcceptingDto.getTime());
//					ausfDataMongoModel.setTenantId(tenentName);
//					ausfDataMongoModel.setSiteId(siteName);
//					ausfDataMongoModel.setAgentId(agentId);
//					ausfDataMongoModel.setNfName(nfName);
//					ausfList.add(ausfDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("bsf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					BsfDataAcceptingDto bsfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), BsfDataAcceptingDto.class);
//					BsfDataMongoModel bsfDataMongoModel = new BsfDataMongoModel();
//					bsfDataMongoModel.setSbi(bsfDataAcceptingDto.getSbi());
//					bsfDataMongoModel.setBsf(bsfDataAcceptingDto.getBsf());
//					bsfDataMongoModel.setScp(bsfDataAcceptingDto.getScp());
//					bsfDataMongoModel.setParameter(bsfDataAcceptingDto.getParameter());
//					bsfDataMongoModel.setMax(bsfDataAcceptingDto.getMax());
//					bsfDataMongoModel.setTime(bsfDataAcceptingDto.getTime());
//					bsfDataMongoModel.setTenantId(tenentName);
//					bsfDataMongoModel.setSiteId(siteName);
//					bsfDataMongoModel.setAgentId(agentId);
//					bsfDataMongoModel.setNfName(nfName);
//					bsfList.add(bsfDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("udr")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					UdrDataAcceptingDto udrDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), UdrDataAcceptingDto.class);
//					UdrDataMongoModel udrDataMongoModel = new UdrDataMongoModel();
//					udrDataMongoModel.setDb_uri(udrDataAcceptingDto.getDb_uri());
//					udrDataMongoModel.setSbi(udrDataAcceptingDto.getSbi());
//					udrDataMongoModel.setUdr(udrDataAcceptingDto.getUdr());
//					udrDataMongoModel.setScp(udrDataAcceptingDto.getScp());
//					udrDataMongoModel.setParameter(udrDataAcceptingDto.getParameter());
//					udrDataMongoModel.setMax(udrDataAcceptingDto.getMax());
//					udrDataMongoModel.setTime(udrDataAcceptingDto.getTime());
//					udrDataMongoModel.setTenantId(tenentName);
//					udrDataMongoModel.setSiteId(siteName);
//					udrDataMongoModel.setAgentId(agentId);
//					udrDataMongoModel.setNfName(nfName);
//					udrList.add(udrDataMongoModel);
//				} catch (Exception e) {
//				}
//			}
//		} else if (nfName.startsWith("nrf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					NrfDataAcceptingDto nrfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), NrfDataAcceptingDto.class);
//					NrfDataMongoModel nrfDataMongoModel = new NrfDataMongoModel();
//					nrfDataMongoModel.setSbi(nrfDataAcceptingDto.getSbi());
//					nrfDataMongoModel.setNrf(nrfDataAcceptingDto.getNrf());
//					nrfDataMongoModel.setScp(nrfDataAcceptingDto.getScp());
//					nrfDataMongoModel.setParameter(nrfDataAcceptingDto.getParameter());
//					nrfDataMongoModel.setMax(nrfDataAcceptingDto.getMax());
//					nrfDataMongoModel.setTime(nrfDataAcceptingDto.getTime());
//					nrfDataMongoModel.setTenantId(tenentName);
//					nrfDataMongoModel.setSiteId(siteName);
//					nrfDataMongoModel.setAgentId(agentId);
//					nrfDataMongoModel.setNfName(nfName);
//					nrfList.add(nrfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}
//
//		else if (nfName.startsWith("udm")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					UdmDataAcceptingDto udmDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), UdmDataAcceptingDto.class);
//					UdmDataMongoModel udmDataMongoModel = new UdmDataMongoModel();
//					udmDataMongoModel.setSbi(udmDataAcceptingDto.getSbi());
//					udmDataMongoModel.setUdm(udmDataAcceptingDto.getUdm());
//					udmDataMongoModel.setScp(udmDataAcceptingDto.getScp());
//					udmDataMongoModel.setParameter(udmDataAcceptingDto.getParameter());
//					udmDataMongoModel.setMax(udmDataAcceptingDto.getMax());
//					udmDataMongoModel.setTime(udmDataAcceptingDto.getTime());
//					udmDataMongoModel.setTenantId(tenentName);
//					udmDataMongoModel.setSiteId(siteName);
//					udmDataMongoModel.setAgentId(agentId);
//					udmDataMongoModel.setNfName(nfName);
//					udmList.add(udmDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} else if (nfName.startsWith("upf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					UpfDataAcceptingDto upfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), UpfDataAcceptingDto.class);
//					UpfDataMongoModel upfDataMongoModel = new UpfDataMongoModel();
//					upfDataMongoModel.setLogger(upfDataAcceptingDto.getLogger());
//					upfDataMongoModel.setUpf(upfDataAcceptingDto.getUpf());
//					upfDataMongoModel.setSmf(upfDataAcceptingDto.getSmf());
//					upfDataMongoModel.setParameter(upfDataAcceptingDto.getParameter());
//					upfDataMongoModel.setMax(upfDataAcceptingDto.getMax());
//					upfDataMongoModel.setTime(upfDataAcceptingDto.getTime());
//					upfDataMongoModel.setTenantId(tenentName);
//					upfDataMongoModel.setSiteId(siteName);
//					upfDataMongoModel.setAgentId(agentId);
//					upfDataMongoModel.setNfName(nfName);
//					upfList.add(upfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} else if (nfName.startsWith("smf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					SmfDataAcceptingDto smfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), SmfDataAcceptingDto.class);
//					SmfDataMongoModel smfDataMongoModel = new SmfDataMongoModel();
//					smfDataMongoModel.setLogger(smfDataAcceptingDto.getLogger());
//					smfDataMongoModel.setSbi(smfDataAcceptingDto.getSbi());
//					smfDataMongoModel.setSmf(smfDataAcceptingDto.getSmf());
//					smfDataMongoModel.setScp(smfDataAcceptingDto.getScp());
//					smfDataMongoModel.setUpf(smfDataAcceptingDto.getUpf());
//					smfDataMongoModel.setParameter(smfDataAcceptingDto.getParameter());
//					smfDataMongoModel.setMax(smfDataAcceptingDto.getMax());
//					smfDataMongoModel.setTime(smfDataAcceptingDto.getTime());
//					smfDataMongoModel.setTenantId(tenentName);
//					smfDataMongoModel.setSiteId(siteName);
//					smfDataMongoModel.setAgentId(agentId);
//					smfDataMongoModel.setNfName(nfName);
//					smfList.add(smfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} else if (nfName.startsWith("nssf")) {
//			if (tenentName != null && siteName != null) {
//				try {
//					NssfDataAcceptingDto nssfDataAcceptingDto = mapper
//							.readValue(new File(filepathofReadYaml + nfName + ".yaml"), NssfDataAcceptingDto.class);
//
//					NssfDataMongoModel nssfDataMongoModel = new NssfDataMongoModel();
//					nssfDataMongoModel.setSbi(nssfDataAcceptingDto.getSbi());
//					nssfDataMongoModel.setNssf(nssfDataAcceptingDto.getNssf());
//					nssfDataMongoModel.setScp(nssfDataAcceptingDto.getScp());
//					nssfDataMongoModel.setParameter(nssfDataAcceptingDto.getParameter());
//					nssfDataMongoModel.setMax(nssfDataAcceptingDto.getMax());
//					nssfDataMongoModel.setTime(nssfDataAcceptingDto.getTime());
//					nssfDataMongoModel.setTenantId(tenentName);
//					nssfDataMongoModel.setSiteId(siteName);
//					nssfDataMongoModel.setAgentId(agentId);
//					nssfDataMongoModel.setNfName(nfName);
//					nssfList.add(nssfDataMongoModel);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		}
//		return new ReadDataReturn(amfList, scpList, pcfList, ausfList, udrList, bsfList, upfList, udmList, smfList,
//				nssfList, nrfList);
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC, partitions = {
//			"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//	public String postDataofYamlFile(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//			@Payload AmfYmlModelForMogoDb amfYmlModelForMogoDb) {
//
////	        if (nfName == null) {
////	            nfName = "amf"; // Provide a default value or handle the missing key appropriately
////	        }
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//
//		file_stat fileStat = amfYmlModelForMogoDb.getLogger();
//		fileStat.setFile_stat(fileStat.getFile_stat());
//
//		SbiData sbiDatas = amfYmlModelForMogoDb.getSbi();
//		ServerAndClientModel client = sbiDatas.getClient();
//		ServerAndClientModel server = sbiDatas.getServer();
//		sbiDatas.setClient(client);
//		sbiDatas.setServer(server);
//
//		AmfAttributeDateModel amf = amfYmlModelForMogoDb.getAmf();
//		DevAndPort sbi = amf.getSbi();
//		DevAndPort ngap = amf.getNgap();
//		DevAndPort metrics = amf.getMetrics();
//		List<guamiDataModel> guami = amf.getGuami();
//		List<Tai> tai = amf.getTai();
//
//		List<PlmnSupport> plmnSupport = amf.getPlmn_support();
//		for (PlmnSupport string : plmnSupport) {
//			MccAndMnc mccAndMnc = string.getPlmn_id();
//			List<Sst> ssts = string.getS_nssai();
//		}
//
//		Security security = amf.getSecurity();
//		List<String> integrityOrder = security.getIntegrity_order();
//		List<String> cipheringOrder = security.getCiphering_order();
//		security.setCiphering_order(cipheringOrder);
//		security.setIntegrity_order(integrityOrder);
//
//		NetworkName networkName = amf.getNetwork_name();
//		String amfName = amf.getAmf_name();
//		String serviceId = amf.getService_id();
//		Nfsupport nfsupport = amf.getNf_support();
//		AmfAttributeDateModel amfAttributeDateModel = new AmfAttributeDateModel(sbi, ngap, metrics, guami, tai,
//				plmnSupport, security, networkName, amfName, serviceId, nfsupport);
//
//		SbiDataModel scp = amfYmlModelForMogoDb.getScp();
//		List<Sbi> sbii = scp.sbi;
//		scp.setSbi(sbii);
//		t3512 time = amfYmlModelForMogoDb.getTime();
//
//		AmfYmlModel amfYmlModel = new AmfYmlModel();
//		amfYmlModel.setLogger(fileStat);
//		amfYmlModel.setSbi(sbiDatas);
//		amfYmlModel.setAmf(amfAttributeDateModel);
//		amfYmlModel.setScp(scp);
//		amfYmlModel.setParameter(amfYmlModelForMogoDb.getParameter());
//		amfYmlModel.setMax(amfYmlModelForMogoDb.getMax());
//		amfYmlModel.setUsrsctp(amfYmlModelForMogoDb.getUsrsctp());
//		amfYmlModel.setTime(time);
//
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), amfYmlModel);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "Error: " + e.getMessage();
//		}
//
//		return "Done";
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC4, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInScp(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload ScpDataMongoModel scpDataMongoModel) {
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//
//		SbiData sbiData = scpDataMongoModel.getSbi();
//		sbiData.setClient(sbiData.getClient());
//		sbiData.setServer(sbiData.getServer());
//		SbiDataScpDto sbiDataScpDto = scpDataMongoModel.getScp();
//		NrfSbiDto nrfSbiDto = scpDataMongoModel.getNrf();
//
//		ScpDataAcceptingDto acceptingDto = new ScpDataAcceptingDto();
//		acceptingDto.setDb_uri(scpDataMongoModel.getDb_uri());
//		acceptingDto.setSbi(sbiData);
//		acceptingDto.setScp(sbiDataScpDto);
//		acceptingDto.setNrf(nrfSbiDto);
//		acceptingDto.setParameter(scpDataMongoModel.getParameter());
//		acceptingDto.setMax(scpDataMongoModel.getMax());
//		acceptingDto.setTime(scpDataMongoModel.getTime());
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), acceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC5, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInpcf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload PcfDataMongoModel pcfDataMongoModel) {
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//		SbiData sbiData = pcfDataMongoModel.getSbi();
//		sbiData.setClient(sbiData.getClient());
//		sbiData.setServer(sbiData.getServer());
//		SbiDataPcfDto sbiDataPcfDto = pcfDataMongoModel.getPcf();
//		NrfSbiDto nrfSbiDto = pcfDataMongoModel.getScp();
//
//		PcfDataAcceptingDto pcfDataAcceptingDto = new PcfDataAcceptingDto();
//		pcfDataAcceptingDto.setDb_uri(pcfDataMongoModel.getDb_uri());
//		pcfDataAcceptingDto.setSbi(sbiData);
//		pcfDataAcceptingDto.setPcf(sbiDataPcfDto);
//		pcfDataAcceptingDto.setScp(nrfSbiDto);
//		pcfDataAcceptingDto.setParameter(pcfDataMongoModel.getParameter());
//		pcfDataAcceptingDto.setMax(pcfDataMongoModel.getMax());
//		pcfDataAcceptingDto.setTime(pcfDataMongoModel.getTime());
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), pcfDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC6, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInAusf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload AusfDataMongoModel ausfDataMongoModel) {
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//		SbiData sbiData = ausfDataMongoModel.getSbi();
//		AusfDataScpDto ausfDataScpDto = ausfDataMongoModel.getAusf();
//		NrfSbiDto nrfSbiDto = ausfDataMongoModel.getScp();
//
//		AusfDataAcceptingDto ausfDataAcceptingDto = new AusfDataAcceptingDto();
//		ausfDataAcceptingDto.setSbi(sbiData);
//		ausfDataAcceptingDto.setAusf(ausfDataScpDto);
//		ausfDataAcceptingDto.setScp(nrfSbiDto);
//		ausfDataAcceptingDto.setParameter(ausfDataMongoModel.getParameter());
//		ausfDataAcceptingDto.setMax(ausfDataMongoModel.getMax());
//		ausfDataAcceptingDto.setTime(ausfDataMongoModel.getTime());
//
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), ausfDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC7, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInBsf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload BsfDataMongoModel bsfDataMongoModel) {
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//
//		SbiData sbiData = bsfDataMongoModel.getSbi();
//		AusfDataScpDto ausfDataScpDto = bsfDataMongoModel.getBsf();
//		NrfSbiDto nrfSbiDto = bsfDataMongoModel.getScp();
//
//		BsfDataAcceptingDto bsfDataAcceptingDto = new BsfDataAcceptingDto();
//		bsfDataAcceptingDto.setSbi(sbiData);
//		bsfDataAcceptingDto.setBsf(ausfDataScpDto);
//		bsfDataAcceptingDto.setScp(nrfSbiDto);
//		bsfDataAcceptingDto.setParameter(bsfDataMongoModel.getParameter());
//		bsfDataAcceptingDto.setMax(bsfDataMongoModel.getMax());
//		bsfDataAcceptingDto.setTime(bsfDataMongoModel.getTime());
//
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), bsfDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC8, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInUdr(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload UdrDataMongoModel udrDataMongoModel) {
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//		SbiData sbiData = udrDataMongoModel.getSbi();
//		SbiDataUdrDto dataScpDto = udrDataMongoModel.getUdr();
//		NrfSbiDto nrfSbiDto = udrDataMongoModel.getScp();
//
//		UdrDataAcceptingDto udrDataAcceptingDto = new UdrDataAcceptingDto();
//		udrDataAcceptingDto.setDb_uri(udrDataMongoModel.getDb_uri());
//		udrDataAcceptingDto.setSbi(sbiData);
//		udrDataAcceptingDto.setUdr(dataScpDto);
//		udrDataAcceptingDto.setScp(nrfSbiDto);
//		udrDataAcceptingDto.setParameter(udrDataMongoModel.getParameter());
//		udrDataAcceptingDto.setMax(udrDataMongoModel.getMax());
//		udrDataAcceptingDto.setTime(udrDataMongoModel.getTime());
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), udrDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC13, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInUpf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload UpfDataMongoModel upfDataMongoModel) {
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//		UpfSbiDataScpDto upfSbiDataScpDto = upfDataMongoModel.getUpf();
//		UpfDataAcceptingDto upfDataAcceptingDto = new UpfDataAcceptingDto();
//		upfDataAcceptingDto.setLogger(upfDataMongoModel.getLogger());
//		upfDataAcceptingDto.setUpf(upfSbiDataScpDto);
//		upfDataAcceptingDto.setSmf(upfDataMongoModel.getSmf());
//		upfDataAcceptingDto.setParameter(upfDataMongoModel.getParameter());
//		upfDataAcceptingDto.setMax(upfDataMongoModel.getMax());
//		upfDataAcceptingDto.setTime(upfDataMongoModel.getTime());
//
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), upfDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC3, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInSmf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload SmfDataMongoModel smfDataMongoModel) {
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//		file_stat file_stat = smfDataMongoModel.getLogger();
//		SbiData sbiData = smfDataMongoModel.getSbi();
//		SmfAttributeDateModel smfAttributeDateModel = smfDataMongoModel.getSmf();
//		NrfSbiDto sbiDataModel = smfDataMongoModel.getScp();
//		UpfPfcpDto smfSbiDataModel = smfDataMongoModel.getUpf();
//
//		SmfDataAcceptingDto smfDataAcceptingDto = new SmfDataAcceptingDto();
//		smfDataAcceptingDto.setLogger(file_stat);
//		smfDataAcceptingDto.setSbi(sbiData);
//		smfDataAcceptingDto.setSmf(smfAttributeDateModel);
//		smfDataAcceptingDto.setScp(sbiDataModel);
//		smfDataAcceptingDto.setUpf(smfSbiDataModel);
//		smfDataAcceptingDto.setParameter(smfDataMongoModel.getParameter());
//		smfDataAcceptingDto.setMax(smfDataMongoModel.getMax());
//		smfDataAcceptingDto.setTime(smfDataMongoModel.getTime());
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), smfDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//     return "done";
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC9, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInUdm(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload UdmDataMongoModel udmDataMongoModel) {
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//
//		UdmDataAcceptingDto udmDataAcceptingDto = new UdmDataAcceptingDto();
//		udmDataAcceptingDto.setSbi(udmDataMongoModel.getSbi());
//		udmDataAcceptingDto.setUdm(udmDataMongoModel.getUdm());
//		udmDataAcceptingDto.setScp(udmDataMongoModel.getScp());
//		udmDataAcceptingDto.setParameter(udmDataMongoModel.getParameter());
//		udmDataAcceptingDto.setMax(udmDataMongoModel.getMax());
//		udmDataAcceptingDto.setTime(udmDataMongoModel.getTime());
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), udmDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC2, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInNssf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload NssfDataMongoModel nssfDataMongoModel) {
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//		SbiData sbiData = nssfDataMongoModel.getSbi();
//		NssfSbiDataDto nssfSbiDataDto = nssfDataMongoModel.getNssf();
//		NrfSbiDto nrfSbiDto = nssfDataMongoModel.getScp();
//		NssfDataAcceptingDto nssfDataAcceptingDto = new NssfDataAcceptingDto();
//		nssfDataAcceptingDto.setSbi(sbiData);
//		nssfDataAcceptingDto.setNssf(nssfSbiDataDto);
//		nssfDataAcceptingDto.setScp(nrfSbiDto);
//		nssfDataAcceptingDto.setParameter(nssfDataMongoModel.getParameter());
//		nssfDataAcceptingDto.setMax(nssfDataMongoModel.getMax());
//		nssfDataAcceptingDto.setTime(nssfDataMongoModel.getTime());
//
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), nssfDataAcceptingDto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "done";
//
//	}
//
//	@KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC10, partitions = {
//	"0" }) }, groupId = "my_group_id", containerFactory = "kafkaListenerContainerFactory")
//public String postDataofYamlFileInNrf(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String nfName,
//	@Payload NrfDataMongoModel nrfDataMongoModel) {
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		mapper.findAndRegisterModules();
//
//		SbiData sbiData = nrfDataMongoModel.getSbi();
//		SbiDataNrfDto sbiDataNrfDto = nrfDataMongoModel.getNrf();
//		NrfSbiDto nrfSbiDto = nrfDataMongoModel.getScp();
//
//		NrfDataAcceptingDto nrfDataAcceptingDto = new NrfDataAcceptingDto();
//		nrfDataAcceptingDto.setSbi(sbiData);
//		nrfDataAcceptingDto.setNrf(sbiDataNrfDto);
//		nrfDataAcceptingDto.setScp(nrfSbiDto);
//		nrfDataAcceptingDto.setParameter(nrfDataMongoModel.getParameter());
//		nrfDataAcceptingDto.setMax(nrfDataMongoModel.getMax());
//		nrfDataAcceptingDto.setTime(nrfDataMongoModel.getTime());
//		try {
//			mapper.writeValue(new File(filepathofReadYaml + nfName + ".yaml"), nrfDataAcceptingDto);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "done";
//
//	}
//}

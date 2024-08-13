package com.other.app.NiralosFiveGCore.BackendServices.JsonDump.Impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.other.app.NiralosFiveGCore.BackendServices.JsonDump.JsonDumpService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.Impl.PfcpInfoServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.JsonDumpDto.JsonDumpDto;
import com.other.app.NiralosFiveGCore.Repository.AlertManager.AlarmManagerModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpfErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpgErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpfErrorDeltaModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpfErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorDeltaModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgFrontendRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgInterfaceDataRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbFailureListRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbRegistrationDeregistrationFailureCountRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbStatistics.GnbStatsRepository;
import com.other.app.NiralosFiveGCore.Repository.Graph.GnbGraphRepository;
import com.other.app.NiralosFiveGCore.Repository.Graph.UeGraphRepository;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.NiralosFiveGCore.Repository.KeycloakTokenConfiguration.AccessTokenRepo;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.LiveDataRepository;
import com.other.app.NiralosFiveGCore.Repository.LogManagement.LogModelMongoRepository;
import com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend.PfcpInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend.PfcpSessioEstablishmentModificationDelationFailReasonListRepository;
import com.other.app.NiralosFiveGCore.Repository.Subscriber.MongoDbRepository;
import com.other.app.NiralosFiveGCore.Repository.Throughput.ThroughputRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeploymentRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeviceRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.GnbFrontendRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.NetworkTopologyRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SdnModelRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SubZoneRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.ZoneRepository;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeHistoryRepository;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UePduCauseInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeRegistraionDeregistrationFailureCountRepository;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.UeStatsRepository1;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend.TopUserRepository;
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
import com.other.app.NiralosFiveGCore.model.GnbStatsModel;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;
import com.other.app.NiralosFiveGCore.model.ThroughputModel;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;
import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorDeltaModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorGraphModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorDeltaModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorGraphModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgInterface;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbFailureList;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbInfoModel;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;
import com.other.app.NiralosFiveGCore.model.Graph.GnbGraphModel;
import com.other.app.NiralosFiveGCore.model.Graph.UeGraphModel;
import com.other.app.NiralosFiveGCore.model.LogManager.LogModelMongo;
import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpInfo;
import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpSessioEstablishmentModificationDelationFailReasonList;
import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;
import com.other.app.NiralosFiveGCore.model.Topology.DeploymentModel;
import com.other.app.NiralosFiveGCore.model.Topology.DeviceModel;
import com.other.app.NiralosFiveGCore.model.Topology.GnBModel;
import com.other.app.NiralosFiveGCore.model.Topology.SdnModel;
import com.other.app.NiralosFiveGCore.model.Topology.SubZoneModel;
import com.other.app.NiralosFiveGCore.model.Topology.ZoneModel;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UePduCauseInfoModel;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeRegistraionDeregistrationFailureCount;
import com.other.app.NiralosFiveGCore.model.UeStats.Frontend.TopUserModel;
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

import io.netty.handler.timeout.TimeoutException;

@Service
@EnableScheduling
@Configuration

public class JsonDumpServiceImpl implements JsonDumpService {
	@Autowired
	GnbInfoRepository gnbInfoRepository;

	@Autowired
	GnbFailureListRepository gnbFailureListRepository;

	@Autowired
	GnbRegistrationDeregistrationFailureCountRepository gnbRegDeRegRepository;
	@Autowired
	UeHistoryRepository ueHistoryRepository;
	@Autowired
	UePduCauseInfoRepository uePduCauseInfoRepository;
	@Autowired
	UeRegistraionDeregistrationFailureCountRepository ueRegistraionDeregistrationFailureCountRepository;
	@Autowired
	PfcpInfoRepository pfcpInfoRepository;
	@Autowired
	PfcpSessioEstablishmentModificationDelationFailReasonListRepository pfcpSessioRepository;
	@Autowired
	ThroughputRepository throughputRepository;
	@Autowired
	AlarmManagerModelRepository alarmManagerModelRepository;

	@Autowired
	UeStatsRepository1 ueStatsRepository1;
	@Autowired
	TopUserRepository topUserRepository;
	@Autowired
	DeploymentRepository deploymentRepository;
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	GnbFrontendRepository gnbFrontendRepository;
	@Autowired
	NetworkTopologyRepository networkTopologyRepository;
	@Autowired
	SdnModelRepository sdnModelRepository;
	@Autowired
	SubZoneRepository subZoneRepository;
	@Autowired
	ZoneRepository zoneRepository;
	@Autowired
	GnbStatsRepository gnbStatsRepository;
	@Autowired
	LiveDataRepository liveDataRepository;
	@Autowired
	LogModelMongoRepository logModelMongoRepository;
	@Autowired
	MongoDbRepository mongoDbRepository;
	@Autowired
	AmfRepoModelDataRepo amfRepoModelDataRepo;
	@Autowired
	AusfDataModelRepository ausfDataModelRepository;
	@Autowired
	BsfDataModelRepository bsfDataModelRepository;
	@Autowired
	NrfDataModelRepository nrfDataModelRepository;
	@Autowired
	NssfDataModelRepository nssfDataModelRepository;
	@Autowired
	PcfDataModelRepository pcfDataModelRepository;
	@Autowired
	ScpDataModelRepository scpDataModelRepository;
	@Autowired
	SmfDataModelRepository smfDataModelRepository;
	@Autowired
	UdmDataModelRepository udmDataModelRepository;
	@Autowired
	UdrDataModelRepository udrDataModelRepository;
	@Autowired
	UpfDataModelRepository upfDataModelRepository;
	// data plane
	@Autowired
	UpfErrorDeltaModelRepository upfErrorDeltaModelRepository;
	@Autowired
	UpfErrorGraphModelRepository upfErrorGraphModelRepository;
	@Autowired
	UpfErrorRepository upfErrorRepository;
	@Autowired
	UpgErrorDeltaModelRepository upgErrorDeltaModelRepository;
	@Autowired
	UpgErrorGraphModelRepository upgErrorGraphModelRepository;
	@Autowired
	UpgErrorRepository upgErrorRepository;
	@Autowired
	UpgInterfaceDataRepository upgInterfaceDataRepository;
	@Autowired
	UpgFrontendRepository upgFrontendRepository;
	// graph
	@Autowired
	GnbGraphRepository gnbGraphRepository;
	@Autowired
	UeGraphRepository ueGraphRepository;
	@Autowired
	AccessTokenRepo accessTokenRepo;
	CommonServiceImpl commonServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(PfcpInfoServiceImpl.class);

	List<GnbGraphModel> getgnbGraphModelInfo() {
		return gnbGraphRepository.findAll();

	}

	List<UeGraphModel> getueGraphModelInfo() {
		return ueGraphRepository.findAll();

	}

	List<UpfErrorDeltaModel> getupfErrorDeltaModelInfo() {
		return upfErrorDeltaModelRepository.findAll();
	}

	List<UpfErrorGraphModel> getupfErrorGraphModelInfo() {
		return upfErrorGraphModelRepository.findAll();
	}

	List<UpfErrorModel> getupfErrorModelInfo() {
		return upfErrorRepository.findAll();
	}

	List<UpgErrorDeltaModel> getupgErrorDeltaModelInfo() {
		return upgErrorDeltaModelRepository.findAll();
	}

	List<UpgErrorGraphModel> getupgErrorGraphModelInfo() {
		return upgErrorGraphModelRepository.findAll();

	}

	List<UpgErrorModel> getupgErrorModelInfo() {
		return upgErrorRepository.findAll();

	}

	List<UpgInterface> getupgInterfaceInfo() {
		return upgInterfaceDataRepository.findAll();

	}

	List<UpgModel> getupgModelInfo() {
		return upgFrontendRepository.findAll();

	}

	List<AmfYmlModelForMogoDb> getamfYmlmodelInfo() {
		return amfRepoModelDataRepo.findAll();

	}

	List<AusfDataMongoModel> getausfDataYmlmodelInfo() {
		return ausfDataModelRepository.findAll();

	}

	List<BsfDataMongoModel> getbsfDataMongoModelInfo() {
		return bsfDataModelRepository.findAll();

	}

	List<NrfDataMongoModel> getnrfDataMongoModelInfo() {
		return nrfDataModelRepository.findAll();

	}

	List<NssfDataMongoModel> getnssfDataMongoModelInfo() {
		return nssfDataModelRepository.findAll();

	}

	List<PcfDataMongoModel> getpcfDataMongoModelInfo() {
		return pcfDataModelRepository.findAll();

	}

	List<ScpDataMongoModel> getscpDataMongoModelInfo() {
		return scpDataModelRepository.findAll();

	}

	List<SmfDataMongoModel> getsmfDataMongoModelInfo() {
		return smfDataModelRepository.findAll();

	}

	List<UdmDataMongoModel> getudmDataMongoModelInfo() {
		return udmDataModelRepository.findAll();

	}

	List<UdrDataMongoModel> getudrDataMongoModelInfo() {
		return udrDataModelRepository.findAll();

	}

	List<UpfDataMongoModel> getupfDataMongoModelInfo() {
		return upfDataModelRepository.findAll();

	}

	List<Subscribers> getsubscriberInfo() {
		return mongoDbRepository.findAll();

	}

	List<LogModelMongo> getlogModelMongoInfo() {
		return logModelMongoRepository.findAll();

	}

	List<LiveDataModel> getliveDataInfo() {
		return liveDataRepository.findAll();

	}

	private List<GnbStatsModel> getgnbStatsModelInfo() {
		return gnbStatsRepository.findAll();

	}

	private List<DeploymentModel> getdeploymentInfo() {
		return deploymentRepository.findAll();

	}

	private List<DeviceModel> getdeviceInfo() {
		return deviceRepository.findAll();

	}

	private List<GnBModel> getgnbModelInfo() {
		return gnbFrontendRepository.findAll();

	}

	private List<NetworkTopologyModel> getnetworkTopologyModelInfo() {
		return networkTopologyRepository.findAll();

	}

	private List<SdnModel> getsdnModelInfo() {
		return sdnModelRepository.findAll();

	}

	private List<SubZoneModel> getsubZoneModelInfo() {
		return subZoneRepository.findAll();

	}

	private List<ZoneModel> getzoneModelInfo() {
		return zoneRepository.findAll();

	}

	public List<GnbInfoModel> getGnbInfoData() {
		return gnbInfoRepository.findAll();
	}

	public List<GnbFailureList> getGnbFailureListData() {
		return gnbFailureListRepository.findAll();
	}

	public List<GnbRegistrationDeregistrationFailureCount> getGnbRegDeRegData() {
		return gnbRegDeRegRepository.findAll();
	}

	private List<UeHistoryModel> getUeHistory() {
		return ueHistoryRepository.findAll();
	}

	private List<UePduCauseInfoModel> getUePduCauseInfo() {
		return uePduCauseInfoRepository.findAll();
	}

	private List<UeRegistraionDeregistrationFailureCount> getUeRegDeregFailCount() {
		return ueRegistraionDeregistrationFailureCountRepository.findAll();
	}

	private List<PfcpInfo> getpfcpInfo() {
		return pfcpInfoRepository.findAll();
	}

	private List<PfcpSessioEstablishmentModificationDelationFailReasonList> getpfcpSession() {
		return pfcpSessioRepository.findAll();
	}

	private List<ThroughputModel> getthroughputInfo() {
		return throughputRepository.findAll();

	}

	private List<AlarmModel> getAlarmInfo() {
		return alarmManagerModelRepository.findAll();
	}

	private List<UeStatsModel1> getUeStats() {
		return ueStatsRepository1.findAll();

	}

	private List<TopUserModel> getTopUser() {
		return topUserRepository.findAll();

	}

	@Autowired
	InternalDataRepository internalFiveGDataRepository;

	String UriProtocol = "http://";
	String alertManagerServicePort = "8098";
	String fivegCoreServicePort = "8088";

//	@Scheduled(fixedRate = 3000)
	@Override
	public void JsonDump() {

		WebClient webForStoringDatabase = WebClient.builder()
				.baseUrl(UriProtocol + internalFiveGDataRepository.searchThecontrollerIp() + ":" + fivegCoreServicePort)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		// Fetch data from all repositories
		List<GnbInfoModel> gnbInfoData = getGnbInfoData();
		List<GnbFailureList> gnbFailureListData = getGnbFailureListData();
		List<GnbRegistrationDeregistrationFailureCount> gnbRegDeRegData = getGnbRegDeRegData();
		List<UeHistoryModel> ueHistoryInfoData = getUeHistory();
		List<UePduCauseInfoModel> uePduCauseInfoData = getUePduCauseInfo();
		List<UeRegistraionDeregistrationFailureCount> UeRegDeregFailCountInfoData = getUeRegDeregFailCount();
		List<PfcpInfo> pfcpInfoData = getpfcpInfo();
		List<PfcpSessioEstablishmentModificationDelationFailReasonList> pfcpSessionData = getpfcpSession();
		List<ThroughputModel> throughputInfoData = getthroughputInfo();
		List<UeStatsModel1> UeStatsData = getUeStats();
		List<TopUserModel> topUserData = getTopUser();
		List<DeploymentModel> deploymentInfoData = getdeploymentInfo();
		List<DeviceModel> deviceInfoData = getdeviceInfo();
		List<GnBModel> gnbModelInfoData = getgnbModelInfo();
		List<NetworkTopologyModel> networkTopologyModelInfoData = getnetworkTopologyModelInfo();
		List<SdnModel> sdnModelInfoData = getsdnModelInfo();
		List<SubZoneModel> subZoneModelInfoData = getsubZoneModelInfo();
		List<ZoneModel> zoneModelInfoData = getzoneModelInfo();
		List<GnbStatsModel> gnbStatsModelInfoData = getgnbStatsModelInfo();
		List<LiveDataModel> liveDataInfoData = getliveDataInfo();
//		List<LogModelMongo> logModelMongoInfoData = getlogModelMongoInfo();
		List<Subscribers> subscriberInfoData = getsubscriberInfo();
//		List<AmfYmlModelForMogoDb> amfYmlmodelInfoData = getamfYmlmodelInfo();
//		List<AusfDataMongoModel> ausfDataYmlmodelInfoData = getausfDataYmlmodelInfo();
//		List<BsfDataMongoModel> bsfDataMongoModelInfoData = getbsfDataMongoModelInfo();
//		List<NrfDataMongoModel> nrfDataMongoModelInfoData = getnrfDataMongoModelInfo();
//		List<NssfDataMongoModel> nssfDataMongoModelInfoData = getnssfDataMongoModelInfo();
//		List<PcfDataMongoModel> pcfDataMongoModelInfoData = getpcfDataMongoModelInfo();
//		List<ScpDataMongoModel> scpDataMongoModelInfoData = getscpDataMongoModelInfo();
//		List<SmfDataMongoModel> smfDataMongoModelInfoData = getsmfDataMongoModelInfo();
//		List<UdmDataMongoModel> udmDataMongoModelInfoData = getudmDataMongoModelInfo();
//		List<UdrDataMongoModel> udrDataMongoModelInfoData = getudrDataMongoModelInfo();
//		List<UpfDataMongoModel> upfDataMongoModelInfoData = getupfDataMongoModelInfo();
		// dataplane
		List<UpfErrorDeltaModel> upfErrorDeltaModelInfoData = getupfErrorDeltaModelInfo();
		List<UpfErrorGraphModel> upfErrorGraphModelInfoData = getupfErrorGraphModelInfo();
		List<UpfErrorModel> upfErrorModelInfoData = getupfErrorModelInfo();
		List<UpgErrorDeltaModel> upgErrorDeltaModelInfoData = getupgErrorDeltaModelInfo();
		List<UpgErrorGraphModel> upgErrorGraphModelInfoData = getupgErrorGraphModelInfo();
		List<UpgErrorModel> upgErrorModelInfoData = getupgErrorModelInfo();
		List<UpgInterface> upgInterfaceInfoData = getupgInterfaceInfo();
		List<UpgModel> upgModelInfoData = getupgModelInfo();
		// graph
		List<GnbGraphModel> gnbGraphModelInfoData = getgnbGraphModelInfo();
		List<UeGraphModel> ueGraphModelInfoData = getueGraphModelInfo();
		// Create AgentDto and set the data
		JsonDumpDto agentDto = new JsonDumpDto();
		agentDto.setGnbInfo(gnbInfoData);
		agentDto.setGnbList(gnbFailureListData);
		agentDto.setGnbdereg(gnbRegDeRegData);
		agentDto.setUeHistory(ueHistoryInfoData);
		agentDto.setUePduCauseInfo(uePduCauseInfoData);
		agentDto.setUeRegDeregFailCount(UeRegDeregFailCountInfoData);
		agentDto.setPfcpInfo(pfcpInfoData);
		agentDto.setPfcpSession(pfcpSessionData);
		agentDto.setThroughput(throughputInfoData);
		agentDto.setTopUser(topUserData);
		agentDto.setUeStats(UeStatsData);
		agentDto.setDeploymentInfo(deploymentInfoData);
		agentDto.setDeviceInfo(deviceInfoData);
		agentDto.setGnbModelInfo(gnbModelInfoData);
		agentDto.setNetworkTopologyModelInfo(networkTopologyModelInfoData);
		agentDto.setSdnModelInfo(sdnModelInfoData);
		agentDto.setSubZoneModelInfo(subZoneModelInfoData);
		agentDto.setZoneModelInfo(zoneModelInfoData);
		agentDto.setGnbStatsModelInfo(gnbStatsModelInfoData);
		agentDto.setLiveDataInfo(liveDataInfoData);
//		agentDto.setLogModelMongoInfo(logModelMongoInfoData);
		agentDto.setSubscriberInfo(subscriberInfoData);
//		agentDto.setAmfYmlmodelInfo(amfYmlmodelInfoData);
//		agentDto.setAusfDataYmlmodelInfo(ausfDataYmlmodelInfoData);
//		agentDto.setBsfDataMongoModelInfo(bsfDataMongoModelInfoData);
//		agentDto.setNrfDataMongoModelInfo(nrfDataMongoModelInfoData);
//		agentDto.setNssfDataMongoModelInfo(nssfDataMongoModelInfoData);
//		agentDto.setPcfDataMongoModelInfo(pcfDataMongoModelInfoData);
//		agentDto.setScpDataMongoModelInfo(scpDataMongoModelInfoData);
//		agentDto.setSmfDataMongoModelInfo(smfDataMongoModelInfoData);
//		agentDto.setUdmDataMongoModelInfo(udmDataMongoModelInfoData);
//		agentDto.setUdrDataMongoModelInfo(udrDataMongoModelInfoData);
//		agentDto.setUpfDataMongoModelInfo(upfDataMongoModelInfoData);
//		//dataplane
		agentDto.setUpfErrorDeltaModelInfo(upfErrorDeltaModelInfoData);
		agentDto.setUpfErrorGraphModelInfo(upfErrorGraphModelInfoData);
		agentDto.setUpfErrorModelInfo(upfErrorModelInfoData);
		agentDto.setUpgErrorDeltaModelInfo(upgErrorDeltaModelInfoData);
		agentDto.setUpgErrorGraphModelInfo(upgErrorGraphModelInfoData);
		agentDto.setUpgErrorModelInfo(upgErrorModelInfoData);
		agentDto.setUpgInterfaceInfo(upgInterfaceInfoData);
		agentDto.setUpgModelInfo(upgModelInfoData);
		agentDto.setGnbGraphModelInfo(gnbGraphModelInfoData);
		agentDto.setUeGraphModelInfo(ueGraphModelInfoData);

		try {
			webForStoringDatabase.post().uri("/api/gnb/savedata").contentType(MediaType.APPLICATION_JSON)
					.headers(h -> h.setBearerAuth(accessTokenRepo.getAccessTokenFromDb())) // Set bearer token
					.bodyValue(agentDto).retrieve().bodyToMono(String.class).timeout(Duration.ofSeconds(5)).block();
		} catch (WebClientResponseException e) {
			logger.info("Error while making a request to the Niral Controller. HTTP Status: {} Response: {}"
					+ e.getRawStatusCode());
		} catch (TimeoutException e) {
			logger.info("Timeout while making a request to the Niral Controller." + e);
		} catch (Exception e) {
			logger.info("An unexpected error occurred during GnbListSync." + e);
		}
		this.AlertJsonDump();
	}

	@Override
	public void AlertJsonDump() {
		WebClient webForStoringDatabaseAlert = WebClient.builder()
				.baseUrl(UriProtocol + internalFiveGDataRepository.searchThecontrollerIp() + ":"
						+ alertManagerServicePort)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		List<AlarmModel> getAlarmInfoData = getAlarmInfo();
		JsonDumpDto agentDto = new JsonDumpDto();
		agentDto.setAlarm(getAlarmInfoData);
		try {
			webForStoringDatabaseAlert.post().uri("/api/alert/savedataAlert").contentType(MediaType.APPLICATION_JSON)
					.headers(h -> h.setBearerAuth(accessTokenRepo.getAccessTokenFromDb())) // Set bearer token
					.bodyValue(agentDto).retrieve().bodyToMono(String.class).timeout(Duration.ofSeconds(5)).block();
		} catch (WebClientResponseException e) {
			logger.info("Error while making a request to the Niral Controller HTTP Status Response "
					+ e.getRawStatusCode());
		} catch (TimeoutException e) {
			logger.info("Alert Json Dump Timeout while making a request to the Niral Controller" + e);
		} catch (Exception e) {
			logger.info("An unexpected error occurred during GnbListSync " + e);
		}
	}
}

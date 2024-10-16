package com.other.app.NiralosFiveGCore.Dto.JsonDumpDto;

import java.util.List;

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

public class JsonDumpDto {
	private List<GnbInfoModel> gnbInfo;
	private List<GnbFailureList> gnbList;
	private List<GnbRegistrationDeregistrationFailureCount> gnbdereg;
	private List<UeHistoryModel> ueHistory;
	private List<UePduCauseInfoModel> uePduCauseInfo;
	private List<UeRegistraionDeregistrationFailureCount> UeRegDeregFailCount;
	private List<PfcpInfo> pfcpInfo;
	private List<PfcpSessioEstablishmentModificationDelationFailReasonList> pfcpSession;
	private List<ThroughputModel> throughput;
	private List<AlarmModel> alarm;
	private List<UeStatsModel1> UeStats;
	private List<TopUserModel> topUser;
	private List<DeploymentModel> deploymentInfo;
	private List<DeviceModel> deviceInfo;
	private List<GnBModel> gnbModelInfo;
	private List<SdnModel> sdnModelInfo;
	private List<SubZoneModel> subZoneModelInfo;
	private List<ZoneModel> zoneModelInfo;
	private List<NetworkTopologyModel> networkTopologyModelInfo;
	private List<GnbStatsModel> gnbStatsModelInfo;
	private List<LiveDataModel> liveDataInfo;
//	private List<LogModelMongo> logModelMongoInfo;
	private List<Subscribers> subscriberInfo;
//	private List<AmfYmlModelForMogoDb> amfYmlmodelInfo;
//	private List<AusfDataMongoModel> ausfDataYmlmodelInfo;
//	private List<BsfDataMongoModel> bsfDataMongoModelInfo;
//	private List<NrfDataMongoModel> nrfDataMongoModelInfo;
//	private List<NssfDataMongoModel> nssfDataMongoModelInfo;
//	private List<PcfDataMongoModel> pcfDataMongoModelInfo;
//	private List<ScpDataMongoModel> scpDataMongoModelInfo;
//	private List<SmfDataMongoModel> smfDataMongoModelInfo;
//	private List<UdmDataMongoModel> udmDataMongoModelInfo;
//	private List<UdrDataMongoModel> udrDataMongoModelInfo;
//	private List<UpfDataMongoModel> upfDataMongoModelInfo;
//	//dataplane
	private List<UpfErrorDeltaModel> upfErrorDeltaModelInfo;
	private List<UpfErrorGraphModel> upfErrorGraphModelInfo;
	private List<UpfErrorModel> upfErrorModelInfo;
	private List<UpgErrorDeltaModel> upgErrorDeltaModelInfo;
	private List<UpgErrorGraphModel> upgErrorGraphModelInfo;
	private List<UpgErrorModel> upgErrorModelInfo;
	private List<UpgInterface> upgInterfaceInfo;
	private List<UpgModel> upgModelInfo;
	//graph
	private List<GnbGraphModel> gnbGraphModelInfo;
	private List<UeGraphModel> ueGraphModelInfo;
	

	public List<GnbGraphModel> getGnbGraphModelInfo() {
		return gnbGraphModelInfo;
	}

	public void setGnbGraphModelInfo(List<GnbGraphModel> gnbGraphModelInfo) {
		this.gnbGraphModelInfo = gnbGraphModelInfo;
	}

	public List<UeGraphModel> getUeGraphModelInfo() {
		return ueGraphModelInfo;
	}

	public void setUeGraphModelInfo(List<UeGraphModel> ueGraphModelInfo) {
		this.ueGraphModelInfo = ueGraphModelInfo;
	}

	public List<UpfErrorDeltaModel> getUpfErrorDeltaModelInfo() {
		return upfErrorDeltaModelInfo;
	}

	public void setUpfErrorDeltaModelInfo(List<UpfErrorDeltaModel> upfErrorDeltaModelInfo) {
		this.upfErrorDeltaModelInfo = upfErrorDeltaModelInfo;
	}

	public List<UpfErrorGraphModel> getUpfErrorGraphModelInfo() {
		return upfErrorGraphModelInfo;
	}

	public void setUpfErrorGraphModelInfo(List<UpfErrorGraphModel> upfErrorGraphModelInfo) {
		this.upfErrorGraphModelInfo = upfErrorGraphModelInfo;
	}

	public List<UpfErrorModel> getUpfErrorModelInfo() {
		return upfErrorModelInfo;
	}

	public void setUpfErrorModelInfo(List<UpfErrorModel> upfErrorModelInfo) {
		this.upfErrorModelInfo = upfErrorModelInfo;
	}

	public List<UpgErrorDeltaModel> getUpgErrorDeltaModelInfo() {
		return upgErrorDeltaModelInfo;
	}

	public void setUpgErrorDeltaModelInfo(List<UpgErrorDeltaModel> upgErrorDeltaModelInfo) {
		this.upgErrorDeltaModelInfo = upgErrorDeltaModelInfo;
	}

	public List<UpgErrorGraphModel> getUpgErrorGraphModelInfo() {
		return upgErrorGraphModelInfo;
	}

	public void setUpgErrorGraphModelInfo(List<UpgErrorGraphModel> upgErrorGraphModelInfo) {
		this.upgErrorGraphModelInfo = upgErrorGraphModelInfo;
	}

	public List<UpgErrorModel> getUpgErrorModelInfo() {
		return upgErrorModelInfo;
	}

	public void setUpgErrorModelInfo(List<UpgErrorModel> upgErrorModelInfo) {
		this.upgErrorModelInfo = upgErrorModelInfo;
	}

	public List<UpgInterface> getUpgInterfaceInfo() {
		return upgInterfaceInfo;
	}

	public void setUpgInterfaceInfo(List<UpgInterface> upgInterfaceInfo) {
		this.upgInterfaceInfo = upgInterfaceInfo;
	}

	public List<UpgModel> getUpgModelInfo() {
		return upgModelInfo;
	}

	public void setUpgModelInfo(List<UpgModel> upgModelInfo) {
		this.upgModelInfo = upgModelInfo;
	}

//	public List<AmfYmlModelForMogoDb> getAmfYmlmodelInfo() {
//		return amfYmlmodelInfo;
//	}
//
//	public void setAmfYmlmodelInfo(List<AmfYmlModelForMogoDb> amfYmlmodelInfo) {
//		this.amfYmlmodelInfo = amfYmlmodelInfo;
//	}
//
//	public List<AusfDataMongoModel> getAusfDataYmlmodelInfo() {
//		return ausfDataYmlmodelInfo;
//	}
//
//	public void setAusfDataYmlmodelInfo(List<AusfDataMongoModel> ausfDataYmlmodelInfo) {
//		this.ausfDataYmlmodelInfo = ausfDataYmlmodelInfo;
//	}
//
//	public List<BsfDataMongoModel> getBsfDataMongoModelInfo() {
//		return bsfDataMongoModelInfo;
//	}
//
//	public void setBsfDataMongoModelInfo(List<BsfDataMongoModel> bsfDataMongoModelInfo) {
//		this.bsfDataMongoModelInfo = bsfDataMongoModelInfo;
//	}
//
//	public List<NrfDataMongoModel> getNrfDataMongoModelInfo() {
//		return nrfDataMongoModelInfo;
//	}
//
//	public void setNrfDataMongoModelInfo(List<NrfDataMongoModel> nrfDataMongoModelInfo) {
//		this.nrfDataMongoModelInfo = nrfDataMongoModelInfo;
//	}
//
//	public List<NssfDataMongoModel> getNssfDataMongoModelInfo() {
//		return nssfDataMongoModelInfo;
//	}
//
//	public void setNssfDataMongoModelInfo(List<NssfDataMongoModel> nssfDataMongoModelInfo) {
//		this.nssfDataMongoModelInfo = nssfDataMongoModelInfo;
//	}
//
//	public List<PcfDataMongoModel> getPcfDataMongoModelInfo() {
//		return pcfDataMongoModelInfo;
//	}
//
//	public void setPcfDataMongoModelInfo(List<PcfDataMongoModel> pcfDataMongoModelInfo) {
//		this.pcfDataMongoModelInfo = pcfDataMongoModelInfo;
//	}
//
//	public List<ScpDataMongoModel> getScpDataMongoModelInfo() {
//		return scpDataMongoModelInfo;
//	}
//
//	public void setScpDataMongoModelInfo(List<ScpDataMongoModel> scpDataMongoModelInfo) {
//		this.scpDataMongoModelInfo = scpDataMongoModelInfo;
//	}
//
//	public List<SmfDataMongoModel> getSmfDataMongoModelInfo() {
//		return smfDataMongoModelInfo;
//	}
//
//	public void setSmfDataMongoModelInfo(List<SmfDataMongoModel> smfDataMongoModelInfo) {
//		this.smfDataMongoModelInfo = smfDataMongoModelInfo;
//	}
//
//	public List<UdmDataMongoModel> getUdmDataMongoModelInfo() {
//		return udmDataMongoModelInfo;
//	}
//
//	public void setUdmDataMongoModelInfo(List<UdmDataMongoModel> udmDataMongoModelInfo) {
//		this.udmDataMongoModelInfo = udmDataMongoModelInfo;
//	}
//
//	public List<UdrDataMongoModel> getUdrDataMongoModelInfo() {
//		return udrDataMongoModelInfo;
//	}
//
//	public void setUdrDataMongoModelInfo(List<UdrDataMongoModel> udrDataMongoModelInfo) {
//		this.udrDataMongoModelInfo = udrDataMongoModelInfo;
//	}
//
//	public List<UpfDataMongoModel> getUpfDataMongoModelInfo() {
//		return upfDataMongoModelInfo;
//	}
//
//	public void setUpfDataMongoModelInfo(List<UpfDataMongoModel> upfDataMongoModelInfo) {
//		this.upfDataMongoModelInfo = upfDataMongoModelInfo;
//	}

	public List<Subscribers> getSubscriberInfo() {
		return subscriberInfo;
	}

	public void setSubscriberInfo(List<Subscribers> subscriberInfo) {
		this.subscriberInfo = subscriberInfo;
	}
//
//	public List<LogModelMongo> getLogModelMongoInfo() {
//		return logModelMongoInfo;
//	}
//
//	public void setLogModelMongoInfo(List<LogModelMongo> logModelMongoInfo) {
//		this.logModelMongoInfo = logModelMongoInfo;
//	}
//
	public List<LiveDataModel> getLiveDataInfo() {
		return liveDataInfo;
	}

	public void setLiveDataInfo(List<LiveDataModel> liveDataInfo) {
		this.liveDataInfo = liveDataInfo;
	}

	public List<GnbStatsModel> getGnbStatsModelInfo() {
		return gnbStatsModelInfo;
	}

	public void setGnbStatsModelInfo(List<GnbStatsModel> gnbStatsModelInfo) {
		this.gnbStatsModelInfo = gnbStatsModelInfo;
	}

	public List<NetworkTopologyModel> getNetworkTopologyModelInfo() {
		return networkTopologyModelInfo;
	}

	public void setNetworkTopologyModelInfo(List<NetworkTopologyModel> networkTopologyModelInfo) {
		this.networkTopologyModelInfo = networkTopologyModelInfo;
	}

	public List<DeploymentModel> getDeploymentInfo() {
		return deploymentInfo;
	}

	public void setDeploymentInfo(List<DeploymentModel> deploymentInfo) {
		this.deploymentInfo = deploymentInfo;
	}

	public List<DeviceModel> getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(List<DeviceModel> deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public List<GnBModel> getGnbModelInfo() {
		return gnbModelInfo;
	}

	public void setGnbModelInfo(List<GnBModel> gnbModelInfo) {
		this.gnbModelInfo = gnbModelInfo;
	}

	public List<SdnModel> getSdnModelInfo() {
		return sdnModelInfo;
	}

	public void setSdnModelInfo(List<SdnModel> sdnModelInfo) {
		this.sdnModelInfo = sdnModelInfo;
	}

	public List<SubZoneModel> getSubZoneModelInfo() {
		return subZoneModelInfo;
	}

	public void setSubZoneModelInfo(List<SubZoneModel> subZoneModelInfo) {
		this.subZoneModelInfo = subZoneModelInfo;
	}

	public List<ZoneModel> getZoneModelInfo() {
		return zoneModelInfo;
	}

	public void setZoneModelInfo(List<ZoneModel> zoneModelInfo) {
		this.zoneModelInfo = zoneModelInfo;
	}

	public List<UeStatsModel1> getUeStats() {
		return UeStats;
	}

	public void setUeStats(List<UeStatsModel1> ueStats) {
		UeStats = ueStats;
	}

	public List<TopUserModel> getTopUser() {
		return topUser;
	}

	public void setTopUser(List<TopUserModel> topUser) {
		this.topUser = topUser;
	}

	public List<AlarmModel> getAlarm() {
		return alarm;
	}

	public void setAlarm(List<AlarmModel> alarm) {
		this.alarm = alarm;
	}

	public List<ThroughputModel> getThroughput() {
		return throughput;
	}

	public void setThroughput(List<ThroughputModel> throughput) {
		this.throughput = throughput;
	}

	public List<PfcpInfo> getPfcpInfo() {
		return pfcpInfo;
	}

	public void setPfcpInfo(List<PfcpInfo> pfcpInfo) {
		this.pfcpInfo = pfcpInfo;
	}

	public List<PfcpSessioEstablishmentModificationDelationFailReasonList> getPfcpSession() {
		return pfcpSession;
	}

	public void setPfcpSession(List<PfcpSessioEstablishmentModificationDelationFailReasonList> pfcpSession) {
		this.pfcpSession = pfcpSession;
	}

	public List<UeHistoryModel> getUeHistory() {
		return ueHistory;
	}

	public void setUeHistory(List<UeHistoryModel> ueHistory) {
		this.ueHistory = ueHistory;
	}

	public List<UePduCauseInfoModel> getUePduCauseInfo() {
		return uePduCauseInfo;
	}

	public void setUePduCauseInfo(List<UePduCauseInfoModel> uePduCauseInfo) {
		this.uePduCauseInfo = uePduCauseInfo;
	}

	public List<UeRegistraionDeregistrationFailureCount> getUeRegDeregFailCount() {
		return UeRegDeregFailCount;
	}

	public void setUeRegDeregFailCount(List<UeRegistraionDeregistrationFailureCount> ueRegDeregFailCount) {
		UeRegDeregFailCount = ueRegDeregFailCount;
	}

	public List<GnbInfoModel> getGnbInfo() {
		return gnbInfo;
	}

	public void setGnbInfo(List<GnbInfoModel> gnbInfo) {
		this.gnbInfo = gnbInfo;
	}

	public List<GnbFailureList> getGnbList() {
		return gnbList;
	}

	public void setGnbList(List<GnbFailureList> gnbList) {
		this.gnbList = gnbList;
	}

	public List<GnbRegistrationDeregistrationFailureCount> getGnbdereg() {
		return gnbdereg;
	}

	public void setGnbdereg(List<GnbRegistrationDeregistrationFailureCount> gnbdereg) {
		this.gnbdereg = gnbdereg;
	}

}

package com.other.app.NiralosFiveGCore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "ue_stats1")
public class UeStatsModel1 {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "log_id")
	private Long logId;
	@Column(name = "nf_domain")
	private String nfDomain;
	@Column(name = "session_status")
    private String sessionStatus;
	@Column(name = "imsi")
    private String imsi;
	@Column(name = "cgi")
    private String cgi;
	@Column(name = "amf_id")
    private String amfId;
	@Column(name = "network_name")
    private String networkName;
	@Column(name = "dnn")
    private String dnn;
	@Column(name = "pdi")
    private String pdi;
	@Column(name = "session_ipv4")
    private String sessionIpv4;
	@Column(name = "session_ipv6")
    private String sessionIpv6;
	@Column(name = "destination_ip")
    private String destinationIp;
	@Column(name = "source_port")
    private String sourcePort;
	@Column(name = "destination_port")
    private String destinationPort;
	@Column(name = "protocol")
    private String protocol;
	@Column(name = "sst")
    private String sst;
	@Column(name = "sd")
    private String sd;
	@Column(name = "uplink_packets")
    private String uplinkPacket;
	@Column(name = "uplink_bytes")
    private String uplinkBytes;
	@Column(name = "downlink_packets")
    private String downlinkPacket;
	@Column(name = "downlink_bytes")
    private String downlinkBytes;
	@Column(name = "session_start_time")
    private String sessionStartTime;
	@Column(name = "session_stop_time")
    private String sessionStopTime;
	@Column(name = "duration")
    private String duration;
	@Column(name = "session_fail_reson")
    private String sessionFailReason;
	@Column(name = "amf_name")
	private String amfName;
	@Column(name = "gnb_id")
	private String gnbId;
	@Column(name = "agent_id")
	private String agentId;
	@Column(name = "site_id")
	private String siteId;
	@Column(name = "tenent_id")
	private String tenentId;
	@Column(name = "status")
	private Boolean status;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	
	public String getNfType() {
		return nfType;
	}
	public void setNfType(String nfType) {
		this.nfType = nfType;
	}
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getTenentId() {
		return tenentId;
	}
	public void setTenentId(String tenentId) {
		this.tenentId = tenentId;
	}
	public String getAmfName() {
		return amfName;
	}
	public void setAmfName(String amfName) {
		this.amfName = amfName;
	}
	public String getNfDomain() {
		return nfDomain;
	}
	public void setNfDomain(String nfDomain) {
		this.nfDomain = nfDomain;
	}
	public String getSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getCgi() {
		return cgi;
	}
	public void setCgi(String cgi) {
		this.cgi = cgi;
	}
	public String getAmfId() {
		return amfId;
	}
	public void setAmfId(String amfId) {
		this.amfId = amfId;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public String getDnn() {
		return dnn;
	}
	public void setDnn(String dnn) {
		this.dnn = dnn;
	}
	public String getPdi() {
		return pdi;
	}
	public void setPdi(String pdi) {
		this.pdi = pdi;
	}
	public String getSessionIpv4() {
		return sessionIpv4;
	}
	public void setSessionIpv4(String sessionIpv4) {
		this.sessionIpv4 = sessionIpv4;
	}
	public String getSessionIpv6() {
		return sessionIpv6;
	}
	public void setSessionIpv6(String sessionIpv6) {
		this.sessionIpv6 = sessionIpv6;
	}
	public String getDestinationIp() {
		return destinationIp;
	}
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	public String getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSst() {
		return sst;
	}
	public void setSst(String sst) {
		this.sst = sst;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getUplinkPacket() {
		return uplinkPacket;
	}
	public void setUplinkPacket(String uplinkPacket) {
		this.uplinkPacket = uplinkPacket;
	}
	public String getUplinkBytes() {
		return uplinkBytes;
	}
	public void setUplinkBytes(String uplinkBytes) {
		this.uplinkBytes = uplinkBytes;
	}
	public String getDownlinkPacket() {
		return downlinkPacket;
	}
	public void setDownlinkPacket(String downlinkPacket) {
		this.downlinkPacket = downlinkPacket;
	}
	public String getDownlinkBytes() {
		return downlinkBytes;
	}
	public void setDownlinkBytes(String downlinkBytes) {
		this.downlinkBytes = downlinkBytes;
	}
	public String getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public String getSessionStopTime() {
		return sessionStopTime;
	}
	public void setSessionStopTime(String sessionStopTime) {
		this.sessionStopTime = sessionStopTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSessionFailReason() {
		return sessionFailReason;
	}
	public void setSessionFailReason(String sessionFailReason) {
		this.sessionFailReason = sessionFailReason;
	}


	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getGnbId() {
		return gnbId;
	}
	public void setGnbId(String gnbId) {
		this.gnbId = gnbId;
	}
	public UeStatsModel1(String nfDomain, String sessionStatus, String imsi, String cgi, String amfId,
			String networkName, String dnn, String pdi, String sessionIpv4, String sessionIpv6, String destinationIp,
			String sourcePort, String destinationPort, String protocol, String sst, String sd, String uplinkPacket,
			String uplinkBytes, String downlinkPacket, String downlinkBytes, String sessionStartTime,
			String sessionStopTime, String duration, String sessionFailReason, String amfName, String gnbId,
			String agentId, String siteId, String tenentId, Boolean status, String nfType, String nfName) {
		super();
		this.nfDomain = nfDomain;
		this.sessionStatus = sessionStatus;
		this.imsi = imsi;
		this.cgi = cgi;
		this.amfId = amfId;
		this.networkName = networkName;
		this.dnn = dnn;
		this.pdi = pdi;
		this.sessionIpv4 = sessionIpv4;
		this.sessionIpv6 = sessionIpv6;
		this.destinationIp = destinationIp;
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
		this.protocol = protocol;
		this.sst = sst;
		this.sd = sd;
		this.uplinkPacket = uplinkPacket;
		this.uplinkBytes = uplinkBytes;
		this.downlinkPacket = downlinkPacket;
		this.downlinkBytes = downlinkBytes;
		this.sessionStartTime = sessionStartTime;
		this.sessionStopTime = sessionStopTime;
		this.duration = duration;
		this.sessionFailReason = sessionFailReason;
		this.amfName = amfName;
		this.gnbId = gnbId;
		this.agentId = agentId;
		this.siteId = siteId;
		this.tenentId = tenentId;
		this.status = status;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public UeStatsModel1() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	


}

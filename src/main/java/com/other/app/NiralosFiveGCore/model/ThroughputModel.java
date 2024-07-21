package com.other.app.NiralosFiveGCore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "throughput_model")
public class ThroughputModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "log_id")
	private Long logId;
	@Column(name = "date_time")
	LocalDateTime localDateTime;
	@Column(name = "uplink_bytes")
    private Integer uplinkBytes;
	@Column(name = "downlink_bytes")
    private Integer downlinkBytes;
	@Column(name = "agent_id")
	private String agentId;
	@Column(name = "site_id")
	private String siteId;
	@Column(name = "tenent_id")
	private String tenentId;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public Integer getUplinkBytes() {
		return uplinkBytes;
	}
	public void setUplinkBytes(Integer uplinkBytes) {
		this.uplinkBytes = uplinkBytes;
	}
	public Integer getDownlinkBytes() {
		return downlinkBytes;
	}
	public void setDownlinkBytes(Integer downlinkBytes) {
		this.downlinkBytes = downlinkBytes;
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
	public ThroughputModel(LocalDateTime localDateTime, Integer uplinkBytes, Integer downlinkBytes, String agentId,
			String siteId, String tenentId, String nfType, String nfName) {
		super();
		this.localDateTime = localDateTime;
		this.uplinkBytes = uplinkBytes;
		this.downlinkBytes = downlinkBytes;
		this.agentId = agentId;
		this.siteId = siteId;
		this.tenentId = tenentId;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public ThroughputModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}

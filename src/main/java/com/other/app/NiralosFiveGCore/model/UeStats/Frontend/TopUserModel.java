package com.other.app.NiralosFiveGCore.model.UeStats.Frontend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TopUserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
    private Long id;
	@Column(name="imsi")
    private String imsi;
	@Column(name="uplink_bytes")
    private String upLinkBytes;
	@Column(name="downlink_bytes")
    private String downLinkBytes;
	@Column(name="total_bytes")
	private Long totalBytes;
	@Column(name = "agent_id")
	private String agentId;
	@Column(name = "site_id")
	private String siteId;
	@Column(name = "tenent_id")
	private String tenentId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getUpLinkBytes() {
		return upLinkBytes;
	}
	public void setUpLinkBytes(String upLinkBytes) {
		this.upLinkBytes = upLinkBytes;
	}
	public String getDownLinkBytes() {
		return downLinkBytes;
	}
	public void setDownLinkBytes(String downLinkBytes) {
		this.downLinkBytes = downLinkBytes;
	}
	public Long getTotalBytes() {
		return totalBytes;
	}
	public void setTotalBytes(Long totalBytes) {
		this.totalBytes = totalBytes;
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
	public TopUserModel(Long id, String imsi, String upLinkBytes, String downLinkBytes, Long totalBytes, String agentId,
			String siteId, String tenentId) {
		super();
		this.id = id;
		this.imsi = imsi;
		this.upLinkBytes = upLinkBytes;
		this.downLinkBytes = downLinkBytes;
		this.totalBytes = totalBytes;
		this.agentId = agentId;
		this.siteId = siteId;
		this.tenentId = tenentId;
	}
	public TopUserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


}

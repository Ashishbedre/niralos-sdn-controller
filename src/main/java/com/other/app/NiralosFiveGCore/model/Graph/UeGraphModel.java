package com.other.app.NiralosFiveGCore.model.Graph;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ue_graph")
public class UeGraphModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "serial_id")
	Long serialId;
	@Column(name = "tenant_id")
	Long tenantId;
	@Column(name = "date_time")
	LocalDateTime localDateTime;
	@Column(name = "ue_count")
	Integer ueCount;
	@Column(name = "agent_id")
	private String agentId;
	@Column(name = "site_id")
	private String siteId;
	@Column(name = "tenent_name")
	private String tenentName;
	
	public UeGraphModel(Long tenantId, LocalDateTime localDateTime, Integer ueCount, String agentId, String siteId,
			String tenentName) {
		super();
		this.tenantId = tenantId;
		this.localDateTime = localDateTime;
		this.ueCount = ueCount;
		this.agentId = agentId;
		this.siteId = siteId;
		this.tenentName = tenentName;
	}
	public UeGraphModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public Integer getUeCount() {
		return ueCount;
	}
	public void setUeCount(Integer ueCount) {
		this.ueCount = ueCount;
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
	public String getTenentName() {
		return tenentName;
	}
	public void setTenentName(String tenentName) {
		this.tenentName = tenentName;
	}
	
	

	
}

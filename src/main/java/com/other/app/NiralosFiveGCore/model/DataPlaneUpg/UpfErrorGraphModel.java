package com.other.app.NiralosFiveGCore.model.DataPlaneUpg;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "upf_errorgraph_model")
public class UpfErrorGraphModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "serial_id")
	Long serialId;
	
	@Column(name = "date_time")
	LocalDateTime localDateTime;
	
	
	@Column(name = "reason")
	String reason;
	@Column(name = "value")
	Integer value;
	@Column(name = "agent_id")
	private String agentId;
	@Column(name = "site_id")
	private String siteId;
	@Column(name = "tenent_name")
	private String tenentName;
	public Long getSerialId() {
		return serialId;
	}
	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
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
	public UpfErrorGraphModel(Long serialId, LocalDateTime localDateTime, String reason, Integer value, String agentId,
			String siteId, String tenentName) {
		super();
		this.serialId = serialId;
		this.localDateTime = localDateTime;
		this.reason = reason;
		this.value = value;
		this.agentId = agentId;
		this.siteId = siteId;
		this.tenentName = tenentName;
	}
	public UpfErrorGraphModel() {
		super();
	}
	




	
	
	
}

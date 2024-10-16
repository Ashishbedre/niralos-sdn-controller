package com.other.app.NiralosFiveGCore.model.DataPlaneUpg;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upg_errorgraph_model")
public class UpgErrorGraphModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "serial_id")
	Long serialId;
	@Column(name = "tenant_id")
	Long tenantId;
	@Column(name = "date_time")
	LocalDateTime localDateTime;
	@Column(name = "error_type")
	String errorType;
	@Column(name = "name")
	String name;
	@Column(name = "reason")
	String reason;
	@Column(name = "value")
	Integer value;
	public UpgErrorGraphModel(Long tenantId, LocalDateTime localDateTime, String errorType, String name, String reason,
			Integer value) {
		super();
		this.tenantId = tenantId;
		this.localDateTime = localDateTime;
		this.errorType = errorType;
		this.name = name;
		this.reason = reason;
		this.value = value;
	}
	public UpgErrorGraphModel() {
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
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	

	
	
	
	
	
	
	
	

}

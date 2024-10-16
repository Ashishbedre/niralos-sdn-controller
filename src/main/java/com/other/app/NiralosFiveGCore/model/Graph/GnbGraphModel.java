package com.other.app.NiralosFiveGCore.model.Graph;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gnb_graph")
public class GnbGraphModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "serial_id")
	Long serialId;
	@Column(name = "tenant_id")
	Long tenantId;
	@Column(name = "date_time")
	LocalDateTime localDateTime;
	@Column(name = "gnb_count")
	Integer gnbCount;

	
	public GnbGraphModel(Long tenantId, LocalDateTime localDateTime, Integer gnbCount) {
		super();
		this.tenantId = tenantId;
		this.localDateTime = localDateTime;
		this.gnbCount = gnbCount;

	}
	public GnbGraphModel() {
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
	public Integer getGnbCount() {
		return gnbCount;
	}
	public void setGnbCount(Integer gnbCount) {
		this.gnbCount = gnbCount;
	}

	

}

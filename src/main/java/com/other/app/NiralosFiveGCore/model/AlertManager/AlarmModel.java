package com.other.app.NiralosFiveGCore.model.AlertManager;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alarm_model")
public class AlarmModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "alarm_id")
	String alarmId;
	@Column(name = "sub_alarm_id")
	String subAlarmId;
	@Column(name = "source_description")
	String sourceDescription;
	@Column(name = "source_type")
	String sourceType;
	@Column(name = "source_ip")
	String sourceIp;
	@Column(name = "destination_description")
	String destinationDescription;
	@Column(name = "destination_type")
	String destinationType;
	@Column(name = "destination_ip")
	String destinationIp;
	@Column(name = "date_time")
	LocalDateTime dateTime;
	@Column(name = "alarm_level")
	String alarmLevel;
	@Column(name = "reason")
	String reason;
	@Column(name = "solution")
	String solution;
	@Column(name = "nf_type")
	String nfType;
	@Column(name = "nf_name")
	String nfName;
	Boolean status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public String getSubAlarmId() {
		return subAlarmId;
	}
	public void setSubAlarmId(String subAlarmId) {
		this.subAlarmId = subAlarmId;
	}
	public String getSourceDescription() {
		return sourceDescription;
	}
	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getDestinationDescription() {
		return destinationDescription;
	}
	public void setDestinationDescription(String destinationDescription) {
		this.destinationDescription = destinationDescription;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public String getDestinationIp() {
		return destinationIp;
	}
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	

	
	
}

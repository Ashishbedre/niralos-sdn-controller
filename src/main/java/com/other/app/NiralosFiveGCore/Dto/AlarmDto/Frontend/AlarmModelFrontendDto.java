package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend;

import java.time.LocalDateTime;

public class AlarmModelFrontendDto {

	String id;
	String alarmId;
	String subAlarmId;
	String sourceDescription;
	String sourceType;
	String sourceIp;
	String destinationDescription;
	String destinationType;
	String destinationIp;
	LocalDateTime dateTime;
	String alarmLevel;
	String reason;
	String solution;
	Boolean status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public AlarmModelFrontendDto() {
		super();
	}
	public AlarmModelFrontendDto(String id, String alarmId, String subAlarmId, String sourceDescription,
			String sourceType, String sourceIp, String destinationDescription, String destinationType,
			String destinationIp, LocalDateTime dateTime, String alarmLevel, String reason, String solution,
			Boolean status) {
		super();
		this.id = id;
		this.alarmId = alarmId;
		this.subAlarmId = subAlarmId;
		this.sourceDescription = sourceDescription;
		this.sourceType = sourceType;
		this.sourceIp = sourceIp;
		this.destinationDescription = destinationDescription;
		this.destinationType = destinationType;
		this.destinationIp = destinationIp;
		this.dateTime = dateTime;
		this.alarmLevel = alarmLevel;
		this.reason = reason;
		this.solution = solution;
		this.status = status;
	}
	
	
	
	
	

}

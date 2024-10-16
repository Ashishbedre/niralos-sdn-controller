package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.time.LocalDateTime;

public class UpgErrorGraphDto {

	LocalDateTime localDateTime;
	String errorType;
	String name;
	String reason;
	Integer value;
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
	public UpgErrorGraphDto(LocalDateTime localDateTime, String errorType, String name, String reason, Integer value) {
		super();
		this.localDateTime = localDateTime;
		this.errorType = errorType;
		this.name = name;
		this.reason = reason;
		this.value = value;
	}
	public UpgErrorGraphDto() {
		super();
	}
	
	

}

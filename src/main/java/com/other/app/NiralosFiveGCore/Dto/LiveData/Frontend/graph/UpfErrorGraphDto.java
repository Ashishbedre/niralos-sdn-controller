package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.time.LocalDateTime;

public class UpfErrorGraphDto {
	LocalDateTime localDateTime;
	String reason;
	Integer value;
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
	public UpfErrorGraphDto(LocalDateTime localDateTime, String reason, Integer value) {
		super();
		this.localDateTime = localDateTime;
		this.reason = reason;
		this.value = value;
	}
	public UpfErrorGraphDto() {
		super();
	}
	
}

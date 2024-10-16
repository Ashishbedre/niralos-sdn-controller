package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.time.LocalDateTime;

public class UeGraphDTO {

	LocalDateTime localDateTime;
	Integer ueCount;

	public UeGraphDTO(LocalDateTime localDateTime, Integer ueCount) {
		super();
		this.localDateTime = localDateTime;
		this.ueCount = ueCount;
	}

	public UeGraphDTO() {
		super();
		// TODO Auto-generated constructor stub
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
}

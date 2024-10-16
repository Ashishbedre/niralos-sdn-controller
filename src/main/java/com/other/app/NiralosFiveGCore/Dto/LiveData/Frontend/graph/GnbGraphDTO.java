package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.time.LocalDateTime;

public class GnbGraphDTO {

	LocalDateTime localDateTime;
	Integer gnbCount;

	public GnbGraphDTO(LocalDateTime localDateTime, Integer gnbCount) {
		super();
		this.localDateTime = localDateTime;
		this.gnbCount = gnbCount;
	}

	public GnbGraphDTO() {
		super();
		// TODO Auto-generated constructor stub
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

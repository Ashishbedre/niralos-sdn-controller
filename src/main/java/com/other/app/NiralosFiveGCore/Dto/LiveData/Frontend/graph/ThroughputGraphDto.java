package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.time.LocalDateTime;

public class ThroughputGraphDto {

	LocalDateTime localDateTime;
	Integer uplinkBytes;
	Integer downlinkBytes;
	public ThroughputGraphDto(LocalDateTime localDateTime, Integer uplinkBytes, Integer downlinkBytes) {
		super();
		this.localDateTime = localDateTime;
		this.uplinkBytes = uplinkBytes;
		this.downlinkBytes = downlinkBytes;
	}
	public ThroughputGraphDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public Integer getUplinkBytes() {
		return uplinkBytes;
	}
	public void setUplinkBytes(Integer uplinkBytes) {
		this.uplinkBytes = uplinkBytes;
	}
	public Integer getDownlinkBytes() {
		return downlinkBytes;
	}
	public void setDownlinkBytes(Integer downlinkBytes) {
		this.downlinkBytes = downlinkBytes;
	}
	
	
	
	
	
	
}

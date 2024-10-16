package com.other.app.NiralosFiveGCore.Dto.ThroughputDto.Frontend;

import java.time.LocalDateTime;

public class ThroughputGraphFrontendDto {
	LocalDateTime localDateTime;
	Integer uplinkBytes;
	Integer downlinkBytes;
	
	
	public ThroughputGraphFrontendDto() {
		super();
	}
	public ThroughputGraphFrontendDto(LocalDateTime localDateTime, Integer uplinkBytes, Integer downlinkBytes) {
		super();
		this.localDateTime = localDateTime;
		this.uplinkBytes = uplinkBytes;
		this.downlinkBytes = downlinkBytes;
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

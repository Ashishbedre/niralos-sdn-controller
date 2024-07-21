package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dc {

	@JsonProperty("Sys.Audit")
	private Integer sysAudit;
	@JsonProperty("SDN.Audit")
	private Integer sdnAudit;
	@JsonProperty("SDN.Allocate")
	private Integer sdnAllocate;
	
	
	public Integer getSysAudit() {
		return sysAudit;
	}
	public void setSysAudit(Integer sysAudit) {
		this.sysAudit = sysAudit;
	}
	public Integer getSdnAudit() {
		return sdnAudit;
	}
	public void setSdnAudit(Integer sdnAudit) {
		this.sdnAudit = sdnAudit;
	}
	public Integer getSdnAllocate() {
		return sdnAllocate;
	}
	public void setSdnAllocate(Integer sdnAllocate) {
		this.sdnAllocate = sdnAllocate;
	}
	
	
}

package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sdn {

	@JsonProperty("Permissions.Modify")
	private Integer permissionsModify;
	@JsonProperty("SDN.Allocate")
	private Integer sdnAllocate;
	@JsonProperty("SDN.Audit")
	private Integer sdnAudit;
	
	public Integer getPermissionsModify() {
		return permissionsModify;
	}
	public void setPermissionsModify(Integer permissionsModify) {
		this.permissionsModify = permissionsModify;
	}
	public Integer getSdnAllocate() {
		return sdnAllocate;
	}
	public void setSdnAllocate(Integer sdnAllocate) {
		this.sdnAllocate = sdnAllocate;
	}
	public Integer getSdnAudit() {
		return sdnAudit;
	}
	public void setSdnAudit(Integer sdnAudit) {
		this.sdnAudit = sdnAudit;
	}
	
	
}

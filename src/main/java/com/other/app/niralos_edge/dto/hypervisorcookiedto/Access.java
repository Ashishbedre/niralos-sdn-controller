package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Access {
	
	@JsonProperty("Group.Allocates")
	private Integer groupAllocates;
	@JsonProperty("User.Modify")
	private Integer userModify;
	@JsonProperty("Permissions.Modify")
	private Integer permissionsModify;
	
	public Integer getGroupAllocates() {
		return groupAllocates;
	}
	public void setGroupAllocates(Integer groupAllocates) {
		this.groupAllocates = groupAllocates;
	}
	public Integer getUserModify() {
		return userModify;
	}
	public void setUserModify(Integer userModify) {
		this.userModify = userModify;
	}
	public Integer getPermissionsModify() {
		return permissionsModify;
	}
	public void setPermissionsModify(Integer permissionsModify) {
		this.permissionsModify = permissionsModify;
	}
	
	

}

package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Storage {

	@JsonProperty("dataAllocateSpace")
	private Integer dataStoreAllocateSpace;
	@JsonProperty("Permissions.Modify")
	private Integer permissionsModify;
	@JsonProperty("Datastore.Allocate")
	private Integer datastoreAllocate;
	@JsonProperty("Datastore.AllocateTemplate")
	private Integer dataAllcateTemplate;
	@JsonProperty("Datastore.Audit")
	private Integer dataAudit;
	
	
	public Integer getDataStoreAllocateSpace() {
		return dataStoreAllocateSpace;
	}
	public void setDataStoreAllocateSpace(Integer dataStoreAllocateSpace) {
		this.dataStoreAllocateSpace = dataStoreAllocateSpace;
	}
	public Integer getPermissionsModify() {
		return permissionsModify;
	}
	public void setPermissionsModify(Integer permissionsModify) {
		this.permissionsModify = permissionsModify;
	}
	public Integer getDatastoreAllocate() {
		return datastoreAllocate;
	}
	public void setDatastoreAllocate(Integer datastoreAllocate) {
		this.datastoreAllocate = datastoreAllocate;
	}
	public Integer getDataAllcateTemplate() {
		return dataAllcateTemplate;
	}
	public void setDataAllcateTemplate(Integer dataAllcateTemplate) {
		this.dataAllcateTemplate = dataAllcateTemplate;
	}
	public Integer getDataAudit() {
		return dataAudit;
	}
	public void setDataAudit(Integer dataAudit) {
		this.dataAudit = dataAudit;
	}
	
	
	
	
	
}

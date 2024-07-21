package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Nodes {

	@JsonProperty("Sys.Incoming")
	private Integer sysIncoming;
	@JsonProperty("Sys.Modify")
	private Integer sysModify;
	@JsonProperty("Sys.Console")
	private Integer sysConsole;
	@JsonProperty("Sys.PowerMgmt")
	private Integer sysPowerMgmt;
	@JsonProperty("Permisssions.Modify")
	private Integer permissionsModify;
	@JsonProperty("Sys.Syslog")
	private Integer sysLog;
	@JsonProperty("Sys.Audit")
	private Integer sysAudit;
	
	public Integer getSysIncoming() {
		return sysIncoming;
	}
	public void setSysIncoming(Integer sysIncoming) {
		this.sysIncoming = sysIncoming;
	}
	public Integer getSysModify() {
		return sysModify;
	}
	public void setSysModify(Integer sysModify) {
		this.sysModify = sysModify;
	}
	public Integer getSysConsole() {
		return sysConsole;
	}
	public void setSysConsole(Integer sysConsole) {
		this.sysConsole = sysConsole;
	}
	public Integer getSysPowerMgmt() {
		return sysPowerMgmt;
	}
	public void setSysPowerMgmt(Integer sysPowerMgmt) {
		this.sysPowerMgmt = sysPowerMgmt;
	}
	public Integer getPermissionsModify() {
		return permissionsModify;
	}
	public void setPermissionsModify(Integer permissionsModify) {
		this.permissionsModify = permissionsModify;
	}
	public Integer getSysLog() {
		return sysLog;
	}
	public void setSysLog(Integer sysLog) {
		this.sysLog = sysLog;
	}
	public Integer getSysAudit() {
		return sysAudit;
	}
	public void setSysAudit(Integer sysAudit) {
		this.sysAudit = sysAudit;
	}
	
	
}

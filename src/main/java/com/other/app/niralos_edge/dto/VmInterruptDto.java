package com.other.app.niralos_edge.dto;


public class VmInterruptDto {

	private Long vmId;
	private Integer interruptCode;
	private String edgeClientId;
	private String siteId;
	private String volId;
	private String iface;
	private String type;
	private String cidr;
	private String target;
	private String url;
	private String fileName;
	
	public VmInterruptDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getVmId() {
		return vmId;
	}
	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}
	public Integer getInterruptCode() {
		return interruptCode;
	}
	public void setInterruptCode(Integer interruptCode) {
		this.interruptCode = interruptCode;
	}
	public String getEdgeClientId() {
		return edgeClientId;
	}
	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getVolId() {
		return volId;
	}
	public void setVolId(String volId) {
		this.volId = volId;
	}
	public String getIface() {
		return iface;
	}
	public void setIface(String iface) {
		this.iface = iface;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCidr() {
		return cidr;
	}
	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}

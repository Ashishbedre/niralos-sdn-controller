package com.other.app.niralos_edge.dto;


public class HypervisorNetworkStatsDto {

	private String type;
	
    private String iface;
	
    private Integer autostart;
	
    private Integer priority;
	
    private Integer active;
	
    private String address;
	
    private String bridge_ports;
	
    private String gateway;
	
    private String cidr;
	
    private String netmask;
	
	private String tenantId;
	
	private String siteId;
	
	private String edgeClientId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIface() {
		return iface;
	}

	public void setIface(String iface) {
		this.iface = iface;
	}

	public Integer getAutostart() {
		return autostart;
	}

	public void setAutostart(Integer autostart) {
		this.autostart = autostart;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBridge_ports() {
		return bridge_ports;
	}

	public void setBridge_ports(String bridge_ports) {
		this.bridge_ports = bridge_ports;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
	
	
}

package com.other.app.niralos_edge.dto.hypervisornetworkstatsdto;

import java.util.ArrayList;
import java.util.List;

public class InData {
	
	
	private String type;
    private List<String> families = new ArrayList<String>();
    private Integer exists;
    private String iface;
    private String method;
    private Integer autostart;
    private Integer priority;
    private String method6;
    private Integer active;
    private String address;
    private String bridge_ports;
    private String bridgeFd;
    private String gateway;
    private String bridgeStp;
    private String cidr;
    private String netmask;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getFamilies() {
		return families;
	}
	public void setFamilies(List<String> families) {
		this.families = families;
	}
	public Integer getExists() {
		return exists;
	}
	public void setExists(Integer exists) {
		this.exists = exists;
	}
	public String getIface() {
		return iface;
	}
	public void setIface(String iface) {
		this.iface = iface;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
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
	public String getMethod6() {
		return method6;
	}
	public void setMethod6(String method6) {
		this.method6 = method6;
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
	public String getBridgeFd() {
		return bridgeFd;
	}
	public void setBridgeFd(String bridgeFd) {
		this.bridgeFd = bridgeFd;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getBridgeStp() {
		return bridgeStp;
	}
	public void setBridgeStp(String bridgeStp) {
		this.bridgeStp = bridgeStp;
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
    
    
    
}

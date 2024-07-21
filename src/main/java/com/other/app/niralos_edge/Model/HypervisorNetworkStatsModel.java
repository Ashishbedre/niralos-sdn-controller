package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hypervisor_network_stats")
public class HypervisorNetworkStatsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "iface")
    private String iface;
	
	@Column(name = "autostart")
    private Integer autostart;
	
	@Column(name = "priority")
    private Integer priority;
	
	@Column(name = "active")
    private Integer active;
	
	@Column(name = "address")
    private String address;
	
	@Column(name = "bridge_ports")
    private String bridge_ports;
	
	@Column(name = "gateway")
    private String gateway;
	
	@Column(name = "cidr")
    private String cidr;
	
	@Column(name = "netmask")
    private String netmask;
	
	@Column(name="edge_client_id")
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

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	public HypervisorNetworkStatsModel(String type, String iface, Integer autostart, Integer priority, Integer active,
			String address, String bridge_ports, String gateway, String cidr, String netmask, String edgeClientId) {
		super();
		this.type = type;
		this.iface = iface;
		this.autostart = autostart;
		this.priority = priority;
		this.active = active;
		this.address = address;
		this.bridge_ports = bridge_ports;
		this.gateway = gateway;
		this.cidr = cidr;
		this.netmask = netmask;
		this.edgeClientId = edgeClientId;
	}

	public HypervisorNetworkStatsModel() {
		super();
	}
	
	
	
    
    

}

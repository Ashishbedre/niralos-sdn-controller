package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vm_stats")
public class VmStatsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="vm_id")
	private Long vmId;
	
	@Column(name="vm_name")
	private String vmName;
		
	@Column(name="vm_status")
	private String vmStatus;
	
	@Column(name="vm_current_cpu_usage")
	private Double vmCurrentCpuUsage;
	
	@Column(name="vm_total_cpus_available")
	private Long vmTotalCpusAvailable;
	
	@Column(name="vm_current_mem_usage")
	private Double vmCurrentMemUsage;
	
	@Column(name="vm_current_mem_usage_in_units")
	private Double vmCurrentMemUsageInUnits;
	
	@Column(name="vm_total_mem_available")
	private Long vmTotalMemAvailable;
	
	@Column(name="vm_total_disk_space")
	private Long vmTotalDiskSpace;
	
	@Column(name="vm_netin")
	private Long vmNetin;
	
	@Column(name="vm_netout")
	private Long vmNetout;
	
	@Column(name="vm_uptime")
	private Long vmUptime;
	
	@Column(name="vm_agent")
	private Long vmAgent;
	
	@Column(name="cores")
	private Long cores;
	
	@Column(name="sockets")
	private Long sockets;
	
	@Column(name="spice_port")
	private String spicePort;
	
	@Column(name="edge_client_id")
	private String edgeClientId;

	public Long getVmId() {
		return vmId;
	}

	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getVmStatus() {
		return vmStatus;
	}

	public void setVmStatus(String vmStatus) {
		this.vmStatus = vmStatus;
	}

	public Double getVmCurrentCpuUsage() {
		return vmCurrentCpuUsage;
	}

	public void setVmCurrentCpuUsage(Double vmCurrentCpuUsage) {
		this.vmCurrentCpuUsage = vmCurrentCpuUsage;
	}

	public Long getVmTotalCpusAvailable() {
		return vmTotalCpusAvailable;
	}

	public void setVmTotalCpusAvailable(Long vmTotalCpusAvailable) {
		this.vmTotalCpusAvailable = vmTotalCpusAvailable;
	}

	public Double getVmCurrentMemUsage() {
		return vmCurrentMemUsage;
	}

	public void setVmCurrentMemUsage(Double vmCurrentMemUsage) {
		this.vmCurrentMemUsage = vmCurrentMemUsage;
	}

	public Double getVmCurrentMemUsageInUnits() {
		return vmCurrentMemUsageInUnits;
	}

	public void setVmCurrentMemUsageInUnits(Double vmCurrentMemUsageInUnits) {
		this.vmCurrentMemUsageInUnits = vmCurrentMemUsageInUnits;
	}

	public Long getVmTotalMemAvailable() {
		return vmTotalMemAvailable;
	}

	public void setVmTotalMemAvailable(Long vmTotalMemAvailable) {
		this.vmTotalMemAvailable = vmTotalMemAvailable;
	}

	public Long getVmTotalDiskSpace() {
		return vmTotalDiskSpace;
	}

	public void setVmTotalDiskSpace(Long vmTotalDiskSpace) {
		this.vmTotalDiskSpace = vmTotalDiskSpace;
	}

	public Long getVmNetin() {
		return vmNetin;
	}

	public void setVmNetin(Long vmNetin) {
		this.vmNetin = vmNetin;
	}

	public Long getVmNetout() {
		return vmNetout;
	}

	public void setVmNetout(Long vmNetout) {
		this.vmNetout = vmNetout;
	}

	public Long getVmUptime() {
		return vmUptime;
	}

	public void setVmUptime(Long vmUptime) {
		this.vmUptime = vmUptime;
	}

	public Long getVmAgent() {
		return vmAgent;
	}

	public void setVmAgent(Long vmAgent) {
		this.vmAgent = vmAgent;
	}

	public Long getCores() {
		return cores;
	}

	public void setCores(Long cores) {
		this.cores = cores;
	}

	public Long getSockets() {
		return sockets;
	}

	public void setSockets(Long sockets) {
		this.sockets = sockets;
	}

	public String getSpicePort() {
		return spicePort;
	}

	public void setSpicePort(String spicePort) {
		this.spicePort = spicePort;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	
	public VmStatsModel(Long vmId, String vmName, String vmStatus, Double vmCurrentCpuUsage, Long vmTotalCpusAvailable,
			Double vmCurrentMemUsage, Double vmCurrentMemUsageInUnits, Long vmTotalMemAvailable, Long vmTotalDiskSpace,
			Long vmNetin, Long vmNetout, Long vmUptime, Long vmAgent, Long cores, Long sockets, String spicePort,
			String edgeClientId) {
		super();
		this.vmId = vmId;
		this.vmName = vmName;
		this.vmStatus = vmStatus;
		this.vmCurrentCpuUsage = vmCurrentCpuUsage;
		this.vmTotalCpusAvailable = vmTotalCpusAvailable;
		this.vmCurrentMemUsage = vmCurrentMemUsage;
		this.vmCurrentMemUsageInUnits = vmCurrentMemUsageInUnits;
		this.vmTotalMemAvailable = vmTotalMemAvailable;
		this.vmTotalDiskSpace = vmTotalDiskSpace;
		this.vmNetin = vmNetin;
		this.vmNetout = vmNetout;
		this.vmUptime = vmUptime;
		this.vmAgent = vmAgent;
		this.cores = cores;
		this.sockets = sockets;
		this.spicePort = spicePort;
		this.edgeClientId = edgeClientId;
	}

	public VmStatsModel() {
		super();
	}
	
	
	

	

}

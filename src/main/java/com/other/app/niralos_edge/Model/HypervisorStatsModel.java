package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hypervisor_stats")
public class HypervisorStatsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "total_diskspace")
	private Long totalDiskspace;
	
	@Column(name = "used_diskspace")
	private Long usedDiskSpace;
	
	@Column(name = "free_diskspace")
	private Long freeDiskSpace;
	
	@Column(name = "total_ramspace")
	private Double totalRamSpace;
	
	@Column(name = "current_ram_usage")
	private Long currentRamUsage;
	
	@Column(name = "available_ramspace")
	private Long availableRamSpace;
	
	@Column(name = "cpu_model")
	private String cpuModel;
	
	@Column(name = "cpu_usage")
	private Double cpuUsage;
	
	@Column(name = "cpu_sockets")
	private Long cpuSockets;
	
	@Column(name = "total_cpus")
	private Long totalCpus;
	
	@Column(name = "cpu_cores")
	private Long cpuCores;
	
	@Column(name = "uptime")
	private Long upTime;
	
	@Column(name = "node_name")
	private String nodeName;
	
	@Column(name="node_status")
	private String nodeStatus;
	
	@Column(name="edge_client_id")
	private String edgeClientId;

	public Long getTotalDiskspace() {
		return totalDiskspace;
	}

	public void setTotalDiskspace(Long totalDiskspace) {
		this.totalDiskspace = totalDiskspace;
	}

	public Long getUsedDiskSpace() {
		return usedDiskSpace;
	}

	public void setUsedDiskSpace(Long usedDiskSpace) {
		this.usedDiskSpace = usedDiskSpace;
	}

	public Long getFreeDiskSpace() {
		return freeDiskSpace;
	}

	public void setFreeDiskSpace(Long freeDiskSpace) {
		this.freeDiskSpace = freeDiskSpace;
	}

	public Double getTotalRamSpace() {
		return totalRamSpace;
	}

	public void setTotalRamSpace(Double totalRamSpace) {
		this.totalRamSpace = totalRamSpace;
	}

	public Long getCurrentRamUsage() {
		return currentRamUsage;
	}

	public void setCurrentRamUsage(Long currentRamUsage) {
		this.currentRamUsage = currentRamUsage;
	}

	public Long getAvailableRamSpace() {
		return availableRamSpace;
	}

	public void setAvailableRamSpace(Long availableRamSpace) {
		this.availableRamSpace = availableRamSpace;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public Double getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(Double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public Long getCpuSockets() {
		return cpuSockets;
	}

	public void setCpuSockets(Long cpuSockets) {
		this.cpuSockets = cpuSockets;
	}

	public Long getTotalCpus() {
		return totalCpus;
	}

	public void setTotalCpus(Long totalCpus) {
		this.totalCpus = totalCpus;
	}

	public Long getCpuCores() {
		return cpuCores;
	}

	public void setCpuCores(Long cpuCores) {
		this.cpuCores = cpuCores;
	}

	public Long getUpTime() {
		return upTime;
	}

	public void setUpTime(Long upTime) {
		this.upTime = upTime;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public HypervisorStatsModel(Long totalDiskspace, Long usedDiskSpace, Long freeDiskSpace, Double totalRamSpace,
			Long currentRamUsage, Long availableRamSpace, String cpuModel, Double cpuUsage, Long cpuSockets,
			Long totalCpus, Long cpuCores, Long upTime, String nodeName, String nodeStatus, String edgeClientId) {
		super();
		this.totalDiskspace = totalDiskspace;
		this.usedDiskSpace = usedDiskSpace;
		this.freeDiskSpace = freeDiskSpace;
		this.totalRamSpace = totalRamSpace;
		this.currentRamUsage = currentRamUsage;
		this.availableRamSpace = availableRamSpace;
		this.cpuModel = cpuModel;
		this.cpuUsage = cpuUsage;
		this.cpuSockets = cpuSockets;
		this.totalCpus = totalCpus;
		this.cpuCores = cpuCores;
		this.upTime = upTime;
		this.nodeName = nodeName;
		this.nodeStatus = nodeStatus;
		this.edgeClientId = edgeClientId;
	}

	public HypervisorStatsModel() {
		super();
	}
	

}

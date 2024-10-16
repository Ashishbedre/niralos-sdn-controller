package com.other.app.niralos_edge.dto;

import java.util.List;

import com.other.app.niralos_edge.Model.graph.CpuGraphModel;
import com.other.app.niralos_edge.Model.graph.MemoryGraphModel;

public class HypervisorStatsDto {

    private String edgeClientId;
	
	private Long totalDiskspace;
	
	private Long usedDiskSpace;
	
	private Long freeDiskSpace;
	
	private Double diskPercentage;
	
	private Double totalRamSpace;
	
	private Long currentRamUsage;
	
	private Double ramPercentage;
	
	private Long availableRamSpace;
	
	private String cpuModel;
	
	private Double cpuUsage;
	
	private Long cpuSockets;
	
	private Long totalCpus;
	
	private Long cpuCores;
	
	private Double cpuPercentage;
	
	private Long upTime;
	
	private String nodeName;
	
	private String nodeStatus;
		
	private List<CpuGraphModel> cpuGraphModel;
	
	private List<MemoryGraphModel> memoryGraphModel;

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

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

	public Double getDiskPercentage() {
		return diskPercentage;
	}

	public void setDiskPercentage(Double diskPercentage) {
		this.diskPercentage = diskPercentage;
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

	public Double getRamPercentage() {
		return ramPercentage;
	}

	public void setRamPercentage(Double ramPercentage) {
		this.ramPercentage = ramPercentage;
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

	public Double getCpuPercentage() {
		return cpuPercentage;
	}

	public void setCpuPercentage(Double cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
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

	public List<CpuGraphModel> getCpuGraphModel() {
		return cpuGraphModel;
	}

	public void setCpuGraphModel(List<CpuGraphModel> cpuGraphModel) {
		this.cpuGraphModel = cpuGraphModel;
	}
	
	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public List<MemoryGraphModel> getMemoryGraphModel() {
		return memoryGraphModel;
	}

	public void setMemoryGraphModel(List<MemoryGraphModel> memoryGraphModel) {
		this.memoryGraphModel = memoryGraphModel;
	}

	public HypervisorStatsDto(String edgeClientId, Long totalDiskspace, Long usedDiskSpace, Long freeDiskSpace,
			Double diskPercentage, Double totalRamSpace, Long currentRamUsage, Double ramPercentage,
			Long availableRamSpace, String cpuModel, Double cpuUsage, Long cpuSockets, Long totalCpus, Long cpuCores,
			Double cpuPercentage, Long upTime, String nodeName,String nodeStatus, List<CpuGraphModel> cpuGraphModel,
			List<MemoryGraphModel> memoryGraphModel) {
		super();
		this.edgeClientId = edgeClientId;
		this.totalDiskspace = totalDiskspace;
		this.usedDiskSpace = usedDiskSpace;
		this.freeDiskSpace = freeDiskSpace;
		this.diskPercentage = diskPercentage;
		this.totalRamSpace = totalRamSpace;
		this.currentRamUsage = currentRamUsage;
		this.ramPercentage = ramPercentage;
		this.availableRamSpace = availableRamSpace;
		this.cpuModel = cpuModel;
		this.cpuUsage = cpuUsage;
		this.cpuSockets = cpuSockets;
		this.totalCpus = totalCpus;
		this.cpuCores = cpuCores;
		this.cpuPercentage = cpuPercentage;
		this.upTime = upTime;
		this.nodeName = nodeName;
		this.cpuGraphModel = cpuGraphModel;
		this.memoryGraphModel = memoryGraphModel;
		this.nodeStatus=nodeStatus;
	}

	public HypervisorStatsDto() {
		super();
	}

	

	
	
}

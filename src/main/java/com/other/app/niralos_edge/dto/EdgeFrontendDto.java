package com.other.app.niralos_edge.dto;

import java.util.List;



public class EdgeFrontendDto {

	private Long vmId;
	private String edgeClientId;
	private String vmName;
	private String vmStatus;
	private Double vmCurrentCpuUsage;
	private Long vmTotalCpusAvailable;
	private Double vmCurrentMemUsage;
	private Double vmCurrentMemUsageInUnits;
	private Long vmTotalMemAvailable;
	private Long vmTotalDiskSpace;
	private Long vmNetin;
	private Long vmNetout;
	private Long vmUptime;
	private Long vmAgent;
	
	private List<VmNetworkStatsDto> vmNetworkInterfaces;

	public Long getVmId() {
		return vmId;
	}

	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
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

	public List<VmNetworkStatsDto> getVmNetworkInterfaces() {
		return vmNetworkInterfaces;
	}

	public void setVmNetworkInterfaces(List<VmNetworkStatsDto> vmNetworkInterfaces) {
		this.vmNetworkInterfaces = vmNetworkInterfaces;
	}

	
	
	

	
}

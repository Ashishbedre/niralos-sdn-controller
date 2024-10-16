package com.other.app.niralos_edge.dto;

import java.util.ArrayList;

import com.other.app.niralos_edge.Model.VmNetworkStatsModel;


public class VmStatsDto {

	private Long vmId;

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
	
	private Long cores;
	
	private Long sockets;
	
	private String spicePort;
	
	public ArrayList<VmNetworkStatsModel> networkInterfaceDetails;

	public VmStatsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmStatsDto(Long vmId, String vmName, String vmStatus, Double vmCurrentCpuUsage, Long vmTotalCpusAvailable,
			Double vmCurrentMemUsage, Double vmCurrentMemUsageInUnits, Long vmTotalMemAvailable, Long vmTotalDiskSpace,
			Long vmNetin, Long vmNetout, Long vmUptime, Long vmAgent, Long cores, Long sockets, String spicePort,
			ArrayList<VmNetworkStatsModel> networkInterfaceDetails) {
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
		this.networkInterfaceDetails = networkInterfaceDetails;
	}

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

	public ArrayList<VmNetworkStatsModel> getNetworkInterfaceDetails() {
		return networkInterfaceDetails;
	}

	public void setNetworkInterfaceDetails(ArrayList<VmNetworkStatsModel> networkInterfaceDetails) {
		this.networkInterfaceDetails = networkInterfaceDetails;
	}

	
	

}

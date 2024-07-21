package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.SbiDevAAndPort;

public class UpfSbiDataScpDto {

	public SbiDevAAndPort pfcp;
	public SbiDevAAndPort metrics;
	public SbiDevAAndPort gtpu;
	public List<UpfSubnet> subnet;
	public UpfSbiDataScpDto(SbiDevAAndPort pfcp, SbiDevAAndPort metrics, SbiDevAAndPort gtpu, List<UpfSubnet> subnet) {
		super();
		this.pfcp = pfcp;
		this.metrics = metrics;
		this.gtpu = gtpu;
		this.subnet = subnet;
	}
	public UpfSbiDataScpDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SbiDevAAndPort getPfcp() {
		return pfcp;
	}
	public void setPfcp(SbiDevAAndPort pfcp) {
		this.pfcp = pfcp;
	}
	public SbiDevAAndPort getMetrics() {
		return metrics;
	}
	public void setMetrics(SbiDevAAndPort metrics) {
		this.metrics = metrics;
	}
	public SbiDevAAndPort getGtpu() {
		return gtpu;
	}
	public void setGtpu(SbiDevAAndPort gtpu) {
		this.gtpu = gtpu;
	}
	public List<UpfSubnet> getSubnet() {
		return subnet;
	}
	public void setSubnet(List<UpfSubnet> subnet) {
		this.subnet = subnet;
	}
	@Override
	public String toString() {
		return "UpfSbiDataScpDto [pfcp=" + pfcp + ", metrics=" + metrics + ", gtpu=" + gtpu + ", subnet=" + subnet
				+ "]";
	}
	
	

}

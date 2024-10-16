package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.DevAndPort;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto.UpfSubnet;

public class SmfAttributeDateModel {

	public DevAndPort sbi;
	public DevAndPort pfcp;
	public SmfDev gtpc;
	public SmfDev gtpu;
	public DevAndPort metrics;
	public String service_id;
	public List<UpfSubnet> subnet;
	public List<SmfInfoSnssai> info;
	public List<String> dns;
	public Integer mtu;
	public CtfEnable ctf;
	public String freeDiameter;
	public SmfAttributeDateModel(DevAndPort sbi, DevAndPort pfcp, SmfDev gtpc, SmfDev gtpu, DevAndPort metrics,
			String service_id, List<UpfSubnet> subnet, List<SmfInfoSnssai> info, List<String> dns, Integer mtu,
			CtfEnable ctf, String freeDiameter) {
		super();
		this.sbi = sbi;
		this.pfcp = pfcp;
		this.gtpc = gtpc;
		this.gtpu = gtpu;
		this.metrics = metrics;
		this.service_id = service_id;
		this.subnet = subnet;
		this.info = info;
		this.dns = dns;
		this.mtu = mtu;
		this.ctf = ctf;
		this.freeDiameter = freeDiameter;
	}
	public SmfAttributeDateModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DevAndPort getSbi() {
		return sbi;
	}
	public void setSbi(DevAndPort sbi) {
		this.sbi = sbi;
	}
	public DevAndPort getPfcp() {
		return pfcp;
	}
	public void setPfcp(DevAndPort pfcp) {
		this.pfcp = pfcp;
	}
	public SmfDev getGtpc() {
		return gtpc;
	}
	public void setGtpc(SmfDev gtpc) {
		this.gtpc = gtpc;
	}
	public SmfDev getGtpu() {
		return gtpu;
	}
	public void setGtpu(SmfDev gtpu) {
		this.gtpu = gtpu;
	}
	public DevAndPort getMetrics() {
		return metrics;
	}
	public void setMetrics(DevAndPort metrics) {
		this.metrics = metrics;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public List<UpfSubnet> getSubnet() {
		return subnet;
	}
	public void setSubnet(List<UpfSubnet> subnet) {
		this.subnet = subnet;
	}
	public List<SmfInfoSnssai> getInfo() {
		return info;
	}
	public void setInfo(List<SmfInfoSnssai> info) {
		this.info = info;
	}
	public List<String> getDns() {
		return dns;
	}
	public void setDns(List<String> dns) {
		this.dns = dns;
	}
	public Integer getMtu() {
		return mtu;
	}
	public void setMtu(Integer mtu) {
		this.mtu = mtu;
	}
	public CtfEnable getCtf() {
		return ctf;
	}
	public void setCtf(CtfEnable ctf) {
		this.ctf = ctf;
	}
	public String getFreeDiameter() {
		return freeDiameter;
	}
	public void setFreeDiameter(String freeDiameter) {
		this.freeDiameter = freeDiameter;
	}
	@Override
	public String toString() {
		return "SmfAttributeDateModel [sbi=" + sbi + ", pfcp=" + pfcp + ", gtpc=" + gtpc + ", gtpu=" + gtpu
				+ ", metrics=" + metrics + ", service_id=" + service_id + ", subnet=" + subnet + ", info=" + info
				+ ", dns=" + dns + ", mtu=" + mtu + ", ctf=" + ctf + ", freeDiameter=" + freeDiameter + "]";
	}
		
	
	
	

}

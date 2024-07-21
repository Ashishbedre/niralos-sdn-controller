package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

import java.util.List;

public class AmfAttributeDateModel {

	public DevAndPort sbi;
	public DevAndPort ngap;
	public DevAndPort metrics;
	public List<guamiDataModel> guami;
	public List<Tai> tai;
	public List<PlmnSupport> plmn_support;
	public Security security;
	public NetworkName network_name;
	public String amf_name;
	public String service_id;
	public Nfsupport nf_support;
	public AmfAttributeDateModel(DevAndPort sbi, DevAndPort ngap, DevAndPort metrics, List<guamiDataModel> guami,
			List<Tai> tai, List<PlmnSupport> plmn_support, Security security, NetworkName network_name, String amf_name,
			String service_id, Nfsupport nf_support) {
		super();
		this.sbi = sbi;
		this.ngap = ngap;
		this.metrics = metrics;
		this.guami = guami;
		this.tai = tai;
		this.plmn_support = plmn_support;
		this.security = security;
		this.network_name = network_name;
		this.amf_name = amf_name;
		this.service_id = service_id;
		this.nf_support = nf_support;
	}
	public AmfAttributeDateModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DevAndPort getSbi() {
		return sbi;
	}
	public void setSbi(DevAndPort sbi) {
		this.sbi = sbi;
	}
	public DevAndPort getNgap() {
		return ngap;
	}
	public void setNgap(DevAndPort ngap) {
		this.ngap = ngap;
	}
	public DevAndPort getMetrics() {
		return metrics;
	}
	public void setMetrics(DevAndPort metrics) {
		this.metrics = metrics;
	}
	public List<guamiDataModel> getGuami() {
		return guami;
	}
	public void setGuami(List<guamiDataModel> guami) {
		this.guami = guami;
	}
	public List<Tai> getTai() {
		return tai;
	}
	public void setTai(List<Tai> tai) {
		this.tai = tai;
	}
	public List<PlmnSupport> getPlmn_support() {
		return plmn_support;
	}
	public void setPlmn_support(List<PlmnSupport> plmn_support) {
		this.plmn_support = plmn_support;
	}
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	public NetworkName getNetwork_name() {
		return network_name;
	}
	public void setNetwork_name(NetworkName network_name) {
		this.network_name = network_name;
	}
	public String getAmf_name() {
		return amf_name;
	}
	public void setAmf_name(String amf_name) {
		this.amf_name = amf_name;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public Nfsupport getNf_support() {
		return nf_support;
	}
	public void setNf_support(Nfsupport nf_support) {
		this.nf_support = nf_support;
	}
	@Override
	public String toString() {
		return "AmfAttributeDateModel [sbi=" + sbi + ", ngap=" + ngap + ", metrics=" + metrics + ", guami=" + guami
				+ ", tai=" + tai + ", plmn_support=" + plmn_support + ", security=" + security + ", network_name="
				+ network_name + ", amf_name=" + amf_name + ", service_id=" + service_id + ", nf_support=" + nf_support
				+ "]";
	}
	
	
	

}

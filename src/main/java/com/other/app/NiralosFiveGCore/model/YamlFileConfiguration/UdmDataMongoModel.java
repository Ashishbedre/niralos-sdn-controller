package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdmYamlDto.UdmHnetIdKey;
@Document
public class UdmDataMongoModel {

	@MongoId
	private String id;
	public SbiData sbi;
	public UdmHnetIdKey udm;
	public NrfSbiDto scp;
	public String parameter;
	public String max;
	public String time;
	private String tenantId;
	private String siteId;
	private String agentId;
	private String nfName;
	public UdmDataMongoModel(String id, SbiData sbi, UdmHnetIdKey udm, NrfSbiDto scp, String parameter, String max,
			String time, String tenantId, String siteId, String agentId, String nfName) {
		super();
		this.id = id;
		this.sbi = sbi;
		this.udm = udm;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.agentId = agentId;
		this.nfName = nfName;
	}
	public UdmDataMongoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public UdmHnetIdKey getUdm() {
		return udm;
	}
	public void setUdm(UdmHnetIdKey udm) {
		this.udm = udm;
	}
	public NrfSbiDto getScp() {
		return scp;
	}
	public void setScp(NrfSbiDto scp) {
		this.scp = scp;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	

}

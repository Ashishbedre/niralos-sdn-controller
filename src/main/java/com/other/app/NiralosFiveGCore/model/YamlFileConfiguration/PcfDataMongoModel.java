package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.PcfYamlDto.SbiDataPcfDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;

@Document
public class PcfDataMongoModel {

	@MongoId
	private String id;
	private String db_uri;
	private SbiData sbi;
	private SbiDataPcfDto pcf;
	private NrfSbiDto scp;
	private String parameter;
	private String max;
	private String time;
	private String tenantId;
	private String siteId;
	private String agentId;
	private String nfName;
	public PcfDataMongoModel(String id, String db_uri, SbiData sbi, SbiDataPcfDto pcf, NrfSbiDto scp, String parameter,
			String max, String time, String tenantId, String siteId, String agentId, String nfName) {
		super();
		this.id = id;
		this.db_uri = db_uri;
		this.sbi = sbi;
		this.pcf = pcf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.agentId = agentId;
		this.nfName = nfName;
	}
	public PcfDataMongoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDb_uri() {
		return db_uri;
	}
	public void setDb_uri(String db_uri) {
		this.db_uri = db_uri;
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public SbiDataPcfDto getPcf() {
		return pcf;
	}
	public void setPcf(SbiDataPcfDto pcf) {
		this.pcf = pcf;
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

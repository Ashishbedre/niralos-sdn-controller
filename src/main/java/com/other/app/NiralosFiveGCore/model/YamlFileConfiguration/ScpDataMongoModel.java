package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.t3512;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.SbiDataScpDto;
@Document
public class ScpDataMongoModel {

	@MongoId
	private String id;
	private String db_uri;
	private SbiData sbi;
	private SbiDataScpDto scp;
	private NrfSbiDto nrf;
	private String parameter;
	private String max;
	private t3512 time;
	private String tenantId;
	private String siteId;
	private String agentId;
	private String nfName;
	public ScpDataMongoModel(String db_uri, SbiData sbi, SbiDataScpDto scp, NrfSbiDto nrf, String parameter, String max,
			t3512 time, String tenantId, String siteId, String agentId, String nfName) {
		super();
		this.db_uri = db_uri;
		this.sbi = sbi;
		this.scp = scp;
		this.nrf = nrf;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.agentId = agentId;
		this.nfName = nfName;
	}
	public ScpDataMongoModel() {
		super();
		// TODO Auto-generated constructor stub
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
	public SbiDataScpDto getScp() {
		return scp;
	}
	public void setScp(SbiDataScpDto scp) {
		this.scp = scp;
	}
	public NrfSbiDto getNrf() {
		return nrf;
	}
	public void setNrf(NrfSbiDto nrf) {
		this.nrf = nrf;
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
	public t3512 getTime() {
		return time;
	}
	public void setTime(t3512 time) {
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

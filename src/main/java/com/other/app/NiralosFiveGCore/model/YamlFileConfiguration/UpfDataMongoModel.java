package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.file_stat;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UpfyamlDto.UpfSbiDataScpDto;
@Document
public class UpfDataMongoModel {

	@MongoId
	private String id;
	private file_stat logger;
	private UpfSbiDataScpDto upf;
	private String smf;
	private String parameter;
	private String max;
	private String time;
	private String tenantId;
	private String siteId;
	private String agentId;
	private String nfName;
	public UpfDataMongoModel(String id, file_stat logger, UpfSbiDataScpDto upf, String smf, String parameter,
			String max, String time, String tenantId, String siteId, String agentId, String nfName) {
		super();
		this.id = id;
		this.logger = logger;
		this.upf = upf;
		this.smf = smf;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.agentId = agentId;
		this.nfName = nfName;
	}
	public UpfDataMongoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public file_stat getLogger() {
		return logger;
	}
	public void setLogger(file_stat logger) {
		this.logger = logger;
	}
	public UpfSbiDataScpDto getUpf() {
		return upf;
	}
	public void setUpf(UpfSbiDataScpDto upf) {
		this.upf = upf;
	}
	public String getSmf() {
		return smf;
	}
	public void setSmf(String smf) {
		this.smf = smf;
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

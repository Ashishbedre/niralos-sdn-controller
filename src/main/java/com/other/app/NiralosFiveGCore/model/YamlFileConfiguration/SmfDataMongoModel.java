package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.file_stat;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.UpfPfcpDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.SmfYamlDto.SmfAttributeDateModel;
@Document
public class SmfDataMongoModel {

	@MongoId
	private String id;
	private file_stat logger;
	private SbiData sbi;
	private SmfAttributeDateModel smf;
	private NrfSbiDto scp;
	private UpfPfcpDto upf;
	private String parameter;
	private String max;
	private String time;
	private String tenantId;
	private String siteId;
	private String agentId;
	private String nfName;
	public SmfDataMongoModel(String id, file_stat logger, SbiData sbi, SmfAttributeDateModel smf, NrfSbiDto scp,
			UpfPfcpDto upf, String parameter, String max, String time, String tenantId, String siteId, String agentId,
			String nfName) {
		super();
		this.id = id;
		this.logger = logger;
		this.sbi = sbi;
		this.smf = smf;
		this.scp = scp;
		this.upf = upf;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.agentId = agentId;
		this.nfName = nfName;
	}
	public SmfDataMongoModel() {
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
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public SmfAttributeDateModel getSmf() {
		return smf;
	}
	public void setSmf(SmfAttributeDateModel smf) {
		this.smf = smf;
	}
	public NrfSbiDto getScp() {
		return scp;
	}
	public void setScp(NrfSbiDto scp) {
		this.scp = scp;
	}
	public UpfPfcpDto getUpf() {
		return upf;
	}
	public void setUpf(UpfPfcpDto upf) {
		this.upf = upf;
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

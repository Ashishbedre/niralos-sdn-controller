package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.AmfAttributeDateModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiDataModel;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.file_stat;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.t3512;
@Document
public class AmfYmlModelForMogoDb {

	@MongoId
	private String id;
	private file_stat logger;
	private SbiData sbi;
	private AmfAttributeDateModel amf;
	private SbiDataModel scp;
	private String parameter;
	private String max;
	private String usrsctp;
	private t3512 time;
	private String tenantId;
	private String siteId;
	private String agentId;
	private String nfName;
	public AmfYmlModelForMogoDb(file_stat logger, SbiData sbi, AmfAttributeDateModel amf, SbiDataModel scp,
			String parameter, String max, String usrsctp, t3512 time, String tenantId, String siteId, String agentId,
			String nfName) {
		super();
		this.logger = logger;
		this.sbi = sbi;
		this.amf = amf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.usrsctp = usrsctp;
		this.time = time;
		this.tenantId = tenantId;
		this.siteId = siteId;
		this.agentId = agentId;
		this.nfName = nfName;
	}
	public AmfYmlModelForMogoDb() {
		super();
		// TODO Auto-generated constructor stub
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
	public AmfAttributeDateModel getAmf() {
		return amf;
	}
	public void setAmf(AmfAttributeDateModel amf) {
		this.amf = amf;
	}
	public SbiDataModel getScp() {
		return scp;
	}
	public void setScp(SbiDataModel scp) {
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
	public String getUsrsctp() {
		return usrsctp;
	}
	public void setUsrsctp(String usrsctp) {
		this.usrsctp = usrsctp;
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

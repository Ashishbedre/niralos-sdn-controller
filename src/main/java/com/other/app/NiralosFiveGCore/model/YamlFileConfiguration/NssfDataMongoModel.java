package com.other.app.NiralosFiveGCore.model.YamlFileConfiguration;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.NssfYamlDto.NssfSbiDataDto;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto.NrfSbiDto;
@Document
public class NssfDataMongoModel {
	@MongoId
	private String id;
	private SbiData sbi;
	private NssfSbiDataDto nssf;
	private NrfSbiDto scp;
	private String parameter;
	private String max;
	private String time;
	private String nfName;
	public NssfDataMongoModel(String id, SbiData sbi, NssfSbiDataDto nssf, NrfSbiDto scp, String parameter, String max,
			String time, String nfName) {
		super();
		this.id = id;
		this.sbi = sbi;
		this.nssf = nssf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
		this.nfName = nfName;
	}
	public NssfDataMongoModel() {
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
	public NssfSbiDataDto getNssf() {
		return nssf;
	}
	public void setNssf(NssfSbiDataDto nssf) {
		this.nssf = nssf;
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
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	
	

}

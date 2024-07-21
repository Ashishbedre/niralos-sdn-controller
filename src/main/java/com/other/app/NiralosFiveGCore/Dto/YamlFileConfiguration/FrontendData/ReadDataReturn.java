package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.FrontendData;

import java.util.List;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AmfYmlModelForMogoDb;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AusfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.BsfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NrfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NssfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.PcfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.ScpDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdmDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdrDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UpfDataMongoModel;

public class ReadDataReturn {

	List<AmfYmlModelForMogoDb> amf;
	List<ScpDataMongoModel> scp;
	List<PcfDataMongoModel>	pcf;
	List<AusfDataMongoModel> ausf;
	List<UdrDataMongoModel>	udr;
	List<BsfDataMongoModel>	bsf ;
	List<UpfDataMongoModel> upf;
	List<UdmDataMongoModel> udm;
	List<SmfDataMongoModel> smf;
	List<NssfDataMongoModel> nssf;
	List<NrfDataMongoModel> nrf;
	public ReadDataReturn(List<AmfYmlModelForMogoDb> amf, List<ScpDataMongoModel> scp, List<PcfDataMongoModel> pcf,
			List<AusfDataMongoModel> ausf, List<UdrDataMongoModel> udr, List<BsfDataMongoModel> bsf,
			List<UpfDataMongoModel> upf, List<UdmDataMongoModel> udm, List<SmfDataMongoModel> smf,
			List<NssfDataMongoModel> nssf, List<NrfDataMongoModel> nrf) {
		super();
		this.amf = amf;
		this.scp = scp;
		this.pcf = pcf;
		this.ausf = ausf;
		this.udr = udr;
		this.bsf = bsf;
		this.upf = upf;
		this.udm = udm;
		this.smf = smf;
		this.nssf = nssf;
		this.nrf = nrf;
	}
	public ReadDataReturn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<AmfYmlModelForMogoDb> getAmf() {
		return amf;
	}
	public void setAmf(List<AmfYmlModelForMogoDb> amf) {
		this.amf = amf;
	}
	public List<ScpDataMongoModel> getScp() {
		return scp;
	}
	public void setScp(List<ScpDataMongoModel> scp) {
		this.scp = scp;
	}
	public List<PcfDataMongoModel> getPcf() {
		return pcf;
	}
	public void setPcf(List<PcfDataMongoModel> pcf) {
		this.pcf = pcf;
	}
	public List<AusfDataMongoModel> getAusf() {
		return ausf;
	}
	public void setAusf(List<AusfDataMongoModel> ausf) {
		this.ausf = ausf;
	}
	public List<UdrDataMongoModel> getUdr() {
		return udr;
	}
	public void setUdr(List<UdrDataMongoModel> udr) {
		this.udr = udr;
	}
	public List<BsfDataMongoModel> getBsf() {
		return bsf;
	}
	public void setBsf(List<BsfDataMongoModel> bsf) {
		this.bsf = bsf;
	}
	public List<UpfDataMongoModel> getUpf() {
		return upf;
	}
	public void setUpf(List<UpfDataMongoModel> upf) {
		this.upf = upf;
	}
	public List<UdmDataMongoModel> getUdm() {
		return udm;
	}
	public void setUdm(List<UdmDataMongoModel> udm) {
		this.udm = udm;
	}
	public List<SmfDataMongoModel> getSmf() {
		return smf;
	}
	public void setSmf(List<SmfDataMongoModel> smf) {
		this.smf = smf;
	}
	public List<NssfDataMongoModel> getNssf() {
		return nssf;
	}
	public void setNssf(List<NssfDataMongoModel> nssf) {
		this.nssf = nssf;
	}
	public List<NrfDataMongoModel> getNrf() {
		return nrf;
	}
	public void setNrf(List<NrfDataMongoModel> nrf) {
		this.nrf = nrf;
	}
	
	
	
	
	
}

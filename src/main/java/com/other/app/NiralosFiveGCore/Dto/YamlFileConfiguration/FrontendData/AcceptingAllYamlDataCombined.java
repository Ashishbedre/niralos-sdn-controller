package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.FrontendData;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AmfYmlModelForMogoDb;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NssfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;
import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UpfDataMongoModel;

public class AcceptingAllYamlDataCombined {

	AmfYmlModelForMogoDb amf;
	UpfDataMongoModel upf;
	NssfDataMongoModel nssf;
	SmfDataMongoModel smf;
	public AcceptingAllYamlDataCombined(AmfYmlModelForMogoDb amf, UpfDataMongoModel upf, NssfDataMongoModel nssf,
			SmfDataMongoModel smf) {
		super();
		this.amf = amf;
		this.upf = upf;
		this.nssf = nssf;
		this.smf = smf;
	}
	public AcceptingAllYamlDataCombined() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AmfYmlModelForMogoDb getAmf() {
		return amf;
	}
	public void setAmf(AmfYmlModelForMogoDb amf) {
		this.amf = amf;
	}
	public UpfDataMongoModel getUpf() {
		return upf;
	}
	public void setUpf(UpfDataMongoModel upf) {
		this.upf = upf;
	}
	public NssfDataMongoModel getNssf() {
		return nssf;
	}
	public void setNssf(NssfDataMongoModel nssf) {
		this.nssf = nssf;
	}
	public SmfDataMongoModel getSmf() {
		return smf;
	}
	public void setSmf(SmfDataMongoModel smf) {
		this.smf = smf;
	}
	
	
	

}



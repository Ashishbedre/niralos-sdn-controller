package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class guamiDataModel {

	public MccAndMnc plmn_id;
	public RegionAndSet amf_id;
	public guamiDataModel(MccAndMnc plmn_id, RegionAndSet amf_id) {
		super();
		this.plmn_id = plmn_id;
		this.amf_id = amf_id;
	}
	public guamiDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MccAndMnc getPlmn_id() {
		return plmn_id;
	}
	public void setPlmn_id(MccAndMnc plmn_id) {
		this.plmn_id = plmn_id;
	}
	public RegionAndSet getAmf_id() {
		return amf_id;
	}
	public void setAmf_id(RegionAndSet amf_id) {
		this.amf_id = amf_id;
	}
	@Override
	public String toString() {
		return "guamiDataModel [plmn_id=" + plmn_id + ", amf_id=" + amf_id + "]";
	}
	
	
	
	

}

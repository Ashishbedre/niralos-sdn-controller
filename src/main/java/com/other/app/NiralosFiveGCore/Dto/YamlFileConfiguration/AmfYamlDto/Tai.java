package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class Tai {

	public MccAndMnc plmn_id;
	public Integer tac;
	public Tai(MccAndMnc plmn_id, Integer tac) {
		super();
		this.plmn_id = plmn_id;
		this.tac = tac;
	}
	public Tai() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MccAndMnc getPlmn_id() {
		return plmn_id;
	}
	public void setPlmn_id(MccAndMnc plmn_id) {
		this.plmn_id = plmn_id;
	}
	public Integer getTac() {
		return tac;
	}
	public void setTac(Integer tac) {
		this.tac = tac;
	}
	@Override
	public String toString() {
		return "Tai [plmn_id=" + plmn_id + ", tac=" + tac + "]";
	}
	
	
	

}

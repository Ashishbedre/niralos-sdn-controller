package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

import java.util.List;

public class Security {

	public List<String> integrity_order;
	public List<String> ciphering_order	;
	public Security(List<String> integrity_order, List<String> ciphering_order) {
		super();
		this.integrity_order = integrity_order;
		this.ciphering_order = ciphering_order;
	}
	public Security() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<String> getIntegrity_order() {
		return integrity_order;
	}
	public void setIntegrity_order(List<String> integrity_order) {
		this.integrity_order = integrity_order;
	}
	public List<String> getCiphering_order() {
		return ciphering_order;
	}
	public void setCiphering_order(List<String> ciphering_order) {
		this.ciphering_order = ciphering_order;
	}
	@Override
	public String toString() {
		return "Security [integrity_order=" + integrity_order + ", ciphering_order=" + ciphering_order + "]";
	}
	
	
	
	

}

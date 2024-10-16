package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class Nfsupport {

	public Boolean enable;
	public Integer length;
	public Integer imsVoPS;
	public Integer emc;
	public Integer emf;
	public Integer iwkN26;
	public Integer mpsi;
	public Integer emcN3;
	public Integer mcsi;
	public Nfsupport(Boolean enable, Integer length, Integer imsVoPS, Integer emc, Integer emf, Integer iwkN26,
			Integer mpsi, Integer emcN3, Integer mcsi) {
		super();
		this.enable = enable;
		this.length = length;
		this.imsVoPS = imsVoPS;
		this.emc = emc;
		this.emf = emf;
		this.iwkN26 = iwkN26;
		this.mpsi = mpsi;
		this.emcN3 = emcN3;
		this.mcsi = mcsi;
	}
	public Nfsupport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getImsVoPS() {
		return imsVoPS;
	}
	public void setImsVoPS(Integer imsVoPS) {
		this.imsVoPS = imsVoPS;
	}
	public Integer getEmc() {
		return emc;
	}
	public void setEmc(Integer emc) {
		this.emc = emc;
	}
	public Integer getEmf() {
		return emf;
	}
	public void setEmf(Integer emf) {
		this.emf = emf;
	}
	public Integer getIwkN26() {
		return iwkN26;
	}
	public void setIwkN26(Integer iwkN26) {
		this.iwkN26 = iwkN26;
	}
	public Integer getMpsi() {
		return mpsi;
	}
	public void setMpsi(Integer mpsi) {
		this.mpsi = mpsi;
	}
	public Integer getEmcN3() {
		return emcN3;
	}
	public void setEmcN3(Integer emcN3) {
		this.emcN3 = emcN3;
	}
	public Integer getMcsi() {
		return mcsi;
	}
	public void setMcsi(Integer mcsi) {
		this.mcsi = mcsi;
	}
	@Override
	public String toString() {
		return "Nfsupport [enable=" + enable + ", length=" + length + ", imsVoPS=" + imsVoPS + ", emc=" + emc + ", emf="
				+ emf + ", iwkN26=" + iwkN26 + ", mpsi=" + mpsi + ", emcN3=" + emcN3 + ", mcsi=" + mcsi + "]";
	}
	
	

}

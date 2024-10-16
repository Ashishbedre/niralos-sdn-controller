package com.other.app.NiralosFiveGCore.model.UeHisotry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uepdu_cause_info")
public class UePduCauseInfoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String uecauselist;
    private String pducauselist;
    private int repeated;
	@Column(name = "nf_type")
	String nfType;
	@Column(name = "nf_name")
	String nfName;
	
	public String getNfType() {
		return nfType;
	}
	public void setNfType(String nfType) {
		this.nfType = nfType;
	}
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUecauselist() {
		return uecauselist;
	}
	public void setUecauselist(String uecauselist) {
		this.uecauselist = uecauselist;
	}
	public String getPducauselist() {
		return pducauselist;
	}
	public void setPducauselist(String pducauselist) {
		this.pducauselist = pducauselist;
	}
	public int getRepeated() {
		return repeated;
	}
	public void setRepeated(int repeated) {
		this.repeated = repeated;
	}
	
}

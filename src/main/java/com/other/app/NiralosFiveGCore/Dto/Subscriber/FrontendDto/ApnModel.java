package com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto;

public class ApnModel {

	// Fields
	private String apn;
	private Integer apn_ambr_dl;
	private Integer apn_ambr_ul;
//	private Integer arp_priority;
//	private Integer ip_version;
//	private String nidd_mechanism;
//	private boolean arp_preemption_capability;
//	private String nidd_rds;
//	private String pgw_address;
//	private boolean arp_preemption_vulnerability;
//	private String nidd_preferred_data_mode;
//	private String sgw_address;
//	private String charging_rule_list;
//	private String last_modified;
//	private String charging_characteristics;
//	private boolean nbiot;
//	private String nidd_scef_id;
	private Integer apn_id;
//	private String nidd_scef_realm;
//	private Integer qci;

	// Default constructor (required for Jackson deserialization)
	public ApnModel() {
	}

	// Parameterized constructor
	public ApnModel(String apn, Integer apn_ambr_dl, Integer apn_ambr_ul
//			,Integer arp_priority, Integer ip_version, String nidd_mechanism, 
//			boolean arp_preemption_capability, String nidd_rds, String pgw_address,
//			boolean arp_preemption_vulnerability, String nidd_preferred_data_mode, String sgw_address,
//			String charging_rule_list, String last_modified, String charging_characteristics, 
//			boolean nbiot, String nidd_scef_id,
			,Integer apn_id
//			,String nidd_scef_realm, Integer qci
			) {
		this.apn = apn;
		this.apn_ambr_dl = apn_ambr_dl;
		this.apn_ambr_ul = apn_ambr_ul;
//		this.arp_priority = arp_priority;
//		this.ip_version = ip_version;
//		this.nidd_mechanism = nidd_mechanism;
//		this.arp_preemption_capability = arp_preemption_capability;
//		this.nidd_rds = nidd_rds;
//		this.pgw_address = pgw_address;
//		this.arp_preemption_vulnerability = arp_preemption_vulnerability;
//		this.nidd_preferred_data_mode = nidd_preferred_data_mode;
//		this.sgw_address = sgw_address;
//		this.charging_rule_list = charging_rule_list;
//		this.last_modified = last_modified;
//		this.charging_characteristics = charging_characteristics;
//		this.nbiot = nbiot;
//		this.nidd_scef_id = nidd_scef_id;
//		this.apn_id = apn_id;
//		this.nidd_scef_realm = nidd_scef_realm;
//		this.qci = qci;
	}

	// Getters and Setters
	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public Integer getApn_ambr_dl() {
		return apn_ambr_dl;
	}

	public void setApn_ambr_dl(Integer apn_ambr_dl) {
		this.apn_ambr_dl = apn_ambr_dl;
	}

	public Integer getApn_ambr_ul() {
		return apn_ambr_ul;
	}

	public void setApn_ambr_ul(Integer apn_ambr_ul) {
		this.apn_ambr_ul = apn_ambr_ul;
	}

//	public Integer getArp_priority() {
//		return arp_priority;
//	}
//
//	public void setArp_priority(Integer arp_priority) {
//		this.arp_priority = arp_priority;
//	}
//
//	public Integer getIp_version() {
//		return ip_version;
//	}
//
//	public void setIp_version(Integer ip_version) {
//		this.ip_version = ip_version;
//	}
//
//	public String getNidd_mechanism() {
//		return nidd_mechanism;
//	}
//
//	public void setNidd_mechanism(String nidd_mechanism) {
//		this.nidd_mechanism = nidd_mechanism;
//	}
//
//	public boolean isArp_preemption_capability() {
//		return arp_preemption_capability;
//	}
//
//	public void setArp_preemption_capability(boolean arp_preemption_capability) {
//		this.arp_preemption_capability = arp_preemption_capability;
//	}
//
//	public String getNidd_rds() {
//		return nidd_rds;
//	}
//
//	public void setNidd_rds(String nidd_rds) {
//		this.nidd_rds = nidd_rds;
//	}
//
//	public String getPgw_address() {
//		return pgw_address;
//	}
//
//	public void setPgw_address(String pgw_address) {
//		this.pgw_address = pgw_address;
//	}
//
//	public boolean isArp_preemption_vulnerability() {
//		return arp_preemption_vulnerability;
//	}
//
//	public void setArp_preemption_vulnerability(boolean arp_preemption_vulnerability) {
//		this.arp_preemption_vulnerability = arp_preemption_vulnerability;
//	}
//
//	public String getNidd_preferred_data_mode() {
//		return nidd_preferred_data_mode;
//	}
//
//	public void setNidd_preferred_data_mode(String nidd_preferred_data_mode) {
//		this.nidd_preferred_data_mode = nidd_preferred_data_mode;
//	}
//
//	public String getSgw_address() {
//		return sgw_address;
//	}
//
//	public void setSgw_address(String sgw_address) {
//		this.sgw_address = sgw_address;
//	}
//
//	public String getCharging_rule_list() {
//		return charging_rule_list;
//	}
//
//	public void setCharging_rule_list(String charging_rule_list) {
//		this.charging_rule_list = charging_rule_list;
//	}
//
//	public String getLast_modified() {
//		return last_modified;
//	}
//
//	public void setLast_modified(String last_modified) {
//		this.last_modified = last_modified;
//	}
//
//	public String getCharging_characteristics() {
//		return charging_characteristics;
//	}
//
//	public void setCharging_characteristics(String charging_characteristics) {
//		this.charging_characteristics = charging_characteristics;
//	}
//
//	public boolean isNbiot() {
//		return nbiot;
//	}
//
//	public void setNbiot(boolean nbiot) {
//		this.nbiot = nbiot;
//	}
//
//	public String getNidd_scef_id() {
//		return nidd_scef_id;
//	}
//
//	public void setNidd_scef_id(String nidd_scef_id) {
//		this.nidd_scef_id = nidd_scef_id;
//	}
//
	public Integer getApn_id() {
		return apn_id;
	}

	public void setApn_id(Integer apn_id) {
		this.apn_id = apn_id;
	}
//
//	public String getNidd_scef_realm() {
//		return nidd_scef_realm;
//	}
//
//	public void setNidd_scef_realm(String nidd_scef_realm) {
//		this.nidd_scef_realm = nidd_scef_realm;
//	}
//
//	public Integer getQci() {
//		return qci;
//	}
//
//	public void setQci(Integer qci) {
//		this.qci = qci;
//	}
}

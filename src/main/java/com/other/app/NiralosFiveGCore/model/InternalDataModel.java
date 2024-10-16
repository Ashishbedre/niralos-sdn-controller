package com.other.app.NiralosFiveGCore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "internal_data")
public class InternalDataModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	@Column(name = "niral_controller_client_id")
	private String niralControllerClientId;
	@Column(name = "amf_ip")
	private String amfIp;
	@Column(name = "amf_port")
	private String amfPort;
	@Column(name = "smf_ip")
	private String smfIp;
	@Column(name = "smf_port")
	private String smfPort;
	@Column(name = "nrf_ip")
	private String nrfIp;
	@Column(name = "nrf_port")
	private String nrfPort;
	@Column(name = "upf_ip")
	private String upfIp;
	@Column(name = "upf_port")
	private String upfPort;
	@Column(name = "udr_ip")
	private String udrIp;
	@Column(name = "udr_port")
	private String udrPort;
	@Column(name = "nssf_ip")
	private String nssfIp;
	@Column(name = "nssf_port")
	private String nssfPort;
	@Column(name = "upg_ip")
	private String upgIp;
	@Column(name = "upg_port")
	private String upgPort;
	@Column(name = "niral_controller_ip")
	private String niralControllerIp;
	@Column(name = "niral_globalcontroller_port")
	private String niralGlobalControllerport;


	
	@Column(name = "pcf_ip")
	private String pcfIp;
	@Column(name = "pcf_port")
	private String pcfPort;
	@Column(name = "bsf_ip")
	private String bsfIp;
	@Column(name = "bsf_port")
	private String bsfPort;
	@Column(name = "udm_ip")
	private String udmIp;
	@Column(name = "udm_port")
	private String udmPort;
	@Column(name = "scpIp")
	private String scpIp;
	@Column(name = "scp_port")
	private String scpPort;
	@Column(name = "ausf_ip")
	private String ausfIp;
	@Column(name = "ausf_port")
	private String ausfPort;
	
	@Column(name = "nef_ip")
	private String nefIp;
	@Column(name = "nef_port")
	private String nefPort;
	
	@Column(name = "nwdaf_ip")
	private String nwdafIp;
	@Column(name = "nwdaf_port")
	private String nwdafPort;
	
	@Column(name = "pcscf_ip")
	private String pcscfIp;
	@Column(name = "pcscf_port")
	private String pcscfPort;
	
	@Column(name = "icscf_ip")
	private String icscfIp;
	@Column(name = "icscf_port")
	private String icscfPort;
	
	@Column(name = "scscf_ip")
	private String scscfIp;
	@Column(name = "scscf_port")
	private String scscfPort;
	
	
	public String getPcscfIp() {
		return pcscfIp;
	}
	public void setPcscfIp(String pcscfIp) {
		this.pcscfIp = pcscfIp;
	}
	public String getPcscfPort() {
		return pcscfPort;
	}
	public void setPcscfPort(String pcscfPort) {
		this.pcscfPort = pcscfPort;
	}
	public String getIcscfIp() {
		return icscfIp;
	}
	public void setIcscfIp(String icscfIp) {
		this.icscfIp = icscfIp;
	}
	public String getIcscfPort() {
		return icscfPort;
	}
	public void setIcscfPort(String icscfPort) {
		this.icscfPort = icscfPort;
	}
	public String getScscfIp() {
		return scscfIp;
	}
	public void setScscfIp(String scscfIp) {
		this.scscfIp = scscfIp;
	}
	public String getScscfPort() {
		return scscfPort;
	}
	public void setScscfPort(String scscfPort) {
		this.scscfPort = scscfPort;
	}
	@Column(name = "n3iwf_ip")
	private String n3iwfIp;
	public String getNwdafIp() {
		return nwdafIp;
	}
	public void setNwdafIp(String nwdafIp) {
		this.nwdafIp = nwdafIp;
	}
	public String getNwdafPort() {
		return nwdafPort;
	}
	public void setNwdafPort(String nwdafPort) {
		this.nwdafPort = nwdafPort;
	}
	public String getN3iwfIp() {
		return n3iwfIp;
	}
	public void setN3iwfIp(String n3iwfIp) {
		this.n3iwfIp = n3iwfIp;
	}
	public String getN3iwfPort() {
		return n3iwfPort;
	}
	public void setN3iwfPort(String n3iwfPort) {
		this.n3iwfPort = n3iwfPort;
	}
	@Column(name = "n3iwf_port")
	private String n3iwfPort;
	
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNiralControllerClientId() {
		return niralControllerClientId;
	}
	public void setNiralControllerClientId(String niralControllerClientId) {
		this.niralControllerClientId = niralControllerClientId;
	}
	public String getAmfIp() {
		return amfIp;
	}
	public void setAmfIp(String amfIp) {
		this.amfIp = amfIp;
	}
	public String getAmfPort() {
		return amfPort;
	}
	public void setAmfPort(String amfPort) {
		this.amfPort = amfPort;
	}
	public String getSmfIp() {
		return smfIp;
	}
	public void setSmfIp(String smfIp) {
		this.smfIp = smfIp;
	}
	public String getSmfPort() {
		return smfPort;
	}
	public void setSmfPort(String smfPort) {
		this.smfPort = smfPort;
	}
	public String getNrfIp() {
		return nrfIp;
	}
	public void setNrfIp(String nrfIp) {
		this.nrfIp = nrfIp;
	}
	public String getNrfPort() {
		return nrfPort;
	}
	public void setNrfPort(String nrfPort) {
		this.nrfPort = nrfPort;
	}
	public String getUpfIp() {
		return upfIp;
	}
	public void setUpfIp(String upfIp) {
		this.upfIp = upfIp;
	}
	public String getUpfPort() {
		return upfPort;
	}
	public void setUpfPort(String upfPort) {
		this.upfPort = upfPort;
	}
	public String getUdrIp() {
		return udrIp;
	}
	public void setUdrIp(String udrIp) {
		this.udrIp = udrIp;
	}
	public String getUdrPort() {
		return udrPort;
	}
	public void setUdrPort(String udrPort) {
		this.udrPort = udrPort;
	}
	public String getNssfIp() {
		return nssfIp;
	}
	public void setNssfIp(String nssfIp) {
		this.nssfIp = nssfIp;
	}
	public String getNssfPort() {
		return nssfPort;
	}
	public void setNssfPort(String nssfPort) {
		this.nssfPort = nssfPort;
	}
	public String getUpgIp() {
		return upgIp;
	}
	public void setUpgIp(String upgIp) {
		this.upgIp = upgIp;
	}
	public String getUpgPort() {
		return upgPort;
	}
	public void setUpgPort(String upgPort) {
		this.upgPort = upgPort;
	}
	public String getNiralControllerIp() {
		return niralControllerIp;
	}
	public void setNiralControllerIp(String niralControllerIp) {
		this.niralControllerIp = niralControllerIp;
	}
	public String getPcfIp() {
		return pcfIp;
	}
	public void setPcfIp(String pcfIp) {
		this.pcfIp = pcfIp;
	}
	public String getPcfPort() {
		return pcfPort;
	}
	public void setPcfPort(String pcfPort) {
		this.pcfPort = pcfPort;
	}
	public String getBsfIp() {
		return bsfIp;
	}
	public void setBsfIp(String bsfIp) {
		this.bsfIp = bsfIp;
	}
	public String getBsfPort() {
		return bsfPort;
	}
	public void setBsfPort(String bsfPort) {
		this.bsfPort = bsfPort;
	}
	public String getUdmIp() {
		return udmIp;
	}
	public void setUdmIp(String udmIp) {
		this.udmIp = udmIp;
	}
	public String getUdmPort() {
		return udmPort;
	}
	public void setUdmPort(String udmPort) {
		this.udmPort = udmPort;
	}
	public String getScpIp() {
		return scpIp;
	}
	public void setScpIp(String scpIp) {
		this.scpIp = scpIp;
	}
	public String getScpPort() {
		return scpPort;
	}
	public void setScpPort(String scpPort) {
		this.scpPort = scpPort;
	}
	public String getAusfIp() {
		return ausfIp;
	}
	public void setAusfIp(String ausfIp) {
		this.ausfIp = ausfIp;
	}
	public String getAusfPort() {
		return ausfPort;
	}
	public void setAusfPort(String ausfPort) {
		this.ausfPort = ausfPort;
	}
	
	
//	public InternalDataModel(long id, String niralControllerClientId, String amfIp, String amfPort, String smfIp,
//			String smfPort, String nrfIp, String nrfPort, String upfIp, String upfPort, String udrIp, String udrPort,
//			String nssfIp, String nssfPort, String upgIp, String upgPort, String niralControllerIp,
//			String niralGlobalControllerport, String pcfIp, String pcfPort, String bsfIp, String bsfPort, String udmIp,
//			String udmPort, String scpIp, String scpPort, String ausfIp, String ausfPort) {
//		super();
//		this.id = id;
//		this.niralControllerClientId = niralControllerClientId;
//		this.amfIp = amfIp;
//		this.amfPort = amfPort;
//		this.smfIp = smfIp;
//		this.smfPort = smfPort;
//		this.nrfIp = nrfIp;
//		this.nrfPort = nrfPort;
//		this.upfIp = upfIp;
//		this.upfPort = upfPort;
//		this.udrIp = udrIp;
//		this.udrPort = udrPort;
//		this.nssfIp = nssfIp;
//		this.nssfPort = nssfPort;
//		this.upgIp = upgIp;
//		this.upgPort = upgPort;
//		this.niralControllerIp = niralControllerIp;
//		this.niralGlobalControllerport = niralGlobalControllerport;
//		this.pcfIp = pcfIp;
//		this.pcfPort = pcfPort;
//		this.bsfIp = bsfIp;
//		this.bsfPort = bsfPort;
//		this.udmIp = udmIp;
//		this.udmPort = udmPort;
//		this.scpIp = scpIp;
//		this.scpPort = scpPort;
//		this.ausfIp = ausfIp;
//		this.ausfPort = ausfPort;
//	}
	
	public InternalDataModel() {
		super();
	}


	public String getNiralGlobalControllerport() {
		return niralGlobalControllerport;
	}
	public InternalDataModel(long id, String niralControllerClientId, String amfIp, String amfPort, String smfIp,
		String smfPort, String nrfIp, String nrfPort, String upfIp, String upfPort, String udrIp, String udrPort,
		String nssfIp, String nssfPort, String upgIp, String upgPort, String niralControllerIp,
		String niralGlobalControllerport, String pcfIp, String pcfPort, String bsfIp, String bsfPort, String udmIp,
		String udmPort, String scpIp, String scpPort, String ausfIp, String ausfPort, String nefIp, String nefPort,
		String nwdafIp, String nwdafPort, String pcscfIp, String pcscfPort, String icscfIp, String icscfPort,
		String scscfIp, String scscfPort, String n3iwfIp, String n3iwfPort) {
	super();
	this.id = id;
	this.niralControllerClientId = niralControllerClientId;
	this.amfIp = amfIp;
	this.amfPort = amfPort;
	this.smfIp = smfIp;
	this.smfPort = smfPort;
	this.nrfIp = nrfIp;
	this.nrfPort = nrfPort;
	this.upfIp = upfIp;
	this.upfPort = upfPort;
	this.udrIp = udrIp;
	this.udrPort = udrPort;
	this.nssfIp = nssfIp;
	this.nssfPort = nssfPort;
	this.upgIp = upgIp;
	this.upgPort = upgPort;
	this.niralControllerIp = niralControllerIp;
	this.niralGlobalControllerport = niralGlobalControllerport;
	this.pcfIp = pcfIp;
	this.pcfPort = pcfPort;
	this.bsfIp = bsfIp;
	this.bsfPort = bsfPort;
	this.udmIp = udmIp;
	this.udmPort = udmPort;
	this.scpIp = scpIp;
	this.scpPort = scpPort;
	this.ausfIp = ausfIp;
	this.ausfPort = ausfPort;
	this.nefIp = nefIp;
	this.nefPort = nefPort;
	this.nwdafIp = nwdafIp;
	this.nwdafPort = nwdafPort;
	this.pcscfIp = pcscfIp;
	this.pcscfPort = pcscfPort;
	this.icscfIp = icscfIp;
	this.icscfPort = icscfPort;
	this.scscfIp = scscfIp;
	this.scscfPort = scscfPort;
	this.n3iwfIp = n3iwfIp;
	this.n3iwfPort = n3iwfPort;
}
	public void setNiralGlobalControllerport(String niralGlobalControllerport) {
		this.niralGlobalControllerport = niralGlobalControllerport;
	}
	
	public InternalDataModel(String niralControllerClientId) {
		this.niralControllerClientId = niralControllerClientId;
	}
	public String getNefIp() {
		return nefIp;
	}
	public void setNefIp(String nefIp) {
		this.nefIp = nefIp;
	}
	public String getNefPort() {
		return nefPort;
	}
	public void setNefPort(String nefPort) {
		this.nefPort = nefPort;
	}

 
	
	
	
}

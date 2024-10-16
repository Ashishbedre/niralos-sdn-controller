package com.other.app.NiralosFiveGCore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="nf_status")
public class NetworkTopologyModel {

	
			@Id
	    	@GeneratedValue(strategy = GenerationType.IDENTITY)
			@Column(name = "id")
			private Long id;
			@Column(name="nf_name")
			private String nfName;
			@Column(name="nf_type")
		    private String nfType;
			@Column(name="nf_ip")
		    private String nfIp;
			@Column(name="nf_status")
		    private String nfStatus;
			@Column(name="device")
			private String device;
		
			
			public NetworkTopologyModel(String nfName, String nfType, String nfIp, String nfStatus, String device) {
				super();
				this.nfName = nfName;
				this.nfType = nfType;
				this.nfIp = nfIp;
				this.nfStatus = nfStatus;
				this.device = device;
			}
			public NetworkTopologyModel() {
				super();
			}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getNfName() {
				return nfName;
			}
			public void setNfName(String nfName) {
				this.nfName = nfName;
			}
			public String getNfType() {
				return nfType;
			}
			public void setNfType(String nfType) {
				this.nfType = nfType;
			}
			public String getNfIp() {
				return nfIp;
			}
			public void setNfIp(String nfIp) {
				this.nfIp = nfIp;
			}
			public String getNfStatus() {
				return nfStatus;
			}
			public void setNfStatus(String nfStatus) {
				this.nfStatus = nfStatus;
			}
			public String getDevice() {
				return device;
			}
			public void setDevice(String device) {
				this.device = device;
			}
			
			
			

			
			
			
}

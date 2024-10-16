package com.other.app.NiralosFiveGCore.Dto.Topology;

import java.util.List;


public class NrfInfoDto {
	private String nfName;
    private String nfType;
    private String nfIp;
    private String nfStatus;
	private String device;
	    private List<GnBDTO> children;
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
		public List<GnBDTO> getChildren() {
			return children;
		}
		public void setChildren(List<GnBDTO> children) {
			this.children = children;
		}

		
		
		
		
}

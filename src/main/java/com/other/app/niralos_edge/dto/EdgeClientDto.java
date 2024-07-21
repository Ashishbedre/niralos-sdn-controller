package com.other.app.niralos_edge.dto;


public class EdgeClientDto {
	
	
	private String edgeClientId;
	
	private String tenantId;
	
	private String siteId;

	public EdgeClientDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EdgeClientDto(String edgeClientId, String tenantId, String siteId) {
		super();
		this.edgeClientId = edgeClientId;
		this.tenantId = tenantId;
		this.siteId = siteId;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	

}

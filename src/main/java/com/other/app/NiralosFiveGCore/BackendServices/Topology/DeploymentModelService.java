package com.other.app.NiralosFiveGCore.BackendServices.Topology;

public interface DeploymentModelService {

	void updateDeploymentSubZoneId();

	void updateSubZoneId();
	
	public void zoneDetails(String agentId, String zoneName, String subzoneName, String tenantId, String siteId);
}

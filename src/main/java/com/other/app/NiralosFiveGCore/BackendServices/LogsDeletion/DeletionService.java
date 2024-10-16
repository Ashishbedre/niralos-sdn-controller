package com.other.app.NiralosFiveGCore.BackendServices.LogsDeletion;

public interface DeletionService {

	public void DeletionThroughputData();
	public void DeletionUeGraphData();
	public void DeletionGnbGraphData();
	public void DeletionErrorgraphData();
	public void DeletionUpgstacgraphData();
	
	 public void CheckUpgerrorAndDeltaErrorSite(String tenantName);
	 
	 public void CheckUpferrorAndDeltaErrorSite(String tenantName);
	void cleanupOldDataUeHistory();
	void cleanupOldDataGnbList();


}

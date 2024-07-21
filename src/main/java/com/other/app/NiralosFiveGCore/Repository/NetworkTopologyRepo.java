package com.other.app.NiralosFiveGCore.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;

public interface NetworkTopologyRepo extends JpaRepository<NetworkTopologyModel, Long>{

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE NetworkTopologyModel i SET i.agentId = ?1, i.nfIp = ?2,"
			+ "i.nfType = ?3, i.nfStatus = ?4, i.tenantId=?6, i.siteId=?7, i.device=?8 WHERE i.nfName = ?5")
	public void updateAmfStatus(String agentId, String nfIp, String nfType, String nfStatus, String nfName, String tenantId,
			String siteId, String device);
	
	@Query("SELECT COUNT(n) FROM NetworkTopologyModel n WHERE n.nfType = ?1 AND n.nfIp=?2 AND n.tenantId=?3 AND n.siteId = ?4 AND n.agentId=?5")
	public Integer countofNetworkData(String nfType, String nfIp, String tenant, String siteId,String agentId);
	
}

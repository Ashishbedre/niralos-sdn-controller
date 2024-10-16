package com.other.app.NiralosFiveGCore.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;

public interface NetworkTopologyRepo extends JpaRepository<NetworkTopologyModel, Long>{

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE NetworkTopologyModel i SET i.nfIp = ?1,"
			+ "i.nfType = ?2, i.nfStatus = ?3, i.device=?5 WHERE i.nfName = ?4")
	public void updateAmfStatus( String nfIp, String nfType, String nfStatus, String nfName, String device);
	
	@Query("SELECT COUNT(n) FROM NetworkTopologyModel n WHERE n.nfType = ?1 AND n.nfIp=?2")
	public Integer countofNetworkData(String nfType, String nfIp);
	
}

package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorGraphModel;

public interface UpfErrorGraphModelRepository extends JpaRepository<UpfErrorGraphModel, Long> {

//	List<UpfErrorGraphModel> findByTenentNameAndSiteId(String tenentName, String siteId);
	
	@Query(value = "SELECT * FROM upf_errorgraph_model u WHERE  u.reason = ?1 ORDER BY u.date_time DESC LIMIT 20", nativeQuery = true)
	public List<UpfErrorGraphModel> getfindByReason(String reason);
	


}

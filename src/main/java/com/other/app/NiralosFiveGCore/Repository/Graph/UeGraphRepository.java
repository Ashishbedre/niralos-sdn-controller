package com.other.app.NiralosFiveGCore.Repository.Graph;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.Graph.UeGraphModel;

@Repository
public interface UeGraphRepository extends JpaRepository<UeGraphModel, Long>{
//  Query used to fetch a tenants ue graph data for last 60 mins in descending order
	@Query(value = "SELECT * FROM ue_graph u WHERE u.tenant_id = ?1 ORDER BY u.date_time DESC LIMIT ?2", nativeQuery = true)
	public ArrayList<UeGraphModel> returnUeData(Long tenantId, Integer samples);

	@Query(value ="SELECT COUNT(*) FROM ue_graph", nativeQuery = true)
	public Integer checkUeDataLinesCount();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value ="DELETE FROM ue_graph  WHERE serial_id ORDER BY serial_id ASC LIMIT ?1", nativeQuery = true)
	public Integer deleteUeData(Integer limit);
}

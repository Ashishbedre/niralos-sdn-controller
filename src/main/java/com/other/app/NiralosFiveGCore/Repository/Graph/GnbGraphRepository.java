package com.other.app.NiralosFiveGCore.Repository.Graph;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.Graph.GnbGraphModel;

@Repository
public interface GnbGraphRepository extends JpaRepository<GnbGraphModel, Long>{
	
//  Query used to fetch a tenants gnb graph data for last 60 mins in descending order
	@Query(value = "SELECT * FROM gnb_graph g WHERE g.tenant_id = ?1 ORDER BY g.date_time DESC LIMIT ?2", nativeQuery = true)
	public ArrayList<GnbGraphModel> returnGnbData(Long tenantId, Integer limit);

	@Query(value ="SELECT COUNT(*) FROM gnb_graph", nativeQuery = true)
	public Integer checkGnbDataLinesCount();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value ="DELETE FROM gnb_graph  WHERE serial_id ORDER BY serial_id ASC LIMIT ?1", nativeQuery = true)
	public Integer deleteGnbData(Integer limit);
}

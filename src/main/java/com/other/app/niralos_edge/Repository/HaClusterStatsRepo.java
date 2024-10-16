package com.other.app.niralos_edge.Repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.HaClusterStatsModel;



public interface HaClusterStatsRepo extends JpaRepository<HaClusterStatsModel, Long> {
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE HaClusterStatsModel v SET  v.state=?1 WHERE v.sid=?2")
	public void updateSidStas(String state, String sid);
	
	
	@Query("SELECT COUNT(*) FROM HaClusterStatsModel v WHERE v.sid=?1")
	public Integer checkIfSidNameExists(String sid);

}

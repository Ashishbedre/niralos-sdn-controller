package com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.UeStats.Frontend.TopUserModel;


@Repository
public interface TopUserRepository extends JpaRepository<TopUserModel, Long>{

	@Query(value="SELECT * FROM top_user_model ORDER BY total_bytes DESC LIMIT 5", nativeQuery=true)
	public ArrayList<TopUserModel> getTopUserData();
	
	
	@Query(value="SELECT COUNT(*) FROM top_user_model m WHERE m.imsi=?1", nativeQuery=true)							
	public Integer checkDistinctImsi(String imsi);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="UPDATE top_user_model m SET m.uplink_bytes=?1,m.downlink_bytes=?2,m.total_bytes=?3 WHERE m.imsi=?4",nativeQuery=true)
	public void updateTopUser(String upLinkBytes,String downLinkBytes,Long totalBytes,String imsi);
}

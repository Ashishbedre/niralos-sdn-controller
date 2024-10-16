//package com.other.app.Repository.Graph;
//
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.other.app.model.ThroughputModel;
//
//@Repository
//public interface ThroughputGraphFrontendRepository extends JpaRepository<ThroughputModel, Long>{
//
//	@Query("SELECT COUNT(r) FROM ThroughputModel r WHERE  r.localDateTime =?1 AND r.uplinkBytes=?2 AND r.downlinkBytes=?3")
//	public Integer checkThroughtputData(LocalDateTime localDateTime, Integer uplinkBytes, Integer downlinkBytes);
//
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query("UPDATE ThroughputModel r SET  r.localDateTime=?1, r.uplinkBytes=?2 , r.downlinkBytes=?3,r.agentId= ?4, r.tenentId = ?5, r.siteId = ?6 WHERE r.localDateTime =?1 AND r.uplinkBytes=?2  AND r.downlinkBytes=?3")
//	public void updateThroughtputData(LocalDateTime localDateTime, Integer uplinkBytes, Integer downlinkBytes,String agentId, String tenentID,
//			String siteId);
//	
//    //Query used to fetch a Uplinkbytes and DownlinkBytes graph data for last 60 mins in descending order
//	@Query(value = "SELECT * FROM throughput_model u WHERE u.tenent_id= ?2 AND u.site_id= ?3 ORDER BY u.date_time DESC LIMIT ?1 ", nativeQuery = true)
//	public ArrayList<ThroughputModel> returnUlandDlData(Integer range, String tenentId, String siteId);
//
//	@Query(value ="SELECT COUNT(*) FROM throughput_model", nativeQuery = true)
//	public Integer checkThroughputDataLinesCount();
//
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query(value ="DELETE FROM throughput_model  WHERE log_id ORDER BY log_id ASC LIMIT ?1", nativeQuery = true)
//	public Integer deleteThroughputData(Integer limit);
//		
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
////@Query(value ="SELECT DISTINCT l.year FROM throughput_model l LIMIT 1", nativeQuery = true)
////public String getYear();
////
////@Query(value ="SELECT  month FROM throughput_model ORDER BY log_id DESC LIMIT 1", nativeQuery = true)
////public String getMonth();
////
////@Query(value ="SELECT   day FROM throughput_model ORDER BY log_id DESC LIMIT 1", nativeQuery = true)
////public String getDay();
////
////@Query(value ="SELECT  hour FROM throughput_model ORDER BY log_id DESC LIMIT 1", nativeQuery = true)
////public String getHour();
////
////@Query(value ="select  minute from throughput_model ORDER BY log_id DESC LIMIT 1", nativeQuery = true)
////public String getMinute();
////
////@Query(value ="SELECT (ul_data/60) FROM throughput_model ORDER BY CAST(ul_data AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
////public Integer getUl();
////
////@Query(value ="SELECT (dl_data/60) FROM throughput_model ORDER BY CAST(dl_data AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
////public Integer getDl();
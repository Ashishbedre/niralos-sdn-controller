package com.other.app.NiralosFiveGCore.Repository.Throughput;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.other.app.NiralosFiveGCore.model.ThroughputModel;

@EnableJpaRepositories
public interface ThroughputRepository extends JpaRepository<ThroughputModel, Long>{

	@Query("SELECT COUNT(t) FROM ThroughputModel t WHERE  t.localDateTime =?1 AND t.uplinkBytes=?2 AND t.downlinkBytes=?3")
	public int checkThroughtputData(LocalDateTime  localDateTime, Integer uplinkBytes, Integer downlinkBytes);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ThroughputModel t SET t.localDateTime =?1, t.uplinkBytes=?2, t.downlinkBytes=?3, t.nfName=?4,t.nfType=?5 WHERE t.localDateTime =?1 AND t.uplinkBytes=?2 AND t.downlinkBytes=?3")
	public void updateThroughtputData(LocalDateTime localDateTime, Integer uplinkBytes, Integer downlinkBytes,
			String nfName, String nfType);
	
	  //Query used to fetch a Uplinkbytes and DownlinkBytes graph data for last 60 mins in descending order
	@Query(value = "SELECT * FROM throughput_model u ORDER BY u.date_time DESC LIMIT ?1 ", nativeQuery = true)
	public ArrayList<ThroughputModel> returnUlandDlData(Integer range);		
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value ="DELETE FROM throughput_model  WHERE log_id ORDER BY log_id ASC LIMIT ?1", nativeQuery = true)
	public Integer deleteThroughputData(Integer limit);
	@Query(value ="SELECT COUNT(*) FROM throughput_model", nativeQuery = true)
	public Integer checkThroughputDataLinesCount();

}

package com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.UeStatsModel1;

//@Repository
public interface UeStatsFrontendRepository extends JpaRepository<UeStatsModel1, Long>{

	
	// To check if exisiting entry with same IMSI,DNN,PSI exists.
	@Query("SELECT COUNT(u) FROM UeStatsModel1 u WHERE u.imsi = ?1 AND u.dnn = ?2 AND u.pdi = ?3 AND u.agentId= ?4 AND u.tenentId = ?5 AND u.siteId = ?6 AND u.nfName=?7 AND u.nfType=?8")
	public Integer findIfSessionExists(String imsi, String dnn, String psi, String agentId, String tenentId, String siteId, String nfName, String nfType);


	@Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE UeStatsModel1 u SET  u.cgi = ?2, u.sessionIpv4 = ?5, u.sst =?6, u.sd = ?7, u.uplinkBytes= ?8,"
            + " u.downlinkBytes=?9, u.sessionStartTime =?10,  u.sessionStopTime =?11, u.duration =?12 , u.sessionFailReason = ?13, "
            + "u.amfName = ?14,  u.status =?15, u.nfDomain= ?16,u.amfId= ?17, u.networkName =?18, u.sessionIpv6 =?19, "
            + "u.destinationIp =?20, u.sessionStatus = ?21, u.gnbId=?27 WHERE u.imsi = ?1 AND u.dnn = ?3 AND u.pdi = ?4"
            + " AND u.agentId= ?22 AND u.tenentId = ?23 AND u.siteId = ?24  AND u.nfName=?25 AND u.nfType=?26")
    public void updateExistingSession(String imsi, String cgi, String dnn, String pdi, String sessionIpv4,
            String sst, String sd, String uplinkBytes, String downlinkBytes, String sessionStartTime,
            String SessionStopTime, String duration, String sessionFailReason, String amfName, 
            Boolean status, String nfDomain, String amfId, String networkName,
            String sessionIpv6,String destinationIp, String sessionStatus, String agentId, String tenentId, String siteId,String nfName, String nfType, String gnbId );
	
	// Get all the unique IMSI values
	@Query(value = "SELECT DISTINCT * FROM ue_stats1 u WHERE u.status IN (1, 0) ORDER BY u.status DESC, u.imsi ASC", nativeQuery = true)
	public List<UeStatsModel1> getAllDistinctImsi(PageRequest pageable);
	
	@Query(value = "SELECT DISTINCT * FROM ue_stats1 u WHERE u.status IN (1, 0) ORDER BY u.status DESC, u.imsi ASC", nativeQuery = true)
	public List<UeStatsModel1> getAllDistinctImsiData();
	
	@Query(value = "SELECT DISTINCT u.imsi FROM ue_stats1 u WHERE u.imsi LIKE %?1", nativeQuery = true)
	public List<String> FindDistinctImsi(String imsi);
	

	// Get all the unique IMSI values per Gnb
	@Query("SELECT DISTINCT u FROM UeStatsModel1 u WHERE u.gnbId = ?1 AND u.status IN (1, 0) ORDER BY u.status DESC, u.imsi ASC")
	public List<UeStatsModel1> getAllDistinctImsiPerGnb(String gnbId);

	
	@Query("SELECT SUM(u.uplinkBytes) FROM UeStatsModel1 u WHERE u.imsi = ?1")
	public String getTotalUplink(String imsi);

	@Query("SELECT SUM(u.downlinkBytes) FROM UeStatsModel1 u WHERE u.imsi = ?1")
	public String getTotalDownlink(String imsi);
	
	@Query(value="SELECT SUM(u.downlink_bytes + u.uplink_bytes) AS total_bytes FROM ue_stats1 u WHERE u.imsi=?1", nativeQuery=true)
	public Long getTotalBytes(String imsi);

	
	@Query("SELECT COUNT(u) FROM UeStatsModel1 u WHERE u.imsi = ?1 AND u.status = true ")
	public String getNumberOfSesions(String imsi);

	// Select specific IMSI from data
	@Query("SELECT u FROM UeStatsModel1 u WHERE u.imsi = ?1 ")
	public ArrayList<UeStatsModel1> getItemizedUeDetails(String imsi);

	@Query("SELECT  COUNT(DISTINCT u.imsi) FROM UeStatsModel1 u WHERE u.status = true")
	public String getActiveUe();

	@Query("SELECT  COUNT(DISTINCT u.imsi) FROM UeStatsModel1 u")
	public String getTotalUe();

	@Query("SELECT COUNT(u) FROM UeStatsModel1 u WHERE u.status = true")
	public String getActiveUeSession();

	@Query("SELECT COUNT(u) FROM UeStatsModel1 u")
	public String getTotalUeSession();

	@Query("SELECT DISTINCT(u.siteId) FROM UeStatsModel1 u WHERE u.tenentId =?1")
	public List<String> findsitelistofUe(String tenentId);

}

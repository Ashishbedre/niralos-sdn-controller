package com.other.app.NiralosFiveGCore.Repository.UeStatistics;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.other.app.NiralosFiveGCore.model.UeStatsModel1;
//@EnableJpaRepositories
public interface UeStatsRepository1 extends JpaRepository<UeStatsModel1, Long>{
	
	
	
	@Query("SELECT COUNT(u) FROM UeStatsModel1 u WHERE u.imsi = ?1 AND u.dnn = ?2 AND u.pdi = ?3")
	public Integer checkIfExist(String imsi, String dnn, String psi);

	@Query("SELECT DISTINCT(u.imsi) FROM UeStatsModel1 u")
	public List<String> getAllDistinctImsi();
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE UeStatsModel1 m SET m.amfName = ?2, m.gnbId = ?1 WHERE m.imsi = ?3 AND m.nfName=?4 AND m.nfType=?5")
	public void updateDtatUsingLiveStats(String gnbId, String amfName, String imsi, String nfName, String nfType);


	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE UeStatsModel1 m SET m.nfDomain = ?1, m.sessionStatus = ?2, m.cgi = ?3,m.amfId = ?4, m.networkName = ?5, m.sessionIpv4 = ?6,"
			+ " m.sessionIpv6 = ?7, m.destinationIp = ?8, m.sourcePort = ?9, m.destinationPort = ?10, m.protocol = ?11,"
			+ "m.sst = ?12, m.sd = ?13, m.uplinkPacket = ?14, m.uplinkBytes = ?15, m.downlinkPacket = ?16"
			+ ", m.downlinkBytes = ?17, m.sessionStartTime = ?18, m.sessionStopTime = ?19, m.duration = ?20"
			+ ", m.sessionFailReason = ?21 , m.status = ?22, m.nfName=?26, m.nfType=?27 WHERE m.imsi = ?23 AND m.dnn = ?24 AND  m.pdi = ?25")
	public void updateUeStac(String nfDomain, String sessionStatus, String cgi, String amfId, String networkName,
			String sessionIpv4, String sessionIpv6, String destinationIp, String sourcePort, String destinationPort,
			String protocol, String sst, String sd, String uplinkPacket, String uplinkBytes, String downlinkPacket,
			String downlinkBytes, String sessionStartTime, String sessionStopTime, String duration,
			String sessionFailReason, Boolean status, String imsi,
			String dnn, String pdi, String nfName, String nfType);
}



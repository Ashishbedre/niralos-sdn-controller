package com.other.app.NiralosFiveGCore.Repository.GnbStatistics;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.other.app.NiralosFiveGCore.model.GnbStatsModel;

@EnableJpaRepositories
public interface GnbStatsRepository extends JpaRepository<GnbStatsModel, Long> {

	@Query("SELECT COUNT(m.gnbId) FROM GnbStatsModel m WHERE m.gnbId = ?1")
	public Integer checkIfGnbExist(String gnbSequenceId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnbStatsModel m SET m.cellGlobalIdentity = (SELECT DISTINCT(u.cgi) FROM UeStatsModel1 u WHERE u.gnbId = ?1  AND m.nfName=?2 AND  m.nfType=?3) WHERE m.gnbId = ?1 AND m.nfName=?2 AND  m.nfType=?3")
	public void updateCgiInGnbStats(String gnbId, String nfName,String nfType);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnbStatsModel m SET m.status = 0 WHERE m.nfName=?1 AND  m.nfType=?2")
	public void updateGnbStatusAllInctive(String nfName,String nfType);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnbStatsModel m SET m.status = 1 WHERE m.gnbId = ?1 AND m.nfName=?2 AND  m.nfType=?3")
	public void updateGnbStatusActive(String gnbId,String nfName,String nfType);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnbStatsModel m SET m.status = 0 WHERE m.gnbId = ?1 AND m.nfName=?2 AND  m.nfType=?3")
	public void updateGnbStatusInactive(String gnbId,String nfName,String nfType);

	//N
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnbStatsModel m SET m.ip = ?1, m.tac = ?2, m.totalGnbSession = ?3, m.activeGnbSession = ?4,m.plmn = ?5,"
			+ " m.lifeTimeAmfSession = ?6, m.activeAmfSession = ?7, m.amfName = ?8,"
			+ " m.status = 1 WHERE m.gnbId = ?9 AND m.nfName=?10 AND m.nfType=?11")
	public void updateExistingGnbListAndAmf(String ip, String tac, String totalGnbSession, String activeGnbSession,
			String plmn, String lifeTimeAmfSession, String activeAmfSession, String amfName, String gnbId,
			String nfName ,String nfType);
	
}

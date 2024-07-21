package com.other.app.NiralosFiveGCore.Repository.Topology;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.Topology.GnBModel;


@Repository
public interface GnbFrontendRepository extends JpaRepository<GnBModel, Long> {

	@Query(value ="SELECT * FROM gnb_model  WHERE device_id=?1", nativeQuery=true)
	public List<GnBModel> findAllGnbs(Long deviceId);
	@Query(value ="SELECT * FROM gnb_model  WHERE site_id=?1", nativeQuery=true)
	public List<GnBModel> findAllGnbs1(String siteId);
	// for Selecting GnbSequence
	@Query("SELECT COUNT(g) FROM GnBModel g WHERE g.gnbId = ?1 AND g.agentId = ?2 AND g.siteId = ?3 AND g.tenentId = ?4 AND g.nfName=?5 AND g.nfType=?6")
	public Integer findGnbExisting(Integer gnbId, String agentId, String siteId, String tenentId, String nfName, String nfType);


	// Update GnbStats Entities
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnBModel g SET g.status=?1 WHERE g.gnbId = ?2 AND g.siteId = ?3 AND g.agentId= ?4 AND g.tenentId = ?5 AND g.nfName =?6 AND g.nfType=?7")
	public void UpdateExistingGnbModel(Boolean status, Integer gnbId, String siteId, String agentId,
			String tenentId, String nfName, String nfType);

	@Query("SELECT g.deviceId FROM GnBModel g WHERE g.gnbId = ?1 AND g.agentId= ?2 AND g.siteId = ?3 AND g.tenentId = ?4")
	public List<Integer>  finddeviceId(Integer gnbId, String agentId, String siteId, String tenentId);
	public List<GnBModel> findAllGnbsBySiteId(String siteId);
	
	

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE GnBModel m SET m.status = 0 WHERE m.nfName=?1 AND  m.nfType=?2")
	public void updateGnbStatusAllInctive(String nfName,String nfType);
	

}

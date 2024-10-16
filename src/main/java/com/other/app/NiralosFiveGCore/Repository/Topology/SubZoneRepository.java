package com.other.app.NiralosFiveGCore.Repository.Topology;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.Topology.SubZoneModel;

public interface SubZoneRepository extends JpaRepository<SubZoneModel, Long>{

	@Query("SELECT s FROM SubZoneModel s WHERE s.zoneId=?1")
	public List<SubZoneModel> findAllSubZones(Long zoneId);
	
//	Query to Count Number of Active SubZone under a ZoneID.
	@Query("SELECT COUNT(s) FROM SubZoneModel s WHERE s.zoneId = ?1 AND s.status = 1")
	public Integer checkIfAnySubZoneActive(Long zoneId);
	
//	Query for Marking a SubZone Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE SubZoneModel s SET s.status = 1 WHERE s.subZoneId = ?1")
	public void setSubZoneActive(Long subzoneId);
	
//	Query for Marking a SubZone Inactive.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE SubZoneModel s SET s.status = 0 WHERE s.subZoneId = ?1")
	public void setSubZoneInactive(Long subzoneId);

	@Query("SELECT COUNT(z) FROM SubZoneModel z WHERE z.subZoneName=?1")
	public int countData(String zoneName);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE SubZoneModel z SET z.subZoneName = ?1 WHERE z.tenentId = ?1")
	public void savesubZoneData(String subzoneName, String tenantId, String siteId);

}

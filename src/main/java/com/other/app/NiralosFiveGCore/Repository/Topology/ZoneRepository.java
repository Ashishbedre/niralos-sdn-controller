package com.other.app.NiralosFiveGCore.Repository.Topology;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.Topology.ZoneModel;

public interface ZoneRepository extends JpaRepository<ZoneModel, Long>{

//	Query for Marking a Zone Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ZoneModel z SET z.status = 1 WHERE z.zoneId = ?1")
	public void setZoneActive(Long zoneId);
	
//	Query for Marking a Zone Inactive.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ZoneModel z SET z.status = 0 WHERE z.zoneId = ?1")
	public void setZoneInactive(Long zoneId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ZoneModel z SET z.zoneName = ?1, z.tenentName =?2 WHERE z.zoneName = ?1 AND z.tenentName =?2")
	public void saveZoneData(String zoneName, String tenentName);

	@Query("SELECT COUNT(z) FROM ZoneModel z WHERE z.zoneName=?1")
	public Integer countData(String zoneName);


}

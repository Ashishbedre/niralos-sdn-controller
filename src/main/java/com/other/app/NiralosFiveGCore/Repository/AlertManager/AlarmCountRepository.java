package com.other.app.NiralosFiveGCore.Repository.AlertManager;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmCount;


public interface AlarmCountRepository extends JpaRepository<AlarmCount, String> {

	AlarmCount findByTenantIdAndSiteId(String tenantId, String siteId);
   // AlarmCount findByTenantId(String tenantId,String siteId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE AlarmCount a SET a.count=?3 WHERE a.tenantId =?1 AND a.siteId =?2")
	public void updateCountData(String tenantId, String siteId, Long currentCount);

	Optional<AlarmCount> findById(long l);

}

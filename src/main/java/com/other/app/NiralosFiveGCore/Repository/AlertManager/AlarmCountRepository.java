package com.other.app.NiralosFiveGCore.Repository.AlertManager;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmCount;


public interface AlarmCountRepository extends JpaRepository<AlarmCount, String> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE AlarmCount a SET a.count=?1 WHERE a.count=?2")
	public void updateCountData(Long currentCount,Long oldCount);

	Optional<AlarmCount> findById(long l);

}

package com.other.app.niralos_edge.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.HypervisorLogModel;


public interface HypervisorLogRepo extends JpaRepository<HypervisorLogModel, Integer> {

	
	@Query("SELECT m FROM HypervisorLogModel m WHERE m.edgeClientId=?1")
	public List<HypervisorLogModel> getLogData(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE HypervisorLogModel m where m.edgeClientId=?1")
	public void deleteId(String edgeClientId);
}

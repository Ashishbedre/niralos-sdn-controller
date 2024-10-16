package com.other.app.niralos_edge.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.EdgeHypervisorAlertModel;

public interface AlertHypervisorRepository extends JpaRepository<EdgeHypervisorAlertModel, Long>{


	@Query("SELECT m FROM EdgeHypervisorAlertModel m WHERE m.edgeClientId=?1")
	public List<EdgeHypervisorAlertModel> getAlert(String edgeClientId);
	
	@Query("SELECT m FROM EdgeHypervisorAlertModel m WHERE m.edgeClientId=?1 AND m.status=false")
	public List<EdgeHypervisorAlertModel> getAlertResolved(String edgeClientId);
	
	@Query("SELECT m FROM EdgeHypervisorAlertModel m WHERE m.edgeClientId=?1 AND m.status=true")
	public List<EdgeHypervisorAlertModel> getAlertUnResolved(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM EdgeHypervisorAlertModel v WHERE v.edgeClientId=?1")
	public void deleteData(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EdgeHypervisorAlertModel v SET v.status=false WHERE v.edgeClientId=?1 AND v.alertId=?2")
	public void updateData(String edgeClientId,Long alertId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EdgeHypervisorAlertModel v SET v.status=false WHERE v.edgeClientId=?1 AND v.status=true")
	public void updateAllResolved(String edgeClientId);
	
}

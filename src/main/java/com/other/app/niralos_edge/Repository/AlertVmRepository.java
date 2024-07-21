package com.other.app.niralos_edge.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.other.app.niralos_edge.Model.EdgeVmAlertModel;

@Service
public interface AlertVmRepository extends JpaRepository<EdgeVmAlertModel, Long>{

	@Query("SELECT m FROM EdgeVmAlertModel m WHERE m.edgeClientId=?1")
	public List<EdgeVmAlertModel> getVmAlert(String edgeClientId);
	
	@Query("SELECT m FROM EdgeVmAlertModel m WHERE m.edgeClientId=?1 AND m.status=false")
	public List<EdgeVmAlertModel> getVmAlertResolved(String edgeClientId);
	
	@Query("SELECT m FROM EdgeVmAlertModel m WHERE m.edgeClientId=?1 AND m.status=true")
	public List<EdgeVmAlertModel> getVmAlertUnResolved(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM EdgeVmAlertModel v WHERE v.edgeClientId=?1")
	public void deleteData(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM EdgeVmAlertModel v WHERE v.edgeClientId=?1 AND v.vmId=?2")
	public void deleteDataOfVm(String edgeClientId,Long vmId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM EdgeVmAlertModel v WHERE v.edgeClientId=?1")
	public void deleteEdgeOfVm(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EdgeVmAlertModel v SET v.status=false WHERE v.edgeClientId=?1 AND v.alertId=?3 AND v.vmId=?2")
	public void updateData(String edgeClientId,Long vmid,Long alert);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EdgeVmAlertModel v SET v.status=false WHERE v.edgeClientId=?1 AND v.status=true")
	public void updateVmData(String edgeClientId);
}

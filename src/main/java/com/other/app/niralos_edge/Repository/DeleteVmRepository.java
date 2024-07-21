package com.other.app.niralos_edge.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.DeleteVmStatusModel;


public interface DeleteVmRepository extends JpaRepository<DeleteVmStatusModel, Long>{

	@Query("SELECT COUNT(v) FROM DeleteVmStatusModel v WHERE v.vmId=?1 AND v.edgeClientId=?2")
	public Integer countVmId(Long vmId,String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeleteVmStatusModel v SET v.status=?1 WHERE v.vmId=?2 AND v.edgeClientId=?3")
	public void updateData(Boolean status,Long vmId,String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeleteVmStatusModel v SET v.status=?1 WHERE v.vmId=?2 AND v.edgeClientId=?3")
	public void updateDeleteStatusData(Boolean status,Long vmId,String edgeClientId);
	
	@Query(value="SELECT m.vm_id FROM delete_vm_status m WHERE m.status=false",nativeQuery = true)
	public List<Long> getStatus();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM DeleteVmStatusModel v WHERE v.vmId=?1 AND v.edgeClientId=?2")
	public void deleteById(Long vmId,String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM DeleteVmStatusModel v WHERE v.edgeClientId=?1")
	public void deleteEdgeId(String edgeClientId);
}

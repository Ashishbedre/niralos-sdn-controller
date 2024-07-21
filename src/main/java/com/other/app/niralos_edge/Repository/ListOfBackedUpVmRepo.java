package com.other.app.niralos_edge.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.ListOfBackedUpVmModel;



public interface ListOfBackedUpVmRepo extends JpaRepository<ListOfBackedUpVmModel, Long> {
	
	@Query("SELECT COUNT(*) FROM ListOfBackedUpVmModel v WHERE v.volId=?1")
	public Integer checkIfVolIdExists(String volId);
	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE ListOfBackedUpVmModel v SET  v.ctime=?1, v.size=?2, v.vmId=?3 WHERE v.volId=?4")
	public void updateBackedUpVmList(String ctime, String size,
			String vmId, String volId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM ListOfBackedUpVmModel v WHERE v.volId=?1")
	public void deleteBackedUpVmList(String volId);

}

package com.other.app.niralos_edge.Repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.InternalDataModels;



public interface InternalDataRepositorys extends JpaRepository<InternalDataModels, Long> {
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE InternalDataModels i SET i.hypervisorIp=?2,i.hypervisorToken=?3,i.userName=?4,i.password=?5,i.hypervisorPort=?6,i.hypervisorNodeName=?7 WHERE i.edgeClientId=?1")
	public void updateInternalData(String edgeClientId, String hypervisorIP, String hypervisorToken,String username,String password,String hypervisorPort,String nodeName);

	@Query(value = "SELECT * FROM edge_internal_data m", nativeQuery = true)
	public List<InternalDataModels> getCoreDetails();
	
	@Query("SELECT m.hypervisorIp FROM InternalDataModels m WHERE m.edgeClientId=?1")
	public String hypervisorIp(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE InternalDataModels m where m.edgeClientId=?1")
	public void deleteId(String edgeClientId);
	
	@Query("SELECT m FROM InternalDataModels m WHERE m.edgeClientId=?1")
	public InternalDataModels getData(String edgeClientId);
	
	@Query("SELECT m.hypervisorNodeName FROM InternalDataModels m WHERE m.edgeClientId=?1")
	public String getNodeName(String edgeClientId);
}

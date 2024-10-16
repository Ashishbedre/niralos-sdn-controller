package com.other.app.niralos_edge.Repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.VmStatsModel;


public interface VmStatsRepo extends JpaRepository<VmStatsModel, Long> {
	
	@Query("SELECT MAX(vmId) FROM VmStatsModel")
	public Long getVmId();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM VmStatsModel v")
	public void deleteVMStatsTableOnly();

//	@Query("SELECT e FROM VmModel e WHERE e.edgeClientId = ?1")
//	public List<VmStatsModel> getAllVmPerEdge(String edgeClientId);
	
	//Getting the vm details for a particular vm
	@Query("SELECT m FROM VmStatsModel m WHERE m.vmId=?1 AND m.edgeClientId=?2")
	public VmStatsModel findDataForVm(Long vmId,String edgeClientId);
	
	//Getting the ram of the particular vm
	@Query(value="SELECT m.vm_total_mem_available FROM vm_stats m WHERE m.vm_id=?1 AND m.edge_client_id=?2",nativeQuery = true)
	public String getMemory(Long vmId,String edgeClientId);
			
	//Getting the cores of the particular vm
	@Query(value="SELECT m.cores FROM vm_stats m WHERE m.vm_id=?1 AND m.edge_client_id=?2",nativeQuery = true)
	public String getCores(Long vmId,String edgeClientId);
		
	//Getting the sockets of the particular vm
	@Query(value="SELECT m.sockets FROM vm_stats m WHERE m.vm_id=?1 AND m.edge_client_id=?2",nativeQuery = true)
	public String getSockets(Long vmId,String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM VmStatsModel v WHERE v.vmId=?1 AND v.edgeClientId=?2")
	public void deleteById(Long vmId,String edgeClientId);
	
	//Getting the vm details for a particular vm
		@Query("SELECT m FROM VmStatsModel m WHERE m.edgeClientId=?1")
		public List<VmStatsModel> findData(String edgeClientId);
		
		//Getting the spice port of the particular vm
		@Query(value="SELECT m.spice_port FROM vm_stats m WHERE m.vm_id=?1 AND m.edge_client_id=?2",nativeQuery = true)
		public String getSpicePort(Long vmId,String edgeClientId);
		
		@Query("SELECT COUNT(v) FROM VmStatsModel v WHERE v.vmId=?1 AND v.edgeClientId=?2")
		public Integer getDataExist(Long vmId,String edgeClientId);
				
		//Updating the data in for vm details
		@Modifying(clearAutomatically = true)
		@Transactional
		@Query("UPDATE VmStatsModel v SET v.vmStatus=?2,v.vmCurrentCpuUsage=?3,v.vmTotalCpusAvailable=?4,v.vmCurrentMemUsage=?5,v.vmCurrentMemUsageInUnits=?6,v.vmTotalMemAvailable=?7,v.vmTotalDiskSpace=?8,v.vmNetin=?9,v.vmNetout=?10,v.vmUptime=?11,v.vmAgent=?12,v.cores=?13,v.sockets=?14,v.spicePort=?15,v.vmName=?1 WHERE v.vmId=?16 AND v.edgeClientId=?17")
		public void UpdateData(String vmName,String vmStatus,Double vmCurrentCpuUsage,Long vmTotalCpusAvailable,Double vmCurrentMemUsage,Double vmCurrentMemUsageInUnits,Long vmTotalMemAvailable,Long vmTotalDiskSpace,Long vmNetin,Long vmNetout,Long vmUptime,Long vmAgent,Long cores,Long sockets,String spicePort,Long vmId,String edgeClientId);
				
		//Getting the vm List of the next vm
		@Query(value="SELECT m.vm_id FROM vm_stats m WHERE m.edge_client_id=?1",nativeQuery = true)
		public List<Long> getVm(String edgeClientId);
		
		@Query("SELECT COUNT(v) FROM VmStatsModel v WHERE v.edgeClientId=?1")
		public Integer getExist(String edgeClientId);
		
		@Modifying(clearAutomatically = true)
		@Transactional
		@Query("DELETE FROM VmStatsModel v WHERE v.edgeClientId=?1")
		public void deleteVMStatsTable(String edgeClientId);
		
		@Modifying(clearAutomatically = true)
		@Transactional
		@Query("UPDATE VmStatsModel v SET v.vmStatus=?1 WHERE v.vmId=?2 AND v.edgeClientId=?3")
		public void UpdateVmData(String vmStatus,Long vmId,String edgeClientId);
}

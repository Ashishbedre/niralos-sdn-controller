package com.other.app.niralos_edge.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.HypervisorStatsModel;


public interface HypervisorStatsRepo extends JpaRepository<HypervisorStatsModel, Integer> {
	
	@Query("SELECT m FROM HypervisorStatsModel m WHERE m.edgeClientId=?1")
	public List<HypervisorStatsModel> getDataForId(String edgeClientId);
	
	@Query("SELECT COUNT(*) FROM HypervisorStatsModel v WHERE v.edgeClientId=?1")
	public Integer checkIfEdgeClientExist(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE HypervisorStatsModel v SET v.totalDiskspace=?1, v.usedDiskSpace=?2, v.freeDiskSpace=?3, v.totalRamSpace=?4, v.currentRamUsage=?5, v.availableRamSpace=?6, v.cpuModel=?7, v.cpuUsage=?8, v.cpuSockets=?9, v.totalCpus=?10,v.cpuCores=?11,v.upTime=?12,v.nodeName=?13,v.nodeStatus=?15 WHERE v.edgeClientId=?14")
	public void updateHypervisorStats(Long totalDiskspace,Long usedDiskSpace,Long freeDiskSpace,Double totalRamSpace,Long currentRamUsage,Long availableRamSpace,String cpuModel,Double cpuUsage,Long cpuSockets,Long totalCpus,Long cpuCores,Long upTime,String nodeName,String edgeClientId,String nodeStatus);
	
	@Query("SELECT m.totalDiskspace FROM HypervisorStatsModel m WHERE m.edgeClientId=?1")
	public Long getTotalMemory(String edgeClientId);
	
	@Query("SELECT m.cpuCores FROM HypervisorStatsModel m WHERE m.edgeClientId=?1")
	public Long getCpuCores(String edgeClientId);
	
	@Query("SELECT m.cpuSockets FROM HypervisorStatsModel m WHERE m.edgeClientId=?1")
	public Long getCpuSockets(String edgeClientId);
	
	@Query("SELECT m FROM HypervisorStatsModel m WHERE m.edgeClientId=?1")
	public HypervisorStatsModel getData(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE HypervisorStatsModel v SET v.nodeStatus=?1 WHERE v.edgeClientId=?2")
	public void updateHypervisorStatus(String nodeStatus,String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM HypervisorStatsModel v WHERE v.edgeClientId=?1")
	public void deleteData(String edgeClientId);
}

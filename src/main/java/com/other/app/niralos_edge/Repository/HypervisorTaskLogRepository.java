package com.other.app.niralos_edge.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.HypervisorTaskLogModel;

public interface HypervisorTaskLogRepository extends JpaRepository<HypervisorTaskLogModel, Long>{

	@Query("SELECT COUNT(v) FROM HypervisorTaskLogModel v WHERE v.starttime=?1 AND v.endtime=?2")
	public Integer getDataExist(Long starttime,Long endtime);
	
	//Updating the data in for vm details
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE HypervisorTaskLogModel v SET v.user=?1,v.status=?4,v.type=?5,v.tokenid=?6,v.upid=?7,v.node=?8,v.saved=?9,v.id=?10 WHERE v.starttime=?2 AND v.endtime=?3 AND v.edgeClientId=?11")
	public void UpdateData(String user,Long starttime,Long endtime,String status,String type,String tokenid,String upid,String node,String saved,String id,String edgeClientId);
	
	@Query("SELECT v FROM HypervisorTaskLogModel v WHERE v.edgeClientId=?1")
	public ArrayList<HypervisorTaskLogModel> getLogData(String edgeClientId);
}

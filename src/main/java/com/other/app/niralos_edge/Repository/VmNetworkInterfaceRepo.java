package com.other.app.niralos_edge.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.VmNetworkStatsModel;



public interface VmNetworkInterfaceRepo extends JpaRepository<VmNetworkStatsModel, Long> {
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE VmNetworkStatsModel v SET  v.net0=?1,v.net1=?2,v.net2=?3,v.net3=?4,v.net4=?5,v.net5=?6,v.net6=?7,v.net7=?8,v.net8=?9,v.net9=?10,v.net10=?11,v.vmName=?14 WHERE v.vmId=?12 AND v.edgeClientId=?13")
	public void updateVmNetworkInterface(String net0,String net1,String net2,String net3,String net4,String net5,String net6,String net7,String net8,String net9,String net10,Long vmId,String edgeClientId,String vmName);
	
	@Query(value = "SELECT * FROM network_interface v WHERE v.VM_ID=?1", nativeQuery = true)
	public ArrayList<VmNetworkStatsModel> networkInterfaceList(Long vmId);
	
	@Query("SELECT COUNT(*) FROM VmNetworkStatsModel v WHERE v.vmId=?1 AND v.edgeClientId=?2")
	public Integer checkIfInterfaceNameExists(Long vmId,String edgeClientId);

	@Query(value = "SELECT * FROM network_interface u WHERE u.vm_id=?1 AND u.edge_client_id=?2", nativeQuery = true)
	public List<VmNetworkStatsModel> findByVmId(Long vmId,String edgeClientId);
	
	@Query(value = "SELECT * FROM network_interface u WHERE u.edge_client_id=?1", nativeQuery = true)
	public List<VmNetworkStatsModel> findByEdgeId(String edgeClientId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM VmNetworkStatsModel v WHERE v.edgeClientId=?1")
	public void deleteData(String edgeClientId);
}

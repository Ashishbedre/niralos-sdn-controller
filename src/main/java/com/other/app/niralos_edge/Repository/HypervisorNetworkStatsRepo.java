package com.other.app.niralos_edge.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.niralos_edge.Model.HypervisorNetworkStatsModel;



public interface HypervisorNetworkStatsRepo extends JpaRepository<HypervisorNetworkStatsModel, Long> {
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE HypervisorNetworkStatsModel v SET  v.type=?1, v.autostart=?2, v.priority=?3, v.active=?4, v.address=?5, v.bridge_ports=?6, v.gateway=?7, v.cidr=?8, v.netmask=?9 WHERE v.iface=?10 AND v.edgeClientId=?11")
	public void updateHypervisorNetworkStats(String type, Integer autostart, Integer priority, Integer active,
			String address, String bridge_ports, String gateway, String cidr, String netmask, String iface,String edgeClientId);
	
	@Query("SELECT COUNT(*) FROM HypervisorNetworkStatsModel v WHERE v.iface=?1 AND v.edgeClientId=?2")
	public Integer checkIfInterfaceNameExistsInHypervisor(String iface,String edgeClientId);

	@Query("SELECT m FROM HypervisorNetworkStatsModel m WHERE m.edgeClientId=?1")
	public List<HypervisorNetworkStatsModel> findByEdgeClientId(String edgeClientId);
	@Query("SELECT m FROM HypervisorNetworkStatsModel m WHERE m.edgeClientId=?1 AND m.type='bridge' ORDER BY m.iface ASC")
	public List<HypervisorNetworkStatsModel> findNetworksEdgeClientId(String edgeClientId);

	@Query("SELECT m FROM HypervisorNetworkStatsModel m WHERE m.edgeClientId=?1 AND m.iface=?2")
	public List<HypervisorNetworkStatsModel> returnAllsubnetsreletedtoInterfaces(String edgeClientId,String iface);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE FROM HypervisorNetworkStatsModel v WHERE v.edgeClientId=?1")
	public void deleteData(String edgeClientId);

}

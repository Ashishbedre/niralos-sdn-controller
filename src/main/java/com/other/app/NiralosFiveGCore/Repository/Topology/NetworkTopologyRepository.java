package com.other.app.NiralosFiveGCore.Repository.Topology;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;

@Repository
public interface NetworkTopologyRepository extends JpaRepository<NetworkTopologyModel, Long>{

	@Query("SELECT COUNT(n) FROM NetworkTopologyModel n WHERE  n.nfName=?3 AND n.nfType=?4")
	public Integer countData( String nfName, String nfType);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE NetworkTopologyModel n SET n.nfStatus=?5 WHERE n.nfName=?3 AND n.nfType=?4")
	public void updatetheData( String nfName, String nfType, String nfStatus);

	public List<NetworkTopologyModel> findByDevice(String upfDevice);
	
//    @Query(value="select * from nf_status  where site_id=?2 AND tenant_id =?1", nativeQuery=true)
//    List<NetworkTopologyModel> findData(String tenentId, String siteId);

//    List<NetworkTopologyModel> findByTenantIdAndSiteId(String tenantId, String siteId);

//	public List<NetworkTopologyModel> findByDevice(
//			String deviceName);
}

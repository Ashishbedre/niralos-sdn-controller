package com.other.app.NiralosFiveGCore.Repository.Topology;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.Topology.DeviceModel;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceModel, Long>{
	
	@Query("SELECT d FROM DeviceModel d WHERE d.deploymentId=?1")
	public List<DeviceModel> findAllDevices(Long deploymentId);
	
//	Query to Count Number of Active SubZones under a ZoneID.
	@Query("SELECT COUNT(d) FROM DeviceModel d WHERE d.deploymentId = ?1 AND d.status = 1")
	public Integer checkIfAnyDeviceActive(Long deploymentId);
	
//	Query for Marking a Deployment Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeviceModel d SET d.status = ?1 WHERE d.agentId = ?2 AND d.DeviceName = '5G-Control-Core' AND d.nfName=?3 AND d.nfType=?4")
	public void updateDeviceStatusForCTL(Boolean status, String agentId, String nfName, String nfType);
	
//	Query for Marking a Deployment Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeviceModel d SET d.status = ?1 WHERE d.agentId = ?2 AND d.DeviceName = '5G-Data-Plane' AND d.nfName=?3 AND d.nfType=?4")
	public void updateDeviceStatusForUPG(Boolean status, String agentId, String nfName, String nfType);
	
    DeviceModel findByTenentIdAndSiteIdAndAgentId(String tenentId, String siteId, String agentId);

    @Query("SELECT d FROM DeviceModel d WHERE d.deploymentId = ?1 AND d.siteId = ?3 AND d.tenentId=?2")
    List<DeviceModel> findAllDevices1(Long deploymentId,String tenentId,String SiteId );

	public DeviceModel findByTenentIdAndSiteIdAndAgentIdAndNfNameAndNfType(String tenentId, String siteId,
			String agentId, String nfName, String nfType);

	 @Query("SELECT d FROM DeviceModel d WHERE d.siteId = ?2 AND d.tenentId=?1 AND d.DeviceName=?3 AND d.deploymentId = ?4")
	public List<DeviceModel> findAllDevices2(String tenantId, String siteId, String deviceName,Long deploymentId);



	

}

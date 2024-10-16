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
	@Query("UPDATE DeviceModel d SET d.status = ?1 WHERE d.DeviceName = '5G-Core-Control-Plane' AND d.nfName=?2 AND d.nfType=?3")
	public void updateDeviceStatusForCTL(Boolean status, String nfName, String nfType);
	
//	Query for Marking a Deployment Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeviceModel d SET d.status = ?1 WHERE d.DeviceName = '5G-Core-User-Plane' AND d.nfName=?2 AND d.nfType=?3")
	public void updateDeviceStatusForUPG(Boolean status, String nfName, String nfType);
	
//    DeviceModel findByTenentIdAndSiteIdAndAgentId(String tenentId, String siteId, String agentId);

    @Query("SELECT d FROM DeviceModel d WHERE d.deploymentId = ?1")
    List<DeviceModel> findAllDevices1(Long deploymentId);

	public DeviceModel findByNfNameAndNfType(String nfName, String nfType);

	 @Query("SELECT d FROM DeviceModel d WHERE d.DeviceName=?1 AND d.deploymentId = ?2")
	public List<DeviceModel> findAllDevices2( String deviceName,Long deploymentId);



	

}

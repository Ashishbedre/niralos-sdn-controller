package com.other.app.NiralosFiveGCore.Repository.Topology;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.Topology.DeploymentModel;

@Repository
public interface DeploymentRepository extends JpaRepository<DeploymentModel, Long>{


	@Query("SELECT d FROM DeploymentModel d WHERE d.subZoneId=?1")
	public List<DeploymentModel> findAllDeployments(Long subZoneId);
	
//	Query to Count Number of Active Deployment under a SubZoneID.
	@Query("SELECT COUNT(d) FROM DeploymentModel d WHERE d.subZoneId = ?1 AND d.status = 1")
	public Integer checkIfAnyDeploymentActive(Long subZoneId);
	
//	Query for Marking a Deployment Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeploymentModel d SET d.status = 1 WHERE d.deploymentId = ?1")
	public void setDeploymentActive(Long subzoneId);
	
//	Query for Marking a Deployment Inactive.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeploymentModel d SET d.status = 0 WHERE d.deploymentId = ?1")
	public void setDeploymentInactive(Long subzoneId);

	@Query("SELECT DISTINCT(d.tenentName) FROM DeploymentModel d")
	public List<String> FindAllTenant();
	
	@Query("SELECT DISTINCT(d.DeploymentName) FROM DeploymentModel d WHERE d.tenentName=?1")
	public List<String> FindAllSite(String tenantId);

	@Query("SELECT COUNT(z) FROM DeploymentModel z WHERE z.subZoneName=?1 AND tenentName=?2 AND DeploymentName=?3")
	public int countData(String subzoneName, String tenantId, String DeploymentName);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE DeploymentModel z SET z.subZoneName=?1, tenentName=?2, DeploymentName=?3 WHERE z.subZoneName=?1 AND tenentName=?2 AND DeploymentName=?3")
	public void updateData(String subzoneName, String tenantId, String siteId);

}

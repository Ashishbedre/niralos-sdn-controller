package com.other.app.NiralosFiveGCore.Repository.InternalServices;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.InternalDataModel;

public interface InternalDataRepository extends JpaRepository<InternalDataModel, Long> {

//	@Query("SELECT tenantId FROM InternalDataModel")
//	public String searchTheTenantId();
//	@Query("SELECT siteId FROM InternalDataModel")
//	public String searchThesiteId();
	@Query("SELECT niralControllerIp FROM InternalDataModel")
	public String searchThecontrollerIp();

//	@Query("SELECT niralControllerClientId FROM InternalDataModel")
//	public String searchThecontrollerClientId();

	@Modifying
	@Transactional
	@Query("UPDATE InternalDataModel i SET "
	        + "i.niralControllerIp = ?2, "
	        + "i.amfIp = ?3, "
	        + "i.smfIp = ?4, "
	        + "i.nrfIp = ?5, "
	        + "i.upfIp = ?6, "
	        + "i.udrIp = ?7, "
	        + "i.nssfIp = ?8, "
	        + "i.upgIp = ?9, "
	        + "i.amfPort = ?10, "
	        + "i.smfPort = ?11, "
	        + "i.nrfPort = ?12, "
	        + "i.upfPort = ?13, "
	        + "i.udrPort = ?14, "
	        + "i.nssfPort = ?15, "
	        + "i.upgPort = ?16, "
	        
	        + "i.ausfIp = ?17, "
	        + "i.ausfPort = ?18, "
	        + "i.pcfIp = ?19, "
	        + "i.pcfPort = ?20, "
	        + "i.udmIp = ?21, "
	        + "i.udmPort = ?22, "
	        + "i.scpIp = ?23, "
	        + "i.scpPort = ?24, "
	        + "i.bsfIp = ?25, "
	        + "i.bsfPort = ?26, i.niralGlobalControllerport = ?27 WHERE i.id = ?1")
	void updateInternalData(long id, String niralControllerIp, String amfIp, String smfIp, String nrfIp,
	                        String upfIp, String udrIp, String nssfIp, String upgIp, String amfPort,
	                        String smfPort, String nrfPort, String upfPort, String udrPort, String nssfPort,
	                        String upgPort,
	                        String ausfIp, String ausfPort, String pcfIp, String pcfPort, String udmIp,
	                        String udmPort, String scpIp, String scpPort, String bsfIp, String bsfPort, 
	                        String niralGlobalControllerPort);




//	@Query("SELECT hypervisorIp FROM InternalDataModel")
//	public String hypervisorIp();
//
//	@Query("SELECT tenantId FROM InternalDataModel")
//	public String tenantId();
//
//	@Query("SELECT siteId FROM InternalDataModel")
//	public String siteId();
//
//	@Query("SELECT niralControllerClientId FROM InternalDataModel")
//	public String niralControllerClientId();

	@Query(value = "SELECT * FROM internal_data", nativeQuery = true)
	public InternalDataModel getCoreDetails();

	public InternalDataModel findByNiralControllerClientId(String deploymentId);

//
//	public List<InternalDataModel> findByTenantIdAndNiralControllerClientId(String tenantId,
//			String niralControllerClientId);

//	@Query("SELECT zoneName FROM InternalDataModel")
//	public String searchTheZoneName();
//	@Query("SELECT subzoneName FROM InternalDataModel")
//	public String searchThesubzoneName();		

}

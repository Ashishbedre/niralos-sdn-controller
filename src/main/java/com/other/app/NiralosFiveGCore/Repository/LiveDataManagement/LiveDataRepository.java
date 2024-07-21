package com.other.app.NiralosFiveGCore.Repository.LiveDataManagement;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.other.app.NiralosFiveGCore.model.LiveDataModel;

@EnableJpaRepositories
public interface LiveDataRepository extends JpaRepository<LiveDataModel, String> {

	@Query("SELECT(l) FROM LiveDataModel l WHERE l.nfName=?1 AND l.nfType=?2")
	public LiveDataModel findLiveDataModel(String nfName,String nfType);

//	Query for Marking a Deployment Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE LiveDataModel l SET "
			+ " l.activeAmfSession = ?2 ,"
			+ " l.totalAmfSession = ?3,"
			+ " l.activeamf = ?4,"
			+ " l.totalamf = ?5,"
			+ " l.activeUe = ?6,"
			+ " l.totalUe = ?7,"
			+ " l.activeUeSession = ?8,"
			+ " l.totalUeSession = ?9,"
			+ " l.activeGnb = ?10,"
			+ " l.totalGnb = ?11,"
			+ " l.activeGnbSession = ?12,"
			+ " l.totalGnbSession = ?13,"
			+ " l.siteId = ?14,"
			+ " l.tenentId = ?15 WHERE l.controllerClientId = ?1 AND l.nfType=?16 AND l.nfName=?17")
	public void updateLiveData(
			String controllerClientId, 
			String activeAmfSession, 
			String totalAmfSession,
			String activeamf, 
			String totalamf,
			String activeUe, 
			String totalUe, 
			String activeUeSession,
			String totalUeSession,
			String activeGnb, 
			String totalGnb, 
			String activeGnbSession, 
			String totalGnbSession,
			String siteId,
			String tenentId,
			String nfType,
			String nfName);
	
	@Query("SELECT COUNT(l) FROM LiveDataModel l")
	public Integer countLiveData();

//	Query for Marking a Deployment Active.
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE LiveDataModel l SET l.controllerClientId=?1, l.tenentId = ?2 ,l.siteId=?3 WHERE l.nfType=?5 AND l.nfName=?4")
	public void updateTenentandSite(String agentId,String tenentName, String siteName, String nfName, String nfType);
}

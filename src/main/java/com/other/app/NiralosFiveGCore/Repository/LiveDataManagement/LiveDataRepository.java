package com.other.app.NiralosFiveGCore.Repository.LiveDataManagement;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.other.app.NiralosFiveGCore.model.LiveDataModel;


public interface LiveDataRepository extends JpaRepository<LiveDataModel, String> {

	@Query(value="SELECT * FROM live_data ORDER BY id DESC LIMIT 1", nativeQuery = true)
	public LiveDataModel findLiveDataModel(String nfName,String nfType);

	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE LiveDataModel l SET "
	        + " l.activeAmfSession = ?1 ,"
	        + " l.totalAmfSession = ?2,"
	        + " l.activeamf = ?3,"
	        + " l.totalamf = ?4,"
	        + " l.activeUe = ?5,"
	        + " l.totalUe = ?6,"
	        + " l.activeUeSession = ?7,"
	        + " l.totalUeSession = ?8,"
	        + " l.activeGnb = ?9,"
	        + " l.totalGnb = ?10,"
	        + " l.activeGnbSession = ?11,"
	        + " l.totalGnbSession = ?12 "
	        + "WHERE l.nfType=?13 AND l.nfName=?14")
	public void updateLiveData(
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
	        String nfType,
	        String nfName);

	
	@Query("SELECT COUNT(l) FROM LiveDataModel l")
	public Integer countLiveData();

////	Query for Marking a Deployment Active.
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query("UPDATE LiveDataModel l SET l.controllerClientId=?1 WHERE l.nfType=?2 AND l.nfName=?1")
//	public void updateTenentandSite(String nfName, String nfType);
}

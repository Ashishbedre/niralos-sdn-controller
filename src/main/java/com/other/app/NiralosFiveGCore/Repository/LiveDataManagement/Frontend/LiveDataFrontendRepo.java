package com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.Frontend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.LiveDataModel;

public interface LiveDataFrontendRepo extends JpaRepository<LiveDataModel, Long>{

	@Query("SELECT(l) FROM LiveDataModel l")
	public LiveDataModel findLiveDataModel();
	
	@Query("SELECT l FROM LiveDataModel l")
	public List<LiveDataModel> getliveData();

	@Query("SELECT DISTINCT(l.siteId) FROM LiveDataModel l WHERE l.tenentId=?1")
	public List<String> returnListof(String tenentId);

	LiveDataModel findByTenentIdAndSiteIdAndNfNameAndNfTypeAndControllerClientId(String tenantId, String siteId, String nfName, String nfType, String controllerClientId);

	
//	@Query("SELECT  l.activeGnb FROM LiveDataModel l WHERE l.tenentId=?1 AND l.siteId = ?2")
//	public String getActiveGnb(String tenentId, String siteId);

}
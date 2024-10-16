package com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.Frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.Graph.GnbGraphModel;

public interface LiveDataFrontendRepo extends JpaRepository<LiveDataModel, Long>{
	
//  Query used to fetch a tenants gnb graph data for last 60 mins in descending order
	@Query(value = "SELECT * FROM live_data ORDER BY date_time DESC LIMIT ?1", nativeQuery = true)
	public ArrayList<LiveDataModel> returnGnbData(Integer limit);

	@Query(value = "SELECT * FROM live_data  ORDER BY id DESC LIMIT 1", nativeQuery = true)
	public LiveDataModel findLiveDataModel();
	
	
	@Query("SELECT l FROM LiveDataModel l")
	public List<LiveDataModel> getliveData();

	@Query("SELECT DISTINCT(l) FROM LiveDataModel l")
	public List<String> returnListof();

//	LiveDataModel findByTenentIdAndSiteIdAndNfNameAndNfTypeAndControllerClientId(String tenantId, String siteId, String nfName, String nfType, String controllerClientId);

	
//	@Query("SELECT  l.activeGnb FROM LiveDataModel l WHERE l.tenentId=?1 AND l.siteId = ?2")
//	public String getActiveGnb(String tenentId, String siteId);

}
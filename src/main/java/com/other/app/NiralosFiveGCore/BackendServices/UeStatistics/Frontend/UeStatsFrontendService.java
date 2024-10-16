package com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend;

import java.util.ArrayList;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.Frontend.ReturnSiteList;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UestatsPagesCount;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend.StatusCountDTO;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend.UeStatsDistinct;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;

public interface UeStatsFrontendService {

	public ArrayList<UeStatsDistinct> getAllUeStatsData(int pageNumber, int pageSize);
	public UestatsPagesCount getAllUeStatsPages(int pageNumber, int pageSize);
	
	public UestatsPagesCount getAllUeStatsPagesperGnb(int pageNumber, int pageSize, String gnbId);
	
	public ArrayList<UeStatsModel1> getItemizedUeDetails(String imsi);
	public ArrayList<UeStatsDistinct> searchUeStatsData(String imsi);
	public ArrayList<UeStatsDistinct> getUeStatsDataPerGnb(String gnbId,int pageNumber,int pageSize);
//	public ArrayList<UeStatsDistinct> UeStatsPerPage(String range);
	
	public List<ReturnSiteList> getallSitelist(String tenentId);
	public List<ReturnSiteList> getallSitelistofOverview(String tenentId);
	public StatusCountDTO getStatusCounts();
}

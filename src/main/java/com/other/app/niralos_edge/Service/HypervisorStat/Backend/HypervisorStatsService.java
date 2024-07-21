package com.other.app.niralos_edge.Service.HypervisorStat.Backend;
 
import java.util.ArrayList;
import java.util.List;
 
import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.HypervisorTaskLogModel;
import com.other.app.niralos_edge.Model.ListOfIsoModel;
import com.other.app.niralos_edge.dto.HypervisorLogDto;
import com.other.app.niralos_edge.dto.HypervisorNetworkStatsDto;
import com.other.app.niralos_edge.dto.HypervisorStatsDto;
 
 
public interface HypervisorStatsService {
	
	public void saveHypervisorStats();
	
	public void saveHypervisorNetworkStats();
	
	public List<ListOfIsoModel> getListOfIso(String edgeClientId);
	
	public List<HypervisorStatsModel> getAllHypervisor();
	
	public ArrayList<HypervisorNetworkStatsDto> getHypervisorNetworkStats(String edgeClientId);
	
	public ArrayList<HypervisorStatsDto> getAllHypervisorForSite(String edgeClientId);
	
	public void saveHypervisorLog();
	
	public List<HypervisorLogDto> getHypervisorLog(String edgeClientId);
	
	public List<HypervisorNetworkStatsDto> getHypervisorBridgeNetworks(String edgeClientId);
	
	public void getLogData();
	
	public ArrayList<HypervisorTaskLogModel> getHypervisorLogData(String edgeClientId);
 
}

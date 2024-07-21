package com.other.app.niralos_edge.Service.VMDetails;

import java.util.ArrayList;

import com.other.app.niralos_edge.dto.EdgeFrontendDto;
import com.other.app.niralos_edge.dto.GetVmData;
import com.other.app.niralos_edge.dto.HypervisorCreateVmDto;
import com.other.app.niralos_edge.dto.VmUpdateDto;

public interface VmStatsService {
	
	public void getVmStats();
	
	public void saveVmNetworkStats();
	
	public ArrayList<EdgeFrontendDto> getVmDetailsParticularSite(String edgeClientId);
	
	public EdgeFrontendDto getParticularVm(Long vmId,String edgeClientId);
	
	public VmUpdateDto getDetails(String edgeClientId);
	
	public HypervisorCreateVmDto getVm(String edgeClientId);
	
	public GetVmData getData(Long vmId,String edgeClientId);

}

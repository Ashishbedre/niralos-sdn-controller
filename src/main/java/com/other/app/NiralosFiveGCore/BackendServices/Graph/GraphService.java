package com.other.app.NiralosFiveGCore.BackendServices.Graph;

import java.util.ArrayList;

import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.GnbGraphDTO;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UeGraphDTO;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpgFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.Frontend.ThroughputGraphFrontendDto;

public interface GraphService {
	
	public void saveGnbGraph();
	public void saveUeGraph();
	public void saveUpgInterfaceData();
	public void saveUpgErrorGraphData();
	public void saveUpfErrorGraphData();
	public void upfErrorLastDataForDeltaValue();

	public void upgErrorLastDataForDeltaValue();
	
	public ArrayList<UeGraphDTO> getUegraph(String range);
    public ArrayList<GnbGraphDTO> getGnbGraph(String range);
	public ArrayList<ThroughputGraphFrontendDto> getThroughputGraph(String range, String tenentId, String siteId);
	public ArrayList<UpgFrontendDto> getN3N4N6Graphdata(String type, Integer limit);

 }

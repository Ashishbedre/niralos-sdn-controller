package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend;

import java.util.ArrayList;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.Frontend.ReturnSiteList;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpfErrorGraphDto;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UpgErrorGraphDto;
import com.other.app.NiralosFiveGCore.Dto.upgDto.UpgUpfRoot;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;

public interface UpgErrorFrontendService {
	
	public List<UpgErrorModel> getUpgErrorData();
	public ArrayList<UpgErrorGraphDto> getUpgErrorbyfilter(String type, String name, String reason);
//	public List<ReturnSiteList> returnSitelistofUpg(String tenentId);
	List<UpfErrorGraphDto> getUpfErrorByFilter(String type,String reason);

	UpgUpfRoot getCombinedErrorByFilter(String type);
}

package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend;

import java.util.List;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;


public interface UpgServiceFontend {
	public List<UpgModel> getUpgData();
	public UpgModel getSpecificUpgData(String interfaceName);
}

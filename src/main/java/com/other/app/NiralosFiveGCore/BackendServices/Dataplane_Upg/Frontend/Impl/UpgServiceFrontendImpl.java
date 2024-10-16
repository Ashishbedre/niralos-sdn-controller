package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.UpgServiceFontend;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgFrontendRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgInterfaceDataRepository;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;


@Service
public class UpgServiceFrontendImpl implements UpgServiceFontend{
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:UpgServiceFrontendImpl");

	
	@Autowired
	UpgInterfaceDataRepository upgInterfaceDataRepository;
	@Autowired
	UpgFrontendRepository upgRepository;

	@Override
	public List<UpgModel> getUpgData() {
		loggerTypeB.info("getUpgData: Retrieving all UPg data.");
		return upgRepository.findAllData();
	}

	@Override
	public UpgModel getSpecificUpgData(String interfaceName) {
		loggerTypeB.info("getSpecificUpgData: Fetching data for interface");
		UpgModel data=upgRepository.getSpecificData(interfaceName);
		return data;
	}


	
	
	

}

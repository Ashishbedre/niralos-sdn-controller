package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Frontend.UpgServiceFontend;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgFrontendRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgInterfaceDataRepository;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;


@EnableScheduling
@Service
public class UpgServiceFrontendImpl implements UpgServiceFontend{
	final Logger logger = LoggerFactory.getLogger(UpgServiceFrontendImpl.class);

	
	@Autowired
	UpgInterfaceDataRepository upgInterfaceDataRepository;
	@Autowired
	UpgFrontendRepository upgRepository;

	@Override
	public List<UpgModel> getUpgData() {
		return upgRepository.findAllData();
	}

	@Override
	public UpgModel getSpecificUpgData(String interfaceName) {
		UpgModel data=upgRepository.getSpecificData(interfaceName);
		return data;
	}


	
	
	

}

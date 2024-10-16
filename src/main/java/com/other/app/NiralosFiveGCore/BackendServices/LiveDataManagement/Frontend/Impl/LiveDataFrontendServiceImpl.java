package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Frontend.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Frontend.LiveDataFrontendService;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.Frontend.LiveDataFrontendRepo;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;

@Service
public class LiveDataFrontendServiceImpl implements LiveDataFrontendService{
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:LiveDataFrontendServiceImpl");
	@Autowired
	LiveDataFrontendRepo liveDataRepository;

	@Override
	public LiveDataModel getLiveData() 
	{    loggerTypeB.info("Starting the process to retrieve live data from the repository.");
		// Retrieve the live data model from the repository
		LiveDataModel dataModel = liveDataRepository.findLiveDataModel();
		return dataModel;
	}


}

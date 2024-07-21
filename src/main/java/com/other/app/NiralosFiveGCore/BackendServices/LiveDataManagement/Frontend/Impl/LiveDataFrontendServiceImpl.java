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
	final Logger logger = LoggerFactory.getLogger(LiveDataFrontendServiceImpl.class);
	@Autowired
	LiveDataFrontendRepo liveDataRepository;

	@Override
	public LiveDataModel getLiveData() 
	{
		LiveDataModel dataModel = liveDataRepository.findLiveDataModel();
		return dataModel;
	}


}

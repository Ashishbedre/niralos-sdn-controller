package com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend.TopUserService;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend.TopUserRepository;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend.UeStatsFrontendRepository;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;
import com.other.app.NiralosFiveGCore.model.UeStats.Frontend.TopUserModel;

@Service
public class TopUserServiceImpl implements TopUserService{
	final Logger logger = LoggerFactory.getLogger(TopUserServiceImpl.class);


	@Autowired
	UeStatsFrontendRepository ueStatsRepository;
	
	@Autowired
	TopUserRepository topUserRepository;
	
	@Override
	public ArrayList<TopUserModel> getTopUser() {
		ArrayList<TopUserModel> topUserModels=new ArrayList<>();
		List<UeStatsModel1> imsis=ueStatsRepository.getAllDistinctImsiData();
		
		for (UeStatsModel1 imsi : imsis) {
			
			
			TopUserModel model=new TopUserModel();
			if(topUserRepository.checkDistinctImsi(imsi.getImsi())==0)
			{
			
			model.setImsi(imsi.getImsi());
			model.setUpLinkBytes(ueStatsRepository.getTotalUplink(imsi.getImsi()));
			model.setDownLinkBytes(ueStatsRepository.getTotalDownlink(imsi.getImsi()));
			model.setTotalBytes(ueStatsRepository.getTotalBytes(imsi.getImsi()));
			List<UeStatsModel1> data=	ueStatsRepository.getItemizedUeDetails(imsi.getImsi());
			for (UeStatsModel1 string : data) {
				model.setTenentId(string.getTenentId() );
				model.setSiteId(string.getSiteId());
			}
		
			
			topUserRepository.save(model);
		     }else {
		    	 topUserRepository.updateTopUser(ueStatsRepository.getTotalUplink(imsi.getImsi()),ueStatsRepository.getTotalDownlink(imsi.getImsi()),ueStatsRepository.getTotalBytes(imsi.getImsi()),imsi.getImsi());
		     }
		}
		topUserModels=topUserRepository.getTopUserData();
		return topUserModels;
	}
}

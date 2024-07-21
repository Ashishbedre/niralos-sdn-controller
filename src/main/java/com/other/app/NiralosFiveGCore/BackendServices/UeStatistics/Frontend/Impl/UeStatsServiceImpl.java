package com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.UeStatistics.Frontend.UeStatsFrontendService;
import com.other.app.NiralosFiveGCore.Dto.Frontend.ReturnSiteList;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UestatsPagesCount;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend.UeStatsDistinct;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.Frontend.LiveDataFrontendRepo;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend.UeStatsFrontendRepository;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;


@Service
public class UeStatsServiceImpl implements UeStatsFrontendService{
	final Logger logger = LoggerFactory.getLogger(UeStatsServiceImpl.class);

	
	@Autowired
	UeStatsFrontendRepository ueStatsRepository;
	
	@Autowired
	LiveDataFrontendRepo liveDataRepository;

	@Override
	public ArrayList<UeStatsDistinct> getAllUeStatsData(int pageNumber, int pageSize) {
		ArrayList<UeStatsDistinct> ueStatsDistincts = new ArrayList<>();
		
		 PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
	        List<UeStatsModel1> imsis = ueStatsRepository.getAllDistinctImsi(pageable);
		for (UeStatsModel1 imsi : imsis) {
				
				UeStatsDistinct ueStatsDistinct = new UeStatsDistinct();
				ueStatsDistinct.setImsi(imsi.getImsi());
				ueStatsDistinct.setTotalUplink(ueStatsRepository.getTotalUplink(imsi.getImsi()));
				ueStatsDistinct.setTotalDownlink(ueStatsRepository.getTotalDownlink(imsi.getImsi()));
				ueStatsDistinct.setNumberOfSessions(ueStatsRepository.getNumberOfSesions(imsi.getImsi()));
				ueStatsDistincts.add(ueStatsDistinct);
			
			}
//		}
		
		return ueStatsDistincts;
	}

	@Override
	public ArrayList<UeStatsModel1> getItemizedUeDetails(String imsi) {
		return ueStatsRepository.getItemizedUeDetails(imsi);
	}

	@Override
	public ArrayList<UeStatsDistinct> getUeStatsDataPerGnb(String gnbId) {
		ArrayList<UeStatsDistinct> ueStatsDistinctsPerGnb = new ArrayList<>();
		List<UeStatsModel1> imsis = ueStatsRepository.getAllDistinctImsiPerGnb(gnbId);
		for (UeStatsModel1 imsi : imsis) 
		{
			UeStatsDistinct ueStatsDistinct = new UeStatsDistinct();
			ueStatsDistinct.setImsi(imsi.getImsi());
			ueStatsDistinct.setTotalUplink(ueStatsRepository.getTotalUplink(imsi.getImsi()));
			ueStatsDistinct.setTotalDownlink(ueStatsRepository.getTotalDownlink(imsi.getImsi()));
			ueStatsDistinct.setNumberOfSessions(ueStatsRepository.getNumberOfSesions(imsi.getImsi()));
			ueStatsDistinctsPerGnb.add(ueStatsDistinct);
		
		}
		return ueStatsDistinctsPerGnb;
	}
	@Override
	public ArrayList<UeStatsDistinct> searchUeStatsData(String imsi) {
		ArrayList<UeStatsDistinct> ueStatsDistincts = new ArrayList<>();
		List<String> imsis = ueStatsRepository.FindDistinctImsi(imsi);
		for (String imsi1 : imsis) 
		{
			UeStatsDistinct ueStatsDistinct = new UeStatsDistinct();
			ueStatsDistinct.setImsi(imsi1);
			ueStatsDistinct.setTotalUplink(ueStatsRepository.getTotalUplink(imsi1));
			ueStatsDistinct.setTotalDownlink(ueStatsRepository.getTotalDownlink(imsi1));
			ueStatsDistinct.setNumberOfSessions(ueStatsRepository.getNumberOfSesions(imsi1));
			ueStatsDistincts.add(ueStatsDistinct);
		}
		
		return ueStatsDistincts;
	}

	@Override
	public List<ReturnSiteList> getallSitelist(String tenentId) {
	List<String> siteList = ueStatsRepository.findsitelistofUe(tenentId);
	
	List<ReturnSiteList> siteLists = new ArrayList<>();
	for (String string : siteList) {
		ReturnSiteList list = new ReturnSiteList();
		list.setSite(string);
		siteLists.add(list);
	}
		return siteLists;
	}
	

	@Override
	public List<ReturnSiteList> getallSitelistofOverview(String tenentId) {
		List<String> siteList = liveDataRepository.returnListof(tenentId);
		List<ReturnSiteList> overviewSiteLists = new ArrayList<>();
		for (String string : siteList) {
			ReturnSiteList list = new ReturnSiteList();
			list.setSite(string);
			overviewSiteLists.add(list);
		}
		
		return overviewSiteLists;
	}

	@Override
	public UestatsPagesCount getAllUeStatsPages(int pageNumber, int pageSize) {
		 PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
		 Page<UeStatsModel1> imsis = ueStatsRepository.findAll(pageable);
		 UestatsPagesCount uestatsPagesCount = new UestatsPagesCount();
		 uestatsPagesCount.setTotalPageCount(imsis.getTotalPages());
	return	uestatsPagesCount;
	}


	

}
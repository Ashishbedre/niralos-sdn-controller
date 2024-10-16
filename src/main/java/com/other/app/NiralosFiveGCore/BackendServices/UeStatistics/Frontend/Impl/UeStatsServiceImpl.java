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
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend.StatusCountDTO;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend.UeStatsDistinct;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.Frontend.LiveDataFrontendRepo;
import com.other.app.NiralosFiveGCore.Repository.UeStatistics.Frontend.UeStatsFrontendRepository;
import com.other.app.NiralosFiveGCore.model.UeStatsModel1;


@Service
public class UeStatsServiceImpl implements UeStatsFrontendService{
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:UeStatsServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(UeStatsServiceImpl.class);

	
	@Autowired
	UeStatsFrontendRepository ueStatsRepository;
	
	@Autowired
	LiveDataFrontendRepo liveDataRepository;

	@Override
	public ArrayList<UeStatsDistinct> getAllUeStatsData(int pageNumber, int pageSize) {
		ArrayList<UeStatsDistinct> ueStatsDistincts = new ArrayList<>();
		
		 PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
		 List<UeStatsModel1> imsis = ueStatsRepository.getAllDistinctImsi(pageable);
		// Iterating over each IMSI to gather detailed UE stats
		for (UeStatsModel1 imsi : imsis) {
			    loggerTypeB.info("getAllUeStatsData: Processing IMSI: " + imsi.getImsi());
				UeStatsDistinct ueStatsDistinct = new UeStatsDistinct();
				ueStatsDistinct.setImsi(imsi.getImsi());
				ueStatsDistinct.setTotalUplink(ueStatsRepository.getTotalUplink(imsi.getImsi()));
				ueStatsDistinct.setTotalDownlink(ueStatsRepository.getTotalDownlink(imsi.getImsi()));
				ueStatsDistinct.setNumberOfSessions(ueStatsRepository.getNumberOfSesions(imsi.getImsi()));
				ueStatsDistincts.add(ueStatsDistinct);
			
			}

		// Returning the list of distinct UE stats data
		return ueStatsDistincts;
	}

	@Override
	public ArrayList<UeStatsModel1> getItemizedUeDetails(String imsi) {
		return ueStatsRepository.getItemizedUeDetails(imsi);
	}

	@Override
	public ArrayList<UeStatsDistinct> getUeStatsDataPerGnb(String gnbId,int pageNumber,int pageSize) {
		// Initializing the list to hold distinct UE stats data per GNB
		ArrayList<UeStatsDistinct> ueStatsDistinctsPerGnb = new ArrayList<>();
		 PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<UeStatsModel1> imsis = ueStatsRepository.getAllDistinctImsiPerGnbwithpage(gnbId,pageable);
		for (UeStatsModel1 imsi : imsis) 
		{  loggerTypeB.info("getUeStatsDataPerGnb: Processing IMSI and GNB ID");
			UeStatsDistinct ueStatsDistinct = new UeStatsDistinct();
			ueStatsDistinct.setImsi(imsi.getImsi());
			ueStatsDistinct.setTotalUplink(ueStatsRepository.getTotalUplink(imsi.getImsi()));
			ueStatsDistinct.setTotalDownlink(ueStatsRepository.getTotalDownlink(imsi.getImsi()));
			ueStatsDistinct.setNumberOfSessions(ueStatsRepository.getNumberOfSesions(imsi.getImsi()));
			// Adding the populated UeStatsDistinct object to the list
			ueStatsDistinctsPerGnb.add(ueStatsDistinct);
		
		}
		return ueStatsDistinctsPerGnb;
	}
	@Override
	public ArrayList<UeStatsDistinct> searchUeStatsData(String imsi) {
		ArrayList<UeStatsDistinct> ueStatsDistincts = new ArrayList<>();
		List<String> imsis = ueStatsRepository.FindDistinctImsi(imsi);
		loggerTypeB.info("searchUeStatsData: Found distinct IMSIs matching the search criteria for IMSI");
		for (String imsi1 : imsis) 
		{   loggerTypeB.info("searchUeStatsData: Processing IMSI: " + imsi1);
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
		// Fetching the list of sites associated with the given tenant ID from the repository
	List<String> siteList = ueStatsRepository.findsitelistofUe(tenentId);
	
	List<ReturnSiteList> siteLists = new ArrayList<>();
	for (String string : siteList) {
		ReturnSiteList list = new ReturnSiteList();
		list.setSite(string);
		siteLists.add(list);
	}
		loggerTypeB.info("getallSitelist: Completed processing all sites for tenant ID");
		return siteLists;
	}
	

	@Override
	public List<ReturnSiteList> getallSitelistofOverview(String tenentId) {
		// Fetching the list of sites from the live data repository
		List<String> siteList = liveDataRepository.returnListof();
		List<ReturnSiteList> overviewSiteLists = new ArrayList<>();
		for (String string : siteList) {
			loggerTypeB.info("getallSitelistofOverview: Processing site for overview");
			ReturnSiteList list = new ReturnSiteList();
			list.setSite(string);
			overviewSiteLists.add(list);
		}
		
		return overviewSiteLists;
	}

	@Override
	public UestatsPagesCount getAllUeStatsPages(int pageNumber, int pageSize) {
		// Creating a PageRequest object with the given page number and page si
		 UestatsPagesCount uestatsPagesCount = new UestatsPagesCount();
		 List<UeStatsModel1>imsis=ueStatsRepository.findAll();
		 if(imsis.isEmpty()){
			 uestatsPagesCount.setTotalPageCount(1);
		 }else {
			 PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
			 Page<UeStatsModel1> imsis1 = ueStatsRepository.findAll(pageable);
			 uestatsPagesCount.setTotalPageCount(imsis1.getTotalPages());
		 }
		loggerTypeB.info("getAllUeStatsPages: Successfully retrieved the total page count for UeStats");
	return	uestatsPagesCount;
	}

	@Override
	public UestatsPagesCount getAllUeStatsPagesperGnb(int pageNumber, int pageSize, String gnbId) {
		// Creating a PageRequest object with the given page number and page size
		 PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
		 Page<UeStatsModel1> imsis = ueStatsRepository.getAllDistinctImsiPerGnbwithpage(gnbId,pageable);
		
		 UestatsPagesCount uestatsPagesCount = new UestatsPagesCount();
		 uestatsPagesCount.setTotalPageCount(imsis.getTotalPages());
		 loggerTypeB.info("getAllUeStatsPagesperGnb: Successfully retrieved the total page count for UeStats for GNB ID");
		// Returning the UestatsPagesCount object containing the total page count
		return uestatsPagesCount;
	}

	@Override
	 public StatusCountDTO getStatusCounts() {
        long totalActive = ueStatsRepository.countByStatusUsingQueryTrue();
        long totalInactive = ueStatsRepository.countByStatusUsingQueryFalse();
        return new StatusCountDTO(totalActive, totalInactive);
    
	}


	

}
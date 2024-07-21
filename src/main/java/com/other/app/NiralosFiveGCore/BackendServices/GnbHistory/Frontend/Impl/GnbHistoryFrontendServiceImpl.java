package com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Frontend.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Frontend.GnbHistoryFrontendService;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.FailureListFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.GnbListDistinctFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.GnbListFrontendDto;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbFailureListRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbRegistrationDeregistrationFailureCountRepository;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbFailureList;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbInfoModel;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;
@Service
public class GnbHistoryFrontendServiceImpl implements GnbHistoryFrontendService {
	@Autowired
	GnbInfoRepository gnbInfoRepository;
	@Autowired
	GnbFailureListRepository gnbFailureListRepository;
	@Autowired
	GnbRegistrationDeregistrationFailureCountRepository gnbRegistrationDeregistrationFailureCountRepository;
	@Override
	public List<GnbRegistrationDeregistrationFailureCount> getGnbInfo() {
		return gnbRegistrationDeregistrationFailureCountRepository.findAll();
	}

	@Override
	public List<GnbListDistinctFrontendDto> getUniqueGnbList() {
	    List<GnbInfoModel> gnbList = gnbInfoRepository.findAll();

	    List<GnbListDistinctFrontendDto> uniqgnbIdDataList = new ArrayList<>();
	    List<String> uniqueGnbList = new ArrayList<>();

	    for (GnbInfoModel entity : gnbList) {
	        String gnbId = entity.getGnbId();

	        // Check if the gnbId is not already in the unique list (i.e., unique)
	        if (!uniqueGnbList.contains(gnbId)) {
	            uniqueGnbList.add(gnbId);

	            // Create a GnbListDistinct object and add it to the result list
	            GnbListDistinctFrontendDto uniqgnbIdDataList1 = new GnbListDistinctFrontendDto();
	            
	            // Populate the values from the original entity
	            uniqgnbIdDataList1.setGnbId(entity.getGnbId());
	            uniqgnbIdDataList1.setRegistrationStatus(entity.isRegistrationStatus());
	            uniqgnbIdDataList1.setGnbUpTime(entity.getGnbUpTime());
	            uniqgnbIdDataList1.setGnbDownTime(entity.getGnbDownTime());
	            uniqgnbIdDataList1.setAgentId(entity.getAgentId());
	            uniqgnbIdDataList1.setSiteId(entity.getSiteId());
	            uniqgnbIdDataList1.setTenentId(entity.getTenentId());

	            uniqgnbIdDataList.add(uniqgnbIdDataList1);
	        }
	    }

	    return uniqgnbIdDataList;
	}

	@Override
	public List<GnbListFrontendDto> getGnbList(String gnbId, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
	    Page<GnbInfoModel> gnbListData = gnbInfoRepository.findByGnbIdOrderByIdDesc(gnbId, pageable);

	    List<GnbListFrontendDto> gnbList = new ArrayList<>();

	    for (GnbInfoModel gnb : gnbListData) {
	        GnbListFrontendDto dto = new GnbListFrontendDto();
	        dto.setGnbDownTime(gnb.getGnbDownTime());
	        dto.setGnbUpTime(gnb.getGnbUpTime());
	        dto.setGnbId(gnb.getGnbId());
	        dto.setRegistrationStatus(gnb.isRegistrationStatus());
	        dto.setAgentId(gnb.getAgentId());
	        dto.setTenentId(gnb.getTenentId());
	        dto.setSiteId(gnb.getSiteId());

	        gnbList.add(dto);
	    }

	    return gnbList;
	}



	@Override
	 public List<FailureListFrontendDto> getGnbfailureList(String gnbId) {
       List<GnbFailureList> gnbFailureList = gnbFailureListRepository.findByGnbId(gnbId);
       List<FailureListFrontendDto> gnbFailureDtoList = new ArrayList<>();

       for (GnbFailureList gnbFailure : gnbFailureList) {
    	   FailureListFrontendDto dto = new FailureListFrontendDto();
           dto.setFailureReason(gnbFailure.getFailureReason());
           dto.setFailureCount(gnbFailure.getFailureCount());
           dto.setGnbId(gnbFailure.getGnbId());
           dto.setTenentId(gnbFailure.getTenentId());
           dto.setSiteId(gnbFailure.getSiteId());

           gnbFailureDtoList.add(dto);
       }

       return gnbFailureDtoList;
   }
}

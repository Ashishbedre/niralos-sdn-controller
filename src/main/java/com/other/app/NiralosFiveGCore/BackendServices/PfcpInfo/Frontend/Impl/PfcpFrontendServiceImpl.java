package com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Frontend.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Frontend.PfcpFrontendService;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpFailReasonFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpInfoFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpInfoRootFrontendDto;
import com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend.PfcpInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend.PfcpSessioEstablishmentModificationDelationFailReasonListRepository;
import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpInfo;
import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpSessioEstablishmentModificationDelationFailReasonList;
@Service
public class PfcpFrontendServiceImpl implements PfcpFrontendService {
	final Logger logger = LoggerFactory.getLogger(PfcpFrontendServiceImpl.class);

	 @Autowired
	    private PfcpInfoRepository pfcpInfoRepository;

	    @Autowired
	    private PfcpSessioEstablishmentModificationDelationFailReasonListRepository pfcpFailReasonListRepository;

	    @Override
	    public PfcpInfoRootFrontendDto getPfcpInfoRootDtoByTenantAndSite() {
	        try {
	            // Fetch data from your data source, e.g., repository or database
	            List<PfcpInfo> pfcpInfoList = pfcpInfoRepository.findAll();

	            if (pfcpInfoList.isEmpty()) {
	                logger.info("PFCP: No data found in the database.");
	                return null;
	            }

	            // Assuming you only want to work with the first PfcpInfo in the list
	            PfcpInfo pfcpInfo = pfcpInfoList.get(0);

	            // Map the data to PfcpInfoRootDto
	            PfcpInfoRootFrontendDto pfcpInfoDto = new PfcpInfoRootFrontendDto();
	            pfcpInfoDto.setTotal_cp_assoc_req(pfcpInfo.getTotal_cp_assoc_req());
	            pfcpInfoDto.setTotal_cp_assoc_resp(pfcpInfo.getTotal_cp_assoc_resp());
	            pfcpInfoDto.setTotalDelReq(pfcpInfo.getTotalDelReq());
	            pfcpInfoDto.setTotalDelRsp(pfcpInfo.getTotalDelRsp());
	            pfcpInfoDto.setTotalModReq(pfcpInfo.getTotalModReq());
	            pfcpInfoDto.setTotalModRsp(pfcpInfo.getTotalModRsp());
	            pfcpInfoDto.setTotalEstRsp(pfcpInfo.getTotalEstRsp());
	            pfcpInfoDto.setTotalEstReq(pfcpInfo.getTotalEstReq());
	            pfcpInfoDto.setFailDelRsp(pfcpInfo.getFailDelRsp());
	            pfcpInfoDto.setFailModRsp(pfcpInfo.getFailModRsp());
	            pfcpInfoDto.setFailEstRsp(pfcpInfo.getFailEstRsp());
	            pfcpInfoDto.setSucessDelReq(pfcpInfo.getSucessDelReq());
	            pfcpInfoDto.setSucessDelRsp(pfcpInfo.getSucessDelRsp());
	            pfcpInfoDto.setSucessModReq(pfcpInfo.getSucessModReq());
	            pfcpInfoDto.setSucessModRsp(pfcpInfo.getSucessModRsp());
	            pfcpInfoDto.setSucessEstReq(pfcpInfo.getSucessEstReq());
	            pfcpInfoDto.setSucessEstRsp(pfcpInfo.getSucessEstRsp());

	            // Map other fields as needed

	            return pfcpInfoDto;
	        } catch (Exception e) {
	            logger.error("Error fetching PFCP data from the database.", e);
	        }
	        return null;
	    }

	    @Override
	    public PfcpInfoFrontendDto getFailReasonList() {
	    	try {
	    		PfcpInfoFrontendDto dto = new PfcpInfoFrontendDto();

	        List<PfcpFailReasonFrontendDto> sessionEstablishmentFailReasonList = new ArrayList<>();
	        List<PfcpFailReasonFrontendDto> sessionModificationFailReasonList = new ArrayList<>();
	        List<PfcpFailReasonFrontendDto> sessionDeletionFailReasonList = new ArrayList<>();

	        // Fetch data from the repository or any other source as needed
	        // You can customize the logic to retrieve data according to your requirements

	        // Example of fetching data from the repository
	        List<PfcpSessioEstablishmentModificationDelationFailReasonList> dataList = pfcpFailReasonListRepository.findAll();

	        for (PfcpSessioEstablishmentModificationDelationFailReasonList entity : dataList) {
	            if (entity.getSessionEstablishmentFailReasonList() != null) {
	            	PfcpFailReasonFrontendDto entry = new PfcpFailReasonFrontendDto(entity.getSessionEstablishmentFailReasonList(), entity.getRepeatData());
	                sessionEstablishmentFailReasonList.add(entry);
	            }
	            if (entity.getSessionModificationFailreasonList() != null) {
	            	PfcpFailReasonFrontendDto entry = new PfcpFailReasonFrontendDto(entity.getSessionModificationFailreasonList(), entity.getRepeatData());
	                sessionModificationFailReasonList.add(entry);
	            }
	            if (entity.getSessionDeletionFailreasonList() != null) {
	            	PfcpFailReasonFrontendDto entry = new PfcpFailReasonFrontendDto(entity.getSessionDeletionFailreasonList(), entity.getRepeatData());
	                sessionDeletionFailReasonList.add(entry);
	            }
	        }

	        dto.setSessionEstablishmentFailReasonList(sessionEstablishmentFailReasonList);
	        dto.setSessionModificationFailreasonList(sessionModificationFailReasonList);
	        dto.setSessionDeletionFailreasonList(sessionDeletionFailReasonList);
	        return dto;
	    	}
	    	catch (Exception e) {
	    		logger.info("PFCP : Not much enough Data to show");
			}
			return null;
	    }
}

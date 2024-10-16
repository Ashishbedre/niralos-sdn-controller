package com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Frontend.Impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Frontend.UeHistoryFrontendService;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.CauseFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.PduEstInfoFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.PduReleaseInfoFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeEntityFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeHistorDistinctFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeInfoFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UestatsPagesCount;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeHistoryRepository;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UePduCauseInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeRegistraionDeregistrationFailureCountRepository;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UePduCauseInfoModel;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeRegistraionDeregistrationFailureCount;
@Service
public class UeHistoryFrontendServiceImpl implements UeHistoryFrontendService {
		@Autowired
		UeHistoryRepository ueStatEntityRepository;
		@Autowired
	    UePduCauseInfoRepository causeInfoRepository;
	    @Autowired
	    UeRegistraionDeregistrationFailureCountRepository ueCountdownInfoRepository;
	    
		//final Logger logger = LoggerFactory.getLogger(UeHistoryFrontendServiceImpl.class);
	    private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:UeHistoryFrontendServiceImpl");
	    @Override
		 public List<UeHistorDistinctFrontendDto> getUniqueImsiData(int pageNumber, int pageSize) {
//	       List<UeHistoryModel> ueStatEntities = ueStatEntityRepository.findAll();
//
//	       List<UeHistorDistinctFrontendDto> uniqueImsiDataList = new ArrayList<>();
//	       List<String> uniqueImsiList = new ArrayList<>();
//	       ueStatEntities.sort(Comparator.comparing(UeHistoryModel::getImsi));
//	       for (UeHistoryModel entity : ueStatEntities) {
//	           String imsi = entity.getImsi();
//
//	           // Check if the IMSI is not null, not equal to "(null)", and not already in the unique list (i.e., unique)
//	           if (imsi != null && !imsi.equals("(null)") && !uniqueImsiList.contains(imsi)) {
//	               uniqueImsiList.add(imsi);
//
//
//	               // Create a UeStatEntityDidtinct object and add it to the result list
//	               UeHistorDistinctFrontendDto ueStatEntityDidtinct = new UeHistorDistinctFrontendDto(
//	                   entity.getUeStatus(),
//	                   entity.getGnbId(),
//	                   imsi,
//	                   entity.getPduStatus()
//	               );
//	               uniqueImsiDataList.add(ueStatEntityDidtinct);
//	           }
//	       }
//	       return uniqueImsiDataList;
	    	
	    	  List<UeHistoryModel> ueStatEntities = ueStatEntityRepository.findAll();

	          // Sort the data by IMSI in ascending order
	          ueStatEntities.sort(Comparator.comparing(UeHistoryModel::getImsi));

	          List<UeHistorDistinctFrontendDto> uniqueImsiDataList = new ArrayList<>();
	          Set<String> uniqueImsiSet = new HashSet<>();

	          // Process retrieved data to filter unique IMSI values
	          for (UeHistoryModel entity : ueStatEntities) {
	              String imsi = entity.getImsi();

	              // Check if the IMSI is not null, not equal to "(null)", and not already in the unique set (i.e., unique)
	              if (imsi != null && !imsi.equals("(null)") && uniqueImsiSet.add(imsi)) {
	                  // Create a UeHistorDistinctFrontendDto object and add it to the result list
	                  UeHistorDistinctFrontendDto ueStatEntityDistinct = new UeHistorDistinctFrontendDto(
	                          entity.getUeStatus(),
	                          entity.getGnbId(),
	                          imsi,
	                          entity.getPduStatus()
	                  );
	                  uniqueImsiDataList.add(ueStatEntityDistinct);
	              }
	          }

	          // Apply manual pagination
	          int fromIndex = Math.min(pageSize * (pageNumber - 1), uniqueImsiDataList.size());
	          int toIndex = Math.min(fromIndex + pageSize, uniqueImsiDataList.size());
	          List<UeHistorDistinctFrontendDto> paginatedList = uniqueImsiDataList.subList(fromIndex, toIndex);

	          // Debugging: Print the size of the result list
//	          System.out.println("Total Unique IMSI Data List Size: " + uniqueImsiDataList.size());
//	          System.out.println("Paginated List Size: " + paginatedList.size());

	          return paginatedList;
	      }
	   
	
	@Override
	public List<UeHistoryModel> getLatest20UeStatsByImsi(String imsi) {
       // Create a Pageable with a limit of 20
       PageRequest pageable = PageRequest.of(0, 20);
       // Fetch the latest 20 data entries based on IMSI
       return ueStatEntityRepository.findByImsiOrderByTimeDesc(imsi, pageable);
   }

	 public UeEntityFrontendDto getUeEntityData() {
		 UeEntityFrontendDto ueEntityDTO = new UeEntityFrontendDto();
	        List<UeInfoFrontendDto> ueInfoList = new ArrayList<>();
	        List<PduEstInfoFrontendDto> pduEstInfoList = new ArrayList<>();
	        List<PduReleaseInfoFrontendDto> pduReleaseInfoList = new ArrayList<>();
	try {
	        // Fetch data from the UeCountdownInfo table based on tenantId and siteId
	        List<UeRegistraionDeregistrationFailureCount> ueCountdownInfoList = ueCountdownInfoRepository.findAll();

	        // Process data from UeCountdownInfo table
	        for (UeRegistraionDeregistrationFailureCount ueCountdownInfo : ueCountdownInfoList) {
				loggerTypeB.info("getUeEntityData: Process data from UeCountdown");
	            UeInfoFrontendDto ueInfo = new UeInfoFrontendDto();
	            ueInfo.setTotalNumberOfRegAttempts(ueCountdownInfo.getTotalNumberOfRegAttempts());
	            ueInfo.setNoOfRegistered(ueCountdownInfo.getNoOfRegistered());
	            ueInfo.setNoOfDeregistered(ueCountdownInfo.getNoOfDeregistered());
	            ueInfo.setNoOfFailure(ueCountdownInfo.getNoOfFailure());

	            // Fetch and process cause information from CauseInfo table
	            List<UePduCauseInfoModel> causeInfoList = causeInfoRepository.findAll();

	            List<CauseFrontendDto> uecauseList = new ArrayList<>();
	            for (UePduCauseInfoModel causeInfo : causeInfoList) {
	                CauseFrontendDto cause = new CauseFrontendDto();
	                if (causeInfo.getUecauselist() != null) {
	                    cause.setCauseName(causeInfo.getUecauselist());
	                    cause.setRepeated(causeInfo.getRepeated());
	                    uecauseList.add(cause);
	                }
	            }
	            ueInfo.setCauseList(uecauseList);
	            ueInfoList.add(ueInfo);
	        }


	        // Fetch data from the PduEstInfo table based on tenantId and siteId (similar process as above)
	        List<UeRegistraionDeregistrationFailureCount> ueCountdownInfoList1 = ueCountdownInfoRepository.findAll();
	        // Process data from UeCountdownInfo table for PduEstInfo
	        for (UeRegistraionDeregistrationFailureCount ueCountdownInfo : ueCountdownInfoList1) {
	            PduEstInfoFrontendDto pduInfo = new PduEstInfoFrontendDto();
	            pduInfo.setNoOfpduEstReq(ueCountdownInfo.getNoOfpduEstReq());
	            pduInfo.setNoOfpduEstSuccess(ueCountdownInfo.getNoOfpduEstSuccess());
	            pduInfo.setNoOfpduEstFailure(ueCountdownInfo.getNoOfpduEstFailure());

	            // Fetch and process cause information from CauseInfo table
				loggerTypeB.info("Fetching cause information for PduEstInfo from CauseInfo table.");
				List<UePduCauseInfoModel> causeInfoList = causeInfoRepository.findAll();

	            List<CauseFrontendDto> pduCauseList = new ArrayList<>();

	            for (UePduCauseInfoModel causeInfo : causeInfoList) {
	                // Check if pducauselist is not null for each row before adding it to the list
	                if (causeInfo.getPducauselist() != null) {
	                    CauseFrontendDto cause1 = new CauseFrontendDto();
	                    cause1.setCauseName(causeInfo.getPducauselist());
	                    cause1.setRepeated(causeInfo.getRepeated());
	                    pduCauseList.add(cause1);
	                }
	            }

	            pduInfo.setPduCauseList(pduCauseList);

	            pduEstInfoList.add(pduInfo);
	        }

	        // Fetch data from the PduReleaseInfo table based on tenantId and siteId (similar process as above)
	        List<UeRegistraionDeregistrationFailureCount> ueCountdownInfoList2 = ueCountdownInfoRepository.findAll();

	        // Process data from UeCountdownInfo table for PduReleaseInfo
	        for (UeRegistraionDeregistrationFailureCount ueCountdownInfo : ueCountdownInfoList2) {
				loggerTypeB.info("");
	            PduReleaseInfoFrontendDto pduReleaseInfo = new PduReleaseInfoFrontendDto();
	            pduReleaseInfo.setNoOfpduReleaseReq(ueCountdownInfo.getNoOfpduReleaseReq());
	            pduReleaseInfo.setNoOfpduReleaseSuccess(ueCountdownInfo.getNoOfpduReleaseSuccess());

	            // You can fetch and process cause information for PduReleaseInfo here if needed
	            pduReleaseInfoList.add(pduReleaseInfo);
	        }
	        // Set the collected data in the UeEntityDTO
	        ueEntityDTO.setUeInfo(ueInfoList);
	        ueEntityDTO.setPduEstInfo(pduEstInfoList);
	        ueEntityDTO.setPduReleaseInfo(pduReleaseInfoList);
		    loggerTypeB.info("Successfully retrieved and processed UE entity data.");
	        return ueEntityDTO;
	}catch (Exception e) {
		loggerTypeB.error("No Data is there to Show Check Data Base for Further Inquires");
	}
	return null;
	    }


	@Override
	public UestatsPagesCount getAllUeStatsPages(int pageSize, int pageNumber) {

		UestatsPagesCount uestatsPagesCount = new UestatsPagesCount();
		List<UeHistoryModel> ueStatEntities = ueStatEntityRepository.findAll();
		 

		 if(ueStatEntities.isEmpty()) {
			
			 uestatsPagesCount.setTotalPageCount(1);
		 }
		else {
			 
        // Sort the data by IMSI in ascending order
        ueStatEntities.sort(Comparator.comparing(UeHistoryModel::getImsi));
        List<UeHistorDistinctFrontendDto> uniqueImsiDataList = new ArrayList<>();
        Set<String> uniqueImsiSet = new HashSet<>();

        // Process retrieved data to filter unique IMSI values
        for (UeHistoryModel entity : ueStatEntities) {
			loggerTypeB.info("getAllUeStatsPages: Process retrieved data to filter");
            String imsi = entity.getImsi();

            // Check if the IMSI is not null, not equal to "(null)", and not already in the unique set (i.e., unique)
            if (imsi != null && !imsi.equals("(null)") && uniqueImsiSet.add(imsi)) {
                // Create a UeHistorDistinctFrontendDto object and add it to the result list
                UeHistorDistinctFrontendDto ueStatEntityDistinct = new UeHistorDistinctFrontendDto(
                        entity.getUeStatus(),
                        entity.getGnbId(),
                        imsi,
                        entity.getPduStatus()
                );
                uniqueImsiDataList.add(ueStatEntityDistinct);
            }
        }

       int totalItems = uniqueImsiDataList.size();
       int totalPages = (int) Math.ceil((double) totalItems / pageSize);
		 uestatsPagesCount.setTotalPageCount(totalPages);
		}
		 return uestatsPagesCount;
		 
	}


}

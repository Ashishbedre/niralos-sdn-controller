package com.other.app.NiralosFiveGCore.BackendServices.LogsDeletion.Impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.LogsDeletion.DeletionService;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpfErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend.UpgErrorRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpfErrorDeltaModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorDeltaModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgErrorGraphModelRepository;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgInterfaceDataRepository;
import com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend.GnbInfoRepository;
import com.other.app.NiralosFiveGCore.Repository.Graph.GnbGraphRepository;
import com.other.app.NiralosFiveGCore.Repository.Graph.UeGraphRepository;
import com.other.app.NiralosFiveGCore.Repository.Throughput.ThroughputRepository;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeHistoryRepository;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbInfoModel;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;


@Service
@Configuration
@EnableScheduling
public class DeletionServiceImpl implements DeletionService{
	final Logger logger = LoggerFactory.getLogger(DeletionServiceImpl.class);

	@Autowired
	ThroughputRepository throughputRepository;
	
	@Autowired
	UeGraphRepository ueGraphRepository;

	@Autowired
	GnbGraphRepository gnbGraphRepository;
	
	@Autowired
	UpgInterfaceDataRepository upgInterfaceDataRepository;
	
	@Autowired
	UpgErrorGraphModelRepository upgErrorGraphModelRepository;
	
	@Autowired
	UpgErrorRepository upgErrorRepository;
	@Autowired
	UpgErrorDeltaModelRepository upgErrorDeltaModelRepository;
	
	@Autowired
	UpfErrorDeltaModelRepository upfErrorDeltaModelRepository;
	
	@Autowired
	UpfErrorRepository upfErrorModelRepository;
	
	@Autowired
    UeHistoryRepository ueHistoryRepository;
   @Autowired
   GnbInfoRepository gnbInfoRepository;
	
	


	@Scheduled(fixedRate = 10000)
	@Override
	public void DeletionThroughputData() {
		if(throughputRepository.checkThroughputDataLinesCount() >= 1000) {
			try {
				throughputRepository.deleteThroughputData(300);
				logger.info("Deleting the throughput Data");
			}
			catch (Exception e) {
				logger.info("Not enough data for deletion");

			}
			
		}else {
			logger.info("Not enough data for deletion");
		}
			
	}

	@Scheduled(fixedRate = 10000)
	@Override
	public void DeletionUeGraphData() {
		if(ueGraphRepository.checkUeDataLinesCount() >= 1000) {
			try {
				ueGraphRepository.deleteUeData(300);
				logger.info("Deleting the UeGraph Data");
			}
			catch (Exception e) {
				logger.info("Not enough data for deletion UeGraphData");

			}
			
		}else {
			logger.info("Not enough data for deletion UeGraphData");
		}
		
		
	}
	@Scheduled(fixedRate = 10000)
	@Override
	public void DeletionGnbGraphData() {
		if(gnbGraphRepository.checkGnbDataLinesCount() >= 1000) {
			try {
				gnbGraphRepository.deleteGnbData(300);
				logger.info("Deleting the GnbGraph Data");
			}
			catch (Exception e) {
				logger.info("Not enough data for deletion GnbGraphData");

			}
			
		}else {
			logger.info("Not enough data for deletion GnbGraphData");
		}		
	}

	@Scheduled(fixedRate = 10000)
	@Override
	public void DeletionErrorgraphData() {
		if(upgErrorGraphModelRepository.checkUpgErrorDataLinesCount() >= 1000) {
			try {
				upgErrorGraphModelRepository.deleteUpgErrorData(300);
				logger.info("Deleting the upgError Data ");
			}
			catch (Exception e) {
				logger.info("Not enough data for deletion upgError");

			}
			
		}else {
			logger.info("Not enough data for deletion upgErrorData");
		}		
		
	}

	@Scheduled(fixedRate = 10000)
	@Override
	public void DeletionUpgstacgraphData() {
		if(upgInterfaceDataRepository.checkUpgStacDataLinesCount() >= 1200) {
			try {
				upgInterfaceDataRepository.deleteUpgStacData(700);
				logger.info("Deleting the upgStac Data ");
			}
			catch (Exception e) {
				logger.info("Not enough data for deletion upgStacData");

			}
			
		}else {
			logger.info("Not enough data for deletion upgStacData");
		}		
	}
	
	@Override
	public void CheckUpgerrorAndDeltaErrorSite(String tenantName) {
		if(upgErrorRepository.countofSite(tenantName)>upgErrorDeltaModelRepository.countofSite(tenantName))
			upgErrorDeltaModelRepository.deleteAll();
	}


	
	@Override
	public void CheckUpferrorAndDeltaErrorSite(String tenantName) {
		if(upfErrorModelRepository.countofSite(tenantName)>upfErrorDeltaModelRepository.countofSite(tenantName))
	upfErrorDeltaModelRepository.deleteAll();
	}
	
	@Scheduled(fixedRate = 10000)
	@Override// Check every 10 seconds
	public void cleanupOldDataUeHistory() {
	    try {
	        long dataCount = ueHistoryRepository.count();

	        if (dataCount > 1000) {
	            int recordsToDelete = (int) (dataCount - 600); // Keep the latest 5 records
	            List<UeHistoryModel> allRecords = ueHistoryRepository.findAll();

	            if (allRecords.size() > recordsToDelete) {
	                Collections.sort(allRecords, (record1, record2) -> {
	                    // Implement a comparison based on the field you want to use (e.g., time)
	                    return record1.getId().compareTo(record2.getId());
	                });

	                for (int i = 0; i < recordsToDelete; i++) {
	                    UeHistoryModel recordToDelete = allRecords.get(i);
	                    ueHistoryRepository.delete(recordToDelete);
	                    logger.info("Data Deleted from UeHistory");

	                }
	            }
	        }
	    } catch (Exception e) {
			logger.error("Not enough data for deletion UeHistory");
	    }
	}

	@Scheduled(fixedRate = 10000) // Check every 10 seconds
	@Override
	public void cleanupOldDataGnbList() {
	    try {
	        long dataCount = gnbInfoRepository.count();
	        
	        if (dataCount > 1000) {
	            int recordsToDelete = (int) (dataCount - 600); // Keep the latest 3 records
	            List<GnbInfoModel> allRecords = gnbInfoRepository.findAll();
	            
	            if (allRecords.size() > recordsToDelete) {
	                Collections.sort(allRecords, (record1, record2) -> {
	                    // Implement a comparison based on the field you want to use (e.g., time)
	                    return record1.getId().compareTo(record2.getId());
	                });

	                for (int i = 0; i < recordsToDelete; i++) {
	                	GnbInfoModel recordToDelete = allRecords.get(i);
	                    gnbInfoRepository.delete(recordToDelete);
	                    logger.info("Data Deleted From GnbList");

	                }
	            }
	        }
	    } catch (Exception e) {
	    	logger.error("Not enough data for deletion GnbList");
	    }
	}
	
}

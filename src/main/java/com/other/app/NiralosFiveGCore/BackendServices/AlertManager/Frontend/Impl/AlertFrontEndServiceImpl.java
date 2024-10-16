package com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Frontend.Impl;
 
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Backend.Impl.AlertDataCollectorServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Frontend.AlertFrontEndService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.AlarmLevelDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.AlarmModelFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.SiteDto;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.AlertManager.AlarmCountRepository;
import com.other.app.NiralosFiveGCore.Repository.AlertManager.AlarmManagerModelRepository;
import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmCount;
import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmModel;
 
@Service
public class AlertFrontEndServiceImpl implements AlertFrontEndService {
 
	@Autowired
	AlarmManagerModelRepository alarmDataSyncRepository;
	@Autowired
	AlarmCountRepository alarmCountRepository;
	@Autowired
	InternalDataService internalDataService;
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:AlertFrontEndServiceImpl");
	
	//final Logger logger = LoggerFactory.getLogger(AlertFrontEndServiceImpl.class);
	
	@Override
	public ArrayList<AlarmModelFrontendDto> DetailsofAlarmsById(Integer id) {
		// TODO Auto-generated method stub
		loggerTypeB.info("Fetching details of alarms by ID: {}", id);
		List<AlarmModel> ListofAlerts = alarmDataSyncRepository.DetailsofAlarmsById(id);
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : ListofAlerts) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
		}
 
		return alarmModelFrontendDtos;
	}
 
	@Override
	public ArrayList<AlarmModelFrontendDto> listOfCriticalAlarms() {
		loggerTypeB.info("Fetching list of critical alarms");
		List<AlarmModel> ListofAlerts = alarmDataSyncRepository.listOfCriticalAlerts();
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : ListofAlerts) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
			}
		loggerTypeB.info("listOfCriticalAlarms: Exiting listOfCriticalAlarms method");
		
		return alarmModelFrontendDtos;
	}
 
	@Override
	public AlarmLevelDto CountLevelsofAlarms() {
		loggerTypeB.info("CountLevelsofAlarms: Counting levels of alarms");
		return new AlarmLevelDto(alarmDataSyncRepository.countofCrticalAlarms().toString(),
				alarmDataSyncRepository.countofMajorAlarms().toString(),
				alarmDataSyncRepository.countofMinorAlarms().toString(),
				alarmDataSyncRepository.countofNotAlarmed().toString(),
				alarmDataSyncRepository.countofNotReported().toString());
	}
 
	@Override
	public List<AlarmModel> listOfAlarms() {
		List<AlarmModel> ListofAlerts = alarmDataSyncRepository.findAll();
		return ListofAlerts;
	}
 
	@Override
	public ArrayList<AlarmModelFrontendDto> alarmsDetailsBydate(String startdate, String enddate) {
		List<AlarmModel> datetime = alarmDataSyncRepository.alarmsListBydateTime(startdate, enddate);
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : datetime) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
		}
		return alarmModelFrontendDtos;
 
	}
 
	@Override
	public void alarmstatusUpdate(String id, Boolean status) {
		if (alarmDataSyncRepository.checkStatusOfAlarm(id) == false) {
			alarmDataSyncRepository.alarmstatusUpdate(Long.parseLong(id), status);
		} else if (alarmDataSyncRepository.checkStatusOfAlarm(id) == true) {
			alarmDataSyncRepository.alarmstatus2Update(Long.parseLong(id), status);
		} else {
			loggerTypeB.info("alarmstatusUpdate: Not correct  Try Again ");
		}
 
	}
 
	@Override
	public ArrayList<AlarmModelFrontendDto> alertFilter(String level, Integer status) {
		List<AlarmModel> filterAlerts = alarmDataSyncRepository.alertFilter(level, status);
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : filterAlerts) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
		}
		return alarmModelFrontendDtos;
 
	}
 
	@Override
	public List<AlarmModel> listOfAlarmsForSite() {
		return alarmDataSyncRepository.findAll();
	}
 
	@Override
	public ArrayList<AlarmModelFrontendDto> alertFilterForSite(String level, Integer status) {
		List<AlarmModel> filterAlerts = alarmDataSyncRepository.alertFilterForSite(level, status);
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : filterAlerts) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
		}
		return alarmModelFrontendDtos;
	}
 
	@Override
	public List<SiteDto> getListOfSite() {
		List<SiteDto> data = new ArrayList<>();
		List<String> list = alarmDataSyncRepository.getListOfSite();
		for (String string : list) {
			SiteDto temp = new SiteDto();
			temp.setSite(string);
			data.add(temp);
		}
		return data;
	}
 

	
	
	public ArrayList<AlarmModelFrontendDto> frontendAlertDtoFunction(AlarmModel alarmModel,ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos){
		AlarmModelFrontendDto alarmModelFrontendDto = new AlarmModelFrontendDto();
		alarmModelFrontendDto.setId(alarmModel.getId().toString());
		alarmModelFrontendDto.setAlarmId(alarmModel.getAlarmId());
		alarmModelFrontendDto.setSubAlarmId(alarmModel.getSubAlarmId());
		alarmModelFrontendDto.setSourceDescription(alarmModel.getSourceDescription());
		alarmModelFrontendDto.setSourceType(alarmModel.getSourceType());
		alarmModelFrontendDto.setSourceIp(alarmModel.getSourceIp());
		alarmModelFrontendDto.setDestinationDescription(alarmModel.getDestinationDescription());
		alarmModelFrontendDto.setDestinationType(alarmModel.getDestinationType());
		alarmModelFrontendDto.setDestinationIp(alarmModel.getDestinationIp());
		alarmModelFrontendDto.setDateTime(alarmModel.getDateTime());
		alarmModelFrontendDto.setAlarmLevel(alarmModel.getAlarmLevel());
		alarmModelFrontendDto.setReason(alarmModel.getReason());
		alarmModelFrontendDto.setSolution(alarmModel.getSolution());
		alarmModelFrontendDto.setStatus(alarmModel.getStatus());
		alarmModelFrontendDtos.add(alarmModelFrontendDto);
		return alarmModelFrontendDtos;
	
	}
	
	
	@Override
	public long countCriticalAlarms() {
//		String tenentID = internalDataService.gettenantId();
//		String siteId = internalDataService.getsiteId();
		// Get the current count of critical alarms
		long currentCount = alarmDataSyncRepository.countofAll();
 
		// Get the previous count from your data source (e.g., a database or a cached
		// variable)
		long previousCount = retrievePreviousCountFromDatabase();
 
		// Calculate the difference between the current count and the previous count
		long countDifference = currentCount - previousCount;
 
		// Ensure countDifference is non-negative, returning 0 if it's negative
		if (countDifference < 0) {
			countDifference = 0;
			loggerTypeB.info("CountCriticalAlarms: CountDifference Value is negative");

		}
 
		// Update the previous count with the current count for future use
		//updatePreviousCountInDatabase(tenentID, siteId,currentCount);
 
		// Return the count difference
		return countDifference;
	}
 
	// Sample method to retrieve the previous count from a database
	private long retrievePreviousCountFromDatabase() {
	    // Assuming alarmCountRepository returns a List
	    List<AlarmCount> alarmCounts = alarmCountRepository.findAll();

	    // Assuming you want to get the count from the first result (if any)
	    if (!alarmCounts.isEmpty()) {
	        // Assuming AlarmCount has a method to retrieve the count (adjust accordingly)
			loggerTypeB.warn("retrievePreviousCountFromDatabase: Retrieved count");
	        return alarmCounts.get(0).getCount();
	    } else {
	        // Return 0 if no previous count is found
			loggerTypeB.info("retrievePreviousCountFromDatabase: No previous count found. Returning 0.");

	        return 0;
	    }
	
}
 
	// Sample method to update the previous count in the database
	public void updatePreviousCountInDatabase(long currentCount) {
		// Update the previous count in the database with the current count
		AlarmCount alarmCount = new AlarmCount();
			if (alarmCountRepository.count()==0) {
				
				loggerTypeB.info("updatePreviousCountInDatabase: New count entry created.");
				alarmCount.setCount(currentCount);
				alarmCountRepository.save(alarmCount);
			} else {
				List<AlarmCount> existingAlarmCount = alarmCountRepository.findAll();
				for (AlarmCount alarmCountData : existingAlarmCount) {
					loggerTypeB.info("updatePreviousCountInDatabase: Count entry updated.");
					alarmCountRepository.updateCountData(currentCount,alarmCountData.getCount());
				}
				
			}
		
 
	}
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	@Override
	public long Zero() {
		loggerTypeB.info("Zero: Method started.");
		long currentCount = alarmDataSyncRepository.countofAll();
		updatePreviousCountInDatabase(currentCount);
		return 0;
	}
	
	@Override
    public void deleteAllData() {
        // Your logic to delete all data in one go
		loggerTypeB.info("deleteAllData: Method started.");
		alarmDataSyncRepository.deleteAll();
    }
	 @Override
	    public void updateAllStatusTo1() {
		 loggerTypeB.info("updateAllStatusTo1: Method started.");
	        List<AlarmModel> recordsToUpdate = alarmDataSyncRepository.findAllByStatus(false);

	        for (AlarmModel record : recordsToUpdate) {
	            record.setStatus(true);
	        }
	        alarmDataSyncRepository.saveAll(recordsToUpdate);
	    }
}

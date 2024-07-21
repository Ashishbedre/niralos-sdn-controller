package com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Frontend.Impl;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public ArrayList<AlarmModelFrontendDto> DetailsofAlarmsById(Integer id) {
		// TODO Auto-generated method stub
		List<AlarmModel> ListofAlerts = alarmDataSyncRepository.DetailsofAlarmsById(id);
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : ListofAlerts) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
		}
 
		return alarmModelFrontendDtos;
	}
 
	@Override
	public ArrayList<AlarmModelFrontendDto> listOfCriticalAlarms() {
		List<AlarmModel> ListofAlerts = alarmDataSyncRepository.listOfCriticalAlerts();
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : ListofAlerts) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
			}	
		
		return alarmModelFrontendDtos;
	}
 
	@Override
	public AlarmLevelDto CountLevelsofAlarms() {
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
			System.out.println("Not correct  Try Again ");
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
 
	@Override
	public ArrayList<AlarmModelFrontendDto> alarmsDetailsBydateAndSite(String startdate, String enddate) {
		List<AlarmModel> datetime = alarmDataSyncRepository.alarmsListBydateTimeAndSite(startdate, enddate);
		ArrayList<AlarmModelFrontendDto> alarmModelFrontendDtos = new ArrayList<>();
		for (AlarmModel alarmModel : datetime) {
			alarmModelFrontendDtos = this.frontendAlertDtoFunction(alarmModel,alarmModelFrontendDtos);
		}
		return alarmModelFrontendDtos;
		
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
		alarmModelFrontendDto.setTenentName(alarmModel.getTenentId());
		alarmModelFrontendDto.setSiteName(alarmModel.getSiteId());
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
	        return alarmCounts.get(0).getCount();
	    } else {
	        // Return 0 if no previous count is found
	        return 0;
	    }
	
}
 
	// Sample method to update the previous count in the database
	public void updatePreviousCountInDatabase(String tenantId, String siteId, long currentCount) {
		// Update the previous count in the database with the current count
		AlarmCount alarmCount = alarmCountRepository.findByTenantIdAndSiteId(tenantId, siteId);
		alarmCount = new AlarmCount();
		if (alarmCountRepository.count()==0) {
		
			alarmCount.setTenantId(tenantId);
			alarmCount.setSiteId(siteId);
			alarmCount.setCount(currentCount);
			alarmCountRepository.save(alarmCount);
		} else {
			alarmCountRepository.updateCountData(tenantId, siteId, currentCount);
		}
 
	}
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	@Override
	public long Zero() {
		 SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			//String LocalSdnId = deployedItem.getDeploymentId();

			// Extract tenantId and siteId from the fetched deployed item
			String tenentID = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
		long currentCount = alarmDataSyncRepository.countofAll();
		if(tenentID!=null) {
		updatePreviousCountInDatabase(tenentID, siteId,currentCount);
		}else {
			System.out.println("Tenant and Site is null");
		}
		return 0;
	}
	
	@Override
    public void deleteAllData() {
        // Your logic to delete all data in one go
		alarmDataSyncRepository.deleteAll();
    }
	 @Override
	    public void updateAllStatusTo1() {
	        List<AlarmModel> recordsToUpdate = alarmDataSyncRepository.findAllByStatus(false);

	        for (AlarmModel record : recordsToUpdate) {
	            record.setStatus(true);
	        }

	        alarmDataSyncRepository.saveAll(recordsToUpdate);
	    }
}

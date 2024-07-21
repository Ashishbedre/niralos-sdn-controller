package com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Frontend;

import java.util.ArrayList;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.AlarmLevelDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.AlarmModelFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.SiteDto;
import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmModel;


//Interface
public interface AlertFrontEndService {

	public List<AlarmModel> listOfAlarms();
	public List<AlarmModel> listOfAlarmsForSite();
	public ArrayList<AlarmModelFrontendDto> listOfCriticalAlarms();

	public ArrayList<AlarmModelFrontendDto> alarmsDetailsBydate(String startdate, String enddate);
	
	public ArrayList<AlarmModelFrontendDto> alarmsDetailsBydateAndSite(String startdate, String enddate);
//	
	public ArrayList<AlarmModelFrontendDto> DetailsofAlarmsById(Integer id);
	public AlarmLevelDto CountLevelsofAlarms();

	public void alarmstatusUpdate(String id, Boolean status);
	public ArrayList<AlarmModelFrontendDto> alertFilter(String level, Integer status);
	public ArrayList<AlarmModelFrontendDto> alertFilterForSite(String level, Integer status);
	
	public List<SiteDto> getListOfSite();
	public long countCriticalAlarms();
	public long Zero();
	void deleteAllData();
	 void updateAllStatusTo1();
}
//public LatestandcountAlarmDto listOfLatestAlarms();

//public DtoofLevels filterLevelsofAlarms();
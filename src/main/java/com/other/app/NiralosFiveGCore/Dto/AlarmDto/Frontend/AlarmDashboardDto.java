package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend;

import java.util.ArrayList;
import java.util.List;

import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmModel;


public class AlarmDashboardDto {
	
	List<AlarmModel> ListofAllAlarms;
	ArrayList<AlarmModelFrontendDto> filtersofAlarms;
//	DtoofLevels filterLevelsofAlarms;
//	LatestandcountAlarmDto ListofLatestAlarms;
//	public List<AlarmModel> getListofAllAlarms() {
//		return ListofAllAlarms;
//	}
//	public void setListofAllAlarms(List<AlarmModel> listofAllAlarms) {
//		ListofAllAlarms = listofAllAlarms;
//	}
//	public ArrayList<AlarmModelDto> getFiltersofAlarms() {
//		return filtersofAlarms;
//	}
//	public void setFiltersofAlarms(ArrayList<AlarmModelDto> filtersofAlarms) {
//		this.filtersofAlarms = filtersofAlarms;
//	}
//	public AlarmDashboardDto(List<AlarmModel> listofAllAlarms, ArrayList<AlarmModelDto> filtersofAlarms) {
//		super();
//		ListofAllAlarms = listofAllAlarms;
//		this.filtersofAlarms = filtersofAlarms;
//	}
//	public AlarmDashboardDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	public List<AlarmModel> getListofAllAlarms() {
		return ListofAllAlarms;
	}
	public void setListofAllAlarms(List<AlarmModel> listofAllAlarms) {
		ListofAllAlarms = listofAllAlarms;
	}
	public ArrayList<AlarmModelFrontendDto> getFiltersofAlarms() {
		return filtersofAlarms;
	}
	public void setFiltersofAlarms(ArrayList<AlarmModelFrontendDto> filtersofAlarms) {
		this.filtersofAlarms = filtersofAlarms;
	}
	public AlarmDashboardDto(List<AlarmModel> listofAllAlarms, ArrayList<AlarmModelFrontendDto> filtersofAlarms) {
		super();
		ListofAllAlarms = listofAllAlarms;
		this.filtersofAlarms = filtersofAlarms;
	}
	public AlarmDashboardDto() {
		super();
	}
	
	
	
	
	
	
	

	
	
	

}

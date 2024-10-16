 package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend;

import java.util.ArrayList;

public class WidgetApi {

	ArrayList<AlarmModelFrontendDto> listofCriticalAlarms;
	AlarmLevelDto countLevelsofAlarms;
//	public WidgetApi(ArrayList<AlarmModelDto> listofCriticalAlarms, AlarmLevelDto countLevelsofAlarms) {
//		super();
//		this.listofCriticalAlarms = listofCriticalAlarms;
//		this.countLevelsofAlarms = countLevelsofAlarms;
//	}
//	public WidgetApi() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public ArrayList<AlarmModelDto> getListofCriticalAlarms() {
//		return listofCriticalAlarms;
//	}
//	public void setListofCriticalAlarms(ArrayList<AlarmModelDto> listofCriticalAlarms) {
//		this.listofCriticalAlarms = listofCriticalAlarms;
//	}
//	public AlarmLevelDto getCountLevelsofAlarms() {
//		return countLevelsofAlarms;
//	}
//	public void setCountLevelsofAlarms(AlarmLevelDto countLevelsofAlarms) {
//		this.countLevelsofAlarms = countLevelsofAlarms;
//	}
	public ArrayList<AlarmModelFrontendDto> getListofCriticalAlarms() {
		return listofCriticalAlarms;
	}
	public void setListofCriticalAlarms(ArrayList<AlarmModelFrontendDto> listofCriticalAlarms) {
		this.listofCriticalAlarms = listofCriticalAlarms;
	}
	public AlarmLevelDto getCountLevelsofAlarms() {
		return countLevelsofAlarms;
	}
	public void setCountLevelsofAlarms(AlarmLevelDto countLevelsofAlarms) {
		this.countLevelsofAlarms = countLevelsofAlarms;
	}
	public WidgetApi(ArrayList<AlarmModelFrontendDto> listofCriticalAlarms, AlarmLevelDto countLevelsofAlarms) {
		super();
		this.listofCriticalAlarms = listofCriticalAlarms;
		this.countLevelsofAlarms = countLevelsofAlarms;
	}
	public WidgetApi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend;

import java.util.ArrayList;
import java.util.List;


public class AlarmRootDto {
	
	 private ArrayList<AlarmList> alarmList;
	    public ArrayList<EventList> eventList;
		public ArrayList<AlarmList> getAlarmList() {
			return alarmList;
		}
		public void setAlarmList(ArrayList<AlarmList> alarmList) {
			this.alarmList = alarmList;
		}
		public ArrayList<EventList> getEventList() {
			return eventList;
		}
		public void setEventList(ArrayList<EventList> eventList) {
			this.eventList = eventList;
		}


	

}

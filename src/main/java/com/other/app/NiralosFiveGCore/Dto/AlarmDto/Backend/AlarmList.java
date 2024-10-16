package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend;

import java.util.ArrayList;
import java.util.List;

public class AlarmList {

	 public String alarmId;
	    public String alarmLevel;
	    public int alarmCounter;
	    public ArrayList<AlarmInfoDto> alarmInfo;
		public String getAlarmId() {
			return alarmId;
		}
		public void setAlarmId(String alarmId) {
			this.alarmId = alarmId;
		}
		public String getAlarmLevel() {
			return alarmLevel;
		}
		public void setAlarmLevel(String alarmLevel) {
			this.alarmLevel = alarmLevel;
		}
		public int getAlarmCounter() {
			return alarmCounter;
		}
		public void setAlarmCounter(int alarmCounter) {
			this.alarmCounter = alarmCounter;
		}
		public ArrayList<AlarmInfoDto> getAlarmInfo() {
			return alarmInfo;
		}
		public void setAlarmInfo(ArrayList<AlarmInfoDto> alarmInfo) {
			this.alarmInfo = alarmInfo;
		}
	    
	    
	    
	    
}

package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend;

public class DataDelete {

	private Integer alarmId;
	private Integer  subAlarmId;
	public Integer getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}
	public Integer getSubAlarmId() {
		return subAlarmId;
	}
	public void setSubAlarmId(Integer subAlarmId) {
		this.subAlarmId = subAlarmId;
	}
	public DataDelete(Integer alarmId, Integer subAlarmId) {
		super();
		this.alarmId = alarmId;
		this.subAlarmId = subAlarmId;
	}
	public DataDelete() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
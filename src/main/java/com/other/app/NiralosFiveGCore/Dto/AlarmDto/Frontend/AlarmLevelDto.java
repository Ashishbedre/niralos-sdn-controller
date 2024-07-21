package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend;

public class AlarmLevelDto {
	
	String critical;
	String major;
	String minor;
	String notAlarmed;
	String notReported;
	
	public AlarmLevelDto(String critical, String major, String minor, String notAlarmed, String notReported) {
		super();
		this.critical = critical;
		this.major = major;
		this.minor = minor;
		this.notAlarmed = notAlarmed;
		this.notReported = notReported;
	}
	public AlarmLevelDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCritical() {
		return critical;
	}
	public void setCritical(String critical) {
		this.critical = critical;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getNotAlarmed() {
		return notAlarmed;
	}
	public void setNotAlarmed(String notAlarmed) {
		this.notAlarmed = notAlarmed;
	}
	public String getNotReported() {
		return notReported;
	}
	public void setNotReported(String notReported) {
		this.notReported = notReported;
	}
	
	
	
	
	
	

}

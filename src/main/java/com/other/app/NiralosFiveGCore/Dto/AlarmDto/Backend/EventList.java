package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend;

import java.util.ArrayList;
import java.util.List;

public class EventList {
	public String eventId;
    public int eventCounter;
    public int files_remaining;
    public ArrayList<EventInfoDto> eventInfo;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public int getEventCounter() {
		return eventCounter;
	}
	public void setEventCounter(int eventCounter) {
		this.eventCounter = eventCounter;
	}
	public int getFiles_remaining() {
		return files_remaining;
	}
	public void setFiles_remaining(int files_remaining) {
		this.files_remaining = files_remaining;
	}
	public ArrayList<EventInfoDto> getEventInfo() {
		return eventInfo;
	}
	public void setEventInfo(ArrayList<EventInfoDto> eventInfo) {
		this.eventInfo = eventInfo;
	}
	    
	    
	    

}

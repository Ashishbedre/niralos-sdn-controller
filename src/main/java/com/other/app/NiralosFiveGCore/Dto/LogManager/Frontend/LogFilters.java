package com.other.app.NiralosFiveGCore.Dto.LogManager.Frontend;

import java.util.List;

import com.other.app.NiralosFiveGCore.model.LogManager.LogModelMongo;


public class LogFilters {
	
	List<LogModelMongo> allData;
	List<LogDto> filters;
	public List<LogModelMongo> getAllData() {
		return allData;
	}
	public void setAllData(List<LogModelMongo> allData) {
		this.allData = allData;
	}
	public List<LogDto> getFilters() {
		return filters;
	}
	public void setFilters(List<LogDto> filters) {
		this.filters = filters;
	}
	public LogFilters(List<LogModelMongo> allData, List<LogDto> filters) {
		super();
		this.allData = allData;
		this.filters = filters;
	}
	public LogFilters() {
		super();
	}
	
	
	

}

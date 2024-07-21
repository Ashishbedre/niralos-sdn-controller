package com.other.app.NiralosFiveGCore.BackendServices.LogManagement.Frontend;

import java.io.IOException;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.LogManager.Frontend.LogDto;
import com.other.app.NiralosFiveGCore.model.LogManager.LogModelMongo;

public interface LogFrontendService {
public List<LogDto> fetchLogData(String level, String module);	
	
	public List<LogModelMongo> fetchAllLogs();

	public void getFileofDb() throws IOException;

	public void deleteDb();
	
	public boolean delete(String filename);

	public List<LogDto> filterDateTimeLogs(String startdate, String enddate);
	
	public List<String> getListofFiles() throws Exception;

	public void setLogLevelDynamically(String logLevel,String module);	}

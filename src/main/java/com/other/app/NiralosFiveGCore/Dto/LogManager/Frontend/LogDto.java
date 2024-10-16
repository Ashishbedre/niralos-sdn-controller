package com.other.app.NiralosFiveGCore.Dto.LogManager.Frontend;

public class LogDto {

    private String module;

    private String logLevel;

    private String protocol;
    
    private String logDescription;
  
    private String dateTime;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getLogDescription() {
		return logDescription;
	}

	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public LogDto(String module, String logLevel, String protocol, String logDescription, String dateTime) {
		super();
		this.module = module;
		this.logLevel = logLevel;
		this.protocol = protocol;
		this.logDescription = logDescription;
		this.dateTime = dateTime;
	}

	public LogDto() {
		super();
		
	}

	

	
    
}

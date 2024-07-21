package com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend;

import com.other.app.NiralosFiveGCore.model.InternalDataModel;

//Interface
public interface InternalDataService {

	public InternalDataModel getInternalDetails();
	public void saveInternalDataModel(InternalDataModel internalData);
	public String getNiralControllerIp();
	public String getNiralControllerClientId();
	public String getNiralGlobalControllerPort();
	public String getAmfIp();
	public String getAmfPort();
	public String getSmfIp();
	public String getSmfPort();
	public String getnrfIp();
	public String getnrfPort();
	public String getupfIp();
	public String getupfPort();
	public String getudrIp();
	public String getudrPort();
	public String getnssfIp();
	public String getnssfPort();
	public String getupgIp();
	public String getupgPort();

	
	public String getpcfIp();
	public String getpcfPort();
	
	public String getbsfIp();
	public String getbsfPort();
	
	public String getudmIp();
	public String getudmPort();
	
	public String getscpIp();
	public String getscpPort();
	
	public String getausfIp();
	public String getausfPort();
	
	
	public long getId();
;	
}

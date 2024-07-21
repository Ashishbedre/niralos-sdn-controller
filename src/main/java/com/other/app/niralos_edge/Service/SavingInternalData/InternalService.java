package com.other.app.niralos_edge.Service.SavingInternalData;

import com.other.app.niralos_edge.dto.InternalDataDto;

public interface InternalService {

	public String saveInternalData(InternalDataDto dto);
	
	public String updateInternalData(InternalDataDto dataDto,String edgeClientId);
	
	public String deleteInternalData(String edgeClientId);
	
}

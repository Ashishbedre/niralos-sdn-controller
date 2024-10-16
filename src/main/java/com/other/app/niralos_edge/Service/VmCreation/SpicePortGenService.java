package com.other.app.niralos_edge.Service.VmCreation;

import com.other.app.niralos_edge.dto.SpiceDataDto;

public interface SpicePortGenService {
	
	public int generateUniqueNumber();

	public SpiceDataDto getSpiceData(String edgeClientId,Long vmId);
}

package com.other.app.niralos_edge.Service.HypervisorSubnet;

import com.other.app.niralos_edge.dto.HypervisorNetworkIntFaceCreationDto;

public interface HypervisorSubnetService {

	public String createInterface(String edgeClientId,HypervisorNetworkIntFaceCreationDto dto);
}

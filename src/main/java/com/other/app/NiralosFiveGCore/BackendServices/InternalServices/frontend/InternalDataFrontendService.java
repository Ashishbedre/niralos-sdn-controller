package com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend;

import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;

import reactor.core.publisher.Mono;

public interface InternalDataFrontendService {
	// public List<InternalDataModel> getDataByTenantAndAgentId(String tenantId, String niralControllerClientId);
	 public Mono<SiteInformationDto> fetchDeployedItemInformation() ;
}

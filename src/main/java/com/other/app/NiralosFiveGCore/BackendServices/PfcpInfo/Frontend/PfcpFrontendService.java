package com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Frontend;

import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpInfoFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend.PfcpInfoRootFrontendDto;

public interface PfcpFrontendService {
	public PfcpInfoRootFrontendDto getPfcpInfoRootDtoByTenantAndSite();
	public PfcpInfoFrontendDto getFailReasonList();
}

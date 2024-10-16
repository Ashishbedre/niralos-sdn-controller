package com.other.app.NiralosFiveGCore.BackendServices.GnbHistory.Frontend;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.FailureListFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.GnbListDistinctFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.GnbHistoryDto.Frontend.GnbListFrontendDto;
import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;

public interface GnbHistoryFrontendService {
	//List<GnbRegistrationDeregistrationFailureCount> getGnbInfo(String tenentId, String siteId);

	public List<GnbListDistinctFrontendDto> getUniqueGnbList();

	//public List<GnbListFrontendDto> getGnbList(String gnbId);

	List<FailureListFrontendDto> getGnbfailureList(String gnbId);

	List<GnbRegistrationDeregistrationFailureCount> getGnbInfo();

	List<GnbListFrontendDto> getGnbList(String gnbId, int page, int size);
}

package com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Frontend;

import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeEntityFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.frontend.UeHistorDistinctFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.UestatsPagesCount;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;

public interface UeHistoryFrontendService {
	public List<UeHistorDistinctFrontendDto> getUniqueImsiData(int pageNumber, int pageSize);
	public List<UeHistoryModel> getLatest20UeStatsByImsi(String imsi);
	public UeEntityFrontendDto getUeEntityData();
	public UestatsPagesCount getAllUeStatsPages(int pageSize, int pageSize2);
}

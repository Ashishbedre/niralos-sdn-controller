package com.other.app.NiralosFiveGCore.BackendServices.Throughput.Frontend;

import java.util.ArrayList;

import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.Frontend.ThroughputGraphFrontendDto;

public interface ThroughputFrontendService {
	public ArrayList<ThroughputGraphFrontendDto> getThroughputGraph(String range);

}

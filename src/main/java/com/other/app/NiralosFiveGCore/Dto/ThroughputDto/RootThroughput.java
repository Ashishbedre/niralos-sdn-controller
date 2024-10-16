
package com.other.app.NiralosFiveGCore.Dto.ThroughputDto;

import java.util.ArrayList;
import java.util.List;

public class RootThroughput {

	private List<ThroughputDto> throughput = new ArrayList<ThroughputDto>();

	public List<ThroughputDto> getThroughput() {
		return throughput;
	}

	public void setThroughput(List<ThroughputDto> throughput) {
		this.throughput = throughput;
	}
}

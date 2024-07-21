package com.other.app.NiralosFiveGCore.BackendServices.Throughput.Frontend.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.Throughput.Frontend.ThroughputFrontendService;
import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.Frontend.ThroughputGraphFrontendDto;
import com.other.app.NiralosFiveGCore.Repository.Throughput.ThroughputRepository;
import com.other.app.NiralosFiveGCore.model.ThroughputModel;

@Service
public class ThroughputFrontendServiceImpl implements ThroughputFrontendService {
	@Autowired
	ThroughputRepository throughputRepository;
	@Override
	public ArrayList<ThroughputGraphFrontendDto> getThroughputGraph(String range)
//			throws InterruptedException
	{
//		Thread.sleep(3000);
		if (range.equals("hour")) {
			ArrayList<ThroughputModel> throughputGraphModels = throughputRepository.returnUlandDlData(60);
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputGraphModels) {

				throughputGraphDtos.add(new ThroughputGraphFrontendDto(throughputModel.getLocalDateTime(),
						throughputModel.getUplinkBytes(), throughputModel.getDownlinkBytes()));

			}
			return throughputGraphDtos;

		} else if (range.equals("day")) {

			ArrayList<ThroughputModel> throughputModels = throughputRepository.returnUlandDlData(1440);
			Integer counter = 0;
			Integer avgSumOfUl = 0;
			Integer avgSumOfDl = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputModels) {
				if (counter < 60) {
					if (counter == 30) {
						localDateTime = throughputModel.getLocalDateTime();
					}
					avgSumOfUl = avgSumOfUl + throughputModel.getUplinkBytes();
					avgSumOfDl = avgSumOfDl + throughputModel.getDownlinkBytes();
					counter++;
				} else {
					avgSumOfDl = avgSumOfDl / 60;
					avgSumOfUl = avgSumOfUl / 60;
					throughputGraphDtos.add(new ThroughputGraphFrontendDto(localDateTime, avgSumOfUl, avgSumOfDl));
					counter = 0;

				}
			}
			return throughputGraphDtos;
		} else if (range.equals("week")) {
			ArrayList<ThroughputModel> throughputModels = throughputRepository.returnUlandDlData(10080);
			Integer counter = 0;
			Integer avgSumOfUl = 0;
			Integer avgSumOfDl = 0;
			LocalDateTime localDateTime = LocalDateTime.now();
			ArrayList<ThroughputGraphFrontendDto> throughputGraphDtos = new ArrayList<>();
			for (ThroughputModel throughputModel : throughputModels) {
				if (counter < 1440) {
					if (counter == 720) {
						localDateTime = throughputModel.getLocalDateTime();
					}
					avgSumOfUl = avgSumOfUl + throughputModel.getUplinkBytes();
					avgSumOfDl = avgSumOfDl + throughputModel.getDownlinkBytes();
					counter++;
				} else {
					avgSumOfDl = avgSumOfDl / 1440;
					avgSumOfUl = avgSumOfUl / 1440;
					throughputGraphDtos.add(new ThroughputGraphFrontendDto(localDateTime, avgSumOfUl, avgSumOfDl));
					counter = 0;

				}
			}

			return throughputGraphDtos;

		}
		return null;

	}
}

package com.other.app.NiralosFiveGCore.BackendServices.Throughput.Backend.Impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.Throughput.Backend.ThroughputService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.RootThroughput;
import com.other.app.NiralosFiveGCore.Dto.ThroughputDto.ThroughputDto;
import com.other.app.NiralosFiveGCore.Repository.Throughput.ThroughputRepository;
import com.other.app.NiralosFiveGCore.model.ThroughputModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@EnableScheduling
@Service
@Configuration
public class ThroughputCollectorImpl implements ThroughputService {

	@Autowired
	UriProtocol uriProtocol;

	@Autowired
	InternalDataService internalDataService;

	@Autowired
	ThroughputRepository throughputRepository;

	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;

	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:ThroughputCollectorImpl");
	//final Logger logger = LoggerFactory.getLogger(ThroughputCollectorImpl.class);

	@Override
	public void ThroughputData() {
//		try {
//		  internalDataFrontendService.fetchDeployedItemInformation().block();
//		}catch (Exception e) {
//			logger.error("Check Updater Agent Port and Ip ");
//		}
	        String smfIp = internalDataService.getSmfIp();
	        String smfPort = internalDataService.getSmfPort();
	        loggerTypeA.info("ThroughputData: Web Scrapper has started");
		
	            int maxCount;
	            try {
	                maxCount = Integer.parseInt(smfIp);
	            } catch (NumberFormatException e) {
	                loggerTypeA.error("ThroughputData: Invalid SMF IP format {}", smfIp);
	                return;
	            }
//	            WebClient smfClient = WebClient.builder().baseUrl("http://" + "192.168.96.131" + ":" + "9090")
//					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	            WebClient smfClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	            
	            for (int count = 1; count <= maxCount; count++) {
	                String networkFunctionName = nfServiceImpl.smfName + count;
	                String baseUrl = "http://" + networkFunctionName + ":" + smfPort;
	                fetchAndProcessThroughputData(smfClient, baseUrl, networkFunctionName);
	            }
	        }
	private void fetchAndProcessThroughputData(WebClient smfClient, String baseUrl, String networkFunctionName) {
	    try {
	        RootThroughput rootThroughput = smfClient.get()
	                .uri(baseUrl + uriProtocol.getFivegcoreThroughputCollector())
	                .accept(MediaType.APPLICATION_JSON)
	                .retrieve()
	                .bodyToMono(RootThroughput.class)
	                .timeout(Duration.ofSeconds(1))
	                .block();

	        if (rootThroughput != null) {
	            for (ThroughputDto throughputDto : rootThroughput.getThroughput()) {
	                LocalDateTime timestamp = LocalDateTime.of(throughputDto.getYear(), throughputDto.getMonth(),
	                        throughputDto.getDay(), throughputDto.getHour(), throughputDto.getMinute());

	                int existingDataCount = throughputRepository.checkThroughtputData(timestamp, throughputDto.getUlData(), throughputDto.getDlData());
	                ThroughputModel throughputModel = new ThroughputModel();

	                if (existingDataCount == 0) {
	                    throughputModel.setLocalDateTime(timestamp);
	                    throughputModel.setUplinkBytes(throughputDto.getUlData());
	                    throughputModel.setDownlinkBytes(throughputDto.getDlData());
	                    throughputModel.setNfName(networkFunctionName);
	                    throughputModel.setNfType(nfServiceImpl.nfTypeofSmf);

	                    throughputRepository.save(throughputModel);
	                } else {
	                    throughputRepository.updateThroughtputData(timestamp, throughputDto.getUlData(), throughputDto.getDlData(),
	                            networkFunctionName, nfServiceImpl.nfTypeofSmf);
						loggerTypeA.info("fetchAndProcessThroughputData: Updated existing throughput data");
	                }
	            }
	        }
	    } catch (Exception e) {
			loggerTypeA.error("fetchAndProcessThroughputData: Failed to fetch data");
	    }
	}
}
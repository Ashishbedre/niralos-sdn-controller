package com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.Impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.PfcpInfoService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.PfcpInfoRootDto;
import com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend.PfcpInfoRepository;
import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpInfo;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class PfcpInfoServiceImpl implements PfcpInfoService {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	PfcpInfoRepository pfcpInfoRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl commonServiceImpl = new CommonServiceImpl();
	private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:PfcpInfoServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(PfcpInfoServiceImpl.class);

//	@Scheduled
	@Override
	public synchronized void fetchDataAndSaveToDatabase() {
//		  internalDataFrontendService.fetchDeployedItemInformation().block();
		loggerTypeA.info("Starting data fetch and save process.");

			String smfIp = internalDataService.getSmfIp();
			String smfPort = internalDataService.getSmfPort();
			
			WebClient webclient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
				for (int count = 1; count <= Integer.parseInt(smfIp); count++) {
					loggerTypeA.info("fetchDataAndSaveToDatabase: Processing network function");

					String networkFunctionName = commonServiceImpl.smfName + count;
					try {
						
								String baseUrl ="http://" + networkFunctionName + ":" + smfPort;
						        loggerTypeA.info("Constructed base URL: {}", baseUrl);
						PfcpInfoRootDto responseMono = webclient.get().uri(baseUrl+uriProtocol.getFivegcorePfcpUri()).retrieve()
								.bodyToMono(PfcpInfoRootDto.class).timeout(Duration.ofSeconds(1)).block();

						if (responseMono != null) {
							PfcpInfo existingInfo = pfcpInfoRepository.findByNfNameAndNfType(networkFunctionName, commonServiceImpl.nfTypeofSmf);
							if (existingInfo != null) {
								// If an existing entity is found, update its fields
								existingInfo.setTotal_cp_assoc_req(responseMono.getTotal_cp_assoc_req());
								existingInfo.setTotal_cp_assoc_resp(responseMono.getTotal_cp_assoc_resp());
								existingInfo.setTotalDelReq(responseMono.getTotalDelReq());
								existingInfo.setTotalDelRsp(responseMono.getTotalDelRsp());
								existingInfo.setTotalModReq(responseMono.getTotalModReq());
								existingInfo.setTotalModRsp(responseMono.getTotalModRsp());
								existingInfo.setTotalEstRsp(responseMono.getTotalEstRsp());
								existingInfo.setTotalEstReq(responseMono.getTotalEstReq());
								existingInfo.setFailDelRsp(responseMono.getFailDelRsp());
								existingInfo.setFailModRsp(responseMono.getFailModRsp());
								existingInfo.setFailEstRsp(responseMono.getFailEstRsp());
								existingInfo.setSucessDelReq(responseMono.getSucessDelReq());
								existingInfo.setSucessDelRsp(responseMono.getSucessDelRsp());
								existingInfo.setSucessModReq(responseMono.getSucessModReq());
								existingInfo.setSucessModRsp(responseMono.getSucessModRsp());
								existingInfo.setSucessEstReq(responseMono.getSucessEstReq());
								existingInfo.setSucessEstRsp(responseMono.getSucessEstRsp());
								existingInfo.setNfName(networkFunctionName);
								existingInfo.setNfType(commonServiceImpl.nfTypeofSmf);
								pfcpInfoRepository.save(existingInfo);
							} else {
								// If no existing entity is found, create a new one
								PfcpInfo entity = new PfcpInfo();

								// Set entity fields based on responseMono
								entity.setTotal_cp_assoc_req(responseMono.getTotal_cp_assoc_req());
								entity.setTotal_cp_assoc_resp(responseMono.getTotal_cp_assoc_resp());
								entity.setTotalDelReq(responseMono.getTotalDelReq());
								entity.setTotalDelRsp(responseMono.getTotalDelRsp());
								entity.setTotalModReq(responseMono.getTotalModReq());
								entity.setTotalModRsp(responseMono.getTotalModRsp());
								entity.setTotalEstRsp(responseMono.getTotalEstRsp());
								entity.setTotalEstReq(responseMono.getTotalEstReq());
								entity.setFailDelRsp(responseMono.getFailDelRsp());
								entity.setFailModRsp(responseMono.getFailModRsp());
								entity.setFailEstRsp(responseMono.getFailEstRsp());
								entity.setSucessDelReq(responseMono.getSucessDelReq());
								entity.setSucessDelRsp(responseMono.getSucessDelRsp());
								entity.setSucessModReq(responseMono.getSucessModReq());
								entity.setSucessModRsp(responseMono.getSucessModRsp());
								entity.setSucessEstReq(responseMono.getSucessEstReq());
								entity.setSucessEstRsp(responseMono.getSucessEstRsp());
								entity.setNfName(networkFunctionName);
								entity.setNfType(commonServiceImpl.nfTypeofSmf);

								pfcpInfoRepository.save(entity);
								loggerTypeA.info("Successfully created new PFCP info for {}", networkFunctionName);
							}
						}

					} catch (Exception e) {
						loggerTypeA.error("Unable to fetch pfcpInformation" + e);
					}
				}
			}
	}

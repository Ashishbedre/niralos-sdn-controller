package com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.UePduCauseInfoService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.Cause;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeCauseInfo;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeInfo;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeInfoDTO;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduCause;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo.PduEstInfo;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UePduCauseInfoRepository;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UePduCauseInfoModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
public class UePduCauseInfoServiceImpl implements UePduCauseInfoService {
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;

	@Autowired
	UePduCauseInfoRepository causeInfoRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(UePduCauseInfoServiceImpl.class);

//	@Async("taskExecutor")
	@Override
	public void fetchDataAndSaveToDatabase() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String LocalSdnId = deployedItem.getDeploymentId();
			// Fetch required data
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			
			WebClient amfClient = WebClient.builder().build();

			if (siteId != null && tenantId != null) {
				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					try {
						
							String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + amfPort;
//            WebClient amfClient = WebClient.builder().baseUrl("http://" + amfIp + ":" + amfPort).build();

						// Fetch data from remote service
						UeInfoDTO responseDto = amfClient.get().uri(baseUrl+uriProtocol.getFivegcoreUePduCauseInfoUri())
								.retrieve().bodyToMono(UeInfoDTO.class).block();

						// Process and save data
						if (responseDto != null && responseDto.getUeInfo() != null
								&& !responseDto.getUeInfo().isEmpty()) {
							List<UePduCauseInfoModel> causeInfoList = processUeInfoList(responseDto.getUeInfo(),
									LocalSdnId, tenantId, siteId, networkFunctionName);
							causeInfoRepository.saveAll(causeInfoList);
						}
					} catch (Exception e) {
						logger.error("Unable to fetch UePduCause Info " + e);
					}
				}
			}
	}

	private List<UePduCauseInfoModel> processUeInfoList(List<UeInfo> ueInfoList, String agentId, String tenantId,
			String siteId, String nfName) {
		List<UePduCauseInfoModel> causeInfoList = new ArrayList<>();

		for (UeInfo ueInfo : ueInfoList) {
			List<Cause> causeList = ueInfo.getCauseList();
			if (causeList != null) {
				// Inside the for loop where you process the CauseList
				for (Cause response : causeList) {
					UeCauseInfo ueCauseInfo = new UeCauseInfo();
					ueCauseInfo.setUecauseName(response.getCauseName());
					ueCauseInfo.setRepeated(response.getRepeated());

					List<UePduCauseInfoModel> existingCauseInfos = causeInfoRepository
							.findByuecauselist(ueCauseInfo.getUecauseName());

					if (existingCauseInfos != null && !existingCauseInfos.isEmpty()) {
						UePduCauseInfoModel existingCauseInfo = existingCauseInfos.get(0);
						existingCauseInfo.setRepeated(ueCauseInfo.getRepeated());
						existingCauseInfo.setTenentId(tenantId);
						existingCauseInfo.setAgentId(agentId);
						existingCauseInfo.setSiteId(siteId);

						existingCauseInfo.setNfName(nfName);
						existingCauseInfo.setNfType(nfServiceImpl.nfTypeofAmf);
						// Save the updated CauseInfo
						causeInfoRepository.save(existingCauseInfo);
					} else {
						UePduCauseInfoModel causeInfo = new UePduCauseInfoModel();
						causeInfo.setUecauselist(ueCauseInfo.getUecauseName());
						causeInfo.setRepeated(ueCauseInfo.getRepeated());
						causeInfo.setTenentId(tenantId);
						causeInfo.setAgentId(agentId);
						causeInfo.setSiteId(siteId);

						causeInfo.setNfName(nfName);
						causeInfo.setNfType(nfServiceImpl.nfTypeofAmf);
						// Save the new CauseInfo
						causeInfoRepository.save(causeInfo);
					}
				}
			}
		}

		return causeInfoList;
	}

//    @Scheduled(fixedRate = 5000)
//	@Async("taskExecutor")
	@Override
	public void fetchDataAndSaveToDatabase1() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String LocalSdnId = deployedItem.getDeploymentId();
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();

			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			WebClient webclient = WebClient.builder().build();
			if (siteId != null && tenantId != null) {
				for (int count = 0; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					try {

					
							String baseurl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + amfPort;
//            WebClient amfClient = WebClient.builder().baseUrl("http://" + amfIp + ":" + amfPort).build();

						UeInfoDTO responseDto = webclient.get().uri(baseurl+uriProtocol.getFivegcoreUePduCauseInfoUri())
								.retrieve().bodyToMono(UeInfoDTO.class).block();

						if (responseDto != null && responseDto.getPduEstInfo() != null
								&& !responseDto.getPduEstInfo().isEmpty()) {
							List<PduEstInfo> pduInfoList = responseDto.getPduEstInfo();
							List<UePduCauseInfoModel> causeInfoList = processPduInfoList(pduInfoList, LocalSdnId,
									tenantId, siteId, networkFunctionName);
							causeInfoRepository.saveAll(causeInfoList);
						}
					} catch (Exception e) {
						logger.error("Unable to fetch UePduCause Info " + e);
					}
				}
			}
	}

	private List<UePduCauseInfoModel> processPduInfoList(List<PduEstInfo> pduInfoList, String agentId, String tenantId,
			String siteId, String nfName) {
		List<UePduCauseInfoModel> causeInfoList = new ArrayList<>();

		for (PduEstInfo pduInfo : pduInfoList) {
			List<Cause> causeList = pduInfo.getPduCauseList();
			if (causeList != null) {
				for (Cause response : causeList) {
					PduCause pduCauseInfo = new PduCause();
					pduCauseInfo.setPducauseName(response.getCauseName());
					pduCauseInfo.setRepeated(response.getRepeated());

					List<UePduCauseInfoModel> existingCauseInfos = causeInfoRepository
							.findBypducauselist(pduCauseInfo.getPducauseName());

					if (existingCauseInfos != null && !existingCauseInfos.isEmpty()) {
						UePduCauseInfoModel existingCauseInfo = existingCauseInfos.get(0);
						existingCauseInfo.setRepeated(pduCauseInfo.getRepeated());
						existingCauseInfo.setTenentId(tenantId);
						existingCauseInfo.setAgentId(agentId);
						existingCauseInfo.setSiteId(siteId);

						existingCauseInfo.setNfName(nfName);
						existingCauseInfo.setNfType(nfServiceImpl.nfTypeofAmf);

						causeInfoList.add(existingCauseInfo);
					} else {
						UePduCauseInfoModel causeInfo = new UePduCauseInfoModel();
						causeInfo.setPducauselist(pduCauseInfo.getPducauseName());
						causeInfo.setRepeated(pduCauseInfo.getRepeated());
						causeInfo.setTenentId(tenantId);
						causeInfo.setAgentId(agentId);
						causeInfo.setSiteId(siteId);

						causeInfo.setNfName(nfName);
						causeInfo.setNfType(nfServiceImpl.nfTypeofAmf);
						causeInfoList.add(causeInfo);
					}
				}
			}
		}

		return causeInfoList;

	}

}

package com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.Impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.UeHistory.Backend.UeHistoryService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.FileRecordApi;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeStatEntityDTO1;
import com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.UeStatEntityDto;
import com.other.app.NiralosFiveGCore.Dto.UeStacDto.FileListNameDto;
import com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend.UeHistoryRepository;
import com.other.app.NiralosFiveGCore.model.DeletingFiles;
import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@Configuration
public class UeHistoryServiceImpl implements UeHistoryService {
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(UeRegistraionDeregistrationFailureCountServiceImpl.class);
	@Autowired
	UeHistoryRepository ueStatEntityRepository;

	@Async("taskExecutor")
	@Override
	public void fetchDataAndSaveToDatabase() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String LocalSdnId = deployedItem.getDeploymentId();
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();

			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();
			CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
			final int size = 1024 * 1024 * 1024;
			final ExchangeStrategies strategies = ExchangeStrategies.builder()
					.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size)).build();
			WebClient webclient = WebClient.builder().exchangeStrategies(strategies)
					.build();
			if (siteId != null && tenantId != null) {
				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					try {
					
							String baseUrl = "http://" + networkFunctionName + ":" + amfPort;
						FileRecordApi fileList = webclient.get()
								.uri(baseUrl+uriProtocol.getFivegcoreUeHistoryandsessionfileListUri())
								.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(FileRecordApi.class)
								.timeout(Duration.ofSeconds(1)).block();

						if (fileList != null && fileList.getFileList() != null) {
							for (FileListNameDto iteratorOfFileNames : fileList.getFileList()) {
								UeStatEntityDTO1 ueStacDataList = webclient.get()
										.uri(baseUrl+uriProtocol.getFivegcoreUeHistoryUri() + iteratorOfFileNames.getFileName())
										.retrieve().bodyToMono(UeStatEntityDTO1.class).timeout(Duration.ofSeconds(1))
										.block();

								if (ueStacDataList != null && ueStacDataList.getFile() != null) {
									List<UeStatEntityDto> arrayList = ueStacDataList.getFile();
									if (arrayList == null) {
										DeletingFiles deleteFile = webclient.get()
												.uri(baseUrl+uriProtocol.getDeletedtatfilejson()
														+ iteratorOfFileNames.getFileName())
												.retrieve().bodyToMono(DeletingFiles.class).block();

										logger.info("Ue History LOG File " + deleteFile.getStatus());
									} else {
										for (UeStatEntityDto ueStatDataDto : arrayList) {
											if (ueStatEntityRepository
													.countByImsiAndGnbIdAndUeStatusAndPduStatusAndTime(
															ueStatDataDto.getImsi(), ueStatDataDto.getGnbId(),
															ueStatDataDto.getUeStatus(), ueStatDataDto.getPduStatus(),
															ueStatDataDto.getTime()) == 0) {

												UeHistoryModel historyModel = new UeHistoryModel();
												historyModel.setNfDomain(ueStatDataDto.getNfDomain());
												historyModel.setImsi(ueStatDataDto.getImsi());
												historyModel.setGnbId(ueStatDataDto.getGnbId());
												historyModel.setDnn(ueStatDataDto.getDnn());
												historyModel.setUeStatus(ueStatDataDto.getUeStatus());
												historyModel.setPduStatus(ueStatDataDto.getPduStatus());
												historyModel.setTime(ueStatDataDto.getTime());
												historyModel.setSst(ueStatDataDto.getSst());
												historyModel.setSd(ueStatDataDto.getSd());
												historyModel.setPsi(ueStatDataDto.getPsi());
												historyModel.setAgentId(LocalSdnId);
												historyModel.setTenentId(tenantId);
												historyModel.setSiteId(siteId);

												historyModel.setNfName(networkFunctionName);
												historyModel.setNfType(nfServiceImpl.nfTypeofAmf);

												ueStatEntityRepository.save(historyModel);
												logger.info("Ue history data is storing in Agent");

											}
										}
										DeletingFiles deleteFile = webclient.get()
												.uri(baseUrl+uriProtocol.getDeletedtatfilejson()
														+ iteratorOfFileNames.getFileName())
												.retrieve().bodyToMono(DeletingFiles.class).block();

										logger.info("Ue History LOG File " + deleteFile.getStatus());
									}
								}
							}
						}
					} catch (Exception e) {
						logger.error("Unable to fetch Ue History from 5GCore Error " + e);

					}
				}
			}
	}
}

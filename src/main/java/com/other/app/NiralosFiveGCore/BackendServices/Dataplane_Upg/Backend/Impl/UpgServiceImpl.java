
package com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.Impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.Dataplane_Upg.Backend.UpgService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.upgDto.RootModel;
import com.other.app.NiralosFiveGCore.Dto.upgDto.UpgStatModelDto;
import com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend.UpgFrontendRepository;
import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
public class UpgServiceImpl implements UpgService {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UpgFrontendRepository upgRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(UpgServiceImpl.class);

	@Override
	public synchronized void saveUpgData() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String LocalSdnId = deployedItem.getDeploymentId();
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();
			String upfIp = internalDataService.getupfIp();
			String upfPort = internalDataService.getupfPort();
			WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
			if (siteId != null && tenantId != null) {

				for (int count = 1; count <= Integer.parseInt(upfIp); count++) {
					String networkFunctionName = nfServiceImpl.upfName + count;
					try {
						 String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + upfPort;
						RootModel stats = webClient.get().uri(baseUrl+uriProtocol.getFivegcoreUpgservice())
								.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(RootModel.class)
								.timeout(Duration.ofSeconds(1)).block();
						List<UpgStatModelDto> data = stats.getStats();
						for (UpgStatModelDto statsModel : data) {
							if (upgRepository.countInterfaceName(statsModel.getInterfaceName(), tenantId, siteId,
									LocalSdnId, networkFunctionName, nfServiceImpl.nfTypeofUpf) == 0) {
								UpgModel model = new UpgModel();
								model.setDrops(statsModel.getDrops());
								model.setIndexData(statsModel.getIndex());
								model.setInterfaceName(statsModel.getInterfaceName());
								model.setIp4(statsModel.getIp4());
								model.setIp6(statsModel.getIp6());
								model.setPunt(statsModel.getPunt());

								model.setRx_bytes(statsModel.getRx_bytes());

								model.setRx_packets(statsModel.getRx_packets());
								model.setThreadNumber(statsModel.getThreadNumber());

								model.setTx_bytes(statsModel.getTx_bytes());

								model.setTx_packets(statsModel.getTx_packets());

								model.setPrevious_tx_bytes(0);
								model.setPrevious_rx_bytes(0);

								model.setPrevious_rx_packets(0);
								model.setPrevious_tx_packets(0);
								model.setAgentId(LocalSdnId);
								model.setTenentId(tenantId);
								model.setSiteId(siteId);

								model.setNfName(networkFunctionName);
								model.setNfType(nfServiceImpl.nfTypeofUpf);
								upgRepository.save(model);
							} else {
								upgRepository.updateData(statsModel.getDrops(), statsModel.getIndex(),
										statsModel.getIp4(), statsModel.getIp6(), statsModel.getPunt(),
										statsModel.getRx_bytes(), statsModel.getRx_packets(),
										statsModel.getThreadNumber(), statsModel.getTx_bytes(),
										statsModel.getTx_packets(),
										upgRepository.getRxPacketsImpl(statsModel.getInterfaceName(), tenantId, siteId),
										upgRepository.getTxPacketsImpl(statsModel.getInterfaceName(), tenantId, siteId),
										statsModel.getInterfaceName(), LocalSdnId, tenantId, siteId, networkFunctionName,
										nfServiceImpl.nfTypeofUpf,
										upgRepository.getrxBytesImpl(statsModel.getInterfaceName(), tenantId, siteId),
										upgRepository.gettxBytesImpl(statsModel.getInterfaceName(), tenantId, siteId)

								);
							}
						}

					} catch (Exception e) {
						logger.error("Unable to fetch UPGStat data from Dataplane " + e);
					}
				}
			} else {
				logger.error("Check Tenant configuration for your Refrence ");
			}

	}
}

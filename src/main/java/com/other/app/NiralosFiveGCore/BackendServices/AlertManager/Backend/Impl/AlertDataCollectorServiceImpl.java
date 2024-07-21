package com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Backend.Impl;

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

import com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Backend.AlertDataCollectorService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.CommonServices;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend.AlarmInfoDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend.AlarmList;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend.AlarmRootDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend.DataDelete;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.AlertManager.AlarmManagerModelRepository;
import com.other.app.NiralosFiveGCore.model.AlertManager.AlarmModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
@Configuration
public class AlertDataCollectorServiceImpl implements AlertDataCollectorService {
	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	AlarmManagerModelRepository alarmModelRepository;
	@Autowired
	InternalDataService internalDataService;
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	CommonServices commonServices;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;

	CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	final Logger logger = LoggerFactory.getLogger(AlertDataCollectorServiceImpl.class);

	// @Scheduled(fixedRate = 6000)
	@Override
	public void AlertDataParse() {
		  SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
			String LocalSdnId = deployedItem.getDeploymentId();
			String tenantId = deployedItem.getTenantName();
			String siteId = deployedItem.getSiteName();

			String nrfIp = internalDataService.getnrfIp();
			String nrfPort = internalDataService.getnrfPort();

			String amfIp = internalDataService.getAmfIp();
			String amfPort = internalDataService.getAmfPort();

			String smfIp = internalDataService.getSmfIp();
			String smfPort = internalDataService.getSmfPort();
			
			
			WebClient webclientCommon = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
			logger.info("Alert Management Web Scrapper has started");
			if (siteId != null && tenantId != null) {
				for (int count = 1; count <= Integer.parseInt(nrfIp); count++) {
					String networkFunctionName = nfServiceImpl.nrfName + count;
					try {
						// Nrf
						 String baseUrlofnrf ="http://" + networkFunctionName + ":" + nrfPort;
						AlarmRootDto alarmDto = webclientCommon.get().uri(baseUrlofnrf+uriProtocol.getFivegcoreAlertData())
								.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AlarmRootDto.class)
								.timeout(Duration.ofSeconds(1)).block();

						//		***----------NRF------------***
						this.model(alarmDto,LocalSdnId,tenantId,siteId,networkFunctionName,webclientCommon,baseUrlofnrf);

					} catch (Exception e) {
						logger.error("Not able to fetch NRF Alarm from 5GCore");
					}
				}
			}
			
		
//		*****----------AMF------------*****
//		********....................********
			if (siteId != null && tenantId != null) {
				for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
					String networkFunctionName = nfServiceImpl.amfName + count;
					try {
						 String AmfbaseUrl =uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + amfPort;
						AlarmRootDto alarmDto1 = webclientCommon.get().uri(AmfbaseUrl+uriProtocol.getFivegcoreAlertData())
								.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AlarmRootDto.class)
								.timeout(Duration.ofSeconds(1)).block();
						this.model(alarmDto1,LocalSdnId,tenantId,siteId,networkFunctionName,webclientCommon,AmfbaseUrl);

					} catch (Exception e) {
						logger.info("Not able to fetch AMF Alarm From 5Gcore");
					}
				}
			}
//		*****----------SMF------------*****
//		********....................*******
			if (siteId != null && tenantId != null) {
				for (int count = 1; count <= Integer.parseInt(smfIp); count++) {
					String networkFunctionName2 = nfServiceImpl.smfName + count;
					try {
					String SmfbaseUrl  = uriProtocol.getFivegcoreProtocol() + networkFunctionName2 + ":" + smfPort;

						AlarmRootDto alarmDto2 = webclientCommon.get().uri(SmfbaseUrl+uriProtocol.getFivegcoreAlertData())
								.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AlarmRootDto.class)
								.timeout(Duration.ofSeconds(1)).block();
						this.model(alarmDto2,LocalSdnId,tenantId,siteId,networkFunctionName2,webclientCommon,SmfbaseUrl);
					} catch (Exception e) {
						logger.info("Not able to fetch SMF Alarm from 5GCore");
					}
				}
			}
	}
	
	public void model(AlarmRootDto alarmRootDto ,String LocalSdnId,String tenantId,String siteId,String networkFunction,WebClient webclientCommon, String baseUrl ){
		
		for (AlarmList alarmListDto : alarmRootDto.getAlarmList()) {
			for (AlarmInfoDto subAlarmDto : alarmListDto.getAlarmInfo()) {
				AlarmModel alarmModel2 = new AlarmModel();
				alarmModel2.setAlarmId(alarmListDto.getAlarmId());
				alarmModel2.setAlarmLevel(alarmListDto.getAlarmLevel());
				alarmModel2.setSubAlarmId(subAlarmDto.getSubAlarmId());
				alarmModel2.setSourceDescription(subAlarmDto.getSrcDescription());
				alarmModel2.setSourceType(subAlarmDto.getSrcType());
				alarmModel2.setSourceIp(subAlarmDto.getSrcIP());
				alarmModel2.setDestinationDescription(subAlarmDto.getDestDescription());
				alarmModel2.setDestinationType(subAlarmDto.getDestType());
				alarmModel2.setDestinationIp(subAlarmDto.getDestIP());

				LocalDateTime localDateTime = this.dateTimeFormateConversion(subAlarmDto.getDateTime());
				alarmModel2.setDateTime(localDateTime);
				alarmModel2.setReason(subAlarmDto.getReason());
				alarmModel2.setSolution(subAlarmDto.getSolution());
				alarmModel2.setStatus(false);

				alarmModel2.setAgentId(LocalSdnId);
				alarmModel2.setTenentId(tenantId);
				alarmModel2.setSiteId(siteId);

				alarmModel2.setNfName(networkFunction);
				alarmModel2.setNfType(nfServiceImpl.nfTypeofSmf);

				alarmModelRepository.save(alarmModel2);

				DataDelete dataDelete = new DataDelete();
				dataDelete.setAlarmId(Integer.parseInt(alarmListDto.getAlarmId()));
				dataDelete.setSubAlarmId(Integer.parseInt(subAlarmDto.getSubAlarmId()));

				String response = webclientCommon.post().uri(baseUrl+uriProtocol.getFivegcoreAlarmDelete())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.bodyValue(dataDelete).retrieve().bodyToMono(String.class)
						.timeout(Duration.ofSeconds(1)).block();

				logger.info( " Alert Delete from Core for " + networkFunction  + response);

			}
		}
		
	}

	public LocalDateTime dateTimeFormateConversion(String DateTimeFromcore) {
		LocalDateTime dateTime = null;
		String b = DateTimeFromcore;
		String[] date1 = b.split("-");
		for (int i = 0; i < date1.length; i++) {
			if (i == 0) {
			} else if (i == 3) {
				String line = date1[i];
				String[] second = line.split(":");
				dateTime = LocalDateTime.of(Integer.parseInt(date1[2]), Integer.parseInt(date1[1]),
						Integer.parseInt(date1[0]), Integer.parseInt(second[0]), Integer.parseInt(second[1]),
						Integer.parseInt(second[2]));
			}
		}
		return dateTime;
	}
}

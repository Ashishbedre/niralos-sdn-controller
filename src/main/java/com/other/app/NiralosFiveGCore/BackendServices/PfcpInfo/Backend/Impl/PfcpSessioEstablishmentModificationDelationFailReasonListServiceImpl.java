package com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.Impl;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.PfcpInfo.Backend.PfcpSessioEstablishmentModificationDelationFailReasonListService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.PfcpInfoRootDto;
import com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend.PfcpSessioEstablishmentModificationDelationFailReasonListRepository;
import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpSessioEstablishmentModificationDelationFailReasonList;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

@Service
@EnableScheduling
public class PfcpSessioEstablishmentModificationDelationFailReasonListServiceImpl
        implements PfcpSessioEstablishmentModificationDelationFailReasonListService {
	@Autowired
	WebClient.Builder customWebClientBuilder;
	@Autowired
	UriProtocol uriProtocol;
    @Autowired
    InternalDataService internalDataService;
    @Autowired
	InternalDataFrontendService internalDataFrontendService;
    @Autowired
    PfcpSessioEstablishmentModificationDelationFailReasonListRepository pfcpFailReasonListRepository;
	CommonServiceImpl commonServiceImpl = new CommonServiceImpl();
    private static final Logger loggerTypeA = LoggerFactory.getLogger("5G-BACKEND:PfcpSessioEstablishmentModificationDelationFailReasonListServiceImpl");
	//final Logger logger = LoggerFactory.getLogger(PfcpSessioEstablishmentModificationDelationFailReasonListServiceImpl.class);

//  @Scheduled(fixedRate = 2000)
    @Override
    public synchronized void fetchDataAndSaveToDatabase() {
        loggerTypeA.info("Starting data fetch and save process.");
//    	  internalDataFrontendService.fetchDeployedItemInformation().block();
        String smfIp = internalDataService.getSmfIp();
        String smfPort = internalDataService.getSmfPort();
        WebClient webclient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build(); 
		for(int count1 = 1;count1<=Integer.parseInt(smfIp);count1++) {
            loggerTypeA.info("fetchDataAndSaveToDatabase: Processing network function");
		String networkFunctionName=commonServiceImpl.smfName+count1;
        try {
    		String baseUrl ="http://" + networkFunctionName + ":" + smfPort;

            PfcpInfoRootDto responseMono = webclient.get().uri(baseUrl+uriProtocol.getFivegcorePfcpUri()).retrieve()
                    .bodyToMono(PfcpInfoRootDto.class)
                    .timeout(Duration.ofSeconds(1))
                    .block();

            if (responseMono != null && responseMono.getSessionEstablishmentFailReasonList() != null) {
                List<String> reasons = responseMono.getSessionEstablishmentFailReasonList();
                loggerTypeA.info("Session Establishment Fail Reason List for {}: {}", networkFunctionName, reasons);


                for (String response : reasons) {
                    int count = 0;
                    
                    // Count occurrences of 'response' in the list
                    loggerTypeA.info("");
                    for (String reason : reasons) {
                        if (response.equals(reason)) {
                            count++;
                        }
                    }

                    // Check if the entry already exists in the database
                    List<PfcpSessioEstablishmentModificationDelationFailReasonList> existingPfcpFailList = pfcpFailReasonListRepository
                            .findBySessionEstablishmentFailReasonListAndNfNameAndNfType( response,networkFunctionName,commonServiceImpl.nfTypeofSmf);

                    if (existingPfcpFailList.isEmpty()) {
                        PfcpSessioEstablishmentModificationDelationFailReasonList pfcpFailList = new PfcpSessioEstablishmentModificationDelationFailReasonList();
                        pfcpFailList.setSessionEstablishmentFailReasonList(response);
                        pfcpFailList.setNfName(networkFunctionName);
                        pfcpFailList.setNfType(commonServiceImpl.nfTypeofSmf);
                        pfcpFailList.setRepeatData(count);

                        pfcpFailReasonListRepository.save(pfcpFailList);
                    } else {
                        for (PfcpSessioEstablishmentModificationDelationFailReasonList existingPfcpFailListEntry : existingPfcpFailList) {
                           // System.out.println("response" + existingPfcpFailListEntry.getSessionEstablishmentFailReasonList());

                            if (existingPfcpFailListEntry.getSessionEstablishmentFailReasonList().equals(response)) {

                                existingPfcpFailListEntry.setRepeatData(count);
                                pfcpFailReasonListRepository.save(existingPfcpFailListEntry);
                                break;
                            }
                        }
                    }
                }
            }
            if (responseMono != null && responseMono.getSessionModificationFailReasonList() != null) {
                List<String> reasons1 = responseMono.getSessionModificationFailReasonList();
               // System.out.println("test"+reasons1);
                loggerTypeA.info("Session Modification Fail Reason List for {}: {}", networkFunctionName, reasons1);
                for (String response1 : reasons1) {
                    int count = 0;
                    
                    // Count occurrences of 'response' in the list
                    for (String reason : reasons1) {
                        if (response1.equals(reason)) {
                            count++;
                        }
                    }

                    // Check if the entry already exists in the database
                    List<PfcpSessioEstablishmentModificationDelationFailReasonList> existingPfcpFailList = pfcpFailReasonListRepository
                            .findBySessionModificationFailreasonListAndNfNameAndNfType( response1,networkFunctionName,commonServiceImpl.nfTypeofSmf);

                    if (existingPfcpFailList.isEmpty()) {

                        PfcpSessioEstablishmentModificationDelationFailReasonList pfcpFailList = new PfcpSessioEstablishmentModificationDelationFailReasonList();
                        pfcpFailList.setSessionModificationFailreasonList(response1);
                        pfcpFailList.setNfName(networkFunctionName);
                        pfcpFailList.setNfType(commonServiceImpl.nfTypeofSmf);
                        pfcpFailList.setRepeatData(count);

                        pfcpFailReasonListRepository.save(pfcpFailList);
                    } else {
                        for (PfcpSessioEstablishmentModificationDelationFailReasonList existingPfcpFailListEntry : existingPfcpFailList) {
                            if (existingPfcpFailListEntry.getSessionModificationFailreasonList().equals(response1)) {
                                existingPfcpFailListEntry.setRepeatData(count);
                                pfcpFailReasonListRepository.save(existingPfcpFailListEntry);
                                break;
                            }
                        }
                    }
                }
            }
            if (responseMono != null && responseMono.getSessionDeletionFailReasonList() != null) {
                List<String> reasons1 = responseMono.getSessionDeletionFailReasonList();
                
                for (String response2 : reasons1) {
                    int count = 0;
                    
                    // Count occurrences of 'response' in the list
                    for (String reason1 : reasons1) {
                        if (response2.equals(reason1)) {
                            count++;
                        }
                    }

                    // Check if the entry already exists in the database
                    List<PfcpSessioEstablishmentModificationDelationFailReasonList> existingPfcpFailList = pfcpFailReasonListRepository
                            .findBySessionDeletionFailreasonListAndNfNameAndNfType(response2,networkFunctionName,commonServiceImpl.nfTypeofSmf);

                    if (existingPfcpFailList.isEmpty()) {
                        PfcpSessioEstablishmentModificationDelationFailReasonList pfcpFailList = new PfcpSessioEstablishmentModificationDelationFailReasonList();
                        pfcpFailList.setSessionDeletionFailreasonList(response2);
                        pfcpFailList.setNfName(networkFunctionName);
                        pfcpFailList.setNfType(commonServiceImpl.nfTypeofSmf);
                        pfcpFailList.setRepeatData(count);

                        pfcpFailReasonListRepository.save(pfcpFailList);
                    } else {
                        for (PfcpSessioEstablishmentModificationDelationFailReasonList existingPfcpFailListEntry : existingPfcpFailList) {

                            if (existingPfcpFailListEntry.getSessionDeletionFailreasonList().equals(response2)) {
                                loggerTypeA.info("Updating existing PFCP fail reason record for {}: {}", networkFunctionName, response2);
                                existingPfcpFailListEntry.setRepeatData(count);
                                pfcpFailReasonListRepository.save(existingPfcpFailListEntry);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        	loggerTypeA.error("Unable to fetch PfcpSessioEstablishmentModificationDelationFailReasonList "+ e);        }
    }
    	}
    	}


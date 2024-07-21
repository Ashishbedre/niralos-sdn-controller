package com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InitializationService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Repository.InternalServices.InternalDataRepository;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.LiveDataRepository;
import com.other.app.NiralosFiveGCore.model.InternalDataModel;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;

import reactor.core.publisher.Mono;

@Service
public class InitializationServiceImpl implements InitializationService {

	@Autowired
	InternalDataRepository internalDataRepository;

	@Autowired
	InternalDataService internalDataService;
	

	@Autowired
	UriProtocol uriProtocol;
	@Autowired
	LiveDataRepository liveDataRepository;
	final Logger logger = LoggerFactory.getLogger(InitializationServiceImpl.class);



	@Override
	public boolean initialize() {
	    try {
	        // Fetch deployed item information from the URL
	        Mono<SiteInformationDto> deployedItemMono = fetchDeployedItemInformation();

	        // Block and get the deployed item information
	        SiteInformationDto deployedItem = deployedItemMono.block();

	        if (deployedItem == null) {
	            // Handle the case where deployedItemMono is null
	            return false;
	        }

	        // Create InternalDataModel from deployed item information
	        InternalDataModel internalData = createInternalDataModel(deployedItem);

	        // Check if an existing record with the same deployment ID exists
	        InternalDataModel existingData = internalDataRepository.findByNiralControllerClientId(internalData.getNiralControllerClientId());
	        if (existingData != null) {
	            // Update existing record with new data
	            updateInternalData(existingData, internalData);
	        } else {
	            // No existing record found, save new InternalDataModel
	            saveInternalData(internalData);
	        }

	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	private void updateInternalData(InternalDataModel existingData, InternalDataModel newData) {
	    // Update existing InternalDataModel with data from newData
	   
	    existingData.setAmfIp(newData.getAmfIp());
	    existingData.setAmfPort(newData.getAmfPort());
	    existingData.setAusfIp(newData.getAusfIp());
	    existingData.setAusfPort(newData.getAusfPort());
	    existingData.setBsfIp(newData.getBsfIp());
	    existingData.setBsfPort(newData.getBsfPort());
	    existingData.setNiralControllerIp(newData.getNiralControllerIp());
	    existingData.setNrfIp(newData.getNrfIp());
	    existingData.setNrfPort(newData.getNrfPort());
	    existingData.setNssfIp(newData.getNssfIp());
	    existingData.setNssfPort(newData.getNssfPort());
	    existingData.setPcfIp(newData.getPcfIp());
	    existingData.setPcfPort(newData.getPcfPort());
	    existingData.setScpIp(newData.getScpIp());
	    existingData.setScpPort(newData.getScpPort());
	    existingData.setSmfIp(newData.getSmfIp());
	    existingData.setSmfPort(newData.getSmfPort());
	    //existingData.setTenantId(newData.getTenantId());
	    existingData.setUdmIp(newData.getUdmIp());
	    existingData.setUdmPort(newData.getUdmPort());
	    existingData.setUdrIp(newData.getUdrIp());
	    existingData.setUdrPort(newData.getUdrPort());
	    existingData.setUpfIp(newData.getUpfIp());
	    existingData.setUpfPort(newData.getUpfPort());
	    existingData.setUpgIp(newData.getUpgIp());
	    existingData.setUpgPort(newData.getUpgPort());
	   

	    // Save the updated InternalDataModel
	    internalDataRepository.save(existingData);
	}

	private void saveInternalData(InternalDataModel internalData) {
	    // Save InternalDataModel to the repository
	    internalDataRepository.save(internalData);
	}

	private Mono<SiteInformationDto> fetchDeployedItemInformation() {
	    String apiUrl = "http://" + uriProtocol.getUpdatedAgentIp() + ":" + uriProtocol.getUpdatedAgenPortNo() + "/api/v1/siteAndContactInfo";
	    WebClient webClient = WebClient.builder().baseUrl(apiUrl).build();
	    return webClient.get().retrieve().bodyToMono(SiteInformationDto.class);
	}

	
	
	private InternalDataModel createInternalDataModel(SiteInformationDto deployedItem) {
		
	    // Create InternalDataModel with data from deployedItem
	    InternalDataModel internalData = new InternalDataModel();
	    internalData.setNiralControllerClientId(deployedItem.getDeploymentId());
	    
	    internalData.setAmfIp("1");
	    internalData.setAmfPort("9090");
	    internalData.setAusfIp("1");
	    internalData.setAusfPort("9090");
	    internalData.setBsfIp("1");
	    internalData.setBsfPort("9090");
	    internalData.setId(1L);
	    internalData.setNiralControllerIp(deployedItem.globalControllerIp);
	    internalData.setNiralGlobalControllerport(deployedItem.globalControllerPort);
	    internalData.setNrfIp("1");
	    internalData.setNrfPort("9090");
	    internalData.setNssfIp("1");
	    internalData.setNssfPort("9090");
	    internalData.setPcfIp("1");
	    internalData.setPcfPort("9090");
	    internalData.setScpIp("1");
	    internalData.setScpPort("9090");
	    internalData.setSmfIp("1");
	    internalData.setSmfPort("9090");
	    internalData.setUdmIp("1");
	    internalData.setUdmPort("9090");
	    internalData.setUdrIp("1");
	    internalData.setUdrPort("9090");
	    internalData.setUpfIp("1");
	    internalData.setUpfPort("9090");
	    internalData.setUpgIp("1");
	    internalData.setUpgPort("9090");
	   
	    // Set additional fields here if needed

	    return internalData;
	}

	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	@Override
	public void intializeLiveData() {
	    internalDataFrontendService.fetchDeployedItemInformation().subscribe(deployedItem -> {
	        String LocalSdnId = deployedItem.getDeploymentId();
	        String tenantId = deployedItem.getTenantName();
	        String siteId = deployedItem.getSiteName();

	        CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
	        String amfIp = internalDataService.getAmfIp();
	        if (liveDataRepository.countLiveData() == 0) {
	            for (int count = 1; count <= Integer.parseInt(amfIp); count++) {
	                String networkFunctionName = nfServiceImpl.amfName + count;
	                liveDataRepository.save(new LiveDataModel(LocalSdnId, "0", "0",
	                        "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", siteId, tenantId,
	                        nfServiceImpl.nfTypeofAmf, networkFunctionName));
	            }
	        }
	    });
	}

}

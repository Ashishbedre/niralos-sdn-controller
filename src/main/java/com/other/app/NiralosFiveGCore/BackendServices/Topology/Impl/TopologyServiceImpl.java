package com.other.app.NiralosFiveGCore.BackendServices.Topology.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.frontend.InternalDataFrontendService;
import com.other.app.NiralosFiveGCore.BackendServices.Topology.TopologyService;
import com.other.app.NiralosFiveGCore.Dto.InternalData.SiteInformationDto;
import com.other.app.NiralosFiveGCore.Dto.Topology.DeviceDTO;
import com.other.app.NiralosFiveGCore.Dto.Topology.GnBDTO;
import com.other.app.NiralosFiveGCore.Dto.Topology.MainDTO;
import com.other.app.NiralosFiveGCore.Dto.Topology.NrfInfoDto;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeploymentRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeviceRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.GnbFrontendRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.NetworkTopologyRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SdnModelRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SubZoneRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.ZoneRepository;
import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;
import com.other.app.NiralosFiveGCore.model.Topology.DeviceModel;
import com.other.app.NiralosFiveGCore.model.Topology.GnBModel;
import com.other.app.NiralosFiveGCore.model.Topology.SdnModel;
import com.other.app.config.CacheConfig.CacheConfig;

@Service
public class TopologyServiceImpl implements TopologyService{
    private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:TopologyServiceImpl");
    //final Logger logger = LoggerFactory.getLogger(TopologyServiceImpl.class);


	@Autowired
	ZoneRepository zoneRepository;
	@Autowired
	SubZoneRepository subZoneRepository;
	@Autowired
	DeploymentRepository deploymentRepository;
	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	GnbFrontendRepository gnbRepository;
	@Autowired
	SdnModelRepository sdnModelRepository;
	@Autowired
	NetworkTopologyRepository networkTopologyRepository;
	@Autowired
	InternalDataFrontendService internalDataFrontendService;
	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
	@Cacheable(value = "topologyData", key = "'topology'")
    @Override
    public MainDTO getNewTopologyData() {
        // Fetch the SdnModel from the database
		   logger.info("Fetching topology data - Cache Miss!");
        SdnModel sdnModel = sdnModelRepository.findById(1L).orElse(null);

        MainDTO mainDTO = new MainDTO();
        mainDTO.setName("Controller"); // Set the name from the database
        mainDTO.setLevel("Main"); // Set the level from the database
        mainDTO.setStatus(1); // Set the status from the database (assuming status is stored as String and converted to Integer)
        mainDTO.setChildren(mapDevicesToDeviceDTOs(deviceRepository.findAll()));
        return mainDTO;
    }

    private List<DeviceDTO> mapDevicesToDeviceDTOs(List<DeviceModel> devices) {
//    	List<DeviceDTO> deviceDTOs = new ArrayList<>();
//        for (DeviceModel device : devices) {
//            DeviceDTO deviceDTO = new DeviceDTO();
//            deviceDTO.setName(device.getDeviceName());
//            Boolean status = device.getStatus();
//            deviceDTO.setStatus(status != null && status ? 1 : 0);
//            deviceDTO.setLevel("device");
//
//            logger.info("Mapping device: {}", device.getDeviceName());
//
//            // Only set children for "5G-Core-Control-Plane"
//            if ("5G-Core-Control-Plane".equals(device.getDeviceName())) {
//                deviceDTO.setChildren(mapDevicesToNrfDTOs());
//                loggerTypeB.info("Mapped children for 5G-Core-Control-Plane");
//            } else {
//                // Make sure no children are set for "5G-Core-User-Plane"
//                loggerTypeB.info("No children for device: {}", device.getDeviceName());
//                deviceDTO.setChildren(Collections.emptyList()); // Or null, depending on your model
//            }
//
//            deviceDTOs.add(deviceDTO);
//        }
//        return deviceDTOs;
    	 List<DeviceDTO> deviceDTOs = new ArrayList<>();
    	    for (DeviceModel device : devices) {
    	        DeviceDTO deviceDTO = new DeviceDTO();
    	        deviceDTO.setName(device.getDeviceName());
    	        Boolean status = device.getStatus();
    	        deviceDTO.setStatus(status != null && status ? 1 : 0);
    	        deviceDTO.setLevel("device");

    	        logger.info("Mapping device: {}", device.getDeviceName());

    	        // Set children based on the device name
    	        if ("5G-Core-Control-Plane".equals(device.getDeviceName())) {
    	            // Add all NFs except for UPF
    	            deviceDTO.setChildren(mapDevicesToNrfDTOs(false)); // false means exclude UPF
    	            loggerTypeB.info("Mapped children for 5G-Core-Control-Plane");
    	        } else if ("5G-Core-User-Plane".equals(device.getDeviceName())) {
    	            // Add only UPF for User Plane
    	            deviceDTO.setChildren(mapDevicesToNrfDTOs(true)); // true means include only UPF
    	            loggerTypeB.info("Mapped UPF for 5G-Core-User-Plane");
    	        } else {
    	            deviceDTO.setChildren(Collections.emptyList());
    	            loggerTypeB.info("No children for device: {}", device.getDeviceName());
    	        }

    	        deviceDTOs.add(deviceDTO);
    	    }
    	    return deviceDTOs;
    	
    
    }

    private List<NrfInfoDto> mapDevicesToNrfDTOs(boolean onlyUpf) {
//        // Fetch all NetworkTopologyModel entries from the repository
//        List<NetworkTopologyModel> nrfInfo = networkTopologyRepository.findAll();
//        List<NrfInfoDto> nrfDtos = new ArrayList<>();
//        loggerTypeB.info("mapDevicesToNrfDTOs: Fetched {} NetworkTopologyModels from the repository.", nrfInfo.size());
//        for (NetworkTopologyModel nf : nrfInfo) {
//            NrfInfoDto nrfInfoDto = new NrfInfoDto();
//            nrfInfoDto.setNfStatus(nf.getNfStatus());
//            nrfInfoDto.setNfType(nf.getNfType());
//            nrfInfoDto.setNfIp(nf.getNfIp());
//
//            if ("amf".equals(nf.getNfType())) {
//                List<GnBModel> gnbs = gnbRepository.findAll();
//                nrfInfoDto.setChildren(mapGnBsToGnBDTOs(gnbs));
//            }
//
//            nrfDtos.add(nrfInfoDto);
//        }
//        return nrfDtos;
    	// Fetch all NetworkTopologyModel entries from the repository
        List<NetworkTopologyModel> nrfInfo = networkTopologyRepository.findAll();
        List<NrfInfoDto> nrfDtos = new ArrayList<>();

        loggerTypeB.info("mapDevicesToNrfDTOs: Fetched {} NetworkTopologyModels from the repository.", nrfInfo.size());

        for (NetworkTopologyModel nf : nrfInfo) {
            NrfInfoDto nrfInfoDto = new NrfInfoDto();

            // Check if we are only including UPF
            if (onlyUpf) {
                if ("upf".equals(nf.getNfType())) {
                    nrfInfoDto.setNfStatus(nf.getNfStatus());
                    nrfInfoDto.setNfType(nf.getNfType());
                    nrfInfoDto.setNfIp(nf.getNfIp());
                    if ("amf".equals(nf.getNfType())) {
                        List<GnBModel> gnbs = gnbRepository.findAll();
                        nrfInfoDto.setChildren(mapGnBsToGnBDTOs(gnbs));
                    }
                    nrfDtos.add(nrfInfoDto);
                    loggerTypeB.info("Added UPF NF to User Plane");
                }
            } else {
                // Add all NFs except UPF
                if (!"upf".equals(nf.getNfType())) {
                    nrfInfoDto.setNfStatus(nf.getNfStatus());
                    nrfInfoDto.setNfType(nf.getNfType());
                    nrfInfoDto.setNfIp(nf.getNfIp());
                    if ("amf".equals(nf.getNfType())) {
                        List<GnBModel> gnbs = gnbRepository.findAll();
                        nrfInfoDto.setChildren(mapGnBsToGnBDTOs(gnbs));
                    }
                    nrfDtos.add(nrfInfoDto);
                    loggerTypeB.info("Added NF: {} to Control Plane", nf.getNfType());
                }
            }
        }

        return nrfDtos;
    
    }

    private List<GnBDTO> mapGnBsToGnBDTOs(List<GnBModel> gnbs) {
        List<GnBDTO> gnbDTOs = new ArrayList<>();
        // Log the number of GnBModels being processed
        loggerTypeB.info("mapGnBsToGnBDTOs:Mapping {} GnBModels to GnBDTOs.", gnbs.size());
        for (GnBModel gnb : gnbs) {
            GnBDTO gnbDTO = new GnBDTO();
            gnbDTO.setName(gnb.getGnbName());
            gnbDTO.setValue(gnb.getGnbId());
            gnbDTO.setStatus(gnb.getStatus() ? 1 : 0);
            gnbDTO.setLevel("gnb");
//            gnbDTO.setDpName(gnb.getSiteId());
//            gnbDTO.setTenentId(gnb.getTenentId());
//            gnbDTO.setSiteId(gnb.getSiteId());
            gnbDTOs.add(gnbDTO);
        }
        // Log the total number of GnBDTOs created
        loggerTypeB.info("mapGnBsToGnBDTOs: Total GnBDTOs created: {}", gnbDTOs.size());
        return gnbDTOs;
    }

}

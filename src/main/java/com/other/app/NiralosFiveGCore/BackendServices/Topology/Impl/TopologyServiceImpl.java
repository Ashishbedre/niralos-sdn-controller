package com.other.app.NiralosFiveGCore.BackendServices.Topology.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class TopologyServiceImpl implements TopologyService{
	final Logger logger = LoggerFactory.getLogger(TopologyServiceImpl.class);


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
   

    @Override
    public MainDTO getNewTopologyData() {
    	SiteInformationDto deployedItem = internalDataFrontendService.fetchDeployedItemInformation().block();
	//	String LocalSdnId = deployedItem.getDeploymentId();

		// Extract tenantId and siteId from the fetched deployed item
		String tenantId = deployedItem.getTenantName();
		String siteId = deployedItem.getSiteName();

        // Fetch the SdnModel from the database
        SdnModel sdnModel = sdnModelRepository.findById(1L).orElse(null);

        MainDTO mainDTO = new MainDTO();
        mainDTO.setName("SDN"); // Set the name from the database
        mainDTO.setLevel("Main"); // Set the level from the database
        mainDTO.setStatus(1); // Set the status from the database (assuming status is stored as String and converted to Integer)
        mainDTO.setChildren(mapDevicesToDeviceDTOs(deviceRepository.findAll(), tenantId, siteId));
        return mainDTO;
    }

    private List<DeviceDTO> mapDevicesToDeviceDTOs(List<DeviceModel> devices, String tenantId, String siteId) {
        List<DeviceDTO> deviceDTOs = new ArrayList<>();
        for (DeviceModel device : devices) {
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setName(device.getDeviceName());
            Boolean status = device.getStatus();
            deviceDTO.setStatus(status != null && status ? 1 : 0);
            deviceDTO.setLevel("device");
            deviceDTO.setTenentId(device.getTenantId());
            deviceDTO.setSiteId(device.getSiteId());

            if (siteId.equals(device.getSiteId()) && tenantId.equals(device.getTenantId())) {
                if ("5G-Control-Core".equals("5G-Control-Core") && "5G-Control-Core".equals(device.getDeviceName())) {
                    deviceDTO.setChildren(mapDevicesToNrfDTOs(tenantId, siteId));
                } else if ("5G-Data-Plane".equals("5G-Data-Plane") && "5G-Data-Plane".equals(device.getDeviceName())) {
                    deviceDTO.setChildren(mapDevicesToNrfDTOs(tenantId, siteId));
                }
            }
            deviceDTOs.add(deviceDTO);
        }
        return deviceDTOs;
    }

    private List<NrfInfoDto> mapDevicesToNrfDTOs(String tenantId, String siteId) {
        List<NetworkTopologyModel> nrfInfo = networkTopologyRepository.findByTenantIdAndSiteId(tenantId, siteId);
        List<NrfInfoDto> nrfDtos = new ArrayList<>();
        for (NetworkTopologyModel nf : nrfInfo) {
            NrfInfoDto nrfInfoDto = new NrfInfoDto();
            nrfInfoDto.setNfStatus(nf.getNfStatus());
            nrfInfoDto.setNfType(nf.getNfType());
            nrfInfoDto.setNfIp(nf.getNfIp());
            nrfInfoDto.setTenantId(nf.getTenantId());
            nrfInfoDto.setSiteId(nf.getSiteId());

            if ("amf".equals(nf.getNfType())) {
                List<GnBModel> gnbs = gnbRepository.findAllGnbsBySiteId(nf.getSiteId());
                nrfInfoDto.setChildren(mapGnBsToGnBDTOs(gnbs));
            }

            nrfDtos.add(nrfInfoDto);
        }
        return nrfDtos;
    }

    private List<GnBDTO> mapGnBsToGnBDTOs(List<GnBModel> gnbs) {
        List<GnBDTO> gnbDTOs = new ArrayList<>();
        for (GnBModel gnb : gnbs) {
            GnBDTO gnbDTO = new GnBDTO();
            gnbDTO.setName(gnb.getGnbName());
            gnbDTO.setValue(gnb.getGnbId());
            gnbDTO.setStatus(gnb.getStatus() ? 1 : 0);
            gnbDTO.setLevel("gnb");
            gnbDTO.setDpName(gnb.getSiteId());
            gnbDTO.setTenentId(gnb.getTenentId());
            gnbDTO.setSiteId(gnb.getSiteId());
            gnbDTOs.add(gnbDTO);
        }
        return gnbDTOs;
    }

}

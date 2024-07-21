package com.other.app.NiralosFiveGCore.BackendServices.Topology.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.Topology.DeviceModelService;
import com.other.app.NiralosFiveGCore.Repository.LiveDataManagement.LiveDataRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeploymentRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeviceRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.NetworkTopologyRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SubZoneRepository;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.NetworkTopologyModel;
import com.other.app.NiralosFiveGCore.model.Topology.DeploymentModel;
import com.other.app.NiralosFiveGCore.model.Topology.DeviceModel;

@Service
@EnableScheduling
@Configuration
public class DeviceModelServiceImpl implements DeviceModelService {
	final Logger logger = LoggerFactory.getLogger(DeviceModelServiceImpl.class);


    @Autowired
    LiveDataRepository liveDataRepository;

    @Autowired
    DeviceRepository deviceRepository;
    
    @Autowired
    NetworkTopologyRepository networkTopologyRepository;

    @Autowired
    DeploymentRepository deploymentRepository;
    @Autowired
    SubZoneRepository subZoneRepository;
    @Scheduled(fixedRate = 5000)
    @Override
    public void updateDeploymentId() {
        List<DeviceModel> deviceModels = deviceRepository.findAll();
        List<DeploymentModel> deploymentModels = deploymentRepository.findAll();

        for (DeviceModel deviceModel : deviceModels) {
            for (DeploymentModel deploymentModel : deploymentModels) {
                if (deviceModel.getDeploymentId() == null &&
                    deviceModel.getSiteId().equals(deploymentModel.getDeploymentName()) &&
                    deviceModel.getTenantId().equals(deploymentModel.getTenentName())) {
                    
                    deviceModel.setDeploymentId(deploymentModel.getDeploymentId());
                    deviceRepository.save(deviceModel);
                } else if (deviceModel.getDeploymentId() != null &&
                           deviceModel.getSiteId().equals(deploymentModel.getDeploymentName())&&
                           deviceModel.getTenantId().equals(deploymentModel.getTenentName())) {
                    deviceModel.setDeploymentId(deploymentModel.getDeploymentId());
                    deviceRepository.save(deviceModel);
                }
            }
        }
    }
    
    @Scheduled(fixedRate = 5000)
    @Override
    public void updateDeviceModelData() {
        try {
            // Fetch the LiveDataModel from the database;
            List<LiveDataModel> dataModels = liveDataRepository.findAll(); // Fetch from database

            for (LiveDataModel dataModel : dataModels) {
                String tenentId = dataModel.getTenentId();
                String siteId = dataModel.getSiteId();
                String agentId = dataModel.getControllerClientId();
                String nfName=dataModel.getNfName();
                String nfType=dataModel.getNfType();
                
                Boolean networkstatus1;
                if("1".equals(dataModel.getActiveamf()))   
                	networkstatus1=true;


                else 
                	networkstatus1=false;
         if ("0".equals(dataModel.getActiveamf()) || "1".equals(dataModel.getActiveamf())) {
                   
					// Check if a DeviceModel already exists with the same tenentId, siteId, and agentId
                    DeviceModel existingDevice = deviceRepository.findByTenentIdAndSiteIdAndAgentIdAndNfNameAndNfType(tenentId, siteId, agentId,nfName,nfType);
                    if (siteId != null && tenentId!=null) {
                    if (existingDevice != null) {
                        // Update the existing record
                        existingDevice.setDeviceName("5G-Control-Core");
                        existingDevice.setTenantId(tenentId);
                        existingDevice.setAgentId(agentId);
                        existingDevice.setStatus(networkstatus1);
                        existingDevice.setNfName(dataModel.getNfName());
                        existingDevice.setNfType(dataModel.getNfType());
                        deviceRepository.save(existingDevice);

                        logger.info("Device model updated");
                    } else {
                        // Create a new DeviceModel if it doesn't exist
                        DeviceModel newDeviceModel = new DeviceModel();
                        newDeviceModel.setTenantId(tenentId);
                        newDeviceModel.setSiteId(siteId);
                        newDeviceModel.setAgentId(agentId);
                        newDeviceModel.setDeviceName("5G-Control-Core");
                        newDeviceModel.setStatus(networkstatus1);
                        newDeviceModel.setNfName(dataModel.getNfName());
                        newDeviceModel.setNfType(dataModel.getNfType());

                        deviceRepository.save(newDeviceModel);

                        logger.info("Device model saved");
                    }
                }}
            }
        } catch (Exception e) {
//            e.printStackTrace();
        	logger.info("issue occure in updating Device Data(Need to Check Live Data)" + e);
            // Consider logging the exception with a logger like log4j or slf4j for better visibility.
        }
    }
    @Scheduled(fixedRate = 5000)
    @Override
    public void updateDeviceModelData1() 
    {
        try {
            List<NetworkTopologyModel> upfInfoList = networkTopologyRepository.findByDevice("5g-Data-plane");
            for (NetworkTopologyModel upfInfo : upfInfoList) {
            	
            	logger.info(upfInfo.getNfName());
            	
                int networkFunctionStatus = Integer.parseInt(upfInfo.getNfStatus()) ;
                if (networkFunctionStatus == 0 || networkFunctionStatus == 1) {
                    String tenentId = upfInfo.getTenantId();
                    String siteId = upfInfo.getSiteId();
                    String agentId = "null";
                    
                    String nfName=upfInfo.getNfName();
                    String nfType=upfInfo.getNfType();
                    Integer status =Integer.parseInt(upfInfo.getNfStatus()) ;
                    Boolean networkstatus;
                 if(status==1) {
                	  networkstatus=true;
                 }else {
               	  networkstatus=false;

                 }
                 
                    DeviceModel existingDevice = deviceRepository.findByTenentIdAndSiteIdAndAgentIdAndNfNameAndNfType(tenentId, siteId,agentId,nfName,nfType);
                    if (siteId != null && tenentId!=null) {
                    if (existingDevice != null) {
                    	
                    	existingDevice.setSiteId(siteId);
                    	existingDevice.setTenantId(tenentId);
                    	existingDevice.setStatus(networkstatus);
                    	existingDevice.setNfName(nfName);
                    	existingDevice.setNfType(nfType);
                        // Update the existing record
                    	existingDevice.setDeviceName("5g-Data-Plane");
                    		deviceRepository.save(existingDevice);
                    	
                    } else {
                        // Create a new DeviceModel if it doesn't exist
                        DeviceModel newDeviceModel = new DeviceModel();
                        newDeviceModel.setTenantId(tenentId);
                        newDeviceModel.setSiteId(siteId);
                        newDeviceModel.setAgentId(agentId);
                        newDeviceModel.setDeviceName("5g-Data-Plane");
                        newDeviceModel.setStatus(networkstatus);
                        newDeviceModel.setNfName(nfName);
                        newDeviceModel.setNfType(nfType);

                        deviceRepository.save(newDeviceModel);
                    }
                }}
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Consider logging the exception using a logging framework like Log4j or SLF4J for better error handling.
        }
    
    }
    
    
}

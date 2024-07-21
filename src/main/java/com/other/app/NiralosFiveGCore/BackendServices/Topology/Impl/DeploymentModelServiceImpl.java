package com.other.app.NiralosFiveGCore.BackendServices.Topology.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.Topology.DeploymentModelService;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeploymentRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeviceRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SubZoneRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.ZoneRepository;
import com.other.app.NiralosFiveGCore.model.Topology.DeploymentModel;
import com.other.app.NiralosFiveGCore.model.Topology.SubZoneModel;
import com.other.app.NiralosFiveGCore.model.Topology.ZoneModel;


@Service
@EnableScheduling
@Configuration
public class DeploymentModelServiceImpl implements DeploymentModelService {
	final Logger logger = LoggerFactory.getLogger(DeploymentModelServiceImpl.class);

	@Autowired
	DeviceRepository deviceRepository;
	@Autowired
	DeploymentRepository deploymentRepository;
	@Autowired
	SubZoneRepository subZoneRepository;
	@Autowired
	ZoneRepository zoneRepository;
	
	@Override
	public void zoneDetails(String agentId, String zoneName, String subzoneName, String tenantId, String siteId) {
		if(zoneRepository.countData(zoneName)==0) {
			ZoneModel zoneModel = new ZoneModel();
			zoneModel.setZoneName(zoneName);
			zoneModel.setTenentName(tenantId);
			zoneRepository.save(zoneModel);
		}
		else
		zoneRepository.saveZoneData(zoneName, tenantId);
		if(subZoneRepository.countData(subzoneName)==0) {
			SubZoneModel subZoneModel = new SubZoneModel();
			subZoneModel.setSubZoneName(subzoneName);
			subZoneModel.setTenentId(tenantId);
			subZoneRepository.save(subZoneModel);
		}
		else
		subZoneRepository.savesubZoneData(subzoneName,tenantId,siteId);		
		if(deploymentRepository.countData(subzoneName,tenantId,siteId)==0) {
			DeploymentModel deploymentData = new DeploymentModel();
			deploymentData.setDeploymentName(siteId);
			deploymentData.setTenentName(tenantId);
			deploymentData.setSubZoneName(subzoneName);
			deploymentRepository.save(deploymentData);
		}
		else
			deploymentRepository.updateData(subzoneName,tenantId,siteId);
			
		
	}

	@Scheduled(fixedRate = 5000) // Adjust the schedule as needed
	@Override
	public void updateDeploymentSubZoneId() {
		List<DeploymentModel> deploymentModels = deploymentRepository.findAll();
		List<SubZoneModel> subZoneModels = subZoneRepository.findAll();

		for (DeploymentModel deploymentModel : deploymentModels) {
			for (SubZoneModel subZoneModel : subZoneModels) {
				if (deploymentModel.getSubZoneId() == null
						&& deploymentModel.getSubZoneName().equals(subZoneModel.getSubZoneName())
						&& deploymentModel.getTenentName().equals(subZoneModel.getTenentId())) {
					deploymentModel.setSubZoneId(subZoneModel.getSubZoneId());
					deploymentRepository.save(deploymentModel);
				} else if (deploymentModel.getSubZoneId() != null
						&& deploymentModel.getSubZoneName().equals(subZoneModel.getSubZoneName())
						&& deploymentModel.getTenentName().equals(subZoneModel.getTenentId())) {
					deploymentModel.setSubZoneId(subZoneModel.getSubZoneId());
					deploymentRepository.save(deploymentModel);

				}
			}
		}
	}
	@Scheduled(fixedRate = 5000) // Adjust the schedule as needed
	@Override
	public void updateSubZoneId() {
	    List<ZoneModel> zoneModels = zoneRepository.findAll();
	    List<SubZoneModel> subZoneModels = subZoneRepository.findAll();
	    
	    for (SubZoneModel subZoneModel : subZoneModels) {
	        if (subZoneModel.getZoneId() == null) {
	            for (ZoneModel zoneModel : zoneModels) {
	                if (subZoneModel.getTenentId().equals(zoneModel.getTenentName())) {
	                    subZoneModel.setZoneId(zoneModel.getZoneId());
	                    subZoneRepository.save(subZoneModel);
	                    break; // Once updated, no need to continue searching for zoneModel
	                }
	            }
	        } else {
	            for (ZoneModel zoneModel : zoneModels) {
	                if (subZoneModel.getTenentId().equals(zoneModel.getTenentName())) {
	                    subZoneModel.setZoneId(zoneModel.getZoneId());
	                    subZoneRepository.save(subZoneModel);
	                    break; // Once updated, no need to continue searching for zoneModel
	                }
	            }
	        }
	    }
	}

}

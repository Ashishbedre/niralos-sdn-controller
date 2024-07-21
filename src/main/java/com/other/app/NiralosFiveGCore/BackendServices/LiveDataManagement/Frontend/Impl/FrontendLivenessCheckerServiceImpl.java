package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Frontend.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Frontend.FrontendLivenessCheckerService;
import com.other.app.NiralosFiveGCore.Repository.GnbStatistics.GnbStatsRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeploymentRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.DeviceRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.SubZoneRepository;
import com.other.app.NiralosFiveGCore.Repository.Topology.ZoneRepository;
import com.other.app.NiralosFiveGCore.model.Topology.DeploymentModel;
import com.other.app.NiralosFiveGCore.model.Topology.SubZoneModel;
import com.other.app.NiralosFiveGCore.model.Topology.ZoneModel;


@Service
@EnableScheduling
public class FrontendLivenessCheckerServiceImpl implements FrontendLivenessCheckerService{
	final Logger logger = LoggerFactory.getLogger(FrontendLivenessCheckerServiceImpl.class);

	
	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	DeploymentRepository deploymentRepository;

	@Autowired
	SubZoneRepository subZoneRepository;

	@Autowired
	ZoneRepository zoneRepository;

	@Autowired
	GnbStatsRepository gnbStatsRepository;

	@Override
	@Scheduled(fixedRate = 2000)
	public void deploymentActiveChecker() {
		List<DeploymentModel> deploymentModels = deploymentRepository.findAll();
		for (DeploymentModel deploymentModel : deploymentModels) {
			if (deviceRepository.checkIfAnyDeviceActive(deploymentModel.getDeploymentId()) >= 1) {
				deploymentRepository.setDeploymentActive(deploymentModel.getDeploymentId());
			} else {
				deploymentRepository.setDeploymentInactive(deploymentModel.getDeploymentId());
			}
		}
	}

	@Override
	@Scheduled(fixedRate = 2000)
	public void subZoneActiveChecker() {
		List<SubZoneModel> subZoneModels = subZoneRepository.findAll();
		for (SubZoneModel subZoneModel : subZoneModels) {
			if (deploymentRepository.checkIfAnyDeploymentActive(subZoneModel.getSubZoneId()) >= 1) {
				subZoneRepository.setSubZoneActive(subZoneModel.getSubZoneId());
			} else {
				subZoneRepository.setSubZoneInactive(subZoneModel.getSubZoneId());
			}
		}
	}

	@Override
	@Scheduled(fixedRate = 2000)
	public void zoneActiveChecker() {
		List<ZoneModel> zoneModels = zoneRepository.findAll();
		for (ZoneModel zoneModel : zoneModels) {
			if (subZoneRepository.checkIfAnySubZoneActive(zoneModel.getZoneId()) >= 1) {
				zoneRepository.setZoneActive(zoneModel.getZoneId());
			} else {
				zoneRepository.setZoneInactive(zoneModel.getZoneId());
			}
		}

	}

}

package com.other.app.niralos_edge.Service.SavingInternalData.InternalServiceImpl;

import java.security.SecureRandom;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.AlertHypervisorRepository;
import com.other.app.niralos_edge.Repository.AlertVmRepository;
import com.other.app.niralos_edge.Repository.HypervisorLogRepo;
import com.other.app.niralos_edge.Repository.HypervisorNetworkStatsRepo;
import com.other.app.niralos_edge.Repository.HypervisorStatsRepo;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Repository.VmNetworkInterfaceRepo;
import com.other.app.niralos_edge.Repository.VmStatsRepo;
import com.other.app.niralos_edge.Service.SavingInternalData.InternalService;
import com.other.app.niralos_edge.dto.InternalDataDto;

@Service
public class InternalServiceImpl implements InternalService{
	private static final Logger logger= LoggerFactory.getLogger("EDGE: InternalServiceImpl");

	@Autowired
	InternalDataRepositorys internalDataRepository;
	
	@Autowired
	VmStatsRepo vmStatsRepo;
	
	@Autowired
	HypervisorStatsRepo hypervisorStatsRepo;
	
	@Autowired
	HypervisorNetworkStatsRepo hypervisorNetworkStatsRepo;
	
	@Autowired
	VmNetworkInterfaceRepo vmNetworkInterfaceRepo;

	@Autowired
	AlertHypervisorRepository alertHypervisorRepository;
	
	@Autowired
	AlertVmRepository alertVmRepository;
	
	@Autowired
	HypervisorLogRepo hypervisorLogRepo;
	
	@Override
	public String saveInternalData(InternalDataDto dto) {
		logger.info("Saving the internal Data.");
		InternalDataModels model=new InternalDataModels();
		model.setEdgeClientId(randomString(12));
		model.setHypervisorIp(dto.getHypervisorIp());
		model.setHypervisorNodeName(dto.getNodeName());
		model.setHypervisorToken(dto.getHypervisorToken());
		model.setPassword(dto.getPassword());
		model.setUserName(dto.getUserName());
		model.setHypervisorPort(dto.getHypervisorPort());
		
		internalDataRepository.save(model);
		
		return "Data Saved Successfully";
	}

	@Override
	public String updateInternalData(InternalDataDto dataDto,String edgeClientId) {
		logger.info("Updating the Data.");

		internalDataRepository.updateInternalData(edgeClientId, dataDto.getHypervisorIp(), dataDto.getHypervisorToken(), dataDto.getUserName(), dataDto.getPassword(),dataDto.getHypervisorPort(),dataDto.getNodeName());
		return "Data Updated Successfully";
	}

	@Override
	public String deleteInternalData(String edgeClientId) {
       logger.info("Deleting the internal Data.");
		internalDataRepository.deleteId(edgeClientId);
		vmStatsRepo.deleteVMStatsTable(edgeClientId);
		hypervisorStatsRepo.deleteData(edgeClientId);
		hypervisorNetworkStatsRepo.deleteData(edgeClientId);
		vmNetworkInterfaceRepo.deleteData(edgeClientId);
		alertHypervisorRepository.deleteData(edgeClientId);
		alertVmRepository.deleteData(edgeClientId);
		hypervisorLogRepo.deleteId(edgeClientId);
		
		return "Data Deleted Successfully";
	}

	
	////////////////Generating Random 12-digit edgeClientId////////////////////////////////////
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public String randomString(int len){
	   StringBuilder sb = new StringBuilder(len);
	   for(int i = 0; i < len; i++)
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   return sb.toString();
	}


}

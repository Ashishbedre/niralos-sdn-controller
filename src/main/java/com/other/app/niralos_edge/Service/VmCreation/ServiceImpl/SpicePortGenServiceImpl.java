package com.other.app.niralos_edge.Service.VmCreation.ServiceImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.other.app.niralos_edge.Model.SpicePortGenModel;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Repository.SpicePortGenRepository;
import com.other.app.niralos_edge.Repository.VmStatsRepo;
import com.other.app.niralos_edge.Service.VmCreation.SpicePortGenService;
import com.other.app.niralos_edge.dto.SpiceDataDto;


@Service
public class SpicePortGenServiceImpl implements SpicePortGenService{

	 private static final Logger logger=LoggerFactory.getLogger("EDGE:SpicePortGenServiceImpl");
	 
	 @Autowired
	 private SpicePortGenRepository genRepository;
	 
	 @Autowired
	 private InternalDataRepositorys internalDataRepositorys;
	 
	 @Autowired
	 private VmStatsRepo vmStatsRepo;

	   
	    @Override
	    public int generateUniqueNumber() {
	    	
	    	int temp=5000;
	        try {
	            
	        	if(genRepository.getDataExist()==0)
	        	{
	        		SpicePortGenModel model=new SpicePortGenModel(1L,temp);
	        		genRepository.save(model);
					logger.info("Initialized and saved new SpicePortGenModel with port number '{}'.", temp);}else {
	        		
	        		temp=genRepository.getData()+1;
	        		
	        		SpicePortGenModel data=new SpicePortGenModel(1L,temp);
	        		genRepository.save(data);
					logger.info("Updated and saved SpicePortGenModel with new port number '{}'.", temp);}
	        	
	        } catch (Exception e) {
	        	
	        	logger.error("Error Ocurred While Generating Port No. For Spice"+e);
	        }
			return temp;
	        
	    }

		@Override
		public SpiceDataDto getSpiceData(String edgeClientId, Long vmId) {
			logger.info("Getting the SpiceData....");
			SpiceDataDto dto=new SpiceDataDto();
			dto.setHypervisorIp(internalDataRepositorys.hypervisorIp(edgeClientId));
			dto.setSpicePort(vmStatsRepo.getSpicePort(vmId, edgeClientId));
			
			return dto;
		}


}

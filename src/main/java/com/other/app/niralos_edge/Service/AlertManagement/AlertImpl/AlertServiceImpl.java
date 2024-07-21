package com.other.app.niralos_edge.Service.AlertManagement.AlertImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.other.app.niralos_edge.Model.EdgeHypervisorAlertModel;
import com.other.app.niralos_edge.Model.EdgeVmAlertModel;
import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.VmStatsModel;
import com.other.app.niralos_edge.Repository.AlertHypervisorRepository;
import com.other.app.niralos_edge.Repository.AlertVmRepository;
import com.other.app.niralos_edge.Repository.HypervisorStatsRepo;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Repository.VmStatsRepo;
import com.other.app.niralos_edge.Service.AlertManagement.AlertService;
import com.other.app.niralos_edge.dto.AlertDto;

@Service
@EnableScheduling
public class AlertServiceImpl implements AlertService{

	@Autowired
	InternalDataRepositorys internalDataRepositorys;
	
	@Autowired
	HypervisorStatsRepo statsRepo;
	
	@Autowired
	VmStatsRepo vmStatsRepo;
	
	@Autowired
	AlertHypervisorRepository alertHypervisorRepository;
	
	@Autowired
	AlertVmRepository alertVmRepository;
	
	@Value("${edge.alert.time}")
	private String alertTime;
	
	@Override
//	@Scheduled(fixedRate = 10000)
	@Scheduled(fixedRateString = "${edge.alert.time}")
	public void savingHypervisorAndVmAlert() {

		List<HypervisorStatsModel> temp=statsRepo.findAll();
		
		for (HypervisorStatsModel hypervisorStatsModel : temp) {
			
			if(hypervisorStatsModel.getCurrentRamUsage()>=90L)
			{
					
						EdgeHypervisorAlertModel alert=new EdgeHypervisorAlertModel();
						alert.setAlert("Ram Usage Exceeded");
						alert.setType("Hypervisor Alarm");
						alert.setEdgeClientId(hypervisorStatsModel.getEdgeClientId());
						alert.setDate(LocalDateTime.now());
						alert.setStatus(true);
						
						alertHypervisorRepository.save(alert);
			}
			
		   if(hypervisorStatsModel.getCpuUsage()>90.00)
		   {
			   
					EdgeHypervisorAlertModel alert=new EdgeHypervisorAlertModel();
					alert.setAlert("High Cpu Usage");
					alert.setType("Hypervisor Alarm");
					alert.setEdgeClientId(hypervisorStatsModel.getEdgeClientId());
					alert.setDate(LocalDateTime.now());
					alert.setStatus(true);
					
					alertHypervisorRepository.save(alert);
				
		   }
		   
		   if((hypervisorStatsModel.getUsedDiskSpace()*100L/hypervisorStatsModel.getTotalDiskspace())>=90L)
		   {
			  
					EdgeHypervisorAlertModel alert=new EdgeHypervisorAlertModel();
					alert.setAlert("High Disk Consumption");
					alert.setType("Hypervisor Alarm");
					alert.setEdgeClientId(hypervisorStatsModel.getEdgeClientId());
					alert.setDate(LocalDateTime.now());
					alert.setStatus(true);
					
					alertHypervisorRepository.save(alert);
				
		   }
		}
		
		
		List<VmStatsModel> data=vmStatsRepo.findAll();
		
		for (VmStatsModel vmStatsModel : data) {
			
			if(vmStatsModel.getVmCurrentCpuUsage()>=95.00)
    		{
    			
    				EdgeVmAlertModel alert=new EdgeVmAlertModel();
    				
    				alert.setAlert("High CPU Usage");
    				alert.setType("Vm Alarm");
    				alert.setDate(LocalDateTime.now());
    				alert.setEdgeClientId(vmStatsModel.getEdgeClientId());
    				alert.setVmId(vmStatsModel.getVmId());
    				alert.setVmName(vmStatsModel.getVmName());
    				alert.setStatus(true);
    				
    				alertVmRepository.save(alert);
    			
    		}
    		
    		if(vmStatsModel.getVmCurrentMemUsage()>=95.00)
    		{
    			
    				EdgeVmAlertModel alert=new EdgeVmAlertModel();
    				
    				alert.setAlert("High Memory Usage");
    				alert.setType("Vm Alarm");
    				alert.setDate(LocalDateTime.now());
    				alert.setEdgeClientId(vmStatsModel.getEdgeClientId());
    				alert.setVmId(vmStatsModel.getVmId());
    				alert.setVmName(vmStatsModel.getVmName());
    				alert.setStatus(true);
    				
    				alertVmRepository.save(alert);
    			
    		}
		}
	}

	@Override
	public AlertDto getListOfAlert(String edgeClientId) {

		
		AlertDto model=new AlertDto();
		
		model.setHypervisorId(edgeClientId);
		
		ArrayList<EdgeHypervisorAlertModel> temp=new ArrayList<>();
		List<EdgeHypervisorAlertModel> list=alertHypervisorRepository.getAlert(edgeClientId);
		for (EdgeHypervisorAlertModel edgeHypervisorAlert : list) {
			
			EdgeHypervisorAlertModel wild=new EdgeHypervisorAlertModel();
			wild.setAlert(edgeHypervisorAlert.getAlert());
			wild.setAlertId(edgeHypervisorAlert.getAlertId());
			wild.setDate(edgeHypervisorAlert.getDate());
			wild.setType(edgeHypervisorAlert.getType());
			wild.setEdgeClientId(edgeHypervisorAlert.getEdgeClientId());
			wild.setStatus(edgeHypervisorAlert.getStatus());
			
			temp.add(wild);
		}
		
		model.setHypervisorAlert(temp);
		
		ArrayList<EdgeVmAlertModel> temp2=new ArrayList<>();
		List<EdgeVmAlertModel> list2=alertVmRepository.getVmAlert(edgeClientId);
		
		for (EdgeVmAlertModel edgeVmAlert : list2) {
			
			EdgeVmAlertModel wild2=new EdgeVmAlertModel();
			wild2.setAlert(edgeVmAlert.getAlert());
			wild2.setAlertId(edgeVmAlert.getAlertId());
			wild2.setDate(edgeVmAlert.getDate());
			wild2.setEdgeClientId(edgeVmAlert.getEdgeClientId());
			wild2.setVmId(edgeVmAlert.getVmId());
			wild2.setVmName(edgeVmAlert.getVmName());
			wild2.setType(edgeVmAlert.getType());
			wild2.setStatus(edgeVmAlert.getStatus());
			
			temp2.add(wild2);
		}
		
		model.setHypervisorVmAlert(temp2);
		
		
		return model;
	}

	@Override
	public String deleteHypervisorAlert(String edgeClientId, Long alertId) {
		
		alertHypervisorRepository.updateData(edgeClientId,alertId);
		
		return "Hypervisor ALert Resolved";
	}

	@Override
	public String deleteVmAlert(String edgeClientId,Long vmid ,Long alertId) {

		alertVmRepository.updateData(edgeClientId, vmid,alertId);
		
		return "VM Alert Resolved";
	}

	@Override
	public AlertDto getListOfAlertResolved(String edgeClientId) {

        AlertDto model=new AlertDto();
		
		model.setHypervisorId(edgeClientId);
		
		ArrayList<EdgeHypervisorAlertModel> temp=new ArrayList<>();
		List<EdgeHypervisorAlertModel> list=alertHypervisorRepository.getAlertResolved(edgeClientId);
		for (EdgeHypervisorAlertModel edgeHypervisorAlert : list) {
			
			EdgeHypervisorAlertModel wild=new EdgeHypervisorAlertModel();
			wild.setAlert(edgeHypervisorAlert.getAlert());
			wild.setAlertId(edgeHypervisorAlert.getAlertId());
			wild.setDate(edgeHypervisorAlert.getDate());
			wild.setType(edgeHypervisorAlert.getType());
			wild.setEdgeClientId(edgeHypervisorAlert.getEdgeClientId());
			wild.setStatus(edgeHypervisorAlert.getStatus());
			
			temp.add(wild);
		}
		
		model.setHypervisorAlert(temp);
		
		ArrayList<EdgeVmAlertModel> temp2=new ArrayList<>();
		List<EdgeVmAlertModel> list2=alertVmRepository.getVmAlertResolved(edgeClientId);
		
		for (EdgeVmAlertModel edgeVmAlert : list2) {
			
			EdgeVmAlertModel wild2=new EdgeVmAlertModel();
			wild2.setAlert(edgeVmAlert.getAlert());
			wild2.setAlertId(edgeVmAlert.getAlertId());
			wild2.setDate(edgeVmAlert.getDate());
			wild2.setEdgeClientId(edgeVmAlert.getEdgeClientId());
			wild2.setVmId(edgeVmAlert.getVmId());
			wild2.setVmName(edgeVmAlert.getVmName());
			wild2.setType(edgeVmAlert.getType());
			wild2.setStatus(edgeVmAlert.getStatus());
			
			temp2.add(wild2);
		}
		
		model.setHypervisorVmAlert(temp2);
		
		
		return model;
	}

	@Override
	public AlertDto getListOfAlertUnResolved(String edgeClientId) {
		
		    AlertDto model=new AlertDto();
			
			model.setHypervisorId(edgeClientId);
			
			ArrayList<EdgeHypervisorAlertModel> temp=new ArrayList<>();
			List<EdgeHypervisorAlertModel> list=alertHypervisorRepository.getAlertUnResolved(edgeClientId);
			for (EdgeHypervisorAlertModel edgeHypervisorAlert : list) {
				
				EdgeHypervisorAlertModel wild=new EdgeHypervisorAlertModel();
				wild.setAlert(edgeHypervisorAlert.getAlert());
				wild.setAlertId(edgeHypervisorAlert.getAlertId());
				wild.setDate(edgeHypervisorAlert.getDate());
				wild.setType(edgeHypervisorAlert.getType());
				wild.setEdgeClientId(edgeHypervisorAlert.getEdgeClientId());
				wild.setStatus(edgeHypervisorAlert.getStatus());
				
				temp.add(wild);
			}
			
			model.setHypervisorAlert(temp);
			
			ArrayList<EdgeVmAlertModel> temp2=new ArrayList<>();
			List<EdgeVmAlertModel> list2=alertVmRepository.getVmAlertUnResolved(edgeClientId);
			
			for (EdgeVmAlertModel edgeVmAlert : list2) {
				
				EdgeVmAlertModel wild2=new EdgeVmAlertModel();
				wild2.setAlert(edgeVmAlert.getAlert());
				wild2.setAlertId(edgeVmAlert.getAlertId());
				wild2.setDate(edgeVmAlert.getDate());
				wild2.setEdgeClientId(edgeVmAlert.getEdgeClientId());
				wild2.setVmId(edgeVmAlert.getVmId());
				wild2.setVmName(edgeVmAlert.getVmName());
				wild2.setType(edgeVmAlert.getType());
				wild2.setStatus(edgeVmAlert.getStatus());
				
				temp2.add(wild2);
			}
			
			model.setHypervisorVmAlert(temp2);
			
			
			return model;
	}

	@Override
	public String allAlertResolved(String edgeClientId) {

		alertHypervisorRepository.updateAllResolved(edgeClientId);
		
		return "All Alert Resolved";
	}

	@Override
	public String allVmAlertResolved(String edgeClientId) {

		alertVmRepository.updateVmData(edgeClientId);
		
		return "All VM Alert Resolved";
	}
	
}

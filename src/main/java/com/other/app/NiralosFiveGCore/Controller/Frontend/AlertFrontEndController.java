package com.other.app.NiralosFiveGCore.Controller.Frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Backend.AlertDataCollectorService;
import com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Frontend.AlertFrontEndService;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.AlarmDashboardDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.AlarmModelFrontendDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.SiteDto;
import com.other.app.NiralosFiveGCore.Dto.AlarmDto.Frontend.WidgetApi;

@RequestMapping("/v2/alarm_manager")
@CrossOrigin
@RestController
public class AlertFrontEndController {

		@Autowired
		AlertFrontEndService restFrontEndService;
		@Autowired
		AlertDataCollectorService alertDataCollectorService;
	
		// Overview Page - Alarm details by id
		@GetMapping("/dashboard/alarms_detailsby/id={id}")
		public ArrayList<AlarmModelFrontendDto> getDetailsofAlarmsById(@PathVariable("id") String id) {
		alertDataCollectorService.AlertDataParse();
		return restFrontEndService.DetailsofAlarmsById(Integer.parseInt(id));
		}

		// Widget Page api - count and critical alarms list
		@GetMapping("/widget/alarms")
		public WidgetApi widgetData() {
		alertDataCollectorService.AlertDataParse();
		return new WidgetApi(restFrontEndService.listOfCriticalAlarms(), restFrontEndService.CountLevelsofAlarms());
		}

		// Overview Page - Alarm details by date time
		@GetMapping("/dashboard/alarms_filterby/startdatetime={startdate}/enddatetime={enddate}")
		public ArrayList<AlarmModelFrontendDto> alarmDataBydate(@PathVariable("startdate") String startdate,
			@PathVariable("enddate") String enddate) {
		alertDataCollectorService.AlertDataParse();
		return restFrontEndService.alarmsDetailsBydate(startdate, enddate);
		}

		@PostMapping("/dashboard/alarms/id={id}/status={status}")
		public void alarmstatusUpdate(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
		alertDataCollectorService.AlertDataParse();
		restFrontEndService.alarmstatusUpdate(id, status);
		}
		// not working
		@GetMapping("/alertfilter/level={level}/status={status}")
		public AlarmDashboardDto resolvedorunresolved(@PathVariable("level") String level,
			@PathVariable("status") Integer status) {
		alertDataCollectorService.AlertDataParse();
		restFrontEndService.Zero();
		return new AlarmDashboardDto(restFrontEndService.listOfAlarms(),
				restFrontEndService.alertFilter(level, status));
		}

		@GetMapping("/get_list_of_site")
		public List<SiteDto> getListOfSite() {
		alertDataCollectorService.AlertDataParse();
		return restFrontEndService.getListOfSite();
		}

		@GetMapping("/countCritical")
		public long countCriticalAlarms() {
		return restFrontEndService.countCriticalAlarms();

		}
	 	@DeleteMapping("/delete/all")
	 	public  void deleteAllData() {
		
	     restFrontEndService.deleteAllData();
	    }
	 	@GetMapping("/update-status")
	    public void updateAllStatusTo1() {
		 restFrontEndService.updateAllStatusTo1();
	    }

}

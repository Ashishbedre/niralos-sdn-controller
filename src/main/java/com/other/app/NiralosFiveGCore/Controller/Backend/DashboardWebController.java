package com.other.app.NiralosFiveGCore.Controller.Backend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.other.app.NiralosFiveGCore.BackendServices.AlertManager.Backend.Impl.AlertDataCollectorServiceImpl;
import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
import com.other.app.NiralosFiveGCore.model.InternalDataModel;

@Controller
public class DashboardWebController {

	
	@Autowired
	InternalDataService internalDataService;
	
	final Logger logger = LoggerFactory.getLogger(DashboardWebController.class);
	
//	@GetMapping("")
//	public String index(Model model) {
//		return "redirect:index";
//	}
//	
//	@GetMapping("/index")
//	public String configure(Model model) {
//		model.addAttribute("internalData", internalDataService.getInternalDetails());
//		logger.info(internalDataService.getInternalDetails().getNiralControllerClientId());
//		return "index";
//	}
	
	
	@GetMapping("/settings")
	public String settings(Model model) {
		InternalDataModel internalData = new InternalDataModel();
		model.addAttribute("internalData", internalData);
		return "settings";
	}

	@PostMapping("/settings")
	public String saveSettings(@ModelAttribute("internalData") InternalDataModel internalData) {
		internalDataService.saveInternalDataModel(internalData);
		return "redirect:index";
	}
	
	


}

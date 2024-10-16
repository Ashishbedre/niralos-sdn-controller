package com.other.app.NiralosFiveGCore.Controller.Frontend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/health")
@CrossOrigin
public class HealthCheck {
	
	@GetMapping("/localsdn")
	public Boolean localSDNHealthChecker() {
		return true;
	}

}

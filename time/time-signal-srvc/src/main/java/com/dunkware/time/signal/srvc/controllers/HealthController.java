package com.dunkware.time.signal.srvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping(path="/health")
	public String health() { 
		return "UP";
	}
}

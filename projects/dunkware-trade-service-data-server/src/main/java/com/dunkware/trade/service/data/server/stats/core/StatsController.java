package com.dunkware.trade.service.data.server.stats.core;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

	
	
	@PostMapping(path = "/stats/session/add")
	public void sessionStats() { 
		
	}
}

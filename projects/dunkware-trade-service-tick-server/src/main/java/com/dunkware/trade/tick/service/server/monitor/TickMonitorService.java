package com.dunkware.trade.tick.service.server.monitor;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TickMonitorService {

	@PostConstruct
	public void load() { 
		System.out.println("load monitor service");
	}
}

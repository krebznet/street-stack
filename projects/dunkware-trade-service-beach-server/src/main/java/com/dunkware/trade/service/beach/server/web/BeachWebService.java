package com.dunkware.trade.service.beach.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Adapts to the angular world 
 * @author duncankrebs
 *
 */
@RestController
public class BeachWebService {

	@PostMapping(path = "/trade/web/broker/add")
	public String addBroker(Object webBroker) { 
		return null;
	}
	
	@PostMapping(path = "/trade/web/bot/save")
	public String saveBot(Object bot) { 
		return null;
	}
	
	@PostMapping(path = "/trade/web/bot/add")
	public String addBot(Object bot) { 
		return null;
	}
	
	@GetMapping(path = "/trade/web/bot/start")
	public void startBot(Object botId) { 
		
	}
	
	@GetMapping(path = "/trade/web/bot/stop")
	public void stopBot(Object botId) { 
		
	}
	
	@GetMapping(path = "/trade/web/bot/delete")
	public void deleteBot(Object botId) { 
		
	}
	
	// 
}

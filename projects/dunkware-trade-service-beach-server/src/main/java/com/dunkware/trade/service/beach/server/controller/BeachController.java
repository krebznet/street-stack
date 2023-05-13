package com.dunkware.trade.service.beach.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.protocol.broker.BeachBrokerAdd;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@RestController
public class BeachController {

	@Autowired()
	private BeachService beachService; 
	
	@PostMapping(path = "/beach/controller/broker/add")
	public void addBroker(@RequestBody() BeachBrokerAdd input) throws Exception { 
		beachService.addBroker(input);
	}
	
	@GetMapping(path = "/beach/controller/play/start")
	public void addPlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@GetMapping(path = "/beach/controller/play/stop")
	public void stopPlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@GetMapping(path = "/beach/controller/play/delete")
	public void deletePlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@GetMapping(path = "/beach/controller/play/create")
	public @ResponseBody() long createPlay(@RequestBody() String play, @RequestParam() int accountId) { 
		return 1;
	}
	
	@GetMapping(path = "/beach/controller/play/update")
	public void updatePlay(@RequestBody() String play, @RequestParam() int accountId) { 
		
	}
	
}

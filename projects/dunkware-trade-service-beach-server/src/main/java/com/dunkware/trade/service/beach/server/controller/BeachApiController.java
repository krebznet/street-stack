	package com.dunkware.trade.service.beach.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.runtime.BeachService;


@RestController
public class BeachApiController {

	@Autowired()	
	private BeachService beachService; 

	
	@GetMapping(path = "/trade/v1/echo")
	public @ResponseBody() String echo(@RequestParam String echo) { 
		return echo;
	}

	@PostMapping(path = "/trade/v1/broker/add")
	public void addBroker(@RequestBody() AddBrokerReq req)   { 
		try {
		//	beachService.getbr
			beachService.addBroker(req);
			
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Exception adding broker " + e.getMessage(), e);
		}
	}
	
	
	@PostMapping(path = "/trade/v1/system/create")
	public void createSystem() { 
		// create a system 
	}
	
	
	@PostMapping(path = "/trade/v1/trade/submit")
	public void submitTrade() { 
		// submit a trade 
	}
	

	
	
	
	
	
}

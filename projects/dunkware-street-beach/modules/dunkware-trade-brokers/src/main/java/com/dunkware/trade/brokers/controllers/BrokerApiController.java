package com.dunkware.trade.brokers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BrokerApiController {


	@PostMapping(path = "/trade/v1/brokers/add")
	public void addBroker(@RequestBody() Object req) throws Exception { 
		try {
		//	beachService.getbr
		//	beachService.addBroker(req);
			
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Exception adding broker " + e.getMessage(), e);
		}
	}
	
}

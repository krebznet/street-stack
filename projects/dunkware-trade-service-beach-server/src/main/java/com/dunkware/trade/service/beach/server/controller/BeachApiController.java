	package com.dunkware.trade.service.beach.server.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachService;
import com.dunkware.trade.service.beach.server.runtime.BeachSystem;


@RestController
public class BeachApiController {
	
	@Autowired()	
	private BeachService beachService; 

	@PostConstruct
	public void init() { 
		System.out.println("fuckin change");
	}
	
	@GetMapping(path = "/trade/v1/echo")
	public @ResponseBody() String echo(@RequestParam String echo) { 
		return echo;
	}

	@PostMapping(path = "/trade/v1/broker/add")
	public void addBroker(@RequestBody() AddBrokerReq req) throws Exception  { 
		try {
		//	beachService.getbr
			beachService.addBroker(req);
			
		} catch (Exception e) {
			throw new UserException("Exception adding broker " + e.toString());
		}
	}
	
	
	@PostMapping(path = "/trade/v1/system/add")
	public void createSystem(@RequestBody() BeachSystemModel model, @RequestParam() long accountId) { 
		try {
			BeachAccount account = null;
			try {
				account = beachService.getAccount(accountId);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			account.addSystem(model);
			System.out.println(accountId);
			System.out.println(DJson.serialize(model));
				
		} catch (Exception e) {
			
		//	e.printStackTrace();
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Exception adding broker " + e.getMessage(), e);
		}
	}
	
	
	@PostMapping(path = "/trade/v1/system/trade/submit")
	public @ResponseBody() String submitTrade(@RequestBody() BeachTradeModel model, @RequestParam() long systemId) { 
		BeachSystem system = null;
		try {
			system = beachService.getSystem(systemId);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "System ID not found " + e.getMessage(), e);
			// TODO: handle exception
		}
		try {
			system.submitTrade(model);		
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Submit trade Exception " + e.getMessage(), e);
			// TODO: handle exception
		}
	
		
		
			return "TradeIDent"; 
			
	}
	

	
	
	
	
	
}

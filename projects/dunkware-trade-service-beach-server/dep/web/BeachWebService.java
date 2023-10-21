package com.dunkware.trade.service.beach.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.protocol.controller.BeachBotAddReq;
import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddReq;
import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddResp;
import com.dunkware.trade.service.beach.protocol.spec.BeachBotState;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachBot;
import com.dunkware.trade.service.beach.server.runtime.BeachBroker;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@RestController()
public class BeachWebService {
	
	@Autowired
	private BeachService service;
	
	@PostMapping(path = "/beach/broker/add")
	public @ResponseBody() BeachBrokerAddResp addBroker(@RequestBody() BeachBrokerAddReq req) { 
		BeachBrokerAddResp resp = new BeachBrokerAddResp();
		try {
			service.addBroker(req.getBroker());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setError(e.toString());
			resp.setCode("ERROR");
			return resp;
		}
	}
	
	@PostMapping(path = "/beach/bot/add") 
	public void addBot(@RequestBody() BeachBotAddReq req) throws Exception { 
		BeachBroker broker = null;
		try {
			broker = service.getBroker(req.getBroker());			
		} catch (Exception e) {
			throw e;
		}
		BeachAccount account = null;
		try {
			account = (BeachAccount)broker.getAccount(req.getAccount());
		} catch (Exception e) {
			throw e;
		}
		try {
			account.createBot(req.getModel(), req.getName());
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping(path = "beach/bot/start")
	public void startBot(@RequestParam() String bot, @RequestParam() String broker, @RequestParam() String account) throws Exception { 
		try {
			BeachAccount beachAccount = service.getAccount(broker,account);
			BeachBot beachBot = beachAccount.getBot(bot);
			if(beachBot.getState() != BeachBotState.Stopped && beachBot.getState() != BeachBotState.Exception) { 
				throw new Exception("beach bot invalid state to start " + beachBot.getState());
			}
			beachBot.start();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@GetMapping(path = "beach/bot/stop")
	public void stopBot(@RequestParam() String bot) throws Exception { 
		
	}
	
	

}

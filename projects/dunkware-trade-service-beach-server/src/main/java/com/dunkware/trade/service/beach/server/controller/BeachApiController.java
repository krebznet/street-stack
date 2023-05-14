package com.dunkware.trade.service.beach.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerResp;
import com.dunkware.trade.service.beach.protocol.play.AddPlayReq;
import com.dunkware.trade.service.beach.protocol.play.AddPlayResp;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@RestController
public class BeachApiController {

	@Autowired()
	private BeachService beachService; 
	
	@PostMapping(path = "/trade/controller/broker/add")
	public @ResponseBody() AddBrokerResp addBroker(@RequestBody() AddBrokerReq input) throws Exception { 
		AddBrokerResp resp = new AddBrokerResp();
		try {
			beachService.addBroker(input);
			resp.setOk(true);
			return resp;
		} catch (Exception e) {
			resp.setOk(false);
			resp.setError(e.toString());
			return resp;
		}
		
	}
	
	@GetMapping(path = "/trade/controller/play/start")
	public void addPlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@GetMapping(path = "/trade/controller/play/stop")
	public void stopPlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@GetMapping(path = "/trade/controller/play/delete")
	public void deletePlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@PostMapping(path = "/trade/controller/play/create")
	public @ResponseBody() AddPlayResp createPlay(@RequestBody() AddPlayReq req) { 
		AddPlayResp resp = new AddPlayResp();
		BeachAccount account = null;
		try {
			 account = beachService.getAccount(req.getAccountId());
			 BeachPlay play = account.createPlay(req.getPlay(), req.getPlay().getName());
			 resp.setPlayId(play.getId());
			 resp.setOk(true);
			 return resp;
		} catch (Exception e) {
			resp.setOk(false);
			resp.setError(e.toString());
			return resp;
		}
		
	}
	
	@GetMapping(path = "/trade/controller/play/update")
	public void updatePlay(@RequestBody() String play, @RequestParam() int accountId) { 
		
	}
	
}

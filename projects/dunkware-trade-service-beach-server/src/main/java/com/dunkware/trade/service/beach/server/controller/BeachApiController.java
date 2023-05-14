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
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerResp;
import com.dunkware.trade.service.beach.protocol.play.AddPlayResp;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.trade.service.beach.server.runtime.BeachPlayStatus;
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
	public void startPlay(@RequestParam() long playId) throws Exception {
		BeachPlay play = null;
		try {
			play = beachService.getPlay(playId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
		}
		if(play.getStatus() != BeachPlayStatus.Stopped) { 
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Play in status " + play.getStatus().name() + " cannot be started");
		}
		try {
			play.start();	
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
		}
		
	}
	
	@GetMapping(path = "/trade/controller/play/stop")
	public void stopPlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@GetMapping(path = "/trade/controller/play/delete")
	public void deletePlay(@RequestParam() int id) throws Exception { 
		
	}
	
	@PostMapping(path = "/trade/controller/play/create")
	public @ResponseBody() long createPlay(@RequestBody() Play play, @RequestParam() long accountId) { 
		BeachAccount account = null;
		try {
			 account = beachService.getAccount(accountId);
			 BeachPlay beachPlay = account.createPlay(play, play.getName());
			 return beachPlay.getId();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.toString());
			
		}
		
	}
	
	@GetMapping(path = "/trade/controller/play/update")
	public void updatePlay(@RequestBody() String play, @RequestParam() int accountId) { 
		
	}
	
}

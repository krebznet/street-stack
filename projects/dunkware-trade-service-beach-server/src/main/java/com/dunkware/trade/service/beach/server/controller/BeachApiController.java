	package com.dunkware.trade.service.beach.server.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
import com.dunkware.trade.service.beach.server.runtime.BeachPlay;
import com.dunkware.trade.service.beach.server.runtime.BeachPlayStatus;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@RestController
public class BeachApiController {

	@Autowired()	
	private BeachService beachService; 


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

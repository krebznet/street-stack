package com.dunkware.trade.service.beach.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddReq;
import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddResp;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@RestController()
public class BeachWebService {
	
	@Autowired
	private BeachService service;
	
	@PostMapping(path = "/beach/controller/broker/add")
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
	
	@PostMapping(path = "/beach/controller/bot/add") 
	public void addBot() { 
		// # 2 -- get the account and then see it insert bot 
		// 	   -- then get it to create a runtime bot with status stopped
	}
	
	@GetMapping(path = "beach/controller/bot/start")
	public void startBot() { 
		// get the bot and start it 
		// that is where the magic will need to take place 
	}
	
	@GetMapping(path = "beach/controller/bot/stop")
	public void stopBot() { 
		
	}
	
	

}

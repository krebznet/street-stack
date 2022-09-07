package com.dunkware.trade.service.beach.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.service.beach.server.trade.BeachTradeService;
import com.dunkware.trade.service.beach.server.web.util.BeachWebConverter;

import comm.dunkware.trade.service.beach.web.model.WebBroker;

/**
 * Adapts to the angular world
 * 
 * @author duncankrebs
 *
 */
@RestController
public class BeachWebService {

	@Autowired
	BeachTradeService tradeService;

	

	@PostMapping(path = "/trade/web/broker/add")
	public void addBroker(@RequestBody WebBroker broker) throws Exception {
		BrokerType type = null;
		try {
			broker.setType("interactive");
			type = BeachWebConverter.toServerBrokerType(broker);
			
		} catch (Exception e) {
			throw e;
		}

		try {
			tradeService.addBroker(type);
		} catch (Exception e) {
			throw new Exception("Exception creating broker " + e.toString());
		}
	}
	
	// accounts grid stream 
	
	

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

package com.dunkware.trade.service.beach.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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
	
	/**
	 * Returns serialized array of com.dunkware.trade.service.beach.web.model.WebEvent
	 * @return
	 */
	@GetMapping(path = "/trade/web/street/events")
	public ResponseEntity<StreamingResponseBody> getStreetEvents() {
		return null;
	
	

	}
	
	/**
	 * Returns streaming array of all WebAccount model objects in array, we will refresh entire grid on each streaming
	 * response. 
	 * @return
	 */
	@GetMapping(path = "/trade/web/street/accounts")
	public ResponseEntity<StreamingResponseBody> getStreetAccounts() { 
		return null;
	}

	
	/**
	 * Returns serialized array of WebOrder objects. 
	 * @return
	 */
	@GetMapping(path = "/trade/web/street/orders/")
	public ResponseEntity<StreamingResponseBody> getStreetOrders() { 
		return null;
	}

	
	
	@GetMapping(path = "/trade/web/street/systems")
	public ResponseEntity<StreamingResponseBody> getStreetSystems() { 
		return null;
	}

	
	
	@GetMapping(path = "/trade/web/account/systems")
	public ResponseEntity<StreamingResponseBody> getAccountSystems(@RequestParam int accountId) { 
		return null;
	}

	/**
	 * returns all the trades for an account 
	 * @return
	 */
	@GetMapping(path = "/trade/web/account/trades")
	public ResponseEntity<StreamingResponseBody> getAccountTrades(@RequestParam int accountId) { 
		return null;
	}
	
	
	/**
	 * returns all the trades for an account 
	 * @return
	 */
	@GetMapping(path = "/trade/web/system/trades")
	public ResponseEntity<StreamingResponseBody> getSystemTrades(@RequestParam int systemId) { 
		return null;
	}
	
	
	
	//
}

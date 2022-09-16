package com.dunkware.trade.service.beach.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.service.beach.server.resources.BeachResourceService;
import com.dunkware.trade.service.beach.server.trade.BeachService;
import com.dunkware.trade.service.beach.server.web.util.BeachWebConverter;

import comm.dunkware.trade.service.beach.web.model.BeachWebBroker;
import comm.dunkware.trade.service.beach.web.model.WebScope;

/**
 * Adapts to the angular world
 * 
 * @author duncankrebs
 *
 */
@RestController
public class BeachWebController {

	@Autowired
	private BeachService tradeService;
	
	
	@Autowired
	private BeachResourceService resourceService; 

	

	@PostMapping(path = "/trade/web/dash/broker/add")
	public void addBroker(@RequestBody BeachWebBroker broker) throws Exception {
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
	@GetMapping(path = "/trade/web/dash/stream/events")
	public ResponseEntity<StreamingResponseBody> getDashEvents() {
		
		return null;
	
	}
	
	/**
	 * Returns streaming array of all WebAccount model objects in array, we will refresh entire grid on each streaming
	 * response. 
	 * @return
	 */
	@GetMapping(path = "/trade/web/dash/stream/accounts")
	public ResponseEntity<StreamingResponseBody> getDashAccounts() { 
		return null;
	}

	
	/**
	 * Returns serialized array of WebOrder objects. 
	 * @return
	 */
	@GetMapping(path = "/trade/web/dash/stream/orders/")
	public ResponseEntity<StreamingResponseBody> getDashOrders() { 
		return null;
	}

	
	
	@GetMapping(path = "/trade/web/dash/stream/systems")
	public ResponseEntity<StreamingResponseBody> getDashSystems() { 
		return null;
	}

	
	@PostMapping(path = "trade/web/dash/scope/add")
	public void addScope(@RequestBody() WebScope scope) { 
		// if account scope --> what if account is already scoped, do we add it to the existing ones 
		// yes -> it adds a scope matcher on accounts 
		// adds a account matcher on accounts/systems/trades/events
		// 
		// if system scope add 
		// adds a system matcher on systems/trades/orders/events
		
		// if system scope remove -- if no more scopes
		
		// Level | Target 
		// Account DLK Paper 
		// System Momentum 1
		// Trade 43
		//Scope DLK Paper 
		// System Scope Momentum 1 
	}
	
	@PostMapping(path = "trade/web/dash/scope/reset")
	public void resetScope() { 
		
	}
	
	@PostMapping(path = "trade/web/dash/scope/remove")
	public void removeScope(@RequestBody() WebScope scope) { 
		
	}
	
	
	
	
	//
}

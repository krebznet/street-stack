package com.dunkware.trade.service.beach.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.lib.model.bot.model.TradeBotType;
import com.dunkware.trade.sdk.lib.model.bot.web.WebTradeBot;
import com.dunkware.trade.service.beach.server.resources.BeachResourceService;
import com.dunkware.trade.service.beach.server.trade.BeachService;
import com.dunkware.trade.service.beach.server.web.util.BeachWebConverter;

import comm.dunkware.trade.service.beach.web.model.BeachWebBroker;

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
	 * Returns streaming array of all WebAccount model objects in array, we will refresh entire grid on each streaming
	 * response. 
	 * @return
	 */
	@GetMapping(path = "/trade/web/dash/accounts/snapshot")
	public ResponseEntity<StreamingResponseBody> getDashAccounts() { 
		return null;
	}

	/**
	 * Returns serialized array of WebOrder objects. 
	 * @return
	 */
	@GetMapping(path = "/trade/web/dash/orders/snapshot")
	public ResponseEntity<StreamingResponseBody> getDashOrders() { 
		return null;
	}

	
	/**
	 * System End Points 
	 * @return
	 */
	
	@GetMapping(path = "/trade/web/dash/systems/snapshot")
	public ResponseEntity<StreamingResponseBody> getDashSystems() { 
		return null;
	}
	
	
	@PostMapping(path = "/trade/web/dash/systems/add")
	public void addSystem(@RequestBody String json) throws Exception {
		WebTradeBot bot = null;
		try {
			bot = DJson.getObjectMapper().readValue(json, WebTradeBot.class);
			
		} catch (Exception e) {
			throw new Exception("Invalid JSON could not deserialize into server-side model " + e.toString());
		}
		TradeBotType nativeBotType = new TradeBotType(); 
		nativeBotType.setWrapper(bot);
		try {
			resourceService.insertSystem(nativeBotType.getIdentifier(), nativeBotType);
		} catch (Exception e) {
			throw new Exception("Exception saving bot resource " + e.toString());
		}
	}
	
	
	@PostMapping(path = "/trade/web/dash/systems/save")
	public void saveSystem(@RequestBody String json) { 
		
	}

	
	@PostMapping(path = "/trade/web/dash/systems/delete")
	public void deleteSystem(@RequestBody Integer id) { 
		
	}

	@GetMapping(path = "/trade/web/dash/systems/start")
	public void startSystem(@RequestParam int id) { 
		
	}

	@GetMapping(path = "/trade/web/dash/systems/stop")
	public void stopSystem(@RequestParam int id) {
		
	}

	@GetMapping(path = "/trade/web/dash/trades/snapshot")
	public ResponseEntity<StreamingResponseBody> getDashTrades() { 
		return null;
	}
	
	
	
	
	
	
	
	
}

package com.dunkware.trade.service.beach.server.trade.core;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.protocol.trade.BeachBrokerAddReq;
import com.dunkware.trade.service.beach.protocol.trade.BeachBrokerAddResp;
import com.dunkware.trade.service.beach.protocol.trade.BeachOrderStatusResp;
import com.dunkware.trade.service.beach.protocol.trade.BeachOrderSubmitReq;
import com.dunkware.trade.service.beach.protocol.trade.BeachOrderSubmitResp;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolAddReq;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolAddResp;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolTradeReq;
import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolTradeResp;
import com.dunkware.trade.service.beach.protocol.trade.spec.BeachOrderSpec;
import com.dunkware.trade.service.beach.server.trade.BeachAccount;
import com.dunkware.trade.service.beach.server.trade.BeachOrder;
import com.dunkware.trade.service.beach.server.trade.BeachPool;
import com.dunkware.trade.service.beach.server.trade.BeachTrade;
import com.dunkware.trade.service.beach.server.trade.BeachTradeService;

@RestController
@Profile("TradeService")
public class BeachTradeWebService {
	
	
	@Autowired
	private BeachTradeService tradeService; 

	@PostConstruct
	public void init() { 
		System.out.println("ghere");
	}
	/**
	 * Broker Management 
	 * @param req
	 * @return
	 */
	@PostMapping(path = "/trade/broker/add")
	public @ResponseBody() BeachBrokerAddResp addBroker(@RequestBody() BeachBrokerAddReq req) { 
		BeachBrokerAddResp resp = new BeachBrokerAddResp();
		try {
			tradeService.addBroker(req.getBroker());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setError(e.toString());
			resp.setCode("ERROR");
			return resp;
		}
	}
	
	@Transactional
	@PostMapping(path = "/order/submit")
	public @ResponseBody() BeachOrderSubmitResp submitOrder(@RequestBody() BeachOrderSubmitReq req) { 
		BeachOrderSubmitResp resp = new BeachOrderSubmitResp();
		if(tradeService.brokerExists(req.getBroker()) == false) { 
			resp.setCode("ERROR");
			resp.setError("Beach Broker " + req.getBroker() + " Not Found");
			return resp;
		}
		BeachAccount account = null;
		try {
			account = tradeService.getAccount(req.getBroker(), req.getAccount());	
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError("Get Account Exception " + req.getBroker() + " " + e.toString());
			return resp;
		}
		BeachOrder order = null;
		try {
			order = (BeachOrder)account.createOrder(req.getOrder());
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError("Create Order Failed With Exception " + e.toString());
			return resp;
		}
		try {
			order.send();
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError("Exception Sending Order " + e.toString());
		}
		resp.setCode("SUCCESS");
		BeachOrderSpec beachOrderSpec = new BeachOrderSpec();
		beachOrderSpec.setAccount(account.getIdentifier());
		beachOrderSpec.setBroker(account.getBroker().getIdentifier());
		beachOrderSpec.setOrder(order.getSpec());
		resp.setOrder(beachOrderSpec);
		return resp;
	}
	
	@RequestMapping(path = "/order/status")
	public @ResponseBody() BeachOrderStatusResp orderStatus(@RequestParam() int orderId) { 
		BeachOrderStatusResp resp = new BeachOrderStatusResp();
		// order details ? 
		
		return resp;
	}
	
	@PostMapping(path = "/trade/submit")
	public @ResponseBody BeachPoolTradeResp submitTrade(@RequestBody BeachPoolTradeReq req) {
		BeachPoolTradeResp resp = new BeachPoolTradeResp();
		try {
			BeachPool pool = tradeService.getPool(req.getPool());
			BeachTrade trade = pool.createTrade(req.getTrade());
			trade.open();
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			if(e.getMessage() == null) { 
				resp.setError(e.toString());
			} else { 
				resp.setError(e.getMessage());				
			}

			return resp;
		}
	}
	
	@PostMapping(path = "/pool/create")
	public @ResponseBody() BeachPoolAddResp createPool(@RequestBody() BeachPoolAddReq req) { 
		BeachPoolAddResp resp = new BeachPoolAddResp();
		try {
			tradeService.createPool(req.getBroker(), req.getAccount(), req.getName());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
	}
	

	// pool/info/status
	// pool/info/trades/open
	// pool/info/systems/running
	// pool/trade/submit
	// pool/trade/status
	// pool/system/add
	// pool/system/stop
	// pool/system/start
	// pool/system/drop 
	
}

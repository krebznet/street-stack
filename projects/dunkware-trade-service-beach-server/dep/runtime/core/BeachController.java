package com.dunkware.trade.service.beach.server.runtime.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddReq;
import com.dunkware.trade.service.beach.protocol.controller.BeachBrokerAddResp;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@RestController
@Profile("TradeService")
public class BeachController {
	
	
	@Autowired
	private BeachService tradeService; 

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
	
	/*
	 * @Transactional
	 * 
	 * @PostMapping(path = "/order/submit") public @ResponseBody()
	 * BeachOrderSubmitResp submitOrder(@RequestBody() BeachOrderSubmitReq req) {
	 * BeachOrderSubmitResp resp = new BeachOrderSubmitResp();
	 * if(tradeService.brokerExists(req.getBroker()) == false) {
	 * resp.setCode("ERROR"); resp.setError("Beach Broker " + req.getBroker() +
	 * " Not Found"); return resp; } BeachAccount account = null; try { account =
	 * tradeService.getAccount(req.getBroker(), req.getAccount()); } catch
	 * (Exception e) { resp.setCode("ERROR"); resp.setError("Get Account Exception "
	 * + req.getBroker() + " " + e.toString()); return resp; } BeachOrder order =
	 * null; try { order = (BeachOrder)account.createOrder(req.getOrder()); } catch
	 * (Exception e) { resp.setCode("ERROR");
	 * resp.setError("Create Order Failed With Exception " + e.toString()); return
	 * resp; } try { order.send(); } catch (Exception e) { resp.setCode("ERROR");
	 * resp.setError("Exception Sending Order " + e.toString()); }
	 * resp.setCode("SUCCESS"); BeachOrderSpec beachOrderSpec = new
	 * BeachOrderSpec(); beachOrderSpec.setAccount(account.getIdentifier());
	 * beachOrderSpec.setBroker(account.getBroker().getIdentifier());
	 * beachOrderSpec.setOrder(order.getSpec()); resp.setOrder(beachOrderSpec);
	 * return resp; }
	 */
	
	
	
	/*
	 * @PostMapping(path = "/trade/system/add") public @ResponseBody()
	 * BeachPoolAddResp createPool(@RequestBody() BeachPoolAddReq req) {
	 * BeachPoolAddResp resp = new BeachPoolAddResp(); try {
	 * tradeService.createPool(req.getBroker(), req.getAccount(), req.getName());
	 * resp.setCode("SUCCESS"); return resp; } catch (Exception e) {
	 * resp.setCode("ERROR"); resp.setError(e.toString()); return resp; } }
	 */

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

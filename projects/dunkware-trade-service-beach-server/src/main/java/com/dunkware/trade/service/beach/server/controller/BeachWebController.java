package com.dunkware.trade.service.beach.server.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.runtime.BeachAccountBean;
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;
import com.dunkware.trade.service.beach.server.runtime.BeachService;



@RestController
public class BeachWebController {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachService beachService;

	@Autowired
	private BeachRuntime runtime;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping(path = "/trade/v1/dash/static/brokers")
	public @ResponseBody() List<BeachBrokerBean> getStaticBrokers() { 
		
		try {
			beachService.getBrokerBeans().getReadWriteLock().readLock().lock();
		List<BeachBrokerBean> results =  new ArrayList<BeachBrokerBean>(); //
		results.addAll(beachService.getBrokerBeans());
			beachService.getBrokerBeans().getReadWriteLock().readLock().unlock();
			return results;
		} catch (Exception e) {
			throw new ResponseStatusException(  
			           HttpStatus.BAD_REQUEST, "Get brokers " + " error " + e.getMessage());
			// TODO: handle exception
		}
		
	}
	
	

	@GetMapping(path = "/trade/v1/dash/static/accounts")
	public @ResponseBody() List<BeachAccountBean> geStaticAccounts() { 
		
		try {
			beachService.getAccountBeans().getReadWriteLock().readLock().lock();
		List<BeachAccountBean> results =  new ArrayList<BeachAccountBean>(); //
		results.addAll(beachService.getAccountBeans());
			beachService.getAccountBeans().getReadWriteLock().readLock().unlock();
			return results;
		} catch (Exception e) {
			throw new ResponseStatusException(  
			           HttpStatus.BAD_REQUEST, "Get brokers " + " error " + e.getMessage());
			// TODO: handle exception
		}
		
	}

	
	
	@GetMapping(path = "/trade/v1/dash/core/brokers",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> brokersStream() {
		
		
		throw new ResponseStatusException(  
		           HttpStatus.BAD_REQUEST, " not implemented");
	}
	
	
	@GetMapping(path = "/trade/v1/dash/core/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> accountsStream() throws Exception {
		throw new ResponseStatusException(  
		           HttpStatus.BAD_REQUEST, " not implemented");
		
		
	}
	
	@GetMapping(path = "/trade/v1/dash/account/trades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> accountTrades(@RequestParam int id) {
		throw new ResponseStatusException(  
		           HttpStatus.BAD_REQUEST, " not implemented");
		
	}
	
	@GetMapping(path = "/trade/v1/dash/account/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> accountOrders(@RequestParam int id) {
		throw new ResponseStatusException(  
		           HttpStatus.BAD_REQUEST, " not implemented");
		
	}

}

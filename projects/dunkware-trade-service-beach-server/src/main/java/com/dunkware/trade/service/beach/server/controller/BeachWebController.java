package com.dunkware.trade.service.beach.server.controller;


import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.runtime.BeachAccount;
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


	@GetMapping(path = "/trade/v1/dash/core/brokers",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> brokersStream() {
		
		StreamingResponseBody stream = out -> {

			GlazedGridWrapper wrapper = new GlazedGridWrapper(beachService.getBrokerBeans(), runtime.getExecutor(), "getId");
			wrapper.start();
			while (true) {
				try {
					
					
					DataGridUpdate update = wrapper.updateQueue().poll(1, TimeUnit.SECONDS);
					if (update == null) {
						out.flush();
						continue;
					}
					
					logger.info("send " + DJson.serialize(update));
					if (update != null) {
						out.write(DJson.serialize(Arrays.asList(update)).getBytes());
						out.flush();
						
						
					}
				} catch (Exception e) {
					
					out.close();
					wrapper.dispose();
					logger.error("closing broker stream");
					return;
				}

			}

		};
		logger.info("steaming response {} ", stream);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stream);
	}
	
	
	@GetMapping(path = "/trade/v1/dash/core/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> accountsStream() {

		GlazedDataGrid grid = new GlazedDataGrid(beachService.getAccountBeans(), runtime.getExecutor(), "getId");

		StreamingResponseBody stream = out -> {
			grid.start();
			while (true) {
				try {
					DataGridUpdate update = grid.pollUpdate(1, TimeUnit.SECONDS);
					if (update == null) {
						continue;
					}
					logger.info("send " + DJson.serialize(update));
					if (update != null) {
						out.write(DJson.serialize(Arrays.asList(update)).getBytes());
						out.flush();
					}
				} catch (Exception e) {
					grid.dispose();
					logger.error("closing download test");
					return;
				}

			}

		};
		logger.info("steaming response {} ", stream);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stream);
	}
	
	@GetMapping(path = "/trade/v1/dash/account/trades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> accountTrades(@RequestParam int id) {
		BeachAccount account = null;
		try {
			account = beachService.getAccount(id);
			if(account == null) { 
				throw new ResponseStatusException(  
				           HttpStatus.BAD_REQUEST, "Account ID " + id + " Not Found");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(  
			           HttpStatus.BAD_REQUEST, "Fetch Account ID " + id + " error " + e.getMessage());
		}
		
		GlazedDataGrid grid = new GlazedDataGrid(account.getTradeBeans(), runtime.getExecutor(), "getId");

		StreamingResponseBody stream = out -> {
			grid.start();
			while (true) {
				try {
					DataGridUpdate update = grid.pollUpdate(1, TimeUnit.SECONDS);
					if (update == null) {
						continue;
					}
					logger.info("send " + DJson.serialize(update));
					if (update != null) {
						out.write(DJson.serialize(Arrays.asList(update)).getBytes());
						out.flush();
					}
				} catch (Exception e) {
					grid.dispose();
					logger.error("closing download test");
					return;
				}

			}
		};
		
		logger.info("steaming response {} ", stream);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stream);
	}
	
	@GetMapping(path = "/trade/v1/dash/account/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> accountOrders(@RequestParam int id) {
		BeachAccount account = null;
		try {
			account = beachService.getAccount(id);
			if(account == null) { 
				throw new ResponseStatusException(  
				           HttpStatus.BAD_REQUEST, "Account ID " + id + " Not Found");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(  
			           HttpStatus.BAD_REQUEST, "Fetch Account ID " + id + " error " + e.getMessage());
		}
		
		GlazedDataGrid grid = new GlazedDataGrid(account.getOrderBeans(), runtime.getExecutor(), "getId");

		StreamingResponseBody stream = out -> {
			grid.start();
			while (true) {
				try {
					DataGridUpdate update = grid.pollUpdate(1, TimeUnit.SECONDS);
					if (update == null) {
						continue;
					}
					logger.info("send " + DJson.serialize(update));
					if (update != null) {
						out.write(DJson.serialize(Arrays.asList(update)).getBytes());
						out.flush();
					}
				} catch (Exception e) {
					grid.dispose();
					logger.error("closing download test");
					return;
				}

			}
		};
		
		logger.info("steaming response {} ", stream);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stream);
	}

}

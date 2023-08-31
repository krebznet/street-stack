package com.dunkware.trade.service.beach.server.controller;


import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

import reactor.core.publisher.Flux;



@RestController
public class BeachWebController {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachService beachService;

	@Autowired
	private BeachRuntime runtime;

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	

	@GetMapping(path = "/trade/v1/dash/core/brokers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> beachBrokers() {
		GlazedDataGrid grid = GlazedDataGrid.newInstance(beachService.getBrokerBeans(),runtime.getExecutor(),"getId");
		grid.start();
		Flux<List<DataGridUpdate>> results = grid.getUpdates();
		//Flux<List<DataGridUpdate>> results = grid.getUpdates();
		//results = results.subscribeOn(Schedulers.boundedElastic());
		
		
		return results;
	
	
	}
	@GetMapping(path = "/trade/v1/dash/core/accounts", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> beacAccounts() {
		GlazedDataGrid grid = GlazedDataGrid.newInstance(beachService.getAccountBeans(),runtime.getExecutor(),"getId");
		grid.start();
		Flux<List<DataGridUpdate>> results = grid.getUpdates();
		//Flux<List<DataGridUpdate>> results = grid.getUpdates();
		//results = results.subscribeOn(Schedulers.boundedElastic());
		return results;
	
	
	}
            

}

package com.dunkware.trade.service.beach.server.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockBrokerEventList;
import com.dunkware.trade.service.beach.server.runtime.BeachAccountBean;
import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;
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
    public Flux<List<DataGridUpdate>> brokerTrades() {
		GlazedDataGrid grid = GlazedDataGrid.newInstance(beachService.getBrokerBeans(),runtime.getExecutor(),"getId");
		grid.start();
		Flux<List<DataGridUpdate>> results = grid.getUpdates();
		return results;
	
	}
            

}

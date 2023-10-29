package com.dunkware.trade.service.beach.server.broker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.trade.service.beach.protocol.broker.AddBrokerReq;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerService;

import reactor.core.publisher.Flux;

@RestController
public class BeachBrokerController {
	
	@Autowired
	private BeachBrokerService brokerService; 
	
	@PostMapping(path = "/trade/v1/broker/add")
	public void addBroker(@RequestBody() AddBrokerReq req) throws Exception  { 
		try {
			brokerService.addBroker(req);
		} catch (Exception e) {
			throw new UserException("Exception adding broker " + e.toString());
		}
	}
	
	
	@GetMapping(path = "/trade/v1/broker/grid/brokers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> beachBrokers() {
		GlazedDataGrid grid = GlazedDataGrid.newInstance(brokerService.getBrokerBeans(),brokerService.getExecutor(),"getId");
		grid.start();
		return grid.getUpdates();
	}
	
	
	@GetMapping(path = "/trade/v1/broker/grid/accounts", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> beacAccounts() {
		GlazedDataGrid grid = GlazedDataGrid.newInstance(brokerService.getBrokerAccountBeans(),brokerService.getExecutor(),"getId");
		grid.start();
		return grid.getUpdates();
	
	}

}

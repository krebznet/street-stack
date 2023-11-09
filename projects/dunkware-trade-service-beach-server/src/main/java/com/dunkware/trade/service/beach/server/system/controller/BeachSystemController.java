package com.dunkware.trade.service.beach.server.system.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.beach.server.system.BeachSystemService;

import reactor.core.publisher.Flux;

@RestController
public class BeachSystemController {

	
	@Autowired
	private BeachSystemService systemService;
	
	
	@Autowired
	private ExecutorService executorService; 
	
	@PostConstruct
	private  void init() { 
		System.out.println("okay");
	}

	@GetMapping(path = "/trade/v1/systems/grid", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> tradeSystems() {
		GlazedDataGrid grid = GlazedDataGrid.newInstance(systemService.getBeans(),executorService.get(),"getId");
		grid.start();
		return grid.getUpdates();
		
	}
	

}

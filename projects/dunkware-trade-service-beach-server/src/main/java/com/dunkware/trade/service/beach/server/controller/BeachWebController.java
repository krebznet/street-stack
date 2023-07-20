package com.dunkware.trade.service.beach.server.controller;


import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


@CrossOrigin("*")
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
		//Flux<List<DataGridUpdate>> results = grid.getUpdates();
		//results = results.subscribeOn(Schedulers.boundedElastic());
		
		results.subscribe(new Subscriber<List<DataGridUpdate>>() {

			private Subscription sub;
			@Override
			public void onSubscribe(Subscription s) {
				this.sub = s;
				sub.request(1);;
			}

			@Override
			public void onNext(List<DataGridUpdate> t) {
				try {
					logger.debug("on next " + DJson.serialize(t));	
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				sub.request(1);
				
				
			}

			@Override
			public void onError(Throwable t) {
				sub.cancel();
				if(logger.isDebugEnabled() ) { 
					logger.debug("one error dispposing mocked broker list" + t.toString());
				}
				//list.dispose();
			}

			@Override
			public void onComplete() {
				sub.cancel();
				if(logger.isDebugEnabled() ) { 
					logger.debug("disposing mocked broker list");
				}
			//	list.dispose();
				
				
			} 
			
		});
		return results;
	
	
	}
            

}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.datagrid.GlazedDataGrid;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockBrokerEventList;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

import reactor.core.publisher.Flux;

@RestController
public class BeachWebMockController {

	// /trade/mock/stream/brokers

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private BeachService beachService;

	@Autowired
	private BeachRuntime runtime;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping(path = "/trade/v1/mock/echo")
	public @ResponseBody() String echo(@RequestParam() String echo) { 
		return echo;
	}
	

	
	@GetMapping(path = "/trade/v1/mock/dash/brokers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<DataGridUpdate>> brokerTrades() {
		MockBrokerEventList list = MockBrokerEventList.newInstance(runtime.getExecutor(), 50, 1000);
		list.start();
		GlazedDataGrid grid = GlazedDataGrid.newInstance(list.getList(),runtime.getExecutor(),"getId");
		grid.start();
		Flux<List<DataGridUpdate>> results = grid.getUpdates();
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
				list.dispose();
			}

			@Override
			public void onComplete() {
				sub.cancel();
				if(logger.isDebugEnabled() ) { 
					logger.debug("disposing mocked broker list");
				}
				list.dispose();
				
				
			} 
			
		});
		return results;
	
	}
                
                	
                
	
                	
				
                
                
                		
    
	


		
	

}

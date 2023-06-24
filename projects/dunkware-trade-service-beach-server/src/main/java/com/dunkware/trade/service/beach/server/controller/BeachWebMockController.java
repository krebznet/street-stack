package com.dunkware.trade.service.beach.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockBrokerEventList;
import com.dunkware.trade.service.beach.server.controller.mock.MockTradeEventList;
import com.dunkware.trade.service.beach.server.runtime.BeachService;
import com.dunkware.trade.service.beach.server.runtime.BeachTradeBean;


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

	
	
	@GetMapping(path = "/trade/v1/mock/dash/brokers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> brokersStream() {
		
		final MockBrokerEventList list = MockBrokerEventList.newInstance(runtime.getExecutor(), 5);
		list.start();
		StreamingResponseBody stream = out -> {

			while (true) {
				try {
					DataGridUpdate update = list.nextUpdate(4, TimeUnit.SECONDS);
					if (update == null) {
						continue;
					}

					logger.info("send " + DJson.serialize(update));
					if (update != null) {
						
							out.write(DJson.serialize(Arrays.asList(update)).getBytes());
							out.flush();
						

					}
				} catch (Exception e) {
					list.dispose();
					out.close();
					logger.error("closing download test");
					return;
				}

			}

		};
		logger.info("steaming response {} ", stream);
		  return ResponseEntity.ok()
			        .contentType(MediaType.APPLICATION_JSON)
			        .body(stream);
	}
	
	
	@GetMapping(path = "/trade/v1/mock/dash/static/trades")
	public @ResponseBody() List<DataGridUpdate>  staticTrades() { 
		List<DataGridUpdate> results = new ArrayList<DataGridUpdate>(); 
		BeachTradeBean b = new BeachTradeBean();
		b.setId(1);
		b.setSymbol(DUUID.randomUUID(4));
		b.setStatus("name1");
		b.setStatus("status1");
		b.setUpl(2.23);
		b.setRpl(3.42);
		b.setAllocatedSize(234);
		b.setFilledSize(200);
		b.setIdent(b.getSymbol() + "_" + b.getId());
		b.setCloseTime("todo");
		b.setClosingTime("todo");
		b.setEntryCommission(DRandom.getRandom(3, 2392));
		DataGridUpdate update = new DataGridUpdate();
		update.setId(1);
		update.setType("ADD");
		update.setJson(results);
		results.add(update);
		return results;
	}

	
	@GetMapping(value = "/trade/v1/mock/dash/trades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> download() {


		final MockTradeEventList list = MockTradeEventList.newInstance(runtime.getExecutor(), 5);
		list.start();
		StreamingResponseBody stream = out -> {

			while (true) {
				try {
					
					DataGridUpdate update = list.nextUpdate(4, TimeUnit.SECONDS);
					if (update == null) {
						continue;
					}

					logger.info("send " + DJson.serialize(update));
					if (update != null) {
							try {
								out.write(DJson.serialize(Arrays.asList(update)).getBytes());
								
								out.flush();
							} catch (RuntimeException e) {
								System.out.println("beak here mother fucke");
							}
							
					
					}
				} catch (Exception e) {
					list.dispose();
					out.close();
					logger.error("closing download test");
					return;
				}

			}

		};
		logger.info("steaming response {} ", stream);
		  return ResponseEntity.ok()
			        .contentType(MediaType.APPLICATION_JSON)
			        .body(stream);
	}

	
	

}

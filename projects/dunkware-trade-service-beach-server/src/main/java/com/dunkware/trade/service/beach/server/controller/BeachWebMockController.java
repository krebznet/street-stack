package com.dunkware.trade.service.beach.server.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockBrokerEventList2;
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

	@GetMapping(path = "/trade/v1/mock/dash/brokers")
	public ResponseEntity<StreamingResponseBody> brokersStream(HttpServletResponse resp) {

		StreamingResponseBody stream = out -> {
			final MockBrokerEventList2 list = MockBrokerEventList2.newInstance(runtime.getExecutor(), 5);
			// PrintWriter p = new PrintWriter(out);
			list.start();
			while (true) {
				try {
					DataGridUpdate update = list.nextUpdate(4, TimeUnit.SECONDS);

					if (update == null) {
						// p.flush();
						// .out.flush();
						continue;
					}

					logger.info("send " + DJson.serialize(update));
					if (update != null) {

						// p.println(Arrays.asList(DJson.serialize(update).getBytes()));
						// resp.getOutputStream().write(DJson.serialize(Arrays.asList(update)).getBytes());
						resp.getWriter().write(DJson.serialize(update) + "\n".getBytes());
						resp.getWriter().flush();
						// p.print(DJson.serialize(Arrays.asList(update)).getBytes());
						// p.flush();
//						out.flush();

					}
				} catch (Exception e) {
					list.dispose();
					out.close();
					// p.close();
					logger.error("closing download test");
					return;
				}

			}

		};
		logger.info("steaming response {} ", stream);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(stream);
	}

	public @ResponseBody() String echo(@RequestParam() String echo) { 
		return echo;
	}
	@GetMapping(path = "/trade/v1/mock/dash/static/trades")
	public @ResponseBody() List<DataGridUpdate> staticTrades() {
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
	

	@GetMapping("/trade/json")
	public ResponseEntity<StreamingResponseBody> streamJson() {
	  int maxRecords = 1000;
	  
	  StreamingResponseBody responseBody = response -> {
			final MockBrokerEventList2 list = MockBrokerEventList2.newInstance(new DExecutor(5), 5);
			list.start();
			while(true) {
				DataGridUpdate update =list.nextUpdate(3, TimeUnit.SECONDS);
				if(update == null) { 
					continue;
			}
				System.err.println("have an update!");
				String out = "[" + DJson.serialize(update) + "]" +  "\n;";
						byte[] fuck = out.getBytes(Charset.defaultCharset());
			response.write(fuck);
	        response.flush();
	        try {
	           Thread.sleep(1000);
	        } catch (InterruptedException e) {
	           e.printStackTrace();
	        }
	     }
	  };
	  return ResponseEntity.ok()
	        .contentType(MediaType.APPLICATION_STREAM_JSON)
	        .body(responseBody);
	}

	@GetMapping(value = "/trade/v1/mock/dash/trades", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<StreamingResponseBody> download() {

		StreamingResponseBody stream = out -> {
			final MockBrokerEventList2 list = MockBrokerEventList2.newInstance(runtime.getExecutor(), 4);
			list.start();
			while (true) {
						
				try {

					DataGridUpdate update = list.nextUpdate(4, TimeUnit.SECONDS);
					if (update == null) {
						continue;
					}

					logger.info("send " + DJson.serialize(update));
					if (update != null) {
						try {
						
							String json = DJson.serialize(update);
							
							json = DJson.serialize(Arrays.asList(json) + "\n");
							
							logger.info("sending:"+  json);
							out.write(json.getBytes());
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
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(stream);
	}
	
	
		

		
		
	

}

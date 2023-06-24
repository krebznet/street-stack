package com.dunkware.trade.service.beach.server.controller;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.controller.mock.MockBrokerEventList;
import com.dunkware.trade.service.beach.server.controller.mock.MockTradeEventList;
import com.dunkware.trade.service.beach.server.runtime.BeachService;

@CrossOrigin()
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

	
	@CrossOrigin()
	@GetMapping(value = "/trade/v1/mock/dash/brokers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> brokersStream(final HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

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
		return new ResponseEntity(stream, HttpStatus.OK);
	}
	

	@CrossOrigin()
	@GetMapping(value = "/trade/v1/mock/dash/trades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> download(final HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

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
		return new ResponseEntity(stream, HttpStatus.OK);
	}

	
	

}

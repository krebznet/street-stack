package com.dunkware.trade.service.stream.server.session.worker.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.trade.service.stream.server.session.worker.StreamSessionWorkerService;
import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStatsResp;
import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.server.session.worker.protocol.spec.StreamSessionWorkerStatsSpec;

@RestController
@Profile("StreamSessionWorker")
public class StreamSessionWorkerWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StreamSessionWorkerService workerService; 
	
	@PostConstruct
	private void postConstruct() { 
		logger.info("Starting Stream Session Worker Web Service");
	}
	

	@PostMapping(path = "/stream/worker/start")
	public @ResponseBody()StreamSessionWorkerStartResp startWorker(@RequestBody()StreamSessionWorkerStartReq req) { 
		StreamSessionWorkerStartResp resp = new StreamSessionWorkerStartResp();
		try {
			resp.setStartingTime(DTime.now());
			workerService.startWorker(req);
			resp.setStartTime(DTime.now());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}

	}
	@RequestMapping(path = "/stream/worker/stop")
	public @ResponseBody()String stopWorker(@RequestParam(name="id")String workerId) { 
		try {
			workerService.stopWorker(workerId);
			return "OK";
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	
	@RequestMapping(path = "/stream/worker/ping")
	public @ResponseBody()String pingWorker() { 
		return "pong";
	}
	
	
	@RequestMapping(path = "/stream/worker/stats")
	public @ResponseBody()StreamSessionWorkerStatsResp workerStats(@RequestParam(name = "id") String workerId) { 
		StreamSessionWorkerStatsResp resp = new  StreamSessionWorkerStatsResp();
		try {
			StreamSessionWorkerStatsSpec stats = workerService.getWorker(workerId).getStats();
			resp.setSpec(stats);
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
	}
	

	
}

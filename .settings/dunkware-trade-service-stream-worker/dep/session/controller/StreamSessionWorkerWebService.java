
package com.dunkware.trade.service.stream.worker.session.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.common.util.json.DunkJson;
import com.dunkware.trade.service.stream.json.query.WebStreamQuery;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStatsResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkersStats;
import com.dunkware.xstream.model.snapshot.EntitySnapshot;

@RestController
public class StreamSessionWorkerWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StreamSessionWorkerService workerService;

	@PostConstruct
	private void postConstruct() {
		logger.info("Starting Stream Session Worker Web Service");
	}

	@PostMapping("/stream/worker/start")
	public String startWorker(@RequestParam("file") MultipartFile file) {
		byte[] bundleBytes = null;
		try {
			bundleBytes = file.getBytes();
		} catch (Exception e) {
			return e.toString();
		}
		StreamSessionWorkerStartReq req = null;
		try {
			req = DunkJson.getObjectMapper().readValue(bundleBytes, StreamSessionWorkerStartReq.class);

		} catch (Exception e) {
			logger.error("Another fuckin error on this shit what the fuck " + e.toString(), e);
			return e.toString();
		}

		try {
			StreamSessionWorker worker = workerService.startWorker(req);
			return "STARTED!";
		} catch (Exception e) {
			return e.toString();
		}

	}
	

	@RequestMapping(path = "/stream/worker/stop")
	public @ResponseBody() String stopWorker(@RequestParam(name = "id") String workerId) {
		try {
			workerService.stopWorker(workerId);
			return "OK";
		} catch (Exception e) {
			return e.toString();
		}
	}

	@RequestMapping(path = "/stream/worker/ping")
	public @ResponseBody() String pingWorker() {
		return "pong";
	}

	@RequestMapping(path = "/stream/worker/stats")
	public @ResponseBody() StreamSessionWorkerStatsResp workerStats(@RequestParam(name = "id") String workerId) {
		StreamSessionWorkerStatsResp resp = new StreamSessionWorkerStatsResp();
		try {
			StreamSessionWorkerStats stats = workerService.getWorker(workerId).getStats();
			resp.setSpec(stats);
			resp.setCode("SUCCESS"); 
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
	}
	
	@RequestMapping(path = "/stream/workers/stats")
	public @ResponseBody() StreamSessionWorkersStats workersStats() throws Exception { 
		StreamSessionWorkersStats workers = new StreamSessionWorkersStats(); 
		for (StreamSessionWorker worker : workerService.getWorkers()) {
			workers.getWorkers().add(worker.getStats());
		}
		return workers;
	}
	
	@RequestMapping(path = "/stream/worker/snapshot")
	public @ResponseBody() EntitySnapshot workerSessionEntitySnapshot(@RequestParam() String ident, @RequestParam() String workerId) throws Exception { 
		StreamSessionWorker worker = workerService.getWorker(workerId);
		return worker.getEntitySnapshot(ident);	
	}
	
	
	@PostMapping(path = "/stream/worker/query/entity")
	public @ResponseBody() List<EntitySnapshot> rowQuery(@RequestBody() WebStreamQuery query, @RequestParam() String streamIdent) { 
		return null;
		
	}
	 
	// then it will be a entity scanner  
	
	
	

}

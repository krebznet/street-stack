package com.dunkware.trade.service.stream.server.controller.session.worker;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.protocol.controller.spec.StreamWorkerMetrics;
import com.dunkware.trade.service.stream.protocol.controller.spec.StreamWorkerTickers;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StartWorkerReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StartWorkerResp;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StopWorkerReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StopWorkerResp;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerMetricsReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerMetricsResp;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerTickersReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerTickersResp;

@RestController
@Profile("StreamWorkerService")
public class StreamWorkerWebService {

	@Autowired
	private StreamWorkerService service; 
	
	@PostConstruct
	private void postConstruct() { 
		System.out.println("ddf");
	}
	
	@GetMapping(path = "/worker/ping")
	public String ping() { 
		return "pong";
	}
	
	@PostMapping(path = "/worker/start")
	public @ResponseBody()StartWorkerResp startWorker(@RequestBody()StartWorkerReq req) { 
		StartWorkerResp resp = new StartWorkerResp();
		try {
			StreamWorker worker = service.startWorker(req.getStreamBundle(), req.getStreamName());
			resp.setIdentifier(worker.getIdentifier());
			resp.setCode("SUCCESS");
			
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;

		}

	}
	@PostMapping(path = "/worker/stop")
	public @ResponseBody()StopWorkerResp stopWorker(@RequestBody()StopWorkerReq req) { 
		StopWorkerResp resp = new StopWorkerResp();
		try {
			service.stopWorker(req.getIdentifier());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;

		}
	
	}
	
	@PostMapping(path = "/worker/metrics")
	public @ResponseBody()WorkerMetricsResp workerStats(@RequestBody()WorkerMetricsReq req) { 
		WorkerMetricsResp resp = new WorkerMetricsResp();
		try {
			if(!service.hasWorker(req.getIdentifier())) { 
				resp.setCode("ERROR");
				resp.setError("Stream Worker with Identifier " + req.getIdentifier() + " not found in stream worker service");
				return resp;
			}
			
			StreamWorker worker = service.getWorker(req.getIdentifier());
			StreamWorkerMetrics stats = new StreamWorkerMetrics();
			stats.setIdentifier(worker.getIdentifier());
			stats.setStats(worker.getStream().getStats(req.isRows(), req.isVars(), req.getRowIds()));
			resp.setStats(stats);
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;

		}
	
	}
	
	@PostMapping(path = "/worker/tickers")
	public @ResponseBody()WorkerTickersResp workerTickers(@RequestBody()WorkerTickersReq req) { 
		WorkerTickersResp resp = new WorkerTickersResp();
		try {
			if(!service.hasWorker(req.getIdentifier())) { 
				resp.setCode("ERROR");
				resp.setError("Stream Worker with Identifier " + req.getIdentifier() + " not found in stream worker service");
				return resp;
			}
			
			StreamWorker worker = service.getWorker(req.getIdentifier());
			StreamWorkerMetrics stats = new StreamWorkerMetrics();
			stats.setIdentifier(worker.getIdentifier());
			List<String> symbols = new ArrayList<String>();
			StreamWorkerTickers workerTickers = new StreamWorkerTickers();
			for (String symbol : worker.getStream().getRowIds()) {
				symbols.add(symbol);
			}
			workerTickers.setIdentifer(worker.getIdentifier());
			workerTickers.setTickers(symbols);
			
			resp.setTickers(workerTickers);
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;

		}
	
	}
	
	
	
	
	
	
	
}

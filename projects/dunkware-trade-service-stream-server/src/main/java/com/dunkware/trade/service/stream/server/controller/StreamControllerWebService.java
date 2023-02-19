package com.dunkware.trade.service.stream.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.json.controller.AddStreamReq;
import com.dunkware.trade.service.stream.json.controller.AddStreamResp;
import com.dunkware.trade.service.stream.json.controller.GetStreamSpecResp;
import com.dunkware.trade.service.stream.json.controller.GetStreamSpecsResp;
import com.dunkware.trade.service.stream.json.controller.StartStreamResp;
import com.dunkware.trade.service.stream.json.controller.StopStreamResp;
import com.dunkware.trade.service.stream.json.controller.StreamStatsResp;
import com.dunkware.trade.service.stream.json.controller.UpdateStreamReq;
import com.dunkware.trade.service.stream.json.controller.UpdateStreamResp;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerState;
import com.dunkware.trade.service.stream.server.controller.util.StreamSpecBuilder;
import com.dunkware.xstream.model.spec.StreamSpec;


@RestController
public class StreamControllerWebService {

	@Autowired
	private StreamControllerService service; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void load() { 
	
	}

	
	
	@PostMapping(path = "/stream/core/add") 
	public @ResponseBody() AddStreamResp addStream(@RequestBody()AddStreamReq req) {
		AddStreamResp resp = new AddStreamResp();
		try {
			service.addStream(req.getSpec());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
		
	}
	

	@PostMapping(path = "/stream/core/update") 
	public @ResponseBody() UpdateStreamResp updateStream(@RequestBody()UpdateStreamReq req) {
		UpdateStreamResp resp = new UpdateStreamResp();
		try {
			service.updateStream(req.getSpec());
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError(e.toString());
			return resp;
		}
		
	}
	
	@GetMapping(path = "/stream/core/start") 
	public @ResponseBody()StartStreamResp startStream(@RequestParam(name = "stream")String stream) {
		StartStreamResp resp = new StartStreamResp();
		StreamController controller = null;
		try {
			controller = service.getStreamByName(stream);
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError("Get stream exception " + e.toString());
			return resp;
		}
		try {
			controller.startSession();
			resp.setCode("SUCCESS");
			return resp;
		} catch (Exception e) {
			resp.setError("Internal " + e.toString());
			resp.setCode("ERROR");
			return resp;
		}
		
	}
	
	@GetMapping(path = "/stream/core/stop") 
	public @ResponseBody()StopStreamResp stopStream(@RequestParam(name = "stream")String stream) {
		StopStreamResp resp = new StopStreamResp();
		StreamController controller = null;
		try {
			controller = service.getStreamByName(stream);
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError("Get stream exception " + e.toString());
			return resp;
		}
		try {
			if(controller.getStats().getState() == StreamControllerState.Running) { 
				controller.stopSession();
				resp.setCode("SUCCSS");
				return resp;
			}
			resp.setCode("ERROR");
			resp.setError("Stream is Not In Session");
			return resp;
		} catch (Exception e) {
			resp.setError("Internal " + e.toString());
			resp.setCode("ERROR");
			return resp;
		}
		
	}
	
	@RequestMapping(path = "/stream/core/stats") 
	public @ResponseBody()StreamStatsResp streamStatus(@RequestParam(name = "stream")String stream) {
		StreamStatsResp resp = new StreamStatsResp();
		try {
			StreamController controller = service.getStreamByName(stream);
			resp.setCode("SUCCESS");
			resp.setStats(controller.getStats());
			return resp;
		} catch (Exception e) {
			resp.setCode("ERROR");
			resp.setError("Exception getting stream " + e.toString());;
			return resp;
		}
	}
	
	
	@RequestMapping(path = "/stream/core/get")
	public @ResponseBody GetStreamSpecResp getStreamSpec(@RequestParam(name = "stream") String stream) { 
		GetStreamSpecResp resp = new GetStreamSpecResp();
		try {
			StreamController controller = service.getStreamByName(stream);
			StreamControllerSpec spec = controller.getSpec();
			resp.setSpec(spec);
			resp.setCode("SUCCESS");
			return resp;			
		} catch (Exception e) {
			resp.setError("Exception Getting Stream " + e.toString());
			return resp;
		}
		
	}

	@GetMapping(path = "/stream/controller/specs")
	public @ResponseBody GetStreamSpecsResp getStreamSpecs() { 
		GetStreamSpecsResp resp = new GetStreamSpecsResp();
		
		try {
			List<StreamControllerSpec> specs = new ArrayList<StreamControllerSpec>();
			for (StreamController controller : service.getStreams()) {
				specs.add(controller.getSpec());
			}
			
			resp.setSpecs(specs);
			resp.setCode("SUCCESS");
			return resp;			
		} catch (Exception e) {
			resp.setError("Exception Getting Stream " + e.toString());
			return resp;
		}
	}
	
	

	@GetMapping(path = "/stream/core/specs")
	public @ResponseBody List<StreamSpec> getSpecs() throws Exception { 
		List<StreamSpec> specs = new ArrayList<StreamSpec>();
		try {
			for (StreamController stream : service.getStreams()) {
				specs.add(StreamSpecBuilder.build(stream));
			}	
		} catch (Exception e) {
			logger.error("Exception building stream specs " + e.toString());;
			throw e;
		}
		
		return specs;
		
	}
	
	


	
	
	

	
}

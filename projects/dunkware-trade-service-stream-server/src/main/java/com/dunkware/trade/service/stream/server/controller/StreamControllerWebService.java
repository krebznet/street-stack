package com.dunkware.trade.service.stream.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.spring.runtime.controller.UserException;
import com.dunkware.trade.service.stream.json.controller.AddStreamReq;
import com.dunkware.trade.service.stream.json.controller.AddStreamResp;
import com.dunkware.trade.service.stream.json.controller.GetStreamSpecResp;
import com.dunkware.trade.service.stream.json.controller.GetStreamSpecsResp;
import com.dunkware.trade.service.stream.json.controller.StartStreamResp;
import com.dunkware.trade.service.stream.json.controller.StopStreamResp;
import com.dunkware.trade.service.stream.json.controller.UpdateStreamReq;
import com.dunkware.trade.service.stream.json.controller.UpdateStreamResp;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.resources.StreamResource;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;


@RestController
public class StreamControllerWebService {

	@Autowired
	private StreamControllerService service; 
	
	@Autowired
	private StreamTickService tickService; 
	
	
	
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
	
	
	@GetMapping(path = "/steam/core/override/tickers")
	public void setTickers(@RequestParam() String stream, @RequestParam() String symbols) throws Exception { 
		String[] parsed = symbols.split(",");
		StreamController controller = service.getStreamByName(stream);
		controller.setTickers(parsed);
		
	}
		
	
	
	@GetMapping(path = "/stream/core/start") 
	public void startStream(@RequestParam(name = "stream")String stream) throws Exception {
		StreamController controller = null;
		try {
			controller = service.getStreamByName(stream);
		} catch (Exception e) {
			throw new UserException("Stream " + stream +  " not found");
		}
		try {
			if(controller.getState() == StreamState.Running) { 
				throw new UserException("Stream cannot be started when Running");
			}
			controller.startSession();
			
		} catch (Exception e) {
			throw new Exception("Internal Exception Starting Stream " 	 + e.toString());
		}
		
	}
	
	@GetMapping(path = "/stream/core/kill") 
	public void streamKill(@RequestParam(name = "stream")String stream) throws Exception {
		StartStreamResp resp = new StartStreamResp();
		StreamController controller = null;
		try {
			controller = service.getStreamByName(stream);
		} catch (Exception e) {
			throw e;
		}
		try {
			 controller.killSession();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	
	@GetMapping(path = "/stream/core/stop") 
	public  void stopStream(@RequestParam(name = "stream")String stream) throws Exception {
		StopStreamResp resp = new StopStreamResp();
		StreamController controller = null;
		try {
			controller = service.getStreamByName(stream);
		} catch (Exception e) {
			throw new UserException("Stream " + stream +  " not found");
			
		}
		try {
			controller.stopSession();
			
		} catch (Exception e) {
			throw new UserException(e.toString());
		}
		
	}
	
	
	@GetMapping(path = "/stream/core/state") 
	public @ResponseBody()String sreamState(@RequestParam(name = "stream")String stream) throws Exception {
		
		try {
			return service.getStreamByName(stream).getState().name();
		} catch (Exception e) {
		
			throw new Exception("Exception interla getting state on stream " + e.toString());
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
	

	

	

	
	@GetMapping(path = "/stream/resource/streams")
	public @ResponseBody() List<StreamResource> getStreamIdentifiers() { 
		List<StreamResource> mock = new ArrayList<StreamResource>();
		try {
			for (StreamController sc : service.getStreams()) {
				StreamResource re = new StreamResource();
				re.setId(sc.getEntity().getId());
				re.setIdentifier(sc.getName());
				re.setName(sc.getName());
				mock.add(re);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Exception getting streams " + e.toString(),e);
		}
		return mock;
		
	}
	
	
	
	
	
	

	
	
	

	
}

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

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.controller.AddStreamReq;
import com.dunkware.trade.service.stream.json.controller.AddStreamResp;
import com.dunkware.trade.service.stream.json.controller.GetStreamSpecResp;
import com.dunkware.trade.service.stream.json.controller.GetStreamSpecsResp;
import com.dunkware.trade.service.stream.json.controller.StartStreamResp;
import com.dunkware.trade.service.stream.json.controller.StopStreamResp;
import com.dunkware.trade.service.stream.json.controller.StreamStatsResp;
import com.dunkware.trade.service.stream.json.controller.UpdateStreamReq;
import com.dunkware.trade.service.stream.json.controller.UpdateStreamResp;
import com.dunkware.trade.service.stream.json.controller.session.StreamDashEntity;
import com.dunkware.trade.service.stream.json.controller.session.StreamDashNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamDashStats;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStats;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerStats;
import com.dunkware.trade.service.stream.resources.StreamResource;
import com.dunkware.trade.service.stream.resources.VariableResource;
import com.dunkware.trade.service.stream.server.controller.util.StreamSpecBuilder;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.spec.StreamSpec;
import com.dunkware.xstream.model.spec.StreamSpecList;
import com.dunkware.xstream.xScript.VarType;


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
	
	@GetMapping(path = "/stream/core/kill") 
	public @ResponseBody()String streamKill(@RequestParam(name = "stream")String stream) {
		StartStreamResp resp = new StartStreamResp();
		StreamController controller = null;
		try {
			controller = service.getStreamByName(stream);
		} catch (Exception e) {
			return e.toString();
		}
		try {
			return controller.killSession();
		} catch (Exception e) {
			return e.toString();
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
			controller.stopSession();
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
	
	@GetMapping(path = "/stream/spec/vars")
	public @ResponseBody() List<VariableResource> getVarResources(@RequestParam() String streamIdent) { 
		List<VariableResource> mock = new ArrayList<VariableResource>();
		StreamController stream = null;
		try {
			stream = service.getStreamByName(streamIdent);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream " + streamIdent + " not found " 	+ e.toString());
			
		}
		
		for (VarType type : stream.getScriptProject().getStreamVars()) {
			VariableResource var = new VariableResource(); 
			var.setId(type.getCode());
			var.setIdent(type.getName());
			var.setName(type.getName());
			mock.add(var);
			
		}
		
		return mock; 
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
	
	@GetMapping(path = "/stream/core/speclist")
	public @ResponseBody StreamSpecList getSpecList() throws Exception { 
		List<StreamSpec> specs = new ArrayList<StreamSpec>();
		try {
			for (StreamController stream : service.getStreams()) {
				specs.add(StreamSpecBuilder.build(stream));
			}	
		} catch (Exception e) {
			logger.error("Exception building stream specs " + e.toString());;
			throw e;
		}
		StreamSpecList list = new StreamSpecList();
		list.setSpecs(specs);
		return list;
		
	}
	
	@GetMapping(path = "/stream/dash/stats")
	public @ResponseBody StreamDashStats streamFuckMe(@RequestParam String ident) throws Exception { 
		StreamController controller = service.getStreamByName("us_equity");
		StreamControllerStats stats = controller.getStats();
		StreamDashStats resp = new StreamDashStats();
		resp.setStatus(stats.getState().name());
		
		StreamSessionStats session = stats.getSession();
		if(session != null) { 
			resp.setEntityCount(session.getTickerCount());
			resp.setNodes(session.getNodeCount());
			resp.setTasksCompleted((int)session.getCompletedTasks());
			resp.setTasksExpired((int)session.getTimeoutTasks());
			resp.setTasksPending((int)session.getPendingTasks());
			resp.setTickCount(session.getTickCount());
			
		} else { 
			resp.setEntityCount(0);
			resp.setNodes(0);
			resp.setTasksCompleted(0);
			resp.setTasksExpired(0);
			resp.setTasksPending(0);
			resp.setTickCount(0);
		}
		return resp;
	}

	@GetMapping(path = "/stream/dash/nodes")
	public @ResponseBody List<StreamDashNode> streamDashNodes(@RequestParam String ident) throws Exception  { 
		
		return null;
	}
	
	
	@GetMapping(path = "/stream/dash/entities")
	public @ResponseBody List<StreamDashEntity> streamDashEntities(@RequestParam String stream) throws Exception  { 
		List<StreamDashEntity> entities = new ArrayList<StreamDashEntity>();
		for (TradeTickerSpec spec : service.getStreamByName("us_equity").getTickers()) {
			StreamDashEntity entity = new StreamDashEntity();
			entity.setSymbol(spec.getSymbol());
			entity.setName(spec.getName());
			entities.add(entity);
		}
		return entities;
	}
	

	@GetMapping(path = "/stream/dash/entity")
	public @ResponseBody StreamDashEntity streamDashEntity(@RequestParam String stream, @RequestParam String ident) throws Exception  { 
		for (TradeTickerSpec spec : service.getStreamByName("us_equity").getTickers()) {
			if(spec.getSymbol().equalsIgnoreCase(ident)) { 
				StreamDashEntity entity = new StreamDashEntity();
				entity.setSymbol(spec.getSymbol());
				entity.setName(spec.getName());
				return entity;	
			}
			
		}
		logger.error("Entity Not found in /stream/dash/entity " + ident);
		throw new Exception("Entity Not Found " + ident);
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
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		List<StreamDashNode> results = new ArrayList<StreamDashNode>();
		int i = 0; 
		while(i < 3) { 
			StreamDashNode node = new StreamDashNode();
			node.setNode("Node-" + i);
			node.setEntityCount(DRandom.getRandom(1, 4));
			node.setStreamTime("09:23:23");
			node.setSystemTime("09:32:23");
			node.setTasksCompleted(DRandom.getRandom(32323, 909099));
			node.setTasksExpired(DRandom.getRandom(5,2332));
			node.setTickCount(DRandom.getRandom(3, 20323));
			i++;
			results.add(node);
		}
		
		try {
			System.out.println(DJson.serializePretty(results));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



	
	
	

	
}

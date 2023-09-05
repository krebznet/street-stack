package com.dunkware.trade.service.stream.server.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.stream.json.worker.scanner.StreamEntityScannerReq;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.xstream.model.entity.query.type.StreamEntityQueryType;
import com.dunkware.xstream.model.util.XStreamConverter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class StreamEntityScanner {
	
	@Autowired
	private StreamControllerService streamService;
	
	private ConcurrentHashMap<StreamSessionNode,StreamEntityScannerNode> nodes = new ConcurrentHashMap<StreamSessionNode,StreamEntityScannerNode>();
	private Sinks.Many<List<DataGridUpdate>> sink = Sinks.many().multicast().onBackpressureBuffer();
	private Flux<List<DataGridUpdate>> flux;
	
	
	public void start(StreamEntityQueryType query, List<String> fields, StreamSession session, int scanInterval) throws Exception { 
		StreamEntityScannerReq req = new StreamEntityScannerReq();
		req.setFields(fields);
		req.setQuery(XStreamConverter.toXStreamEntityQueryType(query));
		req.setScanInterval(scanInterval);
		req.setScannerId(DUUID.randomUUID(5));
		for (StreamSessionNode node : session.getNodes()) {
			StreamEntityScannerNode scannerNode = new StreamEntityScannerNode();
			// take in the channel; 
			//node.getDunkNode().channel(req);
			
		}
		
	}
	
	public void sendUpdates(List<StreamEntityScannerUpdate> updates) { 
		sink.tryEmitNext(convertUpdates(updates));
	}
	
	public Flux<List<DataGridUpdate>> getFlux() { 
		return flux;
	}
	
	
	private List<DataGridUpdate> convertUpdates(List<StreamEntityScannerUpdate> input) {
		List<DataGridUpdate> results = new ArrayList<DataGridUpdate>();
		for (StreamEntityScannerUpdate update : input) {
			results.add(convertUpdate(update));
		}
		return results;
	}
	
	private DataGridUpdate convertUpdate(StreamEntityScannerUpdate update) { 
		DataGridUpdate gu = new DataGridUpdate();
		gu.setType(update.getType().name());
		gu.setId(update.getId());
		JSONObject object = new JSONObject(update.getFields());
		gu.setJson(object.toJSONString());
		return gu;
	}
	
	

}

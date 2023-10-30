package com.dunkware.trade.net.data.server.stream.signals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.model.search.EntitySignalCountResponse;
import com.dunkware.trade.service.data.model.search.EntitySignalCountRequest;
import com.dunkware.trade.service.data.model.search.SignalSearchRequest;
import com.dunkware.trade.service.data.model.search.SignalSearchResponse;
import com.dunkware.trade.service.data.model.signals.StreamSessionSignalTypeBeans;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

@RestController
public class StreamSignalsController {

	
	// 3 things here 
	
	// Signal Type Grid 
	// Signal Grid -- but reusable its gonig to be a query 
	
	
	
	@Autowired
	private StreamSignalsProvider signalsProvider; 
	
	/**
	 * Inserts a signal 
	 * @param signal
	 */
	@PostMapping(path = "/data/v1/stream/signal/insert")
	public void insertSignal(@RequestBody() StreamEntitySignal signal, @RequestParam() String stream) throws Exception { 
		this.signalsProvider.getStreamSignals(stream).insertSignal(signal);
		
	}
	
	
		
	@GetMapping(path = "/data/v1/stream/signa/session/beans")
	public @ResponseBody() StreamSessionSignalTypeBeans sessionSignalypeBeans(@RequestParam() String stream) throws Exception { 
		return signalsProvider.getStreamSignals(stream).getSessionSignals().getSignalTypeBeans();
	}
	
	//SD21-GIFT-01 start here man, this is the end point we need this is what will get 
	// ui going. 
	@PostMapping(path = "/data/v1/stream/signal/search")
	public SignalSearchResponse signalSearch(@RequestBody() SignalSearchRequest req, @RequestParam() String stream) throws Exception { 
		// get a reference to the StreamSignals for this stream identiifer 
		StreamSignals streamSignals = signalsProvider.getStreamSignals(stream);
		
		//SD21-GIFT-15 now you see it full ciricle, we get the stream a mongo collection, logic to conver to a mongo query, logic to convert document back into model get results here and set it on web response 
		return streamSignals.signalSearch(req);
	
	}
	
	//SD-33-06 - rest api
	@PostMapping(path = "/data/v1/stream/signal/entity/count")
	public EntitySignalCountResponse entitySignalCountSearch(@RequestBody() EntitySignalCountRequest req, @RequestParam() String stream) throws Exception { 
		EntitySignalCountResponse respo = signalsProvider.getStreamSignals(stream).entitySignalCountSearchRequest(req);
		return respo;
	}
}

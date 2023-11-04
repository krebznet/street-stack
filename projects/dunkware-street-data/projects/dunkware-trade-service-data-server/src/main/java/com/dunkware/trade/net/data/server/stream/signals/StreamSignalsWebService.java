package com.dunkware.trade.net.data.server.stream.signals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.model.search.EntitySignalCountRequest;
import com.dunkware.trade.service.data.model.search.EntitySignalCountResponse;
import com.dunkware.trade.service.data.model.search.SignalSearchRequest;
import com.dunkware.trade.service.data.model.search.SignalSearchResponse;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

@RestController
public class StreamSignalsWebService {

	
	// 3 things here 
	
	// Signal Type Grid 
	// Signal Grid -- but reusable its gonig to be a query 
	
	
	
	@Autowired
	private StreamSignalsService signalsProvider; 
	
	/**
	 * Inserts a signal 
	 * @param signal
	 */
	@PostMapping(path = "/data/v1/stream/signal/insert")
	public void insertSignal(@RequestBody() StreamEntitySignal signal, @RequestParam() String stream) throws Exception { 
		this.signalsProvider.getStreamSignals(stream).insertSignal(signal);
		
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

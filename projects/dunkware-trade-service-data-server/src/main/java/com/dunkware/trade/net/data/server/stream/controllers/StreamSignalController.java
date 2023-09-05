package com.dunkware.trade.net.data.server.stream.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.net.data.server.stream.provider.StreamDataProviders;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

@RestController
public class StreamSignalController {

	
	@Autowired
	private StreamDataProviders dataProviders; 
	
	
	@PostMapping(path = "/v1/data/signal/insert")
	public void insertSignal(@RequestBody() StreamEntitySignal signal, @RequestParam() int stream) throws Exception  { 
		//StreamData streamData = dataService.getStreamData(stream);
		//streamData.insertSignal(signal);
		
	}
	
	@GetMapping(path = "/v1/data/signal/all")
	public void getSignals() throws Exception { 
		//StreamData data = dataService.getStreamData(1);
		//data.getSignals();
		
	}
	
	
	
}

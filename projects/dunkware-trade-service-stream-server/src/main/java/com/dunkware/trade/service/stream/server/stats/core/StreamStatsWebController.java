package com.dunkware.trade.service.stream.server.stats.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.stream.server.stats.StreamStatsService;
import com.dunkware.xstream.model.stats.StreamStats;

@RestController
public class StreamStatsWebController {

	
	@Autowired
	private StreamStatsService statsService; 
	
	@PostMapping(path = "/stream/stats/store")
	public void storeStats(@RequestBody() StreamStats stats) { 
		// need to convertere
	}
}

package com.dunkware.trade.service.data.service.stream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.json.data.stream.StreamStats;

@RestController
public class StreamWebService {

	
	@Autowired
	private StreamService streamService; 
	
	@RequestMapping(path = "/streamsstats")
	public List<StreamStats> getStreamsStats() { 
		return streamService.getStreamsStats();
	}

}

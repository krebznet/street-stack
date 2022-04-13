package com.dunkware.trade.service.data.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.json.stream.DataStreamStats;
import com.dunkware.trade.service.data.service.stream.DataStreamService;

@RestController
public class DataStreamRestService {

	
	@Autowired
	private DataStreamService streamService; 
	
	@RequestMapping(path = "/streamsstats")
	public List<DataStreamStats> getStreamsStats() { 
		return null;
	}

}

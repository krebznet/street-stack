package com.dunkware.trade.service.data.service.stream;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.json.stream.DataStreamStats;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;

@RestController
public class DataStreamWebService {

	
	@Autowired
	private DataStreamService streamService; 
	
	@RequestMapping(path = "/streams/sessions")
	public @ResponseBody()List<DataStreamSessionStats> getSessionStats() { 
		List<DataStreamSessionStats> results = new ArrayList<DataStreamSessionStats>();
		for (DataStream stream : streamService.getStreams()) {
			if(stream.getSession() != null) { 
				results.add(stream.getSession().getStats());
			}
		}
		return results;
	}

}

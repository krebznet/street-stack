package com.dunkware.trade.service.data.service.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionEntityStats;
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
	
	@RequestMapping(path = "/stream/session/entities")
	public @ResponseBody()Collection<DataStreamSessionEntityStats> getSessionEntityStats(@RequestParam() String stream) { 
		DataStream ds = streamService.getStream(stream);
		if(ds == null) { 
			return new ArrayList<DataStreamSessionEntityStats>();
		}
		if(ds.getSession() == null) { 
			return new ArrayList<DataStreamSessionEntityStats>();
		}
		return ds.getSession().getWebEntityStats().values();
	}

}

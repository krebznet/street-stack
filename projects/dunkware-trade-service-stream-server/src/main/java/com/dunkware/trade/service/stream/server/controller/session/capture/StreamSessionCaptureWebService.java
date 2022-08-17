package com.dunkware.trade.service.stream.server.controller.session.capture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;
import com.dunkware.trade.service.stream.DataStreamSessionEntityStats;


@RestController
public class StreamSessionCaptureWebService {

	
	@Autowired
	private StreamSessionCaptureService streamService; 
	
	@RequestMapping(path = "/stream/session/captures")
	public @ResponseBody()List<DataStreamSessionStats> getSessionStats() { 
		List<DataStreamSessionStats> results = new ArrayList<DataStreamSessionStats>();
		for (StreamSessionCapture stream : streamService.getRunningCaptures()) {
			if(stream.getSession() != null) { 
				results.add(stream.getStats());
			}
		}
		return results;
	}
	
	@RequestMapping(path = "/stream/session/capture/entities")
	public @ResponseBody()Collection<DataStreamSessionEntityStats> getSessionEntityStats(@RequestParam() String stream) { 
		StreamSessionCapture ds = streamService.getRunningCapture(stream);
		if(ds == null) { 
			return new ArrayList<DataStreamSessionEntityStats>();
		}
		if(ds.getSession() == null) { 
			return new ArrayList<DataStreamSessionEntityStats>();
		}
		return ds.getWebEntityStats().values();
	}

}

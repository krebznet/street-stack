package com.dunkware.trade.service.web.server.stream;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dunkware.trade.service.web.service.SessionEntityStreamRequest;
import com.dunkware.trade.service.web.service.SessionEntityStreamResponse;
import com.dunkware.xstream.model.spec.StreamSpec;

public class StreamWebService {
	

	@GetMapping(path = "/stream/specs")
	public @ResponseBody List<StreamSpec> getStreamSpecs() { 
		return null;
	}
	
	// StreamSearch Topic --> 
	
	@PostMapping(path = "/streamm/session/entity/stream")
	public SessionEntityStreamResponse entityStreamRequest(@RequestBody() SessionEntityStreamRequest request) {
		return null;
	}

}

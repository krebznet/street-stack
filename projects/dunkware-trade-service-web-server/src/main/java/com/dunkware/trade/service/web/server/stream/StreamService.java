package com.dunkware.trade.service.web.server.stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.dunkware.trade.service.web.server.stream.search.SessionEntityStream;
import com.dunkware.trade.service.web.service.SessionEntityStreamRequest;

@Service
public class StreamService {
	
	@PostConstruct
	private void load() { 
		//
	}
	
	public SessionEntityStream createSessionEntityStream(SessionEntityStreamRequest request) throws Exception { 
		return null;
	}

}

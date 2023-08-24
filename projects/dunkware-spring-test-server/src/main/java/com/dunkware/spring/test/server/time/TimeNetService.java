package com.dunkware.spring.test.server.time;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.spring.cluster.anot.ADunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.spring.test.model.time.TimeRequest;
import com.dunkware.spring.test.model.time.TimeResponse;

@ADunkNetComponent
public class TimeNetService {

	
	@ADunkNetService()
	public TimeResponse getTime(TimeRequest req) { 
		return new TimeResponse(DTime.now());
		
	}
	
	
	
}

package com.dunkware.trade.service.stream.server.aggregate.core;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamAggServiceController {

	public @ResponseBody int getFieldDayAggCount() { 
		// tells us how many days of variables we have
		return 3;
	}
	
	public @ResponseBody List<Object> getEntityDayAgg(String ident) { 
		return null;
	}
	
	public @ResponseBody List<Object> getEntityDayAgg(String ident, Object beforeDate) { 
		// get all the goods for that. 
		return null;
	}
}

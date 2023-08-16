package com.dunkware.trade.net.service.streamstats.server.statcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

@RestController
public class StreamStatCacheWebService {

	
	@Autowired
	private StreamStatCacheService cacheService;
	
	
	@PostMapping(path = "/streamstats/v1/entitystat")
	public @ResponseBody() EntityStatResp entityStat(@RequestBody() EntityStatReq req) {
		return cacheService.entityStat(req);
	}
	
	@GetMapping(path = "/streamstats/v1/service/stats")
	public @ResponseBody() StreamStatCacheServiceBean serviceBean() { 
		return cacheService.getBean();
	}
}

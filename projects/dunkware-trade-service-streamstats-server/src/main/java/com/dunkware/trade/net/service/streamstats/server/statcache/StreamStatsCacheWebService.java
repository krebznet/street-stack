package com.dunkware.trade.net.service.streamstats.server.statcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsAggVar;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

@Profile("StatCache")
@RestController
public class StreamStatsCacheWebService {

	
	@Autowired
	private StreamStatsCacheService cacheService;
	
	
	@PostMapping(path = "/streamstats/v1/entitystat")
	public @ResponseBody() EntityStatResp entityStat(@RequestBody() EntityStatReq req) {
		return cacheService.entityStat(req);
	}
	
	@GetMapping(path = "/streamstats/v1/entity/agg")
	public @ResponseBody()EntityStatsAgg entityStatAgg(@RequestParam()String entity, @RequestParam() String stream) throws Exception  {
		try {
			return cacheService.getStream(stream).getEntity(entity).getAgg();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping(path = "/streamstats/v1/var/agg")
	public @ResponseBody()EntityStatsAggVar varStatAgg(@RequestParam() String var, @RequestParam()String entity, @RequestParam() String stream) throws Exception  {
		try {
			return cacheService.getStream(stream).getEntity(entity).getVar(var).getAgg();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping(path = "/streamstats/v1/service/stats")
	public @ResponseBody() StreamStatsCacheServiceBean serviceBean() { 
		return cacheService.getBean();
	}
}

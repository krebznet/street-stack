package com.dunkware.stream.data.stats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.stream.data.stats.cache.EntityStatCacheBean;
import com.dunkware.stream.data.stats.cache.EntityStatCacheService;

@RestController
public class StatsController {

	private EntityStatCacheService service; 
	
	
	public StatsController(EntityStatCacheService service) { 
		this.service = service; 
	}
	
	@GetMapping(path = "/health")
	public String heatlh() { 
		return "OK";
	}
	
	@GetMapping(path = "/stats")
	public List<EntityStatCacheBean> cacheStats() { 
		return service.getCacheBeans();
	}
}

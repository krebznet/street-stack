package com.dunkware.trade.service.web.server.services;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

@RestController
public class TestController {

	@Autowired
	private DunkNet net;
	
	
	@PostConstruct
	public void test() { 
		System.out.println("okay here");
	}
	
	
	@GetMapping(path = "/stat/poop")
	public @ResponseBody() String poop() { 
		return "poop";
	}

	@PostMapping(path = "/stat/test")
	public @ResponseBody() EntityStatResp entityStat(@RequestBody() EntityStatReq req) { 
		
		try {
			int i = 0; 
			DStopWatch watch = DStopWatch.create();
			watch.start();
			EntityStatResp resp = (EntityStatResp)net.serviceBlocking(req);
			return resp;
			
			
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, e.toString());
			
		}
		
	}
	
}

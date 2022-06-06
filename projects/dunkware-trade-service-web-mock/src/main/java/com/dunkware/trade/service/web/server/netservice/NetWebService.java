package com.dunkware.trade.service.web.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dunkware.trade.service.web.server.netservice.session.NetSessionService;
import com.dunkware.trade.service.web.server.netservice.session.Test;

@Controller
public class NetWebService {
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private NetSessionService sessionService; 
	
	@Autowired
	private Test test;

	@GetMapping(path = "/net/session/create")
	public @ResponseBody() String createSession(@RequestParam() String user, @RequestParam() String token) { 
		try {
			test.fuck();
	//	String topic = sessionService.createSession(token);
		//	return topic;
			return "fuck";
		} catch (Exception e) {
			return "ERROR";
		}
		
		
	}
	
	@GetMapping(path = "/net/session/source")
	public @ResponseBody() String messageSource() { 
		return "mock";
	}
}

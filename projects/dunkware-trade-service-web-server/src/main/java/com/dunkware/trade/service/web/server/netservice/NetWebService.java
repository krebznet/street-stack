package com.dunkware.trade.service.web.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dunkware.trade.service.web.server.netservice.session.NetSession;
import com.dunkware.trade.service.web.server.netservice.session.NetSessionService;

@Controller
public class NetWebService {
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private NetSessionService sessionService; 

	@GetMapping(path = "/net/session/create")
	public String createSession(String user, String token) { 
		try {
			NetSession session =  sessionService.createSession(token);
			return session.getSessionId();
		} catch (Exception e) {
			return "ERROR";
		}
		
		
	}
}

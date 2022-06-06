package com.dunkware.trade.service.web.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.web.server.netservice.session.NetSession;
import com.dunkware.trade.service.web.server.netservice.session.NetSessionService;

@Controller
public class NetWebService {
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private Cluster cluster;
	
	@Autowired
	private NetSessionService sessionService; 

	@GetMapping(path = "/net/session/source")
	public @ResponseBody() String messageSource() { 
		return cluster.getNodeId();
	}
	
	@GetMapping(path = "/net/session/create")
	public @ResponseBody() String createSession(@RequestParam() String user, @RequestParam() String token) { 
		try {
			NetSession session =  sessionService.createSession(token);
			return session.getSessionId();
		} catch (Exception e) {
			return "ERROR";
		}
		
	}
}

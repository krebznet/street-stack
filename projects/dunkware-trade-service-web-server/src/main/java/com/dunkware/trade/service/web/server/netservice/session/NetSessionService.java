package com.dunkware.trade.service.web.server.netservice.session;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class NetSessionService {
	
	private Map<String,NetSession> sessions = new ConcurrentHashMap<String,NetSession>();
	
	public NetSession createSession(String sessionId) throws Exception { 
		NetSession session = new NetSession();
		session.start(sessionId);
		sessions.put(session.getSessionId(), session);
		return session;
	}
	
	public Collection<NetSession> getSessions() { 
		return sessions.values();
	}

}

package com.dunkware.trade.service.stream.serverd.server.controller.session;

import com.dunkware.trade.service.stream.serverd.controller.session.core.StreamSessionImpl;

public class StreamSessionFactory {

	public static StreamSession createSession() { 
		return new StreamSessionImpl();
	}
}

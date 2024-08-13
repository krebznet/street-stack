package com.dunkware.trade.service.stream.server.controller.session;

import com.dunkware.trade.service.stream.server.controller.session.core.StreamSessionImpl;

public class StreamSessionFactory {

	public static StreamSession createSession() { 
		return new StreamSessionImpl();
	}
}

package com.dunkware.trade.service.stream.server.session;

import com.dunkware.trade.service.stream.server.session.core.StreamSessionImpl;

public class StreamSessionFactory {

	public static StreamSession createSession() { 
		return new StreamSessionImpl();
	}
}

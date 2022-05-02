package com.dunkware.trade.service.stream.server.session.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.anot.AStreamSessionExt;
import com.dunkware.xstream.core.extensions.TimeUpdaterExtType;

@AStreamSessionExt
public class StreamTimeUpdater implements StreamSessionExtension {

	private StreamSession session;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public StreamSession getSession() {
		return session;
	}

	@Override
	public void sessionStarting(StreamSession session) {
		this.session = session;
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		TimeUpdaterExtType ext = new TimeUpdaterExtType();
		ext.setTimeZone(node.getStream().getSpec().getTimeZone());
		node.getStreamBundle().getExtensions().add(ext);
		logger.debug("Stream {} Session {} Add Stream Bundle Extension TimeUpdater ",session.getStream().getName(),session.getSessionId());
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStarted(StreamSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionStopping(StreamSession session) {
		
	}

	@Override
	public void sessionStopped(StreamSession session) {
		
	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		
	} 
	
	

	
}

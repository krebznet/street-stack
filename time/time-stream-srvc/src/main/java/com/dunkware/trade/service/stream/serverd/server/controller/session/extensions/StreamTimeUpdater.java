package com.dunkware.trade.service.stream.serverd.server.controller.session.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.serverd.controller.session.anot.AStreamSessionExt;
import com.dunkware.utils.core.time.DunkTimeZones;
import com.dunkware.xstream.core.extensions.TimeUpdaterExtType;

import ca.odell.glazedlists.impl.Main;

@AStreamSessionExt
public class StreamTimeUpdater implements StreamSessionExtension {

	private StreamSession session;
	
	@Autowired
	private ApplicationContext ac; 
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
		ext.setTimeZone(node.getStream().getTimeZone().getId());
		node.getStreamBundle().getExtensions().add(ext);
		logger.debug("Stream {} Session {} Add Stream Bundle Extension TimeUpdater ",session.getStream().getName(),session.getSessionId());
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		
		
	}

	@Override
	public void sessionStarted(StreamSession session) {
		
		
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

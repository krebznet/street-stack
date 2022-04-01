package com.dunkware.trade.service.stream.server.session.extensions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.anot.AStreamSessionExt;
import com.dunkware.trade.service.stream.server.store.StreamStore;
import com.dunkware.trade.service.stream.server.store.StreamStoreService;
import com.dunkware.xstream.mysql.MySqlCaptureExtType;

//@AStreamSessionExt()
public class MySqlStreamCapture implements StreamSessionExtension {

	@Autowired
	private StreamStoreService storeService; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void sessionStarting(StreamSession session) {
		
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		// kafka admin make sure the
		StreamStore streamStore = null;
		try {
			streamStore = storeService.getDefaultStore();
		} catch (Exception e) {
			logger.error("Stream Store Not Found For Setting Up Stream MySQL Capture " + e.toString());
			return;
		}
		
		String captureTable = null;
		String pattern = "yyyy_MM_dd";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String dateStamp = formatter.format(now);
		
		captureTable = node.getSession().getStream().getName().toLowerCase() +  "_" + dateStamp;
		
		MySqlCaptureExtType capture = new MySqlCaptureExtType();
		capture.setTablePrefix(captureTable);
		capture.setDatabase(streamStore.getEntity().getDbSchema());
		capture.setHost(streamStore.getEntity().getHost());
		capture.setUsername(streamStore.getEntity().getUser());
		capture.setPassword(streamStore.getEntity().getPass());
		capture.setPort(streamStore.getEntity().getPort());
		
		node.getStreamBundle().addExtension(capture);
		
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

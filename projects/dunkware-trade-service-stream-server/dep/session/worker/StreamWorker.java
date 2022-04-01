package com.dunkware.trade.service.stream.server.controller.session.worker;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.stream.server.spring.ExecutorService;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.core.runtime.XStreamCore;
import com.dunkware.xstream.model.XScriptBundle;
import com.dunkware.xstream.model.XStreamBundle;

public class StreamWorker {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExecutorService executor;
	
	private XStreamBundle bundle; 
	private XStream stream; 
	private String uuid;
	private String streamName; 
	
	public StreamWorker() { 
		
	}
	
	public void start(XStreamBundle bundle, String streamName) throws Exception { 
		this.bundle = bundle;
		try {
			XScriptBundle script = bundle.getScriptBundle();
			
			XStreamInput streamInput = XStreamCore.createInput(bundle, executor.getExecutor());			
			uuid = UUID.randomUUID().toString();
			stream = XStreamCore.createStream();
			if(logger.isDebugEnabled()) { 
				logger.debug("Starting Stream Worker Identifier " + uuid);
			}
			stream.start(streamInput);
		} catch (Exception e) {
			throw e;
		}

	}
	
	
	public String getIdentifier() { 
		return uuid;
	}
	
	public XStream getStream() { 
		return stream; 
	}
	
	public String getStreamName() { 
		return streamName;
	}
	
	public void stop() { 
		try {
			if(logger.isDebugEnabled()) { 
				logger.debug("Stopping Stream Worker Identifier " + uuid);
			}
			stream.dispose();
		} catch (Exception e) {
			logger.error("Exception Disposing Worker XStream " + e.toString());
		}
	}

}


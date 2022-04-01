package com.dunkware.trade.service.stream.server.controller.session;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.trade.service.stream.server.controller.event.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.spring.RuntimeService;

@Component
public class StreamSessionCaptureService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RuntimeService runtimeService; 
	
	@Autowired
	private ApplicationContext ac; 
	
	@PostConstruct
	public void start() { 
		logger.info("Starting Stream Session Capture Service");
		runtimeService.getEventTree().getRoot().addEventHandler(this);
	}
	
	
	@ADEventMethod()
	public void streamStarted(EStreamSessionStarted event) { 
		if(logger.isDebugEnabled()) { 
			logger.debug("Session Started Capture Event");
		}
		
		StreamSessionCapture capture = new StreamSessionCapture();
		ac.getAutowireCapableBeanFactory().autowireBean(capture);
		try {
			capture.start(event.getSession());	
		} catch (Exception e) {
			logger.error("Exception starting stream session capture {}",e.toString(),e);
		}
		
	}
}

package com.dunkware.trade.service.stream.server.controller.session.capture;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;

@Service
public class StreamSessionCaptureService {

	@Autowired
	private StreamControllerService streamService;
	
	@Autowired
	private ApplicationContext ac; 
	
	private List<StreamSessionCapture> runningCaptures = new ArrayList<StreamSessionCapture>();
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamCapture");
	
	@Autowired
	private Cluster cluster;
	
	@PostConstruct
	public void load() { 
		cluster.getEventTree().getRoot().addEventHandler(this);
	}
	
	public List<StreamSessionCapture> getRunningCaptures() { 
		return runningCaptures;
	}
	
	public StreamSessionCapture getRunningCapture(String stream) { 
		for (StreamSessionCapture streamSessionCapture : runningCaptures) {
			if(streamSessionCapture.getIdentifier().equals(stream)) { 
				return streamSessionCapture;
			}
		}
		return null;
		
	}
	
	public void sessionCompleted(StreamSessionCapture capture) { 
		logger.info(marker, "Received Stream Session Capture Completed by capture, removing from active list");
		runningCaptures.remove(capture);
	}
	
	
	@ADEventMethod()
	public void sessionStarted(final EStreamSessionStarted started) {
		final StreamSession session = started.getSession();
		Runnable runner = new Runnable() {
			
			@Override
			public void run() {
				logger.info(marker, "Stream Session Started Event Received");
				StreamSessionCapture capture = new StreamSessionCapture();
				ac.getAutowireCapableBeanFactory().autowireBean(capture);
				try {
					capture.controllerStart(session);	
					runningCaptures.add(capture);
				} catch (Exception e) {
					logger.error(marker, "Exception Starting Stream Session Controller  " + e.toString());
				}
				
			}
		};
		cluster.getExecutor().execute(runner);
		
	}
}

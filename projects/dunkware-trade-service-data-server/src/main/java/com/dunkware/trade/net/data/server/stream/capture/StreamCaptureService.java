package com.dunkware.trade.net.data.server.stream.capture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.net.data.server.stream.provider.StreamDataProvider;
import com.dunkware.trade.net.data.server.stream.provider.StreamDataProviders;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;

@Service()
public class StreamCaptureService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamCapture");
	
	private Map<String,StreamCapture> streamCaptures = new ConcurrentHashMap<String,StreamCapture>();
	
	@Autowired
	private StreamDataProviders dataProviders; 
	
	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	public void init() { 
		Thread runner = new Thread() { 
			
			public void run() { 
				
				for (StreamDataProvider provider : dataProviders.getProviders()) {
					StreamCapture capture = new StreamCapture(); 
					logger.info(marker, "Starting Stream {} Capture ", provider.getDescriptor().getIdentifier());
					ac.getAutowireCapableBeanFactory().autowireBean(capture);
					capture.init(provider);
					streamCaptures.put(provider.getDescriptor().getIdentifier(), capture);	
				}
				
				
			
				
			}
		};
		
		runner.start();
	}
	
}

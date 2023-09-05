package com.dunkware.trade.service.stream.server.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.stream.cluster.proto.controller.GetStreamDescriptors;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;

@Service
public class StreamControllerNetService {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamControllerNetService"); 
	
	
	@Autowired
	private StreamControllerService streams; 
	
	@Autowired
	private DunkNet dunkNet;
	
	@PostConstruct
	private void init() { 
		try {
			dunkNet.extensions().addExtension(this);	
		} catch (Exception e) {
			logger.error("Exception setting exteions on stream controller net service call it a day " );
			System.exit(-1);
		}
		
	}
	
	@ADunkNetService(label = "Get Stream Descriptors")
	public StreamDescriptors getDescriptors(GetStreamDescriptors get) { 
		return streams.getStreamDescriptors();
	}
}

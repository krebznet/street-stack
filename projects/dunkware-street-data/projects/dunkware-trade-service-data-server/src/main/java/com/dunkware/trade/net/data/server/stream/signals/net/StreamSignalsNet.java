package com.dunkware.trade.net.data.server.stream.signals.net;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalsProvider;
import com.dunkware.trade.service.data.model.signals.StreamSessionSignalTypeBeans;
import com.dunkware.trade.service.data.model.signals.cluster.StreamSessionSignalTypeBeansReq;

@Service
public class StreamSignalsNet {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalsNet");
	
	@Autowired
	private DunkNet net; 
	
	@Autowired
	private StreamSignalsProvider signalProvider; 
	
	@PostConstruct
	private void init() {
		try {
			//net.addExtension(this);			
		} catch (Exception e) {
			logger.error(marker, "Exception init StreamSignalsNet adding extensions " + e.toString());	
		}
	}
	
	
	
	@ADunkNetService(label = "Get Session Signal Type Beans")
	public StreamSessionSignalTypeBeans getSessionSignalTypeBeans(StreamSessionSignalTypeBeansReq req) throws Exception { 
 
		try {
			return signalProvider.getStreamSignals(req.getStream()).getSessionSignals().getSignalTypeBeans();
		} catch (Exception e) {
			throw new Exception("Signal Session Type not found for " + req.getStream());
		}

	}
	
	
}

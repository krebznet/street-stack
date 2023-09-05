package com.dunkware.trade.net.data.server.stream.capture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.net.data.server.stream.capture.writers.SignalWriter;
import com.dunkware.trade.net.data.server.stream.capture.writers.SnapshotWriter;
import com.dunkware.trade.net.data.server.stream.provider.StreamDataProvider;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

public class StreamCapture {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamCapture");
	
	private StreamDescriptor descriptor;
	
	private SignalWriter signalWriter;
	
	private StreamDataProvider dataProvider; 
	
	private SnapshotWriter snapshotWriter;
	
	public void init(StreamDataProvider dataProvider) { 
		this.dataProvider = dataProvider;
		this.descriptor = dataProvider.getDescriptor();
		
		signalWriter = new SignalWriter();
		try {
			signalWriter.start(dataProvider);
		} catch (Exception e) {
			logger.error(marker, "Exception creating signal writer " + e.toString());
		}
		
		// don't do snapshot for now
	}
}

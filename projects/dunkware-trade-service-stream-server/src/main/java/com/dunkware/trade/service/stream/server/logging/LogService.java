package com.dunkware.trade.service.stream.server.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {
	
	
	
	 
	public static Marker createMarker() { 
		return MarkerFactory.getMarker("STREAM-ID-" + "the stream instance id");
		
	}
	

}

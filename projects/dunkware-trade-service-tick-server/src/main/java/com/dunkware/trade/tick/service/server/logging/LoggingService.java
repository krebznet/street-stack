package com.dunkware.trade.tick.service.server.logging;

import javax.annotation.PostConstruct;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

	@Value("${logging.marker}")
	private String markerName; 
	
	
	@PostConstruct
	private void load() { 
	
	}
	
	public String getMarkerName() { 
		 return markerName;
	}
	
	 public Marker getMarker() { 
		return MarkerFactory.getMarker(markerName);
	}
}

package com.dunkware.trade.service.web.server.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	public static Marker createMarker() { 
		return MarkerFactory.getMarker("WEB-INSTANCE-ID");
		
	}
}

package com.dunkware.trade.service.stream.server.controller.session.container;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class SessionContainerMarker {
	
	public static Marker get() { 
		return MarkerFactory.getMarker("SessionContainer");
	}

}

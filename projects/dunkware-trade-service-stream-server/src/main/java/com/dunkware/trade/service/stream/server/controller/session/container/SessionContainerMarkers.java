package com.dunkware.trade.service.stream.server.controller.session.container;


import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class SessionContainerMarkers {
	
	
	public static Marker containerMarker() { 
		return MarkerFactory.getMarker("SessionContainer");
	}

}

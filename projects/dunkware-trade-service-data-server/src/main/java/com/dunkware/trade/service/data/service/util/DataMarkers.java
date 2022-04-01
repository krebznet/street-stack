package com.dunkware.trade.service.data.service.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class DataMarkers {
	
	public static Marker getServiceMarker() { 
		return MarkerFactory.getMarker("DataService");
		
	}

}

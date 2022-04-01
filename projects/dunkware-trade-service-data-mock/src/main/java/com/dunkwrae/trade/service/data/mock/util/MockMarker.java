package com.dunkwrae.trade.service.data.mock.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MockMarker {
	
	private static Marker marker = null;
	
	
	public static Marker getMarker() { 
		if(marker == null) { 
			marker = MarkerFactory.getMarker("Mock");
		}
		return marker;
		
	}

}

package com.dunkware.common.util.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class DMarkers {

	public static Marker marker(String prefix, int id) { 
		return MarkerFactory.getMarker(prefix + ": " + id);
	}
	
	// we need a UI 
	// variable ID; 
}

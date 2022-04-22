package com.dunkware.common.util.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Markers {
	
	public static Marker serviceStart() { 
		return MarkerFactory.getMarker("ServiceStart");
	}
	
	public static Marker systemCrash() { 
		return MarkerFactory.getMarker("Crash");
	}

}

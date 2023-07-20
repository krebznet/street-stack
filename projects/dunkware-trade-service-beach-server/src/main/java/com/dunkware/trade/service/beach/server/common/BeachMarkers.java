package com.dunkware.trade.service.beach.server.common;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class BeachMarkers {

	public static Marker events() {
		return MarkerFactory.getMarker("beach.events");
	}
}

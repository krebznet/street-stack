package com.dunkware.common.util.bitch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class BitchLogger {
	
	private static Logger logger = LoggerFactory.getLogger(BitchLogger.class);
	
	private static Marker marker = MarkerFactory.getMarker("bitch");
	public static void log(String message) { 
		logger.info(marker,message);
	}

}

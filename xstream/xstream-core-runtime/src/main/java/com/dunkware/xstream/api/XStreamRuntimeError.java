package com.dunkware.xstream.api;

import java.time.LocalTime;

public interface XStreamRuntimeError {

	 String getType();
	
	 LocalTime getTime();
	 
	 String getMessage();
	
}

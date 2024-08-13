package com.dunkware.time.client.lib.exception;

public class TimeException extends Exception  {

	public TimeException(String s) { 
		super(s);
	}
	
	public TimeException(String s, Throwable t) { 
		super(s,t);
	}
}

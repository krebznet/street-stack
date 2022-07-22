package com.dunkware.spring.channel;

public class ChannelException extends Exception {

	public ChannelException(String s) {
		super(s);
	}
	
	public ChannelException(String s, Throwable t) { 
		super(s,t);
	}
}

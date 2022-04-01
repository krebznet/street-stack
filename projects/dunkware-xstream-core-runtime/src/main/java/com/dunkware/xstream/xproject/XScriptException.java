package com.dunkware.xstream.xproject;

public class XScriptException extends Exception  {

	private static final long serialVersionUID = 1L;

	public XScriptException(String s) { 
		super(s);
	}
	
	
	public XScriptException(String s, Throwable t) { 
		super(s,t);
	}
}

package com.dunkware.trade.sdk.core.runtime.system;

public class SystemException extends Exception  {

	private static final long serialVersionUID = 1334238179579485308L;

	public SystemException(String s) { 
		super(s);
	}
	
	public SystemException(String s, Throwable t) { 
		super(s,t);
	}
}

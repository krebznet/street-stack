package com.dunkware.cluster.proto.message;

/**
 * Interceptors are called before Message handlers and listeners so they can 
 * return false to not forward message to handlers - do this for things like 
 * acknolwedgement messages, responses to requests etc. 
 * 
 * @author duncankrebs
 *
 */
public interface MessageInterceptor {

	
	public boolean intercept(Message message);
}

package com.dunkware.trade.service.stream.server.session;

import java.util.List;

/**
 * Hello Duncan Krebs 
 * @author dkrebs
 *
 */
public interface StreamSessionService {
	
	/**
	 * Create a new Stream Session 
	 * @return
	 */
	public StreamSession newSession(); 

	/**
	 * Creates the extensions 
	 * @return
	 */
	public List<StreamSessionExtension> createExtensions() throws StreamSessionException;
	
}

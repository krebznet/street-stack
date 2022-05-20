package com.dunkware.xstream.net.server;

import com.dunkware.net.proto.netstream.GNetClientConnect;

/** 
 * so now the server will do all the stuff. 
 * @author duncankrebs
 *
 */
public interface StreamServer {
	
	public void newConnection(StreamConnector connection, GNetClientConnect handshake) throws Exception;
	
}

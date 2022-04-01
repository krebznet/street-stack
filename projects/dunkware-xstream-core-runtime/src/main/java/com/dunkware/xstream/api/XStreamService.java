package com.dunkware.xstream.api;

/**
 * Used for extension types that don't need configurations and are 
 * by default added to the XStream if annotation is one classpath?
 * @author dkrebs
 *
 */
public interface XStreamService {

	public void init(XStream stream) throws XStreamException;

	public void preStart() throws XStreamException;

	public void start() throws XStreamException;

	public void preDispose();

	public void dispose();
}

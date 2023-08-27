package com.dunkware.xstream.api;

import java.util.List;

import org.slf4j.Marker;

import com.dunkware.xstream.model.metrics.XStreamMetrics;
import com.dunkware.xstream.model.query.XStreamEntityQueryModel;

import io.vertx.core.Future;

public interface XStream {
	
	void start(XStreamInput input) throws XStreamException;
	
	void dispose() throws XStreamException;
	
	void cancel();
	
	XStreamStatus getStatus();
	
	/**
	 * Gets a row by ID, throws XStreamRuntimeException
	 * if row does not exist. 
	 * @param id
	 * @return
	 */
	XStreamEntity getRow(String id);
	
	List<String> getRowIdentifiers();
	
	/**
	 * Returns a List of Rows -- can do better here. 
	 * @return
	 */
	List<XStreamEntity> getRows();
	
	/**
	 * This will return the stats my friend and cache as needed.
	 * @return
	 */
	XStreamStatService getStatProvider();
	
	/**
	 * Returns a String array of all the row ids that 
	 * exist at the current point in time. 
	 * @return
	 */
	String[] getRowIds();
	
	
	XStreamEntity createRow(String rowId, int rowIdentifier);
	
	XStreamExecutor getExecutor();
	
	boolean hasRow(String id);

	XStreamTickRouter getTickRouter();
	
	XStreamClock getClock();

	XStreamInput getInput();
	
	/**
	 * Adds an XStream Listener 
	 * @param listener
	 */
	void addStreamListener(XStreamListener listener);
	
	/**
	 * Removes a XStreamListener 
	 * @param listener
	 */
	void removeStreamListener(XStreamListener listener);

	/**
	 * Returns XStreamStats
	 * @param rowStats include row stats 
	 * @param varStats include var stats
	 * @param rows = * for all or ID1,ID2
	 * @return XStreamStats
	 * @throws XStreamException
	 */
	XStreamMetrics getStats(boolean rowStats, boolean varStats, String rows) throws XStreamException; 
	
	/**
	 * Returns the row count
	 * @return
	 */
	int getRowCount();
	
	/**
	 * Registers an XRowListener and adds it to all existing and future rows
	 * @param listener
	 */
	public void addRowListener(XStreamEntityListener listener);
	
	/**
	 * Removes a XRowListener from all existing and future rows
	 * @param listener
	 */
	public void removeRowListener(XStreamEntityListener listener);
	
	/**
	 * Returns a service and throws exception if not exists
	 * @param <T>
	 * @param service
	 * @return
	 * @throws XStreamException
	 */
	public <T> T getService(Class<T> service) throws XStreamException;
	
	/**
	 * Creates a XQuery and initializes the query for execution. 
	 * @param type
	 * @return
	 * @throws XQueryException
	 */
	
	
	/**
	 * Creates a unique session id so we can trace logging 
	 * @return
	 */
	public String getSessionId(); 
	
	
	public Marker getSessionMarker();
	
		
	/***			
	 * adds a signal listener
	 * @param list
	 */
	public void addSignalListener(XStreamSignalListener list);
	
	/**
	 * removes a signal listener 
	 * @param list
	 */
	public void removeSignalListener(XStreamSignalListener list);
	
	
	public XStreamExtension getExtension(Class clazz) throws XStreamException;
	
	/**
	 * Okay this should create the query, does it do the lookup as needed based on the criteria type? 
	 * @param model
	 * @return
	 * @throws XStreamQueryException
	 */
	public Future<XStreamEntityQuery> buildEntityQuery(XStreamEntityQueryModel model) throws XStreamQueryException;
		
	
}


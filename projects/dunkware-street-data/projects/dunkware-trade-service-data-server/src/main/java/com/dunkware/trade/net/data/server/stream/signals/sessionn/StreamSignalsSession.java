package com.dunkware.trade.net.data.server.stream.signals.sessionn;

import java.util.List;

import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.net.data.server.stream.signals.sessionn.query.SessionSignalQueryGrid;
import com.dunkware.trade.net.data.server.stream.signals.sessionn.query.SessionSignalTypeQueryGrid;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalSessionQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeSessionQuery;

public interface StreamSignalsSession {

	/**
	 * This is a session singal session streaming grid used for 
	 * viewing all signals for when clicking on a signal type
	 * when viewing on a signal type through an entity for all signals on an entity 
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public SessionSignalQueryGrid querySignalGrid(StreamSignalSessionQuery query) throws Exception; 
	
	/**
	 * Okay coming together this is s signal type session query stream grid 
	 * @param query
	 * @return
	 * @throws Excepption
	 */
	public SessionSignalTypeQueryGrid querySignalTypeGrid(StreamSignalTypeSessionQuery query) throws Exception; 
	
 	/**
 	 * Called when we indicate session is starting or stopping
 	 */
 	public void reset();
 	
 	/**
 	 * Cache of session signals
 	 * @return
 	 */
 	public List<StreamSignalBean> sessionSignals();
 	
 	
 	/**
 	 * Return the stream signals 
 	 * @return
 	 */
 	public StreamSignals getStreamSignals();
 	
}
 	
 	
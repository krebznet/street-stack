package com.dunkware.trade.net.data.server.stream.signals.sessionn;

import java.util.List;

import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalsSessionTypeBeans;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalsSessionQuery;

public interface StreamSignalsSession {

	/**
	 * This will give us all the session signals can be for a signal type or for a signal type/entity or whatever. 
	 * @param query
	 * @return
	 * @throws Exception
	 */
 	public StreamSignalsSessionBeans streamingSignalQuery(StreamSignalsSessionQuery query) throws Exception;
 
 	/**
 	 * So this will give us a streaming grid of type beans can be for entity or for all signal types 
 	 * @param query
 	 * @return
 	 * @throws Exception
 	 */
 	public StreamSignalsSessionTypeBeans streamingSignalTypeQuery(StreamSignalsSessionQuery query) throws Exception;

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
 	
 	
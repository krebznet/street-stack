package com.dunkware.trade.net.data.server.stream.signals;

import java.util.List;

import com.dunkware.trade.net.data.server.stream.signals.injestor.StreamSignalIngestor;
import com.dunkware.trade.net.data.server.stream.signals.session.StreamSessionSignals;
import com.dunkware.trade.net.data.server.stream.streamprovider.StreamDataProvider;
import com.dunkware.trade.service.data.model.search.EntitySignalCountRequest;
import com.dunkware.trade.service.data.model.search.EntitySignalCountResponse;
import com.dunkware.trade.service.data.model.search.SignalSearchRequest;
import com.dunkware.trade.service.data.model.search.SignalSearchResponse;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public interface StreamSignals {

	/**
	 * Returns the streamidentifier 
	 * @return
	 */
	public String getStreamIdentifier(); 
	
	/**
	 * returns the StreamDescriptor 
	 * @return
	 */
	public StreamDescriptor getStreamDescriptor();
	
	/**
	 * Inserts a signal into the mongo databae collection 
	 * @param signal
	 * @throws Exception
	 */
	public void insertSignal(StreamEntitySignal signal) throws Exception;
	
	/**
	 * Mostly used for testing, in reality we probably don't want to query the entire fuckin
	 * database. we also need to get mongo express working for data2 
	 * @return
	 * @throws Exception
	 */
	public List<StreamEntitySignal> signalDump(int limit) throws Exception; 
	
	/**
	 * Returns the stream data provider.
	 * @return
	 */
	public StreamDataProvider getDataProvider();
	
	public StreamSignalIngestor getSignalIngestor();
	
	/**
	 * okay returns the shit here. 
	 * @return
	 */
	public StreamSessionSignals getSessionSignals();
	
	//SD21-GIFT-05 sorry incremented accidentely but here is step 6!
	//SD21-GIFT-06 SreamSignals interface, if we have 3 sreams in the cluster we have 
	// 3 instances of these 1 for each stream, that is how we scope things and get data 
	// like signals and a lot of other stuff on a stream level. 
	/**
	 * Executes a signal search we can wrap it in a stop watch to get search time. 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SignalSearchResponse signalSearch(SignalSearchRequest request) throws Exception;
	
	
	/**
	 * //SD-33-03 - new interface method
	 * Gets list of entity signals. 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public EntitySignalCountResponse entitySignalCountSearchRequest(EntitySignalCountRequest request) throws Exception;
}

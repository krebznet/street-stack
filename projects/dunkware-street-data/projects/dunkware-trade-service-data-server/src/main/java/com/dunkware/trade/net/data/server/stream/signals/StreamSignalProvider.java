package com.dunkware.trade.net.data.server.stream.signals;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelClient;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalGrid;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalTypeStatsGrid;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalList;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalTypeStatsList;
import com.dunkware.trade.net.data.server.stream.streamprovider.StreamDataProvider;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

import ca.odell.glazedlists.EventList;

/**
 * Signal Provider for a Stream 
 * @author duncankrebs
 *
 */
public interface StreamSignalProvider {

	/**
	 * Returns the streamidentifier 
	 * @return
	 */
	public String streamIdentifier(); 
	
	/**
	 * returns the StreamDescriptor 
	 * @return
	 */
	public StreamDescriptor streamDescriptor();
	
	/**
	 * Returns the stream data provider.
	 * @return
	 */
	public StreamDataProvider dataProvider();
	
	
	/**
	 * Signal Ingestor 
	 * @return
	 */
	public StreamSignalIngestor getSignalIngestor();
	
	
	/**
	 * Executor
	 * @return
	 */
	public DExecutor getExecutor();
	
 
	/**
	 * Executes a signal search we can wrap it in a stop watch to get search time. 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<StreamSignalBean> signalDataQuery(StreamSignalQuery query) throws Exception;
	
	
	
	/***
	 * Returns the blueprint channel
	 * @return
	 * @throws Exception
	 */
	public StreamBlueprintChannelClient streamBlueprint() throws Exception;
	
	
	/**
	 * Okay returns the type stats list. 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public StreamSignalTypeStatsList signalTypeStatsList(StreamSignalTypeStatsQuery query) throws Exception;
	
	
	
	/**
	 * Creates a stream signal list 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public StreamSignalList signalList(StreamSignalQuery query) throws Exception;
	
	
	/***
	 * returns the stream date 
	 * @return
	 */
	public LocalDate streamDate();
	
	/**
	 * Returns the session signals 
	 * @return
	 */
	public EventList<StreamSignalBean> sessionSignals();
	
	
	/**
	 * Executes a session signal query 
	 * @param predicate
	 * @return
	 */
	public List<StreamSignalBean> sessionSignalQuery(Predicate<StreamSignalBean> predicate); 
	
	
	 
	
	
}

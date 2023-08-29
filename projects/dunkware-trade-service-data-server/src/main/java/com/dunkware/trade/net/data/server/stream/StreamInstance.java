package com.dunkware.trade.net.data.server.stream;

import com.dunkware.trade.net.data.server.stream.ingestors.EntitySignalIngestor;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

/**
 * Manages Data Ingestion/Searching/Aggregations For a specific stream 
 * 
 * @author duncankrebs
 *
 */

public interface StreamInstance {

	public void init(StreamDescriptor descriptor); 
	
	public int getStreamId();
	
	EntitySignalIngestor getSignalWriter();
	
	public StreamDescriptor getDescriptor();
	
	public String getStreamIdentifier();
	
	public String getStreamTimeZone();
	
	
}

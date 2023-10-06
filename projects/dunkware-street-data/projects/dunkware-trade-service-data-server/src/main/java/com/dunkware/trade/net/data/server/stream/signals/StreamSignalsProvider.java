package com.dunkware.trade.net.data.server.stream.signals;

import java.util.Collection;

public interface StreamSignalsProvider {
	
	public StreamSignals getStreamSignals(String sreamIdentifier) throws Exception;
	
	public Collection<StreamSignals> getStreams();
	

}

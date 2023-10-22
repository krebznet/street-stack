package com.dunkware.trade.net.data.server.stream.entitystats;

public interface StreamEntityStatsFileWriterListener {

	public void onComplete(StreamEntityStatsFileWriter writer);
	
	public void onException(StreamEntityStatsFileWriter writer);
}

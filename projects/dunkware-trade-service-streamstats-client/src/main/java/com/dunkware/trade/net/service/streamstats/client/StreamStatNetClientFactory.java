package com.dunkware.trade.net.service.streamstats.client;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class StreamStatNetClientFactory {

	
	public static StreamStatsClient create(String brokers, String reqTopic, String respTopic, String clientId, String groupId, long timeout, TimeUnit timeoutUnit, LocalDate date) throws StreamStatsClientException { 
		StreamStatsClientInput input = new StreamStatsClientInput();
		return null; 
				
	}
}

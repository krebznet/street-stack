package com.dunkware.trade.net.service.streamstats.client;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import com.dunkware.trade.net.service.streamstats.client.impl.StreamStatsClientBean;
import com.dunkware.trade.net.service.streamstats.client.impl.StreamStatsClientImpl;

public class StreamStatsCientFactory {

	
	public static StreamStatsClient create(String brokers, String reqTopic, String respTopic, String clientId, String groupId, long timeout, TimeUnit timeoutUnit, LocalDate date) throws StreamStatsClientException { 
		StreamStatsClientBean bean = new StreamStatsClientBean();
		bean.setBrokers(brokers);	
		bean.setRequestTopic(reqTopic);
		bean.setResponseTopic(reqTopic);
		bean.setClientId(clientId);
		bean.setGroupId(groupId);
		bean.setDate(date);
		bean.setTimeout(timeout);
		bean.setTimeoutUnit(timeoutUnit);
		StreamStatsClientImpl client = new StreamStatsClientImpl();
		client.load(bean);
		return client;
	}
}

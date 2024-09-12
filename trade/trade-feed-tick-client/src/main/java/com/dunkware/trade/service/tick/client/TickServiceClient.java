package com.dunkware.trade.service.tick.client;

import java.util.List;

import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;

public interface TickServiceClient {
	
	public void connect(String endpoint) throws TickServiceClientException;

	public TickServiceClientFeed createFeed(TickConsumerSpec spec) throws TickServiceClientException;

	public String getEndpoint();
	
	public Object postResponseObject(String path, Object request, Class responseClass) throws TickServiceClientException; 
	
	public void post(String path, Object request) throws TickServiceClientException;

	public TradeTickerListSpec getTickerList(String listId) throws TickServiceClientException;
	
	public List<TickFeedSubscriptionBean> getSubscriptions() throws TickServiceClientException;

	public void ping() throws Exception;
	
	public TradeTickerSpec getTickerBySymbol(String symbol) throws TickServiceClientException;
}

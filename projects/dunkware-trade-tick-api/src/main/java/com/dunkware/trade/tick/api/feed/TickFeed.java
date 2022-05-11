package com.dunkware.trade.tick.api.feed;

import java.util.Collection;
import java.util.List;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.model.feed.TickFeedBean;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface TickFeed  {

	public void start(TickProvider provider, DExecutor exector, String kafkaBrokers) throws TickFeedException;
	
	public void subscribe(Collection<TradeTickerSpec> specs) throws TickFeedException;
	
	public void addListener(TickFeedListener listener);
	
	public void removeListener(TickFeedListener listener);
	
	public Collection<TickFeedSubscription> getSubscriptions();
	
	public TickFeedSubscription getSubscription(String ticker) throws TickFeedException;
	
	public TickFeedBean getBean();
	
	public DExecutor getExecutor();
	
	public boolean hasSubscription(String ticker);
	
	public String getConsumerBrokers();
	
	public int getSymbolId(String symbol);
	
	public TradeTickerSpec getTickerSpec(String symbol);
}


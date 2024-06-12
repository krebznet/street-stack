package com.dunkware.trade.tick.api.feed;

import java.util.Collection;
import java.util.List;

import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedStats;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.utils.core.concurrent.DunkExecutor;


public interface TickFeed  {

	public void start(TickProvider provider, DunkExecutor exector, String kafkaBrokers) throws TickFeedException;
	
	public void subscribe(Collection<TradeTickerSpec> specs) throws TickFeedException;
	
	public void addListener(TickFeedListener listener);
	
	public void removeListener(TickFeedListener listener);
	
	public Collection<TickFeedSubscription> getSubscriptions();
	
	public TickFeedSubscription getSubscription(String ticker) throws TickFeedException;
	
	public TickFeedSubscriptionBean getSubscriptionBean(String symbol) throws TickFeedException; 
	
	public List<TickFeedSubscriptionBean> getSubscriptionBeans();
	
	public int getSubscriptionCount();
	
	public int getTradeTickCount();
	
	public TickFeedStats getStats();
	
	public DunkExecutor getExecutor();
	
	public boolean hasSubscription(String ticker);
	
	public String getConsumerBrokers();
	
	public int getSymbolId(String symbol);
	
	public TradeTickerSpec getTickerSpec(String symbol);
	
	public void onTrade(TickFeedTrade trade);
	
	public void onQuote(TickFeedQuote quote);
}


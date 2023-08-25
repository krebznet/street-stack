package com.dunkware.trade.service.web.server.feed;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.test.model.feed.Quote;
import com.dunkware.spring.test.model.feed.Subscription;
import com.dunkware.spring.test.model.feed.SubscriptionReset;

public class FeedClient implements DunkNetChannelHandler {

	private DunkNetChannel channel;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SpringTest");
	
	public Map<String,Quote> quotes = new ConcurrentHashMap<String,Quote>();
	
	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		channel.addExtension(this);
	}

	@Override
	public void channelStart() throws DunkNetException {
		logger.info(marker, "Feed Client Handler Start Invoked");
		
	}

	public void close() { 
		channel.closeChannel();
	}
	public void subscribe(String symbol) { 
		try {
			channel.serviceBlocking(Subscription.builder().symbol(symbol).build());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	public void reset() { 
		try {
			channel.serviceBlocking(new SubscriptionReset());
			quotes.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}
	
	public Collection<Quote> quotes() { 
		return quotes.values();
	}

	@Override
	public void channelStartError(String exception) {
		// TODO Auto-generated method stub
		
	}
	
	@ADunkNetEvent
	public void quote(Quote quote) {
		quotes.put(quote.getSymbol(), quote);
		System.out.println(quote.toString());
	}

	
}

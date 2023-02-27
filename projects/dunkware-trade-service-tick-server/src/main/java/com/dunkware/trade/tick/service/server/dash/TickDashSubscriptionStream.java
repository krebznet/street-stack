package com.dunkware.trade.tick.service.server.dash;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.spring.streaming.StreamingAdapter;
import com.dunkware.spring.streaming.StreamingAdapterListener;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.service.server.feed.FeedService;

public class TickDashSubscriptionStream implements StreamingAdapterListener  {
	
	private StreamingAdapter streamingAdapter; 
	
	private FeedService feedService;
	
	private Streamer streamer;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	public void start(FeedService feedService) {
		this.feedService = feedService;
		streamingAdapter = new StreamingAdapter("SubscriptionStream");
		streamingAdapter.addListener(this);
		this.streamer = new Streamer();
		streamer.start();
	}

	public StreamingAdapter getStreamingAdapter() { 
		return streamingAdapter;
	}

	@Override
	public void clientDisconnect(StreamingAdapter adapter) {
		streamer.interrupt();
	}

	@Override
	public void serverDisconnect(StreamingAdapter adapter) {
		streamer.interrupt();
	}
	
	private class Streamer extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					List<TickFeedSubscriptionBean> beans = feedService.getFeed().getSubscriptionBeans();
					streamingAdapter.send(beans);
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) { 
					return;
				}
				logger.error("Exception sending subscription beans in subscription stream " + e.toString());
				
			}
		}
	}

}

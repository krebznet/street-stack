package com.dunkware.spring.test.server.feed;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.concurrent.Semaphore;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.spring.cluster.protocol.descriptors.DunkVoid;
import com.dunkware.spring.test.model.feed.Quote;
import com.dunkware.spring.test.model.feed.Subscription;
import com.dunkware.spring.test.model.feed.SubscriptionReset;

public class FeedChannel implements DunkNetChannelHandler {

	private DunkNetChannel channel;
	private List<String> subs = new ArrayList<String>();
	private Semaphore subLock = new Semaphore(1);
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SpringTest");
	
	private Feeder feeder;
	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
 		this.channel = channel;
		channel.addExtension(this);
	}
	
	
	
	
	@ADunkNetService(label = "Subscribe Symbol")
	public String subscribe(Subscription subscription) { 
		try {
			subLock.acquire();
			subs.add(subscription.getSymbol());
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			subLock.release();
		}
		
		return subscription.getSymbol();
	}

	@Override
	public void channelStart() throws DunkNetException {
		
		logger.info(marker, "Feed Channel Server started");
		feeder = new Feeder();
		feeder.start();
	}

	@Override
	public void channelClose() {
		
		feeder.interrupt();
		  
	}
	
	@ADunkNetService(label = "Subscription Reset")
	public DunkVoid subscriptionReset(SubscriptionReset rest) {
		try {
			subLock.acquire();
			subs.clear();
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			subLock.release();
		}
		
		return new DunkVoid();
	}

	@Override
	public void channelStartError(String exception) {
		logger.error(marker, "Client Error notification " + exception);
		
	}
	
	
	private class Feeder extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					subLock.acquire();
					for (String string : subs) {
						try {
							channel.event(Quote.builder().symbol(string).price(DRandom.getRandom(3, 540003)).build());	
						} catch (Exception e) {
							if(e instanceof InterruptedException) { 
								return;
							}
							System.err.println("cant;t send qoute");
						}
						
						
					}
					
				} catch (Exception e) {
					if(e instanceof InterruptedException) { 
						return;
					}
					
				} finally { 
					subLock.release();
				}
				
				try {
					Thread.sleep(450);
				} catch (Exception e) {
					if(e instanceof InterruptedException) { 
						return;
					}
				}
			}
		}
	}

	
}

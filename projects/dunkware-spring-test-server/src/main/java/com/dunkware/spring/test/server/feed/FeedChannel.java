package com.dunkware.spring.test.server.feed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.spring.cluster.protocol.descriptors.DunkVoid;
import com.dunkware.spring.test.model.feed.Quote;
import com.dunkware.spring.test.model.feed.SubscriptionReset;

public class FeedChannel implements DunkNetChannelHandler {

	private DunkNetChannel channel;
	private List<String> subs = new ArrayList<String>();
	private Semaphore subLock = new Semaphore(1);
	
	private Feeder feeder;
	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		channel.addExtension(this);
	}
	
	
	@ADunkNetService
	public String subscribe(String symbol) { 
		try {
			subLock.acquire();
			subs.add(symbol);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			subLock.release();
		}
		
		return symbol;
	}

	@Override
	public void channelStart() throws DunkNetException {
		feeder = new Feeder();
		feeder.start();
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}
	
	@ADunkNetService()
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
		// TODO Auto-generated method stub
		
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
							System.err.println("cant;t send qoute");
						}
						
						
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					subLock.release();
				}
				
				try {
					Thread.sleep(450);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	
}

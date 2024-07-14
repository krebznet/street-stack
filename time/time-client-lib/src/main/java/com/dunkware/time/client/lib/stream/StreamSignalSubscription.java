package com.dunkware.time.client.lib.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.time.client.lib.listener.SignalListener;
import com.dunkware.time.data.model.entity.EntitySignalModel;

//TODO: AVINASHANV-05 SteramSignalSubscription
/**
 * This will have a instance of i think WebFlux of type EntitySignalModel
 * then a subscription i believe, i am trying to use the use case of having 
 * a signal subscription that clients or consumers can add SignalListener so 
 * when a signal is recieved it notifies all the listeners. he disopse method
 *  will close the http stream. 
 */

//TODO: AVINASHANV-06 Reactive without Spring 
/**
 * question is can we use reactive web flux without running in spring boot the server that 
 * sends signals does but some of the trading engine and other things won't always be in spring boot need
 * to figure this out and we can mock up teh code. 
 */
public class StreamSignalSubscription {
	
	private List<SignalListener> listeners = new ArrayList<SignalListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
//	private Object webFluxConsumer we can subscribe to; 
	// it should be of type 
	EntitySignalModel signal;
	
	public void addListener(SignalListener listener)  {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
	}
	
	public void removeListener(SignalListener listener) { 
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			listenerLock.release();
		}
	}
	
	public void dispose() { 
		 
	}
	
	

}

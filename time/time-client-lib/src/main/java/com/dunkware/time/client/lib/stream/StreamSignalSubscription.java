package com.dunkware.time.client.lib.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.time.client.lib.listener.SignalListener;
import com.dunkware.time.data.model.entity.EntitySignalModel;

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

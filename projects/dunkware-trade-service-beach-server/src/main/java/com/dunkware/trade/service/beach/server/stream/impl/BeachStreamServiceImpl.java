package com.dunkware.trade.service.beach.server.stream.impl;

import java.util.Vector;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.beach.server.stream.BeachSignalListener;
import com.dunkware.trade.service.beach.server.stream.BeachStreamService;

public class BeachStreamServiceImpl implements BeachStreamService  {
	
	
	@Autowired
	private DunkNet dunkNet; 

	private Vector<RegisteredSignalListener> listeners = new Vector<RegisteredSignalListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	
	@Override
	public void addSignalListener(BeachSignalListener listener, int... signalIds) {
		try {
			listenerLock.acquire();
			RegisteredSignalListener reg = new RegisteredSignalListener(listener, signalIds);
			this.listeners.add(reg);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public void removeSignalListener(BeachSignalListener listener) {
		try {
			listenerLock.acquire();
			RegisteredSignalListener remove = null;
			for (RegisteredSignalListener registeredSignalListener : listeners) {
				if(registeredSignalListener.getListnener().hashCode() == listener.hashCode()) { 
					remove = registeredSignalListener;
					break;
				}
			}
			if(remove != null) { 
				listeners.remove(remove);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			this.listenerLock.release();
		}
	}
	
	
	private class RegisteredSignalListener { 
		
		private BeachSignalListener listener; 
		private int[] signals;
		
		public RegisteredSignalListener(BeachSignalListener listener, int[] signals) { 
			this.listener = listener; 
			this.signals = signals; 
		}
		
		public boolean subscribed(int signalId) { 
			for (int i : signals) {
				if(i == signalId) { 
					return true; 
				}
			}
			return false; 
			
		}
		
		public BeachSignalListener getListnener() { 
			return listener;
		}
		
	}

	
	
}

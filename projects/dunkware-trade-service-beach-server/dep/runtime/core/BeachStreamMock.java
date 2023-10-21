package com.dunkware.trade.service.beach.server.runtime.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.beach.server.common.BeachRuntime;
import com.dunkware.trade.service.beach.server.runtime.BeachStream;
import com.dunkware.xstream.model.signal.StreamSignal;
import com.dunkware.xstream.model.signal.StreamSignalListener;
import com.dunkware.xstream.model.spec.StreamSpec;
import com.dunkware.xstream.model.spec.StreamSpecEntitySignal;

public class BeachStreamMock implements BeachStream  {


	@Autowired
	private BeachRuntime runtime;

	private List<StreamSignalListenerWrapper> signalListeners = new ArrayList<StreamSignalListenerWrapper>();
	private Semaphore signalListenerLock = new Semaphore(1);

	private ConcurrentHashMap<String, AtomicInteger> signalSubscriptions = new ConcurrentHashMap<String, AtomicInteger>();

	private StreamSpec streamSpec;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private Marker signalMarker = MarkerFactory.getMarker("beach.stream.signals");

	private String[] mock5secSymbols = new String[] {"AAPL","JPM", "GE","ATT"};
	
	private Mock5SecSignals signals;
	
	public void init(StreamSpec streamSpec) throws Exception {
		
		streamSpec = new StreamSpec();
		StreamSpecEntitySignal sig1 = new StreamSpecEntitySignal();
		sig1.setIdentifier("MOCK5SEC");
		streamSpec.getEntitySignals().add(sig1);
		this.streamSpec = streamSpec;
		
		signals = new Mock5SecSignals();
		signals.start();

	}

	@Override
	public boolean signalExists(String signal) {
		if(1 == 1) return true;
		for (StreamSpecEntitySignal specSignal : streamSpec.getEntitySignals()) {
			if (specSignal.getIdentifier().equalsIgnoreCase(signal)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public StreamSpec getSpec() {
		return streamSpec;
	}

	@Override
	public void addSignalListener(StreamSignalListener listener, String... signals) throws Exception {
		for (String signal : signals) {
			if (!signalExists(signal)) {
				throw new Exception(
						"Signal Listener Invalid Signal Subscription " + signal + " does not exist in stream spec");
			}
		}
		for (String signal : signals) {
			AtomicInteger count = signalSubscriptions.get(signal);
			if (count == null) {
				count = new AtomicInteger(1);
			}
			signalSubscriptions.put(signal, count);
		}
		StreamSignalListenerWrapper wrapper = new StreamSignalListenerWrapper(listener, signals);
		try {
			signalListenerLock.acquire();
			signalListeners.add(wrapper);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			signalListenerLock.release();
		}

	}

	@Override
	public void removeSignalListener(StreamSignalListener listener) {
		try {
			signalListenerLock.acquire();
			StreamSignalListenerWrapper wrapper = null;
			for (StreamSignalListenerWrapper StreamSignalListenerWrapper : signalListeners) {
				if (StreamSignalListenerWrapper.getListener().equals(listener)) {
					wrapper = StreamSignalListenerWrapper;
					break;
				}
			}
			if (wrapper != null) {
				for (String subscription : wrapper.getSubscriptions()) {
					AtomicInteger count = signalSubscriptions.get(subscription);
					if (count != null) {
						if (count.decrementAndGet() == 0) {
							signalSubscriptions.remove(subscription);
						} else {
							signalSubscriptions.put(subscription, count);
						}
					}

				}
				signalListeners.remove(wrapper);
			} else {
				logger.warn("Signal Listener Remove Request Not Found (The Listener " + listener.getClass().getName());
			}

			// decrement signal subscriptions

		} catch (Exception e) {
			logger.error("Remove Signal Listener Exception " + e.toString());
		} finally {
			signalListenerLock.release();
		}

	}

	private class StreamSignalListenerWrapper {

		private StreamSignalListener listener;
		private Map<String, String> subscriptions = new ConcurrentHashMap<String, String>();

		public StreamSignalListenerWrapper(StreamSignalListener listener, String[] subs) {
			this.listener = listener;
			for (String string : subs) {
				subscriptions.put(string, string);
			}
		}

		public boolean subscribed(String signalName) {
			if (subscriptions.containsKey(signalName)) {
				return true;
			}
			return false;
		}

		public StreamSignalListener getListener() {
			return listener;
		}

		public Collection<String> getSubscriptions() {
			return subscriptions.values();
		}

	}

	
	private class Mock5SecSignals extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					Thread.sleep(5000);
					for (String symbol : mock5secSymbols) {
						StreamSignal signal = new StreamSignal();
						signal.setEntId(1);
						signal.setEntIdent(symbol);
						signal.setId(4);
						signal.setIdent("MOCK5SEC");
						try {
							signalListenerLock.acquire();
							for (StreamSignalListenerWrapper wrapper : signalListeners) {
								if(wrapper.subscribed(signal.getIdent())) { 
									wrapper.getListener().onSignal(signal);;
								}
							}
						} catch (Exception e) {
							logger.error("Exception processing stream signal " + e.toString());
						} finally { 
							signalListenerLock.release();
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

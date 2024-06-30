package com.dunkware.xstream.core.xsignal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XSignal;
import com.dunkware.xstream.api.XSignalListener;
import com.dunkware.xstream.api.XSignalType;
import com.dunkware.xstream.api.XSignals;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.xScript.SignalType;

public class XSignalsImpl implements XSignals, XSignalListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private XStream stream;
	private Map<Integer, XSignalType> signalTypeIds = new ConcurrentHashMap<Integer, XSignalType>();
	private Map<String, XSignalType> signalTypeIdents = new ConcurrentHashMap<String, XSignalType>();

	private List<XSignalListener> signalListeners = new ArrayList<XSignalListener>();
	private Semaphore signalListenerLock = new Semaphore(1);

	public void init(XStream stream) throws Exception {
		this.stream = stream;
		List<SignalType> scriptSignalTypes = stream.getInput().getScript().getStreamSignals();
		for (SignalType signalType : scriptSignalTypes) {
			XSignalTypeImpl xSignalType = new XSignalTypeImpl();
			xSignalType.init(this,signalType, stream);
			xSignalType.addListener(this);
			signalTypeIdents.put(xSignalType.getName(), xSignalType);
			signalTypeIds.put(xSignalType.getId(), xSignalType);
		}

	}

	public void dispose() {
		for (XSignalType signalType : signalTypeIds.values()) {
			signalType.removeListener(this);
			((XSignalTypeImpl)signalType).dispose();
		}
	}

	@Override
	public void addSignalListener(XSignalListener listener) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				boolean gotIt = true;
				try {
					gotIt = signalListenerLock.tryAcquire(20, TimeUnit.SECONDS);
					if (!gotIt) {
						logger.error("Signal Lock Listener add listener in XSginals no good");
						stream.runtimeError("XSignals", "Signal Lock add listener in XSignals not aquired ");
						return;
					}
					signalListeners.add(listener);
				} catch (Exception e) {
					logger.error("Exception adding signal listener " + e.toString());
				} finally {
					if (gotIt)
						signalListenerLock.release();
				}
			}

		};

		stream.getExecutor().execute(runner);

	}

	@Override
	public void removeSignalListener(XSignalListener listener) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				boolean gotIt = true;
				try {
					gotIt = signalListenerLock.tryAcquire(20, TimeUnit.SECONDS);
					if (!gotIt) {
						logger.error("Signal Lock Listener remove listener in XSginals no good");
						stream.runtimeError("XSignals", "Signal Lock remove listener in XSignals not aquired ");
						return;
					}
					signalListeners.remove(listener);
				} catch (Exception e) {
					logger.error("Exception adding signal listener " + e.toString());
				} finally {
					if (gotIt)
						signalListenerLock.release();
				}
			}

		};

		stream.getExecutor().execute(runner);

	}

	@Override
	public Collection<XSignalType> getSignalTypes() {
		return signalTypeIdents.values();
	}

	@Override
	public void onSignal(final XSignal signal) {
		Runnable runner = new Runnable() { 
			
			public void run() {
				boolean gotIt = true;
				try {
					gotIt = signalListenerLock.tryAcquire(20, TimeUnit.SECONDS);
					if (!gotIt) {
						logger.error("Signal Lock Listener remove listener in XSginals no good");
						stream.runtimeError("XSignals", "Signal Lock remove listener in XSignals not aquired ");
						return;
					}
					for (XSignalListener xSignalListener : signalListeners) {
						xSignalListener.onSignal(signal);
					}
				} catch (Exception e) {
					logger.error("Exception adding signal listener " + e.toString());
				} finally {
					if (gotIt)
						signalListenerLock.release();
				}
			}
					
		};
		
		stream.getExecutor().execute(runner);
	}
	
	

}

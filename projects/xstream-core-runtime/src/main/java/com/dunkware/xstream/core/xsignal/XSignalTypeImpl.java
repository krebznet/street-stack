package com.dunkware.xstream.core.xsignal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XSignal;
import com.dunkware.xstream.api.XSignalListener;
import com.dunkware.xstream.api.XSignalType;
import com.dunkware.xstream.api.XSignals;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.xScript.SignalType;

public class XSignalTypeImpl implements XSignalType {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SignalType model; 
	private boolean scanning = false; 
	private List<XSignal> signals = new ArrayList<XSignal>();
	private XSignalTypeScannerImpl scanner; 
	private XStream stream;
	private XSignals xSignals;
	
	private List<XSignalListener> signalListeners = new ArrayList<XSignalListener>();
	private Semaphore signalListenerLock = new Semaphore(1);
	
	public void init(XSignals xSignals, SignalType model, XStream stream) throws Exception { 
		this.model = model;
		this.stream = stream; 
		this.xSignals = xSignals;
		
		if(model.getScanner() != null) { 
			scanning = true; 
			scanner = new XSignalTypeScannerImpl();
			scanner.init(this, model.getScanner(),xSignals,stream);
			
		}
		
	}
	
	public void dispose() {
		if(scanning) { 
			scanner.dispose();
		}
	}
	
	@Override
	public int getId() {
		return model.getId();
	}

	@Override
	public SignalType getModel() {
		return model;
	}

	@Override
	public void addListener(XSignalListener listener) {
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
	public void removeListener(XSignalListener listener) {
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
	public String getName() {
		return model.getName();
	}

	@Override
	public XSignal signal(XStreamEntity entity, LocalDateTime dateTime, Map<Integer, Number> varSnapshots) {
		final XSignal signal = new XSignalImpl(entity, this, dateTime, varSnapshots);
		Runnable notifyListeners = new  Runnable() {
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
		stream.getExecutor().execute(notifyListeners);
		return signal;
	}
	
	

}

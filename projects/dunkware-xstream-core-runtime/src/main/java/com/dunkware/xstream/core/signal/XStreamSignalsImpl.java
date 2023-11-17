package com.dunkware.xstream.core.signal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalListener;
import com.dunkware.xstream.api.XStreamSignalSearch;
import com.dunkware.xstream.api.XStreamSignals;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class XStreamSignalsImpl implements XStreamSignals  {
	
	private XStream stream; 
	private Map<Integer,XStreamSignalHandler> handlers = new ConcurrentHashMap<Integer,XStreamSignalHandler>();
	private List<XStreamSignalType> signalTypes = new ArrayList<XStreamSignalType>();
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker builders = MarkerFactory.getMarker("SignalHandlerBuilders");
	
	
	
	private List<XStreamSignalListener> listeners = new ArrayList<XStreamSignalListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private List<XStreamSignal> signals = new ArrayList<XStreamSignal>();
	private Semaphore signalLock = new Semaphore(1);
	
	public void start(XStream stream)  { 
		this.stream = stream; 
		this.signalTypes = stream.getInput().getSignalTypes();
	
		for (XStreamSignalType signalType : stream.getInput().getSignalTypes()) {
			
			handleSignalStart(signalType);
		}
	
	}
	
	
	
	@Override
	public List<XStreamSignal> search(XStreamSignalSearch search) {
		try {
			signalLock.acquire();
			return signals.stream().filter(search).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Fuck here search exception on signals " + e.toString());
			return new ArrayList<XStreamSignal>();
		} finally { 
			signalLock.release();
		}
	}






	public void entitySignal(XStreamSignalHandler handler, XStreamEntity entity) { 
		StreamEntitySignal signal = new StreamEntitySignal();
		signal.setDateTime(stream.getClock().getLocalDateTime());
		signal.setEntity(entity.getIdentifier());
		signal.setId((int)handler.getType().getId());
		Map<Integer,Object> varValues = entity.varValues();
		Map<Integer,Number> numValues = new HashMap<Integer,Number>();
		for (Integer varId : varValues.keySet()) {
			Object var = varValues.get(varId);
			if (var instanceof Number) {
				Number varValue = (Number) var;
				numValues.put(varId, varValue);
			}
		}
		signal.setVars(numValues);
		XStreamSignalImpl sig = new XStreamSignalImpl();
		sig.setEntity(entity);
		sig.setSignal(signal);
		try {
			signalLock.acquire();
			
			signals.add(sig);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			signalLock.release();
		}
		stream.getExecutor().execute(new SignalEmitter(sig));
		
	}

	@Override
	public Collection<XStreamSignalType> getSiganlTypes() {
		return signalTypes;
	}

	@Override
	public void addListener(XStreamSignalListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
		
	}

	@Override
	public void removeListener(XStreamSignalListener listener) {
		try {
			listenerLock.acquire();
			listeners.remove(listener);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public void startSignalType(XStreamSignalType type) throws Exception {
		handleSignalStart(type);
	}

	@Override
	public void stopSignalType(int id) throws Exception {
		XStreamSignalHandler handler = handlers.get(id);
		if(handler == null) { 
			throw new Exception("Signal Handler not found for id " + id);
		}
		handler.stop();
		handlers.remove(id);
		updateSignalTypeList();
	}

	private void updateSignalTypeList() { 
		signalTypes.clear();
		for (XStreamSignalHandler handler : handlers.values()) {
			signalTypes.add(handler.getType());
		}
	}
	
	
	private void handleSignalStart(XStreamSignalType signalType) { 
		if(logger.isDebugEnabled()) { 
			
			logger.debug(builders,"Handling Start Signal Type {} ", signalType);
		}
		XStreamSignalHandlerBuilder builder = new XStreamSignalHandlerBuilder();
		Future<XStreamSignalHandler> future = builder.build(this, stream, signalType);
		future.onComplete(new Handler<AsyncResult<XStreamSignalHandler>>() {
			
			@Override
			public void handle(AsyncResult<XStreamSignalHandler> event) {
				if(logger.isDebugEnabled()) { 
					logger.debug(builders,"Handling Start Callback event failed {} ", event.failed());
				}
				
				if(event.failed()) { 
					// xst
					if(logger.isInfoEnabled()) { 
						logger.info(builders, "Signal Handler Builder Exception {}",event.cause());
					}
					stream.runtimeError("SignalHandler", event.cause().toString());
					return;
				}
				if(event.succeeded()) { 
					XStreamSignalHandler handler = event.result();
					
					
					handlers.put((int)handler.getType().getId(), handler);
					if(logger.isDebugEnabled()) { 
						logger.debug(builders, "Signal Handler Built ID {}",handler.getType().getId());
					}
					
				}
			}
		});
	}
	
	private class SignalEmitter implements Runnable { 
		
		private XStreamSignal signal; 
		
		public SignalEmitter(XStreamSignal signal) { 
			this.signal = signal;
		}
		
		public void run() { 
			try {
				 
				signalLock.acquire(); 
				for (XStreamSignalListener xStreamSignalListener : listeners) {
					xStreamSignalListener.onSignal(signal);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally { 
				signalLock.release();
			}
		}
	}
	
}

package com.dunkware.xstream.core.mock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.utils.tick.stream.TickStream;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamRowSnapshot;
import com.dunkware.xstream.model.metrics.XStreamRowMetrics;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SignalType;

public class MockXStreamEntity implements XStreamEntity {

	private String id; 
	private int identifier; 
	private XStream stream; 
	
	
	@Override
	public XStreamExpression createExpressoin(ExpressionType type) throws Exception {
		
		return null;
	}



	private Logger logger = LoggerFactory.getLogger(getClass());

	private ConcurrentHashMap<String, XStreamEntityVar> vars = new ConcurrentHashMap<String, XStreamEntityVar>();

	private List<XStreamRowSignal> signals = new ArrayList<XStreamRowSignal>();
	private AtomicInteger signalCount = new AtomicInteger(0);
	private TickStream tickStream;

	private LocalTime realTimeCreate;
	private LocalTime streamTimeCreate;

	private List<XStreamEntityListener> rowListeners = new ArrayList<XStreamEntityListener>();
	private Semaphore rowListenerLock = new Semaphore(1);

	private List<XStreamEntityVarListener> varListeners = new ArrayList<XStreamEntityVarListener>();
	private Semaphore varListenerLock = new Semaphore(1);

	
	@Override
	public void start(String id, int identifier, XStream stream) {
		this.stream = stream; 
		this.identifier = identifier; 
		this.id = id; 
	}
	
	

	@Override
	public List<XStreamEntityVar> getNumericVars() {
		
		return null;
	}



	@Override
	public Map<Integer, Number> numericVarSnapshot() {
		
		return null;
	}



	@Override
	public void dispose() {
		
		
	}

	@Override
	public String getId() {
		return id; 
	}

	@Override
	public XStream getStream() {
		return stream; 
	}

	@Override
	public Collection<XStreamEntityVar> getVars() {
		return vars.values();
	}

	@Override
	public XStreamEntityVar getVar(String name) {
		return vars.get(name);
	}

	@Override
	public TickStream getTickStream() {
		
		return null;
	}

	@Override
	public LocalDateTime getLocalDateTime() {
		
		return null;
	}

	@Override
	public XStreamRowSnapshot snapshot() {
		
		return null;
	}

	@Override
	public XStreamRowMetrics getStats(boolean varStats) {
		
		return null;
	}

	

	@Override
	public Map<Integer, Object> varValues() {
		
		return null;
	}

	@Override
	public void addRowListener(XStreamEntityListener list) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					rowListenerLock.acquire();
					rowListeners.add(list);
				} catch (Exception e) {
					
				} finally {
					rowListenerLock.release();
				}
			}
		};

		stream.getExecutor().execute(runner);
	}

	@Override
	public void removeRowListener(XStreamEntityListener list) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					rowListenerLock.acquire();
					rowListeners.remove(list);
				} catch (Exception e) {
					
				} finally {
					rowListenerLock.release();
				}
			}
		};

		stream.getExecutor().execute(runner);
	}

	@Override
	public List<XStreamRowSignal> getSignals() {
		
		return null;
	}

	@Override
	public int getSignalCount() {
		
		return 0;
	}

	@Override
	public int getIdentifier() {
		
		return 0;
	}

	@Override
	public void addVarListener(XStreamEntityVarListener listener) {
		
		
	}

	@Override
	public void removeVarListener(XStreamEntityVarListener listener) {
		
		
	}



	@Override
	public void signal(SignalType type) {
		
		
	}

	
}

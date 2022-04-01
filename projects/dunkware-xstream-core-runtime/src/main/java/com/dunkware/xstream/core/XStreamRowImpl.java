package com.dunkware.xstream.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.tick.stream.impl.TickStreamImpl;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamRowSnapshot;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.model.metrics.XStreamRowMetrics;
import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;

public class XStreamRowImpl implements XStreamRow {

	private String id;
	private XStream stream;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ConcurrentHashMap<String, XStreamVar> vars = new ConcurrentHashMap<String, XStreamVar>();

	private List<XStreamRowSignal> signals = new ArrayList<XStreamRowSignal>();
	private AtomicInteger signalCount = new AtomicInteger(0);
	private TickStream tickStream;

	private DTime realTimeCreate;
	private DTime streamTimeCreate;

	private List<XStreamRowListener> rowListeners = new ArrayList<XStreamRowListener>();
	private Semaphore rowListenerLock = new Semaphore(1);

	private int identifier; 
	@Override
	public void start(String id, int identifier, XStream stream) {
		tickStream = new TickStreamImpl();
		this.identifier = identifier;
		this.id = id;
		this.stream = stream;
		realTimeCreate = DTime.now();
		streamTimeCreate = (DTime) stream.getClock().getTime();
		List<VarType> varTypes = stream.getInput().getScript().getStreamVars();
		for (VarType varType : varTypes) {
			XStreamVarImpl var = new XStreamVarImpl();
			var.init(this, varType);
			vars.put(varType.getName(), var);
		}
		for (XStreamVar var : vars.values()) {
			var.start();
		}
	}

	@Override
	public void dispose() {
		for (XStreamVar var : vars.values()) {
			var.dispose();
		}
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
	public Collection<XStreamVar> getVars() {
		return vars.values();
	}

	@Override
	public XStreamVar getVar(String name) {
		if (vars.containsKey(name) == false) {
			throw new XStreamRuntimeException("Variable " + name + " not found on row " + id);
		}
		return vars.get(name);
	}

	
	@Override
	public TickStream getTickStream() {
		return tickStream;
	}

	@Override
	public XStreamRowSnapshot snapshot() {
		Map<String, Object> varMap = new HashMap<String, Object>();
		for (String key : vars.keySet()) {
			XStreamVar var = vars.get(key);
			if (var.getSize() == 0) {
				varMap.put(var.getVarType().getName(), null);
			} else {
				varMap.put(var.getVarType().getName(), var.getValue(0));
			}
		}
		
		DTime time = stream.getClock().getTime();
		XStreamRowSnapshot snapshot = new XStreamRowSnapshot(getId(), time, varMap);
		return snapshot;
	}

	@Override
	public XStreamRowMetrics getStats(boolean varStats) {
		XStreamRowMetrics stats = new XStreamRowMetrics();
		stats.setStreamTimeCreate(streamTimeCreate);
		stats.setRealTimeCreate(realTimeCreate);
		stats.setRowId(id);
		stats.setVarCount(vars.size());
		stats.setTickCount(getTickStream().getTickCount());
		List<XStreamVarMetrics> varList = new ArrayList<XStreamVarMetrics>();
		if (varStats) {
			for (XStreamVar var : vars.values()) {
				varList.add(var.getStats());
			}
			stats.setVarStats(varList);
		}
		return stats;
	}

	@Override
	public void signal(SignalType type) {
		
		long timestamp = getStream().getClock().getTimestamp();
		DTime time = getStream().getClock().getTime();
		if(logger.isTraceEnabled()) { 
			String formmatedTime = DunkTime.formatDateTimeStamp(timestamp);
			String clockDateTimeFormatted = DunkTime.format(stream.getClock().getLocalDateTime(), DunkTime.YYYY_MM_DD_HH_MM_SS);
			logger.trace("Signal {} Entity {} Clock Time {} Formatted Timestamp {} Clock DateTime Formatted {}",
					type.getName(),this.getId(),time.getHour() + ":" + 
			time.getMinute() + ":" + time.getSecond(), formmatedTime, clockDateTimeFormatted);
		}
		
		final XStreamSignalImpl signal = new XStreamSignalImpl(this, type, timestamp, time,stream.getClock().getLocalDateTime());
		this.signals.add(signal);
	
		signalCount.incrementAndGet();
		
		try {
			rowListenerLock.acquire();
			for (XStreamRowListener xStreamRowListener : rowListeners) {
				xStreamRowListener.rowSignal(XStreamRowImpl.this, signal);
			}
		} catch (Exception e) {
			logger.error("NASTY Row Listener Signal Outer Exception " + e.toString(),e);
		} finally {
			rowListenerLock.release();
		}

	}

	@Override
	public void addRowListener(XStreamRowListener list) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					rowListenerLock.acquire();
					rowListeners.add(list);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					rowListenerLock.release();
				}
			}
		};

		stream.getExecutor().execute(runner);
	}

	@Override
	public void removeRowListener(XStreamRowListener list) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					rowListenerLock.acquire();
					rowListeners.remove(list);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					rowListenerLock.release();
				}
			}
		};

		stream.getExecutor().execute(runner);
	}

	@Override
	public List<XStreamRowSignal> getSignals() {
		return signals;
	}

	@Override
	public int getSignalCount() {
		return signalCount.get();
	}

	@Override
	public int getIdentifier() {
		return identifier;
	}
	
	
	
	
	

}

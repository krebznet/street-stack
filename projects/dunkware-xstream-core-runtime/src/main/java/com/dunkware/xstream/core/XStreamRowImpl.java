package com.dunkware.xstream.core;

import java.time.LocalDateTime;
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
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.tick.stream.impl.TickStreamImpl;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamRowSnapshot;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.model.metrics.XStreamRowMetrics;
import com.dunkware.xstream.model.metrics.XStreamVarMetrics;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;

public class XStreamRowImpl implements XStreamEntity, XStreamEntityVarListener {

	private String id;
	private XStream stream;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker volumeUpdateMarker = MarkerFactory.getMarker("VolumeSnapshotValue");

	private ConcurrentHashMap<String, XStreamEntityVar> vars = new ConcurrentHashMap<String, XStreamEntityVar>();
	private List<XStreamEntityVar> numericVars = new ArrayList<XStreamEntityVar>();
	private List<XStreamRowSignal> signals = new ArrayList<XStreamRowSignal>();
	private AtomicInteger signalCount = new AtomicInteger(0);
	private TickStream tickStream;

	private DTime realTimeCreate;
	private DTime streamTimeCreate;

	private List<XStreamEntityListener> rowListeners = new ArrayList<XStreamEntityListener>();
	private Semaphore rowListenerLock = new Semaphore(1);

	private List<XStreamEntityVarListener> varListeners = new ArrayList<XStreamEntityVarListener>();
	private Semaphore varListenerLock = new Semaphore(1);

	
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
		for (XStreamEntityVar var : vars.values()) {
			var.start();
			if(var.isNumeric()) { 
				numericVars.add(var);
			}
			
			var.addVarListener(this);
		}
	}
	
	

	@Override
	public XStreamExpression createExpressoin(ExpressionType type) throws Exception {
		XStreamExpression exp = stream.getInput().getRegistry().createVarExpression(type);
		exp.init(this, type);
		return exp;
	}



	@Override
	public void dispose() {
		for (XStreamEntityVar var : vars.values()) {
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
	public Collection<XStreamEntityVar> getVars() {
		return vars.values();
	}
	
	

	@Override
	public List<XStreamEntityVar> getNumericVars() {
		return numericVars;
		
	}

	@Override
	public XStreamEntityVar getVar(String name) {
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
	public Map<Integer, Object> varValues() {
		Map<Integer, Object> varMap = new HashMap<Integer, Object>();
		for (String key : vars.keySet()) {
			XStreamEntityVar var = vars.get(key);
			if (var.getSize() > 0) {
				varMap.put(var.getVarType().getCode(),vars.get(key));
			}
		}
		return varMap;
			
	}

	@Override
	public XStreamRowSnapshot snapshot() {
		Map<String, Object> varMap = new HashMap<String, Object>();
		for (String key : vars.keySet()) {
			XStreamEntityVar var = vars.get(key);
			if (var.getSize() == 0) {
				varMap.put(var.getVarType().getName(), null);
			} else {
				varMap.put(var.getVarType().getName(), var.getValue(0));
			}
		}

		DTime time = stream.getClock().getTime();
		XStreamRowSnapshot snapshot = new XStreamRowSnapshot(getId(),getIdentifier(), time, varMap);
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
			for (XStreamEntityVar var : vars.values()) {
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
		if (logger.isTraceEnabled()) {
			String formmatedTime = DunkTime.formatDateTimeStamp(timestamp);
			String clockDateTimeFormatted = DunkTime.format(stream.getClock().getLocalDateTime(),
					DunkTime.YYYY_MM_DD_HH_MM_SS);
			logger.trace("Signal {} Entity {} Clock Time {} Formatted Timestamp {} Clock DateTime Formatted {}",
					type.getName(), this.getId(), time.getHour() + ":" + time.getMinute() + ":" + time.getSecond(),
					formmatedTime, clockDateTimeFormatted);
		}

		final XStreamSignalImpl signal = new XStreamSignalImpl(this, type, timestamp, time,
				stream.getClock().getLocalDateTime());
		this.signals.add(signal);

		signalCount.incrementAndGet();

		try {
			rowListenerLock.acquire();
			for (XStreamEntityListener xStreamRowListener : rowListeners) {
				xStreamRowListener.rowSignal(XStreamRowImpl.this, signal);
			}
		} catch (Exception e) {
			logger.error("NASTY Row Listener Signal Outer Exception " + e.toString(), e);
		} finally {
			rowListenerLock.release();
		}

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
					// TODO: handle exception
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

	

	@Override
	public Map<Integer, Number> numericVarSnapshot() {
		Map<Integer,Number> values = new HashMap<Integer,Number>();
		for (XStreamEntityVar var : numericVars) {
			if(var.getSize() > 0) { 
				values.put(var.getVarType().getCode(), var.getNumber(0));
			}
		}
		return values;
	}

	@Override
	public void varUpdate(XStreamEntityVar var) {
		Runnable run = new Runnable() {
			public void run() {
				try {
					varListenerLock.acquire();
					for (XStreamEntityVarListener varListener : varListeners) {
						varListener.varUpdate(var);
					}
				} catch (Exception e) {
					logger.error("Exception in var update dispatch");
				} finally {
					varListenerLock.release();
				}

			}
		};
		getStream().getExecutor().execute(run);
	}

	@Override
	public void addVarListener(XStreamEntityVarListener listener) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					varListenerLock.acquire();
					varListeners.add(listener);
				} catch (Exception e) {

				} finally {
					varListenerLock.release();

				}
			}
		};

		stream.getExecutor().execute(run);

	}

	@Override
	public void removeVarListener(XStreamEntityVarListener listener) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					varListenerLock.acquire();
					varListeners.remove(listener);
				} catch (Exception e) {

				} finally {
					varListenerLock.release();

				}
			}
		};

		stream.getExecutor().execute(run);

	}

	@Override
	public LocalDateTime getLocalDateTime() {
		return stream.getClock().getLocalDateTime();
	}



}

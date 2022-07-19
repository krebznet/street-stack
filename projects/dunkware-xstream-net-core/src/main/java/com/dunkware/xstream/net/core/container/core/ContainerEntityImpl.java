package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetBean;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntityCriteriaVar;
import com.dunkware.net.proto.stream.GEntityCriteriaVarType;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerEntityListener;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.ContainerEntitySnapshot;
import com.dunkware.xstream.net.core.container.ContainerEntityVar;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.util.ContainerHelper;

public class ContainerEntityImpl implements ContainerEntity {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String, ContainerEntityVar> vars = new ConcurrentHashMap<String, ContainerEntityVar>();
	private Map<String, Object> varLastValues = new ConcurrentHashMap<String, Object>();

	private Semaphore snapshotListLock = new Semaphore(1);
	private Map<LocalDateTime, ContainerEntitySnapshot> snapshots = new ConcurrentHashMap<LocalDateTime, ContainerEntitySnapshot>();
	private Semaphore signalLock = new Semaphore(1);
	private Vector<ContainerEntityListener> listeners = new Vector<ContainerEntityListener>();
	private Semaphore listenerLock = new Semaphore(1);

	private Map<String, List<ContainerEntitySignal>> signals = new ConcurrentHashMap<String, List<ContainerEntitySignal>>();

	private int id;
	private String identifier;

	private GEntitySnapshot lastSnapshot;
	private LocalDateTime lastSnapshotTime;

	private Container container;

	public void init(Container container, int id, String identifier) {
		
		this.id = id;
		this.identifier = identifier;
		this.container = container;
		
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Collection<ContainerEntityVar> getVars() {
		return vars.values();
	}

	@Override
	public Object getLastVarValue(String ident) {
		return vars.get(ident).getLastValue();
	}

	@Override
	public Map<String, Object> getLastVarValues() {
		return varLastValues;
	}

	@Override
	public boolean varExists(String ident) {
		if (vars.get(ident) == null) {
			return false;
		}
		return true;
	}

	@Override
	public ContainerEntityVar getVar(String ident) {
		return vars.get(ident);
	}

	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public int getSnapshotCount() {
		return snapshots.values().size();
	}
	
	
	
	@Override
	public int getSignalCount(LocalDateTime from, LocalDateTime to, String... signalTypes) {
		int count = 0;
		for (String signalType : signalTypes) {
			List<ContainerEntitySignal> signalTypeSignals = signals.get(signalType);
			if (signalTypeSignals != null) {
				for (ContainerEntitySignal sig : signalTypeSignals) {
					if (DunkTime.inTimeDateRange(from, to, sig.getTime())) {
						count++;
					}
				}
			}
		}
		return count;

	}
	

	@Override
	public List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop) {
		List<ContainerEntitySignal> results = new ArrayList<ContainerEntitySignal>();
		for (List<ContainerEntitySignal> list : signals.values()) {
			for (ContainerEntitySignal sig : list) {
				if(DunkTime.inTimeDateRange(start,stop,sig.getTime())) { 
					results.add(sig);
				}
			}
			
		}
		return results;

	}

	@Override
	public List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop, String... signalTypes) {
		List<ContainerEntitySignal> results = new ArrayList<ContainerEntitySignal>();
		for (String signalType : signalTypes) {
			List<ContainerEntitySignal> signalTypeSignals = signals.get(signalType);
			if (signalTypeSignals != null) {
				for (ContainerEntitySignal sig : signalTypeSignals) {
					if (DunkTime.inTimeDateRange(start, stop, sig.getTime())) {
						results.add(sig);
					}
				}
			}
		}
		return results;
	}

	@Override
	public void addListener(ContainerEntityListener listener) {
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
	public void removeListener(ContainerEntityListener listener) {
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
	public String getIdent() {
		return identifier;
	}

	@Override
	public void consumeSignal(GEntitySignal signal) {
		try {
			this.signalLock.acquire();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			signalLock.release();
		}
	}

	@Override
	public GEntitySnapshot getLastSnapshot() {
		return lastSnapshot;
	}

	@Override
	public void consumeSnapshot(GEntitySnapshot snap) {
		try {
			lastSnapshot = snap;

			LocalDateTime time = DunkTime.toLocalDateTime(snap.getTime(), container.getTimeZone());
			lastSnapshotTime = time;
			for (GEntityVarSnapshot var : snap.getVarsList()) {
				Object varValue = ContainerHelper.getSnapshotVarValue(var);
				if (varValue == null) {
					logger.error("Null Variable Detected in snapsht var name " + var.getIdentifier());
					return;
				}
				varLastValues.put(var.getIdentifier(), varValue);
				ContainerEntityVar cVar = vars.get(var.getIdentifier());
				if (cVar == null) {

					ContainerEntityVarImpl varImpl = new ContainerEntityVarImpl(this, var.getIdentifier(), var.getId(),
							varValue, time);
					vars.put(var.getIdentifier(), varImpl);
				} else {
					cVar.setValue(time, varValue);
				}
			}
			snapshotListLock.acquire();

		} catch (Exception e) {
			logger.error("Exception consuming snapshot " + e.toString());
		} finally {
			snapshotListLock.release();
		}
	}



	@Override
	public Object resolveCriteriaVar(GEntityCriteriaVar var) throws ContainerException, ContainerSearchException {
		if (var.getType() == GEntityCriteriaVarType.VALUE_NOW) {
			return varLastValues.get(var.getIdent());
		}
		throw new ContainerSearchException("GNetEntityVarValue type " + var.getType().name() + " not implemented");

		/*
		 * if(var.getType() == GEntityCriteriaVarType.VALUE_RELATIVE) { GCalendarRange
		 * range = var.getTimeRange(); if(range.getType() ==
		 * GCalendarRangeType.TIME_DURATION == false) { throw new
		 * ContainerSearchException("Criteria Var configured as VALUE_RELATIVE but range type is "
		 * + range.getType().name() ); } // okay so we assume that every 1 second is
		 * that. GDurationRange duration = range.getDurationRange(); int durationSeconds
		 * = 0; try { durationSeconds = GProtoHelper.getDurationRangeSeconds(duration);
		 * } catch (Exception e) { throw new
		 * ContainerSearchException("Exception getting duration range seconds " +
		 * e.toString()); } if(getSnapshotCount() < durationSeconds) { return null; //
		 * null means it can't resolve. } else { //ContainerEntitySnapshot snapshot =
		 * getBackSnapshot(durationSeconds); //Object value =
		 * snapshot.getVars().getValue(var.getIdent()); //if(value == null) { // throw
		 * new
		 * ContainerSearchException("Relative value variable not found on snapshot var "
		 * + var.getIdent()); //} }
		 * 
		 * } // TODO Auto-generated method stub return null;
		
		 *
		 */
	}

	@Override
	public NetBean toBean() {
		NetBean bean = new NetBean();
		bean.setValue("id", getId());
		bean.setValue("ident", getIdent());
		for (ContainerEntityVar var : getVars()) {
			bean.setValue(var.getIdent(), var.getLastValue());
		}
		return bean;
	}

	@Override
	public NetBean toBean(List<String> vars) {
		NetBean bean = new NetBean();
		bean.setValue("id", getId());
		bean.setValue("ident", getIdent());
		for (ContainerEntityVar var : getVars()) {
			if(vars.contains(var.getIdent()))
				bean.setValue(var.getIdent(), var.getLastValue());
		}
		return bean;
	}
	
	
	

}

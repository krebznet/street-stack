package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.netstream.GNetEntityVarValue;
import com.dunkware.net.proto.netstream.GNetEntityVarValueType;
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

public class ContainerEntityImpl implements ContainerEntity  {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String,ContainerEntityVar> vars = new ConcurrentHashMap<String,ContainerEntityVar>();
	private Map<String,Object> varLastValues = new ConcurrentHashMap<String, Object>();
	
	private Semaphore snapshotListLock = new Semaphore(1);
	private Map<LocalDateTime,ContainerEntitySnapshot> snapshots = new ConcurrentHashMap<LocalDateTime,ContainerEntitySnapshot>();
	private Semaphore signalLock = new Semaphore(1);
	private Vector<ContainerEntityListener> listeners = new Vector<ContainerEntityListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private int id; 
	private String identifier; 
	
	
	private GEntitySnapshot lastSnapshot;
	private LocalDateTime lastSnapshotTime; 
	
	private Container container; 
	
	private boolean snapshotHistory; 
	
	public void init(Container container, int id, String identifier) { 
		this.id = id;
		this.identifier = identifier;
		this.container = container; 
		this.snapshotHistory = container.getInput().isSnapshotHistory();
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
		if(vars.get(ident) == null) { 
			return false; 
		}
		return true;
	}

	

	@Override
	public ContainerEntityVar getVar(String ident)  {
		return  vars.get(ident);
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
	public List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop) {
		return null;
		/*
		 * try { List<ContainerEntitySignal> results = new
		 * ArrayList<ContainerEntitySignal>(); signalLock.acquire(); for
		 * (ContainerEntitySignal signal : signals) { if(signal.getTime().isAfter(start)
		 * == true && signal.getTime().isAfter(stop) == false) { results.add(signal); }
		 * } return results; } catch (Exception e) { //TOOD: log return new
		 * ArrayList<ContainerEntitySignal>(); } finally { signalLock.release(); }
		 */
	}

	@Override
	public List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop, String... signalTypes) {
		return null;
		/*
		 * try { List<ContainerEntitySignal> results = new
		 * ArrayList<ContainerEntitySignal>(); signalLock.acquire(); for
		 * (ContainerEntitySignal cacheEntitySignal : signals) { boolean ofType = false;
		 * for (String type : signalTypes) {
		 * if(cacheEntitySignal.getIdent().equals(type)) { ofType = true; break; } }
		 * if(!ofType) { continue; }
		 * 
		 * if(cacheEntitySignal.getTime().isAfter(start) == true &&
		 * cacheEntitySignal.getTime().isAfter(stop) == false) {
		 * results.add(cacheEntitySignal); } } return results; } catch (Exception e) {
		 * //TOOD: log return new ArrayList<ContainerEntitySignal>(); } finally {
		 * signalLock.release(); }
		 */
	}


	@Override
	public void addListener(ContainerEntityListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		}	 finally { 
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
		}	 finally { 
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
			
			LocalDateTime time = DunkTime.toLocalDateTime(snap.getTime(),container.getTimeZone());
			lastSnapshotTime = time;
			for (GEntityVarSnapshot var : snap.getVarsList()) {
				Object varValue = ContainerHelper.getSnapshotVarValue(var);
				if(varValue == null) { 
					logger.error("Null Variable Detected in snapsht var name " + var.getIdentifier());
					return;
				}
				varLastValues.put(var.getIdentifier(), varValue);
				ContainerEntityVar cVar = vars.get(var.getIdentifier());
				if(cVar == null) { 
					
					ContainerEntityVarImpl varImpl = new ContainerEntityVarImpl(this,var.getIdentifier(),var.getId(),varValue,time);
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
	public int getSignalCount(LocalDateTime from, LocalDateTime to, String... types) {
		return 0;
		/*
		 * try { signalLock.acquire(); int counter = 0; for (ContainerEntitySignal
		 * cacheEntitySignal : signals) { boolean ofType = false; for (String type :
		 * types) { if(cacheEntitySignal.getIdent().equals(type)) { ofType = true;
		 * break; } } if(!ofType) { continue; }
		 * 
		 * if(cacheEntitySignal.getTime().isAfter(from) == true &&
		 * cacheEntitySignal.getTime().isAfter(to) == false) { counter++; } } return
		 * counter; } catch (Exception e) { return 0; } finally { signalLock.release();
		 * }
		 */
		
	}
	
	

	@Override
	public Object resolveCriteriaVar(GNetEntityVarValue var) throws ContainerException, ContainerSearchException {
		if(var.getType() == GNetEntityVarValueType.VALUE_NOW) { 
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
		 */
	}
	
	

	


	
	

	

	
	
	

}

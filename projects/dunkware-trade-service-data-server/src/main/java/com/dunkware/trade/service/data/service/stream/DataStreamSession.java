package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
import com.dunkware.trade.service.data.service.repository.DataStreamSessionEntity;
import com.dunkware.trade.service.data.service.repository.DataStreamSessionInstrumentEntity;
import com.dunkware.trade.service.data.service.stream.writers.DataStreamSessionSnapshotWriter;
import com.dunkware.trade.service.data.service.util.DataMarkers;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.xstream.data.cache.CacheStream;

public class DataStreamSession {
	
	
	@Autowired
	private RuntimeConfig configService; 
	
	@Autowired
	private DataServiceRepository dataRepo;
	
	@Autowired
	private ApplicationContext ac;
	
	private Map<String,DataStreamSessionInstrument> instruments = new ConcurrentHashMap<String,DataStreamSessionInstrument>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DataStream stream; 
	private StreamSessionSpec spec; 
	private CacheStream cache;
	
	private LocalDateTime startTime; 
	
	private DataStreamSessionSnapshotWriter snapshotWriter; 
	
	// Entities 
	private DataStreamSessionEntity sessionEntity; 
	
	
	private EntityPersister entityPersister;
	private SessionMonitor sessionMonitor;
	
	private boolean snapshotWriterComplete = false; 
	private boolean signalWriterComplete = false; 
	
	public void controllerStart(DataStream stream, StreamSessionSpec spec) throws Exception { 
		this.stream = stream;
		this.spec = spec; 
		this.startTime = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone())), LocalTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		
		// okay so we create our new entity
		sessionEntity = new DataStreamSessionEntity();
		sessionEntity.setControllerStartTime(startTime);
		sessionEntity.setStream(stream.getEntity());
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		sessionEntity.setSessionIdentifier(spec.getSessionId());
		sessionEntity.setState(DataStreamSessionState.Running);
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		try {
			dataRepo.persist(sessionEntity);
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), " Data Repo Persist new stream entity failed we are fucked " + e.toString());
			throw e;
		}
		
		try {
			snapshotWriter = new DataStreamSessionSnapshotWriter();
			ac.getAutowireCapableBeanFactory().autowireBean(snapshotWriter);
			snapshotWriter.start(this);
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), "Exception starting data stream session snapshot writer " + e.toString());
			throw e;
		}
		
		entityPersister = new EntityPersister();
		entityPersister.start();
		
		sessionMonitor = new SessionMonitor();
		sessionMonitor.start();
		
	}
	
	public DataStreamSessionState getState() { 
		return sessionEntity.getState();
	}
	
	/**
	 * Called by the writer when its written all messages
	 * @param writer
	 */
	public void entitySnapshot(GEntitySnapshot snapshot) { 
	
		DataStreamSessionInstrument inst = instruments.get(snapshot.getIdentifier());
		if(inst == null) { 
			inst = new DataStreamSessionInstrument();
			ac.getAutowireCapableBeanFactory().autowireBean(inst);
			DataStreamSessionInstrumentEntity ent = new DataStreamSessionInstrumentEntity();
			ent.setInstId(snapshot.getId());
			ent.setInstIdentifier(snapshot.getIdentifier());
			ent.setScriptVersion(DataStreamSession.this.spec.getStreamScript().getVersion());
			ent.setSessionIdentifier(getIdentifier());
			ent.setStreamName(stream.getName());
			ent.setSignalCount(0);
			ent.setSnapshotCount(0);
			inst.start(DataStreamSession.this, ent);
			instruments.put(snapshot.getIdentifier(), inst);
			
		}		
		inst.snapshot(snapshot);
		this.sessionEntity.setSnapshotCount(sessionEntity.getSnapshotCount() + 1);
	}
	
	public void entitySignal(GEntitySignal signal) {
		DataStreamSessionInstrument inst = instruments.get(signal.getEntityIdentifier());
		if(inst == null) { 
			inst = new DataStreamSessionInstrument();
			ac.getAutowireCapableBeanFactory().autowireBean(inst);
			DataStreamSessionInstrumentEntity ent = new DataStreamSessionInstrumentEntity();
			ent.setInstId(signal.getEntityId());
			ent.setInstIdentifier(signal.getEntityIdentifier());
			ent.setScriptVersion(DataStreamSession.this.spec.getStreamScript().getVersion());
			ent.setSessionIdentifier(getIdentifier());
			ent.setStreamName(stream.getName());
			ent.setSignalCount(0);
			ent.setSnapshotCount(0);
			
			inst.start(DataStreamSession.this, ent);
			inst.signal(signal);
			instruments.put(signal.getEntityIdentifier(), inst);
			this.sessionEntity.setSignalCount(sessionEntity.getSignalCount() + 1);
			
		}		
	}
	
	
	public void controllerStop() { 
		sessionEntity.setControllerStopTime(LocalDateTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		sessionEntity.setState(DataStreamSessionState.Persisting);
		// who will update session enetity
	}
	
	public DataStream getStream() { 
		return this.stream;
	}
	
	public DataStreamSessionStats getStats() { 
		return new DataStreamSessionStats();
	}
	
	public LocalDateTime getStartDateTime() { 
		return startTime; 
	}
	
	public LocalTime getStartTime() { 
		return startTime.toLocalTime();
	}
	
	public StreamSessionSpec getSpec() { 
		return spec;
	}
	
	public void snapshotWriterComplete() { 
		snapshotWriterComplete = true;
	}
	
	public void signalWriterComplete() { 
		signalWriterComplete = true;
	}
	
	public CacheStream getCache() { 
		return cache; 
	}
	
	public String getIdentifier() { 
		return sessionEntity.getSessionIdentifier();
	}
	
	public DataStreamSessionEntity getEntity() { 
		return sessionEntity;
	}
	
	
	
	private class SessionMonitor extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(1000);
					if(sessionEntity.getState() == DataStreamSessionState.Persisting) {
						if(snapshotWriterComplete && signalWriterComplete) { 
							// then change state
							sessionEntity.setState(DataStreamSessionState.Completed);
							sessionEntity.setSessionStopTime(LocalDateTime.now(DTimeZone.toZoneId(stream.getTimeZone())));
							// persist -- events? 
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	private class EntityPersister extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EntityManager em = dataRepo.createEntityManager();
					em.getTransaction().begin();
					em.persist(sessionEntity);
					for (DataStreamSessionInstrument inst : instruments.values()) {
						
						em.persist(inst.getEntity());
					}
					em.flush();
					em.getTransaction().commit();
					em.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
}

package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
import com.dunkware.trade.service.data.service.repository.DataStreamSessionEntity;
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
	
	
	private DataStreamSessionState state = DataStreamSessionState.Running;

	
	public void startSession(DataStream stream, StreamSessionSpec spec) throws Exception { 
		this.stream = stream;
		this.spec = spec; 
		this.startTime = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone())), LocalTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		
		// okay so we create our new entity
		sessionEntity = new DataStreamSessionEntity();
		sessionEntity.setMessageStartDateTime(startTime);
		sessionEntity.setStream(stream.getEntity());
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		sessionEntity.setSessionIdentifier(spec.getSessionId());
		sessionEntity.setState(state);
		
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
		
		
	}
	
	public DataStreamSessionState getState() { 
		return state;
	}
	
	/**
	 * Called by the writer when its written all messages
	 * @param writer
	 */
	public void snapshotWriterComplete(DataStreamSessionSnapshotWriter writer) { 
		
	}
	
	public void stopSession() { 
		state = DataStreamSessionState.Stopping;
		// need to listen to snapshot writer until its done; 
		
		// okay here this is where
		// we set status to Stopping
		
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
	
	
	public CacheStream getCache() { 
		return cache; 
	}
	
	public String getIdentifier() { 
		return sessionEntity.getSessionIdentifier();
	}
	
	public DataStreamSessionEntity getEntity() { 
		return sessionEntity;
	}
	
}

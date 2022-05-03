package com.dunkware.trade.service.data.service.stream.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterSessionStats2;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.repository.DataStreamInstrumentEntity;
import com.dunkware.trade.service.data.service.repository.DataStreamInstrumentEntityRepo;
import com.dunkware.trade.service.data.service.repository.DataStreamSessionEntity;
import com.dunkware.trade.service.data.service.repository.DataStreamSessionEntityRepo;
import com.dunkware.trade.service.data.service.stream.DataStream;
import com.dunkware.trade.service.data.service.stream.DataStreamSessionInstrument;
import com.dunkware.trade.service.data.service.stream.session.writers.DataStreamSessionSignalWriter;
import com.dunkware.trade.service.data.service.stream.session.writers.DataStreamSessionSnapshotWriter2;
import com.dunkware.trade.service.data.service.util.DataMarkers;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.xstream.data.cache.CacheStream;

public class DataStreamSession {
	
	
	@Autowired
	private RuntimeConfig configService; 
	
	@Autowired
	private DataStreamSessionEntityRepo sessionRepo;
	
	@Autowired
	private DataStreamInstrumentEntityRepo instrumentRepo;
	
	@Autowired
	private ApplicationContext ac;
	
	
	private Map<String,DataStreamSessionInstrument> instruments = new ConcurrentHashMap<String,DataStreamSessionInstrument>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DataStream stream; 
	private StreamSessionSpec spec; 
	private CacheStream cache;
	
	private LocalDateTime startTime; 
	
	private DataStreamSessionSnapshotWriter2 snapshotWriter; 
	private DataStreamSessionSignalWriter signalWriter;
	
	// Entities 
	private DataStreamSessionEntity sessionEntity; 
	
	private DKafkaByteConsumer2 timeConsumer;
	
	private EntityPersister entityPersister;

	
	private boolean snapshotWriterComplete = false; 
	private boolean signalWriterComplete = false; 
	
	private boolean completed = false; 
	
	private volatile LocalDateTime sessionTime;
	private TimeUpdateHandler timeUpdateHandler;
	
	public void init() { 
		
	}
	
	@Transactional
	public void controllerStart(DataStream stream, StreamSessionSpec spec) throws Exception { 
		this.stream = stream;
		this.spec = spec; 
		this.startTime = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone())), LocalTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		logger.info("Starting Data Stream Session {} Stream {}",spec.getSessionId(),stream.getName());
		try {
			DKafkaByteConsumer2Spec consumerSpec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).setBrokerString(spec.getKafkaBrokers())
					.addTopic("stream_" + this.stream.getName().toLowerCase() + "_event_time").setClientAndGroup("DataStreamTimeConsumer " + DUUID.randomUUID(4), "DataStremTimeConsimer" + DUUID.randomUUID(4)).build();
			timeConsumer = DKafkaByteConsumer2.newInstance(consumerSpec);
			timeConsumer.start();
			timeUpdateHandler = new TimeUpdateHandler();
			timeConsumer.addStreamHandler(timeUpdateHandler);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// okay so we create our new entity
		sessionEntity = new DataStreamSessionEntity();
		sessionEntity.setControllerStartTime(startTime);
		sessionEntity.setStream(stream.getEntity());
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		sessionEntity.setSessionIdentifier(spec.getSessionId());
		sessionEntity.setState(DataStreamSessionState.Running);
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		try {
			sessionRepo.save(sessionEntity);
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), " Data Repo Persist new stream entity failed we are fucked " + e.toString());
			throw e;
		}
		
		try {
			snapshotWriter = new DataStreamSessionSnapshotWriter2();
			ac.getAutowireCapableBeanFactory().autowireBean(snapshotWriter);
			snapshotWriter.start(this);
			logger.info("Started Snapshot Writer Session {}", spec.getSessionId());
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), "Exception starting data stream session snapshot writer " + e.toString());
			throw e;
		}
		try {
			signalWriter = new DataStreamSessionSignalWriter();
			ac.getAutowireCapableBeanFactory().autowireBean(signalWriter);
			signalWriter.startSession(this);
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), "Exception starting session signal writer " + e.toString());
			// TODO: handle exception
		}
		
		entityPersister = new EntityPersister();
		entityPersister.start();
	
		
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
			DataStreamInstrumentEntity ent = new DataStreamInstrumentEntity();
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
			DataStreamInstrumentEntity ent = new DataStreamInstrumentEntity();
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
	
	@Transactional
	public void saveInstruments() {
		List<DataStreamInstrumentEntity> ents = new ArrayList<DataStreamInstrumentEntity>();
		for (DataStreamSessionInstrument instrument : instruments.values()) {
			ents.add(instrument.getEntity());
		}
		try {
			instrumentRepo.saveAll(ents);	
		} catch (Exception e) {
			logger.error("Exception saving instruments " + e.toString());
		}
		
	}
	
	@Transactional
	public void saveSession() { 
		sessionRepo.save(sessionEntity);
	}
	
	public void controllerStop() { 
		//RIGHT HERE! 
		logger.info("Controller Stop Method Invoked");
		sessionEntity.setControllerStopTime(LocalDateTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		sessionEntity.setState(DataStreamSessionState.Persisting);
		timeConsumer.dispose();
		saveSession();
		// who will update session enetity
	}
	
	public DataStream getStream() { 
		return this.stream;
	}
	
	public DataStreamSessionStats getStats() { 
		DataStreamSessionStats stats = new  DataStreamSessionStats();
		stats.setIdentifier(spec.getSessionId());
		stats.setStream(spec.getStreamIdentifier());
		DataStreamSnapshotWriterSessionStats2 writerStats = snapshotWriter.getStats();
		stats.setLastSnapshotWriteTime(writerStats.getLastWriteTime());
		stats.setSessionTime(DDateTime.from(getSessionTime()));
		stats.setState(this.sessionEntity.getState());
		stats.setSignalStats(signalWriter.getStats());
		stats.setSnapshotStats(writerStats);
		return stats;
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
		sessionEntity.setSnapshotCompleteTime(LocalDateTime.now(DTimeZone.toZoneId(stream.getTimeZone())));
		saveSession();
		completeSession();
	}
	
	public void signalWriterComplete() { 
	
		signalWriterComplete = true;
		completeSession();
		
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
	
	private void completeSession() { 
		if(completed) { 
			return;
		}
		if(signalWriterComplete && snapshotWriterComplete) {
			
			LocalDateTime now = LocalDateTime.now(DTimeZone.toZoneId(stream.getTimeZone()));
			sessionEntity.setSessionStopTime(now);
			sessionEntity.setState(DataStreamSessionState.Completed);
			entityPersister.interrupt();
			saveSession();	
		}
		
	}
	
	public LocalDateTime getSessionTime() { 
		return sessionTime;
	}
	
	private class TimeUpdateHandler implements DKafkaByteHandler2 {

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {
			try {
				GStreamEvent event = GStreamEvent.parseFrom(record.value());
				sessionTime = DProtoHelper.toLocalDateTime(event.getTimeUpdate().getTime(), stream.getTimeZone());
			} catch (Exception e) {
				logger.error("Exception consuming Time update event " + e.toString());
			}
		} 
		
	}
	
	private class EntityPersister extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
					saveInstruments();
				} catch (Exception e) {
				
					// TODO: handle exception
				}
				
				
			}
		}
	}
	
}

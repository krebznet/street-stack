package com.dunkware.trade.service.stream.server.controller.session.capture;




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
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
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
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.core.util.GDataHelper;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionEntityStats;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionStats;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterSessionStats2;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.capture.writers.DataStreamSessionSignalWriter;
import com.dunkware.trade.service.stream.server.controller.session.capture.writers.DataStreamSessionSnapshotWriter2;
import com.dunkware.trade.service.stream.server.repository.StreamSessionCaptureEntity;
import com.dunkware.trade.service.stream.server.repository.StreamSessionCaptureRepo;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntEntity;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntRepo;
import com.dunkware.trade.service.stream.server.spring.ConfigService;

public class StreamSessionCapture {

	@Autowired
	private ConfigService configService;

	@Autowired
	private StreamSessionCaptureRepo sessionRepo;

	@Autowired
	private StreamSessionEntRepo entityRep;

	@Autowired
	private ApplicationContext ac;


	private Map<String, DataStreamSessionEntityStats> webEntities = new ConcurrentHashMap<String, DataStreamSessionEntityStats>();

	private Map<String, StreamSessionCaptureEnt> entities = new ConcurrentHashMap<String, StreamSessionCaptureEnt>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	private StreamSession session;
	private StreamSessionSpec spec;
	

	private LocalDateTime startTime;

	private DataStreamSessionSnapshotWriter2 snapshotWriter;
	private DataStreamSessionSignalWriter signalWriter;

	// Entities
	private StreamSessionCaptureEntity sessionEntity;

	private DKafkaByteConsumer2 timeConsumer;

	private EntityPersister entityPersister;

	private DataStreamSessionState state = DataStreamSessionState.Pending;
	private boolean snapshotWriterComplete = false;
	private boolean signalWriterComplete = false;

	private boolean completed = false;

	private volatile LocalDateTime sessionTime;
	private TimeUpdateHandler timeUpdateHandler;
	
	private Marker marker = MarkerFactory.getMarker("StreamCapture");
	

	public void init() {

	}

	@Transactional
	public void controllerStart(StreamSession session) throws Exception {
		this.session = session;
		this.spec = session.getSessionSpec();
		this.startTime = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone())),
				LocalTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		logger.info("Starting Data Stream Session {} Stream {}", spec.getSessionId(), session.getStream().getName());
		try {
			DKafkaByteConsumer2Spec consumerSpec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).setBrokerString(spec.getKafkaBrokers())
					.addTopic("stream_" + this.session.getStream().getName().toLowerCase() + "_event_time")
					.setClientAndGroup("DataStreamTimeConsumer " + DUUID.randomUUID(4),
							"DataStremTimeConsimer" + DUUID.randomUUID(4))
					.build();
			timeConsumer = DKafkaByteConsumer2.newInstance(consumerSpec);
			timeConsumer.start();
			timeUpdateHandler = new TimeUpdateHandler();
			timeConsumer.addStreamHandler(timeUpdateHandler);
		} catch (Exception e) {
			logger.error(getIdentifier());
		}
		// okay so we create our new entity
		sessionEntity = new StreamSessionCaptureEntity();
		sessionEntity.setControllerStartTime(startTime);
		sessionEntity.setStream(session.getStream().getEntity());
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		sessionEntity.setSessionIdentifier(spec.getSessionId());
		sessionEntity.setState(DataStreamSessionState.Running);
		sessionEntity.setScriptVersion(spec.getStreamScript().getVersion());
		
		try {
			sessionRepo.save(sessionEntity);
		} catch (Exception e) {
			logger.error(
					" Data Repo Persist new stream entity failed we are fucked " + e.toString());
			throw e;
		}

		try {
			snapshotWriter = new DataStreamSessionSnapshotWriter2();
			ac.getAutowireCapableBeanFactory().autowireBean(snapshotWriter);
			snapshotWriter.start(this);
			logger.info("Started Snapshot Writer Session {}", spec.getSessionId());
		} catch (Exception e) {
			logger.error(
					"Exception starting data stream session snapshot writer " + e.toString());
			throw e;
		}
		try {
			signalWriter = new DataStreamSessionSignalWriter();
			ac.getAutowireCapableBeanFactory().autowireBean(signalWriter);
			signalWriter.startSession(this);
		} catch (Exception e) {
			logger.error( "Exception starting session signal writer " + e.toString());
			// TODO: handle exception
		}

		entityPersister = new EntityPersister();
		entityPersister.start();

		state = DataStreamSessionState.Running;

	}

	public DataStreamSessionState getState() {
		return sessionEntity.getState();
	}

	public Map<String, DataStreamSessionEntityStats> getWebEntityStats() {
		return webEntities;
	}

	/**
	 * Called by the writer when its written all messages
	 * 
	 * @param writer
	 */

	public void entitySnapshot(GEntitySnapshot snapshot) {
		DataStreamSessionEntityStats webEnt = webEntities.get(snapshot.getIdentifier());
		if (webEnt == null) {
			webEnt = new DataStreamSessionEntityStats();
			webEnt.setId(snapshot.getId());
			webEnt.setIdent(snapshot.getIdentifier());
			webEnt.setLastSnapshot(GDataHelper.toTimeStringTimeStamp(snapshot.getTime(), getStream().getTimeZone()));
			webEnt.setSnapshotCount(1);
			webEnt.setSignalCount(0);
			webEnt.setLastSignal(null);
			webEnt.setCreatedTime(
					DunkTime.toStringTimeStamp(LocalTime.now(DTimeZone.toZoneId(getStream().getTimeZone()))));
			webEntities.put(webEnt.getIdent(), webEnt);
		} else {
			webEnt.setLastSnapshot(GDataHelper.toTimeStringTimeStamp(snapshot.getTime(), getStream().getTimeZone()));
			webEnt.setSnapshotCount(webEnt.getSnapshotCount() + 1);
			webEntities.put(webEnt.getIdent(), webEnt);
		}

		StreamSessionCaptureEnt inst = entities.get(snapshot.getIdentifier());
		if (inst == null) {
			inst = new StreamSessionCaptureEnt();
			ac.getAutowireCapableBeanFactory().autowireBean(inst);
			StreamSessionEntEntity ent = new StreamSessionEntEntity();
			ent.setEntId(snapshot.getId());
			ent.setEntIdentifier(snapshot.getIdentifier());
			ent.setScriptVersion(StreamSessionCapture.this.spec.getStreamScript().getVersion());
			ent.setSessionIdentifier(getIdentifier());
			ent.setStreamName(session.getStream().getName());
			ent.setSignalCount(0);
			ent.setSnapshotCount(0);
			ent.setSession(session.getEntity());
			entities.put(snapshot.getIdentifier(), inst);
			if (logger.isDebugEnabled()) {
				logger.debug(MarkerFactory.getMarker("NewSessionInstrument"), "{} {}", ent.getEntIdentifier(),
						getSpec().getSessionId());
			}
			saveEntity(ent);
			this.sessionEntity.setInstrumentCount(sessionEntity.getInstrumentCount() + 1);
			inst.start(StreamSessionCapture.this, ent);

		}

		inst.snapshot(snapshot);
		this.sessionEntity.setSnapshotCount(sessionEntity.getSnapshotCount() + 1);
	}
	
	public StreamSession getSession() { 
		return session;
	}
	


	@Transactional
	public void saveEntity(StreamSessionEntEntity ent) {
		try {
			entityRep.save(ent);
		} catch (Exception e) {
			logger.error("Exception saving instrument " + e.toString());
			// TODO: handle exception
		}
	}

	public void entitySignal(GEntitySignal signal) {
		StreamSessionCaptureEnt inst = entities.get(signal.getEntityIdentifier());
		if (inst == null) {
			logger.error("Signal Created Before Snapshot Written? {}", signal.getIdentifier());
			return;
		}
		this.sessionEntity.setSignalCount(sessionEntity.getSignalCount() + 1);

	}
	
	

	@Transactional
	public void saveInstruments() {
		List<StreamSessionEntEntity> writes = new ArrayList<StreamSessionEntEntity>();
		Iterable<StreamSessionEntEntity> ents  = null;
		try {
			ents = entityRep.findAll();
			if(ents == null) { 
				return;
			}
		} catch (Exception e) {
			logger.error("Exception entityRepo.findAll() in save instruments " + e.toString());
		}
		
		for (StreamSessionEntEntity ent : ents) {
			StreamSessionCaptureEnt instrument = entities.get(ent.getEntIdentifier());
			ent.setSignalCount(instrument.getSignalCount());
			ent.setSnapshotCount(instrument.getSnapshotCount());
			writes.add(ent);
		}

		try {
			entityRep.saveAll(ents);
		} catch (Exception e) {
			logger.error("Exception saving instruments " + e.toString());
		}

	}

	@Transactional
	public void saveSession() {
		try {
			sessionRepo.save(sessionEntity);
		} catch (Exception e) {
			logger.error("Exception saving session entity " + e.toString());
		}

	}

	public void controllerStop() {
		// RIGHT HERE!
		logger.info("Controller Stop on Data Stream Session " + spec.getSessionId());
		state = DataStreamSessionState.Persisting;
		logger.info("Controller Stop Method Invoked");
		sessionEntity.setControllerStopTime(LocalDateTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		sessionEntity.setState(DataStreamSessionState.Persisting);
		snapshotWriter.sessionStopped();
		signalWriter.sessionStopped();
		entityPersister.interrupt();
		timeConsumer.dispose();
		saveSession();
		// who will update session enetity
	}

	public StreamController getStream() {
		return session.getStream();
	}

	public DataStreamSessionStats getStats() {
		DataStreamSessionStats stats = new DataStreamSessionStats();
		stats.setIdentifier(spec.getSessionId());
		stats.setStream(spec.getStreamIdentifier());
		DataStreamSnapshotWriterSessionStats2 writerStats = snapshotWriter.getStats();
		stats.setLastSnapshotWriteTime(writerStats.getLastWriteTime());
		stats.setSessionTime(DDateTime.from(getSessionTime()));
		stats.setState(this.sessionEntity.getState());
		stats.setSignalStats(signalWriter.getStats());
		stats.setSnapshotStats(writerStats);
		stats.setState(state);

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
		completeSession();
	}

	public void signalWriterComplete() {

		signalWriterComplete = true;
		completeSession();

	}


	public String getIdentifier() {
		return sessionEntity.getSessionIdentifier();
	}

	public StreamSessionCaptureEntity getEntity() {
		return sessionEntity;
	}

	private void completeSession() {
		if (completed) {
			return;
		}
		if (signalWriterComplete && snapshotWriterComplete) {
			sessionEntity.setSnapshotCompleteTime(LocalDateTime.now(DTimeZone.toZoneId(getStream().getTimeZone())));
			sessionEntity.setSnapshotCount(snapshotWriter.getStats().getInserteCount());
			LocalDateTime now = LocalDateTime.now(DTimeZone.toZoneId(getStream().getTimeZone()));
			sessionEntity.setSessionStopTime(now);
			sessionEntity.setState(DataStreamSessionState.Completed);
			entityPersister.interrupt();
			timeConsumer.dispose();
			timeUpdateHandler.toString();
			saveSession();
			state = DataStreamSessionState.Completed;
			logger.info("Data Session Completed " + spec.getSessionId());
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
				sessionTime = DProtoHelper.toLocalDateTime(event.getTimeUpdate().getTime(), session.getStream().getTimeZone());
			} catch (Exception e) {
				logger.error("Exception consuming Time update event " + e.toString());
			}
		}

	}

	private class EntityPersister extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(3000);
					saveInstruments();
					saveSession();

				} catch (Exception e) {
					logger.error("Exception in entity persister " + e.toString());

				}

			}
		}
	}

}

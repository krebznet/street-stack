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
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;
import com.dunkware.trade.service.stream.server.controller.session.capture.writers.DataStreamSessionSignalWriter;
import com.dunkware.trade.service.stream.server.controller.session.capture.writers.DataStreamSessionSnapshotWriter2;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntEntity;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntRepo;
import com.dunkware.trade.service.stream.server.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.spring.ConfigService;

@AStreamSessionExt
public class StreamSessionCaptureExt implements StreamSessionExtension {

	@Autowired
	private ConfigService configService;

	@Autowired
	private StreamSessionEntRepo entityRep;

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private StreamSessionRepo sessionRepo; 
	
	private Map<String, StreamSessionEntEntity> sessionEntities = new ConcurrentHashMap<String, StreamSessionEntEntity>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	private StreamSession session;
	private StreamSessionSpec spec;

	private LocalDateTime startTime;

	// test commit

	private DataStreamSessionSnapshotWriter2 snapshotWriter;
	private DataStreamSessionSignalWriter signalWriter;
	
	

	
	private DataStreamSessionState state = DataStreamSessionState.Pending;
	private boolean snapshotWriterComplete = false;
	private boolean signalWriterComplete = false;

	private boolean completed = false;


	private Marker marker = MarkerFactory.getMarker("stream.session.capture");

	@Override
	public void sessionStarted(StreamSession session) {
		this.session = session;
		this.spec = session.getSessionSpec();
		this.startTime = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone())),
				LocalTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		logger.info("Starting Data Stream Session {} Stream {}", spec.getSessionId(), session.getStream().getName());
		
		try {
			snapshotWriter = new DataStreamSessionSnapshotWriter2();
			ac.getAutowireCapableBeanFactory().autowireBean(snapshotWriter);
			snapshotWriter.start(this);
			logger.info(marker, "Started Snapshot Writer Session {}", spec.getSessionId());
		} catch (Exception e) {
			logger.error(marker, "Exception starting data stream session snapshot writer " + e.toString());
		}
		try {
			signalWriter = new DataStreamSessionSignalWriter();
			ac.getAutowireCapableBeanFactory().autowireBean(signalWriter);
			signalWriter.startSession(this);
		} catch (Exception e) {
			logger.error("Exception starting session signal writer " + e.toString());
		}

		state = DataStreamSessionState.Running;
	}

	/**
	 * Called by the writer when its written all messages we create session entities
	 * but don't persist
	 * 
	 * @param writer
	 */

	public void entitySnapshot(GEntitySnapshot snapshot) {
		StreamSessionEntEntity webEnt = sessionEntities.get(snapshot.getIdentifier());
		if (webEnt == null) {
			webEnt = new StreamSessionEntEntity();
			webEnt.setEntId(snapshot.getId());
			webEnt.setEntIdentifier(snapshot.getIdentifier());
			webEnt.setSession(session.getEntity());
			webEnt.setSnapshotCount(1);
			webEnt.setSignalCount(0);
			sessionEntities.put(snapshot.getIdentifier(), webEnt);
			session.getEntity().getEntities().add(webEnt);
			saveSessionEntity();
		} else {
			webEnt.setSnapshotCount(webEnt.getSnapshotCount() + 1);
			sessionEntities.put(snapshot.getIdentifier(), webEnt);
		}

	}

	public StreamSession getSession() {
		return session;
	}

	
	public void saveEntity(StreamSessionEntEntity ent) {
		try {
			entityRep.save(ent);
		} catch (Exception e) {
			logger.error("Exception saving instrument " + e.toString());
			// TODO: handle exception
		}
	}

	public void entitySignal(GEntitySignal signal) {
		StreamSessionEntEntity webEnt = sessionEntities.get(signal.getEntityIdentifier());
		// if its null what do we do?
		if (webEnt == null) {
			webEnt = new StreamSessionEntEntity();
			webEnt.setEntId(signal.getEntityId());
			webEnt.setEntIdentifier(signal.getEntityIdentifier());
			webEnt.setSnapshotCount(0);
			webEnt.setSignalCount(1);
			sessionEntities.put(signal.getEntityIdentifier(), webEnt);
			session.getEntity().getEntities().add(webEnt);
			try {
				entityRep.save(webEnt);	
			} catch (Exception e) {
				logger.error(marker, "Exception saving session entity entity in snapshot new entity " + e.toString());
			}
			
		} else {
			webEnt.setSignalCount(webEnt.getSignalCount() + 1);
			sessionEntities.put(signal.getEntityIdentifier(), webEnt);
		}
	}

	
	public void saveSessionEntity() { 
		try {
			sessionRepo.save(session.getEntity());
		} catch (Exception e) {
			logger.error(marker, "Exception saving session entity with new web entity " + e.toString());
		}
	}
	
	public void writeSessionEntities() {
		try {
			entityRep.saveAll(sessionEntities.values());
		} catch (Exception e) {
			logger.error(marker, "Exception saving session entities in session capture " + e.toString(), e);
		}

	}

	@Override
	public void sessionStopped(StreamSession session) {
		logger.info(marker, "Closing Snapshot/Signal Writers");
		// close signal and snapshot writers
		signalWriter.closeWriter();
		snapshotWriter.closeWriter();
		logger.info(marker, "Closed Snapshot/SignalWriters");
		// now save all the session entities 
		try {
			writeSessionEntities();	
		} catch (Exception e) {
			logger.error(marker, "exception writing session entities in sessionStopped " + e.toString());
		}
		
		// compute the snapshot and signal count sums
		long snapshotcount = 0; 
		long signalcount = 0; 
		for (StreamSessionEntEntity ent : sessionEntities.values()) {
			snapshotcount = snapshotcount + ent.getSnapshotCount();
			signalcount = signalcount + ent.getSignalCount();
		}
		
		session.getEntity().setSnapshotCount(snapshotcount);
		session.getEntity().setSignalCount(signalcount);
		
		
	}

	public StreamController getStream() {
		return session.getStream();
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

}

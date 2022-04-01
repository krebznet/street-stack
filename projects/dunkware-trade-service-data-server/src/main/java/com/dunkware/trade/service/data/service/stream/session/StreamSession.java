package com.dunkware.trade.service.data.service.stream.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.service.data.json.controller.spec.StreamSessionSpec;
import com.dunkware.trade.service.data.json.data.stream.session.StreamSessionCacheStats;
import com.dunkware.trade.service.data.json.data.stream.session.StreamSessionStats;
import com.dunkware.trade.service.data.json.enums.StreamSessionState;
import com.dunkware.trade.service.data.service.config.ConfigService;
import com.dunkware.trade.service.data.service.stream.Stream;
import com.dunkware.trade.service.data.service.stream.session.writers.SessionMongoSignalWriter;
import com.dunkware.trade.service.data.service.stream.session.writers.SessionMongoSnapshotWriter;
import com.dunkware.trade.service.data.service.stream.session.writers.SessionMongoSnapshotWriterInput;
import com.dunkware.trade.service.data.service.util.NameHelper;
import com.dunkware.xstream.data.cache.CacheExtensionType;
import com.dunkware.xstream.data.cache.CacheStream;
import com.dunkware.xstream.data.cache.CacheStreamInput;
import com.dunkware.xstream.data.cache.core.CacheStreamImpl;
import com.dunkware.xstream.data.cache.ext.CacheKafkaSignalConsumerExtType;
import com.dunkware.xstream.data.cache.ext.CacheKafkaSnapshotConsumerExtType;

public class StreamSession {
	
	
	@Autowired
	private ConfigService configService; 
	
	@Autowired
	private ApplicationContext ac;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Stream stream; 
	private StreamSessionSpec spec; 
	private CacheStream cache;
	
	private LocalDateTime startTime; 
	
	private SessionMongoSnapshotWriter snapshotWriter; 
	private SessionMongoSignalWriter signalWriter; 
	
	private StreamSessionState state = StreamSessionState.Stopped;

	
	public void start(Stream stream, StreamSessionSpec spec) throws Exception { 
		this.stream = stream;
		this.spec = spec; 
		this.startTime = LocalDateTime.of(LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone())), LocalTime.now(DTimeZone.toZoneId(spec.getTimeZone())));
		
		// Create CacheInput
		List<CacheExtensionType> extTypes = new ArrayList<CacheExtensionType>();
		CacheKafkaSignalConsumerExtType sigType = new CacheKafkaSignalConsumerExtType();
		sigType.setKafkaBrokers(spec.getKafkaBrokers());
		sigType.setKafkaTopic(spec.getKafkaSignalTopic());
		sigType.setKafkaConsumerId("signal_" + spec.getSessionId());
		sigType.setTimeZone(spec.getTimeZone());
		extTypes.add(sigType);
		CacheKafkaSnapshotConsumerExtType snapType = new CacheKafkaSnapshotConsumerExtType();
		snapType.setKafkaBrokers(spec.getKafkaBrokers());
		snapType.setKafkaConsumerId("snapshot_" + spec.getSessionId());
		snapType.setKafkaTopic(spec.getKafkaSnapshotTopic());
		snapType.setTimeZone(spec.getTimeZone());
		
		extTypes.add(snapType);
		CacheStreamInput input = new CacheStreamInput(extTypes,configService.getExecutor(),spec.getTimeZone(),spec.getSessionId());	
		
		try {
			cache = new CacheStreamImpl();
			cache.start(input);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Data"), "Exception starting session cache " + e.toString());
			throw new Exception("Stream Session Start Exception Starting Cache " + e.toString());
		}
		
		this.signalWriter = new SessionMongoSignalWriter();
		ac.getAutowireCapableBeanFactory().autowireBean(signalWriter);
		
		signalWriter.start(this);
		SessionMongoSnapshotWriterInput writerInput = new SessionMongoSnapshotWriterInput();
		writerInput.setBatchSize(30);
		writerInput.setCaptureId(spec.getSessionId());
		writerInput.setDebugLogging(false);
		
		try {
			DKafkaConsumerSpec2 kspec = DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest)
					.addBroker(spec.getKafkaBrokers()).addTopic(spec.getKafkaSnapshotTopic()).setBrokerString(spec.getKafkaBrokers()).setClientAndGroup(DUUID.randomUUID(5), DUUID.randomUUID(5)).build();
					
		 writerInput.setStream(spec.getStreamIdentifier());
		 writerInput.setMongoURL(configService.getMongoURL());
		 writerInput.setMongoDatabase(configService.getMongoDatabase());
		 writerInput.setMongoCollection(NameHelper.getSessionSnapshotCollectionName(spec.getStreamIdentifier(), LocalDate.now(DTimeZone.toZoneId(spec.getTimeZone()))));
		 writerInput.setTimeZone(spec.getTimeZone());;
		 writerInput.setWriteQueueSizeLimit(10000);
		 writerInput.setKafkaSpec(kspec);
		 snapshotWriter = new SessionMongoSnapshotWriter();
		 snapshotWriter.start(writerInput);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Data"), "exception starting mongo snapshot writer " + e.toString());
			throw e; 
		}
	
	
		state  =StreamSessionState.Running;
		
	}
	
	public void stop() { 
		state = StreamSessionState.Stopping;
		signalWriter.stop();
		snapshotWriter.stop();	
		state = StreamSessionState.Stopped;
		
	}
	
	public StreamSessionStats getStats() { 
		StreamSessionStats stats = new StreamSessionStats();
		stats.setSessionId(spec.getSessionId());
		StreamSessionCacheStats cstats = new StreamSessionCacheStats();
		cstats.setEntityCount(cache.getEntities().size());
		cstats.setSignalCount(cache.getSignals().size());
		cstats.setSnapshotCount(cache.getSnapshotCount());
		stats.setCache(cstats);
		stats.setSessionStart(DTime.from(getStartTime()));
		stats.setStreamStart(spec.getStartTime());
		stats.setSignalWriter(signalWriter.getStats());
		stats.setSnapshotWriter(snapshotWriter.getStats());
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
	
	public StreamSessionState getState() { 
		return state;
	}
	public void dispose() { 
		state = StreamSessionState.Stopped;
	}
	
	public CacheStream getCache() { 
		return cache; 
	}

}

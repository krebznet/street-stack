package com.dunkware.trade.service.stream.worker.session.publishers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalListener;
import com.dunkware.xstream.model.snapshot.SnapshotHelper;
import com.dunkware.xstream.model.snapshot.SnapshotValue;


@AStreamWorkerExtension
public class SnapshotEntityPublisher implements StreamWorkerExtension, XStreamSignalListener, XStreamListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SnapshotProducer");
	private StreamWorker worker;
	private XStream stream;
	private List<EntitySnapshotRunnable> entitySnapshotRunnables = new ArrayList<EntitySnapshotRunnable>();
	private AtomicInteger publishCount = new AtomicInteger(0);
	private DKafkaByteProducer kafkaProducer;
	private Map<Integer, AtomicInteger> entitySnapshotCount = new ConcurrentHashMap<Integer, AtomicInteger>();
	private AtomicLong maxPublishTime = new AtomicLong(0);

	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;

		String brokers = worker.getStreamDescriptor().getKafkaBrokers();
		String topicName = "system_stream_snapshot_entity";
		kafkaProducer = DKafkaByteProducer.newInstance(brokers,topicName,worker.getStartReq().getWorkerId());
	}

	@Override
	public void start() throws Exception {
		stream = worker.getStream();
		stream.addStreamListener(this);
		stream.getSignals().addListener(this);

	}

	@Override
	public void stop() throws Exception {
		for (EntitySnapshotRunnable entitySnapshotRunnable : entitySnapshotRunnables) {
			stream.getClock().unscheduleRunnable(entitySnapshotRunnable);
		}

	}

	@Override
	public void rowInsert(XStreamEntity row) {
		EntitySnapshotRunnable runnable = new EntitySnapshotRunnable(row, stream);
		stream.getClock().scheduleRunnable(runnable, 1);
		this.entitySnapshotRunnables.add(runnable);
	}

	@Override
	public void onSignal(XStreamSignal signal) {
		SnapshotValue value = SnapshotHelper.snapshotValue(signal.getSignal());
		long start = System.currentTimeMillis();
		try {
			kafkaProducer.sendBytes(DJson.serialize(value).getBytes());
			publishCount.incrementAndGet();
		} catch (Exception e) {
			logger.error(marker, "Exception publishing signal {} ", e.toString(), e);
		}

		long time = System.currentTimeMillis() - start;
		if (time > maxPublishTime.get()) {
			maxPublishTime.set(time);
		}
	}

	public SnapshotPublisherStats getStats() {
		SnapshotPublisherStats stats = new SnapshotPublisherStats();
		stats.setEntityCount(entitySnapshotCount.keySet().size());
		stats.setPublishCount(publishCount.get());
		stats.setMaxPublishTime(maxPublishTime.get());
		return stats;

	}

	public class EntitySnapshotRunnable implements Runnable {

		private XStreamEntity entity;
		private XStream stream;

		public EntitySnapshotRunnable(XStreamEntity entity, XStream stream) {
			this.entity = entity;
			this.stream = stream;
		}

		public void run() {
			try {
				LocalDateTime dateTime = stream.getClock().getLocalDateTime();
				SnapshotValue value = SnapshotHelper.snapshotValue((int) stream.getInput().getId(),
						entity.getIdentifier(), entity.numericVarSnapshot(), dateTime);

				long start = System.currentTimeMillis();
				kafkaProducer.sendBytes(DJson.serialize(value).getBytes());
				long time = new Date().getTime() - start;
				if (time > maxPublishTime.get()) {
					maxPublishTime.set(time);
				}
				publishCount.incrementAndGet();
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error(marker, "Exception sending Snapshot Entity Value {}", e.toString(), e);
			}
		}

	}

}

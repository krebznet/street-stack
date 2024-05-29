package com.dunkware.stream.data.injest.consumers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.cassy.helpers.StreamSessionRowHelper;
import com.dunkware.stream.data.cassy.repository.session.StreamSessionRepo;
import com.dunkware.stream.data.codec.session.meta.SessionModelCodec;
import com.dunkware.stream.data.injest.config.InjestConfig;
import com.dunkware.stream.data.model.session.StreamSessionModel;
import com.dunkware.stream.data.util.constants.StreamDataTopics;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumerSpec;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteHandler;

import jakarta.annotation.PostConstruct;

@Service
public class StreamSessionConsumer implements KafkaByteHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSessionConsumer");

	private InjestConfig config; 
	
	private BlockingQueue<StreamSessionModel> queue = new LinkedBlockingQueue<StreamSessionModel>();

	private StreamSessionRepo repo;

	private KafkaByteConsumer kafkaConsumer;

	private InjestorThread injestorThread;

	private AtomicLong injestCounter = new AtomicLong(0);

	public StreamSessionConsumer(StreamSessionRepo repo, InjestConfig config) {
		this.repo = repo;
		this.config = config;
	}

	public StreamSessionConsumerMetrics getMetrics() {
		StreamSessionConsumerMetrics sats = new StreamSessionConsumerMetrics();
		sats.setInjestCount(injestCounter.get());
		sats.setQueueSize(queue.size());
		return sats;
	}

	@PostConstruct
	private void postConstruct() {

		Thread init = new Thread() {

			public void run() {
				try {
					KafkaByteConsumerSpec spec = KafkaByteConsumerSpec
							.newBuilder(KafkaByteConsumerSpec.ConsumerType.Auto,
									KafkaByteConsumerSpec.OffsetType.Earliest)
							.addBroker(config.getKafkaBrokers()).addTopic(StreamDataTopics.CaptureSession).setClientAndGroup(config.getKafkaConsumerId(), config.getKafkaConsumerGroup())
							.setThrottle(500000).build();
					kafkaConsumer = KafkaByteConsumer.newInstance(spec);
					kafkaConsumer.addStreamHandler(StreamSessionConsumer.this);
					kafkaConsumer.start();
					injestorThread = new InjestorThread();
					injestorThread.start();

				} catch (Exception e) {
					logger.error(marker, "Exceptoin Starting Var Start Kafka Consumer " + e.toString(), e);

				}

			}

		};

		init.start();

	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			queue.add(SessionModelCodec.decode(record.value()));
		} catch (Exception e) {
			logger.error("Exception Decoding EntityDateStats " + e.toString());
		}
	}

	private class InjestorThread extends Thread {

		public void run() {

			while (!interrupted()) {
				try {
					StreamSessionModel inbound = queue.poll(5, TimeUnit.SECONDS);
					if (inbound == null) {
						continue;
					}

					StreamSessionRow entity = StreamSessionRowHelper.toRow(inbound);
					repo.save(entity);
					injestCounter.incrementAndGet();

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;

					}

				}
			}
		}

	}

}

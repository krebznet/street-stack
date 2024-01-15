package com.dunkware.trade.service.stream.worker.session.publishers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalListener;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

//@AStreamWorkerExtension
public class SignalPublisher implements StreamWorkerExtension, XStreamSignalListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SignalPublisher");

	private BlockingQueue<StreamEntitySignal> snapshots = new LinkedBlockingQueue<StreamEntitySignal>();

	private StreamWorker worker;
	
	private SnapshotKafkaPublisher publisher;

	private DKafkaByteProducer producer;

	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
		String brokers = worker.getStreamDescriptor().getKafkaBrokers();
		String topicName = worker.getStreamDescriptor().getSignalEventTopic();
		producer = DKafkaByteProducer.newInstance(brokers,topicName,worker.getStartReq().getWorkerId());
	}

	@Override
	public void start() throws Exception {
		worker.getStream().getSignals().addListener(this);
		publisher = new SnapshotKafkaPublisher();
		publisher.start();
	}

	@Override
	public void stop() throws Exception {
		worker.getStream().getSignals().removeListener(this);
		publisher.interrupt();
		producer.dispose();
	}

	@Override
	public void onSignal(XStreamSignal signal) {
		snapshots.add(signal.getSignal());

	}

	private class SnapshotKafkaPublisher extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					try {
						StreamEntitySignal snap = snapshots.take();
						byte[] bytes = DJson.serialize(snap).getBytes();
						producer.sendBytes(bytes);
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						if(e instanceof org.apache.kafka.common.errors.InterruptException) {
							return;
						}
						logger.error(marker, "Exception publishing snapshot {}", e.toString());
					}

				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				if(e instanceof org.apache.kafka.common.errors.InterruptException) {
					return;
				}
				logger.error(marker, "Exception publishing snapshot {}", e.toString());
			}

		}
	}

}

package com.dunkware.trade.service.data.worker.stream.net.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.data.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.data.cluster.GContainerWorkerMessage.TypeCase;
import com.dunkware.net.proto.data.cluster.GContainerWorkersMessage;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartReq;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerBuilder;

public class WorkerContainer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Container container;

	private DataStreamWorkerContainerStartReq req;

	@Autowired
	private Cluster cluster;

	private DKafkaByteConsumer2 workerConsumer;
	private DKafkaByteConsumer2 workersConsumer;
	private DKafkaByteProducer serverProducer;

	private BlockingQueue<GContainerWorkerMessage> workerMessageQueue = new LinkedBlockingQueue<GContainerWorkerMessage>();
	private BlockingQueue<GContainerWorkersMessage> workersMessageQueue = new LinkedBlockingQueue<GContainerWorkersMessage>();

	private WorkerContainerMetrics metrics;

	private WorkersMessageConsumer workersMessageConsumer;
	private WorkerMessageConsumer workerMessageConsumer;

	private WorkerMessageHandler workerMessageHandler;

	public void start(DataStreamWorkerContainerStartReq req) throws Exception {
		metrics = new WorkerContainerMetrics(this);
		this.req = req;
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(req.getKafkaBroker())
					.addTopic(req.getWorkersTopic())
					.setClientAndGroup("DataContainerWorker_" + req.getWorkerId() + "_" + DUUID.randomUUID(4),
							"DataContainerWorker_" + req.getWorkerId() + "_" + DUUID.randomUUID(4))
					.build();
			workersConsumer = DKafkaByteConsumer2.newInstance(spec);
			workersConsumer.start();
			workersMessageConsumer = new WorkersMessageConsumer();
			workersConsumer.addStreamHandler(workersMessageConsumer);
		} catch (Exception e) {
			logger.error("Exception creating workers consumer " + e.toString());
			throw new Exception("Exception creating workers kafka consumer " + e.toString());
		}
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(req.getKafkaBroker())
					.addTopic(req.getWorkerTopic())
					.setClientAndGroup("DataContainerWorker_" + req.getWorkerId() + "_" + DUUID.randomUUID(4),
							"DataContainerWorker_" + req.getWorkerId() + "_" + DUUID.randomUUID(4))
					.build();
			workerConsumer = DKafkaByteConsumer2.newInstance(spec);
			workerConsumer.start();
			workerMessageConsumer = new WorkerMessageConsumer();
			workerConsumer.addStreamHandler(workerMessageConsumer);
		} catch (Exception e) {
			logger.error("Exception creating workers consumer " + e.toString());
			throw new Exception("Exception creating worker kafka consumer " + e.toString());
		}
		try {
			serverProducer = DKafkaByteProducer.newInstance(req.getKafkaBroker(), req.getServerTopic(),
					req.getWorkerId() + "_" + DUUID.randomUUID(4));
		} catch (Exception e) {
			logger.error("Exception creating server producer " + e.toString());
			throw new Exception("Exception creating server kafka producer " + e.toString());
		}

		try {
			container = ContainerBuilder.newInstance().executor(cluster.getExecutor())
					.extensions(req.getContainerExtensions()).timezone(req.getTimeZone()).identifier(req.getWorkerId())
					.build();
		} catch (Exception e) {
			logger.error("Exception creating worker contaienr " + e.toString());
			throw new Exception("Exception creating worker container " + e.toString());
		}
	}

	public void resetContainer() {

	}

	public void dispose() {
		workerConsumer.dispose();
		workersConsumer.dispose();
		serverProducer.dispose();

	}

	public Container getStreamContainer() {
		return container;
	}

	public void sendMessage(GContainerServerMessage message) {
		try {
			serverProducer.sendBytes(message.toByteArray());
		} catch (Exception e) {
			logger.error("Exception sending message to server " + e.toString());
		}
	}

	private class WorkerMessageConsumer implements DKafkaByteHandler2 {

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {
			try {
				GContainerWorkerMessage message = GContainerWorkerMessage.parseFrom(record.value());
				workerMessageQueue.add(message);

			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Exception consuming worker message " + e.toString());
			}
		}

	}

	private class WorkersMessageConsumer implements DKafkaByteHandler2 {

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {
			try {
				GContainerWorkersMessage workersMessage = GContainerWorkersMessage.parseFrom(record.value());
				workersMessageQueue.add(workersMessage);
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Exception consuming workers message " + e.toString());
			}

		}

	}

	public DataStreamWorkerContainerStartReq getReq() {
		return req;
	}

	private class WorkerMessageHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				GContainerWorkerMessage message = null;
				try {
					message = workerMessageQueue.take();
					if (message.getTypeCase() == TypeCase.STREAMEVENT) {
						GStreamEvent event = message.getStreamEvent();
						if (event.getType() == GStreamEventType.EntitySnapshot) {
							GEntitySnapshot snapshot = event.getEntitySnapshot();
							metrics.entitySnapshot(snapshot);
							container.consumeStreamSnapshot(snapshot);
						}
						if (event.getType() == GStreamEventType.EntitySignal) {
							GEntitySignal signal = event.getEntitySignal();
							metrics.entitySignal(signal);
							container.consumeStreamSignal(signal);
						}
						if (event.getType() == GStreamEventType.TimeUpdate) {
							GStreamTimeUpdate update = event.getTimeUpdate();
							metrics.timeUpdate(update);
							container.consumeStreamTime(update);
						}
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Handling Worker Message " + e.toString());
				}
			}
		}
	}

	private class WorkersMessageHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				GContainerWorkersMessage workersMessage = null;
				try {
					workersMessage = workersMessageQueue.take();
					if (workersMessage.getTypeCase() == GContainerWorkersMessage.TypeCase.ENTITYSEARCHREQUEST) {

					}

					if (workersMessage.getTypeCase() == GContainerWorkersMessage.TypeCase.ENTITYSCANNERREQUEST) {

					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Handling Workers Message " + e.toString());
				}
			}
		}
	}

}

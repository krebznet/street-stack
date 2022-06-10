package com.dunkware.trade.service.stream.worker.session.container;

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
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.proto.cluster.GContainerServerMessage;
import com.dunkware.net.proto.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.cluster.GContainerWorkerMessage.TypeCase;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartReq;
import com.dunkware.trade.service.stream.worker.session.container.agents.WorkerEntitySearch;
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

	private WorkerContainerMetrics metrics;
	private WorkerMessageConsumer workerMessageConsumer;

	private WorkerMessageHandler workerMessageHandler;
	
	

	public void start(DataStreamWorkerContainerStartReq req) throws Exception {
		metrics = new WorkerContainerMetrics(this);
		this.req = req;
		
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
			workerMessageHandler  = new WorkerMessageHandler();
			workerMessageHandler.start();
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
		container.newSession();
	}
	
	public Container getContainer() { 
		return container;
	}

	public DTimeZone getTimeZone() { 
		return req.getTimeZone();
	}
	public String getWorkerId() { 
		return req.getWorkerId();
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

	

	public DataStreamWorkerContainerStartReq getReq() {
		return req;
	}

	private class WorkerMessageHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				GContainerWorkerMessage message = null;
				try {
					message = workerMessageQueue.take();
					if(message.getTypeCase() == TypeCase.ENTITYSEARCHREQUEST) {
						GNetEntitySearchRequest req = message.getEntitySearchRequest();
						WorkerEntitySearch search = new WorkerEntitySearch(req,WorkerContainer.this);
						cluster.getExecutor().execute(search);
					}
					if(message.getTypeCase() == TypeCase.ENTITYSNAPSHOT) {
						GEntitySnapshot snapshot = message.getEntitySnapshot();
						container.consumeStreamSnapshot(snapshot);
					}
					if(message.getTypeCase() == TypeCase.TIMEUPDATE) {
						container.consumeStreamTime(message.getTimeUpdate());
					}
					if(message.getTypeCase() == TypeCase.ENTITYSIGNAL) { 
						container.consumeStreamSignal(message.getEntitySignal());
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

	

}

package com.dunkware.trade.service.data.service.stream.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.trade.service.data.json.worker.container.DataStreamWorkerContainerStartReq;
import com.dunkware.trade.service.data.service.stream.DataStream;

public class StreamContainerController implements DKafkaByteHandler2 {

	@Autowired
	private Cluster cluster;

	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	@Value("${stream.container.node.count}")
	private int workerNodeCount;
	
	@Autowired
	private ApplicationContext ac;
	
	private List<StreamContainerWorker> workers = new ArrayList<StreamContainerWorker>();

	private DKafkaByteConsumer2 eventConsumer;
	
	private DKafkaByteConsumer2 serverConsumer;

	private DKafkaByteProducer workersProducer; 
	
	private BlockingQueue<GContainerServerMessage> serverMessageQueue = new LinkedBlockingQueue<GContainerServerMessage>();
	
	private DataStream dataStream;
	
	private BlockingQueue<GStreamEvent> eventQueue = new LinkedBlockingQueue<GStreamEvent>();
	
	private List<EventPublisher> eventPublishers = new ArrayList<EventPublisher>();
	
	private Map<String,StreamContainerWorker> entityAssignments = new ConcurrentHashMap<String,StreamContainerWorker>();
	
	private int entityAssignmentIndex = 0; 
	
	private ServerMessageController serverMessageController;
	
	// workers 

	private Logger logger = LoggerFactory.getLogger(getClass());

	public DataStream getStream() { 
		return dataStream;
	}
	public void start(DataStream dataStream, DExecutor executor) throws Exception {
		this.dataStream = dataStream;
		List<ClusterNode> workers = null;
		try {
			cluster.getNodeSevice().reserveWorkerNodes("stream-data-container", workerNodeCount);
		} catch (Exception e) {
			logger.error("Exception Getting Worker Nodes " + e.toString());
			return;
		}
		Thread starter = new Thread() {

			public void run() {
				try {
					int count = 1;
					for (ClusterNode clusterNode : workers) {
						DataStreamWorkerContainerStartReq req = new DataStreamWorkerContainerStartReq();
						String wokerId = dataStream.getName() + "container_worker_" + count;
						req.setWorkerId(wokerId);
						req.setKafkaBroker(kafkaBrokers);
						req.setServerTopic(dataStream.getName() + "_container_server");
						req.setStreamIdentifier(dataStream.getName());
						req.setWorkersTopic(dataStream.getName() + "container_workers");
						req.setWorkerTopic(dataStream.getName() + "_" + req.getWorkerId());
						req.setTimeZone(dataStream.getTimeZone());
						StreamContainerWorker worker = new StreamContainerWorker();
						ac.getAutowireCapableBeanFactory().autowireBean(worker);
						try {
							worker.start(clusterNode, req, StreamContainerController.this);
							StreamContainerController.this.workers.add(worker);
						} catch (Exception e) {
							logger.error("Worker container node " + count + " failed " + e.toString());;
						}
						count++;
					}
					if(StreamContainerController.this.workers.size() == 0) { 
						logger.error("Failed To Start Data Stream Cluster no started nodes");
						return;
					}
					
					
					String serverTopic = dataStream.getName().toLowerCase() + "_container_server";
					DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
							.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(kafkaBrokers)
							.addTopic(serverTopic).setClientAndGroup("DataContainerCluster_" + DUUID.randomUUID(4),
									"DataContainerCluster_" + DUUID.randomUUID(4))
							.build();
					serverConsumer = DKafkaByteConsumer2.newInstance(spec);
					serverConsumer.start();
					ServerMessageConsumer mConsumer = new ServerMessageConsumer();
					serverConsumer.addStreamHandler(mConsumer);
					// handler 
					String eventTopic = "stream_" + dataStream.getName().toLowerCase() + "_events_all";
					DKafkaByteConsumer2Spec spec2 = DKafkaByteConsumer2SpecBuilder
							.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(kafkaBrokers)
							.addTopic(eventTopic).setClientAndGroup("DataContainerCluster_" + DUUID.randomUUID(4),
									"DataContainerCluster_" + DUUID.randomUUID(4))
							.build();
					eventConsumer = DKafkaByteConsumer2.newInstance(spec2);
					eventConsumer.start();
					
					serverMessageController = new ServerMessageController();
					serverMessageController.start();
					// then we need shit. 
					
					
				} catch (Exception e) {
					logger.error("Exception Creating Kafak Event Consumer " + e.toString());

					// TODO: handle exception
				}

			}
		};
		starter.start();
	}
	
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			GStreamEvent event = GStreamEvent.parseFrom(record.value());
			eventQueue.add(event);
		} catch (Exception e) {
			logger.error("Exception consuming event " + e.toString());
		}
	}


	private class ServerMessageConsumer implements DKafkaByteHandler2 {

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {
			try {
				serverMessageQueue.add(GContainerServerMessage.parseFrom(record.value()));	
			} catch (Exception e) {
				logger.error("exception consuming server message " + e.toString());

			}
			
		} 
	}


	
	private StreamContainerWorker nextEntityWorker() { 
		if(entityAssignmentIndex == workers.size() - 1) { 
			entityAssignmentIndex = 0;
			return workers.get(0);
		} else { 
			StreamContainerWorker worker =  workers.get(entityAssignmentIndex);
			entityAssignmentIndex++;
			return worker;
		}
	}
	
	private class EventPublisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) {
				GStreamEvent event = null;
				try {
				    event = eventQueue.take();
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Event Publisher Remove From Q Exception " + e.toString());
				}
				if(event.getType() == GStreamEventType.EntitySnapshot) { 
					GEntitySnapshot snapshot = event.getEntitySnapshot();
					StreamContainerWorker worker = entityAssignments.get(snapshot.getIdentifier());
					if(worker == null) { 
						worker = nextEntityWorker();
						entityAssignments.put(snapshot.getIdentifier(), worker);
					}
					worker.streamEvent(event);
					
				}
				if(event.getType() == GStreamEventType.EntitySignal) { 
					for (StreamContainerWorker worker : workers) {
						worker.streamEvent(event);
					}
				}
				if(event.getType() == GStreamEventType.TimeUpdate) { 
					for (StreamContainerWorker worker : workers) {
						worker.streamEvent(event);
					}
				}
				
				
			}
		}
		
	}
	
	private class ServerMessageController extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					GContainerServerMessage message = serverMessageQueue.take();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}

}

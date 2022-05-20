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

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.trade.service.data.service.stream.DataStream;

public class DataStreamContainerCluster implements DKafkaByteHandler2 {

	@Autowired
	private Cluster cluster;

	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	private List<DataStreamContainerWorker> workers = new ArrayList<DataStreamContainerWorker>();

	private DKafkaByteConsumer2 eventConsumer;

	private DataStream dataStream;
	
	private BlockingQueue<GStreamEvent> eventQueue = new LinkedBlockingQueue<GStreamEvent>();
	
	private List<EventPublisher> eventPublishers = new ArrayList<EventPublisher>();
	
	private Map<String,DataStreamContainerWorker> entityAssignments = new ConcurrentHashMap<String,DataStreamContainerWorker>();
	
	private int entityAssignmentIndex = 0; 
	
	// workers 

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void start(DataStream dataStream, DExecutor executor) throws Exception {

		this.dataStream = dataStream;

		Thread starter = new Thread() {

			public void run() {
				try {
					String eventTopic = "stream_" + dataStream.getName().toLowerCase() + "_event_all";
					DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
							.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(kafkaBrokers)
							.addTopic(eventTopic).setClientAndGroup("DataContainerCluster_" + DUUID.randomUUID(4),
									"DataContainerCluster_" + DUUID.randomUUID(4))
							.build();
					eventConsumer = DKafkaByteConsumer2.newInstance(spec);
					eventConsumer.start();
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



	public class WorkerStarter implements Runnable {

		public void run() {

		}
	}

	
	private DataStreamContainerWorker nextEntityWorker() { 
		if(entityAssignmentIndex == workers.size() - 1) { 
			entityAssignmentIndex = 0;
			return workers.get(0);
		} else { 
			DataStreamContainerWorker worker =  workers.get(entityAssignmentIndex);
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
					DataStreamContainerWorker worker = entityAssignments.get(snapshot.getIdentifier());
					if(worker == null) { 
						worker = nextEntityWorker();
						entityAssignments.put(snapshot.getIdentifier(), worker);
					}
					
				}
				if(event.getType() == GStreamEventType.EntitySignal) { 
					
				}
				if(event.getType() == GStreamEventType.TimeUpdate) { 
					
				}
				
				
			}
		}
		
	}

}

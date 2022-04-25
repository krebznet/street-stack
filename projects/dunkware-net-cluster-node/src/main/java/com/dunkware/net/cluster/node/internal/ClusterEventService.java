package com.dunkware.net.cluster.node.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.anot.AClusterPojoEventHandler;
import com.dunkware.net.cluster.node.events.EClusterComponentAdded;
import com.dunkware.net.cluster.node.events.DClusterComponentRemoved;
import com.dunkware.net.proto.cluster.GClusterEvent;
import com.dunkware.net.proto.cluster.GClusterEventType;
import com.dunkware.net.proto.cluster.GClusterPojoEvent;


public class ClusterEventService implements DKafkaByteHandler2 {
	
	@Autowired
	private ClusterConfig config;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Cluster cluster; 
	
	private DKafkaByteConsumer2 eventConsumer; 
	
	private List<PojoEventHandler> pojoEventHandlers = new ArrayList<PojoEventHandler>();
	private Semaphore pojoEventHandlerLock = new Semaphore(1);
	
	private EventProcessor eventProcessor;
	private BlockingQueue<GClusterEvent> eventQueue = new LinkedBlockingQueue<GClusterEvent>();
	
	Reflections reflections = null;
	public void start(ClusterImpl cluster) throws Exception { 
		this.cluster = cluster;
		cluster.getEventTree().getRoot().addEventHandler(this);
		if(logger.isDebugEnabled()) {
			logger.debug("Creating Reflection Instance");
		}
		reflections = new Reflections("com.dunkware");
		if(logger.isDebugEnabled()) { 
			logger.debug("Created Reflection Instance");
		}
		DKafkaByteConsumer2Spec kafkaSpec = null;
		try {
			kafkaSpec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest)
			.addBroker(config.getServerBrokers()).addTopic("cluster_core_events")
			.setClientAndGroup("NodeEventManager-" + cluster.getNodeId() + "_" + DUUID.randomUUID(5), DUUID.randomUUID(4))
			.build();
			eventConsumer = DKafkaByteConsumer2.newInstance(kafkaSpec);
			eventConsumer.start();
			eventConsumer.addStreamHandler(this);
		} catch (Exception e) {
			logger.error("Exception Creating Cluster Event Kafka Consumer " + e.toString());
			throw new Exception("Exception Creating Event Manager Kafka Consumer " + e.toString());
		}
		
		eventProcessor = new EventProcessor();
		eventProcessor.start();
		
	}
	
	
	@ADEventMethod()
	public void componentAdded(EClusterComponentAdded added	 ) {
		Object component = added.getComponent();
		for (Method method : component.getClass().getMethods()) {
			AClusterPojoEventHandler[] anots = method.getAnnotationsByType(AClusterPojoEventHandler.class);
			if(anots.length > 0) { 
				AClusterPojoEventHandler anot = anots[0];
				if(method.getParameterTypes().length != 1) { 
					logger.error("Annotated Pojo Event Method has more than 1 argument " + component.getClass().getName());
					return;
				}
				Class<?> param = method.getParameterTypes()[0];
			
				PojoEventHandler eventHandler = new PojoEventHandler();
				eventHandler.setMethod(method);
				eventHandler.setComponent(component);
				eventHandler.setPojoClass(anot.pojoClass());
				if(logger.isDebugEnabled()) { 
					logger.debug("Created Pojo Event Handler {} {} {}",component.getClass().getName(),method.getName(),anot.pojoClass().getName());
				}
				pojoEventHandlers.add(eventHandler);
			}
		}
		
	}
	
	@ADEventMethod()
	public void componentRemoved(DClusterComponentRemoved removed) { 
		// need to remove any cluster event listeners; 
	}
	
	
	
	
	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			GClusterEvent event = GClusterEvent.parseFrom(record.value());
			eventQueue.put(event);
		} catch (Exception e) {
			logger.error("Exception parsing GClusterEvent in kafka handler " + e.toString());
			
		}
	}




	public class PojoEventHandler { 
		
		private Object component; 
		private Method method; 
		private Class pojoClass;
		public Object getComponent() {
			return component;
		}
		public void setComponent(Object component) {
			this.component = component;
		}
		public Method getMethod() {
			return method;
		}
		public void setMethod(Method method) {
			this.method = method;
		}
		public Class getPojoClass() {
			return pojoClass;
		}
		public void setPojoClass(Class pojoClass) {
			this.pojoClass = pojoClass;
		} 
		
		
	}
	

	private class EventProcessor extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				GClusterEvent event = null;
				try {
					event = eventQueue.take();
				} catch (Exception e) {
					logger.error("Exception taking event from q " + e.toString());
					continue;
				}
				if(event.getType() == GClusterEventType.POJO) { 
					handlePojoEvent(event);
				}
				
			}
			
		}
		
		public void handlePojoEvent(GClusterEvent event) { 
			GClusterPojoEvent pojoEvent = event.getPojoEvent();
			String pojoClass = pojoEvent.getClassName();
			Class pojoClazz = null;
			Object parsedPojo = null;
			try {
				pojoClazz = Class.forName(pojoEvent.getClassName());
			} catch (Exception e) {
				return;
			}
			try {
				parsedPojo = DJson.getObjectMapper().readValue(pojoEvent.getJson(), pojoClazz);
			} catch (Exception e) {
				logger.error("Exception parsing pojo event json after class found on classpath " + e.toString());
				return;
			}
			try {
				pojoEventHandlerLock.acquire();
				for (PojoEventHandler handler : pojoEventHandlers) {
					if(handler.getPojoClass().isInstance(parsedPojo)) { 
						Method method = handler.getMethod();
						try {
							method.invoke(handler.getComponent(),parsedPojo);	
							if(logger.isDebugEnabled()) { 
								logger.debug("Invoked Pojo Event method on class {} method {}",handler.getClass().getName(),method.getName());
							}
						} catch (Exception e) {
							logger.error("Exception invoking pojo event method on class {} method {} exception {}",handler.getClass().getName(), method.getName(), e.toString());;
						}
					}
				}
			} catch (Exception e) {
				logger.error("Outside exception processing pojo event " + e.toString());
			} finally { 
				pojoEventHandlerLock.release();
			}
		}
	}
	
	
	
	
	
	
	
}

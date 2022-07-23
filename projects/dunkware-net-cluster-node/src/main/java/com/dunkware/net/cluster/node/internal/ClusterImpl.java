package com.dunkware.net.cluster.node.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.bitch.BitchLogger;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.cluster.json.job.ClusterJobState;
import com.dunkware.net.cluster.json.node.ClusterNodeServiceDescriptor;
import com.dunkware.net.cluster.json.node.ClusterNodeServiceType;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterJob;
import com.dunkware.net.cluster.node.ClusterJobRunner;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeException;
import com.dunkware.net.cluster.node.internal.runners.NetCallRequestRunner;
import com.dunkware.net.core.anot.NetAnnotationRegistry;
import com.dunkware.net.core.anot.NetCallServiceDescriptor;
import com.dunkware.net.core.anot.NetChannelServiceDescriptor;
import com.dunkware.net.core.data.NetDataFactory;
import com.dunkware.net.core.service.NetCallResponseCallback;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetChannelResponseCallback;
import com.dunkware.net.core.service.NetChannelService;
import com.dunkware.net.core.service.NetMessageHandler;
import com.dunkware.net.proto.cluster.GClusterEvent;
import com.dunkware.net.proto.cluster.GNodeUpdate;
import com.dunkware.net.proto.cluster.GNodeUpdateRequest;
import com.dunkware.net.proto.cluster.GNodeUpdateResponse;
import com.dunkware.net.proto.cluster.GReleaseWorkerNodesRequest;
import com.dunkware.net.proto.cluster.GReleaseWorkerNodesResponse;
import com.dunkware.net.proto.cluster.GReserveWorkerNodesRequest;
import com.dunkware.net.proto.cluster.GReserveWorkerNodesResponse;
import com.dunkware.net.proto.cluster.GReserveWorkerNodesResponse.GrantedNode;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc.GClustererviceStub;
import com.dunkware.net.proto.net.GNetChannelRequest;
import com.dunkware.net.proto.net.GNetMessage;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@Service
public class ClusterImpl implements Cluster {

	@Autowired
	private ClusterConfig clusterConfig;

	@Autowired
	private ApplicationContext ac;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ClusterRegistry registry;

	private DEventTree eventTree;

	private DExecutor executor;
	
	private NetDataFactory netDataFactory;

	private DKafkaByteProducer eventProducer;

	private DDateTime startTime;

	private ClusterEventService eventService;

	private List<ClusterJob> jobs = new ArrayList<ClusterJob>();

	private Map<String, ClusterNode> nodes = new ConcurrentHashMap<String, ClusterNode>();

	private Semaphore jobLock = new Semaphore(1);

	private ManagedChannel serverChannel;

	private List<ClusterNodeServiceDescriptor> netServiceDescriptors = new ArrayList<ClusterNodeServiceDescriptor>();

	private List<NetMessageHandler> netMessageHandlers = new ArrayList<NetMessageHandler>();

	private Semaphore netMessageHandlerLock = new Semaphore(1);

	private NodeUpdaterHandler updateHandler;

	private ClusterImpl cluster;

	private GClustererviceStub stub;
	
	private BlockingQueue<GNetMessage> netMessageQueue = new LinkedBlockingQueue<GNetMessage>();
	
	private DKafkaByteConsumer2 netMessageConsuer;
	
	private NetMessageRouter netMessageRouter = null;

	private BlockingQueue<GNodeUpdateResponse> responseQueue = new LinkedBlockingDeque<GNodeUpdateResponse>();
	
	private Map<String,NetCallService> callServices = new ConcurrentHashMap<String, NetCallService>();
	private Map<String,NetChannelService> channelServices = new ConcurrentHashMap<String, NetChannelService>();

	private AtomicInteger pendingRunnables = new AtomicInteger(0);

	private Reflections reflections;
	
	@PostConstruct
	public void load() {
		try {
			// need to build our service registry
			// create the kafka consume r
		
			try {
				
				reflections = new Reflections("com.dunkware");
				
				String topic = "cluster_node_" + clusterConfig.getNodeId() + "_net_messages";
				DKafkaByteConsumer2Spec spec = 
				DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(clusterConfig.getServerBrokers()).addTopic(topic).setClientAndGroup(clusterConfig.getNodeId() + "_" + DUUID.randomUUID(5), 
						clusterConfig.getNodeId() + "_" + DUUID.randomUUID(4)).build();
				netMessageConsuer = DKafkaByteConsumer2.newInstance(spec);		
				System.err.println("Consuming topic topic on node " + clusterConfig.getNodeId());
				netMessageConsuer.start();
				BitchLogger.log("Started kafka consumer to topic " + topic + " node " + getNodeId());
				netMessageConsuer.addStreamHandler(new NetMessageKafkaConsumer());
				netMessageRouter = new NetMessageRouter();
				netMessageRouter.start();
				
			
			} catch (Exception e) {
				logger.error("Exception creating cluster event kafka consumer " + e.toString());
			}

			Thread runner = new Thread() { 
				
				public void run() { 
					try {
						Thread.sleep(5000);
						for (NetChannelServiceDescriptor servDesc : NetAnnotationRegistry.get()
								.getChannelServiceDescriptors()) {
							ClusterNodeServiceDescriptor desc = new ClusterNodeServiceDescriptor();
							desc.setType(ClusterNodeServiceType.CHANNEL);
							desc.setClassName(servDesc.getClazz().getName());
							desc.setEndpoint(servDesc.getEndpoint());
							if(desc.getEndpoint() == null) { 
								logger.error("right fucki here");
							}
							try {
								
								NetChannelService service = (NetChannelService)servDesc.getClazz().newInstance();
								ac.getAutowireCapableBeanFactory().autowireBean(service);
								channelServices.put(servDesc.getEndpoint(), service);
								netServiceDescriptors.add(desc);
							} catch (Exception e) {
								logger.error("Exception instntianting net channel service " + servDesc.getClazz().getName() + " " + e.toString());
							}

							
						}

						for (NetCallServiceDescriptor servDesc : NetAnnotationRegistry.get().getCallServiceDescriptors()) {
							ClusterNodeServiceDescriptor desc = new ClusterNodeServiceDescriptor();
							desc.setType(ClusterNodeServiceType.CALL);
							desc.setEndpoint(servDesc.getEndpoint());
							desc.setClassName(servDesc.getClazz().getName());
							try {
								NetCallService callService = (NetCallService)servDesc.getClazz().newInstance();
								ac.getAutowireCapableBeanFactory().autowireBean(callService);
								callServices.put(servDesc.getEndpoint(), callService);
								netServiceDescriptors.add(desc);
							} catch (Exception e) {
								logger.error("Exception creating registered net call service " + e.toString());
							}

							
						}
					} catch (Exception e) {
						logger.error(MarkerFactory.getMarker(getNodeId()),
								"Exception parsing or building service descriptors in cluster load " + e.toString());
						System.exit(-1);
					}			
					
				}
			};
			
			
			runner.start();

			
			serverChannel = ManagedChannelBuilder.forTarget(clusterConfig.getClusterGrpc()).usePlaintext().build();
			ConnectivityState channelState = serverChannel.getState(true);
			if (channelState == ConnectivityState.TRANSIENT_FAILURE) {
				logger.error(MarkerFactory.getMarker("Crash"), "Getting Cluster GRPC Channel at "
						+ clusterConfig.getClusterGrpc() + " Returned Transietn Failture");
				;
				System.exit(-1);
			}

			if (channelState == ConnectivityState.CONNECTING) {
				int i = 1;
				while (channelState == ConnectivityState.CONNECTING) {
					i++;
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (i > 15) {
						logger.error(MarkerFactory.getMarker("Crash"), "Getting Cluster GRPC Channel at "
								+ clusterConfig.getClusterGrpc() + " Returned Transietn Failture");
						;
						System.exit(-1);
						;
					}
				}
			}
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"),
					"Getting Cluster GRPC Channel at " + clusterConfig.getClusterGrpc() + " Exception " + e.toString());
			;
			System.exit(-1);
		}
		
		startNodeStatConsumer(this);
		startTime = DDateTime.now(DTimeZone.NewYork);
		registry = new ClusterRegistry();
		registry.start(this);
		executor = new DExecutor(15);
		NetCallRequestRunner runner = new NetCallRequestRunner(this);
		ac.getAutowireCapableBeanFactory().autowireBean(runner);
		addNetMessageHandler(runner);
		// sstart the router thread. 
		netDataFactory = NetDataFactory.newInstance(executor);
		eventTree = DEventTree.newInstance(executor);
		eventService = new ClusterEventService();
		eventTree = DEventTree.newInstance(executor);
		ac.getAutowireCapableBeanFactory().autowireBean(eventService);
		try {
			eventService.start(this);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"), "Event Service Failed to start " + e.toString());
			System.exit(-1);
		}

		try {
			eventProducer = DKafkaByteProducer.newInstance(clusterConfig.getServerBrokers(), "cluster_core_events",
					"ClusterNode-" + getNodeId());
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"), "Exception creating cluster event producer " + e.toString());
			System.exit(-1);
		}

		

	}
	
	

	@Override
	public Reflections getDunkwareReflections() {
		return reflections;
	}


	@Override
	public NetDataFactory netDataFactory() {
		return netDataFactory;
	}




	@Override
	public NetCallService netCallService(String endpoint) throws ClusterNodeException {
		NetCallService serv = callServices.get(endpoint);
		if(serv == null) { 
			throw new ClusterNodeException("NetCall Service not found for endpoint" + endpoint);
		}
		return serv;
	}



	@Override
	public NetChannelService netChannelService(String endpoint) throws ClusterNodeException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void addNetMessageHandler(final NetMessageHandler handler) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					netMessageHandlerLock.acquire();
					netMessageHandlers.add(handler);
					pendingRunnables.decrementAndGet();
				} catch (Exception e) {
					logger.error("Exception adding net message handler " + e.toString());
				} finally {
					netMessageHandlerLock.release();
				}
			}
		};

		pendingRunnables.incrementAndGet();
		getExecutor().execute(runner);
	}

	@Override
	public void removeNetMessageHandler(NetMessageHandler handler) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					netMessageHandlerLock.acquire();
					netMessageHandlers.remove(handler);
					pendingRunnables.decrementAndGet();
				} catch (Exception e) {
					logger.error("Exception adding net message handler " + e.toString());
				} finally {
					netMessageHandlerLock.release();
				}
			}
		};

		pendingRunnables.incrementAndGet();
		getExecutor().execute(runner);
	}

	public ClusterRegistry getRegistry() {
		return registry;
	}

	@Override
	public DEventTree getEventTree() {
		return eventTree;
	}

	@Override
	public ClusterConfig getConfig() {
		return clusterConfig;
	}

	@Override
	public DExecutor getExecutor() {
		return executor;
	}

	@Override
	public ClusterJob startJob(ClusterJobRunner runner, String type, String name) throws ClusterNodeException {
		try {
			jobLock.acquire();
			ClusterJobImpl job = new ClusterJobImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(job);
			job.start(runner, type, name);
			jobs.add(job);
			return job;
		} catch (Exception e) {
			throw new ClusterNodeException("Start Job rinner " + runner.getClass().getName() + e.toString(), e);
		} finally {
			jobLock.release();
		}

	}

	@Override
	public void pojoEvent(Object pojo) throws ClusterNodeException {
		try {
			GClusterEvent event = ClusterHelper.pojoEvent(pojo, getNodeId());
			eventProducer.sendBytes(event.toByteArray());
		} catch (Exception e) {
			throw new ClusterNodeException("Exception sending pojo event " + e.toString(), e);
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Pojo Event Published {} node {}", pojo.getClass().getName(), getNodeId());
		}
	}

	@Override
	public void addComponent(Object component) {
		registry.addComponent(component);
	}

	@Override
	public ManagedChannel getServerChannel() {
		return serverChannel;
	}

	@Override
	public void removeComponent(Object component) {
		registry.removeComponent(component);

	}

	@Override
	public String getNodeId() {
		return clusterConfig.getNodeId();
	}

	@Override
	public LocalDateTime now() {
		return LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
	}

	@Override
	public ClusterNodeStats getStats() {
		ClusterNodeStats stats = new ClusterNodeStats();
		stats.setGrpcEndpoint(clusterConfig.getServerGrpc());
		stats.setHttpEndpoint(clusterConfig.getServerHttp());
		stats.setId(getNodeId());
		stats.setStart(startTime.toString());
		stats.setExecutorStats(executor.getStats());
		int activeJobs = 0;
		for (ClusterJob clusterJob : jobs) {
			if (clusterJob.getState() == ClusterJobState.Running) {
				activeJobs++;
			}
		}
		stats.setRunningJobCount(activeJobs);
		stats.setType(clusterConfig.getNodeType());
		for (ClusterNodeServiceDescriptor desc : netServiceDescriptors) {
			if(desc.getEndpoint() == null || desc.getType() == null || desc.getClassName() == null) { 
				logger.error("Stop Right Fucking Here null shit on your service descriptors");
			}
		}
		stats.setServices(netServiceDescriptors);
		

		// now we need the other stuff -
		return stats;
	}

	public void startNodeStatConsumer(ClusterImpl cluster) {
		this.cluster = cluster;
		Thread runner = new Thread() {

			public void run() {

				try {

					Thread.sleep(5000);

				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					stub = GClustererviceGrpc.newStub(ManagedChannelBuilder
							.forTarget(cluster.getConfig().getClusterGrpc()).usePlaintext().build());
					GNodeUpdateRequest request = GNodeUpdateRequest.newBuilder().setNode(cluster.getNodeId()).build();

					StreamObserver<GNodeUpdateResponse> response = new StreamObserver<GNodeUpdateResponse>() {

						@Override
						public void onNext(GNodeUpdateResponse value) {
							if (logger.isDebugEnabled()) {
								logger.debug(cluster.getNodeId() + " received node update response");
							}
							responseQueue.add(value);
						}

						@Override
						public void onError(Throwable t) {
							logger.error("Error returned on Node Update Request " + t.getMessage(), t);
							t.printStackTrace();
						}

						@Override
						public void onCompleted() {
							// TODO Auto-generated method stub

						}

					};

					stub.nodeUpdateStream(request, response);
					updateHandler = new NodeUpdaterHandler();
					updateHandler.start();

				} catch (Exception e) {
					logger.error(MarkerFactory.getMarker("Crash"),
							"Exception Start Cluster Node Service GRPC Update Stream Request " + e.toString());
					System.exit(-1);
				}
			}
		};
		runner.start();

	}

	@Override
	public void releaseWorkerNodes(String owner) {
		GReleaseWorkerNodesRequest req = GReleaseWorkerNodesRequest.newBuilder().setIdentifier(owner).build();

		StreamObserver<GReleaseWorkerNodesResponse> fucker = new StreamObserver<GReleaseWorkerNodesResponse>() {

			@Override
			public void onNext(GReleaseWorkerNodesResponse value) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub

			}
		};
		stub.releaseWorkerNodes(req, fucker);
		return;
	}

	@Override
	public ClusterNode getNode(String nodeId) throws ClusterNodeException {
		return nodes.get(nodeId);
	}

	@Override
	public List<ClusterNode> reserveWorkerNodes(String owner, int requested) throws Exception {
		GReserveWorkerNodesRequest request = GReserveWorkerNodesRequest.newBuilder().setIdentifier(owner)
				.setRequestedNodes(requested).build();
		List<ClusterNode> obtained = new ArrayList<ClusterNode>();
		AtomicInteger fucker = new AtomicInteger(0);
		stub.reserveWorkerNodes(request, new StreamObserver<GReserveWorkerNodesResponse>() {

			@Override
			public void onNext(GReserveWorkerNodesResponse value) {
				for (GrantedNode granted : value.getGrantedNodesList()) {
					try {
						obtained.add(getNode(granted.getNodeId()));
					} catch (Exception e) {
						logger.error("error getting granted noded wtf " + e.toString());

					}

				}
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCompleted() {
				fucker.incrementAndGet();
			}

		});

		int count = 1;
		while (fucker.get() == 0) {
			try {
				Thread.sleep(300);
				count++;
				if (count > 14) {
					throw new Exception("Fuck you nothing back");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return obtained;
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		int resultCount = 0;
		for (String key : nodes.keySet()) {
			ClusterNode node = nodes.get(key);
			if (node.getState() == ClusterNodeState.Available) {
				if (node.getType() == ClusterNodeType.Worker) {
					results.add(node);
					resultCount++;
					if (resultCount == count) {
						return results;
					}
				}
			}
		}
		throw new ClusterNodeException(
				"Requested " + count + " avaiable nodes is bigger than nodes avaiable which is " + results.size());
	}

	@Override
	public int getAvailableWorkerCount() {
		return getAvailableWorkerNodes().size();
	}

	@Override
	public List<ClusterNode> getAvailableWorkerNodes() {
		List<ClusterNode> results = new ArrayList<ClusterNode>();
		for (String key : nodes.keySet()) {
			ClusterNode node = nodes.get(key);
			if (node.getState() == ClusterNodeState.Available) {
				if (node.getType() == ClusterNodeType.Worker) {
					results.add(node);
				}
			}
		}
		return results;
	}

	private class NodeUpdaterHandler extends Thread {

		public void run() {
			while (!interrupted()) {
				GNodeUpdateResponse response = null;
				try {
					response = responseQueue.take();
					if (logger.isTraceEnabled()) {
						logger.trace("Handling {} Node Updates on Node " + cluster.getNodeId(),
								response.getUpdatesList().size());
					}
				} catch (Exception e) {
					logger.error("Exception taking GNodeUpdateResponse from queue " + e.toString());
					continue;
				}
				for (GNodeUpdate gUpdate : response.getUpdatesList()) {
					ClusterNodeUpdate update = null;
					try {
						update = DJson.getObjectMapper().readValue(gUpdate.getJson(), ClusterNodeUpdate.class);
					} catch (Exception e) {
						logger.error("Exception deserializing node update " + e.toString());
						continue;
					}

					ClusterNodeImpl node = (ClusterNodeImpl) nodes.get(update.getNode());
					if (node == null) {
						node = new ClusterNodeImpl();
						ac.getAutowireCapableBeanFactory().autowireBean(node);
						node.start(update);
						nodes.put(node.getId(), node);
					} else {
						node.update(update);
					}
				}

			}
		}
	}

	void nodeUpdates(List<ClusterNodeUpdate> updates) {
		Runnable nodeUpdater = new Runnable() {

			@Override
			public void run() {
				for (ClusterNodeUpdate update : updates) {
					ClusterNode node = nodes.get(update.getNode());
					if (node == null) {
						// log new node
						node = new ClusterNodeImpl();
						ClusterNodeImpl impl = (ClusterNodeImpl) node;
						impl.start(update);
						;
						nodes.put(update.getNode(), node);

					} else {
						ClusterNodeImpl impl = (ClusterNodeImpl) node;
						impl.update(update);
					}

				}
			}
		};
		getExecutor().execute(nodeUpdater);
	}
	
	

	@Override
	public String getNetTopic() {
		String topic = "cluster_node_" + getNodeId() + "_net_messages";
		return topic;
	}

	@Override
	public ClusterNode getCallRequestNode(String endpoint) throws ClusterNodeException {
		for (ClusterNode node : nodes.values()) {
		for (ClusterNodeServiceDescriptor serv : node.getStats().getServices()) {
			if(serv.getEndpoint().equals(endpoint)) { 
				return node;
			}
		}
			
		}
		throw new ClusterNodeException("Service not found in cluster for endpoint " + endpoint);
		
	}

	@Override
	public boolean hasCallNode(String endpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasChannelNode(String endpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClusterNode getChannelRequestNode(String endpoint) {
		for (ClusterNode node : nodes.values()) {
			
		}
		return null;
		
	}

	

	
	@Override
	public void netCall(String endPoint, int requestId, NetCallResponseCallback callback) throws ClusterNodeException {
		ClusterNetCall call = new ClusterNetCall();
		ac.getAutowireCapableBeanFactory().autowireBean(call);
		ClusterNode serviceNode = getCallRequestNode(endPoint);
		call.invoke(endPoint, requestId, serviceNode,callback);
		cluster.getExecutor().execute(call);
	}

	



	@Override
	public void netChannel(GNetChannelRequest request, NetChannelResponseCallback callback) throws ClusterNodeException {
		// TODO Auto-generated method stub
		
	}

	private class NetMessageKafkaConsumer implements DKafkaByteHandler2 {

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {
			try {
				GNetMessage message = GNetMessage.parseFrom(record.value());
				netMessageQueue.add(message);
				
			} catch (Exception e) {
				logger.error("Exception parsing GNetMessage from kafka consumer " + e.toString());
			}
		}
	}
	
	private class  NetMessageRouter extends Thread { 
		
		public void run() { 
			while(! interrupted()) { 
				try {
					GNetMessage message = netMessageQueue.take();
					try {
						BitchLogger.log("Net Message Router Recieved Message node " + getNodeId());
						netMessageHandlerLock.acquire();
						
						for (NetMessageHandler handler : netMessageHandlers) {
							BitchLogger.log("invoking handler " + handler.getClass().getName());
							handler.handle(message);
						}
						BitchLogger.log("finsihed callign handlers");
					} catch (Exception e) {
						logger.error("Exception invoking net message handler " + e.toString());
					} finally { 
						netMessageHandlerLock.release();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		
	}

}

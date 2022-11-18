package com.dunkware.net.cluster.node.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

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
import com.dunkware.net.cluster.json.node.ClusterNodeServiceDescriptor;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.net.cluster.json.node.ClusterNodeUpdate;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeException;
import com.dunkware.net.proto.cluster.GNodeUpdate;
import com.dunkware.net.proto.cluster.GNodeUpdateRequest;
import com.dunkware.net.proto.cluster.GNodeUpdateResponse;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc;
import com.dunkware.net.proto.cluster.service.GClustererviceGrpc.GClustererviceStub;
import com.dunkware.spring.message.MessageTransport;

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

	private DDateTime startTime;

	private ClusterEventService eventService;

	private Map<String, ClusterNode> nodes = new ConcurrentHashMap<String, ClusterNode>();

	private ManagedChannel serverChannel;

	private List<ClusterNodeServiceDescriptor> netServiceDescriptors = new ArrayList<ClusterNodeServiceDescriptor>();

	private NodeUpdaterHandler updateHandler;

	private ClusterImpl cluster;

	private GClustererviceStub stub;

	private DKafkaByteConsumer2 messageConsumer;

	private MessageHandler messageHandler;

	private MessageRouter messageRouter;

	private BlockingQueue<GNodeUpdateResponse> responseQueue = new LinkedBlockingDeque<GNodeUpdateResponse>();

	private BlockingQueue<MessageTransport> messageQueue = new LinkedBlockingQueue<MessageTransport>();

	private Reflections reflections;
	
	private ClusterChannelService channelService = null;

	@PostConstruct
	public void load() {
		try {

			Thread runner = new Thread() {

				public void run() {
					try {
						Thread.sleep(3000);
						try {

							reflections = new Reflections("com.dunkware");
							String topic = "cluster_node_message_" + clusterConfig.getNodeId();
							DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
									.newBuilder(ConsumerType.Auto, OffsetType.Latest)
									.addBroker(clusterConfig.getServerBrokers()).addTopic(topic)
									.setClientAndGroup(clusterConfig.getNodeId() + "_" + DUUID.randomUUID(5),
											clusterConfig.getNodeId() + "_" + DUUID.randomUUID(4))
									.build();
							messageConsumer = DKafkaByteConsumer2.newInstance(spec);
							messageConsumer.start();
							messageHandler = new MessageHandler();
							messageConsumer.addStreamHandler(messageHandler);
							messageRouter = new MessageRouter();
							messageRouter.start();

						} catch (Exception e) {
							logger.error("Exception creating cluster event kafka consumer " + e.toString());
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

	}

	@Override
	public Reflections getDunkwareReflections() {
		return reflections;
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
		stats.setRunningJobCount(activeJobs);
		stats.setType(clusterConfig.getNodeType());
		for (ClusterNodeServiceDescriptor desc : netServiceDescriptors) {
			if (desc.getEndpoint() == null || desc.getType() == null || desc.getClassName() == null) {
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
							if (logger.isTraceEnabled()) {
								logger.trace(cluster.getNodeId() + " received node update response");
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
	public ClusterNode getNode(String nodeId) throws ClusterNodeException {
		return nodes.get(nodeId);
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

	private class MessageHandler implements DKafkaByteHandler2 {

		@Override
		public void record(ConsumerRecord<String, byte[]> record) {
			try {
				MessageTransport transport = DJson.getObjectMapper().readValue(record.value(), MessageTransport.class);
				messageQueue.add(transport);
			} catch (Exception e) {
				logger.error("Invalid Cluster Message handler " + e.toString());
			}
		}
	}

	private class MessageRouter extends Thread {

		public void run() {

			while (!interrupted()) {
				MessageTransport transport = null;
				try {
					transport = messageQueue.take();
				} catch (Exception e) {
					logger.error("Invalid Cluster Message Router " + e.toString());
					continue;
				}
				
				// MessageRouter ---> --> filter chain 
				// 

			}

		}
	}
	
	
	

}

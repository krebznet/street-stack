package com.dunkware.trade.service.stream.server.session.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.net.cluster.node.trace.Trace;
import com.dunkware.net.cluster.node.trace.TraceLogger;
import com.dunkware.net.cluster.node.trace.TraceService;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.trade.service.stream.json.controller.model.StreamEntitySpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatsSpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.json.message.StreamSessionPing;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;
import com.dunkware.trade.service.stream.server.cluster.ClusterNode;
import com.dunkware.trade.service.stream.server.cluster.ClusterService;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.util.GStreamHelper;
import com.dunkware.trade.service.stream.server.controller.util.StreamSessionSpecBuilder;
import com.dunkware.trade.service.stream.server.messaging.StreamMessageService;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.StreamSessionService;
import com.dunkware.trade.service.stream.server.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.session.events.EStreamSessionStopping;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionDO;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionTickerDO;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionTickerRepo;
import com.dunkware.trade.service.stream.server.spring.RuntimeService;
import com.dunkware.trade.service.stream.server.tick.StreamTickService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.dunkware.trade.tick.service.protocol.ticker.spec.TradeTickerListSpec;
import com.dunkware.xstream.xproject.XScriptProject;

public class StreamSessionImpl implements StreamSession {

	private StreamController stream;
	private Logger logger = LoggerFactory.getLogger(getClass());

	private ClusterNode[] clusterNodes;
	private List<StreamSessionNode> nodes = new ArrayList<StreamSessionNode>();
	private String sessionId;

	private StreamSessionStatsSpec stats = new StreamSessionStatsSpec();

	private DEventNode eventNode;
	
	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	@Autowired
	private TraceService trace;

	@Autowired
	private RuntimeService runtimeService;

	private List<StreamSessionExtension> extensions;

	@Autowired
	private ClusterService clusterService;

	@Autowired
	private StreamTickService tickService;

	@Autowired
	private StreamSessionService sessionService;

	@Autowired
	private StreamSessionRepo sessionRepo;

	@Autowired
	private StreamSessionTickerRepo tickerRepo;

	@Autowired
	private StreamMessageService messageService;

	private StreamSessionDO sessionEntity;

	private TradeTickerListSpec tickerList = null;

	private BlockingQueue<StreamSessionNode> nodeStartCallbackQueue = new LinkedBlockingQueue<StreamSessionNode>();
	private BlockingQueue<StreamSessionNode> nodeStopCallbackQueue = new LinkedBlockingQueue<StreamSessionNode>();

	private EntityHeartbeatUpdater heartbeatUpdater;

	private Map<String, String> sessionProperties = new HashMap<String, String>();

	private StreamSessionStart startMessage = new StreamSessionStart();

	private List<StreamEntitySpec> entities = new ArrayList<StreamEntitySpec>();

	private SnapshotMessageSender snapshotSender;

	
	private String kafkaSnapshotTopic;

	private String kafkaSignalTopic;

	private long sessionEntityId;
	
	private TraceLogger traceLogger;

	@Override
	public void startSession(StreamController stream) throws StreamSessionException {
		traceLogger = trace.logger(getClass());
		this.stream = stream;
		if (logger.isDebugEnabled()) {
			logger.debug("Session Debug - StartSession() called on StreamSession()");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
		String formatDateTime = LocalDateTime.now().format(formatter);
		sessionId = stream.getName() + "_" + formatDateTime;
		traceLogger.addLabel("SessionIdentifier", sessionId);
		traceLogger.info("Starting Stream {} Session {}",stream.getName(),sessionId);
		
		
		// set kafka topics
		kafkaSignalTopic = "stream_" + stream.getName() + "_signals";
		kafkaSnapshotTopic = "stream_snapshots_" + sessionId;
		// initialize stats
		stats.setStatus(StreamSessionStatus.Starting);
		stats.setState(StreamSessionState.Green);
		stats.setStartingTime(DTime.now());
		stats.setStream(stream.getName());
		stats.setSessionId(sessionId);
		stats.setExceptionNodeCount(0);

		

		// Entity:

		// create event node
		eventNode = runtimeService.getEventTree().getRoot().createChild("/stream/session/" + stream.getName());
		// create session extensions
		extensions = sessionService.createExtensions();
		// create the session id

		logger.info("Starting Stream Session For {}", stream.getName());
		// get healthy cluster nodes with the right profiles
		clusterNodes = clusterService.getGreeNodes("StreamSessionWorker");
		// validate tick service is online and healthy
		try {
			if (!tickService.isOnline()) {
				stats.setStatus(StreamSessionStatus.Exception);
				stats.setException("Tick Service Not Available");
				traceLogger.error("Tick Service Coming Back as offline");
				throw new StreamSessionException("Tick Service Not Online");
			}
		} catch (Exception e) {
			throw new StreamSessionException("Tick service online threw exception " + e.toString());
		}

		// check our avaialble cluster node count
		if (clusterNodes.length == 0) {
			stats.setStatus(StreamSessionStatus.Exception);
			stats.setException("No Cluster Nodes Available");
			traceLogger.error("No available stream worker nodes found");
			throw new StreamSessionException(
					"No Cluster Nodes Available In Cluster Healthy & StreamSessionWorkerProfile");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Stream Debug - StartSession() session nodes created");
		}
		// create the xscript bundle

		if (logger.isDebugEnabled()) {
			logger.debug("Stream Debug - StartSession() scriptBundle deserailzied");
		}
		// get the ticker list configured for this stream
		try {
			tickerList = tickService.getClient().getTickerList(stream.getEntity().getTickerLists());
			if (logger.isDebugEnabled()) {
				logger.debug("Stream Debug - StartSession() Ticker List retreived");
			}
		} catch (Exception e) {
			stats.setStatus(StreamSessionStatus.Exception);
			stats.setException("Stream Ticker List Get Exception " + e.toString());
			throw new StreamSessionException("Exception getting ticker list " + e.toString());
		}
		// create our stream session nodes from the avaiable cluster nodes
		for (ClusterNode clusterNode : clusterNodes) {
			// validate ping on node
			boolean badNode = false;
			try {
				clusterNode.reqResp("/stream/worker/ping");
			} catch (Exception e) {
				boolean error = true;
				int errorCount = 0;
				while (error) {
					try {
						Thread.sleep(1000);
						clusterNode.reqResp("/stream/worker/ping");
						error = false;
					} catch (Exception e2) {
						errorCount++;
						if (errorCount > 9) {
							traceLogger.error("Session Starting could not ping workder node {} skipping", clusterNode.getId());
							logger.error("Stream Session Starting Could not ping worker node {} with endpoint {}",
									clusterNode.getId(), clusterNode.getEndpoint("/stream/worker/ping"));
							stats.setSkippedNodeCount(stats.getSkippedNodeCount() + 1);
							badNode = true;
							break;
						}
					}
				}
				if (badNode) {
					continue;
				}

			}
			StreamSessionNodeImpl node = new StreamSessionNodeImpl(this, clusterNode);

			nodes.add(node);
			// add node stats to our stats
			stats.getNodes().add(node.getStats());
		}
		// evenly route the tickers across the node set
		if (nodes.size() == 0) {
			logger.error("No Available Nodes To Start Stream Perhaps Ping Failed");
			throw new StreamSessionException("No available nodes");
		}
		int nodeIndex = 0;
		for (TradeTickerSpec ticker : tickerList.getTickers()) {
			StreamEntitySpec ent = new StreamEntitySpec();
			ent.setId(ticker.getId());
			ent.setIndentifier(ticker.getSymbol());
			this.entities.add(ent);
			if (nodeIndex == nodes.size()) {
				nodeIndex = 0;
			}
			getNodes().get(nodeIndex).getTickers().add(ticker);
			nodeIndex++;
		}
		// update node count on stats
		stats.setNodeCount(nodes.size());
		stats.setTickerCount(tickerList.getTickers().size());
		stats.setNodeTickerCount(nodes.get(0).getTickers().size());
		// create and initialize the session entity
		sessionEntity = new StreamSessionDO();
		sessionEntity.setDate(LocalDate.now());
		sessionEntity.setStatus(getStatus());
		sessionEntity.setStartingTime(LocalDateTime.now());
		sessionEntity.setStream(getStream().getEntity());
		sessionEntity.setStreamName(getStream().getName());
		sessionEntity.setVersion(getStream().getCurrentVersion());
		sessionEntity.setVersionValue(getStream().getCurrentVersion().getVersion());
		sessionEntity.setNodeCount(getNodes().size());
		sessionEntity.setLastHeartbeat(LocalDateTime.now());
		sessionEntity.setTickerCount(tickerList.getSize());
		for (TradeTickerSpec ticker : tickerList.getTickers()) {
			StreamSessionTickerDO ent = new StreamSessionTickerDO();
			ent.setSession(sessionEntity);
			ent.setSymbol(ticker.getSymbol());
			ent.setTickerId(ticker.getId());
			ent.setValidated(false);
			ent.setType(TradeTickerType.Equity);
			sessionEntity.getTickers().add(ent);
		}

		try {
			sessionRepo.save(sessionEntity);
			sessionEntityId = sessionEntity.getId();

		} catch (Exception e) {
			logger.error("Exception saving session entity " + e.toString());
			stats.setStatus(StreamSessionStatus.Exception);
			stats.setException("Internal Exception Saving Session Entity " + e.toString());
			throw new StreamSessionException("Internal Exception Saving Session Entity " + e.toString(), e.getCause());
		}
		// key logging here info starting stream with node count, session id, ticker
		// count
		logger.info("Starting Stream {} ID {} With {} Nodes, Ticker count for each node is {}", stream.getName(),
				getSessionId(), nodes.size(), tickerList.getTickers().size());
		// call session starting on session extensions
		for (StreamSessionExtension ext : extensions) {
			ext.sessionStarting(this);
		}
		// lets create and start our node launch handler that will receive callback on
		// node launch results
		NodeLaunchHandler nodeLaunchHandler = new NodeLaunchHandler();
		nodeLaunchHandler.start();
		// lets start each stream sesson node that will run in its own thread and notify
		// events to blocking queue
		// picked up by node launch handler
		int id = 1;
		for (StreamSessionNode node : nodes) {
			if (logger.isDebugEnabled()) {
				logger.debug("Stream Debug - StartSession() starting node " + node.getWorkerId());
			}
			stats.incrementStartingNodes();
			node.startNode(this, node.getNode(), node.getNodeId() + "_" + id, node.getTickers());
			id++;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Stream Debug - StartSession() start returning");
		}
		try {
			startMessage.setSpec(StreamSessionSpecBuilder.build(this));
			messageService.sendMessage(startMessage);
			traceLogger.info("Sent Session Start Message");
		} catch (Exception e) {
			traceLogger.error("Exception creating spec or sending start message {}",e.toString());
			logger.error("Exception building stream session spec " + e.toString());
			throw new StreamSessionException("Stream Start Message Creation Failed " + e.toString());
		}

		// start session event delay 5 seconds
		class EventSender extends Thread {

			public void run() {
				try {
					Thread.sleep(1000);
					DKafkaByteProducer producer = DKafkaByteProducer.newInstance(kafkaBrokers, "stream_" + getStream().getName().toLowerCase() + "_signals", getSessionId());
					GStreamEvent event = GStreamHelper.buildSessionStartEvent(StreamSessionImpl.this);
					producer.sendBytes(event.toByteArray());
					producer.dispose();
					producer = DKafkaByteProducer.newInstance(kafkaBrokers, "stream_" + getStream().getName().toLowerCase() + "_snapshots", getSessionId());
					producer.sendBytes(event.toByteArray());
					traceLogger.info("Sent GStream Start Events to signal and snapshot topics");
				} catch (Exception e) {
					traceLogger.error("Exception sending GStream Start Events " + e.toString());
					logger.error(
							"Exception sending stream session event message in controller session " + e.toString());
				}
			}
		}

		EventSender eventSender = new EventSender();
		eventSender.start();

	}

	@Override
	public void stopSession() throws StreamSessionException {
		traceLogger.info("Stop Session Method Invoked");
		if (heartbeatUpdater != null) {
			heartbeatUpdater.interrupt();
			heartbeatUpdater = null;
		}
		if (snapshotSender != null) {
			snapshotSender.interrupt();
		}
		// check for valid status to stop session
		if (getStatus() != StreamSessionStatus.Running) {
			throw new StreamSessionException("Cannot stop stream session when status is " + getStatus().toString());
		}
		// notify stopping event
		stats.setStatus(StreamSessionStatus.Stopping);
		sessionEntity.setStatus(StreamSessionStatus.Stopping);
		sessionRepo.save(sessionEntity);
		EStreamSessionStopping stopping = new EStreamSessionStopping(this);
		getEventNode().event(stopping);
		// invoke session stopping on extensions
		for (StreamSessionExtension ext : extensions) {
			ext.sessionStopping(this);
		}
		// create/setup NodeStopHandler thread
		NodeStopHandler stopHandler = new NodeStopHandler();
		stopHandler.start();
		// call stop on each node
		for (StreamSessionNode node : nodes) {
			node.stopNode();
		}
		
		class StopSender extends Thread { 
			
			public void start() { 
				try {
					Thread.sleep(1000);
					DKafkaByteProducer producer = DKafkaByteProducer.newInstance(kafkaBrokers, "stream_" + getStream().getName().toLowerCase() + "_signals", getSessionId());
					GStreamEvent event = GStreamHelper.buildSessionStopEvent(StreamSessionImpl.this);
					producer.sendBytes(event.toByteArray());
					producer.dispose();
					producer = DKafkaByteProducer.newInstance(kafkaBrokers, "stream_" + getStream().getName().toLowerCase() + "_snapshots", getSessionId());
					producer.sendBytes(event.toByteArray());
					traceLogger.info("Sent GStream Event Session Stop message to signal and snapshot writers");
				} catch (Exception e) {
					traceLogger.error("Exception sending GStream Event Session Stop " + e.toString());
					logger.error(
							"Exception sending stream session stop event message in controller session " + e.toString());
				}
			}
		}
		
		// send stop event 
		StopSender eventSender = new StopSender();
		eventSender.start();
		

		// send stop message 
		try {
			StreamSessionStop stop = new StreamSessionStop();
			stop.setSpec(startMessage.getSpec());
			messageService.sendMessage(stop);
			traceLogger.info("Sent StreamSessionStop message");
		} catch (Exception e) {
			traceLogger.error("Exception sending StreamSession Stop Message " + e.toString());;
			logger.error("Exception sending stream stop message " + e.toString());
		}
		stats.setStatus(StreamSessionStatus.Running);

	}

	@Override
	public StreamSessionStatus getStatus() {
		return stats.getStatus();
	}

	@Override
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	@Override
	public String getKafkaSignalTopic() {
		return kafkaSignalTopic;
	}

	@Override
	public String getKafkaSnapshotTopic() {
		return kafkaSnapshotTopic;
	}

	@Override
	public XScriptProject getScriptProject() {
		return stream.getScriptProject();
	}

	@Override
	public TradeTickerListSpec getTickerListSpec() {
		return tickerList;
	}

	@Override
	public StreamSessionStatsSpec getStats() {
		// Aggregate Node Stats
		aggregateNodeStats();
		return stats;
	}

	@Override
	public List<StreamSessionNode> getNodes() {
		return nodes;
	}

	@Override
	public StreamController getStream() {
		return stream;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public List<StreamSessionExtension> getExtensions() {
		return extensions;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public Long getSessionEntityId() {
		return sessionEntityId;
	}

	@Override
	public StreamSessionDO getEntity() {
		return sessionEntity;
	}

	public void nodeStartCallback(StreamSessionNode node) {
		this.nodeStartCallbackQueue.add(node);
	}

	public void nodeStopCallback(StreamSessionNode node) {
		this.nodeStopCallbackQueue.add(node);
	}

	@Override
	public Map<String, String> getProperties() {
		return sessionProperties;
	}

	public void setProperty(String name, String value) {
		this.sessionProperties.put(name, value);
	}

	private void aggregateNodeStats() {
		AtomicLong pendingTasks = new AtomicLong(0);
		AtomicLong completedTasks = new AtomicLong(0);
		AtomicLong timeoutTasks = new AtomicLong(0);
		AtomicInteger nodeProblemCount = new AtomicInteger(0);
		AtomicInteger nodeWarningCount = new AtomicInteger(0);
		AtomicInteger signalCount = new AtomicInteger(0);
		for (StreamSessionNode node : nodes) {
			signalCount.addAndGet(node.getStats().getSignalCount());
			pendingTasks.addAndGet(node.getStats().getPendingTaskCount());
			completedTasks.addAndGet(node.getStats().getCompletedTaskCount());
			timeoutTasks.addAndGet(node.getStats().getTimeoutTaskCount());
			nodeProblemCount.addAndGet(node.getStats().getProblems().size());
			nodeWarningCount.addAndGet(node.getStats().getWarnings().size());
		}
		stats.setSignalCount(signalCount.get());
		stats.setCompletedTaskCount(completedTasks.get());
		stats.setTimeoutTaskCount(timeoutTasks.get());
		stats.setPendingTaskCount(pendingTasks.get());
		stats.setNodeProblemCount(nodeProblemCount.get());
		stats.setNodeWarningCount(nodeWarningCount.get());
	}

	private class SnapshotMessageSender extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(10000);
					StreamSessionPing snapshot = new StreamSessionPing();
					snapshot.setSpec(startMessage.getSpec());
					messageService.sendMessage(snapshot);
				} catch (Exception e) {
					logger.error("Exception sending session snapshot");
				}
			}
		}
	}

	private class EntityHeartbeatUpdater extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(10000);
					sessionEntity.setLastHeartbeat(LocalDateTime.now());
					// sessionRepo.save(sessionEntity);
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception setting heart beat on session entity " + e.toString());
				}

			}
		}
	}

	private class NodeLaunchHandler extends Thread {

		private AtomicInteger pendingCallbacks = new AtomicInteger(nodes.size());

		public void run() {

			while (!interrupted()) {
				try {
					StreamSessionNode node = nodeStartCallbackQueue.take();
					// decrement starting nodes
					stats.decrementStartingNodes();
					// check if it started successfully
					if (node.getStatus() == StreamSessionNodeStatus.Running) {
						// increment running node count

						stats.incrementRunningNodes();
						stats.setStartTime(DTime.now());
					}
					if (node.getStatus() == StreamSessionNodeStatus.Exception) {
						// increment exception node count
						stats.incrementExceptionNodeCount();
						// add problem to stats
						String problem = "Node " + node.getWorkerId() + " Start Exception "
								+ node.getStats().getException();
						getStats().addProblem(problem);
						getStats().setState(StreamSessionState.Red);

					}
					int pending = pendingCallbacks.decrementAndGet();
					if (pending == 0) {
						getStats().setStatus(StreamSessionStatus.Running);
						stats.setStartTime(DTime.now());
						sessionEntity.setStatus(StreamSessionStatus.Running);
						DTime.from(LocalTime.now(DTimeZone.toZoneId(stream.getSpec().getTimeZone())));
						sessionEntity.setStartDateTime(LocalDateTime.now());
						sessionRepo.save(sessionEntity);
						EStreamSessionStarted startedEvent = new EStreamSessionStarted(StreamSessionImpl.this);
						getEventNode().event(startedEvent);
						// call session started on extensions
						for (StreamSessionExtension ext : extensions) {
							ext.sessionStarted(StreamSessionImpl.this);
						}
						// start heartbeat updater
						heartbeatUpdater = new EntityHeartbeatUpdater();
						heartbeatUpdater.start();

						// start snapshot sender
						snapshotSender = new SnapshotMessageSender();
						snapshotSender.start();
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

	}

	private class NodeStopHandler extends Thread {

		private AtomicInteger pendingCallbacks = new AtomicInteger(nodes.size());

		public void run() {

			while (!interrupted()) {
				try {
					StreamSessionNode node = nodeStopCallbackQueue.poll(60, TimeUnit.SECONDS);
					if (node == null) {
						logger.error(
								"Session Stop Callback Handler Wait Timeout For Pending Node Stop Callback, pending count is "
										+ pendingCallbacks.get());
						continue;
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Node Stop Callback Recieved From Worker Node {} ", node.getWorkerId());
					}
					if (pendingCallbacks.decrementAndGet() == 0) {
						if (logger.isDebugEnabled()) {
							logger.debug("Status Change To Stopped ");
						}
						stats.setStatus(StreamSessionStatus.Stopped);

						sessionEntity.setStopDateTime(LocalDateTime.now());
						sessionEntity.setStatus(StreamSessionStatus.Stopped);
						sessionRepo.save(sessionEntity);
						// notify stopped event
						EStreamSessionStopped stopped = new EStreamSessionStopped(StreamSessionImpl.this);
						getEventNode().event(stopped);
						// invoke sessionStopped on extensions
						for (StreamSessionExtension ext : extensions) {
							ext.sessionStopped(StreamSessionImpl.this);
						}
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
				}
			}
		}

	}

}

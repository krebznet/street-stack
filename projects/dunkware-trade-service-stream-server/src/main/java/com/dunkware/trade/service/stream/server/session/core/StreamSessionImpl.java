package com.dunkware.trade.service.stream.server.session.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.trade.service.stream.json.controller.model.StreamEntitySpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStats;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.util.GStreamHelper;
import com.dunkware.trade.service.stream.server.controller.util.StreamSessionSpecBuilder;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.StreamSessionService;
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

	private StreamSessionStats stats = new StreamSessionStats();

	private DEventNode eventNode;
	
	@Value("${kafka.brokers}")
	private String kafkaBrokers;


	@Autowired
	private RuntimeService runtimeService;

	private List<StreamSessionExtension> extensions;

	@Autowired
	private Cluster cluster;

	@Autowired
	private StreamTickService tickService;

	@Autowired
	private StreamSessionService sessionService;

	@Autowired
	private StreamSessionRepo sessionRepo;

	@Autowired
	private StreamSessionTickerRepo tickerRepo;

	private StreamSessionDO sessionEntity;

	private TradeTickerListSpec tickerList = null;

	private BlockingQueue<StreamSessionNode> nodeStartCallbackQueue = new LinkedBlockingQueue<StreamSessionNode>();
	private BlockingQueue<StreamSessionNode> nodeStopCallbackQueue = new LinkedBlockingQueue<StreamSessionNode>();

	private Map<String, String> sessionProperties = new HashMap<String, String>();

	private long sessionEntityId;
	
	private StreamSessionInput input;


	@Override
	public void startSession(StreamSessionInput input) throws StreamSessionException {
		
		this.input = input;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
		String formatDateTime = LocalDateTime.now().format(formatter);
		sessionId = stream.getName() + "_" + formatDateTime;
		
		// initialize stats
		stats.setStatus(StreamSessionStatus.Starting);
		stats.setState(StreamSessionState.Green);
		stats.setStartingTime(DTime.now());
		stats.setStream(stream.getName());
		stats.setSessionId(sessionId);
		stats.setExceptionNodeCount(0);

		// create event node
		eventNode = runtimeService.getEventTree().getRoot().createChild("/stream/session/" + stream.getName());
		// create session extensions
		extensions = sessionService.createExtensions();
		// create the session id

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
				
				} catch (Exception e) {
				
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
		
		// call stop on each node
		for (StreamSessionNode node : nodes) {
			node.stopNode();
		}
		
		try {
			DKafkaByteProducer producer = DKafkaByteProducer.newInstance(kafkaBrokers, "stream_" + getStream().getName().toLowerCase() + "_signals", getSessionId());
			GStreamEvent event = GStreamHelper.buildSessionStopEvent(StreamSessionImpl.this);
			producer.sendBytes(event.toByteArray());
			producer.dispose();
			producer = DKafkaByteProducer.newInstance(kafkaBrokers, "stream_" + getStream().getName().toLowerCase() + "_snapshots", getSessionId());
			producer.sendBytes(event.toByteArray());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// send stop message 
		try {
			StreamSessionStop stop = new StreamSessionStop();
			stop.setSpec(null);
			cluster.pojoEvent(stop);
		} catch (Exception e) {
			logger.error("Exception sending stream stop message " + e.toString());
		}
		stats.setStatus(StreamSessionStatus.Running);
	}

	@Override
	public StreamSessionStatus getStatus() {
		return stats.getStatus();
	}

	@Override
	public XScriptProject getScriptProject() {
		return stream.getScriptProject();
	}

	@Override
	public StreamSessionStats getStats() {
		// Aggregate Node Stats
		return stats;
	}

	@Override
	public List<StreamSessionNode> getNodes() {
		return nodes;
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

	

}

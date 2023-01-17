package com.dunkware.trade.service.stream.server.controller.session.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.data.NetScanner;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionService;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopping;
import com.dunkware.trade.service.stream.server.controller.util.StreamSessionSpecBuilder;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntity;
import com.dunkware.trade.service.stream.server.repository.StreamSessionProblemEntity;
import com.dunkware.trade.service.stream.server.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.xproject.XScriptProject;

public class StreamSessionImpl implements StreamSession {
	
	public static final String METRIC_PENDING_TASK_COUNT = "stream.us_equity.stats.cluster.pendingtasks"; 
	public static final String METRIC_NODE_COUNT = "stream.us_equity.stats.cluster.nodecount"; 
	

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private List<StreamSessionNode> nodes = new ArrayList<StreamSessionNode>();
	private StreamSessionInput input;
	


	@Autowired
	private ConfigService configService;

	@Autowired
	private StreamSessionService sessionService;

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private Cluster cluster;

	private StreamSessionStatus status;

	
	private List<StreamSessionExtension> extensionTypes;

	private NodeCallback nodeCallback = new NodeCallback();

	private AtomicInteger callbackCountDown = new AtomicInteger(0);
	private AtomicInteger stopCountDown = new AtomicInteger(0);
	@Autowired
	private StreamSessionRepo sessionRepo;

	private StreamSessionEntity sessionEntity;

	private DEventNode eventNode;

	private String sessionId;

	private AtomicInteger nodeStartFailures = new AtomicInteger(0);
	
	private MetricsUpdater metricsUpdater;

	private StreamSessionSpec sessionSpec;
	
	@Override
	public void startSession(StreamSessionInput input) throws StreamSessionException {
		this.input = input;
		nodeStartFailures = new AtomicInteger(0);
		eventNode = cluster.getEventTree().getRoot();
		;
		status = new StreamSessionStatus();
		status.setState(StreamSessionState.Starting);
		status.setStartingTime(DTime.from(LocalTime.now(DTimeZone.toZoneId(input.getController().getTimeZone()))));
		ZoneId zoneId = DTimeZone.toZoneId(input.getController().getTimeZone());
		sessionId = input.getController().getName() + " _"
				+ DunkTime.format(LocalDateTime.now(zoneId), DunkTime.YYYY_MM_DD_HH_MM_SS);
		logger.info(MarkerFactory.getMarker(sessionId), "Starting Session");
		List<List<TradeTickerSpec>> nodeTickers = StreamSessionHelper.nodeTickers(input.getWorkerNodes().size(),
				input.getTickers());
		List<TradeTickerSpec> dupes = StreamSessionHelper.searchForDups(nodeTickers);
		if(dupes.size() > 0) { 
			logger.error(MarkerFactory.getMarker("BUG"), "Duplicate Tickers in node subscriptions size " + dupes.size());
		}
		logger.debug("Created {} Ticker Lists for nodes", nodeTickers.size());
		int nodeIndex = 0;
		extensionTypes = sessionService.createExtensions();
		for (StreamSessionExtension ext : extensionTypes) {
			ext.sessionStarting(this);
		}
		try {
			createSessionEntity();	
		} catch (Exception e) {
			logger.error("Exception creating Sesssion Entity " + e.toString(),e);
			status.setState(StreamSessionState.Exception);
			status.setException(e.toString());
			throw new StreamSessionException("Exception creating Session Entity " + e.toString(),e);
		}
		
		for (ClusterNode workerNode : input.getWorkerNodes()) {
			StreamSessionNodeInput nodeInput = new StreamSessionNodeInput(nodeTickers.get(nodeIndex), workerNode,
					extensionTypes, this, input.getController(), this.nodeCallback);
			
			StreamSessionNodeImpl sessionNode = new StreamSessionNodeImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(sessionNode);
			logger.info(MarkerFactory.getMarker(sessionId), "Starting session node on worker " + workerNode.getId());
			nodes.add(sessionNode);
			sessionNode.startNode(nodeInput);
			nodeIndex++;

		}
		handleSessionStarted();

	}
 
	@Override
	public void stopSession() throws StreamSessionException {
		logger.info(MarkerFactory.getMarker(getSessionId()), "Stopping Session");
		EStreamSessionStopping stopping = new EStreamSessionStopping(this);
		eventNode.event(stopping);
		for (StreamSessionExtension streamSessionExtension : extensionTypes) {
			streamSessionExtension.sessionStopping(this);
		}
		for (StreamSessionNode streamSessionNode : nodes) {
			streamSessionNode.stopNode();
		}

		StreamSessionStop stop = new StreamSessionStop();
		try {
			stop.setSpec(StreamSessionSpecBuilder.build(this, sessionId));

		} catch (Exception e) {
			logger.error("Exception building session spec " + e.toString());
			logger.error("No start session getting sent to cluster");
		}
		for (StreamSessionNode node : nodes) {
			logger.info(MarkerFactory.getMarker(getSessionId()), "Stopping Session node " + node.getNodeId());
			node.stopNode();
		}
	}

	@Override
	public StreamSessionStatus getStatus() {
		if(status.getNodes() != null)
			status.getNodes().clear();
		status.setNodeCount(nodes.size());
		AtomicLong pendingTasks = new AtomicLong(0);
		AtomicLong completedTasks = new AtomicLong(0);
		AtomicLong timeoutTasks = new AtomicLong(0);
		AtomicLong tickCount = new AtomicLong(0);
		AtomicInteger nodeProblemCount = new AtomicInteger(0);
		AtomicInteger rowCount = new AtomicInteger(0);
		AtomicInteger nodeWarningCount = new AtomicInteger(0);
		AtomicInteger signalCount = new AtomicInteger(0);
		
		for (StreamSessionNode node : nodes) {
			StreamSessionWorkerStats workerStats = node.getWorkerStats();
			rowCount.addAndGet(workerStats.getRowCount());
			signalCount.addAndGet(workerStats.getSignalCount());
			pendingTasks.addAndGet(workerStats.getPendingTaskCount());
			completedTasks.addAndGet(workerStats.getCompletedTaskCount());
			timeoutTasks.addAndGet(workerStats.getTimeoutTaskCount());
			tickCount.addAndGet(workerStats.getTickCount());
			status.getNodes().add(workerStats);
		}
		status.setSignalCount(signalCount.get());
		status.setCompletedTasks(completedTasks.get());
		status.setTimeoutTasks(timeoutTasks.get());
		status.setTickerCount(rowCount.get());
		status.setTickCount(tickCount.get());
		status.setPendingTasks(pendingTasks.get());
		
		return status;
	}

	@Override
	public List<StreamSessionNode> getNodes() {
		return nodes;
	}

	@Override
	public StreamController getStream() {
		return input.getController();
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public List<StreamSessionExtension> getExtensions() {
		return extensionTypes;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public Long getSessionEntityId() {
		return sessionEntity.getId();
	}

	@Override
	public XScriptProject getScriptProject() {
		return input.getController().getScriptProject();
	}

	@Override
	public StreamSessionEntity getEntity() {
		return sessionEntity;
	}

	@Override
	public List<TradeTickerSpec> getTickers() {
		return input.getTickers();
	}

	@Transactional
	private void createSessionEntity() {
		sessionEntity = new StreamSessionEntity();
		sessionEntity.setProblemCount(0);
		sessionEntity.setDate(LocalDate.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
		sessionEntity.setState(getStatus().getState());
		sessionEntity.setStartingTime(LocalDateTime.now());
		sessionEntity.setStream(getStream().getEntity());
		sessionEntity.setStreamName(getStream().getName());
		sessionEntity.setVersion(getStream().getCurrentVersion());
		sessionEntity.setVersionValue(getStream().getCurrentVersion().getVersion());
		sessionEntity.setNodeCount(getNodes().size());
		sessionEntity.setTickerCount(input.getTickers().size());

		try {
			sessionRepo.save(sessionEntity);
		} catch (Exception e) {
			logger.error("Exception Saving Session Entity " + e.toString());
			status.getProblems().add("Exception Saving Session Entity " + e.toString());

		}
	}

	@Transactional
	private void saveSessionEntity() {
		sessionRepo.save(sessionEntity);
	}

	
	@Override
	public StreamSessionSpec getSessionSpec() {
		return sessionSpec;
	}

	private void handleSessionStarted() {
		StreamSessionStart start = new StreamSessionStart();
		try {
			start.setSpec(StreamSessionSpecBuilder.build(this, configService.getKafkaBrokers()));
			this.sessionSpec = start.getSpec();
			try {

			//	cluster.pojoEvent(start);
				logger.info(MarkerFactory.getMarker(sessionId), "Published Stream Session Start Message");
			} catch (Exception e) {
				logger.error("Exception sending session start message " + e.toString());
			}
		} catch (Exception e) {
			logger.error("Exception building session spec " + e.toString());
			logger.error("No start session getting sent to cluster");
		}
		for (StreamSessionExtension ext : extensionTypes) {
			ext.sessionStarted(this);
		}
		EStreamSessionStarted started = new EStreamSessionStarted(this);
		eventNode.event(started);
		input.getController().sessionEvent(started);
		metricsUpdater = new MetricsUpdater();
		metricsUpdater.start();
	}

	private void handleSessionStopped() {
		StreamSessionStop stop = new StreamSessionStop();
		try {
			stop.setSpec(StreamSessionSpecBuilder.build(this, configService.getKafkaBrokers()));
			try {

				//cluster.pojoEvent(stop);
				logger.info(MarkerFactory.getMarker(sessionId), "Published Stream Session Stop Message");
			} catch (Exception e) {
				logger.error("Exception sending session start message " + e.toString());
			}
			try {
				sessionEntity
						.setStopDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
				sessionEntity.setState(status.getState());
				saveSessionEntity();
			} catch (Exception e) {
				logger.error("Exception updating session entity stopped ");
			}
		} catch (Exception e) {
			logger.error("Exception building session spec " + e.toString());
			logger.error("No start session getting sent to cluster");
		}

		
		EStreamSessionStopped stopped = new EStreamSessionStopped(this);
		input.getController().sessionEvent(stopped);
		//eventNode.event(stopped);
		metricsUpdater.interrupt();
		

	}
	
	

	@Override
	public NetScanner entityScanner(SessionEntityScanner model) throws XStreamRuntimeException {
		// okay fun ->  
		return null;
	}



	private class NodeCallback implements StreamSessionNodeCallback {

		@Override
		public void nodeStarted(StreamSessionNode node) {
			logger.info(MarkerFactory.getMarker(getSessionId()),
					"Stream Session Node " + node.getNodeId() + "Started callback");
			int count = callbackCountDown.incrementAndGet();
			if (count == nodes.size()) {
				if (status.getProblems().size() > 0) {
					logger.warn(MarkerFactory.getMarker(getSessionId()),
							"Started Session with {} node start exceptions", nodeStartFailures.get());
				} else {
					status.setState(StreamSessionState.Running);
					logger.info(MarkerFactory.getMarker(getSessionId()), "Started Session With No Exceptions");
				}
				sessionEntity.setState(status.getState());
				sessionEntity
						.setStartDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
				saveSessionEntity();
				
			}

		}

		@Override
		public void nodeStartException(StreamSessionNode node) {
			logger.warn(MarkerFactory.getMarker(getSessionId()), "Session Node {} Failed To Sstart {} ", node.getNode(),
					node.getEventNode());
			nodeStartFailures.incrementAndGet();
			sessionEntity.setNodeStartFailures(sessionEntity.getNodeStartFailures() + 1);
			;
			sessionEntity.setProblemCount(sessionEntity.getProblemCount() + 1);
			status.getProblems().add("Node " + node.getNodeId() + " Start Exception " + node.getStartError());
			StreamSessionProblemEntity probEnt = new StreamSessionProblemEntity();
			probEnt.setProblem("Node Start Exception " + node.getNodeId() + " Exception " + node.getStartError());
			sessionEntity.getProblems().add(probEnt);
			status.setState(StreamSessionState.RunningErrors);
			sessionEntity.setState(StreamSessionState.RunningErrors);
			int count = callbackCountDown.incrementAndGet();

			if (count == nodes.size()) {
				logger.warn(MarkerFactory.getMarker(getSessionId()), "Started Session with {} node start exceptions",
						nodeStartFailures.get());
				sessionEntity.setState(status.getState());
				sessionEntity
						.setStartDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
				saveSessionEntity();
				handleSessionStarted();
			}
			saveSessionEntity();
		}

		@Override
		public void nodeStopped(StreamSessionNode node) {
			if (logger.isDebugEnabled()) {
				logger.debug(MarkerFactory.getMarker(getSessionId()), "Session Node {} stop Callback",
						node.getNodeId());
			}

			int count = stopCountDown.incrementAndGet();
			if (count == nodes.size()) {
				if (status.getProblems().size() > 0) {
					status.setState(StreamSessionState.CompletedErrors);
					logger.info(MarkerFactory.getMarker(getSessionId()), "Stopped Session Completed with errors");
				} else {
					logger.info(MarkerFactory.getMarker(getSessionId()), " Stopped Session with no errors ");
					status.setState(StreamSessionState.Completed);
				}
				handleSessionStopped();
			}
		}
		

	}
	
	
	private  class MetricsUpdater extends Thread { 
	
		public void run() { 
			try {
				while(!interrupted()) { 
					Thread.sleep(10000);
					StreamSessionStatus stats = getStatus();
				//	metricsService.setGauge(METRIC_NODE_COUNT, stats.getNodeCount());
				//	metricsService.setGauge(METRIC_PENDING_TASK_COUNT, stats.getPendingTasks());	
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error("Exception updating session metrics " + e.toString());
			}
		}
		
	}
	

}

package com.dunkware.trade.service.stream.server.controller.session.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStats;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionService;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStartExcepton;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopped;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStartException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopped;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionStopping;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntity;
import com.dunkware.trade.service.stream.server.repository.StreamSessionRepo;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class StreamSessionImpl implements StreamSession {

	public static final String METRIC_PENDING_TASK_COUNT = "stream.us_equity.stats.cluster.pendingtasks";
	public static final String METRIC_NODE_COUNT = "stream.us_equity.stats.cluster.nodecount";

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSession");

	private StreamSessionInput input;

	@Autowired
	private ExecutorService executorService;

	@Autowired
	private StreamSessionService sessionService;

	@Autowired
	private ApplicationContext ac;

	private StreamSessionStats status;

	private List<StreamSessionExtension> extensionTypes;

	@Autowired
	private StreamSessionRepo sessionRepo;

	private StreamSessionEntity sessionEntity;

	private DEventNode eventNode;

	private String sessionId;

	private AtomicInteger nodeStartFailures = new AtomicInteger(0);

	private StreamSessionSpec sessionSpec;

	private String startException;

	private int nodeCount = 0;

	private AtomicBoolean stoppedSessionInvoked = new AtomicBoolean(false);

	private Map<String, StreamSessionNode> nodes = new ConcurrentHashMap<String, StreamSessionNode>();

	private AtomicInteger nodeStartEventCount = new AtomicInteger(0);
	private AtomicInteger nodeStopEventCount = new AtomicInteger(0);

	// put the stream session capture in here?

	

	public StreamSessionImpl() {
		
	}

	@Override
	public StreamState getState() {
		return status.getState();
	}



	@Override
	public void startSession(StreamSessionInput input) throws StreamSessionException {
		this.input = input;
		status = new StreamSessionStats();
		status.setState(StreamState.Starting);
		status.setStartingTime(DTime.from(LocalTime.now(DTimeZone.toZoneId(input.getController().getTimeZone()))));
		nodeStartEventCount.set(0);
		logger.info(marker, "Starting Stream {} Session with {} workers ", input.getController().getName(),
				input.getWorkerNodes().size());

		stoppedSessionInvoked.set(false);
		this.input = input;

		nodeStartFailures = new AtomicInteger(0);
		eventNode = input.getController().getEventNode().createChild(this);
		eventNode.addEventHandler(this);

		ZoneId zoneId = DTimeZone.toZoneId(input.getController().getTimeZone());
		sessionId = input.getController().getName() + " _"
				+ DunkTime.format(LocalDateTime.now(zoneId), DunkTime.YYYY_MM_DD_HH_MM_SS);
		logger.info(marker, "Starting Stream Session " + input.getController().getName());
		List<List<TradeTickerSpec>> nodeTickers = StreamSessionHelper.nodeTickers(input.getWorkerNodes().size(),
				input.getTickers());
		List<TradeTickerSpec> dupes = StreamSessionHelper.searchForDups(nodeTickers);
		if (dupes.size() > 0) {
			logger.error(marker, "Duplicate Tickers in node subscriptions size " + dupes.size());
		}

		extensionTypes = sessionService.createExtensions();
		for (StreamSessionExtension ext : extensionTypes) {
			if (logger.isDebugEnabled()) {
				logger.debug(marker, "Session Extension " + ext.getClass().getName() + " Added");
			}
			ext.sessionStarting(this);
		}
		try {
			createSessionEntity();
		} catch (Exception e) {
			logger.error(marker, "Exception creating Sesssion Entity " + e.toString(), e);
			status.setState(StreamState.StartException);
			status.setException(e.toString());
			throw new StreamSessionException("Exception creating Session Entity " + e.toString(), e);
		}
		int numericId = 1;
		int nodeIndex = 0;
		for (DunkNetNode node : input.getWorkerNodes()) {
			String workerId = getStream().getName().toUpperCase() + "_" + numericId;
			StreamSessionNodeInput nodeInput = new StreamSessionNodeInput(numericId, workerId,
					nodeTickers.get(nodeIndex), node, extensionTypes, this, input.getController());
			StreamSessionNodeImpl sessionNode = new StreamSessionNodeImpl();
			input.getController().getSessionNodeBeans().add(sessionNode.getBean());
			ac.getAutowireCapableBeanFactory().autowireBean(sessionNode);
			sessionNode.start(nodeInput);
			logger.info(marker, "Started {} Session Worker {} on node {}", getStream().getName(), workerId,
					node.getId());
			nodes.put(node.getId(), sessionNode);
			nodeIndex++;
			numericId++;
		}

	}

	@Override
	public String killSession() {
		if (getState() == StreamState.Stopped) {
			return "Session Already Stopped";
		}

		int killCount = 0;
		for (StreamSessionNode node : nodes.values()) {
			node.cancel();
		}
		status.setState(StreamState.RunKill);
		return "Killed All Nodes";

	}

	@Override
	public void stopSession() throws StreamSessionException {
		if (!(status.getState() == StreamState.Running)) {
			throw new StreamSessionException("Session is not running, how do you want to stop it?");
		}
		logger.info(marker, "Stopping {} Session", getStream().getName());
		EStreamSessionStopping stopping = new EStreamSessionStopping(this);
		eventNode.event(stopping);
		for (StreamSessionExtension streamSessionExtension : extensionTypes) {
			streamSessionExtension.sessionStopping(this);
		}
		nodeStopEventCount.set(0);
		;
		for (StreamSessionNode node : nodes.values()) {
			node.stop();
		}
	}

	@Override
	public synchronized StreamSessionStats getStatus() {
		if (status.getNodes() != null)
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

		for (StreamSessionNode node : nodes.values()) {
			StreamSessionNodeBean nodeBean = node.getBean();
			rowCount.addAndGet(nodeBean.getEntityCount());
			signalCount.addAndGet(nodeBean.getSignalCount());
			pendingTasks.addAndGet(nodeBean.getTasksPending());
			completedTasks.addAndGet(nodeBean.getTasksCompleted());
			timeoutTasks.addAndGet(nodeBean.getTasksExpired());
			tickCount.addAndGet(nodeBean.getEntityCount());
			status.getNodes().add(nodeBean);
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
	public Collection<StreamSessionNode> getNodes() {
		return nodes.values();
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
		sessionEntity.setStartingTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
		sessionEntity.setStream(getStream().getEntity());
		sessionEntity.setStreamName(getStream().getName());
		sessionEntity.setVersion(getStream().getCurrentVersion());
		sessionEntity.setVersionValue(getStream().getCurrentVersion().getVersion());
		sessionEntity.setNodeCount(getNodes().size());
		sessionEntity.setTickerCount(input.getTickers().size());

		try {
			sessionRepo.save(sessionEntity);
		} catch (Exception e) {
			logger.error(marker, "Exception Saving Session Entity " + e.toString());
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

	

	@Override
	public StreamSessionNode getEntityNode(String ident) throws Exception {
		for (StreamSessionNode node : this.nodes.values()) {
			if (node.hasTicker(ident)) {
				return node;
			}
		}
		throw new Exception("Unresolved entity does not have node " + ident);
	}

	@ADEventMethod()
	public void nodeStarted(EStreamSessionNodeStarted nodeStarted) {
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Event Session node " + nodeStarted.getNode().getWorkerId() + "Started");
		}

		if (logger.isDebugEnabled()) {
			logger.debug(marker, "Starting Stream Session Start Runnable {}", getStream().getName());
		}
		int started = nodeStartEventCount.incrementAndGet();
		if (started == nodes.size()) {
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					handleSessionSart();
				}
			};
			executorService.execute(runner);

		}

	}

	@ADEventMethod()
	public void nodeStartException(EStreamSessionNodeStartExcepton exp) {
		logger.error(marker, "Stream {} Session Node {} Start Exception {}", getStream().getName(),
				exp.getNode().getWorkerId(), exp.getException());
		nodeStartFailures.incrementAndGet();
		status.getNodeStartErrors().add("Node Worker " + exp.getNode().getWorkerId() + " " + exp.getException());
		int started = nodeStartEventCount.incrementAndGet();
		if (started == nodes.size()) {
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					handleSessionSart();
				}
			};
			executorService.execute(runner);

		}

	}

	@ADEventMethod
	public void nodeStopped(EStreamSessionNodeStopped stopped) {
		int stopCount = nodeStopEventCount.incrementAndGet();
		if (stopCount == nodes.size()) {
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					handleSessionStop();
				}
			};
			executorService.execute(runner);

		}
	}

	@ADEventMethod
	public void nodeStopExcetion(EStreamSessionNodeStopException stopExp) {
		int stopCount = nodeStopEventCount.incrementAndGet();
		if (stopCount == nodes.size()) {
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					handleSessionStop();
				}
			};
			executorService.execute(runner);

		}
	}

	private void handleSessionSart() {
		logger.info(marker, "Handling Stream {} Session Start", getStream().getName());
		if (nodeStartFailures.get() > 0) {
			this.status.setState(StreamState.StartException);
			for (StreamSessionNode node : nodes.values()) {
				if (node.isRunning()) {
					node.cancel();
				}
			}
			eventNode.event(new EStreamSessionStartException(this,"Node Errors"));
			return;
		}

		this.status.setState(StreamState.Running);
		this.status.setStartTime(DTime.now());
		for (StreamSessionExtension ext : extensionTypes) {
			ext.sessionStarted(this);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(marker, "EStreamSessionStarted Event Trigger");
		}
		EStreamSessionStarted started = new EStreamSessionStarted(this);
		eventNode.event(started);
	}

	private void handleSessionStop() {
		status.setState(StreamState.Stopped);
		;
		logger.info(marker, "Handling Stream {} Session Stop", getStream().getName());
		this.stoppedSessionInvoked.set(true);
		try {
			sessionEntity.setStopDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
			sessionEntity.setState(status.getState());
			saveSessionEntity();
		} catch (Exception e) {
			logger.error(marker, "Exception updating session entity stopped ");
		}
		for (StreamSessionExtension streamSessionExtension : extensionTypes) {
			streamSessionExtension.sessionStopped(this);
		}
		EStreamSessionStopped stopped = new EStreamSessionStopped(this);
		eventNode.event(stopped);
	}

	@Override
	public XScriptBundle getXScriptBundle() {
		return getStream().getScriptBundle();
	}

}

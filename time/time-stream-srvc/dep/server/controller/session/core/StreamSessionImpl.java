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

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.cluster.events.StreamSessionStarting;
import com.dunkware.trade.service.stream.cluster.events.StreamSessionStopped;
import com.dunkware.trade.service.stream.cluster.events.StreamSessionStopping;
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
import com.dunkware.utils.core.events.DunkEventNode;
import com.dunkware.utils.core.events.anot.ADunkEventHandler;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.time.DunkTime;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.model.XScriptBundle;

import jakarta.transaction.Transactional;

//TODO: AVINASHANV-18 Controller Stream Session
/**
 * this is where we create session nodes for each instance of the stream worker service
 * and the node is responsbile for starting a worker stream node. we abstract all the 
 * distribution and can treat a cluster stream as a single entity so we can do things like
 * execute scannes that behind the scenes has a controller that talks to each node to pass in
 * the scanner expression, signal listeners etc. 
 */
public class StreamSessionImpl implements StreamSession {

	public static final String METRIC_PENDING_TASK_COUNT = "stream.us_equity.stats.cluster.pendingtasks";
	public static final String METRIC_NODE_COUNT = "stream.us_equity.stats.cluster.nodecount";
	private Marker stopTrace = MarkerFactory.getMarker("StreamStopTrace");
	Marker stop = MarkerFactory.getMarker("SessionStop");
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

	@Autowired
	private DunkNet dunkNet;

	private StreamSessionEntity sessionEntity;

	private String sessionId;

	private AtomicInteger nodeStartFailures = new AtomicInteger(0);

	private StreamSessionSpec sessionSpec;

	private String startException;
	
	//private LocalTime startTime; 
	
	private LocalTime stopTime; 

	private int nodeCount = 0;

	private AtomicBoolean stoppedSessionInvoked = new AtomicBoolean(false);

	private Map<String, StreamSessionNode> nodes = new ConcurrentHashMap<String, StreamSessionNode>();

	private AtomicInteger nodeStartEventCount = new AtomicInteger(0);
	private AtomicInteger nodeStopEventCount = new AtomicInteger(0);

	private LocalDateTime startTime;
	
	private DunkEventNode eventNode; 

	// put the stream session capture in here?

	public StreamSessionImpl() {

	}

	@Override
	public StreamState getState() {
		return status.getState();
	}

	@Override
	public StreamSessionInput getInput() {
		return input;
	}
	
	
	

	@Override
	public ZoneId getZoneId() {
		return getStream().getTimeZone();
	}

	@Override
	public LocalTime getStartTime() {
		return startTime.toLocalTime();
	}

	@Override
	public LocalTime getStopTime() {
		return stopTime;
	}

	@Override
	public int getStreamId() {
		return (int)input.getController().getEntity().getId();	
	}
	
	

	@Override
	public DunkEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public void startSession(StreamSessionInput input) throws StreamSessionException {
		this.eventNode = input.getController().getEventNode().createChild(this);
		if(logger.isInfoEnabled()) { 
			logger.info("Starting stream session {}",input.getController().getName());
		}
		this.input = input;
		status = new StreamSessionStats();
		try {
			createSessionEntity();
		} catch (Exception e) {
			logger.error(marker, "Exception creating Sesssion Entity " + e.toString(), e);
			status.setState(StreamState.StartException);
			status.setException(e.toString());
			throw new StreamSessionException("Exception creating Session Entity " + e.toString(), e);
		}

		status.setState(StreamState.Starting);
		Thread sender = new Thread() { 
			
			public void run() { 
				StreamSessionStarting starting = new StreamSessionStarting();
				starting.setIdentifier(getStream().getName());
				starting.setStartingTime(getStream().getDateTime());
				starting.setSessionId(sessionEntity.getId());
				
				try {
					dunkNet.event(starting);	
				} catch (Exception e) {
					logger.error(marker,"Exception sending stream starting event " + e.toString());;
				}

			}
		};
		
		sender.start();
		status.setStartingTime(getStream().getTime());
		nodeStartEventCount.set(0);
		logger.info(marker, "Starting Stream {} Session with {} workers ", input.getController().getName(),
				input.getWorkerNodes().size());

		stoppedSessionInvoked.set(false);
		this.input = input;

		nodeStartFailures = new AtomicInteger(0);
		
		
		

		
		sessionId = input.getController().getName() + " _"
				+ DunkTime.format(LocalDateTime.now(getZoneId()), DunkTime.YYYY_MM_DD_HH_MM_SS);
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

		int numericId = 1;
		int nodeIndex = 0;
		for (DunkNetNode node : input.getWorkerNodes()) {
			String workerId = getStream().getName().toUpperCase() + "_" + numericId;
			StreamSessionNodeInput nodeInput = new StreamSessionNodeInput(numericId, workerId,
					nodeTickers.get(nodeIndex), node, extensionTypes, this, input.getController());
			StreamSessionNodeImpl sessionNode = new StreamSessionNodeImpl();
			nodeInput.setNodeBean(new StreamSessionNodeBean());
			
			if (logger.isDebugEnabled()) {
				logger.debug(marker, "Adding Stream Session Node Bean to controller "
						+ nodeInput.getNode().getId() + " " + nodeInput.getWorkerId());
			}
			
			
			ac.getAutowireCapableBeanFactory().autowireBean(sessionNode);
			sessionNode.start(nodeInput);
			sessionNode.getEventNode().adDunkEventHandler(this);
			input.getController().getSessionNodeBeans().getReadWriteLock().writeLock().lock();
			input.getController().getSessionNodeBeans().add(sessionNode.getBean());
			input.getController().getSessionNodeBeans().getReadWriteLock().writeLock().unlock();
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
		logger.info(stopTrace, "Stop Session Invoked");
		if (!(status.getState() == StreamState.Running)) {
			logger.error(stopTrace, "Stop Session throwing exception because status is not running but is " + status.getState().toString());
			throw new StreamSessionException("Session is not running, how do you want to stop it?");
		}
		logger.info(stopTrace, "Setting session status to stopping");
		status.setState(StreamState.Stopping);

		Thread fucker = new Thread() {

			public void run() {
				logger.info(stopTrace, "stopSessionMethod() thread running");
				StreamSessionStopping netMessage = null;
				try {
					logger.info(stop, "StreamSessionStopping message creating");
					netMessage = new StreamSessionStopping();
					netMessage.setId(getStream().getEntity().getId());
					netMessage.setIdentifier(getStream().getName());
					netMessage.setStartTime(startTime);
					netMessage.setStopTime(getStream().getDateTime());
					netMessage.setSessionId(getEntity().getId());
					logger.info(stop, "Sending stop session " + DunkJson.serializePretty(netMessage));
					System.out.println("Bun in hell sent the fuckin messge");
					StreamSessionImpl.this.dunkNet.event(netMessage);
				} catch (Exception e) {
					logger.error(stop, "StreamSessionStopping message creae exception " + e.toString());

				}

				try {

					logger.info(marker, "Sending stop session " + DunkJson.serializePretty(netMessage));
				} catch (Exception e) {
					logger.error(marker, "Stopping Session Message not sent " + e.toString());
				}

				logger.info(marker, "Stopping {} Session", getStream().getName());
				EStreamSessionStopping stopping = new EStreamSessionStopping(StreamSessionImpl.this);
				eventNode.event(stopping);
				logger.info(stopTrace, "Inovoking Session Stopping on stream session extensions");
				for (StreamSessionExtension streamSessionExtension : extensionTypes) {
					streamSessionExtension.sessionStopping(StreamSessionImpl.this);

				}
				nodeStopEventCount.set(0);
				;
				for (StreamSessionNode node : nodes.values()) {
					logger.info(stopTrace, "Calling stop() on node " + node.getNodeId());
					node.stop();
				}

			}

		};

		fucker.start();
		
		

	}

	@Override
	public synchronized StreamSessionStats getStats() {
		if (status.getNodes() != null)
			status.getNodes().clear();
		status.setNodeCount(nodes.size());
		AtomicLong pendingTasks = new AtomicLong(0);
		AtomicLong completedTasks = new AtomicLong(0);
		AtomicLong timeoutTasks = new AtomicLong(0);
		AtomicLong tickCount = new AtomicLong(0);

		AtomicInteger rowCount = new AtomicInteger(0);
		AtomicInteger nodeIssueCount = new AtomicInteger(0);
		AtomicInteger signalCount = new AtomicInteger(0);
		AtomicLong entitySnapshotPublishCount = new AtomicLong(0);

		for (StreamSessionNode node : nodes.values()) {
			StreamSessionNodeBean bean = node.getBean();
			pendingTasks.addAndGet(bean.getTasksPending());
			completedTasks.addAndGet(bean.getTasksCompleted());
			timeoutTasks.addAndGet(bean.getTasksExpired());
			signalCount.addAndGet(bean.getSignalCount());
			nodeIssueCount.addAndGet(bean.getIssueCount());
			rowCount.addAndGet(bean.getEntityCount());
			tickCount.addAndGet(bean.getTickCount());
			entitySnapshotPublishCount.addAndGet(node.getBean().getEntitySnapshotCount());
			status.getNodes().add(bean);
		}
		status.setSignalCount(signalCount.get());
		status.setCompletedTasks(completedTasks.get());
		status.setTimeoutTasks(timeoutTasks.get());
		status.setTickerCount(rowCount.get());
		status.setTickCount(tickCount.get());
		status.setSignalCount(signalCount.get());
		status.setPendingTasks(pendingTasks.get());
		status.setNodeIssueCount((int)nodeIssueCount.get());
		status.setEntitySnapshotCount(entitySnapshotPublishCount.get());
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
		sessionEntity.setDate(LocalDate.now(getStream().getTimeZone()));
		sessionEntity.setState(getStats().getState());
		sessionEntity.setStartingTime(LocalDateTime.now(getStream().getTimeZone()));
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
	public StreamSessionNode getEntityNode(String ident) throws Exception {
		for (StreamSessionNode node : this.nodes.values()) {
			if (node.hasTicker(ident)) {
				return node;
			}
		}
		throw new Exception("Unresolved entity does not have node " + ident);
	}

	@Override
	public StreamSessionNode getEntityNode(int entityId) throws Exception {
		for (StreamSessionNode node : nodes.values()) {
			List<TradeTickerSpec> tickers = node.getTickers();
			for (TradeTickerSpec tradeTickerSpec : tickers) {
				if (tradeTickerSpec.getId() == entityId) {
					return node;
				}
			}
		}
		throw new Exception("Entity id " + entityId + " not found on any session worker nodes");
	}

	@ADunkEventHandler
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

	
	@ADunkEventHandler
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

	
	@ADunkEventHandler
	public void nodeStopped(EStreamSessionNodeStopped stopped) {
		logger.info(stopTrace, "nodeStopped() invoked from node " + stopped.getNode().getNodeId());
		int stopCount = nodeStopEventCount.incrementAndGet();
		logger.info(stopTrace, "Incremented stopCounter to " + stopCount);
		if (stopCount == nodes.size()) {
			logger.info(stopTrace, "nodeStopped() creating handleSessionStop() runnable");
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					try {
						logger.info(stopTrace, " invoking handleSessionStopped()");
						handleSessionStop();						
						logger.info(stopTrace, "invoked handleSessionStopped()");
					} catch (Exception e) {
						logger.error(stopTrace, "Exception invoking handleSesssionStopped() " + e.toString());
					}

				}
			};
			executorService.execute(runner);

		}
	}

	@ADunkNetEvent
	public void nodeStopExcetion(EStreamSessionNodeStopException stopExp) {
		logger.error(stopTrace, "nodeStopException() invoked from node {} exception is {}",stopExp.getNode().getNodeId(), stopExp.getException());
		int stopCount = nodeStopEventCount.incrementAndGet();
		logger.info(stopTrace, "nodeStopException() stopCount = " + stopCount);
		if (stopCount == nodes.size()) {
			logger.info(stopTrace, "nodeStopEception() creating handleSessionStop() runnable");
			Runnable runner = new Runnable() {

				@Override
				public void run() {
					try {
						logger.info(stopTrace, "nodeStopException() runnable invoking handleSessionStop() in Runnable");
						handleSessionStop();						
						logger.info(stopTrace, "nodeStopException() runnable invoked handleSessionStop() in Runnable");
					} catch (Exception e) {
						logger.error(stopTrace, "nodeStopException() runnable exception calling handleSessionStop() error {}",e.toString(),e);
					}

				}
			};
			executorService.execute(runner);

		}
	}

	private void handleSessionSart() {
		startTime = LocalDateTime.now(getStream().getTimeZone());
		logger.info(marker, "Handling Stream {} Session Start", getStream().getName());
		if (nodeStartFailures.get() > 0) {
			this.status.setState(StreamState.StartException);
			for (StreamSessionNode node : nodes.values()) {
				if (node.isRunning()) {
					node.cancel();
				}
			}
			
			eventNode.event(new EStreamSessionStartException(this, "Node Errors"));
			return;
		}

		this.status.setState(StreamState.Running);
		this.status.setStartTime(LocalTime.now());
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
		logger.info(stopTrace, "handleSessionStop() invoked setting status state to Stopped");
		status.setState(StreamState.Stopped);
		;
		logger.info(marker, "Handling Stream {} Session Stop", getStream().getName());

		
		this.stoppedSessionInvoked.set(true);
		try {
			LocalDateTime stop =  LocalDateTime.now(getStream().getTimeZone());
			stopTime = stop.toLocalTime();
			sessionEntity.setStopDateTime(getStream().getDateTime());
			sessionEntity.setState(status.getState());
			logger.info(stopTrace, "handleSessionStop() Updating session entity with stop date time and state	");
			saveSessionEntity();
		} catch (Exception e) {
			logger.error(stopTrace, "handleSessionStop() Exception saving session entity on s");
			logger.error(marker, "Exception updating session entity stopped ");
		}
		logger.info(stopTrace, "handleSessionstop() invoking sessionStopped on excetions");
		for (StreamSessionExtension streamSessionExtension : extensionTypes) {

			streamSessionExtension.sessionStopped(this);
		}
		logger.info(stopTrace, "handleSessionStop() invoked sessionStopped on extensions sensing session stopped DunkEvent");
		EStreamSessionStopped stopped = new EStreamSessionStopped(this);
		eventNode.event(stopped);
		
		Thread fucker = new Thread() {

			public void run() {
				logger.info(stopTrace, "sending StreamSessionStopped DUnkNet event message");
				StreamSessionStopped netMessage = null;
				try {
					logger.info(stop, "StreamSessionStopped message creating");
					netMessage = new StreamSessionStopped();
					netMessage.setId(getStream().getEntity().getId());
					netMessage.setIdentifier(getStream().getName());
					netMessage.setStartTime(startTime);
					netMessage.setStopTime(getStream().getDateTime());
					netMessage.setSessionId(getEntity().getId());
					netMessage.setProperties(input.getProperties());
					logger.info(stop, "Sending stop session " + DunkJson.serializePretty(netMessage));
					StreamSessionImpl.this.dunkNet.event(netMessage);
				} catch (Exception e) {
					logger.error(stopTrace, "Exception sending StreamSessionStopped message {}",e.toString(),e);
					logger.error(stop, "StreamSessionStopped message creae exception " + e.toString());

				}

			

			}

		};

		fucker.start();
	}

	@Override
	public XScriptBundle getXScriptBundle() {
		return getStream().getScriptBundle();
	}

}

package com.dunkware.trade.service.stream.server.session.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionInput;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.session.StreamSessionService;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionDO;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionProblemDO;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionRepo;
import com.dunkware.trade.service.stream.server.session.repository.StreamSessionTickerDO;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;
import com.dunkware.xstream.xproject.XScriptProject;

public class StreamSessionImpl implements StreamSession {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	private List<StreamSessionNode> nodes = new ArrayList<StreamSessionNode>();
	private StreamSessionInput input; 
	
	@Autowired
	private StreamSessionService sessionService; 
	
	@Autowired
	private ApplicationContext ac;
	
	private StreamSessionStatus status;
	
	private List<StreamSessionExtension> extensionTypes;
	
	private NodeCallback nodeCallback = new NodeCallback();
	
	private AtomicInteger callbackCountDown = new AtomicInteger(0);
	private AtomicInteger stopCountDown = new AtomicInteger(0);
	@Autowired
	private StreamSessionRepo sessionRepo; 
	
	private StreamSessionDO sessionEntity;
	
	@Override
	public void startSession(StreamSessionInput input) throws StreamSessionException {
		this.input = input;
		status = new StreamSessionStatus();
		status.setState(StreamSessionState.Starting);
		status.setStartingTime(DTime.
				from(LocalTime.now(DTimeZone.toZoneId(input.getController().getTimeZone()))));
		List<List<TradeTickerSpec>> nodeTickers = StreamSessionHelper.nodeTickers(input.getWorkerNodes().size(), input.getTickers());
		int nodeIndex = 0;
		extensionTypes = sessionService.createExtensions();
		for (ClusterNode workerNode : input.getWorkerNodes()) {
			StreamSessionNodeInput nodeInput = new StreamSessionNodeInput(nodeTickers.get(nodeIndex),workerNode,extensionTypes,this,input.getController(),this.nodeCallback);
			StreamSessionNodeImpl sessionNode = new StreamSessionNodeImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(sessionNode);
			callbackCountDown.incrementAndGet();
			sessionNode.startNode(nodeInput);
			nodes.add(sessionNode);
		}
	}

	@Override
	public void stopSession() throws StreamSessionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StreamSessionStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StreamSessionExtension> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DEventNode getEventNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSessionEntityId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XScriptProject getScriptProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamSessionDO getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String name, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TradeTickerSpec> getTickers() {
		return input.getTickers();
	}
	
	@Transactional
	private void createSessionEntity() { 
		sessionEntity = new StreamSessionDO();
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
		for (TradeTickerSpec ticker : input.getTickers()) {
			StreamSessionTickerDO ent = new StreamSessionTickerDO();
			ent.setSession(sessionEntity);
			ent.setTickerIdentifer(ticker.getSymbol());
			ent.setTickerId(ticker.getId());
			ent.setValidated(false);
			ent.setType(TradeTickerType.Equity);
			sessionEntity.getTickers().add(ent);
		}

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
	
	private class NodeCallback implements StreamSessionNodeCallback {

		@Override
		public void nodeStarted(StreamSessionNode node) {
			int count = callbackCountDown.incrementAndGet();
			if(count == nodes.size()) { 
				if(status.getProblems().size() == 0) { 
					status.setState(StreamSessionState.Running);
					sessionEntity.setState(StreamSessionState.Running);
					sessionEntity.setStartDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
					saveSessionEntity();
				}
			}
		}

		@Override
		public void nodeStartException(StreamSessionNode node) {
			sessionEntity.setProblemCount(sessionEntity.getProblemCount() + 1);
			status.getProblems().add("Node " + node.getNodeId() + " Start Exception " + node.getStartError());
			StreamSessionProblemDO probEnt = new StreamSessionProblemDO();
			probEnt.setProblem("Node Start Exception " + node.getNodeId() + " Exception " + node.getStartError());
			sessionEntity.getProblems().add(probEnt);
			status.setState(StreamSessionState.RunningErrors);
			sessionEntity.setState(StreamSessionState.RunningErrors);
			int count = callbackCountDown.incrementAndGet();
			if(count == nodes.size()) { 
				if(status.getProblems().size() == 0) { 
					sessionEntity.setState(StreamSessionState.RunningErrors);
					sessionEntity.setStartDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
					saveSessionEntity();
				}
			}
			saveSessionEntity();
		}

		@Override
		public void nodeStopped(StreamSessionNode node) {
			int count = stopCountDown.incrementAndGet();
			if(count == nodes.size()) { 
				if(status.getProblems().size() > 0) { 
					status.setState(StreamSessionState.CompletedErrors);
				} else { 
					status.setState(StreamSessionState.Completed);
				}
				sessionEntity.setStopDateTime(LocalDateTime.now(DTimeZone.toZoneId(input.getController().getTimeZone())));
				sessionEntity.setState(status.getState());
				saveSessionEntity();
			}
		} 
		
		
	}


	
	

}

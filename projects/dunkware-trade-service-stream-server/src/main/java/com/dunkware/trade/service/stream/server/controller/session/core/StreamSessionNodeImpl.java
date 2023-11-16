package com.dunkware.trade.service.stream.server.controller.session.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequestListener;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.json.worker.service.StreamEntitySnapshotReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCancelReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCreateReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeKilled;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStartExcepton;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopped;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionNodeImpl implements StreamSessionNode, DunkNetChannelHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
    private Marker stopMarker = MarkerFactory.getMarker("StreamStop");
	private XStreamBundle streamBundle;

	private StreamSessionNodeInput input;

	private ConcurrentHashMap<String, String> tickerMap = new ConcurrentHashMap<String, String>();

	private StreamState state = StreamState.Starting;

	private String startException = null;

	private String stopException;

	private DStopWatch startingTimer;

	private DStopWatch stoppingTimer;

	private volatile boolean starting = false;

	private volatile boolean stopping = false;

	private String workerId;

	private DEventNode eventNode;

	private AtomicBoolean stopped = new AtomicBoolean(false);

	private List<String> errors = new ArrayList<String>();

	private Marker marker = MarkerFactory.getMarker("StreamSessionNode");

	private DunkNetNode dunkNode;

	private DunkNetChannel channel;

	private StreamSessionWorkerStartReq startReq;
	
	private StreamSessionNodeBean bean = new StreamSessionNodeBean();
	
	private StreamSessionWorkerStats workerStats = null;
	
	private boolean startingInitiazed = false; 
	private boolean stoppingInitiazed = false; 
	
	@Value("${dunknet.brokers}")
	private String kafkaBrokers; 
	

	@Override
	public void start(StreamSessionNodeInput input) {

		this.input = input;
		this.dunkNode = input.getNode();
		bean.setWorkerId(input.getWorkerId());
		bean.setNodeId(input.getNode().getId());
		bean.setId(input.getNumericId());
		bean.setStatus(state.name());
		bean.setSignalCount(0);
		eventNode = input.getSession().getEventNode().createChild(this);

		for (TradeTickerSpec tickerSpec : input.getTickers()) {
			tickerMap.put(tickerSpec.getSymbol().toUpperCase(), tickerSpec.getName());
		}

		Thread starter = new Thread() {

			public void run() {
				starting = true;
				startingInitiazed = true;
				try {
					startingTimer = DStopWatch.create();
					startingTimer.start();
					streamBundle = createBundle();
				} catch (Exception e) {
					startException = e.toString();
					setState(StreamState.StartException);
					eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this, startException));
					startingTimer.stop();
					starting = false; 
					logger.error(marker, "Node Start Exception {} creating stream bundle {}",input.getWorkerId(),e.toString());
					return;
				}

				notifyExtensions(StreamSessionExtension.NODE_STARTING);
				startReq = new StreamSessionWorkerStartReq();
				startReq.setWorkerId(input.getWorkerId());
				startReq.setNumericId(input.getNumericId());
				startReq.setStream(input.getSession().getStream().getName());
				startReq.setSessionId(input.getSession().getSessionId());
				startReq.setStreamBundle(streamBundle);
				startReq.setNodeId(input.getNode().getId());
				startReq.setKafkaBrokers(kafkaBrokers);
				startReq.setSignals(input.getSession().getInput().getSignalTypes());
				startReq.setStreamDescriptor(input.getSession().getStream().getDescriptor());
				startReq.setStreamProperties(input.getSession().getInput().getProperties());;
				startReq.setEntitySessionId(input.getSession().getEntity().getId());
				try {
					StreamSessionWorkerCreateReq req = new StreamSessionWorkerCreateReq();
					req.setWorkerId(input.getWorkerId());
					DunkNetChannelRequest request = dunkNode.channel(req);
					channel = request.get(90, TimeUnit.SECONDS);	
					channel.setHandler(StreamSessionNodeImpl.this);
				} catch (Exception e) {
					logger.error(marker, "Exception creating session node chanel " + e.toString());
					setState(StreamState.StartException);
					startException = "Exception creating node channel " + e.toString();
					eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this,
							"Exception creating channel " + e.toString()));
					return;
				}
				
			}

		};
		starter.start();
	}

	@Override
	public XStreamBundle getStreamBundle() {
		return streamBundle;
	}

	@Override
	public boolean isStarting() {
		return starting;
	}
	
	

	@Override
	public StreamEntitySnapshot entitySnapshot(int entityId) throws Exception {
		StreamEntitySnapshotReq req = new StreamEntitySnapshotReq();
		req.setEntityId(entityId);
		try {
			Object result = channel.serviceBlocking(req);			
			try {
				StreamEntitySnapshot snapshot = (StreamEntitySnapshot)result;
				return snapshot;
			} catch (Exception e) {
				throw new Exception("exception casting entity snapshot service result to correct class type " + result.getClass().getName());
			}
		} catch (Exception e) {
			throw new Exception("exception calling entity snapshot service on worker " + e.toString());
		}
	}

	@Override
	public boolean isStopping() {
		return stopping;
	}
	

	@Override
	public boolean isRunning() {
		if(state == StreamState.Running) {
			return true;
		}
		return false;
	}

	@Override
	public String getStartExcetpion() {
		return startException;
	}

	@Override
	public String getStopException() {
		return stopException;
	}

	@Override
	public double stoppingElapsedTime() {
		if(!stoppingInitiazed) {
			return 0;
		}
		if(stopping) { 
			return stoppingTimer.getRunningSeconds();
		}
		return stoppingTimer.getCompletedSeconds();
	}

	@Override
	public double startingElapsedTime() {
		if(!startingInitiazed) { 
			return 0;
		}
		if(starting) { 
			return startingTimer.getRunningSeconds();
		}
		return startingTimer.getCompletedSeconds();
	}

	
	@Override
	public DunkNetChannel getChannel() {
		return channel;
	}

	@Override
	public DunkNetNode getDunkNode() {
		return dunkNode;
	}

	@ADunkNetEvent
	public void workerStats(StreamSessionWorkerStats stats) {
		bean.setEntityCount(stats.getRowCount());
		bean.setSignalCount(stats.getSignalCount());
		if(stats.getStreamTime() != null)
		bean.setStreamTime(stats.getStreamTime().toHHmmSS());
		if(stats.getSystemTime() != null)
		bean.setSystemTime(stats.getSystemTime().toHHmmSS());
		bean.setIssueCount(errors.size());
		bean.setTasksCompleted(stats.getCompletedTaskCount());
		bean.setTasksPending(stats.getPendingTaskCount());
		bean.setTasksExpired(stats.getTimeoutTaskCount());
		bean.notifyUpdate();
		this.workerStats = stats;
		
		
		// okay here mother fucker think about it -> 
	}

	@Override
	public StreamState getState() {
		return state;
	}

	@Override
	public String getNodeId() {
		return input.getNode().getId();
	}

	@Override
	public List<TradeTickerSpec> getTickers() {
		return input.getTickers();
	}

	@Override
	public StreamSession getSession() {
		return input.getSession();
	}

	@Override
	public int getNumericId() {
		return input.getNumericId();
	}

	@Override
	public String getWorkerId() {
		return input.getWorkerId();
	}

	@Override
	public List<String> getErrors() {
		return errors;
	}
	

	@Override
	public StreamSessionNodeBean getBean() {
		return bean;
	}
	
	

	@Override
	public void stop() {
			logger.info(stopMarker, "Calling stop on node {} and worker id {}", input.getNode().getId(),input.getWorkerId());
			if(stoppingInitiazed) { 
				logger.error(marker, "Stop invoked on node with stop initiazed");
			}
			stopping = true;
			stoppingTimer = DStopWatch.create();
			stoppingTimer.start();
			stoppingInitiazed = true;
			if(state != StreamState.Running) { 
				stopping = false;
				stoppingTimer.stop();
				logger.info(stopMarker, "Not sending stop service method to worker node because status is not running node {} worker {}",input.getNode().getId(), input.getWorkerId());
				eventNode.event(new EStreamSessionNodeStopped(this));
			}
			if(state == StreamState.Running) { 
				StreamSessionWorkerStopReq req = new StreamSessionWorkerStopReq();
				req.setWorkerId(input.getWorkerId());
				DunkNetServiceRequest sReq = null;
				try {
				    logger.info(stopMarker, "Sending stop message to node {} worker {}",input.getNode().getId(), input.getWorkerId());
					sReq = channel.service(req);
				} catch (Exception e) {
					logger.error(stopMarker, "Exception sending stop message to node {} worker {}",input.getNode().getId(),input.getWorkerId());
					setState(StreamState.StopException);
					stopException = "Stop Request Service Failed " + e.toString();
					stopping = false; 
					stoppingTimer.stop();
					logger.error(marker, "Stream {} Worker {} Node {} Stop Exception Service Request Timeout",
							input.getStream().getName(),input.getWorkerId(),input.getNode().getId());
					eventNode.event(new EStreamSessionNodeStopException(StreamSessionNodeImpl.this,stopException));
					return;
				}

				sReq.addListener(new DunkNetServiceRequestListener() {
					
					@Override
					public void onServiceReqTimeout(DunkNetServiceRequest req) {
						setState(StreamState.StopException);
						stopException = "Stop Request Timeout";
						stopping = false; 
						stoppingTimer.stop();
						logger.error(stopMarker, "Stream {} Worker {} Node {} Stop Exception Service Request Timeout",
								input.getStream().getName(),input.getWorkerId(),input.getNode().getId());
						eventNode.event(new EStreamSessionNodeStopException(StreamSessionNodeImpl.this,stopException));
						return;
					
					}
					
					@Override
					public void onServiceReqError(DunkNetServiceRequest req) {
						setState(StreamState.StopException);
						stopException = "Stop Service Error Response + " + req.getError();
						stopping = false; 
						stoppingTimer.stop();
						logger.error(stopMarker, "Stream {} Worker {} Node {} Stop Exception Service Response Error {}",
								input.getStream().getName(),input.getWorkerId(),input.getNode().getId(),stopException);
						eventNode.event(new EStreamSessionNodeStopException(StreamSessionNodeImpl.this,stopException));
						return;
					}
					
					@Override
					public void onServiceReqDone(DunkNetServiceRequest req) {
						setState(StreamState.Stopped);
						stopping = false; 
						stoppingTimer.stop();
						logger.info(stopMarker, "Stream {} Worker {} Stopped On Node {} Duration {}",input.getStream().getName(),
								input.getWorkerId(),input.getNode().getId(),stoppingTimer.getCompletedSeconds());
						eventNode.event(new EStreamSessionNodeStopped(StreamSessionNodeImpl.this));
						
						
					}
				});
			}

		

	}
	
	

	@Override
	public StreamController getStream() {
		return input.getStream();
	}

	@Override
	public boolean hasTicker(String symbol) {
		if (tickerMap.containsKey(symbol.toUpperCase())) {
			return true;
		}
		return false;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public StreamSessionNodeInput getInput() {
		return input;
	}

	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		channel.addExtension(this);
	}

	@Override
	public void channelStart() throws DunkNetException {
		DunkNetServiceRequest sReq = null;
		try {
			sReq = channel.service(startReq);			
		} catch (Exception e) {
			startException = "exception invoking start stream service " + e.toString();
			starting = false; 
			startingTimer.stop();
			startException = "channel service request failed " + e.toString();
			eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this, "Exception getting service request " + e.toString()));
			return;
		}
		sReq.addListener(new DunkNetServiceRequestListener() {
			
			@Override
			public void onServiceReqTimeout(DunkNetServiceRequest req) {
				setState(StreamState.StartException);
				startException = "Start Request Timeout";
				starting = false; 
				startingTimer.stop();
				eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this,startException));
				return;
			}
			
			@Override
			public void onServiceReqError(DunkNetServiceRequest req) {
				startException = "start service response error " + req.getError();
				starting = false; 
				startingTimer.stop();
				setState(StreamState.StartException);
				eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this, startException));
				return;
			}
			
			@Override
			public void onServiceReqDone(DunkNetServiceRequest req) {
				try {
					StreamSessionWorkerStartResp resp = (StreamSessionWorkerStartResp)req.getResponse();
					startingTimer.stop();
					starting = false;
					if(resp.getCode().equals("ERROR")) { 
						startException = resp.getError();
						setState(StreamState.StartException);
						startException = "Start Request service returned error " + resp.getError();
						eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this, startException));
						return;
					}
					setState(StreamState.Running);
					eventNode.event(new EStreamSessionNodeStarted(StreamSessionNodeImpl.this));
				} catch (Exception e) {
					startingTimer.stop();
					starting = false; 
					setState(StreamState.StartException);
					startException = "get responsnse on net service " + e.toString();
					eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this, "Internal shit could not get service req resp " + e.toString()));
				}

			}
		});

	}

	@Override
	public void channelClose() {
		logger.info(startException);

	}
	

	@Override
	public void cancel() {
		setState(StreamState.RunKill);
		try {
			//if(channel.isOpen() == false) { 
			//	return;
			//}
			//if(!isRunning()) { 
			////	return;
			//}
			StreamSessionWorkerCancelReq req = new StreamSessionWorkerCancelReq();
			req.setWorkerId(getWorkerId());
			channel.serviceBlocking(req);
			eventNode.event(new EStreamSessionNodeKilled(this));
			channel.closeChannel();
		} catch (Exception e) {
			logger.error(marker, "Exception cancelling session node " + e.toString());
		}
	}

	@Override
	public void channelStartError(String exception) {
		logger.error(marker, "Stream Node {} channel start exception {}", input.getNode().getId(), exception);
		startException = exception;
		setState(StreamState.StartException);
		starting = false;
		startingTimer.stop();
		eventNode.event(new EStreamSessionNodeStartExcepton(this, "channel stat error " + exception));

	}

	private void notifyExtensions(int type) {
		switch (type) {
		case StreamSessionExtension.NODE_STARTING:
			for (StreamSessionExtension ext : input.getSession().getExtensions()) {
				ext.nodeStarting(this);
			}
			return;
		case StreamSessionExtension.NODE_STARTED:
			for (StreamSessionExtension ext : input.getSession().getExtensions()) {
				ext.nodeStarted(this);
			}

			return;
		case StreamSessionExtension.SESSION_NODE_START_EXCEPTION:
			for (StreamSessionExtension ext : input.getSession().getExtensions()) {
				ext.nodeStartException(this, startException);
			}

		}
	}

	private XStreamBundle createBundle() throws Exception {
		XStreamBundle xstreamBundle = null;
		xstreamBundle = new XStreamBundle();
		xstreamBundle.setDate(DDate.now());
		xstreamBundle.setTimeZone(DTimeZone.NewYork);
		try {
			xstreamBundle.setScriptBundle(input.getSession().getStream().getScriptBundle());
		} catch (Exception e) {
			logger.error(marker,
					"Stream Session Node Start Exception setting script bundle on stream bundle " + e.toString(), e);
			startException = "Setting XScriptBundle that is encoded into bytes on steram bundle exception "
					+ e.toString();
			throw new Exception("Exceptoin setting XScriptBundle int byotes " + e.toString());
		}

		return xstreamBundle;
	}
	
	private void setState(StreamState state) { 
		this.state = state;
		bean.setStatus(state.name());
		bean.notifyUpdate();
	}

}

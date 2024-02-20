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
import com.dunkware.spring.cluster.DunkNetTimeoutException;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequestListener;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.json.controller.spec.StreamState;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerEntitiesReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCancelReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCreateReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopResp;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNodeStopState;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeKilled;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStartExcepton;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopped;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionNodeImpl implements StreamSessionNode, DunkNetChannelHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
    private Marker stopMarker = MarkerFactory.getMarker("StreamStop");
	private Marker stopTrace = MarkerFactory.getMarker("StreamStopTrace");

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

	private StreamSessionWorkerStartReq startReq = new StreamSessionWorkerStartReq();
	
	private StreamSessionNodeBean bean;
	
	private StreamSessionWorkerStats workerStats = null;
	
	private boolean startingInitiazed = false; 
	private boolean stoppingInitiazed = false; 
	
	private StreamSessionNodeStopState stopState = StreamSessionNodeStopState.StopPending;
	
	@Value("${dunknet.brokers}")
	private String kafkaBrokers; 
	
	private List<Integer> sessionEntities = new ArrayList<Integer>();
	

	@Override
	public void start(StreamSessionNodeInput input) {

		this.input = input;
		this.bean = input.getNodebean();
		this.dunkNode = input.getNode();
		bean.setWorkerId(input.getWorkerId());
		bean.setNodeId(input.getNode().getId());
		bean.setId(input.getNumericId());
		bean.setStatus(state.name());
		bean.setSignalCount(0);
		bean.setStopState(stopState.name());
		bean.notifyUpdate();
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
				startReq.setWorkerId(input.getWorkerId());
				startReq.setNumericId(input.getNumericId());
				startReq.setStreamId((int)input.getStream().getEntity().getId());
				startReq.setStream(input.getSession().getStream().getName());
				startReq.setSessionId(input.getSession().getSessionId());
				startReq.setStreamBundle(streamBundle);
				startReq.setNodeId(input.getNode().getId());
				startReq.setKafkaBrokers(kafkaBrokers);
				startReq.setSignals(input.getSession().getInput().getSignalTypes());
				startReq.setStreamDescriptor(input.getSession().getStream().getDescriptor());
				for (String key : input.getSession().getInput().getProperties().keySet()) {
					startReq.getStreamProperties().put(key, input.getSession().getInput().getProperties().get(key));
				}
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
	public StreamSessionWorkerStartReq getStartReq() {
		return startReq;
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
	public void sessionEntity(StreamSessionWorkerEntitiesReq entity) { 
		this.sessionEntities.add(entity.getEntity());
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
		bean.setTickCount((int)stats.getTickCount());
		bean.setEntitySnapshotCount(stats.getEntitySnapshotCount());
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
	public boolean isStopException() {
		if(stopState == StreamSessionNodeStopState.StopInvokeException || stopState == StreamSessionNodeStopState.StopInvokeTimeout) { 
			return true;
		}
		return false;
	}

	@Override
	public void stop() {
			Thread stopper = new Thread() { 
				
				public void run() { 
					if(stopState != StreamSessionNodeStopState.StopPending) { 
						logger.error(stopTrace, "stop() invoked on worker node {} with stop state {}, not doing anything",input.getWorkerId(), stopState.name());
					
						// not sure here; 
						return;
					}
					setState(StreamState.Stopping);
					stopping = true;
					stoppingTimer = DStopWatch.create();
					stoppingTimer.start();
					stoppingInitiazed = true;
					
					
					StreamSessionWorkerStopReq req = new StreamSessionWorkerStopReq();
					req.setWorkerId(input.getWorkerId());
					try {
						setStopState(StreamSessionNodeStopState.StopInvoked);
						logger.info(stopTrace, "Invoking stop request on worker {} ", input.getWorkerId());
						Object response = channel.serviceBlocking(req);						
						logger.info(stopTrace, "Invoked stop requested on worker {}",input.getWorkerId());
						try {
							StreamSessionWorkerStopResp resp = (StreamSessionWorkerStopResp)response;
							setState(StreamState.Stopped);
							if(resp.getStopProblems().size() > 0) { 
								setStopState(StreamSessionNodeStopState.StopCompleteRProblems);
								bean.setStopProblems(resp.getStopProblems());;
								bean.notifyUpdate();
								logger.info(stopTrace, "Stop complete problems worker {} but sending stopped event ", input.getWorkerId());
								eventNode.event(new EStreamSessionNodeStopped(StreamSessionNodeImpl.this));
							} else { 
								setStopState(StreamSessionNodeStopState.StopComplete);
								logger.info(stopTrace, "Stop completed invoking stopped event worker {}", input.getWorkerId());
								eventNode.event(new EStreamSessionNodeStopped(StreamSessionNodeImpl.this));
							}
							
						} catch (Exception e) {
							logger.error(stopTrace, "Exception casting response object on stop request on worker {} exception is {}",input.getWorkerId(),e.toString());;
							setStopState(StreamSessionNodeStopState.StopInvokeException);
							setState(StreamState.StopException);
							setStopException("stop response cast exception " + e.toString());
							logger.error(stopTrace, "Stop node exception worker {} exception {}",input.getWorkerId(),e.toString());;
							eventNode.event(new EStreamSessionNodeStopException(StreamSessionNodeImpl.this,stopException));
							return;
						}
						
					} catch (DunkNetException e) {
						if(DunkNetTimeoutException.class.isInstance(e)) {
							setStopState(StreamSessionNodeStopState.StopInvokeTimeout);
							setStopException("Stop invoke timeout");
							logger.error(stopTrace, "Stop rquest timeout on worker " + input.getWorkerId());
							eventNode.event(new EStreamSessionNodeStopException(StreamSessionNodeImpl.this,stopException));
						} else { 
							setStopState(StreamSessionNodeStopState.StopInvokeException);
							setState(StreamState.StopException);
							setStopException("stop request exception " + e.toString());
							logger.error(stopTrace, "Stop Request invoke exception on worker {} exception {}",input.getWorkerId(),e.toString());;
							eventNode.event(new EStreamSessionNodeStopException(StreamSessionNodeImpl.this,stopException));
							}
						}
					}

					
			};
			
			
			stopper.start();
			
		

	}
	
	
	

	@Override
	public List<Integer> getSessionEntities() {
		return sessionEntities;
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
		xstreamBundle.setDate(DDate.now(DTimeZone.toZoneId(input.getStream().getTimeZone())));
		xstreamBundle.setTimeZone(input.getStream().getTimeZone());
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
	
	public void setStopException(String exception) { 
		this.stopException = exception;
		bean.setStopError(exception);
		bean.notifyUpdate();
	}
	
	private void setState(StreamState state) { 
		this.state = state;
		bean.setStatus(state.name());
		bean.notifyUpdate();
	}
	
	public void setStopState(StreamSessionNodeStopState state) { 
		this.stopState = state;
		this.bean.setStopState(state.name());
		this.bean.notifyUpdate();
	}

	@Override
	public StreamSessionNodeStopState getStopState() {
		return stopState;
	}

}

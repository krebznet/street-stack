package com.dunkware.trade.service.stream.server.controller.session.core;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStartException;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStarted;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStopped;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStoppedException;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStartExcepton;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStarted;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopException;
import com.dunkware.trade.service.stream.server.controller.session.events.EStreamSessionNodeStopped;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionNodeImpl implements StreamSessionNode, DunkNetChannelHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private XStreamBundle xstreamBundle;

	private StreamSessionNodeInput input;

	private ConcurrentHashMap<String, String> tickerMap = new ConcurrentHashMap<String, String>();

	private StreamSessionNodeState state = StreamSessionNodeState.Starting;

	private String startException = null;

	private String workerId;

	private DEventNode eventNode;

	private StreamSessionWorkerStats workerStats = null;

	private AtomicBoolean stopped = new AtomicBoolean(false);

	private Marker marker = MarkerFactory.getMarker("StreamSessionNode");
	
	private DunkNetNode dunkNode;
	
	private DunkNetChannel channel;

	@Override
	public void startNode(StreamSessionNodeInput input) {

		this.input = input;
		this.dunkNode = input.getNode();
		eventNode = input.getSession().getEventNode().createChild(this);

		for (TradeTickerSpec tickerSpec : input.getTickers()) {
			tickerMap.put(tickerSpec.getSymbol().toUpperCase(), tickerSpec.getName());
		}

		Thread starter = new Thread() {

			public void run() {
				
				setName("SessionNodeStarter_" + input.getNode().getId());
				xstreamBundle = new XStreamBundle();
				xstreamBundle.setDate(DDate.now());
				xstreamBundle.setTimeZone(DTimeZone.NewYork);
				try {
					xstreamBundle.setScriptBundle(input.getSession().getStream().getScriptBundle());
				} catch (Exception e) {
					logger.error("Stream Session Node Start Exception setting script bundle on stream bundle "
							+ e.toString(), e);
					startException = "Setting XScriptBundle that is encoded into bytes on steram bundle exception "
							+ e.toString();
					input.getCallBack().nodeStartException(StreamSessionNodeImpl.this);
					return;
				}

				for (StreamSessionExtension ext : input.getSession().getExtensions()) {
					ext.nodeStarting(StreamSessionNodeImpl.this);

				}
				StreamSessionWorkerStartReq req = new StreamSessionWorkerStartReq();
				workerId = input.getStream().getName() + "_session_worker_" + input.getNode().getId();
				workerStats = new StreamSessionWorkerStats();
				workerStats.setNodeId(workerId);
				// yes put the stats
				
				req.setWorkerId(input.getId());
				req.setStream(input.getSession().getStream().getName());
				req.setSessionId(input.getSession().getSessionId());
				req.setStreamBundle(xstreamBundle);
				// okay here we need to insert the
				
				try {
					DunkNetChannelRequest request =  dunkNode.channel(req);
					channel.setHandler(StreamSessionNodeImpl.this);
					channel = request.get(30, TimeUnit.SECONDS);
					
					
				} catch (Exception e) {
					logger.error(marker, "Exception creating session node chanel " + e.toString());
					state = StreamSessionNodeState.StartException;
					startException = "Exception creating node channel " + e.toString();
					eventNode.event(new EStreamSessionNodeStartExcepton(StreamSessionNodeImpl.this,"Exception creating channel " + e.toString()));
					return;
				}


			}

		};

		starter.start();

	}
	

	

	@Override
	public StreamSessionWorkerStats getWorkerStats() {
		return workerStats;
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
	public void workerStarted(WorkerStarted started) { 
		eventNode.event(new EStreamSessionNodeStarted(this));
	}
	
	@ADunkNetEvent
	public void workerStartException(WorkerStartException exp) { 
		eventNode.event(new EStreamSessionNodeStartExcepton(this, exp.getException()));
	}

	@ADunkNetEvent
	public void workerStopped(WorkerStopped stopped) { 
		eventNode.event(new EStreamSessionNodeStopped(this));
		channel.closeChannel();
	}
	
	@ADunkNetEvent
	public void workerStoppedException(WorkerStoppedException exp) { 
		eventNode.event(new EStreamSessionNodeStopException(this, exp.getException()));
		channel.closeChannel();
	}
	
	@ADunkNetEvent
	public void workerStats(StreamSessionWorkerStats stats) { 
		this.workerStats = stats;
	}
	
	@Override
	public StreamSessionNodeStatus getStatus() {
		StreamSessionNodeStatus status = new StreamSessionNodeStatus();
		status.setStream(input.getStream().getName());
		status.setNodeId(input.getNode().getId());
		if (workerStats != null) {
			status.setLastDataTickTime(workerStats.getLastDataTickTime());
			status.setPendingTaskCount(workerStats.getPendingTaskCount());
			status.setRowCount(workerStats.getRowCount());
			status.setSignalCount(workerStats.getSignalCount());
			status.setStreamTime(workerStats.getStreamTime());
			status.setSystemTime(workerStats.getSystemTime());
			status.setStream(input.getStream().getName());
			status.setTickCount(input.getTickers().size());
			status.setTimeoutTaskCount(workerStats.getTimeoutTaskCount());
		}
		return status;
	}

	@Override
	public StreamSessionNodeState getState() {
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
	public String getStartError() {
		return startException;
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
	public void stopNode() {
		logger.info(marker, "Stopping node " + workerId);
	
	}

	@Override
	public XStreamBundle getStreamBundle() {
		return xstreamBundle;
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
		// plau gppd
	}

	@Override
	public void channelClose() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void channelStartError(String exception) {
		eventNode.event(new EStreamSessionNodeStartExcepton(this, "channel stat error " + exception));
		
	}
	
	
	

	

	
	

}

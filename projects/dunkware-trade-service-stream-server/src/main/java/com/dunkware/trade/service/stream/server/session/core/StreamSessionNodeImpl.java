package com.dunkware.trade.service.stream.server.session.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.json.bytes.DBytes;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.anot.AClusterPojoEventHandler;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.StreamSessionNodeInput;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionNodeImpl implements StreamSessionNode {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private XStreamBundle xstreamBundle;
	
	private StreamSessionNodeInput input; 
	
	private StreamSessionNodeState state = StreamSessionNodeState.Starting;
	
	private String startException = null;
	
	private String workerId; 
	
	private DEventNode eventNode;
	
	@Autowired
	private Cluster cluster; 
	
	private StreamSessionWorkerStats workerStats = null;
	
	
	@Override
	public void startNode(StreamSessionNodeInput input)   {
		cluster.addComponent(this);
		this.input = input;
		eventNode = input.getSession().getEventNode().createChild("/node/" + input.getClusterNode().getId());
		
		
		input.getSession().getStream().getSpec().getBundle();
		Thread starter = new Thread() { 
			
			public void start() { 
				xstreamBundle = new XStreamBundle();
				xstreamBundle.setDate(DDate.now());
				xstreamBundle.setTimeZone(DTimeZone.NewYork);
				try {
					xstreamBundle.setScriptBundle(input.getSession().getStream().getScriptBundle());	
				} catch (Exception e) {
					logger.error("Stream Session Node Start Exception setting script bundle on stream bundle " + e.toString(),e);
					startException = "Setting XScriptBundle that is encoded into bytes on steram bundle exception " + e.toString();
					input.getCallBack().nodeStartException(StreamSessionNodeImpl.this);
					return;
				}
				
				for (StreamSessionExtension ext : input.getSession().getExtensions()) {
						ext.nodeStarting(StreamSessionNodeImpl.this);
					
				}
				StreamSessionWorkerStartReq req = new StreamSessionWorkerStartReq();
				workerId = input.getStream().getName() + "_session_worker_" + input.getClusterNode().getId();
				req.setWorkerId(workerId);
				req.setStream(input.getSession().getStream().getName());
				req.setSessionId(input.getSession().getSessionId());
				req.setStreamBundle(xstreamBundle);
				
				
				StreamSessionWorkerStartResp resp = null;
				try {
					String serialized = DJson.serialize(req);
					
					try {
						StreamSessionWorkerStartReq reqParsed = DJson.getObjectMapper().readValue(serialized, StreamSessionWorkerStartReq.class);
						logger.error("Parsed serialized fine");
					} catch (Exception e) {
						logger.error("session node can't deserialize its own fucking request " + e.toString(),e);
						// TODO: handle exception
					}
					DBytes dbytes = new DBytes(DJson.serialize(req).getBytes());
					
					
					resp = (StreamSessionWorkerStartResp)input.getClusterNode().jsonPost("/stream/worker/start", dbytes, StreamSessionWorkerStartResp.class);
				} catch (Exception e) {
					state = StreamSessionNodeState.StartException;
					startException = "Exception invoking worker api " + e.toString();
					input.getCallBack().nodeStartException(StreamSessionNodeImpl.this);
					return;
				}
				if(resp.getCode().equalsIgnoreCase("error")) {
					startException = resp.getError();
					startException = "Worker error return on node " + getNodeId() + " " + resp.getError();
					input.getCallBack().nodeStartException(StreamSessionNodeImpl.this);
					state = StreamSessionNodeState.StartException;
				}
				state = StreamSessionNodeState.Running;
				input.getCallBack().nodeStarted(StreamSessionNodeImpl.this);
			}
			
		};
		
		starter.start();
		
	
	}
	
	@Override
	public StreamSessionNodeStatus getStatus() {
		StreamSessionNodeStatus status = new StreamSessionNodeStatus();
		status.setStream(input.getStream().getName());
		status.setNodeId(input.getClusterNode().getId());
		if(workerStats != null) { 
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
	public StreamSessionWorkerStats getWorkerStats() {
		StreamSessionWorkerStats stats = new StreamSessionWorkerStats();
		return stats;
	}

	@Override
	public ClusterNode getNode() {
		return input.getClusterNode();
	}

	@Override
	public String getNodeId() {
		return input.getClusterNode().getId();
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
	
	@AClusterPojoEventHandler(pojoClass = StreamSessionWorkerStats.class)
	public void streamWorkerStatus(StreamSessionWorkerStats workerStats) { 
		if(workerStats.getNodeId().equals(input.getClusterNode().getId())) {
			this.workerStats = workerStats;
		}
	}

	@Override
	public void stopNode() {
		Thread stopper = new Thread() { 
			
			public void run() { 
				if(state == StreamSessionNodeState.Running) { 
					// interrupt/stop the worker monitor
					// nodeStopping event fire
					for (StreamSessionExtension ext : input.getSession().getExtensions()) {
						ext.nodeStopping(StreamSessionNodeImpl.this);
					}
					// send stop request to worker node 
					StreamSessionWorkerStopReq req = new StreamSessionWorkerStopReq();
					req.setWorkerId(workerId);
					try {
						String resp = input.getClusterNode().httpGet("/stream/worker/stop?id=" + workerId);
					
						if(resp.equals("OK") == false) { 
							logger.error("ERROR Code Stopping Session Worker Node " + workerId + " Exception " + resp);
						}
					} catch (Exception e) {
						
						logger.error("Exception Invoking /stream/worker/stop on " +  input.getClusterNode().getId() + " exception " + e.toString());
					}
					state = StreamSessionNodeState.Stopped;
				}
				input.getCallBack().nodeStopped(StreamSessionNodeImpl.this);
			}
			
		};
		
		stopper.start();
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

	

}

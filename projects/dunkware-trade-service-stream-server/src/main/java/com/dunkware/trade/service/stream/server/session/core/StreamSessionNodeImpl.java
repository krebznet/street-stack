package com.dunkware.trade.service.stream.server.session.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
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
	@Override
	public void startNode(StreamSessionNodeInput input) throws Exception {
		this.input = input;
		eventNode = input.getSession().getEventNode().createChild("/node/" + input.getClusterNode().getId());
		xstreamBundle = new XStreamBundle();
		xstreamBundle.setDate(DDate.now());
		xstreamBundle.setTimeZone(DTimeZone.NewYork);
		xstreamBundle.setScriptBundle(input.getSession().getStream().getSpec().getBundle());
		
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
			resp = (StreamSessionWorkerStartResp)input.getClusterNode().jsonPost("/stream/worker/start", req, StreamSessionWorkerStartResp.class);
		} catch (Exception e) {
			state = state.StartException;
			// TODO: handle exception
		}
		if(resp.getCode().equalsIgnoreCase("error")) {
			// exception
		}
		
		state = StreamSessionNodeState.Running;
	
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
	public void stopNode() {
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
		// stay in exception status after stopped node is called?
		// no put it to stop
	
		
		//StreamSessionImpl impl = (StreamSessionImpl)getSession();
		//impl.nodeStopCallback(StreamSessionNodeImpl.this);
		// callback ? 
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

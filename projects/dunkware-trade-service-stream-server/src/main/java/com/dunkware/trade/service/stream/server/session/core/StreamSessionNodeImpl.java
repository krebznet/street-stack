package com.dunkware.trade.service.stream.server.session.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStats;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatsSpec;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStatsResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.session.StreamSessionNodeInput;
import com.dunkware.trade.service.stream.server.session.worker.protocol.spec.StreamSessionWorkerStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionNodeImpl implements StreamSessionNode {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ClusterNode node;
	private DDateTime startingTime;
	private DDateTime startedTime;
	private int startupTime;
	private StreamSession session;
	private StreamSessionNodeStats stats = new StreamSessionNodeStats();
	
	private WorkerMonitor workerMonitor = null;

	private DEventNode eventNode;
	
	private StreamSessionNodeInput input;


	public StreamSessionNodeImpl() { 
		
	}
	@Override
	public void startNode(StreamSessionNodeInput input) {
		// set session, node, tickers
		this.input = input;
		
		session.getEventNode().createChild("/node/" + input.getClusterNode().getId());
		// set starting time
		stats.setStartedTime(DDateTime.now(input.getStream().getTimeZone()));
		stats.setNode(input.getClusterNode().getId());
		// initialize stats
		//this.stats.setStatus(StreamSessionNodeStatus.Starting);
		//this.stats.setState(StreamSessionNodeState.Green);
		//this.stats.setWorkerId(workerId);
		//this.stats.setTickerCount(tickers.size());
		Launcher launcher = new Launcher();
		launcher.start();
	}


	@Override
	public String getNodeId() {
		return node.getId();
	}

	@Override
	public List<TradeTickerSpec> getTickers() {
		return input.getTickers();
	}

	


	@Override
	public void stopNode() {
		Stopper stopper = new Stopper();
		stopper.start();
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}
	
	
	@Override
	public StreamSessionNodeStatus getStatus() {
		return this.stat
	}
	@Override
	public StreamSessionWorkerStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ClusterNode getNode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StreamSessionNodeInput getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	class NodeStarter extends Thread {
		

		public void run() {

			// invoke extensions
			for (StreamSessionExtension ext : session.getExtensions()) {
				ext.nodeStarting(StreamSessionNodeImpl.this);
			}
			StreamSessionWorkerStartReq req = new StreamSessionWorkerStartReq();
			req.setWorkerId(stats.getNode());
			req.setStream(session.getStream().getName());
			req.setSessionId(session.getSessionId());
			//req.setStreamBundle(input.getStream().getSpec().getb);
			StreamSessionWorkerStartResp resp = null;
			
			try {
				resp = (StreamSessionWorkerStartResp)node.jsonPost("/stream/worker/start", req, StreamSessionWorkerStartResp.class);
			} catch (Exception e) {
				//stats.setStatus(StreamSessionNodeStatus.Exception);
				//stats.setException("Start Worker Exception " + e.toString());
				StreamSessionImpl sessionImpl = (StreamSessionImpl) getSession();
				sessionImpl.nodeStartCallback(StreamSessionNodeImpl.this);
				return;
			}
			
			// check response code for error 
			if(resp.getCode().equals("ERROR")) { 
				stats.setStatus(StreamSessionNodeStatus.Exception);
				stats.setException("Worker Start ERROR Code Exception " + resp.getError());
				StreamSessionImpl sessionImpl = (StreamSessionImpl) getSession();
				sessionImpl.nodeStartCallback(StreamSessionNodeImpl.this);
				return;
			}
			// okay we have started!
			stats.setStatus(StreamSessionNodeStatus.Running);
			stats.setStartTime(DTime.now());
			StreamSessionImpl sessionImpl = (StreamSessionImpl) getSession();
			sessionImpl.nodeStartCallback(StreamSessionNodeImpl.this);
			
			// start the worker monitor
			workerMonitor = new WorkerMonitor();
			workerMonitor.start();
		}
	};

	
	private class NodeStopper extends Thread { 
		
		public void run() { 
			// Right Do this in a different thread! 
			if(getStatus() == StreamSessionNodeStatus.Running) { 
				// interrupt/stop the worker monitor
				workerMonitor.interrupt();
				// nodeStopping event fire
				for (StreamSessionExtension ext : session.getExtensions()) {
					ext.nodeStopping(StreamSessionNodeImpl.this);
				}
				// send stop request to worker node 
				StreamSessionWorkerStopReq req = new StreamSessionWorkerStopReq();
				req.setWorkerId(node.getId());
				try {
					String resp = node.reqResp("/stream/worker/stop?id=" + getWorkerId());
					if(resp.equals("OK") == false) { 
						logger.error("ERROR Code Stopping Session Worker Node " + getWorkerId() + " Exception " + resp);
					}
				} catch (Exception e) {
					logger.error("Exception Invoking /stream/worker/stop on " +  node.getId() + " exception " + e.toString());
				}
				stats.setStatus(StreamSessionNodeStatus.Stopped);	
			}
			// stay in exception status after stopped node is called?
			// no put it to stop
			stats.setStatus(StreamSessionNodeStatus.Stopped);	
			StreamSessionImpl impl = (StreamSessionImpl)getSession();
			impl.nodeStopCallback(StreamSessionNodeImpl.this);
		}
	}
	
	
	private class WorkerMonitor extends Thread { 
		
		private boolean statsErrorCodeHandled = false; 
		private boolean statsErrorCallExceptionHandled = false; 
		
		public void run() { 
			try {
				setName("Stream Session Worker Monitor ID " + getWorkerId());
				while(!interrupted()) { 
					try {
						Thread.sleep(5000);
						StreamSessionWorkerStatsResp resp = null;
						try {
							resp = (StreamSessionWorkerStatsResp)node.reqResp("/stream/worker/stats?id=" + getWorkerId(), StreamSessionWorkerStatsResp.class);	
						} catch (Exception e) {
							if(!statsErrorCallExceptionHandled) { 
								stats.setState(StreamSessionNodeState.Red); 
								stats.setException("Worker Stats Call Exception " + e.toString());
								stats.addProblem("Exception Making Get Stats Call " + e.toString());
								statsErrorCallExceptionHandled = true; 
								// notify on red exception? 
							}
						}
						
						
						if(resp.getCode().equals("ERROR")) { 
							logger.error("Exception Code Returned From StreamWorker Get Worker Status Node Workder ID {} Exception {}",getWorkerId(),resp.getError());
							if(!statsErrorCodeHandled) { 
								if(stats.getState() != StreamSessionNodeState.Red) { 
									stats.setState(StreamSessionNodeState.Red);
									stats.addProblem("Cannot get worker stats, error returned  " + resp.getError());
									statsErrorCodeHandled = true; 
								}	
							}
							
							
						}
						StreamSessionWorkerStatsSpec ws = resp.getSpec();
						stats.setCompletedTaskCount(ws.getCompletedTaskCount());
						stats.setPendingTaskCount(ws.getPendingTaskCount());
						stats.setTimeoutTaskCount(ws.getTimeoutTaskCount());
						stats.setStreamTime(ws.getStreamTime());
						stats.setSystemTime(ws.getSystemTime());
						stats.setRowCount(ws.getRowCount());
						stats.setLastDataTickTime(ws.getLastDataTickTime());
						stats.setSignalCount(ws.getSignalCount());
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return; 
						}
						logger.error("Outer Exception Getting Stream Worker Stats " + e.toString());
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return; 
				}
				logger.error("Outer Exception Getting Stream Worker Stats " + e.toString());
			}
		}
	}
}

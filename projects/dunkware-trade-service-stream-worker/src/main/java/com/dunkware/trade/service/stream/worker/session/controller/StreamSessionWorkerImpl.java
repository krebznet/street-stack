package com.dunkware.trade.service.stream.worker.session.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.spring.channel.Channel;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamSignalService;
import com.dunkware.xstream.core.XStreamCore;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionWorkerImpl implements StreamSessionWorker {
	
	public static final String METRIC_PENDING_TASK_COUNT = "stream.us_equity.stats.node.pendingtasks"; 
	public static final String METRIC_ROW_COUNT = "stream.us_equity.stats.node.entities"; 
	

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Cluster cluster;
	

	private XStreamBundle bundle;
	private XStream stream;
	private String uuid;
	private String streamName;

	private XStreamSignalService signalService; 
	
	private StreamSessionWorkerStartReq startReq;
	
	

	
	
	private StatusPublisher statusPublisher;
	
	public StreamSessionWorkerImpl() {

	}

	
	@Override
	public XStream getXStream() {
		return stream;
	}
	
	

	
	public void startWorker() throws Exception {

		try {

			// this is where we should create a cluster job
			XScriptBundle script = bundle.getScriptBundle();
			
			XStreamInput streamInput = XStreamCore.createInput(bundle, cluster.getExecutor());
			streamInput.setIdentifier(startReq.getStream());
			streamInput.setSessionId(startReq.getSessionId());
			uuid = UUID.randomUUID().toString();
			stream = XStreamCore.createStream();
			if (logger.isDebugEnabled()) {
				logger.debug("Starting Stream Worker Identifier " + uuid);
			}
			stream.start(streamInput);
			
			signalService = stream.getService(XStreamSignalService.class);
			statusPublisher = new StatusPublisher();
			statusPublisher.start();
			
		} catch (Exception e) {
			throw e;
		}
	}


	

	@Override
	public void start(StreamSessionWorkerStartReq req) throws Exception {
		this.bundle = req.getStreamBundle();
		this.startReq = req;
	//	cluster.startJob(this, "StreamWorker", req.getStream() + cluster.getNodeId());
		
	}

	

	@Override
	public void stop() throws Exception {
		this.stream.dispose();
		//clusterJob.jobComplete();
		statusPublisher.interrupt();
		
	}
	
	
	

	@Override
	public Channel getChannel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// channel handlers 
	
	//@AMessageHandler()
	//public StreamStats getSessionStats() {
//		.bundle..stream.getService(Streamstat)
//		return null;
	//}


	@Override
	public StreamSessionWorkerStats getStats() {
		StreamSessionWorkerStats spec = new StreamSessionWorkerStats();
		DExecutor ex = stream.getExecutor().getDExecutor();
		spec.setPendingTaskCount(ex.getPendingTaskCount());
		spec.setCompletedTaskCount(ex.getCompletedTaskCount());
		spec.setTimeoutTaskCount(ex.getTimeoutTaskCount());
		spec.setRowCount(stream.getRowCount());
		spec.setSystemTime(DTime.now(stream.getInput().getTimeZone()));
		spec.setStreamTime(stream.getClock().getTime());
		spec.setTickCount(stream.getTickRouter().getTickCount());
		spec.setSignalCount(signalService.getSignalCount());
		spec.setLastDataTickTime(stream.getTickRouter().getLastDataTickTime());
		spec.setNodeId(cluster.getNodeId());
		return spec;
	}

	private class StatusPublisher extends Thread { 
		public void run() { 
			StreamSessionWorkerStats stats = getStats();
			try {
				//cluster.pojoEvent(stats);
			} catch (Exception e) {
				logger.error("Exceptin sending worker stream node status " + e.toString());
			}
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
	
	

	



}

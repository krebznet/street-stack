package com.dunkware.trade.service.data.worker.stream.session.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.net.cluster.node.ClusterJob;
import com.dunkware.net.cluster.node.ClusterJobRunner;
import com.dunkware.trade.service.data.worker.stream.session.StreamSessionWorker;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.core.XStreamCore;
import com.dunkware.xstream.core.services.XStreamSignalService;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionWorkerImpl implements StreamSessionWorker, ClusterJobRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Cluster cluster;

	private XStreamBundle bundle;
	private XStream stream;
	private String uuid;
	private String streamName;

	private XStreamSignalService signalService; 
	
	private StreamSessionWorkerStartReq startReq;
	
	
	private ClusterJob clusterJob; 
	
	public StreamSessionWorkerImpl() {

	}

	
	@Override
	public XStream getXStream() {
		return stream;
	}
	
	

	@Override
	public void startJob(ClusterJob job) throws Exception {

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
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void start(StreamSessionWorkerStartReq req) throws Exception {
		this.bundle = req.getStreamBundle();
		this.startReq = req;
		cluster.startJob(this, "StreamWorker", req.getStream() + cluster.getNodeId());
		
	}

	

	@Override
	public void stop() throws Exception {
		this.stream.dispose();
		clusterJob.jobComplete();
	}


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
		return spec;
	}

	

	

	



}

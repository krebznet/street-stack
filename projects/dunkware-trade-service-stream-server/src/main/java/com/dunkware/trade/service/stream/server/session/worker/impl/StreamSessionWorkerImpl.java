package com.dunkware.trade.service.stream.server.session.worker.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.service.stream.server.session.worker.StreamSessionWorker;
import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.server.session.worker.protocol.spec.StreamSessionWorkerStatsSpec;
import com.dunkware.trade.service.stream.server.spring.ExecutorService;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.core.XStreamCore;
import com.dunkware.xstream.core.services.XStreamSignalService;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionWorkerImpl implements StreamSessionWorker {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ExecutorService executor;

	private XStreamBundle bundle;
	private XStream stream;
	private String uuid;
	private String streamName;

	private XStreamSignalService signalService; 
	
	public StreamSessionWorkerImpl() {

	}

	
	@Override
	public XStream getXStream() {
		return stream;
	}

	@Override
	public void start(StreamSessionWorkerStartReq req) throws Exception {
		this.bundle = req.getStreamBundle();
		try {
			XScriptBundle script = bundle.getScriptBundle();
			
			XStreamInput streamInput = XStreamCore.createInput(bundle, executor.getExecutor());
			streamInput.setIdentifier(req.getStream());
			streamInput.setSessionId(req.getSessionId());
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
	public void stop() throws Exception {
		this.stream.dispose();
	}


	@Override
	public StreamSessionWorkerStatsSpec getStats() {
		StreamSessionWorkerStatsSpec spec = new StreamSessionWorkerStatsSpec();
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

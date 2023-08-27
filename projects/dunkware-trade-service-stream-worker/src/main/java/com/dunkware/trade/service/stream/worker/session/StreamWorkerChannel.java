package com.dunkware.trade.service.stream.worker.session;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCancelReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCancelResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCreateReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopResp;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamSignalService;
import com.dunkware.xstream.api.XStreamStatus;
import com.dunkware.xstream.core.XStreamCore;
import com.dunkware.xstream.core.XStreamImpl;

public class StreamWorkerChannel implements DunkNetChannelHandler {

	private StreamSessionWorkerStartReq req;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSessionWorker");

	private DunkNetChannel channel;

	private XStreamInput input = new XStreamInput();

	@Autowired
	private DunkNet dunkNet;

	@Autowired
	private ApplicationContext ac;

	private XStream stream;

	private StreamSessionWorkerStats stats = new StreamSessionWorkerStats();

	private StreamSessionWorkerStatus status = StreamSessionWorkerStatus.Pending;

	private Timer statTimer = null;

	//private XStreamSignalService signalService;

	@Autowired
	private ExecutorService executorService;

	public void init() {
		
	}

	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		this.channel.addExtension(this);
		
		
	}
	

	/**
	 * Here we will start the stream 
	 */
	@Override
	public void channelStart() throws DunkNetException {
		// don't really care 
	}
	
	@ADunkNetService(label = "Stop Session Worker Stream")
	public StreamSessionWorkerStopResp stopStream(StreamSessionWorkerStopReq req) {
		statTimer.cancel();
		StreamSessionWorkerStopResp resp = new StreamSessionWorkerStopResp();
		resp.setStoppingTime(DTime.now(input.getTimeZone()));
		try {
			stream.dispose();
			resp.setStopTime(DTime.now(input.getTimeZone()));
			resp.setCode("OK");
			logger.info(marker, "Stopped Stream {} Session Worker Node {}",input.getSessionId(),req.getWorkerId());
			return resp;
		} catch (Exception e) {
			resp.setStopTime(DTime.now(input.getTimeZone()));
			resp.setCode("ERROR");
			resp.setError(e.toString());
			logger.error(marker, "Exception stopping stream session node " + e.toString());
			return resp;
		}

	}
	
	@ADunkNetService(label = "Cancel Session Worker Stream")
	public StreamSessionWorkerCancelResp cancelStream(StreamSessionWorkerCancelReq req) { 
		if(stream.getStatus() == XStreamStatus.Running) { 
			stream.cancel();
		}
		setStatus(StreamSessionWorkerStatus.Cancelled);
		StreamSessionWorkerCancelResp resp = new StreamSessionWorkerCancelResp();
		resp.setStatus("OK");
		return resp;
	}

	@ADunkNetService(label = "Start Session Worker Stream")
	public StreamSessionWorkerStartResp startStream(StreamSessionWorkerStartReq req) { 
		this.req = req;
		stats.setNodeId(dunkNet.getId());
		stats.setStream(req.getStream());
		stats.setStatus(status.name());
		stats.setCompletedTaskCount(0);
		stats.setPendingTaskCount(0);
		stats.setWorkerId(req.getWorkerId());
		stats.setNumericId(req.getNumericId());
		setStatus(StreamSessionWorkerStatus.Sopped.Starting);
		StreamSessionWorkerStartResp resp = new StreamSessionWorkerStartResp();
		resp.setStartingTime(DTime.now(req.getStreamBundle().getTimeZone()));
		try {
			this.input = XStreamCore.createInput(req.getStreamBundle(), executorService.get());
		} catch (Exception e) {
			logger.error(marker, "Exception starting stream session worker {} " + req.getWorkerId(), e.toString());
			resp.setError(e.toString());
			resp.setCode("ERROR");
			resp.setStartTime(DTime.now(input.getTimeZone()));
			setStatus(StreamSessionWorkerStatus.StartException);
			return resp;
		}
		try {
			stream = new XStreamImpl();
			stream.start(input);
			postStart();
			logger.info(marker, "Started Stream {} Session Worker {} on Node {}",input.getSessionId(),req.getWorkerId(),req.getNodeId());
			setStatus(StreamSessionWorkerStatus.Running);
			resp.setCode("OK");
			resp.setStartTime(DTime.now(input.getTimeZone()));
			return resp;
		} catch (Exception e) {
			logger.error(marker, "Exception starting stream session worker {} " + req.getWorkerId(), e.toString());
			resp.setError(e.toString());
			resp.setCode("ERROR");
			resp.setStartTime(DTime.now(input.getTimeZone()));
			setStatus(StreamSessionWorkerStatus.StartException);
			return resp;
		}
	}
	


	@Override
	public void channelClose() {
		logger.info(marker, "Stopping Worker Node " + req.getNodeId());

	}

	public XStream getStream() {
		return stream;
	}
	
	public void setStatus(StreamSessionWorkerStatus status) {
		this.status = status;
		this.stats.setStatus(status.name());
	}

	private void updateStats() {
		try {
			DExecutor ex = executorService.get();
			stats.setPendingTaskCount(ex.getPendingTaskCount());
			stats.setCompletedTaskCount(ex.getCompletedTaskCount());
			stats.setTimeoutTaskCount(ex.getTimeoutTaskCount());
			stats.setRowCount(stream.getRowCount());
			stats.setSystemTime(DTime.now(stream.getInput().getTimeZone()));
			stats.setStreamTime(stream.getClock().getTime());
			stats.setTickCount(stream.getTickRouter().getTickCount());
	//		stats.setSignalCount(signalService.getSignalCount());
			stats.setLastDataTickTime(stream.getTickRouter().getLastDataTickTime());	
		} catch (Exception e) {
			logger.error(marker, "Exception update stats " + e.toString());
			
		}
		
	}
	
	private class StatPublisher extends TimerTask {

		public void run() {
			try {
				updateStats();
				channel.event(stats);
			} catch (Exception e) {
				if(e instanceof InterruptedException) {
					return;
				}
				logger.error(marker, "Exception sending stats " + e.toString());
			}
		}
	}

	@Override
	public void channelStartError(String exception) {
		logger.error(marker, "Channel Start Exception on worker " + exception);
	}
	
	
	private void postStart() { 
		statTimer = new Timer();
		statTimer.scheduleAtFixedRate(new StatPublisher(), 0, 1000);
	}
	
	
	private void preStop() { 
		statTimer.cancel();
	}
	

}

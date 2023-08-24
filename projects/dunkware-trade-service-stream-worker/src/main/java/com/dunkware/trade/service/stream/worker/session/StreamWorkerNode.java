package com.dunkware.trade.service.stream.worker.session;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.worker.event.WorkerException;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStartException;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStarted;
import com.dunkware.trade.service.stream.json.worker.event.WorkerStopped;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamSignalService;
import com.dunkware.xstream.core.XStreamCore;
import com.dunkware.xstream.xproject.model.XStreamBundle;



public class StreamWorkerNode implements DunkNetChannelHandler {

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

	private XStreamSignalService signalService;

	@Autowired
	private ExecutorService executorService;

	public void init(StreamSessionWorkerStartReq req) {
		this.req = req;
		stats.setNodeId(dunkNet.getId());
		stats.setStream(req.getStream());
		stats.setStatus(status.name());
		stats.setCompletedTaskCount(0);
		stats.setPendingTaskCount(0);
		stats.setWorkerId(req.getWorkerId());

	}

	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		this.channel.addExtension(this);
		// okay we have done initializing
	}

	@Override
	public void channelStart() {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				// where we start our stream
				statTimer = new Timer();
				statTimer.scheduleAtFixedRate(new StatPublisher(), 0, 1000);

				// this is where we should create a cluster job
				XStreamBundle bundle = req.getStreamBundle();
				try {
					XStreamInput streamInput = XStreamCore.createInput(bundle, executorService.get());
					streamInput.setIdentifier(req.getStream());
					streamInput.setSessionId(req.getSessionId());
					stream.start(streamInput);
					signalService = stream.getService(XStreamSignalService.class);
					channel.event(new WorkerStarted());
				} catch (Exception e) {
					WorkerStartException ex = new WorkerStartException();
					ex.setException(e.toString());
					try {
						channel.event(ex);						
					} catch (Exception e2) {
						logger.error(marker, "Exception sending start exception " + e2.toString());
					}
					status = StreamSessionWorkerStatus.StartException;
					logger.debug(marker, "Start Exception " + e.toString());
					stats.setStartException(e.toString());
					statTimer.cancel();
					return;
				}

			}
		};
		executorService.execute(runner);
	}

	@ADunkNetEvent
	public void stopStream(StreamSessionWorkerStopReq req) {
		Runnable runner = new Runnable() {

			public void run() {
				try {
					stream.dispose();
					channel.event(new WorkerStopped());
				} catch (Exception e) {
					WorkerException ex = new WorkerException();
					ex.setException(e.toString());
					try {
						channel.event(ex);
						channel.event(new WorkerStopped());						
					} catch (Exception e2) {
						logger.error(marker, "Exception sending stop exception event " + e.toString());
					}
					logger.error(marker, "Exception disposing worker stream " + e.toString());
					statTimer.cancel();
				}
			}
		};
		executorService.execute(runner);

	}

	@Override
	public void channelClose() {
		logger.info(marker, "Stopping Worker Node " + req.getNodeId());

	}
	
	public XStream getStream() { 
		return stream;
	}

	private void updateStats() {
		DExecutor ex = stream.getExecutor().getDExecutor();
		stats.setPendingTaskCount(ex.getPendingTaskCount());
		stats.setCompletedTaskCount(ex.getCompletedTaskCount());
		stats.setTimeoutTaskCount(ex.getTimeoutTaskCount());
		stats.setRowCount(stream.getRowCount());
		stats.setSystemTime(DTime.now(stream.getInput().getTimeZone()));
		stats.setStreamTime(stream.getClock().getTime());
		stats.setTickCount(stream.getTickRouter().getTickCount());
		stats.setSignalCount(signalService.getSignalCount());
		stats.setLastDataTickTime(stream.getTickRouter().getLastDataTickTime());
	}

	private class StatPublisher extends TimerTask {

		public void run() {
			try {
				updateStats();
				channel.event(stats);
			} catch (Exception e) {
				logger.error(marker, "Exception sending stats");
				// TODO: handle exception
			}
		}
	}

	@Override
	public void channelStartError(String exception) {
		// TODO Auto-generated method stub
		
	}

}

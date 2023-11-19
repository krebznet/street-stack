package com.dunkware.trade.service.stream.worker.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.DunkNetChannelHandler;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.trade.service.stream.json.worker.service.StreamEntitySnapshotReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCancelReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerCancelResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopResp;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStopState;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.query.StreamWorkerEntityQueryBuilder;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamStatus;
import com.dunkware.xstream.core.XStreamCore;
import com.dunkware.xstream.core.XStreamImpl;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;
import com.dunkware.xstream.model.entity.StreamEntitySnapshotVar;

public class StreamWorker implements DunkNetChannelHandler {

	private StreamSessionWorkerStartReq req;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSessionWorker");
	private Marker stopMarker = MarkerFactory.getMarker("StreamStop");
	private Marker stopTrace = MarkerFactory.getMarker("StreamStopTrace");

	private DunkNetChannel channel;

	private XStreamInput input = new XStreamInput();

	//private boolean stopInvoked = false;
	private volatile boolean stopComplete = false;

	@Autowired
	private DunkNet dunkNet;

	@Autowired
	private ApplicationContext ac;

	private XStream stream;

	private StreamSessionWorkerStats stats = new StreamSessionWorkerStats();

	private StreamSessionWorkerStatus status = StreamSessionWorkerStatus.Pending;
	private StreamSessionWorkerStopState stopState = StreamSessionWorkerStopState.StopPending;

	private Timer statTimer = null;

	// private XStreamSignalService signalService;

	private List<StreamWorkerExtension> workerExtensions = new ArrayList<StreamWorkerExtension>();

	@Autowired
	private ExecutorService executorService;

	public void init() {

	}

	public StreamSessionWorkerStartReq getStartReq() {
		return req;
	}

	public StreamDescriptor getStreamDescriptor() {
		return req.getStreamDescriptor();
	}

	@Override
	public void channelInit(DunkNetChannel channel) throws DunkNetException {
		this.channel = channel;
		this.channel.addExtension(this);

	}

	public XStream getXStream() {
		return stream;
	}

	public DunkNet getDunkNet() {
		return dunkNet;
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
		StreamSessionWorkerStopResp resp = new StreamSessionWorkerStopResp();
		if (stopState != StreamSessionWorkerStopState.StopPending) {
			logger.error(stopTrace, "Stop request on worker " + req.getWorkerId() + " invoked with stop state = " + stopState.name());
			resp.setCode("ERROR");
			resp.setError("Stop invoked with stop state set to " + stopState.name());
			return resp;
		}
		stopState = StreamSessionWorkerStopState.StopRequested;
		logger.info(stopTrace, "Stop Request Invoked on worker " + req.getWorkerId());;
		new StopTimeoutMonitor().start();
		preStop();
		resp.setStoppingTime(DTime.now(input.getTimeZone()));
		for (StreamWorkerExtension ext : workerExtensions) {
			try {
				ext.stop();
			} catch (Exception e) {
				logger.error(stopTrace, "Exception stopping extension {} error {} worker {}",ext.getClass().getName(),e.toString(),req.getWorkerId());
				logger.error(marker, "Exception stopping wroker extension {} error {}", ext.getClass().getName(),
						e.toString());
				resp.getStopProblems().add("Exception stopping worker extension " + ext.getClass().getName() + " error " + e.toString() + " worker " + req.getWorkerId());
			}
		}
		try {
			statTimer.cancel();			
		} catch (Exception e2) {
			resp.getStopProblems().add("Exception cancelling stat timer publisher " + e2.toString());
		}

		try {
			stream.dispose();
		} catch (Exception e) {
			resp.getStopProblems().add("XStream dispose exception " + e.toString());
			logger.error(stopTrace, "Exception stopping stream session node {} and worker {} exception {}", e.toString(),
					dunkNet.getId(), req.getWorkerId());
		}
		
		resp.setStopTime(DTime.now(input.getTimeZone()));
		if(resp.getStopProblems().size() > 0) { 
			stopState = StreamSessionWorkerStopState.StopCompleteProblems;
			logger.info(stopTrace, "Worker stop complete problems on worker {}",req.getWorkerId());
		} else { 
			stopState = StreamSessionWorkerStopState.StopComplete;
			logger.info(stopTrace, "Worker stop complete perfect worker {}",req.getWorkerId());
		}
		resp.setCode("OK");
		stopComplete = true;
		return resp;
	}

	@ADunkNetService(label = "Cancel Session Worker Stream")
	public StreamSessionWorkerCancelResp cancelStream(StreamSessionWorkerCancelReq req) {
		
		if (stream.getStatus() == XStreamStatus.Running) {
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
		Set<Class<?>> classes = DAnotHelper.getClassesAnnotedWith(AStreamWorkerExtension.class);
		for (Class<?> class1 : classes) {
			try {
				StreamWorkerExtension ext = (StreamWorkerExtension) class1.newInstance();
				ext.init(this);
				workerExtensions.add(ext);
			} catch (Exception e) {
				logger.error(marker, "Exception init extension " + e.toString());

			}
		}
		stats.setNodeId(dunkNet.getId());
		stats.setStream(req.getStream());
		stats.setStatus(status.name());
		stats.setCompletedTaskCount(0);
		stats.setPendingTaskCount(0);
		stats.setWorkerId(req.getWorkerId());
		stats.setNumericId(req.getNumericId());
		setStatus(StreamSessionWorkerStatus.Starting);
		StreamSessionWorkerStartResp resp = new StreamSessionWorkerStartResp();
		resp.setStartingTime(DTime.now(req.getStreamBundle().getTimeZone()));
		try {
			StreamWorkerEntityQueryBuilder queryBuilder = new StreamWorkerEntityQueryBuilder();
			queryBuilder.init(this);
			this.input = XStreamCore.createInput(req.getStreamBundle(), executorService.get(), queryBuilder);
			this.input.setSignalTypes(req.getSignals());
			this.input.setIdentifier(req.getStream());
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
			logger.info(marker, "Started Stream {} Session Worker {} on Node {}", input.getSessionId(),
					req.getWorkerId(), req.getNodeId());
			setStatus(StreamSessionWorkerStatus.Running);
		} catch (Exception e) {
			logger.error(marker, "Exception starting stream session worker {} " + req.getWorkerId(), e.toString());
			resp.setError(e.toString());
			resp.setCode("ERROR");
			resp.setStartTime(DTime.now(input.getTimeZone()));
			setStatus(StreamSessionWorkerStatus.StartException);
			return resp;
		}
		for (StreamWorkerExtension ext : workerExtensions) {
			try {
				ext.start();
			} catch (Exception e) {
				logger.error(marker, "Exception starting worker extension {}  error {}", ext.getClass().getName(),
						e.toString());
				stream.cancel();
				resp.setCode("ERROR");
				resp.setError("Worker Extension " + ext.getClass().getName() + " exception " + e.toString());
				return resp;

			}

		}
		statTimer = new Timer();
		statTimer.scheduleAtFixedRate(new StatPublisher(), 0, 1000);

		resp.setCode("OK");
		
		return resp;
	}

	@Override
	public void channelClose() {
		logger.debug(stopTrace, "Stream worker channelClose() invoked on worker {}",req.getWorkerId());
		logger.info(stopMarker, "Channel Closed invoked on Worker {} Node {}", req.getWorkerId(), dunkNet.getId());

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
			stats.setSignalCount(stream.getSignals().getSignalCount());
			stats.setLastDataTickTime(stream.getTickRouter().getLastDataTickTime());
		} catch (Exception e) {
			logger.error(marker, "Exception update stats " + e.toString());

		}

	}

	@ADunkNetService(label = "Entity Snapshot Request")
	public StreamEntitySnapshot entitySnapshot(StreamEntitySnapshotReq req) throws Exception {
		if (this.getXStream() == null) {
			throw new Exception("XStream is null");
		}
		XStream stream = getXStream();
		XStreamEntity ent = null;
		try {
			ent = stream.getRow(req.getEntityId());
		} catch (XStreamRuntimeException e) {
			throw new Exception("Entity ID not found on worker stream " + req.getEntityId());
		}

		StreamEntitySnapshot snap = new StreamEntitySnapshot();
		snap.setDateTime(stream.getClock().getLocalDateTime());
		snap.setId(ent.getIdentifier());
		snap.setIdentifier(ent.getId());
		for (XStreamEntityVar var : ent.getVars()) {
			StreamEntitySnapshotVar snapVar = new StreamEntitySnapshotVar();
			snapVar.setId(var.getVarType().getCode());
			snapVar.setIdentifier(var.getVarType().getName());
			snapVar.setUpdateCount(var.getValueCount());
			if (var.getSize() > 0) {
				snapVar.setLastUpdate(var.getLastUpdate());
				;
				snapVar.setValue(var.getValue(0));
			}
			snap.getVars().put(snapVar.getId(), snapVar);

		}
		return snap;

	}

	private class StatPublisher extends TimerTask {

		public void run() {
			try {
				if (stopComplete) {
					logger.error(stopMarker, "Stop Invokved Stats Publisher still running worker {} node {}",
							req.getWorkerId(), dunkNet.getId());
					return;
				}
				updateStats();

				channel.event(stats);
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				StreamWorker.this.statTimer.cancel();
				logger.error(marker, "Exception sending stats " + e.toString());
			}
		}
	}

	@Override
	public void channelStartError(String exception) {
		logger.error(marker, "Channel Start Exception on worker " + exception);
	}

	private void preStop() {

	}

	private class StopTimeoutMonitor extends Thread {

		public void run() {
			int count = 0;
			while (true) {
				try {
					Thread.sleep(1000);
					if (stopComplete) {
						logger.info(stopMarker, "Stop Monitor Detected Stop Complete on node {} worker {}",
								dunkNet.getId(), req.getWorkerId());
						return;
					}
					count++;
					if (count > 20) {
						logger.error(stopTrace, "Stop Worker took more than 20 seconds worker {} ", req.getWorkerId());
						logger.error(stopMarker,
								"Stop Monitor detected 20 seconds without stop complete node {} worker {}",
								dunkNet.getId(), req.getWorkerId());
						return;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
	}

}

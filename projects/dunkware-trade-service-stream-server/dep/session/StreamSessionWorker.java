package com.dunkware.trade.service.stream.server.controller.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.protocol.controller.spec.StreamWorkerMetrics;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.worker.StreamWorkerException;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StartWorkerReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StartWorkerResp;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StopWorkerReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.StopWorkerResp;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerMetricsReq;
import com.dunkware.trade.service.stream.server.controller.session.worker.protocol.WorkerMetricsResp;
import com.dunkware.xstream.model.XStreamBundle;
import com.dunkwrae.trade.service.tick.client.stream.NetTradeStream;

public class StreamSessionWorker {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String endpoint;
	private String key;
	private String identifier;

	private StreamSessionWorkerStatus status = StreamSessionWorkerStatus.Pending;

	private NetTradeStream feedStream;

	private StreamController controller;

	public StreamSessionWorker(String endpoint, String key, StreamController controller) throws StreamWorkerException {
		this.endpoint = endpoint;
		this.key = key;
		this.controller = controller;
		// shold validate
		try {
			DHttpHelper.getURLContent(endpoint + "/worker/ping");
			status = StreamSessionWorkerStatus.Exception;
		} catch (Exception e) {
			throw new StreamWorkerException("Exception connecting to worker " + endpoint + " " + e.toString());
		}
	}

	public StreamWorkerMetrics streamMetrics(boolean includeRows, boolean includeVars, String rowIds) throws StreamWorkerException {
		if(status != StreamSessionWorkerStatus.Running) { 
			throw new StreamWorkerException("Stream worker is not running, cannot get stats");
		}
		WorkerMetricsReq req = new WorkerMetricsReq();
		req.setIdentifier(identifier);
		req.setRowIds(rowIds);
		req.setRows(includeRows);
		req.setVars(includeVars);

		try {
			String respString = DHttpHelper.postJson(endpoint + "/worker/metrics", req);
			WorkerMetricsResp resp = (WorkerMetricsResp) DJson.getObjectMapper().readValue(respString,
					WorkerMetricsResp.class);
			if (resp.getCode().equals("ERROR")) {
				throw new StreamWorkerException("Get Stream Stats for stream  " + controller.getEntity().getName()
						+ " Exception " + resp.getError());
			}
			StreamWorkerMetrics workerStats = resp.getStats();
			workerStats.setStream(controller.getName());
			workerStats.setEndpoint(endpoint);
			return workerStats;
		} catch (Exception e) {
			throw new StreamWorkerException("Exception startign working web serice call " + e.toString());
		}

	}

	public void startWorker(XStreamBundle bundle, String streamName, NetTradeStream feedStream)
			throws StreamWorkerException {
		if (logger.isDebugEnabled()) {
			logger.debug("Starting Worker " + endpoint);
		}
		StartWorkerReq req = new StartWorkerReq();
		this.feedStream = feedStream;
		req.setStreamBundle(bundle);
		req.setStreamName(streamName);
		
		try {
			if(logger.isDebugEnabled()) { 
				logger.debug("Starting Worker endpoint " + endpoint);
			}
			String respString = DHttpHelper.postJson(endpoint + "/worker/start", req);
			StartWorkerResp resp = (StartWorkerResp) DJson.getObjectMapper().readValue(respString,
					StartWorkerResp.class);
			if (resp.getCode().equals("ERROR")) {
				logger.error("Exception starting Stream Session Worker Error Returned " + resp.getError());
				throw new StreamWorkerException("Remote exception starting worker " + resp.getError());
			}
			this.identifier = resp.getIdentifier();
			status = StreamSessionWorkerStatus.Running;
		} catch (Exception e) {
			throw new StreamWorkerException("Exception startign working web serice call " + e.toString());
		}
	}

	public StreamSessionWorkerStatus getStatus() {
		return status;
	}

	public NetTradeStream getFeedStream() {
		return feedStream;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void stopWorker() throws StreamWorkerException {
		if (logger.isDebugEnabled()) {
			logger.debug("Stopping Worker " + endpoint);
		}
		StopWorkerReq req = new StopWorkerReq();
		req.setIdentifier(identifier);
		try {
			String respString = DHttpHelper.postJson(endpoint + "/worker/stop", req);
			StopWorkerResp resp = (StopWorkerResp) DJson.getObjectMapper().readValue(respString, StopWorkerResp.class);
			if (resp.getCode().equals("ERROR")) {
				throw new StreamWorkerException("Remote exception stopping worker " + resp.getError());
			}

			status = StreamSessionWorkerStatus.Stopped;
		} catch (Exception e) {
			throw new StreamWorkerException("Exception stopping working web serice call " + e.toString());
		}
	}
}

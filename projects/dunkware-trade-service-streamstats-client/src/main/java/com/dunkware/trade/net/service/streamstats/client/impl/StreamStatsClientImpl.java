package com.dunkware.trade.net.service.streamstats.client.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.stopwatch.DStopWatchPool;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.net.service.streamstats.client.StreamStatsClient;
import com.dunkware.trade.net.service.streamstats.client.StreamStatsClientException;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsRequest;
import com.dunkware.trade.net.service.streamstats.client.proto.StreamStatsResponse;

public class StreamStatsClientImpl implements StreamStatsClient, DKafkaByteHandler2 {

	private DKafkaByteConsumer2 kafkaConsumer;
	private DKafkaByteProducer kafkaProducer;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private StreamStatsClientBean bean;

	private BlockingQueue<StreamStatsResponse> responseQueue = new LinkedBlockingQueue<StreamStatsResponse>();

	private boolean disposed = true;

	private ConcurrentHashMap<String, ResponseFuture> callbacks = new ConcurrentHashMap<String, StreamStatsClientImpl.ResponseFuture>();

	private DStopWatchPool pool = DStopWatchPool.createPool(400);
	
	private ResponseController responseController;

	@Override
	public void load(StreamStatsClientBean bean) throws StreamStatsClientException {
		this.bean = bean;
		try {
			DKafkaByteConsumer2Spec spec = new DKafkaByteConsumer2Spec();
			spec.setBrokers(bean.getBrokers().split(","));
			spec.setConsumerGroup(bean.getGroupId());
			spec.setConsumerId(bean.getClientId());
			spec.setTopics(new String[] { bean.getResponseTopic() });
			spec.setOffsetType(OffsetType.Latest);
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.addStreamHandler(this);
			kafkaConsumer.start();
		} catch (Exception e) {
			throw new StreamStatsClientException(
					"Exception creating consumer kafka topic " + bean.getBrokers() + " " + e.toString());
		}
		try {
			kafkaProducer = DKafkaByteProducer.newInstance(bean.getBrokers(), bean.getResponseTopic(),
					bean.getClientId());

		} catch (Exception e) {
			kafkaConsumer.dispose();
			disposed = true;
			throw new StreamStatsClientException("Exception creating and connecting kafka consumer " + e.toString());
		}
		disposed = false;
	}

	@Override
	public Future<StreamStatsResponse> request(StreamStatsRequest req) throws StreamStatsClientException {
		try {
			req.setReqId(DUUID.randomUUID(10));
			String reqId = req.getReqId();
			ResponseFuture future = new ResponseFuture(req);
			callbacks.put(reqId, future);
			kafkaProducer.sendBytes(DJson.serialize(req).getBytes());
			return future;
		} catch (Exception e) {
			throw new StreamStatsClientException("Exception requesting stream stats not sure " + e.toString());
		}
	}

	@Override
	public void dispose() {
		if (!disposed) {
			kafkaConsumer.dispose();
			kafkaProducer.dispose();
			disposed = true;
		}

	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			responseQueue.add(DJson.getObjectMapper().readValue(record.value(), StreamStatsResponse.class));
		} catch (Exception e) {
			logger.error("Exception deserializaing streamstats response in consumer " + e.toString());
		}

	}
	
	public void releaseWatch(DStopWatch watch) { 
		pool.release(watch);
	}

	
	

	private  class ResponseFuture implements Future<StreamStatsResponse> {

		private StreamStatsRequest req;
		private StreamStatsResponse resp;
		private volatile boolean done = false;
		private DStopWatch watch;

		public ResponseFuture(StreamStatsRequest request) {
			this.req = request;
			watch.start();
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return false;
		}
		

		public void setResponse(StreamStatsResponse resp) {
			watch.stop();
			resp.setTime(watch.getCompletedSeconds());
		   releaseWatch(watch);
			this.resp = resp;
			this.done = true;
		}

		@Override
		public boolean isCancelled() {
			return false;
		}

		@Override
		public boolean isDone() {
			return done;
		}

		@Override
		public StreamStatsResponse get() throws InterruptedException, ExecutionException {
			return resp;
		}

		@Override
		public StreamStatsResponse get(long timeout, TimeUnit unit)
				throws InterruptedException, ExecutionException, TimeoutException {
			long mils = unit.toMillis(timeout);
			int seepCount = 0;
			while(!isDone()) { 
				Thread.sleep(250);
				seepCount = seepCount + 250;
				if(seepCount > mils) {
					throw new TimeoutException("Response etimeout in " +  unit.name() + " " + timeout);
				}
			}
			return get();
			
		}

	}
	

	private  class ResponseController extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					StreamStatsResponse resp = responseQueue.take();
					ResponseFuture future = callbacks.remove(resp.getReqId());
					if(future == null) { 
						logger.error("Getting a stream stat response with a future req id not in map " + resp.getReqId());
						continue;
					}
					future.setResponse(resp);
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Ezception response controler outer exception " + e.toString(),e);
				}
			}
		}
		
	}

}

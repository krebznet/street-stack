package com.dunkware.trade.service.stream.worker.session.persist;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalListener;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;

public class StreamPersisterExt implements StreamWorkerExtension, XStreamSignalListener {

	private XStream stream;

	private ConcurrentHashMap<Integer, Map<Integer, Number>> lastVarSnapshots = new ConcurrentHashMap<Integer, Map<Integer, Number>>();

	private VarSnapshotCollector varSnapshotCollector;

	private BlockingQueue<XStreamSignal> signalInsertQueue = new LinkedBlockingQueue<XStreamSignal>();
	private BlockingQueue<VarSnapshotInsert> varSnapshotInsertQueue = new LinkedBlockingDeque<VarSnapshotInsert>();

	
	private AtomicBoolean disposing = new AtomicBoolean(false);
	private AtomicInteger returnedVarSnapshotWriers = new AtomicInteger(0);
	JedisPooled jedis = null;

	@Override
	public void init(StreamWorker worker) throws Exception {
		stream = worker.getStream();
		try {
			String redisHost = worker.getStartReq().getStreamProperties().get("redisHost");
			Integer redisPort = Integer.valueOf(worker.getStartReq().getStreamProperties().get("redisPort"));
			jedis = new JedisPooled(redisHost, redisPort);
				
		} catch (JedisException e) {
			throw new Exception("Stream Persister Failed connection to jedis " + e.toString());
		}
	}

	@Override
	public void start() throws Exception {
		varSnapshotCollector = new VarSnapshotCollector();
		stream.getClock().scheduleRunnable(varSnapshotCollector, 1);

	}

	@Override
	public void stop() throws Exception {
		stream.getClock().unscheduleRunnable(null);
	}

	@Override
	public void onSignal(XStreamSignal signal) {
		signalInsertQueue.add(signal);
	}
	
	public class VarSnapshotInsert {
		public int entity;
		public int var;
		public LocalDateTime timestamp;
		public double value;
	}

	public class VarSnapshotCollector implements Runnable {
		
		public void run() {
			LocalDateTime timestamp = stream.getClock().getLocalDateTime();
			for (XStreamEntity entity : stream.getRows()) {
				Map<Integer, Number> snapshots = entity.numericVarSnapshot();
				Map<Integer, Number> changedSnapshots = null;
				if (lastVarSnapshots.get(entity.getIdentifier()) != null) {
					changedSnapshots = StreamPersistHelper.removeEqualKeyValues(snapshots,
							lastVarSnapshots.get(entity.getId()));
				}
				Map<Integer, Number> insertSnapshots = snapshots;
				if (changedSnapshots != null) {
					insertSnapshots = changedSnapshots;
				}
				for (Integer key : insertSnapshots.keySet()) {
					VarSnapshotInsert in = new VarSnapshotInsert();
					in.entity = entity.getIdentifier();
					in.var = key;
					in.value = insertSnapshots.get(key).doubleValue();
					in.timestamp = timestamp;
					varSnapshotInsertQueue.add(in);
				}

			}
		}

	}
	
	
	public class EntityVarWriter extends Thread { 
		
		public void run() { 
			int pendingSyncCount = 0; 
			Pipeline pipeline = jedis.pipelined();
			while(true) { 
				VarSnapshotInsert insert = null;
				try {
					insert = varSnapshotInsertQueue.poll(250,TimeUnit.MILLISECONDS);
					if(insert == null) { 
						if(pendingSyncCount > 0) { 
							try {
								pipeline.sync();
							} catch (Exception e) {
									// todo;
							}
							pendingSyncCount = 0;
						}
						if(disposing.get() == true) { 
							returnedVarSnapshotWriers.incrementAndGet();
							return;
						}
						continue;
					}
					// else we need to create key for the time thing
					// check if it exists 
					// then do a tsAdd to pipeline
					// if pipeline > sizeLimit
					// pipeline.sync()
					
					
				} catch(InterruptedException e) {
					// interrupted
					
				}
				
			}
		}
	}
	
	public class EntitySignalWriter extends Thread { 
		
		public void run() {
			int pendingSyncCount = 0;
			
			while(true) { 
				
				XStreamSignal signal = null;
				try {
					signal = signalInsertQueue.poll(250,TimeUnit.MILLISECONDS);
					if(signal == null) { 
						if(pendingSyncCount > 0 ) { 
							
						}
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				//convert signal to a stream 
				// validate stream exists 
				// 
				
				
			}
			
		}
		
	}

	

}

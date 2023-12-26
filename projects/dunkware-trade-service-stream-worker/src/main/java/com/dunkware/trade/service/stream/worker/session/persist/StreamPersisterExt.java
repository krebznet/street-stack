package com.dunkware.trade.service.stream.worker.session.persist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalListener;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;

@AStreamWorkerExtension
public class StreamPersisterExt implements StreamWorkerExtension, XStreamSignalListener {

	public static final int SNAPSHOT_WRITER_COUNT = 1;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("VarSnapshotInsert");
	private Marker writerDisposingQueueSize = MarkerFactory.getMarker("VarSnapshotDisposeQueueSize");
	private Marker volumeVarCache = MarkerFactory.getMarker("VolumeVarCache");
	private Marker tsAddMaker = MarkerFactory.getMarker("tsAdd");
	private Marker CollectorTimeStamp = MarkerFactory.getMarker("VarSnapshotCollectorTimestamp");
	private XStream stream;

	private ConcurrentHashMap<Integer, Map<Integer, Number>> lastVarSnapshots = new ConcurrentHashMap<Integer, Map<Integer, Number>>();

	private VarSnapshotCollector varSnapshotCollector;

	private BlockingQueue<XStreamSignal> signalInsertQueue = new LinkedBlockingQueue<XStreamSignal>();
	private BlockingQueue<VarSnapshotInsert> varSnapshotInsertQueue = new LinkedBlockingDeque<VarSnapshotInsert>();

	
	private List<VarSnapshotWriter> varSnapshotWriters = new ArrayList<StreamPersisterExt.VarSnapshotWriter>();
	
	private AtomicBoolean disposing = new AtomicBoolean(false);
	private AtomicInteger returnedVarSnapshotWriers = new AtomicInteger(0);
	JedisPooled jedis = null;
	
	private StreamWorker worker;
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
		try {
			String redisHost = worker.getStartReq().getStreamProperties().get("redis.host");
			Integer redisPort = Integer.valueOf(worker.getStartReq().getStreamProperties().get("redis.port"));
			jedis = new JedisPooled(redisHost, redisPort);
				
		} catch (Exception e) {
			throw new Exception("Stream Persister Failed connection to jedis " + e.toString());
		}
	}

	@Override
	public void start() throws Exception {
		stream = worker.getStream();
		int i = 0;
		while(i < SNAPSHOT_WRITER_COUNT) {
			VarSnapshotWriter writer = new VarSnapshotWriter();
			writer.start();
			varSnapshotWriters.add(writer);
			i++;
		}
		varSnapshotCollector = new VarSnapshotCollector();
	
		stream.getClock().scheduleRunnable(varSnapshotCollector, 1);

	}

	@Override
	public void stop() throws Exception {
		stream.getClock().unscheduleRunnable(varSnapshotCollector);
		disposing.set(true);;
		int count = 0;
		while(returnedVarSnapshotWriers.get() < SNAPSHOT_WRITER_COUNT) { 
			try {
				Thread.sleep(250);
				count++;
				if(count > 40) { 
					logger.error("Stopping Stream Persister SnapshotWriters Timedout");
					break;
				}
			} catch (Exception e) {
				logger.error("Outer stop exception " + e.toString());
			}
		}
	}

	@Override
	public void onSignal(XStreamSignal signal) {
		signalInsertQueue.add(signal);
	}
	
	public class VarSnapshotInsert {
		public int entity;
		public int var;
		public long milliseconds;
		public double value;
		public String key;
	}

	
	/**
	 * Runnable that will every 1 second go through all variables
	 * for all entities, create the time series key if not exists 
	 * then get all numeric vars for each entity, match them to 
	 * the last set of snapshots and filter out non-changed vars 
	 * then create a VarSnapshotInsert object and push it to the 
	 * write queue. 
	 * @author Duncan Krebs 
	 *
	 */
	public class VarSnapshotCollector implements Runnable {
		
		
				public void run() { 
					
						
						long timestamp = DunkTime.toMilliseconds(stream.getClock().getLocalDateTime());
						if(logger.isTraceEnabled()) { 
							logger.trace(CollectorTimeStamp, "{}",timestamp);
						}
						
						for (XStreamEntity entity : stream.getRows()) {
							List<XStreamEntityVar> numericVars = entity.getNumericVars();
							for (XStreamEntityVar xStreamEntityVar : numericVars) {
								// validate the key exists
								boolean go = false;
								if(xStreamEntityVar.getVarType().getCode() == 3 || xStreamEntityVar.getVarType().getCode() == 2) { 
									go = true;
								}
								if(!go) { 
									continue;
								}
								String varSnapshotKey = StreamPersistHelper.getVarSnapshotKey(xStreamEntityVar,stream);
								if(jedis.exists(varSnapshotKey) == false) { 
									try {
										StreamPersistHelper.createVarSnapshotKey(xStreamEntityVar, stream, jedis);
									} catch (Exception e) {
										logger.error("Exception Creating Var Serries Key " + e.toString());
										continue;
									}
								}
								
									if(xStreamEntityVar.getSize() > 0) {
										VarSnapshotInsert in = new VarSnapshotInsert();
										in.entity = entity.getIdentifier();
										in.var = xStreamEntityVar.getVarType().getCode();
										in.value = xStreamEntityVar.getNumber(0).doubleValue();
										in.milliseconds = timestamp;
										in.key = varSnapshotKey;
									varSnapshotInsertQueue.add(in);		
									}
								
								}
							}
							


					
									
				}
			
			
			
		}
	

	
	
	public class VarSnapshotWriter extends Thread { 
		
		AtomicLong inserts = new AtomicLong(0);
		public void run() { 
			 long first = 0;
			 long last = 0;
			while(!interrupted()) {
			int pendingSyncCount = 0; 
			Pipeline pipeline = jedis.pipelined();
			while(true) { 
				VarSnapshotInsert insert = null;
				try {
					insert = varSnapshotInsertQueue.poll(250,TimeUnit.MILLISECONDS);
					if(logger.isTraceEnabled()) { 
						if(disposing.get() == true) { 
							logger.trace(writerDisposingQueueSize, "Disposed Invoked Write Queu Size is {}",varSnapshotInsertQueue.size());
						}
					}
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
				
					inserts.incrementAndGet();
					if(insert.var == 3) { 
						if(logger.isTraceEnabled()) { 
							logger.trace(volumeVarCache, "value {} timestamp {} key{} ",insert.value,insert.milliseconds,insert.key);
						}
					}
					pipeline.tsAdd(insert.key, insert.milliseconds,insert.value);
					if(logger.isTraceEnabled()) { 
						logger.trace(tsAddMaker,"tsAdd({},{},{}",insert.key,insert.milliseconds,insert.value);
					}
					if(first == 0) { 
						first = insert.milliseconds;
					}
					last = insert.milliseconds;
					if(logger.isTraceEnabled()) { 
						logger.trace("{}:{}:{}:{}",insert.key,insert.milliseconds,insert.value,inserts.get());
						logger.trace("{}:{}",first,last);
					}
					pendingSyncCount++;
					if(pendingSyncCount > 10) { 
						pipeline.sync();
						pendingSyncCount = 0;
					}
				} catch(InterruptedException e) {
						logger.error("VarSnapshot Thread Interrupted don't like that");
					
				}
				
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

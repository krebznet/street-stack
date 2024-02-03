package com.dunkware.trade.service.stream.worker.session.persist;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.stream.cluster.proto.controller.session.WorkerPersistStats;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.api.XStreamSignalListener;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.UnifiedJedis;

//@AStreamWorkerExtension
public class StreamPersisterExt implements StreamWorkerExtension, XStreamSignalListener, XStreamListener {

	
	
	@Autowired
	private DunkNet dunkNet;
	
	public static final int SNAPSHOT_WRITER_COUNT = 5;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("VarSnapshotInsert");
	private Marker entityVarCollectorCreate = MarkerFactory.getMarker("EntityVarCollectorCreate");
	private Marker writerDisposingQueueSize = MarkerFactory.getMarker("VarSnapshotDisposeQueueSize");
	private Marker writerSyncTime = MarkerFactory.getMarker("SnapshotWriterSyncTime");
	private Marker snapshotWriter = MarkerFactory.getMarker("VarSnapshotWriter");
	private Marker volumeVarCache = MarkerFactory.getMarker("VolumeVarCache");
	private Marker tsAddMaker = MarkerFactory.getMarker("tsAdd");
	private Marker CollectorTimeStamp = MarkerFactory.getMarker("VarSnapshotCollectorTimestamp");
	private XStream stream;

	private ConcurrentHashMap<Integer, Map<Integer, Number>> lastVarSnapshots = new ConcurrentHashMap<Integer, Map<Integer, Number>>();


	private BlockingQueue<XStreamSignal> signalInsertQueue = new LinkedBlockingQueue<XStreamSignal>();
	private BlockingQueue<VarSnapshotInsert> varSnapshotInsertQueue = new LinkedBlockingDeque<VarSnapshotInsert>();

	
	private List<VarSnapshotWriter> varSnapshotWriters = new ArrayList<StreamPersisterExt.VarSnapshotWriter>();
	
	private AtomicBoolean disposing = new AtomicBoolean(false);
	private AtomicLong snapshotWriteCount = new AtomicLong(0);
	private AtomicInteger returnedVarSnapshotWriers = new AtomicInteger(0);
	
	private StatsPublisher statsPublisher; 
	private volatile WorkerPersistStats snapshotStats;
	JedisPooled jedis = null;
	
	private volatile long firstVarCapture; 
	private volatile long lastVarCapture;
	
	private Map<Integer,EntityVarSnapshotCollector> entitySnapshotCollectors = new ConcurrentHashMap<Integer,EntityVarSnapshotCollector>();
	
	private StreamWorker worker;
	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
		try {
			worker.getChannel().addExtension(this);
			String redisHost = worker.getStartReq().getStreamProperties().get("redis.host");
			Integer redisPort = 32595;
		jedis = new JedisPooled(redisHost, redisPort);
		
		} catch (Exception e) {
			throw new Exception("Stream Persister Failed connection to jedis " + e.toString());
		}
	}

	@Override
	public void start() throws Exception {
		stream = worker.getStream();
		worker.getStream().addStreamListener(this);
		
		int i = 0;
		while(i < SNAPSHOT_WRITER_COUNT) {
			try {
				VarSnapshotWriter writer = new VarSnapshotWriter();
				writer.start();
				varSnapshotWriters.add(writer);	
				i++;
			} catch (Exception e) {
				logger.error(snapshotWriter, "Exception creating new writer {}",e.toString(),e);
			}
			
			
		}
		statsPublisher = new StatsPublisher();
		statsPublisher.start();

	}

	@Override
	public void stop() throws Exception {
		statsPublisher.interrupt();
		for (EntityVarSnapshotCollector collector : entitySnapshotCollectors.values()) {
			stream.getClock().unscheduleRunnable(collector);
		}
		
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
	
	
	
	@Override
	public void rowInsert(XStreamEntity row) {
		EntityVarSnapshotCollector collector = new EntityVarSnapshotCollector(row);
		entitySnapshotCollectors.put(row.getIdentifier(), collector);
		worker.getStream().getClock().scheduleRunnable(collector, 1);;
		if(logger.isInfoEnabled()) { 
			logger.info(entityVarCollectorCreate, "Created VarSnapshotCollector for entity {} {}",row.getId(),row.getIdentifier());
		}
		
	}

	public WorkerPersistStats getPersistStats() { 
		return snapshotStats;
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
	public class EntityVarSnapshotCollector implements Runnable {
			
				XStreamEntity entity;
				Map<Integer,String> varSnapshotKeys = new HashMap<Integer,String>();
				Map<Integer,Number> lastValues = new HashMap<Integer,Number>();
				UnifiedJedis unified = null; 
				
				
				
				
				
				public EntityVarSnapshotCollector(XStreamEntity entity) { 
					this.entity = entity;
					try {

					} catch (Exception e) {
						// TODO: handle exception
					}
					List<XStreamEntityVar> numericVars = entity.getNumericVars();
					for (XStreamEntityVar xStreamEntityVar : numericVars) {
						// validate the key exists
					
						String varSnapshotKey = StreamPersistHelper.getVarSnapshotKey(xStreamEntityVar,stream);
						if(jedis.exists(varSnapshotKey) == false) { 
							try {
								StreamPersistHelper.createVarSnapshotKey(xStreamEntityVar, stream, jedis);

							} catch (Exception e) {
								logger.error("Exception Creating Var Serries Key " + e.toString());
								continue;
							}
						}
						varSnapshotKeys.put(xStreamEntityVar.getVarType().getCode(), varSnapshotKey);
						
						}
				}
		
				public void run() { 
					
						
						long timestamp = DunkTime.toMilliseconds(stream.getClock().getLocalDateTime());
						
							List<XStreamEntityVar> numericVars = entity.getNumericVars();
							for (XStreamEntityVar xStreamEntityVar : numericVars) {
								// validate the key exists
							
								String varSnapshotKey = varSnapshotKeys.get(xStreamEntityVar.getVarType().getCode());
								if(varSnapshotKey == null) { 
									logger.error("VarSnapshotKey not found in var key map " + entity.getId() + " " + entity.getIdentifier() + " " + xStreamEntityVar.getVarType().getCode() + " " + xStreamEntityVar.getVarType().getName());
									continue;
								}
								
									
									if(xStreamEntityVar.getSize() > 0) {
										
										Number lastValue = lastValues.get(xStreamEntityVar.getVarType().getCode());
										Number thisValue = xStreamEntityVar.getNumber(0);
										if(lastValue != null) { 
											if(DNumberHelper.compare(thisValue, lastValue) != 0) { 
												VarSnapshotInsert in = new VarSnapshotInsert();
												in.entity = entity.getIdentifier();
												in.var = xStreamEntityVar.getVarType().getCode();
												in.value = xStreamEntityVar.getNumber(0).doubleValue();
												in.milliseconds = timestamp;
												in.key = varSnapshotKey;
										     	varSnapshotInsertQueue.add(in);
												
											}
										} else { 
											VarSnapshotInsert in = new VarSnapshotInsert();
											in.entity = entity.getIdentifier();
											in.var = xStreamEntityVar.getVarType().getCode();
											in.value = xStreamEntityVar.getNumber(0).doubleValue();
											in.milliseconds = timestamp;
											in.key = varSnapshotKey;
									     	varSnapshotInsertQueue.add(in);
										}
										lastValues.put(xStreamEntityVar.getVarType().getCode(), thisValue);	
									}
									
								
								}
						
									
				}
			
			
			
		}
	
	

	public class VarSnapshotWriter extends Thread { 
		
		AtomicLong inserts = new AtomicLong(0);
		JedisClientConfig config = DefaultJedisClientConfig.builder().socketTimeoutMillis(20000).timeoutMillis(200000).build();
		JedisPooled pooled = null;
		
		public VarSnapshotWriter() throws Exception { 
			try {
				pooled = new JedisPooled("testrock1.dunkware.net",32595);		
			} catch (Exception e) {
				throw e;
			}

		}
		
		
		public void run() { 
			
			while(!interrupted()) {
			int pendingSyncCount = 0; 
			Pipeline pipeline = pooled.pipelined();
			int nullCount = 0;
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
						nullCount = 0;
						if(pendingSyncCount > 0) { 
							try {
								pipeline.sync();
								pipeline.close();
								pipeline = pooled.pipelined();
								
								snapshotWriteCount.addAndGet(pendingSyncCount);
								pendingSyncCount = 0;
							} catch (Exception e) {
									logger.error(marker, "what the fuck null snapshot pending write error " + e.toString());
							}
							pendingSyncCount = 0;
						}
						if(disposing.get() == true) { 
							returnedVarSnapshotWriers.incrementAndGet();
							pipeline.close();
							pooled.close();
							return;
						}
						continue;
					}
				
					inserts.incrementAndGet();
					
					pipeline.tsAdd(insert.key, insert.milliseconds,insert.value);
					
					if(logger.isTraceEnabled()) { 
						logger.trace(tsAddMaker,"tsAdd({},{},{}",insert.key,insert.milliseconds,insert.value);
					}
					
					//last = insert.milliseconds;
					
					if(logger.isTraceEnabled()) { 
						logger.trace("{}:{}:{}:{}",insert.key,insert.milliseconds,insert.value,inserts.get());
						//logger.trace("{}:{}",first,last);
					}
					pendingSyncCount++;
					if(pendingSyncCount > 1000) { 
						try {
							Long startTime = System.currentTimeMillis();
							pipeline.sync();
							Long syncTime = System.currentTimeMillis() - startTime;
							logger.info(writerSyncTime, "Sync Time " + syncTime);
							
							pipeline.close();
							pipeline = pooled.pipelined();
							snapshotWriteCount.addAndGet(pendingSyncCount);
							if(firstVarCapture == 0) { 
								firstVarCapture =  insert.milliseconds;
							}
							lastVarCapture = insert.milliseconds;
							pendingSyncCount = 0;	
						} catch (Exception e) {
							pipeline = pooled.pipelined();
							logger.error(snapshotWriter, "Exception syncing pipeline " + e.toString(),e);
						}
						
						
					}
				} catch(InterruptedException e) {
						logger.error("VarSnapshot Thread Interrupted don't like that");
						pipeline.close();
						pooled.close();
				}
				
			}
		}
		}
	}
	
	
	private class StatsPublisher extends Thread { 
		
		public void run() { 
			DStopWatch stop = DStopWatch.create();
			while(!interrupted()) { 
				stop.start();
				long writeCount = snapshotWriteCount.get();
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				long postWriteCount = snapshotWriteCount.get();
				stop.stop();
				WorkerPersistStats snapshotStats = new WorkerPersistStats();
				snapshotStats.setStream(worker.getStream().getInput().getIdentifier());
				snapshotStats.setVarSnapshotCount(snapshotWriteCount.get());
				snapshotStats.setVarSnapshotQueue(varSnapshotInsertQueue.size());
				snapshotStats.setVarSnapshotSecondCount(postWriteCount - writeCount);
				snapshotStats.setVarSnapshotSecondTime(stop.getCompletedSeconds());
				snapshotStats.setVarSnapshotFirstCaptureTime(firstVarCapture);
				snapshotStats.setVarSnapshotLastCaptureTime(lastVarCapture);
				StreamPersisterExt.this.snapshotStats = snapshotStats;
				
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

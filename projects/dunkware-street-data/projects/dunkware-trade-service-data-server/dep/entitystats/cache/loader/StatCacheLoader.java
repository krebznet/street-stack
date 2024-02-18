package com.dunkware.trade.net.data.server.stream.entitystats.cache.loader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.stopwatch.DTimer;
import com.dunkware.trade.net.data.server.stream.entitystats.StreamEntityStats;
import com.dunkware.trade.net.data.server.stream.entitystats.StreamEntityStatsHelper;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StatCacheLoader extends Thread {

	public static final int ENTITY_BATCH_SIZE = 8;
	public static final int LOADER_THREADS = 2;
	public static final int ENTITY_ID_HIGH = 12000;
	
	private volatile int cacheCount = 0;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamData");

	private BlockingQueue<StatCacheLoaderBatch> batchQueue = new LinkedBlockingQueue<StatCacheLoaderBatch>();

	private List<StatCacheBatchLoader> loaders = new ArrayList<StatCacheBatchLoader>();
	private BlockingQueue<StatCacheBatchLoader> loaderCompleteQueue = new LinkedBlockingQueue<StatCacheBatchLoader>();

	private StreamEntityStats entityStats;

	private DStopWatch timer = DStopWatch.create();

	private String preparedStatementQuery = null;
	
	private Speeder speeder; 
	
	public StatCacheLoader(StreamEntityStats entityStats) {
		this.entityStats = entityStats;
	}

	public void run() {
		speeder = new Speeder();
		speeder.start();
		logger.info(marker, "Starting Stat Cache Loader for stream {}", entityStats.getStreamIdentifier());

		List<StatCacheLoaderBatch> batches = StatCacheLoaderHelper.buildLoaderBatchSet(ENTITY_ID_HIGH, ENTITY_BATCH_SIZE);
		StatCacheLoaderBatch b = new StatCacheLoaderBatch(0, 500);
		StatCacheLoaderBatch b1 = new StatCacheLoaderBatch(501, 800);
		StatCacheLoaderBatch b2 = new StatCacheLoaderBatch(801, 12000);

		batchQueue.add(b);
		batchQueue.add(b1);
		batchQueue.add(b2);
		
		//logger.info(marker, "Created Load Batch Count {} Batch Size {}",batches.size(),ENTITY_BATCH_SIZE);
		
		int i = 0; 
		DStopWatch timer = DStopWatch.create();
		timer.start();
		while(i < LOADER_THREADS) { 
			StatCacheBatchLoader loader = new StatCacheBatchLoader("StatCacheLoader-" + i);
			loader.start();
			loaders.add(loader);
			i++;
		}
		logger.info(marker, "Cache is Loading Waiting for loader threads to return");
		try {
			int count = 0;
			while(loaderCompleteQueue.size() != LOADER_THREADS) { 
				try {
					Thread.sleep(1500);
					count++;
					logger.info(marker, "Cache Loader Count is " + cacheCount);
					// okay how many seconds is your max. 
					if(count > 120) { 
						logger.warn(marker, "Waiting {} Seconds for loader thread to complete pending batch count is {}", cacheCount, batchQueue.size());
					}
					if(count > 250)	 { 
						logger.error(marker, "bailing on waiting for batch loader complete, interrupting total insert count is {} batch size is {} ",cacheCount,batchQueue.size());
						for (StatCacheBatchLoader loader : loaders) {
							loader.interrupt();
						}
					}
				} catch (Exception e) {
					logger.error(marker, "Shit whats up oute exception " + e.toString());
				}
			}
			timer.stop();
			logger.info(marker, "Loaded Cache in {} Seconds Record Count {} ", timer.getCompletedSeconds(), cacheCount);
			speeder.interrupt();
		} catch (Exception e) {
			logger.error(marker, "Fcuk " + e.toString());
		}
		
	}
	
	

	/**
	 * Extends a Thread that has a connection and it will continue to pull
	 * StatCacheBatchLoads from a queue and query and insert entity stats into
	 * cache.
	 * 
	 * @author duncankrebs
	 *
	 */
	public class StatCacheBatchLoader extends Thread {

		private String threadName;
		
		public StatCacheBatchLoader(String threadName) {
			this.threadName = threadName;
		}
		
		public void run() {
			setName(threadName);
			Connection cn = null;
			PreparedStatement st = null;
			DStopWatch timer = DStopWatch.create();
			try {
				cn = entityStats.getConnectionPool().getConnectionFromPool();
				String query = StreamEntityStatsHelper.sqlPreparedStatementEntityStatLoadEntRange(entityStats.getStreamIdentifier());
				st = cn.prepareStatement(query, ResultSet.CONCUR_READ_ONLY,ResultSet.FETCH_FORWARD,ResultSet.TYPE_FORWARD_ONLY);
				//st.setFetchSize(1000);
				StatCacheLoaderBatch batch = null;
				while (true) {
					try {
						 batch = batchQueue.poll(5, TimeUnit.SECONDS);
						if (batch == null) {
							logger.info(marker, "StatsCache Builder Thread Returning");
							loaderCompleteQueue.add(this);
							return;
						}
						logger.info(marker, "Pulled Batch " + batch.getEntityStart() + " " + batch.getEntityStop());
						try {
							int greaterThan = batch.getEntityStart() - 1;
							int lessThan = batch.getEntityStop() + 1;
							st.setInt(1, greaterThan);
							st.setInt(2, lessThan);
							timer.start();
							
							ResultSet rs = st.executeQuery();
							int count = 0;
							while(rs.next()) { 
								try {
									EntityStat staty = StreamEntityStatsHelper.toEntityStat(rs);
									entityStats.getCache().cache(staty);
									cacheCount++;
									count++;
									if(count == 25000) {
										logger.info(marker, "Cache Loader Thread Reached 250K");
										count = 0;
									}
									
								} catch (Exception e) {
									logger.error(marker, "exception convering rs to entity stat " + e.toString());						
								}
								
							}
							timer.stop();
							if(logger.isDebugEnabled()) { 
								logger.debug(marker, "Completed Batch Load Query Size {} in {} Seconds",count,timer.getCompletedSeconds());
							}
							timer.start();
							
						} catch (Exception e) {
							logger.error(marker, "Exception executing query on statement " + e.toString(),e);
						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error(marker, "Exception pulling batchQueu that is not inerrtup exception " + e.toString());;
						continue;
					}

				}
				
				
			} catch (Exception e) {
				logger.error(marker, "Exception Creating JDBC connection in cache loader thread " + e.toString());
				return;
			} finally { 
				if(st != null) { 
					try {
						st.close();
					} catch (Exception e2) {
					}
					if(cn != null) { 
						entityStats.getConnectionPool().returnConnectionToPool(cn);
					}
				}
				
			}


		}

	}
	
	
	private class Speeder extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					int lat = cacheCount;
					Thread.sleep(1000);
					System.out.println(cacheCount + " Read Per/Sec " +( cacheCount - lat));
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				
			}
		}
	}

}

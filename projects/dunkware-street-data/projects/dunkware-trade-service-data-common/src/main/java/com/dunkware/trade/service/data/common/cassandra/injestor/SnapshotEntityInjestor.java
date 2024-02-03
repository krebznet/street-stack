package com.dunkware.trade.service.data.common.cassandra.injestor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.data.common.cassandra.StreamDataTables;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;
import com.dunkware.xstream.model.snapshot.SnapshotHelper;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;

public class SnapshotEntityInjestor {
	
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SnapshotEntityInjestor"); 
	
	private BlockingQueue<SnapshotEntity> queue = new LinkedBlockingQueue<SnapshotEntity>();
	private AtomicBoolean disposed = new AtomicBoolean(false);
	private List<InjestorThread> injestorThreads = new ArrayList<InjestorThread>();
	private SnapshotEntityInjestorConfig config; 
	private AtomicInteger errorCount = new AtomicInteger(0);
	private AtomicInteger writeCount = new AtomicInteger(0);
	private AtomicInteger threadDisposeCount = new AtomicInteger(0);
	private int ips = 0; 
	private IPS ipsThread; 
	private int BATCH_SIZE = 100;
	
	public void injest(List<SnapshotEntity> entities) { 
		for (SnapshotEntity snapshotEntity : entities) {
			injest(snapshotEntity);
		}
	}
	
	public void injest(SnapshotEntity entity) { 
		this.queue.add(entity);
	}
	
	public SnapshotEntityInjestorStats getStats() { 
		SnapshotEntityInjestorStats stats = new SnapshotEntityInjestorStats();
		stats.setErrors(errorCount.get());
		stats.setInserts(writeCount.get());
		stats.setQueue(queue.size());
		stats.setIls(ips);
		return stats;
		
	}
	
	public void start(SnapshotEntityInjestorConfig config) throws Exception  { 
		this.config = config;
		BATCH_SIZE = config.getBatchSize();
		if(config.getThreads() < 1) { 
			throw new Exception("Invalid Config thread count must be > 0");
		}
		ipsThread = new  IPS();
		ipsThread.start();
		int i = 0; 
		while(i < config.getThreads()) { 
			InjestorThread thread = new InjestorThread();
			thread.start();
			injestorThreads.add(thread);
			i++;
		}
	}
	
	public void dispose(boolean block) throws Exception { 
		ipsThread.interrupt();
		this.disposed.set(true);
		if(block) { 
			int blockTime = 0; 
			while(threadDisposeCount.get() < injestorThreads.size()) { 
				try {
					Thread.sleep(500);
					blockTime += 500;
				} catch (Exception e) {
					throw e;
				}
				if(blockTime > 5000) { 
					throw new Exception("Dispose Timeout Threads diposed is " + threadDisposeCount.get() + " total thread count is " + injestorThreads.size());
				}
			}
			
		}
	}

	private class InjestorThread extends Thread { 
		
		
		private PreparedStatement preparedStatement;
		private volatile int batchSize = 0;
		private BatchStatementBuilder builder = new BatchStatementBuilder(BatchType.LOGGED);
		
		public InjestorThread() { 
			
			RegularInsert insertInto = QueryBuilder.insertInto(StreamDataTables.VAR_SNAPSHOT)
					.value("stream", QueryBuilder.bindMarker()).value("date", QueryBuilder.bindMarker())
					.value("time", QueryBuilder.bindMarker()).value("entity", QueryBuilder.bindMarker())
					.value("var", QueryBuilder.bindMarker()).value("value", QueryBuilder.bindMarker());
			SimpleStatement insertStatement = insertInto.build();
			preparedStatement = config.getSession().prepare(insertStatement);
			
		}
		
		
		/**
		 * Writes the currente batch insert 
		 */
		private void writeBatch() { 
			try {
			//	System.out.println("Writing Batch Size " + BATCH_SIZE);
				DStopWatch timer = DStopWatch.create();
				timer.start();
				config.getSession().execute(builder.build());
				timer.stop();
				writeCount.addAndGet(batchSize);
			//	System.out.println("Writing Batch Time " + timer.getCompletedSeconds()	);
			} catch (Exception e) {
				errorCount.incrementAndGet();
				logger.error(marker, "Exception batch execute " + e.toString(),e);
			} finally { 
				
				batchSize = 0;
				builder = new BatchStatementBuilder(BatchType.LOGGED);
			}
			
		}
		
		
		private void checkWriteBatch() { 
			if(batchSize == BATCH_SIZE || batchSize > BATCH_SIZE) { 
				writeBatch();
			}
		}
		
		
		private void batchInsert(SnapshotVariable var) { 
			checkWriteBatch();
			LocalDateTime dt  = DunkTime.toLocalDateTime(var.getTimestamp());
			BoundStatement statement = preparedStatement.bind().setInt(0, var.getStream())
					.setLocalDate(1,dt.toLocalDate())
					.setLocalTime(2, dt.toLocalTime())
					.setInt(3, var.getEntity())
					.setInt(4, var.getVar())
					.setDouble(5, var.getValue());
			builder.addStatement(statement);
			batchSize++;
		}
		
		public void run() { 
			while(disposed.get() == false) { 
				try {
					SnapshotEntity entity = queue.poll(1,TimeUnit.SECONDS);
					if(entity == null) { 
						continue;
					}
					List<SnapshotVariable> snapshotVars = SnapshotHelper.snapshotVariables(entity);
					for(SnapshotVariable var : snapshotVars) { 
						batchInsert(var);
					}
				} catch (InterruptedException e) {
					logger.error("Interrupted injestor thread should be disposed gracefully");
					errorCount.incrementAndGet();
				}
				 
			}
			if(batchSize > 0) { 
				writeBatch();
			}
			threadDisposeCount.incrementAndGet();
			
		}
	}
	
	
	private class IPS extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				int inserts = writeCount.get();
				try {
					Thread.sleep(1000);
					ips = writeCount.get() - inserts;
				} catch (Exception e) {
					return;
				}
				
				
			}
		}
		
	}

}

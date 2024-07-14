package com.dunkware.stream.data.cassy.injestors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.stream.data.cassy.entity.EntityStats;
import com.dunkware.stream.data.cassy.repository.EntityStatsRepository;
import com.dunkware.utils.core.stopwatch.StopWatch;

public class EntityStatsInjestor implements EntityInjestor<EntityStats> {

	public static EntityStatsInjestor newInstance(EntityStatsRepository repo, int threads, int batchSize) {
		return new EntityStatsInjestor(repo, threads, batchSize);
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatsInjestor");
	private BlockingQueue<EntityStats> queue = new LinkedBlockingQueue<EntityStats>();


	private EntityStatsRepository repo;
	private int threadCount;
	private int batchSize;

	private List<BatchWriter> writers = new ArrayList<BatchWriter>();
	
	private AtomicLong writeCount = new AtomicLong(0);
	private volatile int lastBatchSize = 0;
	private volatile double lastBatchSpeed = 0;
	private volatile LocalDateTime lastBatchTime = LocalDateTime.of(2000, 1, 1, 1, 9,0,0);
	private int maxBatchSize = 0; 
	private double maxBatchSpeed = 0;
	
	private AtomicInteger errorCount = new AtomicInteger(0);
	
	private EntityInjestorMetrics metrics = new EntityInjestorMetrics();
	
	private EntityStatsInjestor(EntityStatsRepository repo, int threads, int batchSize) {
		this.repo = repo;
		this.threadCount = threads; 
		this.batchSize = batchSize; 
		for(int i = 0; i < threadCount; i++) {
			BatchWriter writer = new BatchWriter(i);
			writer.start();
			writers.add(writer);
		}
	}

	@Override
	public void injest(EntityStats entity) {
		queue.add(entity);
	}

	@Override
	public EntityInjestorMetrics getMetrics() {
		metrics.setCount(writeCount.get());
		metrics.setQueue(queue.size());
		metrics.setMaxSize(maxBatchSize);
		metrics.setMaxSpeed(maxBatchSpeed);
		metrics.setLastSize(lastBatchSize);
		metrics.setLastSpeed(lastBatchSpeed);
		metrics.setLastTime(lastBatchTime.getHour() + ":" + lastBatchTime.getMinute() + ":" + lastBatchTime.getSecond());;
		metrics.setErrors(errorCount.get());
		return metrics; 
	}

	@Override
	public void dispose( )   {
		// its a future 
	}

	private class BatchWriter extends Thread {

		private List<EntityStats> batch = new ArrayList<EntityStats>();
		private StopWatch stopWatch = StopWatch.newInstance();
		
		private int id; 
		
		public BatchWriter(int id) { 
			this.id = id; 
		}
		
		public void run() {
			setName("Entity-Stats_Injestor-" + id);
			while (!interrupted()) {
				try {
					EntityStats row = queue.poll(3, TimeUnit.SECONDS);
					if (row == null) {
						writeBatch();
						continue;
					}
					batch.add(row);
					if(batch.size() > batchSize) { 
						writeBatch();
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						writeBatch();
						return;
					}
					errorCount.incrementAndGet();
					logger.error(marker, "Injestor Loop Exception " +  e.toString());
				}
			}

		}

		private void writeBatch() {
			
			if (batch.size() > 0) {
				stopWatch.start();
				try {
					repo.saveAll(batch);
				} catch (Exception e) {
					errorCount.incrementAndGet();
					logger.error(marker, "Exception Injestion " + e.toString());
					return;
				}
				
				stopWatch.stop();
				writeCount.addAndGet(batch.size());
				if(batch.size() > maxBatchSize) {
					maxBatchSize = batch.size();
					maxBatchSpeed = stopWatch.seconds();
				}
				lastBatchTime = LocalDateTime.now();
				lastBatchSize = batch.size();
				lastBatchSpeed = stopWatch.seconds();
				batch.clear();	
			}
		}

	}

}

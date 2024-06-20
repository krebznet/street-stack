package com.dunkware.stream.data.cassy.loaders;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.shaded.guava.common.util.concurrent.AtomicDouble;
import com.dunkware.stream.data.cassy.constants.CassySchema;
import com.dunkware.time.data.model.entity.EntitySignal;
import com.dunkware.utils.core.json.DunkJson;

public class SessionSignalEntityLoader {
	
public int BATCH_SIZE = 3000;
	
    private Logger logger = LoggerFactory.getLogger(getClass());
	private CqlSession session; 

	private PreparedStatement preparedStatement;
	private SimpleStatement simpleStatement; 
	
	private BlockingQueue<EntitySignal> queue = new LinkedBlockingQueue<EntitySignal>();
	private AtomicInteger errorCount = new AtomicInteger();
	private AtomicInteger insertCount = new AtomicInteger();
	private AtomicDouble lastBulkSpped = new AtomicDouble();
	private AtomicInteger lastBulkSize = new AtomicInteger();
	
	AtomicInteger completed = new AtomicInteger(0);
	volatile int pending = 0; 
	
	
	private List<BatchLoader> loaders = new ArrayList<BatchLoader>();
	
	public int queueSize() { 
		return queue.size();
	}
	
	public SessionSignalEntityLoader(CqlSession session, int loaders, int batchSize ) { 
		this.session = session;
		BATCH_SIZE = batchSize;
		
		
		RegularInsert insertInto = QueryBuilder.insertInto(CassySchema.TableNames.SessionSignalEntity)
				.value("stream", QueryBuilder.bindMarker()).value("date", QueryBuilder.bindMarker())
				.value("entity", QueryBuilder.bindMarker()).value("time", QueryBuilder.bindMarker())
				.value("data", QueryBuilder.bindMarker());
		simpleStatement = insertInto.build().setKeyspace(CassySchema.Keyspaces.StreamData);
		preparedStatement = session.prepare(simpleStatement);
		int i = 0; 
		while(i < loaders) { 
			BatchLoader loader = new BatchLoader();
			loader.start();
			this.loaders.add(loader);
			i++;
		}
		
		Metrics m  = new Metrics();
		m.start();
	}
	
	
	public int getPending() { 
		return pending;
	}
	
	public int getCompleted() { 
		return completed.get();
	}
	
	public void load(EntitySignal signal) { 
		pending++;
		queue.add(signal);
	}
	public void load(List<EntitySignal> stats) { 
		pending = pending + stats.size();
		queue.addAll(stats);
	}
	
	private class BatchLoader extends Thread { 
		
		private BatchStatementBuilder builder = new BatchStatementBuilder(BatchType.LOGGED);
		//private List<EntityStatModel> batch = new ArrayList<EntityStatModel>();
		private int batchCount = 0;
		public void run() { 
			while(!interrupted()) { 
				try {
					EntitySignal model = queue.poll(4,TimeUnit.SECONDS);
					if(model == null) { 
						writeBatch();
						continue;
					}
					
					byte[] bytes = DunkJson.getObjectMapper().writeValueAsBytes(model.getVars());
					ByteBuffer buffer = ByteBuffer.wrap(bytes);
					
					//String data = model.getVars();
					BoundStatement statement = preparedStatement.bind().setInt(0, model.getStream())
							.setLocalDate(1, model.getTimestamp().toLocalDate()).setInt(2, model.getEntity())
							.setLocalTime(3,model.getTimestamp().toLocalTime()).setBytesUnsafe(4, buffer);
					
					
					builder.addStatement(statement);
					batchCount++;
					if(batchCount > BATCH_SIZE)
						writeBatch();
					
				} catch (Exception e) {
					errorCount.incrementAndGet();
					logger.error("Exception insert " + e.toString());;
				}
			}
		}
		
		private void writeBatch() { 
			if(batchCount > 0) { 
				try {
					session.execute(builder.build());
					completed.addAndGet(batchCount);
					pending = pending - batchCount;
					batchCount = 0;
					builder = new BatchStatementBuilder(BatchType.LOGGED);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
		
	}
	
	private class Metrics extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) {
					Thread.sleep(1000);
					System.out.println(queueSize() + " com" + completed.get() + " pending " + pending);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	

}

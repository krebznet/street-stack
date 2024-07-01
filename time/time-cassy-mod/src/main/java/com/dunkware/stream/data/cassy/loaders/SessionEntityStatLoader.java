package com.dunkware.stream.data.cassy.loaders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.dunkware.stream.data.cassy.constants.CassySchema;
import com.dunkware.time.data.model.entity.EntityStatModel;


public class SessionEntityStatLoader {
	
	
	
	public int BATCH_SIZE = 3000;
	
	private CqlSession session; 

	private PreparedStatement preparedStatement;
	private SimpleStatement simpleStatement; 
	
	private BlockingQueue<EntityStatModel> queue = new LinkedBlockingQueue<EntityStatModel>();
	
	
	AtomicInteger completed = new AtomicInteger(0);
	volatile int pending = 0; 
	
	
	private List<BatchLoader> loaders = new ArrayList<BatchLoader>();
	
	public int queueSize() { 
		return queue.size();
	}
	
	public SessionEntityStatLoader(CqlSession session, int loaders, int batchSize ) { 
		this.session = session;
		BATCH_SIZE = batchSize;
		
		
		RegularInsert insertInto = QueryBuilder.insertInto(CassySchema.TableNames.SessionEntityStat)
				.value("stream", QueryBuilder.bindMarker()).value("date", QueryBuilder.bindMarker())
				.value("entity", QueryBuilder.bindMarker()).value("stat", QueryBuilder.bindMarker())
				.value("element", QueryBuilder.bindMarker()).value("value", QueryBuilder.bindMarker()).value("time",QueryBuilder.bindMarker());
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
	
	
	public void load(EntityStatModel model) { 
		pending++;
		this.queue.add(model);
		
	}
	public void load(List<EntityStatModel> stats) { 
		pending++;
		queue.addAll(stats);
	}
	
	private class BatchLoader extends Thread { 
		
		private BatchStatementBuilder builder = new BatchStatementBuilder(BatchType.LOGGED);
		//private List<EntityStatModel> batch = new ArrayList<EntityStatModel>();
		private int batchCount = 0;
		public void run() { 
			while(!interrupted()) { 
				try {
					EntityStatModel model = queue.poll(4,TimeUnit.SECONDS);
					if(model == null) { 
						writeBatch();
						continue;
					}
					BoundStatement statement = preparedStatement.bind().setInt(0, model.getStream())
							.setLocalDate(1, model.getDate()).setInt(2, model.getEntity())
							.setInt(3,model.getStat()).setInt(4,model.getElement()).setDouble(5, model.getValue()).setLocalTime(6, model.getTime());
					
					builder.addStatement(statement);
					batchCount++;
					if(batchCount > BATCH_SIZE)
						writeBatch();
					
				} catch (Exception e) {
					e.printStackTrace();
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

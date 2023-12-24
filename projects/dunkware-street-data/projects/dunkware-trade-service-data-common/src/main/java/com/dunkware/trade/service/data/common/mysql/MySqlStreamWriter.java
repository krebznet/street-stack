package com.dunkware.trade.service.data.common.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class MySqlStreamWriter {

	private BlockingQueue<EntityStat> writeQueue = new LinkedBlockingQueue<EntityStat>();
	private MySqlConnectionPool pool;
	private AtomicInteger threadReturnCount = new AtomicInteger(0);
	private AtomicInteger errrorCount = new AtomicInteger(0);
	private AtomicInteger insertCount = new AtomicInteger(0);

	private Marker marker = MarkerFactory.getMarker("EntityStatsSqlWriter");
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public static MySqlStreamWriter newInstance(MySqlConnectionPool pool, int writeThread, String tableName) throws Exception { 
		return new MySqlStreamWriter(pool,writeThread,tableName);
	}

	public static MySqlStreamWriter newInstance(String dbHost, String dbName, int dbPort, String userName,
			String password, int poolSize, int writeThread, String tableName) throws Exception {
		try {

			MySqlConnectionPool pool = new MySqlConnectionPool(dbHost, dbName, dbPort, userName, password, poolSize);
			return new MySqlStreamWriter(pool, writeThread, tableName);
		} catch (Exception e) {
			throw e;
		}

	}

	private MySqlConnectionPool poool;
	private int writeThreads;
//	private BlockingQueue<EntityStatsInsertThread> completedThreads = new LinkedBlockingQueue<EntityStatsInsertThread>();
	private List<EntityStatsInsertThread> threads = new ArrayList<EntityStatsInsertThread>();
	private String tableName;

	
	
	
	private MySqlStreamWriter(MySqlConnectionPool pool, int writeThreads, String tableName) throws Exception {
		this.pool = pool;
		this.writeThreads = 1;
		int i = 0;
		this.writeThreads = writeThreads;
		this.tableName = tableName;
		while (i < this.writeThreads) {
			threads.add(new EntityStatsInsertThread());
			i++;
			
		}

	}
	
	
	public void createTable(String DDL) throws Exception { 
		Connection cn = null;
		Statement st = null;
		try {
			cn = pool.getConnectionFromPool();
			st = cn.createStatement();
			boolean results = st.execute(DDL);
			if(results == false) { 
				throw new Exception("DDL Execution returnd false");
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if(st != null) { 
				st.close();
			}
			if(cn != null) { 
				cn.close();
			}
		}
		
	}
	
	public double getErrorCount() { 
		return errrorCount.get(); 
	}

	public double write(List<EntityStat> stats) throws Exception {
		DStopWatch watch = DStopWatch.create();
		watch.start();
		for (EntityStatsInsertThread thread : threads) {
			thread.start();
		}
		for (EntityStat entityStat : stats) {
			writeQueue.add(entityStat);
		}
		int counter = 0;
		while (threadReturnCount.get() < threads.size()) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception

			}
			counter++;
			if(counter == 10) { 
				counter = 0;
				System.out.println("Returned Thread count is " + threadReturnCount.get());
			}
		}

		watch.stop();
		return watch.getCompletedSeconds();

	}

	private class EntityStatsInsertThread extends Thread {

		private Connection cn;
		private PreparedStatement st;

		public EntityStatsInsertThread() throws Exception {
		
		}

		public void run() {
			try {
				cn = pool.getConnectionFromPool();
				st = cn.prepareStatement(
						"insert into " + tableName + "(date,ent,element,stat,value,time) VALUES (?, ?, ?, ?, ?, ?)");
			} catch (Exception e) {
				logger.error("Exception getting connection from pool " + e.toString());
				errrorCount.incrementAndGet();
				threadReturnCount.incrementAndGet();
				return;
			}

		
			int batchCount = 0;
			while (true) {
			
				EntityStat stat = null;
				try {
					stat = writeQueue.poll(5, TimeUnit.SECONDS);
				} catch (Exception e) {
					threadReturnCount.incrementAndGet();
					return;
					// TODO: handle exception
				}
				
				if (stat == null) {
					if(batchCount > 0) {
						try {
							st.executeBatch();
							insertCount.addAndGet(batchCount);
							batchCount = 0;
							logger.info(marker, "Inserted Entity Stat batch size " + insertCount + " after 5 seconds");
							
							
						} catch (Exception e) {
							logger.error(marker, "EntityStats", "Exception bulk insert on batch " + e.toString());
							
						}	
					}
					try {
						st.close();
					} catch (Exception e) {
					}
					pool.returnConnectionToPool(cn);
					threadReturnCount.incrementAndGet();
					return;
					
				}

				try {
					st.setDate(1, java.sql.Date.valueOf(stat.getDate()));
					st.setInt(2, stat.getEntity());
					st.setInt(3, stat.getElement());
					st.setInt(4, stat.getStat());
					double value = DCalc.castRoundTo3(stat.getValue().doubleValue());
					st.setDouble(5, value);
					if (stat.getTime() != null) {
						st.setString(6, DunkTime.format(stat.getTime(), DunkTime.HH_MMM_SS));
					} else {
						st.setTime(6, null);
					}
					try {
						st.addBatch();
						batchCount++;
					} catch (Exception e) {
						logger.error(marker, "Exception adding to batch in ingestor writer thread " + e.toString());
						continue;
					}
					if (batchCount == 2500) {
						try {
							logger.info(marker, "Inserting 5000 Records Entity Stats");
						
							st.executeBatch();
							insertCount.incrementAndGet();
						} catch (Exception e) {
							errrorCount.incrementAndGet();
							logger.error(marker,
									"Exception inserting batch count = 3000K execute exception " + e.toString());
						}
						batchCount = 0;
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}
	}

}

package com.dunkware.trade.net.service.streamstats.server.statstore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.model.stats.entity.EntityStats;

@Profile("StreamStore")
@Service()
public class StreamEntityStats {

	public static final int BATCH_INSERT_SIZE = 3500;

	@Value("${stat.store.host}")
	private String dbHost;
	@Value("${stat.store.schema}")
	private String dbName;
	@Value("${stat.store.port}")
	private int dbPort;
	@Value("${stat.store.user}")
	private String dbUsername;
	@Value("${stat.store.pass}")
	private String dbPassword;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("statstore");

	private MySqlConnectionPool pool;


	@PostConstruct
	public void init( ) throws Exception {
		try {
			
			pool = new MySqlConnectionPool(dbHost, dbName, dbPort, dbUsername, dbPassword, 5);
			createTables();
		} catch (Exception e) {
			logger.error(marker, "Fata no connection to daabase  " + e.toString());
			throw new Exception("Stream Stats Store Can't connect to the daabase " + e.toString());
		}
	}

	public void createTables() throws Exception {
		Connection cn = null;
		try {
			 cn = pool.getConnectionFromPool();
			DatabaseMetaData md = cn.getMetaData();
			ResultSet rs = md.getTables(null, null,EntityStatsHelper.createVarStatTable(streamStats.getStreamIdentifier()), null);
			if(!rs.next()) { 
				logger.info(marker, "Creating Stream Entity Stat Table for " + streamStats.getStreamIdentifier());
				Statement st = null;
				try {
					st = cn.createStatement();
					st.execute(EntityStatsHelper.createVarStatTable(streamStats.getStreamIdentifier()));	
					
				} catch (Exception e) {
					logger.error(marker, "Exception creating var stats table " + e.toString());;
					throw e;
				} finally { 
					if(st != null) { 
						st.close();
					}
				}
				
			}
		} catch (Exception e2) {
			logger.error("Exception somewhere here " + e2.toString(),e2);;
			throw new Exception("Creating tables exception var stats " + e2.toString());
		} finally { 
			if(cn != null) {
				try {
					pool.returnConnectionToPool(cn);
				} catch (Exception e) {
					logger.error("Exception returning connection to pool " + e.toString());
				}
				
			}
		}
	}

	public double deleteEntityStats() throws Exception {
		try {
			DStopWatch timer = DStopWatch.create();

			Connection cn = pool.getConnectionFromPool();
			Statement st = cn.createStatement();
			st.execute("DELETE FROM stream_stats_vars");
			timer.stop();
			return timer.getCompletedSeconds();
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}
	
	public MySqlConnectionPool getConnectionPool() { 
		return pool;
	}

	public double getVarStatesForDate(LocalDate date) {
		try {
			Connection cn = pool.getConnectionFromPool();
			String query = "SELECT * FROM `dunkstreet`.`stream_stats_vars`";
			DStopWatch t = DStopWatch.create();

			t.start();
			PreparedStatement st = cn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			int count = 0;

			while (rs.next()) {
				count++;
				rs.getDate("date");
				rs.getInt("var");
				if (count == 25000) {
					count = 0;
					System.out.println("25K");
				}
			}
			pool.returnConnectionToPool(cn);
			t.stop();
			return t.getCompletedSeconds();
		} catch (Exception e) {
			return -1;
		}
	}

	public double insertVarStats(Collection<EntityStats> records) throws Exception {
		Connection cn;
		PreparedStatement st;
		try {
			cn = pool.getConnectionFromPool();
			st = cn.prepareStatement(
					"insert into stream_stats_vars (date,ent,var,stat,value,time) VALUES (?, ?, ?, ?, ?, ?)");

		} catch (Exception e) {
			throw new Exception("exception creating connection and sored procedure " + e.toString());
		}
		int batchCount = 0;
		int counter = 0;
		DStopWatch watch = DStopWatch.create();
		watch.start();
		Iterator<EntityStats> iter = records.iterator();
		while (iter.hasNext()) {
			counter++;
			EntityStats stat = iter.next();
			//st.setDate(1, StreamStatsStoreHelper.toSqlDate(stat.getDate()));
			//st.setInt(2, stat.getEntity());
			//st.setInt(3, stat.getVar());
			//st.setInt(4, stat.getType());
			//st.setDouble(5, stat.getValue().doubleValue());
			//st.setTime(6, StreamStatsStoreHelper.toSqlTime(stat.getTime()));
			st.addBatch();
			batchCount++;
			if (batchCount == BATCH_INSERT_SIZE) {
				batchCount = 0;
				System.out.println("batch execute records = " + counter);
				st.executeBatch();

			}

		}
		if (batchCount > 0) {
			st.executeBatch();
		}
		watch.stop();
		pool.returnConnectionToPool(cn);
		return watch.getCompletedSeconds();

	}

}

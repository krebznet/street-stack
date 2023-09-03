package com.dunkware.trade.net.service.streamstats.server.statstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
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
import com.dunkware.xstream.model.stats.model.VarStat;
import com.mysql.cj.protocol.Resultset;


@Profile("StreamStore")
@Service()
public class StreamStatStore {
	
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
	private void init() {
		try {
			
			pool = new MySqlConnectionPool(dbHost, dbName, dbPort, dbUsername, dbPassword, 5);
		} catch (Exception e) {
			logger.error(marker, "Fata no connection to daabase  " + e.toString());
			return;
		}
	}
	
	public double deleteVarStats() throws Exception { 
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

	public double getVarStatesForDate(LocalDate date) { 
		try {
			Connection cn = pool.getConnectionFromPool();
			String query = "SELECT * FROM `dunkstreet`.`stream_stats_vars`";
			DStopWatch t = DStopWatch.create();
			
			t.start();
			PreparedStatement st = cn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			int count = 0;
			
			while(rs.next()) { 
				count++;
				rs.getDate("date");
				rs.getInt("var");
				if(count == 25000) {
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

	public double insertVarStats(Collection<VarStat> records) throws Exception {
		 Connection cn;
		 PreparedStatement st;
			try {
				cn = pool.getConnectionFromPool();
				st = cn.prepareStatement("insert into stream_stats_vars (date,ent,var,stat,value,time) VALUES (?, ?, ?, ?, ?, ?)");
				
			} catch (Exception e) {
				throw new Exception("exception creating connection and sored procedure " + e.toString());
			}
			int batchCount = 0;
			int counter = 0;
			DStopWatch watch = DStopWatch.create();
			watch.start();
			Iterator<VarStat> iter = records.iterator();
			while(iter.hasNext()) { 
				counter++;
				VarStat stat = iter.next();
				st.setDate(1, StreamStatStoreHelper.toSqlDate(stat.getDate()));
				st.setInt(2,stat.getEntity());
				st.setInt(3, stat.getVar());
				st.setInt(4, stat.getType());
				st.setDouble(5,stat.getValue().doubleValue());
				st.setTime(6,StreamStatStoreHelper.toSqlTime(stat.getTime()));
				st.addBatch();
				batchCount++;
				if(batchCount == BATCH_INSERT_SIZE) { 
					batchCount = 0;
					System.out.println("batch execute records = " + counter);
					 st.executeBatch();
					
				}
			
			}
			if(batchCount > 0) { 
				st.executeBatch();
			}
			watch.stop();
			pool.returnConnectionToPool(cn);
			return watch.getCompletedSeconds();
	
	}
	
}

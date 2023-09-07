package com.dunkware.trade.net.data.server.stream.entitystats.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;

public class EntityStreamStatsHelper {
	
	private static Logger logger = LoggerFactory.getLogger(EntityStreamStatsHelper.class);
	private static Marker  marker = MarkerFactory.getMarker("EntityStats");

	/**
	 * Checks our tables exist for the stream, if not it creates them 
	 * @param streamIdentifier
	 * @param pool
	 * @throws Exception
	 */
	public static void initTables(String streamIdentifier, MySqlConnectionPool pool) throws Exception { 
		Connection cn = null;
		try {
			 cn = pool.getConnectionFromPool();
			DatabaseMetaData md = cn.getMetaData();
			ResultSet resultSet = md.getTables(null, "PUBLIC", null, new String[] {"TABLE"});
			boolean exists = false; 
			String name = streamIdentifier + "_entity_stats_session";
			while(resultSet.next()) { 
				String table = resultSet.getString("TABLE_NAME");
				if(name.equals(table)) { 
					exists = true;
				}
			}
			if(!exists) { 
				Statement st = null;
				try {
					st = cn.createStatement();
					st.execute(sqlEntityStatsCreateTable(streamIdentifier));	
					logger.info(marker, "Created Entity Stats Table for stream " + streamIdentifier);
				} catch (Exception e) {
					throw new Exception("Exception creating entity stats table " + e.toString());
				} finally { 
					if(st != null) { 
						st.close();
					}
					
				}
				
			}
		} catch (Exception e2) {
			throw new Exception("Creating tables exception entity stats " + e2.toString());
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
	
	private static String sqlEntityStatsCreateTable(String streamIdentifier) { 
		return "CREATE TABLE" +  "`" + streamIdentifier + "_entity_stats_session` (\n"
				+ "  `id` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `date` datetime NOT NULL,\n"
				+ "  `ent` int NOT NULL,\n"
				+ "  `element` int NOT NULL,\n"
				+ "  `stat` int NOT NULL,\n"
				+ "  `value` decimal(10,2) NOT NULL,\n"
				+ "  `time` datetime DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`id`),\n"
				+ "  INDEX (`date`, `ent`, `stat`)\n"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; \n"
				+ "";
		
		
	}
}

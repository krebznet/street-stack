package com.dunkware.trade.net.data.server.stream.entitystats;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.data.model.entitystats.EntityStatRequest;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamEntityStatsHelper {
	
	private static Logger logger = LoggerFactory.getLogger(StreamEntityStatsHelper.class);
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
	
	public static EntityStat toEntityStat(ResultSet rs) throws Exception { 
		EntityStat stat = new EntityStat();
		java.sql.Date date = rs.getDate("date");
		
		Instant instant = Instant.ofEpochMilli(date.getTime());
		ZonedDateTime dt = instant.atZone(DTimeZone.toZoneId(DTimeZone.NewYork));
		LocalDate fuck = dt.toLocalDate();
		stat.setDate(fuck);
		stat.setElement(rs.getInt("element"));
		stat.setValue(rs.getDouble("value"));
		Time fuckTime = rs.getTime("time");
		if(fuckTime != null) { 
			LocalTime time = LocalTime.of(fuckTime.getHours(), fuckTime.getMinutes(), fuckTime.getSeconds());
			stat.setTime(time);
		}
		stat.setEntity(rs.getInt("ent"));
		stat.setStat(rs.getInt("stat"));
		return stat; 

		
	}
	
	
	public static String sqlPreparedStatementEntityStatLoadEntRange(String streamIdentifier) { 
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM "); 
		builder.append(getEntityStatsTableName(streamIdentifier));
		builder.append(" ").append("WHERE ent > ? AND ent < ?");
		String query = builder.toString();
		return query;
	}
	
	public static String sqlQueryEntityStatRequest(String streamIdentifier, EntityStatRequest req) { 
		String dateFormat = DunkTime.format(req.getDate(), DunkTime.YYYY_MM_DD);
		
		String query = "SELECT *\n"
				+ "FROM " + streamIdentifier + "_entity_stats_session"  + " where `date` = "
						+ "'" + dateFormat + "'" + " and `ent` = " + req.getEntity() + " and `stat` = " + req.getStat()  + " and"
								+ " `element` = " + req.getElement() + "";
		System.out.println(query);
		return query;
		
	}
	

	private static String sqlEntityStatsCreateTable(String streamIdentifier) { 
		return "CREATE TABLE" +  "`" + streamIdentifier + "_entity_stats_session` (\n"
				+ "  `id` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `date` datetime NOT NULL,\n"
				+ "  `ent` int NOT NULL,\n"
				+ "  `element` int NOT NULL,\n"
				+ "  `stat` int NOT NULL,\n"
				+ "  `value` decimal(15,2) NOT NULL,\n"
				+ "  `time` time DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`id`),\n"
				+ "  INDEX (`date`, `ent`, `stat`) )\n"
				+ "ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci	\n"
				+ "  PARTITION BY Key()\n"
				+ "PARTITIONS 5;\n"
				+ "  ";
		
		
	}
	
	public static String getEntityStatsTableName(String streamIdentifier) { 
		return streamIdentifier + "_entity_stats_session";
	}
}

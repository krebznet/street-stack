package com.dunkware.trade.net.service.streamstats.server.statstore;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.xstream.model.stats.entity.EntityStats;


public class EntityStatsHelper {
	
	
	public static String createVarStatTable(String ident) { 
		
		String table = "CREATE TABLE `stream_" + ident + "_stats_session_entity` (\n"
				+ "  `id` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `date` datetime NOT NULL,\n"
				+ "  `ent` int NOT NULL,\n"
				+ "  `target` int NOT NULL,\n"
				+ "  `type` int NOT NULL,\n"
				+ "  `value` decimal(10,2) NOT NULL,\n"
				+ "  `time` time DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`id`)\n"
				+ "  INDEX (`date`, `ent`)\\n\""
				+ ") ENGINE=InnoDB AUTO_INCREMENT=6425263 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
		return table;
		
		/*
		 * return "CREATE TABLE" + "`" + ident + "_stats_session_entity` (\n" +
		 * "  `id` int NOT NULL,\n" + "  `date` datetime NOT NULL,\n" +
		 * "  `ent` int NOT NULL,\n" + "  `var` int NOT NULL,\n" +
		 * "  `stat` int NOT NULL,\n" + "  `vale` decimal(10,2) NOT NULL,\n" +
		 * "  `time` datetime DEFAULT NULL,\n" + "  PRIMARY KEY (`id`),\n" +
		 * "  INDEX (`date`, `ent`)\n" +
		 * ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" + "";
		 */
	}
	
	public static String varStatTableName(String streamIdent) { 
		return streamIdent + "_session_var_stats";
	}
	
	public static Time toSqlTime(LocalTime localTime) { 
		return java.sql.Time.valueOf(localTime);
	}
	
	public static LocalTime toLocalTime(java.sql.Time time) {
		return time.toLocalTime();
	}
	
	public static java.sql.Date toSqlDate(LocalDate date) { 
		return java.sql.Date.valueOf(date);
	}
	
	public static LocalDate toLocalDate(java.sql.Date input) {
		return input.toLocalDate();
	}
	
	public static String create() { 
		StringBuilder writer = new StringBuilder();
		return writer.append("insert into stream_stats_vars (date,ent,var,stat,value,time) VALUES (?, ?, ?, ?, ?, ?)").toString();
	}
	
	
	public static List<EntityStats> creatMockVarStats(LocalDate from, LocalDate to, List<Integer> identities, List<Integer> vars, List<Integer> statTypes) { 
		List<EntityStats> stats = new ArrayList<EntityStats>();
		long days = ChronoUnit.DAYS.between(from, to);
		long index = 0;
		LocalDate currentDate = from;
		double rangeMin = 3.23;
		double rangeMax = 3293443.3;
		Random r = new Random();
		while(index - 1 < days) { 
			
			for (Integer ident : identities) {
				for (Integer statType : statTypes) {
					for (int var : vars) {
						EntityStats stat = new EntityStats();
					//	stat.setType(statType);
					//	stat.setEntity(ident);
						//stat.setValue(var);
						//stat.setDate(currentDate);
						//stat.setValue(rangeMin + (rangeMax - rangeMin) * r.nextDouble());
						int hour = DRandom.getRandom(2, 22);
						int minute = DRandom.getRandom(3, 59);
						int second = DRandom.getRandom(0, 59);
						//stat.setTime(LocalTime.of(hour, minute,second));
						stats.add(stat);
				}
			}
		}
			index++;
			currentDate = currentDate.plusDays(1);
	}
		return stats;
	}
	
}

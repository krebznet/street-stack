package com.dunkware.trade.net.service.streamstats.server.statstore;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.xstream.model.stats.model.VarStat;


public class StreamStatStoreHelper {
	
	
	public static String createVarStatTable(String ident) { 
		return "CREATE TABLE `stream_stats_vars` (\n"
				+ "  `id` int NOT NULL,\n"
				+ "  `date` datetime NOT NULL,\n"
				+ "  `ent` int NOT NULL,\n"
				+ "  `var` int NOT NULL,\n"
				+ "  `stat` int NOT NULL,\n"
				+ "  `vale` decimal(10,2) NOT NULL,\n"
				+ "  `time` datetime DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`date`,`ent`,`id`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
				+ "";
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
	
	
	public static List<VarStat> creatMockVarStats(LocalDate from, LocalDate to, List<Integer> identities, List<Integer> vars, List<Integer> statTypes) { 
		List<VarStat> stats = new ArrayList<VarStat>();
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
						VarStat stat = new VarStat();
						stat.setType(statType);
						stat.setEntity(ident);
						stat.setValue(var);
						stat.setDate(currentDate);
						stat.setValue(rangeMin + (rangeMax - rangeMin) * r.nextDouble());
						int hour = DRandom.getRandom(2, 22);
						int minute = DRandom.getRandom(3, 59);
						int second = DRandom.getRandom(0, 59);
						stat.setTime(LocalTime.of(hour, minute,second));
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

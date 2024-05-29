package com.dunkware.stream.data.lib.stats.entity;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.utils.core.time.DunkDateFormatter;

public class EntityStatHelper {

	public static void main(String[] args) {
		System.out.println(buildStatKey(2, LocalDate.of(2024,2,3), 2, 4, 3));
	}
	
	public static String buildStatKey(int stream, LocalDate date, int entity, int stat, int element) { 
		return new StringBuilder().append("ES").append(stream).append("").append(DunkDateFormatter.yyyyMMdd(date)).append("").append(entity).append("").append(element).append("").
				append(stat).toString();
	}
	
	
	public static List<String> getRelativeStatKeys(int stream, LocalDate date, int days, int entity, int element, int stat) { 
		return null;
		
	}
	
	
}


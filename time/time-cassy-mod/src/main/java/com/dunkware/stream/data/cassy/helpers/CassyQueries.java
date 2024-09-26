package com.dunkware.stream.data.cassy.helpers;

import java.time.LocalDate;

import com.dunkware.utils.core.time.DunkDateFormatter;

public class CassyQueries {

	
	
	public static String streamSession(int stream, LocalDate date) {
		StringBuilder  builder = new StringBuilder();
		builder.append("select * from dunkstreet.session_entity_stat where stream = ").append(stream).append(" and date = '");
		builder.append(DunkDateFormatter.defaultFormat(date)).append("'");
		System.out.println(builder.toString());
		return builder.toString();
		
		
	}
}

package com.dunkware.street.data.lib.cassy.helpers;

import java.time.LocalDate;

import com.dunkware.stream.utils.time.format.LocalDateFormatter;

public class CassyQueries {

	
	
	public static String streamSession(int stream, LocalDate date) {
		StringBuilder  builder = new StringBuilder();
		builder.append("select * from stream_data.session_entity_stat where stream = ").append(stream).append(" and date = '");
		builder.append(LocalDateFormatter.defaultFormat(date)).append("'");
		System.out.println(builder.toString());
		return builder.toString();
		
		
	}
}

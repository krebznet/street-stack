package com.dunkware.stream.data.cassy.constants;

import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;

public class CassySchema {

	
	public static class TableNames { 
		
		public static final String EntityStats = "entity_stats";
		public static final String SessionEntityStats = "session_entity_stats";
		public static final String SessionEntityStat = "session_entity_stat";
		public static final String StreamSession = "stream_session";
	}
	
	public static class Keyspaces { 
		
		public static final String StreamData = "dunkstreet";
	}
	
	
	
	
	
	
}

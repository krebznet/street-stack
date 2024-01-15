package com.dunkware.trade.service.data.tester;

import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.core.CqlSession;

public class CassandraTestInjestor {

	
	public void test(CqlSession session) { 
		
		Insert into = QueryBuilder.insertInto("stream_data_snapshot_var").value(null, getClass());
	//	session.execute(into);
	}
}

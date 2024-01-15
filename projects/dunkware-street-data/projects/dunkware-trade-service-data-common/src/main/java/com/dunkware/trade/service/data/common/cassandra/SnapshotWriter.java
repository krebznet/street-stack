package com.dunkware.trade.service.data.common.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.dunkware.xstream.model.snapshot.SnapshotSignal;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;

public class SnapshotWriter {

	private PreparedStatement varPreparedSatement;
	private PreparedStatement signalTypePreparedStatement;
	private PreparedStatement signalEntityPreparedStatement;

	private CqlSession session;
	
	// 1 how to write date
	// 2 how to write timestamp; 

	/*
	 * "stream" int, "date" date, "entity" int, "var" int, "datetime" timestamp,
	 * "value" double,
	 */
	public SnapshotWriter(CqlSession session) {
		this.session = session;

		RegularInsert insertInto = QueryBuilder.insertInto(StreamDataTables.VAR_SNAPSHOT).value("stream", QueryBuilder.bindMarker())
				.value("date", QueryBuilder.bindMarker()).value("entity", QueryBuilder.bindMarker())
				.value("var",QueryBuilder.bindMarker()).value("datetime", QueryBuilder.bindMarker())
				.value("value", QueryBuilder.bindMarker());

		SimpleStatement insertStatement = insertInto.build();
		insertStatement = insertStatement.setKeyspace("dunkstreet");
		varPreparedSatement = session.prepare(insertStatement);

	}
	
	public void writeSnapshotVariable(SnapshotVariable var) throws Exception { 
	//	BoundStatement statement = varPreparedSatement.bind()
       //         .setUuid(0, video.getId())
       //         .setString(1, video.getTitle())
       //         .setInstant(2, video.getCreationDate());

	}
	
	public void writeSignal(SnapshotSignal signal) throws Exception { 
		
	}
	

	// PreparedStatement - cache
	// BoundStatement prepared.bind
}

package com.dunkware.trade.service.data.common.cassandra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.snapshot.SnapshotSignal;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;

public class SnapshotWriter {

	public static void main(String[] args) {

	}

	private PreparedStatement varPreparedSatement;
	private PreparedStatement signalTypePreparedStatement;
	private PreparedStatement signalEntityPreparedStatement;
    private SimpleStatement varSimpleStatement; 
	private CqlSession session;

	// 1 how to write date
	// 2 how to write timestamp;

	/*
	 * "stream" int, "date" date, "entity" int, "var" int, "datetime" timestamp,
	 * "value" double,
	 */
	// when first instantiated i took example code from that repo and created a PreparedSatement
	// upfront for inserting variable snapshots 
	public SnapshotWriter(CqlSession session) {
		this.session = session;

		RegularInsert insertInto = QueryBuilder.insertInto(StreamDataTables.VAR_SNAPSHOT)
				.value("stream", QueryBuilder.bindMarker()).value("date", QueryBuilder.bindMarker())
				.value("time", QueryBuilder.bindMarker()).value("entity", QueryBuilder.bindMarker())
				.value("var", QueryBuilder.bindMarker()).value("value", QueryBuilder.bindMarker());

		SimpleStatement insertStatement = insertInto.build();
		
		//insertStatement = insertStatement.setKeyspace("dunkstreet");
		varPreparedSatement = session.prepare(insertStatement);

	}

	
	public String writeSnapshotVariables(List<SnapshotVariable> vars) throws Exception { 
		StringBuilder builder = new StringBuilder(); 
		DStopWatch w = DStopWatch.create();
		w.start();
		builder.append("BEGIN BATCH ");
		builder.append("\n");
		//BatchStatement batch = new BatchStatementBuilder(BatchType.UNLOGGED).build(); // tried with unlogged also
		for (SnapshotVariable snapshotVariable : vars) {
			
			builder.append("INSERT INTO dunkstreet.stream_data_var_snapshot (stream, date, entity, var, time, value) VALUES (");
			builder.append(snapshotVariable.getStream());
			builder.append(", ");
			builder.append("'2024-01-03'");
			builder.append(", ");
			builder.append(snapshotVariable.getEntity());
			builder.append(", ");
			builder.append(snapshotVariable.getVar());
			builder.append(", ");
			builder.append("'23:32:44', ");
			builder.append(snapshotVariable.getValue());
			builder.append(");");
			builder.append("\n");
			
			
			
			
		}
		builder.append("APPLY BATCH;");
	//	System.out.println(builder.toString());
		session.execute(builder.toString());
		w.stop();
		return String.valueOf(w.getCompletedSeconds());
		
	}
	
	// 
	
	// here we pass in a list of SnapshotVariable objects and by making batchStatement 
	// it is super fast. 
	public void writeSnapshotVariable(List<SnapshotVariable> vars) throws Exception {
		// build the batchstatement 
		
		BatchStatementBuilder builder = new BatchStatementBuilder(BatchType.LOGGED);
		
		BatchStatement batch = new BatchStatementBuilder(BatchType.LOGGED).build(); // tried with unlogged also

		for (SnapshotVariable var : vars) {
			
			// for each object in vars list we call bind() on preparedStatement to create
			// a bound statement which is where we set the values, the first param int
			// matches the columns up here
			LocalDateTime dt  = DunkTime.toLocalDateTime(var.getTimestamp());
			BoundStatement statement = varPreparedSatement.bind().setInt(0, var.getStream())
					.setLocalDate(1,dt.toLocalDate())
					.setLocalTime(2, dt.toLocalTime())
					.setInt(3, var.getEntity())
					.setInt(4, var.getVar())
					.setDouble(5, var.getValue());
			//Object foo = session.execute(statement);
			//System.out.println("sotp here");
			
			try {
				// add it to the batch 
			builder.addStatement(statement);
			//	System.out.println("added " + vars.size() + " Records");
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		try {
			// execute the batch
			
			Object test = session.execute(builder.build());
		//	System.out.println(test.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}

	public void writeSignal(SnapshotSignal signal) throws Exception {

	}

	// PreparedStatement - cache
	// BoundStatement prepared.bind
}

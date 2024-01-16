package com.dunkware.trade.service.data.common.cassandra;

import java.time.LocalTime;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchStatementBuilder;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.snapshot.SnapshotSignal;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;

public class SnapshotWriter {

	public static void main(String[] args) {

	}

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

		RegularInsert insertInto = QueryBuilder.insertInto(StreamDataTables.VAR_SNAPSHOT)
				.value("stream", QueryBuilder.bindMarker()).value("date", QueryBuilder.bindMarker())
				.value("time", QueryBuilder.bindMarker()).value("entity", QueryBuilder.bindMarker())
				.value("var", QueryBuilder.bindMarker()).value("value", QueryBuilder.bindMarker());

		SimpleStatement insertStatement = insertInto.build();
		insertStatement = insertStatement.setKeyspace("dunkstreet");
		varPreparedSatement = session.prepare(insertStatement);

	}

	public void writeSnapshotVariable(List<SnapshotVariable> vars) throws Exception {
		BatchStatement batch = new BatchStatementBuilder(BatchType.UNLOGGED).build(); // tried with unlogged also

		for (SnapshotVariable var : vars) {
			BoundStatement statement = varPreparedSatement.bind().setInt(0, var.getStream())
					.setLocalDate(1, DunkTime.toLocalDateTime(var.getTimestamp()).toLocalDate())
					.setLocalTime(2, LocalTime.now()).setInt(3, var.getEntity()).setInt(4, var.getVar())
					.setDouble(5, var.getValue());
			try {
				batch.add(statement);

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		try {
			session.execute(batch);
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

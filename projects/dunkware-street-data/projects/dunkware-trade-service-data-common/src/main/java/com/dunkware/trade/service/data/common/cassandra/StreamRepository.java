package com.dunkware.trade.service.data.common.cassandra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ExecutionInfo;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.internal.core.cql.DefaultAsyncResultSet;
import com.datastax.oss.driver.internal.core.cql.SinglePageResultSet;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;

/**
 * Created a StreamRepository class that takes in a CqlSession which is thread
 * safe here i will start to add query methods around the schema
 * 
 * @author duncankrebs
 *
 */
public class StreamRepository {

	private CqlSession session;

	public StreamRepository(CqlSession session) {
		this.session = session;
	}

	public int testCount() {
		try {
			String query = "SELECT COUNT(*) from " + StreamDataTables.VAR_SNAPSHOT;
			SinglePageResultSet rs = (SinglePageResultSet) session.execute(query);

			return rs.all().get(0).getInt("count");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
			// TODO: handle exception
		}

	}

	// this for now is selecting all and i add a limit of 10,000 and allow filtering
	public List<SnapshotVariable> findSnapshotVars(int stream, LocalDate date, int entity, int var) throws Exception {
		List<SnapshotVariable> results = new ArrayList<SnapshotVariable>();

		SimpleStatement st = new SimpleStatementBuilder("SELECT * FROM " + StreamDataTables.VAR_SNAPSHOT).build();
		ResultSet rs = session.execute(st); //CqlSession being used
		Iterator<Row> iterator = rs.iterator();
		int rowCount = 0;
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			rowCount++;
			System.out.println("fully fetched " + rs.isFullyFetched());
			
		}
		System.out.println("Results returned : " + rowCount);
	
		
		return null;

	}

}

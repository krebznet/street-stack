package com.dunkware.trade.service.data.tester;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.core.CqlSession;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.data.common.cassandra.SnapshotWriter;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;


@RestController
@Profile("Injestor")
public class CassandraTestInjestor {

	
	@Autowired
	CassandraConfig config; 
	
	private CqlSession session;
	
	private SnapshotWriter writer;
	
	@PostConstruct
	public void postInit() { 
		try {
			session = config.cassandraSession().getObject();
			writer = new SnapshotWriter(session);
		} catch (Exception e) {
			System.out.println("error getting session " + e.toString());
			e.printStackTrace();
			System.exit(-1);

		}
	}
	
	@RequestMapping(path = "/test/injest/var/snapshot")
	public String test(@RequestParam int count) { 
		int i = 0; 
		List<SnapshotVariable> vars = new ArrayList<SnapshotVariable>();
		while(i < count) { 
			SnapshotVariable v = new SnapshotVariable();
			v.setStream(2);
			v.setEntity(i);
			v.setVar(i);
			v.setTimestamp(322332L);
			v.setValue(32.3 + i);
			vars.add(v);
			i++;
		}
		try {
			DStopWatch watch = DStopWatch.create();
			watch.start();
			writer.writeSnapshotVariable(vars);
			watch.stop();
			return String.valueOf(watch.getCompletedSeconds());
			
		} catch (Exception e) {
			return e.toString();
		}
	}
}

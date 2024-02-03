package com.dunkware.trade.service.data.tester;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.CqlSession;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.data.common.cassandra.SnapshotWriter;
import com.dunkware.trade.service.data.common.cassandra.StreamRepository;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;


@RestController
@Profile("InjestorTest")
public class CassandraTestInjestor {

	
	@Autowired
	CassandraConfig config; 
	
	private CqlSession session;
	
	// i created a snapshot writer here
	private SnapshotWriter writer;
	
	private StreamRepository streamRepo;
	
	@PostConstruct
	public void postInit() { 
		try {
			session = config.cassandraSession().getObject();
			writer = new SnapshotWriter(session);
			streamRepo = new StreamRepository(session);
		} catch (Exception e) {
			System.out.println("error getting session " + e.toString());
			e.printStackTrace();
			System.exit(-1);

		}
	}
	
	
	@RequestMapping(path = "/test/query/var/snapshot1")
	public String query1() { 
		try {
			List<SnapshotVariable> results = streamRepo.findSnapshotVars(1, LocalDate.now(), 0, 0);
			return String.valueOf(streamRepo.testCount());
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	// this will insert recrods into the var snapshot table based on the count query param 
	@RequestMapping(path = "/test/injest/var/snapshot")
	public String test(@RequestParam int count) { 
		int i = 0; 
		List<SnapshotVariable> vars = new ArrayList<SnapshotVariable>();
		LocalDateTime dt = LocalDateTime.of(2001, 1, 1, 1, 1);
		LocalTime lt = dt.toLocalTime();
		while(i < count) { 
			SnapshotVariable var = new SnapshotVariable();
			var.setEntity(1);
			var.setStream(1);
			var.setVar(1);
			var.setValue(1.0 + i);
			var.setTimestamp(DunkTime.toMilliseconds(LocalDateTime.of(dt.toLocalDate(), lt)));
			vars.add(var);
			lt = lt.plusSeconds(1);
			i++;
		}
		
		
		
		
		try {
			
			DStopWatch w = DStopWatch.create();
			w.start();
			writer.writeSnapshotVariable(vars);
			w.stop();
			return String.valueOf(w.getCompletedSeconds());
			
			
		} catch (Exception e) {
			return e.toString();
		}
	}
}

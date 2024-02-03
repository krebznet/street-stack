package com.dunkware.trade.service.data.tester;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.data.common.cassandra.injestor.SnapshotEntityInjestor;
import com.dunkware.trade.service.data.common.cassandra.injestor.SnapshotEntityInjestorConfig;
import com.dunkware.trade.service.data.common.cassandra.injestor.SnapshotEntityInjestorStats;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;
import com.dunkware.xstream.model.snapshot.factories.SnapshotEntityFactory;

@RestController()
public class CassandraEntityInjestor {

	@Autowired
	CassandraConfig cassandraConfig; 
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("CassandraEntityInjestor");
	
	private SnapshotEntityInjestor injestor = null;
	
	private int batchSize; 

	
	@GetMapping("/injestor/stats")
	public @ResponseBody SnapshotEntityInjestorStats getStats() { 
		return injestor.getStats();
	}
	
	@GetMapping("/injestor/create")
	public String create(@RequestParam int threads, @RequestParam int batchSize) {
		
		SnapshotEntityInjestorConfig injestorConfig = new SnapshotEntityInjestorConfig(cassandraConfig.cassandraSession().getObject(),threads,batchSize);
		try {
			injestor = new SnapshotEntityInjestor();
			injestor.start(injestorConfig);
		} catch (Exception e) {
			return e.toString();
		}
		return "OK";
	}
	
	@GetMapping("/injestor/dispose")
	public String dispose() { 
		if(injestor == null) { 
			return "NULL INJESTOR";
		}
		try {
			injestor.dispose(true);
		} catch (Exception e) {
			return e.toString();
		}
		return "OK";
	}
	
	
	@GetMapping(path = "/injestor/injest")
	
	public String insertSnapshots(@RequestParam int entities, @RequestParam int variables, @RequestParam int seconds,
			@RequestParam int year, @RequestParam int month, @RequestParam int day, @RequestParam int stream) {
		if(injestor == null) { 
			return "INJESTOR NULL CALL /injestor/create";
		}
		LocalDateTime start = LocalDateTime.of(year, month, day, 9, 0);
		LocalDateTime stop = start.plusSeconds(seconds);
		List<SnapshotEntity> snapshots = null;
		try {
			snapshots = SnapshotEntityFactory.instance().entityCount(entities).varCount(variables).timeRange(start, stop).streamId(stream).build();	
		} catch (Exception e) {
			return "Snapshot Builder Exception " + e.toString();
		}
		injestor.injest(snapshots);
		return "OK Entity Snapshots = " + snapshots.size() + " Variable Snapshots = " + snapshots.size() * variables; 
		
	}
	
	
}

package com.dunkware.trade.service.stream.server.stats.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.stats.repository.SessionEntityStatsDoc;
import com.dunkware.trade.service.stream.server.stats.repository.SessionEntityStatsRepo;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.model.stats.SessionStats;
import com.mongodb.bulk.BulkWriteResult;

@RestController
public class StreamStatsWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoOperations mongoTemplate;

	// @Autowired
	// private StreamStatsService statsService;

	@Autowired
	private SessionEntityStatsRepo entityStats;

	@PostConstruct
	private void testInsert() {

		/*
		 * try { int i = 0; System.out.println("being insert"); while(i < 100) {
		 * 
		 * SessionEntityStats stats = new SessionEntityStats();
		 * stats.setId(DRandom.getRandom(4, 43343434));
		 * stats.setEndTime(LocalDateTime.now());
		 * stats.setStartTime(LocalDateTime.now().minusHours(4));
		 * stats.setSessionId(23323); stats.setStreamId(323);
		 * stats.setStreamIdent("Equity"); SessionEntityVarStats vStats = new
		 * SessionEntityVarStats(); vStats.setHigh(32);
		 * vStats.setHighTime(LocalDateTime.now()); vStats.setLow(423.3);
		 * vStats.setLowTime( LocalDateTime.now().minusDays(323));
		 * stats.getVarStats().add(vStats);
		 * 
		 * entityStats.save(stats); System.out.println("saved"); i++; }
		 * System.out.println("end insert");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); // TODO: handle exception }
		 */

	}

	@PostMapping("/session/stats/submit")
	public String startWorker(@RequestParam("file") MultipartFile file) {
		byte[] bundleBytes = null;
		try {
			bundleBytes = file.getBytes();
		} catch (Exception e) {
			return e.toString();
		}
		SessionStats stats = null;
		try {
			stats = DJson.getObjectMapper().readValue(bundleBytes, SessionStats.class);
		} catch (Exception e) {
			logger.error("Exception deserializing stats from session submit " + e.toString(), e);
			return e.toString();
		}

		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,
				SessionEntityStatsDoc.class);
		
		List<SessionEntityStatsDoc> docs = new ArrayList<SessionEntityStatsDoc>();
		for (EntityStats entityStatus : stats.getEntityStats()) {
			SessionEntityStatsDoc doc = StreamStatsHelper.toSessionEntityStatsDoc(entityStatus);
			docs.add(doc);
			bulkOps.insert(doc);
		}

		try {
			System.out.println("wirting bulk ops" + LocalDateTime.now());
			BulkWriteResult results = bulkOps.execute();
			System.out.println("wrote bulk ops" + LocalDateTime.now());
		} catch (Exception e) {
			logger.error("Exception saving all session entity stats " + e.toString(), e);
		}

		try {
			return "POSTED!";
		} catch (Exception e) {
			return e.toString();
		}

	}

	// it would be a list of stats. for a session

}

package com.dunkware.trade.service.stream.server.stats.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.xstream.core.stats.StreamStats;
import com.dunkware.xstream.model.stats.StreamEntityDayStats;
import com.mongodb.bulk.BulkWriteResult;

@RestController
public class StreamStatsWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoOperations mongoTemplate;

	private Marker sessionstats = MarkerFactory.getMarker("stream.session.stats");
	
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
	public String sessionStatsSubmit(@RequestParam("file") MultipartFile file) {
		logger.error("Nice you! In session stats submit!");
		byte[] bundleBytes = null;
		try {
			bundleBytes = file.getBytes();
		} catch (Exception e) {
			logger.error(sessionstats, "Exception getting file bytes on submit from node " + e.toString());
			return e.toString();
		}
		StreamStats stats = null;
		try {
			stats = DJson.getObjectMapper().readValue(bundleBytes, StreamStats.class);
		} catch (Exception e) {
			logger.error("Exception deserializing stats from session submit " + e.toString(), e);
			return e.toString();
		}

		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,
				StreamEntityDayStatsDoc.class);
		
		List<StreamEntityDayStatsDoc> docs = new ArrayList<StreamEntityDayStatsDoc>();
		for (StreamEntityDayStats dayStats : stats.getEntities()) {
			docs.add(StreamStatsHelper.toStreamEntityDayStatsDoc(dayStats));
		}
		DStopWatch watch = DStopWatch.create();
		watch.start();
		
		bulkOps.insert(docs);
		try {
			BulkWriteResult results = bulkOps.execute();
			if(results.getInsertedCount() < docs.size()) { 
				logger.error(sessionstats, "Stats Bulk insert result count is " + results.getInsertedCount() + " doc list size is " + docs.size());
			}
		} catch (Exception e) {
			logger.error(sessionstats, "Stats push Exception inserting node stats " + e.toString(),e);
			return e.toString();
		}
		watch.stop();
		if(logger.isDebugEnabled()) { 
			StringBuilder builder = new StringBuilder();
			builder.append("Stats pushed for " + stats.getStreamIdent() + ", "  + docs.size() + " entities inserted into db " + watch.getCompletedSeconds() + "secs");
			logger.debug(sessionstats,builder.toString());
		}
		return "POSTED!";
	}

	// it would be a list of stats. for a session

}

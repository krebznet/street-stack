package com.dunkware.trade.service.stream.server.stats.core;

import java.util.ArrayList;
import java.util.List;

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
import com.dunkware.trade.service.stream.server.sequence.MongoSequenceService;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsRepo;
import com.dunkware.xstream.core.stats.StreamStats;
import com.dunkware.xstream.model.stats.StreamEntityDayStats;
import com.mongodb.bulk.BulkWriteResult;

@RestController
public class StreamStatsWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoOperations mongoTemplate;

	private Marker sessionstats = MarkerFactory.getMarker("stream.session.stats");
	
	@Autowired
	private StreamEntityDayStatsRepo repo; 
	
	@Autowired
	private MongoSequenceService sequenceService;
	
	

	@PostMapping("/session/stats/submit")
	public String sessionStatsSubmit(@RequestParam("file") MultipartFile file) {
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
			StreamEntityDayStatsDoc doc = new StreamEntityDayStatsDoc();
			
			doc.setDate(dayStats.getDate());
			doc.setEntId(dayStats.getEntityId());
			doc.setEntIdent(dayStats.getEntityIdent());
			doc.setSigs(dayStats.getSignals());
			doc.setVars(dayStats.getVariables());
			doc.setId(sequenceService.generateSequence(StreamEntityDayStatsDoc.SEQUENCE_NAME));
			doc.setStream(dayStats.getStreamIdent());
			docs.add(doc);
			
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

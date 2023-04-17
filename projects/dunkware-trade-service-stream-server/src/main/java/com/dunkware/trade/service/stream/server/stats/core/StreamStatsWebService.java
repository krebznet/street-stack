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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.stream.server.sequence.MongoSequenceService;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsRepo;
import com.dunkware.xstream.core.stats.StreamStats;
import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsSession;
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
		for (EntityStatsSession dayStats : stats.getEntities()) {
			StreamEntityDayStatsDoc doc = new StreamEntityDayStatsDoc();
			doc.setDate(dayStats.getDate());
			doc.setEntId(dayStats.getId());
			doc.setEntIdent(dayStats.getIdent());
			doc.setVars(dayStats.getVars());
			doc.setId(DRandom.getRandom(4, 489383948));
			doc.setId(sequenceService.generateSequence(StreamEntityDayStatsDoc.SEQUENCE_NAME));
			doc.setStream(dayStats.getStream());
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

	
	@GetMapping(path = "/stats/debug/entity")
	public @ResponseBody EntityStatsAgg debugEntityStats(@RequestParam String ident) throws Exception { 
		// Find
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("entIdent").is(ident));
			
			List<StreamEntityDayStatsDoc> results = mongoTemplate.find(query, StreamEntityDayStatsDoc.class);
			System.out.println(results.size());
			EntityStatsAgg agg = StreamStatsUtils.buildEntityStatsAgg(results, ident, 1);
			
			return agg; 
		} catch (Exception e) {
			logger.error("/debug/entity/stats error " + e.toString());
			throw e;
		}

		
	}
	
	// lets see that happen. 
	
	// then a screen 

}

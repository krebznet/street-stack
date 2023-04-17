package com.dunkware.trade.service.stream.server.stats.web;

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
import com.dunkware.trade.service.stream.server.stats.StreamStats;
import com.dunkware.trade.service.stream.server.stats.StreamStatsEntity;
import com.dunkware.trade.service.stream.server.stats.StreamStatsService;
import com.dunkware.trade.service.stream.server.stats.core.StreamStatsHelper;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsRepo;
import com.dunkware.xstream.core.stats.StreamStatsPayload;
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
	
	@Autowired
	private StreamStatsService statsService; 
	
	

	@PostMapping("/stats/payload/session")
	public String sessionStatsSubmit(@RequestParam("file") MultipartFile file) {
		byte[] bundleBytes = null;
		try {
			bundleBytes = file.getBytes();
		} catch (Exception e) {
			logger.error(sessionstats, "Exception getting file bytes on submit from node " + e.toString());
			return e.toString();
		}
		StreamStatsPayload payload = null;
		try {
			payload = DJson.getObjectMapper().readValue(bundleBytes, StreamStatsPayload.class);
		} catch (Exception e) {
			logger.error("Exception deserializing stats from session submit " + e.toString(), e);
			return e.toString();
		}
		try {
			StreamStats streamStats = statsService.getStreamStats(payload.getStreamIdent());
			streamStats.payload(payload);
		} catch (Exception e) {
			logger.error(sessionstats, "Did not find stream stats for payload stream ident " + payload.getStreamIdent());
			return e.toString();
		}
		return "POSTED!";
	}
	
	@GetMapping(path = "/stats/entity/agg")
	public @ResponseBody EntityStatsAgg statsEntityAgg(@RequestParam String stream, @RequestParam String ident) throws Exception { 
		try {
			StreamStats stats = statsService.getStreamStats(stream);
			StreamStatsEntity entity = stats.getEntity(ident);
			return entity.getAgg();
		} catch (Exception e) {
			throw new Exception("Exception getting stream entity agg " + e.toString());
		}
	}

	
	@GetMapping(path = "/stats/debug/entity")
	public @ResponseBody EntityStatsAgg debugEntityStats(@RequestParam String ident) throws Exception { 
		// Find
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("entIdent").is(ident));
			
			List<StreamEntityDayStatsDoc> results = mongoTemplate.find(query, StreamEntityDayStatsDoc.class);
			System.out.println(results.size());
			EntityStatsAgg agg = StreamStatsHelper.buildEntityStatsAgg(results, ident, 1);
			
			return agg; 
		} catch (Exception e) {
			logger.error("/debug/entity/stats error " + e.toString());
			throw e;
		}
	}
	

}

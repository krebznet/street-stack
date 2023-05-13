package com.dunkware.trade.service.stream.server.stats.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.stream.json.stats.EntityStatsSessionResp;
import com.dunkware.trade.service.stream.server.sequence.MongoSequenceService;
import com.dunkware.trade.service.stream.server.stats.StreamStats;
import com.dunkware.trade.service.stream.server.stats.StreamStatsEntity;
import com.dunkware.trade.service.stream.server.stats.StreamStatsService;
import com.dunkware.trade.service.stream.server.stats.core.StreamStatsHelper;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsRepo;
import com.dunkware.xstream.core.stats.StreamStatsPayload;
import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsSessions;

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
			logger.error(sessionstats,
					"Did not find stream stats for payload stream ident " + payload.getStreamIdent());
			return e.toString();
		}
		return "POSTED!";
	}

	@GetMapping(path = "/stream/stats/entity/agg")
	public @ResponseBody EntityStatsAgg statsEntityAgg(@RequestParam String stream, @RequestParam String ident)
			throws Exception {
		try {
			StreamStats stats = statsService.getStreamStats(stream);
			StreamStatsEntity entity = stats.getEntity(ident);
			return entity.getAgg();
		} catch (Exception e) {
			
			throw new Exception("Exception getting stream entity agg " + e.toString());
		}
	}

	@GetMapping(path = "/stream/stats/entity/sessions")
	public @ResponseBody EntityStatsSessions statsEntitySessions(@RequestParam String stream,
			@RequestParam String ident) {
		try {
			return statsService.getStreamStats(stream).getEntity(ident).getSessions();
		} catch (Exception e) {
			EntityStatsSessions sessions = new EntityStatsSessions();
			sessions.setResolved(false);
			return sessions;
		}
	}

	@GetMapping(path = "/stream/stats/entity/session")
	public @ResponseBody EntityStatsSessionResp statsEntitySession(@RequestParam String stream, @RequestParam String ident,
			@RequestParam String date) {
		LocalDate ld = null;
		EntityStatsSessionResp resp = new EntityStatsSessionResp();
		try {
			LocalDate.parse(date,DateTimeFormatter.ofPattern(DunkTime.YYYY_MM_DD));
		} catch (Exception e) {
			resp.setResolved(false);
			resp.setResolveError("Invalid Date Format " + e.toString());
			return resp;
		}
		StreamStats stats = null;
		try {
			 stats = statsService.getStreamStats(stream);
		} catch (Exception e) {
			resp.setResolved(false);
			resp.setResolveError("Stream Stats" + stream + " not found");
		}
		StreamStatsEntity entity = null;
		try {
			entity = stats.getEntity(ident);
		} catch (Exception e) {
			resp.setResolved(false);
			resp.setResolveError("Entity " + ident + " stats does not exist");
			return resp;
		}
		if(entity.sessionExists(ld) == false) { 
			resp.setResolved(false);
			resp.setResolveError("Entity Session for Date " + date + " does not exist");
			return resp;
		}
		try {
			resp.setSession(entity.getSession(ld));
			resp.setResolved(true);
		} catch (Exception e) {
			resp.setResolved(false);
			resp.setResolveError("Not sure get session on entity failed but checked " + e.toString());
			return resp;
		}
		
		
		return null;
	}

	
}

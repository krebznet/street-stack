package com.dunkware.trade.service.stream.server.stats.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.sequence.MongoSequenceService;
import com.dunkware.trade.service.stream.server.stats.StreamStats;
import com.dunkware.trade.service.stream.server.stats.StreamStatsEntity;
import com.dunkware.trade.service.stream.server.stats.repository.EntityStatsSessionDoc;
import com.dunkware.xstream.core.stats.StreamStatsPayload;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.mongodb.bulk.BulkWriteResult;

public class StreamStatsImpl implements StreamStats {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker sessionstats = MarkerFactory.getMarker("stream.stats.session");
	
	
	@Autowired
	private MongoOperations mongoTemplate;
	
	@Autowired
	private MongoSequenceService sequenceService;
	

	private StreamController stream; 
	private Map<String,StreamStatsEntity> entities = new ConcurrentHashMap<String,StreamStatsEntity>();

	public void init(StreamController stream) throws Exception { 
		this.stream = stream; 
		// Load the EntityStatsSessionDocs
		List<EntityStatsSessionDoc> results = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("stream").is(stream.getName()));
			results = mongoTemplate.find(query, EntityStatsSessionDoc.class);
		} catch (Exception e) {
			logger.error(sessionstats, "Exception Querying EntitySessionStats " + e.toString());
			throw e;
		}
		// Now Create the StreamStats Entities 
		Map<String,List<EntityStatsSession>> sessionMap = new ConcurrentHashMap<String, List<EntityStatsSession>>();
		for (EntityStatsSessionDoc entityStatsSessionDoc : results) {
			EntityStatsSession session = StreamStatsHelper.buildEntityStatsSession(entityStatsSessionDoc);
			List<EntityStatsSession> sessions = sessionMap.get(session.getIdent());
			if(sessions == null) { 
				sessions = new ArrayList<EntityStatsSession>();
			}
			sessions.add(session);
			sessionMap.put(session.getIdent(), sessions);
		}
		for (String entityIdent : sessionMap.keySet()) {
			List<EntityStatsSession> sess = sessionMap.get(entityIdent);
			try {
				StreamStatsEntityImpl ent = new StreamStatsEntityImpl();
				ent.init(StreamStatsImpl.this,0, entityIdent, sess);
				entities.put(entityIdent, ent);
			} catch (Exception e) {
				logger.error(sessionstats, "Exception Creating StreamStatsEntity " + e.toString());
			}
		}
		
	}

	@Override
	public void payload(StreamStatsPayload payload) {
		
		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,
				EntityStatsSessionDoc.class);
		
		List<EntityStatsSessionDoc> docs = new ArrayList<EntityStatsSessionDoc>();
		
		for (EntityStatsSession entityStats : payload.getEntities()) {
			try {
				docs.add(StreamStatsHelper.buildEntityStatsSessionDoc(entityStats, sequenceService));
			} catch (Exception e) {
				logger.error(sessionstats, "Exception Converting EntitySessionStats to Mongo Entity " + e.toString());
			}
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
			logger.error(sessionstats, "Exception inserting Stream Stats Payload into mongo  " + e.toString(),e);
		}
		watch.stop();
		if(logger.isDebugEnabled()) { 
			StringBuilder builder = new StringBuilder();
			builder.append("Stats pushed for " + payload.getStreamIdent() + ", "  + docs.size() + " entities inserted into db " + watch.getCompletedSeconds() + "secs");
			logger.debug(sessionstats,builder.toString());
		}
		
		// now lets update our entities with new stats or create any entities that
		// might not have been created during startup if no stats were found. 
		
		for (EntityStatsSession entityStatsSession : payload.getEntities()) {
			StreamStatsEntityImpl entImpl = null;
			if(entities.get(entityStatsSession.getIdent()) == null) { 
				entImpl = new StreamStatsEntityImpl();
				List<EntityStatsSession> sessions = new ArrayList<EntityStatsSession>();
				sessions.add(entityStatsSession);
				entImpl.init(this, 0, entityStatsSession.getIdent(), sessions);
				entities.put(entityStatsSession.getIdent(), entImpl);
			} else { 
				entImpl = (StreamStatsEntityImpl)entities.get(entityStatsSession.getIdent());
				entImpl.addSession(entityStatsSession);
				this.entities.put(entityStatsSession.getIdent(), entImpl);
			}
			
		}
	}

	public void setStream(StreamController stream) {
		this.stream = stream;
	}

	public void setEntities(Map<String, StreamStatsEntity> entities) {
		this.entities = entities;
	}

	@Override
	public StreamController getStream() {
		return stream; 
	}

	@Override
	public StreamStatsEntity getEntity(String ident) throws Exception {
		if(entities.get(ident) == null) { 
			throw new Exception("Entity Stats " + ident + " not found");
		}
		return entities.get(ident);
	}

	@Override
	public Collection<StreamStatsEntity> getEntities() {
		return entities.values();
	}

	@Override
	public boolean entityExists(String ident) {
		if(entities.containsKey(ident)) { 
			return true; 
		}
		return false; 
	}
	
	
	
	
	

	
	
	
	

}

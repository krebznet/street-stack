package com.dunkware.trade.net.service.streamstats.server.service.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
import com.dunkware.trade.net.service.streamstats.server.repository.EntityStatsSessionDoc;
import com.dunkware.trade.net.service.streamstats.server.repository.EntityStatsSessionRepo;
import com.dunkware.trade.net.service.streamstats.server.sequence.MongoSequenceService;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStats;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsEntity;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.StreamStatsPayload;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCursor;

public class StreamStatsImpl implements StreamStats {

	private Marker marker = MarkerFactory.getMarker("streamstats");
	private Logger logger = LoggerFactory.getLogger(getClass());

	private BlockingQueue<PayloadWrapper> payloads = new LinkedBlockingQueue<PayloadWrapper>();
	private PayloadInserter payloadInserter;

	@Autowired
	private MongoOperations mongoTemplate;

	@Autowired
	private MongoSequenceService sequenceService;
	
	@Autowired
	private EntityStatsSessionRepo repo1; 

	private String streamIdent;
	private Map<String, StreamStatsEntity> entities = new ConcurrentHashMap<String, StreamStatsEntity>();

	public void init(String streamIdent) throws Exception {

		this.streamIdent = streamIdent;
		payloadInserter = new PayloadInserter();

		payloadInserter.start();

		try {
			DStopWatch watch = DStopWatch.create();
			watch.start();
			logger.debug(marker,"Starting stat query");
			Map<String, List<EntityStatsSession>> sessionMap = new ConcurrentHashMap<String, List<EntityStatsSession>>();
			Stream<EntityStatsSessionDoc> stream = repo1.findAll().parallelStream();
			

			AtomicInteger count = new AtomicInteger(0);
			AtomicInteger counter = new AtomicInteger(0);
			
			stream.forEach(doc -> {
				EntityStatsSession session = StreamStatsHelper.buildEntityStatsSession(doc);
				List<EntityStatsSession> sessions = sessionMap.get(session.getIdent());
				if (sessions == null) {
					sessions = new ArrayList<EntityStatsSession>();
				}
				sessions.add(session);
				sessionMap.put(session.getIdent(), sessions);
				counter.incrementAndGet();
		        
		    });
			
			logger.debug(marker, "Finished stats query" );
			
		
	
			for (String entityIdent : sessionMap.keySet()) {
				List<EntityStatsSession> sess = sessionMap.get(entityIdent);
				try {
					StreamStatsEntityImpl ent = new StreamStatsEntityImpl();
					ent.init(StreamStatsImpl.this, 0, entityIdent, sess);
					entities.put(entityIdent, ent);
				} catch (Exception e) {
					logger.error(marker, "Exception Creating StreamStatsEntity " + e.toString());
				}
			}

		} catch (Exception e) {
			logger.error(marker, "Exception Querying Entitymarker " + e.toString());
			throw e;
		}

	}

	@Override
	public void payload(final StreamStatsPayload payload) {
		PayloadWrapper wrapper = new PayloadWrapper();
		wrapper.payload = payload;
		payloads.add(wrapper);

	}

	public void setEntities(Map<String, StreamStatsEntity> entities) {
		this.entities = entities;
	}

	@Override
	public String getStreamIdent() {
		return streamIdent;
	}

	@Override
	public StreamStatsEntity getEntity(String ident) throws Exception {
		if (entities.get(ident) == null) {
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
		if (entities.containsKey(ident)) {
			return true;
		}
		return false;
	}

	/**
	 * Run from another thread
	 * 
	 * @param payload
	 */
	private void processPayload(StreamStatsPayload payload) {
		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, EntityStatsSessionDoc.class);

		List<EntityStatsSessionDoc> docs = new ArrayList<EntityStatsSessionDoc>();

		for (EntityStatsSession entityStats : payload.getEntities()) {
			try {
				docs.add(StreamStatsHelper.buildEntityStatsSessionDoc(entityStats, sequenceService));
			} catch (Exception e) {
				logger.error(marker, "Exception Converting Entitymarker to Mongo Entity " + e.toString());
			}
		}

		DStopWatch watch = DStopWatch.create();
		watch.start();
		bulkOps.insert(docs);
		try {
			BulkWriteResult results = bulkOps.execute();
			if (results.getInsertedCount() < docs.size()) {
				logger.error(marker, "Stats Bulk insert result count is " + results.getInsertedCount()
						+ " doc list size is " + docs.size());
			}
		} catch (Exception e) {
			logger.error(marker, "Exception inserting Stream Stats Payload into mongo  " + e.toString(), e);
		}
		watch.stop();
		if (logger.isDebugEnabled()) {
			StringBuilder builder = new StringBuilder();
			builder.append("Stats pushed for " + payload.getStreamIdent() + ", " + docs.size()
					+ " entities inserted into db " + watch.getCompletedSeconds() + "secs");
			logger.debug(marker, builder.toString());
		}

		// now lets update our entities with new stats or create any entities that
		// might not have been created during startup if no stats were found.

		for (EntityStatsSession entityStatsSession : payload.getEntities()) {
			StreamStatsEntityImpl entImpl = null;
			if (entities.get(entityStatsSession.getIdent()) == null) {
				entImpl = new StreamStatsEntityImpl();
				List<EntityStatsSession> sessions = new ArrayList<EntityStatsSession>();
				sessions.add(entityStatsSession);
				entImpl.init(this, 0, entityStatsSession.getIdent(), sessions);
				entities.put(entityStatsSession.getIdent(), entImpl);
			} else {
				entImpl = (StreamStatsEntityImpl) entities.get(entityStatsSession.getIdent());
				entImpl.addSession(entityStatsSession);
				this.entities.put(entityStatsSession.getIdent(), entImpl);
			}

		}
	}

	private class PayloadWrapper {

		public StreamStatsPayload payload;
	}

	// not sure here -- okay fine
	private class PayloadInserter extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					PayloadWrapper wrapper = payloads.take();

					processPayload(wrapper.payload);
					logger.info(marker, "inserted payload ");
				} catch (Exception e) {
					logger.error("Exception in payload inserted");
				}
			}

		}
	}

	
	@Override
	public EntityStatResp entityStatRequest(EntityStatReq req) {
		// okay so here we go we want the goods right. 
		// 
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

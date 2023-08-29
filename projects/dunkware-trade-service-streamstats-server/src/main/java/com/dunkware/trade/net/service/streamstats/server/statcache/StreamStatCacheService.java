package com.dunkware.trade.net.service.streamstats.server.statcache;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.common.mongo.DMongoClient;
import com.dunkware.common.mongo.DMongoCollection;
import com.dunkware.common.mongo.DMongoDatabase;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.xstream.model.stats.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

@Service
public class StreamStatCacheService {

	private DMongoClient mongoClient;
	private DMongoDatabase mongoDatabase;
	private DMongoCollection mongoEntityStatSessions;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Marker marker = MarkerFactory.getMarker("statscache");
	
	private Map<String,StreamStatCache> streamStats = new ConcurrentHashMap<String,StreamStatCache>();

	private StreamStatCacheServiceBean bean;
	
	
	public static void main(String[] args) {
		double discountedPrice = 0;
		double price = 1000.0;
		double discount = 105;
		discountedPrice = (discount * 100) / price;
		System.out.println(discountedPrice);
	
	}
	@PostConstruct
	private void load() {
		
		
		logger.info("Can you see me?");
		bean = new StreamStatCacheServiceBean();
		bean.setLoaded(false);
		
		int batchSize = Integer.valueOf(System.getProperty("batchsize"));
		Thread runner = new Thread() {

				
			DStopWatch watch = DStopWatch.create();

			public void run() {
				LocalTime start = LocalTime.now();
				try {
					
					watch.start();
					logger.debug(marker, "Connecting to Mongo");
					mongoClient = DMongoClient.connect("mongodb://data2.dunkware.net:27017");
					mongoDatabase = mongoClient.getDatabase("dunkstreet");
					mongoEntityStatSessions = mongoDatabase.getCollection("stream_stats_entity_session");
				//	mongoEntityStatSessions.get().withReadConcern(ReadConcern.LOCAL);
					
					watch.stop();
					logger.debug(marker, "Connected To Mongo " + watch.getCompletedSeconds());
				} catch (Exception e) {
					logger.error(marker, "Fatal connection to mongo failed " + e.toString());
					System.exit(-1);
				}
				logger.debug(marker, "Query Entity Stats");
				watch.start();
				BasicDBObject query = new BasicDBObject();
				query.put("stream", "us_equity");
				Bson projection = fields(exclude("vars.id","vars.lowTime","vars.highTime"),excludeId());
			//	Bson filter = Filters.or(Filters.eq("ident", "AAPL"),Filters.eq("ident", "BAC"),Filters.eq("ident","JPM"));
				long docSize = mongoEntityStatSessions.get().countDocuments();
				logger.info(marker, "cache collection size " + docSize);
				watch.stop();
				
				bean.setDocuments(docSize);
				bean.setCountTime((long)watch.getCompletedSeconds());
				watch.start();
				logger.info(marker, "starting cache query");
				MongoCursor<Document> docs = mongoEntityStatSessions.get().find(query).batchSize(batchSize).projection(projection).cursor();
			
				int counter = 0;
				int countme = 0;
				int loggercounter =0;
				LocalTime start2 = LocalTime.now();
				
				while(docs.hasNext()) {
					Document doc = docs.next();
					countme++;
					counter++;
					loggercounter++;
					if(loggercounter == 10000) { 
						logger.info(marker, "loaded 10K cache docs");
						loggercounter = 0;
					}
					if(countme == 1000) { 
						bean.setLoadBatch(Duration.between(start2, LocalTime.now()).toMillis());
						start2 = LocalTime.now();
						double percentDone = (counter * 100) / bean.getDocuments();
						bean.setCompleted(String.valueOf(percentDone));
						countme = 0;
					}
					bean.setLoadCount(bean.getLoadCount() + 1);
					
					String streamIdent = doc.getString("stream");
					StreamStatCache streamCache = streamStats.get(doc.get("stream"));
					if(streamCache == null) { 
						streamCache = new StreamStatCache();
						streamStats.put(streamIdent,streamCache);
					}
					
					streamCache.consumeEntitySession(doc);
				
				}
				long loadTime = Duration.between(start, LocalTime.now()).toSeconds();
				logger.info(marker,"completed cache query");
				bean.setLoaded(true);
				bean.setLoadTime(loadTime);
			}
		};

		runner.start();

	}
	
	
	public StreamStatCacheServiceBean getBean() { 
		return bean;
	}
	
	
	public StreamStatCache getStream(String stream) throws Exception { 
		StreamStatCache cache = streamStats.get(stream);
		if(cache == null) { 
			throw new Exception("Stream Stat Cache Not Found For Stream " + stream);
		}
		return cache;
	}
	
	
	public EntityStatBulkResp entityBulkStat(EntityStatBulkReq req) { 
		StreamStatCache cache = streamStats.get(req.getStream());
		if(cache == null) { 
			EntityStatBulkResp resp = new EntityStatBulkResp();
			resp.setSuccess(false);
			resp.setException("Stream Stats Cache not found for stream " + req.getStream());
			return resp;
		}
		return cache.getBulkStat(req);
	}
	
	public EntityStatResp entityStat(EntityStatReq req) { 
		StreamStatCache cache = streamStats.get(req.getStream());
		if(cache == null) { 
			return EntityStatRespBuilder.newInstance().exception("Stream stat cache not found for stream " + req.getStream()).build();
		}
		return cache.getStat(req);
	}
	
	
	

}

package com.dunkware.trade.net.service.streamstats.server.statcache;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static com.mongodb.client.model.Projections.*;

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
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.ReadConcern;
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
		bean = new StreamStatCacheServiceBean();
		bean.setLoaded(false);
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
					mongoEntityStatSessions.get().withReadConcern(ReadConcern.LOCAL);
					
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
				//query.put("ident", "AAPL");
				Bson projection = fields(exclude("vars.id","vars.lowTime","vars.highTime"),excludeId());
				
				long docSize = mongoEntityStatSessions.get().countDocuments();
				watch.stop();
				
				bean.setDocuments(docSize);
				bean.setCountTime((long)watch.getCompletedSeconds());
				watch.start();
				MongoCursor<Document> docs = mongoEntityStatSessions.get().find(query).batchSize(1000).projection(projection).cursor();
			
				int counter = 0;
				int countme = 0;
				LocalTime start2 = LocalTime.now();
				
				while(docs.hasNext()) {
					Document doc = docs.next();
					countme++;
					counter++;
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
				bean.setLoaded(true);
				bean.setLoadTime(loadTime);
			}
		};

		runner.start();

	}
	
	
	public StreamStatCacheServiceBean getBean() { 
		return bean;
	}
	
	
	public EntityStatResp entityStat(EntityStatReq req) { 
		StreamStatCache cache = streamStats.get(req.getStream());
		if(cache == null) { 
			return EntityStatRespBuilder.newInstance().exception("Stream stat cache not found for stream " + req.getStream()).build();
		}
		return cache.getStat(req);
	}
	
	
	

}

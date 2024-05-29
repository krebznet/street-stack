package com.dunkware.stream.data.stats.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.cassy.repository.session.StreamSessionRepo;
import com.dunkware.utils.redis.JedisPoolBuilder;

import jakarta.annotation.PostConstruct;
import redis.clients.jedis.JedisPool;

/**
 * It will query the session records for all streams, break the sessions down by
 * stream id and create a EntityStatCache for each unique stream that will
 * multithreaded query session statss and write into redis using naming
 * convention
 */
@Service()
@Profile("EntityStatCacheService")
public class EntityStatCacheService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatCacheService");

	private Map<Integer, EntityStatCache> cacheMap = new ConcurrentHashMap<Integer, EntityStatCache>();

	@Autowired
	private StreamSessionRepo streamSessionRepo;

	@Autowired
	private ApplicationContext ac;
	
	private JedisPool jedisPool;

	@PostConstruct
	public void load() {
		try {
			jedisPool = JedisPoolBuilder.newBuilder().serverAndPort("testrock1.dunkware.net", 32595).build();	
		} catch (Exception e) {
			logger.error(marker, "Jedis Pool Create Exception " + e.toString());
			System.exit(01);
		}
		
		Thread runner = new Thread() {
			
			public void start() {
				try {
					Map<Integer, List<StreamSessionRow>> streamSessions = new ConcurrentHashMap<Integer, List<StreamSessionRow>>();
					List<StreamSessionRow> results = streamSessionRepo.findAll();
					for (StreamSessionRow row : results) {
						if (streamSessions.containsKey(row.getKey().getStream())) {
							streamSessions.get(row.getKey().getStream()).add(row);
						} else {
							List<StreamSessionRow> sessions = new ArrayList<StreamSessionRow>();
							sessions.add(row);
							streamSessions.put(row.getKey().getStream(), sessions);
						}
					}

					for (Integer stream : streamSessions.keySet()) {
						EntityStatCache cache = new EntityStatCache();
						ac.getAutowireCapableBeanFactory().autowireBean(cache);
						cacheMap.put(stream, cache);
						cache.start(streamSessions.get(stream), jedisPool);
					}

				} catch (Exception e) {
					logger.error(marker, "Exception Loading Thread " + e.toString(),e);
				}

			}
		};

		runner.start();

	}

}

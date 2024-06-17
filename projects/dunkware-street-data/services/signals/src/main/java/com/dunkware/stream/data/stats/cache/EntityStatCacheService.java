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
import org.springframework.stereotype.Service;

import com.dunkware.stream.data.cassy.entity.sesion.DBStreamSessionRow;
import com.dunkware.stream.data.cassy.repository.session.StreamSessionRepo;

import jakarta.annotation.PostConstruct;

/**
 * It will query the session records for all streams, break the sessions down by
 * stream id and create a EntityStatCache for each unique stream that will
 * multithreaded query session statss and write into redis using naming
 * convention
 */
@Service()
public class EntityStatCacheService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatCacheService");

	private Map<Integer, EntityStatCacheLoader> cacheMap = new ConcurrentHashMap<Integer, EntityStatCacheLoader>();

	@Autowired
	private StreamSessionRepo streamSessionRepo;

	@Autowired
	private ApplicationContext ac;

	@PostConstruct
	public void load() {
		Thread runner = new Thread() {

			public void start() {
				try {
					Map<Integer, List<DBStreamSessionRow>> streamSessions = new ConcurrentHashMap<Integer, List<DBStreamSessionRow>>();
					List<DBStreamSessionRow> results = streamSessionRepo.findAll();
					for (DBStreamSessionRow row : results) {
						if (streamSessions.containsKey(row.getKey().getStream())) {
							streamSessions.get(row.getKey().getStream()).add(row);
						} else {
							List<DBStreamSessionRow> sessions = new ArrayList<DBStreamSessionRow>();
							sessions.add(row);
							streamSessions.put(row.getKey().getStream(), sessions);
						}
					}

					for (Integer stream : streamSessions.keySet()) {
						EntityStatCacheLoader cache = new EntityStatCacheLoader();
						ac.getAutowireCapableBeanFactory().autowireBean(cache);
						cacheMap.put(stream, cache);
						cache.start(streamSessions.get(stream));
					}

				} catch (Exception e) {
					logger.error(marker, "Exception Loading Thread " + e.toString(),e);
				}

			}
		};

		runner.start();

	}

}

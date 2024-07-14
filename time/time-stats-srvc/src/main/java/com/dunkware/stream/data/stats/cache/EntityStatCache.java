package com.dunkware.stream.data.stats.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.stream.data.cassy.entity.sesion.DBStreamSessionRow;
import com.dunkware.stream.data.cassy.services.CassyQueryService;
import com.dunkware.stream.data.lib.stats.entity.EntityStatHelper;
import com.dunkware.stream.data.model.stats.entity.EntityStatModel;
import com.dunkware.utils.core.stopwatch.StopWatch;
import com.dunkware.utils.redis.injestor.JedisInjestor;

import redis.clients.jedis.JedisPool;

public class EntityStatCache {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatCache");
	
	private BlockingQueue<DBStreamSessionRow> sessionQueue = new LinkedBlockingQueue<DBStreamSessionRow>();
	
	@Autowired
	private CassyQueryService queryService;
	
	private JedisPool pool;
	
	private AtomicInteger completedLoaders = new AtomicInteger(0);
	private List<SessionStatLoader> loaders = new ArrayList<SessionStatLoader>();
	
	private List<DBStreamSessionRow> sessions;
	private AtomicInteger statCounter = new AtomicInteger(0);
	private JedisInjestor injestor;
	
	private EntityStatCacheBean bean;
	
	
	public void start(List<DBStreamSessionRow> sessions, JedisPool pool) {
		this.sessions = sessions;
		bean = new EntityStatCacheBean();
		Metrics metrics = new Metrics();
		metrics.start();
		injestor = JedisInjestor.newInstance(pool);
		sessionQueue.addAll(sessions);
		this.pool = pool;
		for(int i = 0; i < 5; i++) {
			SessionStatLoader loader = new SessionStatLoader();
			loader.start();
			loaders.add(loader);
			
		}
	}
	
	
	
	public EntityStatCacheBean getBean() { 
		bean.setSessions(sessions.size());
		bean.setKeycount(statCounter.get());
		return bean;
		
	}

	
	private class SessionStatLoader extends Thread { 
		
		
		
		public SessionStatLoader() { 
			
		}
		
		public void run() {
			
			while(true) {
				try {
					
					DBStreamSessionRow row = sessionQueue.poll(250, TimeUnit.MILLISECONDS);
					if(row == null) {
						completedLoaders.incrementAndGet();
						return;
					}
					try {
						List<EntityStatModel> results = queryService.sessionStats(row.getKey().getStream(), row.getKey().getDate());
						for (EntityStatModel stat : results) {
							String key = EntityStatHelper.buildStatKey(stat.getStream(), stat.getDate(), stat.getEntity(), stat.getStat(), stat.getElement());
							injestor.injest(key,String.valueOf(stat.getValue()));
							statCounter.incrementAndGet();
						}
					} catch (Exception e) {
						logger.error(marker, "Exception query stats and injesting" + e.toString());
					}
					
				} catch (Exception e) {
						logger.error(marker, "Exception query stats out " + e.toString());
						
				}
				
			}
			
			
		}
	}
	
	private class Metrics extends Thread { 
		
		public void run() { 
			StopWatch watch = StopWatch.newInstance();
			watch.start();
			while(completedLoaders.get() < loaders.size()) { 
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					
				}
			}
			watch.stop();
			bean.setLoaLocalTime(watch.seconds());
			return;
		}
	}

}

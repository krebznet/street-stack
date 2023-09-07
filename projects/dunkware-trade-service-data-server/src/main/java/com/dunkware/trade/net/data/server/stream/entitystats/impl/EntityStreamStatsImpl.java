package com.dunkware.trade.net.data.server.stream.entitystats.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.net.data.server.stream.entitystats.EntityStatsService;
import com.dunkware.trade.net.data.server.stream.entitystats.EntityStreamStats;
import com.dunkware.trade.net.data.server.stream.entitystats.EntityStreamStatsBean;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStreamStatsImpl implements EntityStreamStats {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");
	
	private EntityStatsService entityStatsService; 

	private EntityStreamStatsBean bean; 
	
	private EntityStreamStatsIngestor injestor; 
	
	private MySqlConnectionPool connectionPool;
	private StreamDescriptor descriptor;
	
	public void init(StreamDescriptor descriptor, EntityStatsService entityStatsService) throws Exception { 
		this.descriptor = descriptor;
		this.entityStatsService = entityStatsService;
		try {
			connectionPool = new MySqlConnectionPool(descriptor.getStatDbHost(), descriptor.getStatDbName(), descriptor.getStatDbPort(), descriptor.getStatDbUser(), descriptor.getStatDbPass(), descriptor.getStatDbPoolSize());
			
		} catch (Exception e) {
			throw new Exception("Could not create mysql connection pool for entity stream stats " + e.toString());
		}
		bean = new EntityStreamStatsBean();
	
		EntityStreamStatsHelper.initTables(descriptor.getIdentifier(),getConnectionPool());
		injestor = new EntityStreamStatsIngestor();
		injestor.init(this);
	}
	
	public double deleteAll() throws Exception { 
		Connection cn = null;
		Statement st = null;
		try {
			DStopWatch timer = DStopWatch.create();

			cn = getConnectionPool().getConnectionFromPool();
			st = cn.createStatement();
			st.execute("DELETE FROM " + getStreamIdentifier() + "_entity_stats_session");
			timer.stop();
			return timer.getCompletedSeconds();
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		} finally { 
			if(st != null) { 
				st.close();
			}
			if(cn != null) { 
				getConnectionPool().returnConnectionToPool(cn);
			}
		}
	}
	
	@Override
	public String getStreamIdentifier() {
		return descriptor.getIdentifier();
	}
	
	

	@Override
	public StreamDescriptor getStreamDescriptor() {
		return descriptor;
	}

	@Override
	public MySqlConnectionPool getConnectionPool() {
		return connectionPool;
	}


	@Override
	public int getStreamId() {
		return (int)descriptor.getId();
	}
	
	

	@Override
	public EntityStreamStatsBean getBean() {
		if(injestor != null) { 
			bean.setInsertCount(injestor.getInsertCount());
			bean.setInsertLastSecond(injestor.getInsertCountLastSecond());
			bean.setInsertQueue(injestor.getQueueSize());
			bean.setInsertMaxSecond(injestor.getInsertCountMaxSecond());
		}
		return bean;
	}

	

	@Override
	public void insert(List<EntityStat> stats) throws Exception {
		for (EntityStat entityStat : stats) {
			
			injestor.insert(entityStat);
		}
	}


	

	
}

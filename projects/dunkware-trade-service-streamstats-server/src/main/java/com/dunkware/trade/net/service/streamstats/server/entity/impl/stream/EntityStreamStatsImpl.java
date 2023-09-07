package com.dunkware.trade.net.service.streamstats.server.entity.impl.stream;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.net.service.streamstats.server.entity.EntityStatsService;
import com.dunkware.trade.net.service.streamstats.server.entity.EntityStreamStats;
import com.dunkware.trade.net.service.streamstats.server.entity.EntityStreamStatsBean;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatQuery;
import com.dunkware.xstream.model.stats.entity.EntityStats;

import io.vertx.core.Future;

public class EntityStreamStatsImpl implements EntityStreamStats {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");
	
	private EntityStatsService entityStatsService; 
	private String streamIdentifier; 
	private int streamId; 
	
	private EntityStreamStatsBean bean; 
	
	private EntityStreamStatsIngestor injestor; 
	
	public void init(int streamId, String streamIdentifier, EntityStatsService entityStatsService) throws Exception { 
		bean = new EntityStreamStatsBean();
		this.entityStatsService = entityStatsService;
		this.streamId = streamId;
		this.streamIdentifier = streamIdentifier;
		EntityStreamStatsHelper.initTables(streamIdentifier,entityStatsService.getConnectionPool());
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
		return streamIdentifier;
	}
	
	

	@Override
	public MySqlConnectionPool getConnectionPool() {
		return entityStatsService.getConnectionPool();
	}


	@Override
	public int getStreamId() {
		return streamId;
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


	@Override
	public Future<EntityStats> query(EntityStatQuery query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}

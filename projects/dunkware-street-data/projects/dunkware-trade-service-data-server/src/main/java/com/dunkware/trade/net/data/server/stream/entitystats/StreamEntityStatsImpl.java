package com.dunkware.trade.net.data.server.stream.entitystats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.StatCache;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.loader.StatCacheLoader;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggResponse;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggType;
import com.dunkware.trade.service.data.model.entitystats.EntityStatRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatResponse;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamEntityStatsImpl implements StreamEntityStats {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");

	private StreamEntityStatsProvider entityStatsService;

	private StreamEntityStatsBean bean;

	private StreamEntityStatsIngestorMySql injestor;

	private MySqlConnectionPool connectionPool;
	private StreamDescriptor descriptor;

	private StatCache cache = new StatCache();

	public void init(StreamDescriptor descriptor, StreamEntityStatsProvider entityStatsService) throws Exception {
		logger.info(marker, "Star");
		this.descriptor = descriptor;
		this.entityStatsService = entityStatsService;
		try {
			connectionPool = new MySqlConnectionPool(descriptor.getStatDbHost(), descriptor.getStatDbName(),
					descriptor.getStatDbPort(), descriptor.getStatDbUser(), descriptor.getStatDbPass(),
					descriptor.getStatDbPoolSize());

		} catch (Exception e) {
			throw new Exception("Could not create mysql connection pool for entity stream stats " + e.toString());
		}
		bean = new StreamEntityStatsBean();

		StreamEntityStatsHelper.initTables(descriptor.getIdentifier(), getConnectionPool());
		injestor = new StreamEntityStatsIngestorMySql();
		injestor.init(this);
		//StatCacheLoader cacheBuilder = new StatCacheLoader(this);
		//cacheBuilder.start();
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
			if (st != null) {
				st.close();
			}
			if (cn != null) {
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
		return (int) descriptor.getId();
	}

	@Override
	public StreamEntityStatsBean getBean() {
		if (injestor != null) {
			bean.setInsertCount(injestor.getInsertCount());
			bean.setInsertLastSecond(injestor.getInsertCountLastSecond());
			bean.setInsertQueue(injestor.getQueueSize());
			bean.setInsertMaxSecond(injestor.getInsertCountMaxSecond());
		}
		return bean;
	}

	@Override
	public void persist(List<EntityStat> stats) throws Exception {
		for (EntityStat entityStat : stats) {

			injestor.insert(entityStat);
		}
	}

	@Override
	public StatCache getCache() {
		return cache;
	}

	@Override
	public EntityStatResponse statRequest(EntityStatRequest req) {
		//
		EntityStatResponse resp = new EntityStatResponse();
		List<EntityStat> stats = new ArrayList<EntityStat>();
		Connection cn = null;
		Statement st = null;
		DStopWatch timer = DStopWatch.create();
		try {
			timer.start();
			cn = connectionPool.getConnectionFromPool();
			st = cn.createStatement();
			ResultSet rs = st
					.executeQuery(StreamEntityStatsHelper.sqlQueryEntityStatRequest(getStreamIdentifier(), req));
			while (rs.next()) {
				EntityStat stat = StreamEntityStatsHelper.toEntityStat(rs);
				stats.add(stat);

			}
			timer.stop();
			resp.setTime(timer.getCompletedSeconds());
			resp.setValues(stats);
			return resp;

		} catch (Exception e) {
			resp.setException(e.toString());
			resp.setTime(0);
			return resp;
		} finally {
			timer = null;
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e2) {

			}

			if (cn != null) {

				connectionPool.returnConnectionToPool(cn);
			}
		}

	}

	@Override
	public EntityStatAggResponse statAgg(EntityStatAggRequest req) {
		EntityStatAggResponse response = new EntityStatAggResponse();

		LocalDate date = req.getDate();
		List<EntityStat> results = cache.getStatsHistoricalRelative(date, req.getDaysBack(), true, req.getEntity(),
				req.getStat(), req.getElement());
		if (results == null) {
			response.setResolved(false);
			return response;
		}

		Number high = 0;
		LocalTime highTime = null;
		LocalDate highDate = null;
		boolean init = false;
		for (EntityStat stat : results) {
			if (init == false) {
				high = stat.getValue();
				highTime = stat.getTime();
				highDate = stat.getDate();
				init = true;
			} else {
				if (DNumberHelper.isFirstGreater(stat.getValue(), high)) {
					high = stat.getValue();
					highTime = stat.getTime();
					highDate = stat.getDate();
				}
			}
		}

		response.setDate(highDate);
		response.setTime(highTime);
		response.setResolved(true);
		response.setValue(high);

		return response;
	}

}

package com.dunkware.trade.net.data.server.stream.entitystats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;
import com.dunkware.stream.cluster.proto.controller.messages.StreamSessionStopping;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.StatCache;
import com.dunkware.trade.net.data.server.stream.entitystats.repository.EntityStatsEnt;
import com.dunkware.trade.net.data.server.stream.entitystats.repository.EntityStatsRepo;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggResponse;
import com.dunkware.trade.service.data.model.entitystats.EntityStatRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatResponse;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamEntityStatsImpl implements StreamEntityStats {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStats");

	private StreamEntityStatsProvider entityStatsService;
	
	@Autowired
	private EntityStatsRepo repo;
	
	@Value("${config.mount.entity.stats}")
	private String entitySatsDirectory; 

	private StreamEntityStatsBean bean;
	
	@Autowired
	private DunkNet dunkNet;


	private MySqlConnectionPool connectionPool;
	private StreamDescriptor descriptor;

	private StatCache cache = new StatCache();
	
	private List<EntityStatsEnt> sessionstatRecords = new ArrayList<EntityStatsEnt>();

	public void init(StreamDescriptor descriptor, StreamEntityStatsProvider entityStatsService) throws Exception {
		logger.info(marker, "Star");
		this.descriptor = descriptor;
		this.entityStatsService = entityStatsService;
		dunkNet.extensions().addExtension(this);
		
		
		  try { connectionPool = new MySqlConnectionPool(descriptor.getStatDbHost(),
		  descriptor.getStatDbName(), descriptor.getStatDbPort(),
		  descriptor.getStatDbUser(), descriptor.getStatDbPass(),
		  descriptor.getStatDbPoolSize());
		  
		  } catch (Exception e) { throw new
		  Exception("Could not create mysql connection pool for entity stream stats " +
		  e.toString()); }
		 
		bean = new StreamEntityStatsBean();

		// so lets load the sessions
		this.sessionstatRecords = repo.findByStreamIdent(descriptor.getIdentifier());
		
	}
	
	
	public boolean statsRecordExistsForDate(LocalDate date) { 
		for (EntityStatsEnt entityStatsEnt : sessionstatRecords) {
			if(entityStatsEnt.getDate().isEqual(date)) { 
				return true; 
			}
		}
		return false; 
	}
	
	
	// jps data_entity_stats_capture
	
	
	@ADunkNetEvent()
	public void onStreamSessionStopping(StreamSessionStopping stopping) { 
		if(stopping.getIdentifier().equals(getStreamDescriptor().getIdentifier())) { 
			Thread runner = new Thread() { 
				
				public void run() { 
				//	if(statsRecordExistsForDate(stopping.getStartTime().toLocalDate())) { 
					//	logger.info(marker, "Skipping Entity Stats Session File Write Because Session Stats File Exists for same date ");
					//}
					// 
					EntityStatsEnt record = new EntityStatsEnt();
					record.setDate(stopping.getStartTime().toLocalDate());
					record.setSessionId(stopping.getSessionId());
					record.setStreamIdent(stopping.getIdentifier());
					record.setStreamId(stopping.getId());
					record.setStartDateTime(stopping.getStartTime());;
					record.setStopDateTime(stopping.getStopTime());
					record.setSessionFileDirectory(entitySatsDirectory);
					String startTimeFormat = DunkTime.format(record.getStartDateTime(), DunkTime.YYMMDDHHMMSS);
					String stopTimeFormat = DunkTime.format(record.getStopDateTime(), DunkTime.YYMMDDHHMMSS);
					String fileName = descriptor.getIdentifier().toUpperCase() + "_" + startTimeFormat + "_" + stopTimeFormat + ".csv";
					record.setSessionFile(fileName);;
					
					
					try {
						repo.save(record);
					} catch (Exception e) {
						logger.error(marker, "Exception saving StreamStatsEnt record " + e.toString());
						
					}
					
					StreamEntityStatsFileWriter writer = new StreamEntityStatsFileWriter();
					writer.init(record, StreamEntityStatsImpl.this, new StreamEntityStatsFileWriterListener() {
						
						@Override
						public void onException(StreamEntityStatsFileWriter writer) {
							logger.error(marker, "Exception Writing Entity Stats " + writer.getError());
							record.setException(true);
							record.setExceptionMessage(writer.getError());
							repo.save(record);
						}
						
						@Override
						public void onComplete(StreamEntityStatsFileWriter writer) {
							record.setException(false);
							record.setInsertCount(writer.getInsertCount());
						}
					});
				}
			};
			runner.start();
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
		return null;
		/*
		 * EntityStatAggResponse response = new EntityStatAggResponse();
		 * 
		 * LocalDate date = req.getDate(); List<EntityStat> results =
		 * cache.getStatsHistoricalRelative(date, req.getDaysBack(), true,
		 * req.getEntity(), req.getStat(), req.getElement()); if (results == null) {
		 * response.setResolved(false); return response; }
		 * 
		 * Number high = 0; LocalTime highTime = null; LocalDate highDate = null;
		 * boolean init = false; for (EntityStat stat : results) { if (init == false) {
		 * high = stat.getValue(); highTime = stat.getTime(); highDate = stat.getDate();
		 * init = true; } else { if (DNumberHelper.isFirstGreater(stat.getValue(),
		 * high)) { high = stat.getValue(); highTime = stat.getTime(); highDate =
		 * stat.getDate(); } } }
		 * 
		 * response.setDate(highDate); response.setTime(highTime);
		 * response.setResolved(true); response.setValue(high);
		 * 
		 * return response;
		 */
	}

}

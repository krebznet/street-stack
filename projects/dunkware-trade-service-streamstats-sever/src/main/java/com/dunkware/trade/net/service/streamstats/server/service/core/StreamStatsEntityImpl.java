package com.dunkware.trade.net.service.streamstats.server.service.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStats;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsEntity;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsInternalException;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsResolveException;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsAggVar;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;
import com.dunkware.xstream.model.stats.EntityStatsSessions;
import com.dunkware.xstream.model.stats.comparator.EntityStatsSessionDateComparator;

/**
 * This will just work off of stat model objects nothing mongo db related
 * @author Duncan Krebs 
 *
 */
public class StreamStatsEntityImpl implements StreamStatsEntity {

	private int id; 
	private String ident; 
	
	private EntityStatsAgg statsAgg = null; 
	
	private List<EntityStatsSession> sessions = new ArrayList<EntityStatsSession>();
	
	private StreamStats streamStats; 
	
	public void init(StreamStats streamStats, int entityId, String entityIdent, List<EntityStatsSession> sessions) {
		this.id = entityId;
		this.streamStats = streamStats;
		this.ident = entityIdent;
		this.sessions = sessions;
		// we assum this will put the most recent date first
		Collections.sort(this.sessions, EntityStatsSessionDateComparator.recentFirst());
		// build the agg 
		//this.sessions = sessions.sort(new EntityStatsSessionDateComparator);
		buildAgg();
	}
	
	@Override
	public int getId() {
		return id; 
	}

	@Override
	public String getIdent() {
		return ident; 
	}

	@Override
	public int getSessionCount() {
		return sessions.size();
	}

	@Override
	public EntityStatsAgg getAgg() {
		return statsAgg;
	}

	@Override
	public EntityStatsSessions getSessions() {
		EntityStatsSessions sessionsWrapper = new EntityStatsSessions();
		sessionsWrapper.setSessions(sessions);
		return sessionsWrapper;
	}

	@Override
	public void addSession(EntityStatsSession session) {
		this.sessions.add(session);
	}
	
	
	private void buildAgg() { 
		EntityStatsAgg agg = new EntityStatsAgg();
		agg.setStream(streamStats.getStreamIdent());
		LocalDate startDate = null; 
		LocalDate endDate = null;
		agg.setSessions(sessions.size());
		
		Map<String,EntityStatsAggVar> vars = new ConcurrentHashMap<String,EntityStatsAggVar>();
		for (EntityStatsSession doc : sessions) {
			if(startDate == null && endDate == null) { 
				startDate = doc.getDate();
				endDate = doc.getDate();
			}
			if(doc.getDate().isAfter(endDate)) { 
				endDate = doc.getDate();
			}
			if(doc.getDate().isBefore(startDate)) { 
				startDate = doc.getDate();
			}
			for (EntityStatsSessionVar var : doc.getVars()) {
				EntityStatsAggVar aggVar = vars.get(var.getIdent());
				if(aggVar == null) { 
					aggVar = new EntityStatsAggVar();
					aggVar.setSessionCount(1);
					aggVar.setIdent(var.getIdent());
					aggVar.setHigh(var.getHigh());
					aggVar.setLow(var.getLow());
					aggVar.setHighDateTime(var.getHighDateTime());
					aggVar.setLowDateTime(var.getLowDateTime());
					aggVar.setValueCount(var.getValueCount());
					vars.put(var.getIdent(), aggVar);
				} else { 
					aggVar.setSessionCount(aggVar.getSessionCount() + 1);
					aggVar.setValueCount(aggVar.getValueCount() + var.getValueCount());
					if(var.getHigh().doubleValue() > aggVar.getHigh().doubleValue()) { 
						aggVar.setHigh(var.getHigh());
						aggVar.setHighDateTime(var.getHighDateTime());
					}
					if(var.getLow().doubleValue() < aggVar.getLow().doubleValue()) {
						aggVar.setLow(var.getLow());
						aggVar.setLowDateTime(var.getLowDateTime());
					}
					vars.put(var.getIdent(), aggVar);
				}
			}
		}
		
		agg.setEnd(endDate);
		agg.setStart(startDate);
		agg.setId(id);
		agg.setIdent(ident);
		agg.setDays((int)DunkTime.daysBetween(startDate, endDate));
		ArrayList<EntityStatsAggVar> newList = new ArrayList<EntityStatsAggVar>(vars.values());
		Collections.sort(newList, new Comparator<EntityStatsAggVar>() {

			@Override
			public int compare(EntityStatsAggVar o1, EntityStatsAggVar o2) {
				return o1.getIdent().compareTo(o2.getIdent());
			}
			
		}	);
		
		agg.setVars(newList);
		this.statsAgg = agg; 
	}

	@Override
	public boolean sessionExists(LocalDate date) {
		for (EntityStatsSession session : sessions) {
			if(session.getDate().isEqual(date)) { 
				return true; 
			}
		}
		return false;
	}

	@Override
	public EntityStatsSession getSession(LocalDate date) throws Exception {
		for (EntityStatsSession session : sessions) {
			if(session.getDate().isEqual(date)) { 
				return session;
			}
		}
		throw new Exception("Entity Stats Session not found for " + DunkTime.format(date, DunkTime.YYYY_MM_DD));
	}

	
	@Override
	public Number resolveVarRelativeHigh(LocalDate date, String varIdent, int daysBack) throws StreamStatsInternalException, StreamStatsResolveException {
		List<EntityStatsSession> samples = new ArrayList<EntityStatsSession>();
		for (EntityStatsSession session : sessions) {
			if(samples.size() == daysBack) { 
				break;
			}
			if(session.getDate().isBefore(date)) { 
				samples.add(session);
			}
		}
		if(samples.size() < daysBack) { 
			throw new StreamStatsResolveException("Not enough days of stats found for " + varIdent );
		}
		Double high = samples.get(0).getVar(varIdent).getHigh().doubleValue();
		for (EntityStatsSession sess : samples) {
			if(sess.getVar(varIdent).getHigh().doubleValue() > high) { 
				high = sess.getVar(varIdent).getHigh().doubleValue();
			}
		}
		
		System.out.println("high is " + high);
		for (EntityStatsSession entityStatsSession : samples) {
			System.out.println(entityStatsSession.getDate().toString() + " " + entityStatsSession.getVar(varIdent).getHigh());
		}
		return high;
	
	}

	@Override
	public EntityStatResp statRequest(EntityStatReq req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	

	
}

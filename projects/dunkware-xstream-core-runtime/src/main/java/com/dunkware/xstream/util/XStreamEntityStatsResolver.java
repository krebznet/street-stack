package com.dunkware.xstream.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.xstream.api.XStreamResolveException;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;

public class XStreamEntityStatsResolver {
	
	
	public static XStreamEntityStatsResolver newInstance(List<EntityStatsSession> sessions) {
		return new XStreamEntityStatsResolver(sessions);
	}
	
	private List<EntityStatsSession> sessions;
	
	private Map<String,List<EntityStatsSessionVar>> varSessions = new ConcurrentHashMap<String,List<EntityStatsSessionVar>>();
	
	private XStreamEntityStatsResolver(List<EntityStatsSession> sessions) {
		this.sessions = sessions;
		for (EntityStatsSession entityStatsSession : sessions) {
			for (EntityStatsSessionVar varStats : entityStatsSession.getVars()) {
				List<EntityStatsSessionVar> varStatList = varSessions.get(varStats.getIdent());
				if(varStatList == null) { 
					varStatList = new ArrayList<EntityStatsSessionVar>();
				}
				varStatList.add(varStats);
				varSessions.put(varStats.getIdent(), varStatList);
			}
		}
	}
	
	public int getSessionCount() { 
		return sessions.size();
	}
	
	public boolean canResolveRelativeHigh(String ident, int daysBack) { 
		List<EntityStatsSessionVar> varStats = varSessions.get(ident);
		if(varStats == null) { 
			return false; 
		}
		if(varStats.size() < daysBack) { 
			return false; 
		}
		return true; 
	}
	
	public Number resolveRelativeVarHigh(String ident, int daysBack) throws XStreamResolveException { 
		List<EntityStatsSessionVar> varStats = varSessions.get(ident);
		
		if(varStats.size() == 0) { 
			throw new XStreamResolveException("Var Stats Size is 0");
		}
		if(varStats.size() < daysBack) {
			throw new XStreamResolveException("Var Stats size " + varStats.size() + " less than " + daysBack);
		}
		Number high = -100.00;
		int counter =  0; 
		for (EntityStatsSessionVar statsSession : varStats) {
			if(DNumberHelper.compare(statsSession.getHigh(), high) == 1) { 
				high = statsSession.getHigh();
			}
			counter++;
			if(counter == daysBack) { 
				break;
			}
		}
		return high;
		
	}

}

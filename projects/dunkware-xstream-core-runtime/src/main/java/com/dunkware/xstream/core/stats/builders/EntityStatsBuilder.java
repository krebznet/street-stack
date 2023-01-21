package com.dunkware.xstream.core.stats.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.stats.StreamStatsExt;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.model.stats.EntityVarStats;

public class EntityStatsBuilder  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	
	public static EntityStatsBuilder newInstance(StreamStatsExt ext, XStreamRow row) { 
		return new EntityStatsBuilder(ext,row);
	}
	
	
	private ConcurrentHashMap<String, EntityVarStatsBuilder> varStats = new ConcurrentHashMap<String,EntityVarStatsBuilder>();
	private EntityStats stats = new EntityStats();
	private XStream stream; 
	private XStreamRow row; 
	
	private EntityStatsBuilder(StreamStatsExt ext, XStreamRow row) { 
		this.stream = row.getStream();
		this.row = row; 
		stats.setFrom(row.getStream().getClock().getLocalDateTime());
		stats.setEntityId(row.getIdentifier());
		stats.setEntityIdent(row.getId());
		stats.setStreamIdent(stream.getInput().getIdentifier()); 
		stats.setSessionId(stream.getInput().getSessionId());
		// for each variable build a EntityVarStats builder
		for (XStreamVar var : row.getVars()) {
			EntityVarStatsBuilder varStatBuilder = EntityVarStatsBuilder.newInstance(var);
			this.varStats.put(var.getVarType().getName(), varStatBuilder);
		}
	}
	
	/**
	 * This will call dispose on all the var stat builders 
	 * and also remove its listener resources
	 */
	public EntityStats dispose() { 
		List<EntityVarStats> statList = new ArrayList<EntityVarStats>();
		for (EntityVarStatsBuilder builder : varStats.values()) {
			statList.add(builder.dispose());
		}
		stats.setTo(stream.getClock().getLocalDateTime());
		return stats;
	}

	public EntityStats getStats() { 
		stats.setTo(stream.getClock().getLocalDateTime());
		stats.getVarStats().clear();
		for (EntityVarStatsBuilder varStatBuilder : varStats.values()) {
			stats.getVarStats().add(varStatBuilder.getStats());
		}
		return stats; 
	}
	
	

}

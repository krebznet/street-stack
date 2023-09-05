package com.dunkware.trade.service.stream.worker.session.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.service.stream.worker.session.stats.builder.EntitySignalStatCollector;
import com.dunkware.trade.service.stream.worker.session.stats.builder.EntityVarStatCollector;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.model.stats.entity.EntityStats;

public class StreamEntityStatsBuilder   {

	private XStreamEntity entity;
	
	private Map<Integer,EntityVarStatCollector> varStats = new ConcurrentHashMap<Integer,EntityVarStatCollector>();
	private EntitySignalStatCollector signalCollector;
	
	public void start(XStreamEntity entity) { 
		for (XStreamEntityVar var : entity.getVars()) {
			EntityVarStatCollector collector = new EntityVarStatCollector();
			collector.init(var);
			varStats.put(var.getVarType().getCode(), collector);
		}
		
	}

	public EntityStats getStats() { 
		EntityStats stats = new EntityStats();
		stats.setEntityId(entity.getIdentifier());
		stats.setEntityIdentifier(entity.getId());
		for (EntityVarStatCollector collector : varStats.values()) {
			collector.collectStats(stats);
		}
		// signals now? 
		return null;
	}
}

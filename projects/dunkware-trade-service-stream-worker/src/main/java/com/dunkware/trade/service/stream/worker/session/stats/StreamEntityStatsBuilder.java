package com.dunkware.trade.service.stream.worker.session.stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.service.stream.worker.session.stats.builder.EntityVarStatsCollector;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamSignals;
import com.dunkware.xstream.core.signal.search.XStreamSignalSearchBuilder;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatType;

public class StreamEntityStatsBuilder   {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatsCollector");

	private XStreamEntity entity;
	
	private Map<Integer,EntityVarStatsCollector> varStats = new ConcurrentHashMap<Integer,EntityVarStatsCollector>();
	private XStreamSignals signals;
	
	public void start(XStreamEntity entity, XStreamSignals signals) {
		this.entity = entity;
		this.signals = signals;
		for (XStreamEntityVar var : entity.getVars()) {
			EntityVarStatsCollector collector = new EntityVarStatsCollector();
			collector.init(var);
			varStats.put(var.getVarType().getCode(), collector);
		}
		
	}

	public void collectStats(List<EntityStat> stats) { 
		
		for (EntityVarStatsCollector collector : varStats.values()) {
			collector.collectStats(stats);
		}
		LocalDate date = entity.getStream().getClock().getLocalDateTime().toLocalDate();
		for (XStreamSignalType type : signals.getSiganlTypes()) {
			int count =  signals.search(XStreamSignalSearchBuilder.builder().entityFilter(entity.getIdentifier()).signalTypeFilter((int)type.getId()).build()).size();
			EntityStat stat = new EntityStat();
			stat.setDate(date);
			stat.setEntity(entity.getIdentifier());
			stat.setElement((int)type.getId());
			stat.setValue(count);
			stat.setStat(EntityStatType.SIGNAL_COUNT);
			stats.add(stat);
			
		}
		
	}
}

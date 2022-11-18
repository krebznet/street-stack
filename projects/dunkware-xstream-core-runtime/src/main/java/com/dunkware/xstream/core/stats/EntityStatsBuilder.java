package com.dunkware.xstream.core.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.DataHelper;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.api.XStreamVarListener;
import com.dunkware.xstream.model.stats.EntitySignalStats;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.model.stats.EntityVarStats;

public class EntityStatsBuilder implements XStreamRowListener, XStreamVarListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private XStreamRow row;
	private SessionStatsService service;
	private Map<String, EntityVarStats> varStats = new ConcurrentHashMap<String, EntityVarStats>();
	private Map<String,EntitySignalStats> sigStats = new ConcurrentHashMap<String, EntitySignalStats>();
	
	public void init(XStreamRow row, SessionStatsService service) {
		this.service = service;
		this.row = row;
		row.addVarListener(this);
	}
	
	public void dispose() { 
		row.removeVarListener(this);
		row.removeRowListener(this);
	}

	@Override
	public void varUpdate(XStreamVar var) {
		if(var.getSize() < 1) {
			return;
		}
		if(StreamStatsHelper.isNumeric(var) == false) { 
			return;
		}
		
		EntityVarStats poop = varStats.get(var.getVarType().getName());
		try {
			if(poop == null) {
				poop = new EntityVarStats();
				poop.setId(var.getVarType().getCode());
				poop.setIdent(var.getVarType().getName());
				varStats.put(var.getVarType().getName(), poop);
				poop.setHigh(DataHelper.convertToDouble(var.getValue(0)));
				poop.setHighTime(var.getRow().getStream().getClock().getTime());
				poop.setLowTime(var.getRow().getStream().getClock().getTime());
				poop.setLow(DataHelper.convertToDouble(var.getValue(0)));
				return; 
			}
			// else 
			Object value = var.getValue(0);
			int compare = DataHelper.compareData(value, poop.getHigh());
			if(compare == 1) {
				poop.setHigh(DataHelper.convertToDouble(value));
				poop.setHighTime(var.getRow().getStream().getClock().getTime());
			}
			compare = DataHelper.compareData(value, poop.getLow()); 
			if(compare == -1) { 
				poop.setLowTime(var.getRow().getStream().getClock().getTime());
				poop.setLow(DataHelper.convertToDouble(var.getValue(0)));
			}
			
		} catch (Exception e) {
			logger.error("Exception EntityStatsVar Builder " + e.toString());
		}
		
	}

	@Override
	public void rowSignal(XStreamRow row, XStreamRowSignal signal) {
		EntitySignalStats stats = sigStats.get(signal.getSignalType().getName());
		if(stats == null) { 
			stats = new EntitySignalStats();
			stats.setSigId(signal.getSignalType().getId());
			stats.setSigIdent(signal.getSignalType().getName());
			stats.setCount(1);
			sigStats.put(signal.getSignalType().getName(), stats);
		} else { 
			stats.setCount(stats.getCount() + 1);
			sigStats.put(stats.getSigIdent(), stats);
		}

	}
	
	public EntityStats getStats() { 
		EntityStats stats = new EntityStats();
		stats.setDate(row.getStream().getInput().getDate());
		stats.setId(row.getIdentifier());
		stats.setIdent(row.getId());
		for (EntityVarStats varStat : varStats.values()) {
			stats.getVarStats().add(varStat);
		}
		for (EntitySignalStats stat : sigStats.values()) {
			stats.getSigStats().add(stat);
		}
		return stats;
		
	}

	

}

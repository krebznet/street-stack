package com.dunkware.xstream.core.stats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.api.XStreamVarListener;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.model.stats.EntityVarStats;
import com.dunkware.xstream.model.stats.StreamStats;

public class EntityStatsBuilder implements XStreamRowListener, XStreamVarListener {

	private XStreamRow row;
	private SessionStatsService service;
	private Map<String, EntityVarStats> varStats = new ConcurrentHashMap<String, EntityVarStats>();

	public void init(XStreamRow row, SessionStatsService service) {
		this.service = service;
		this.row = row;
		row.addVarListener(this);
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
		if(poop == null) {
			poop = new EntityVarStats();
			poop.setId(var.getVarType().getCode());
			poop.setIdent(var.getVarType().getName());
			varStats.put(var.getVarType().getName(), poop);
		} else { 
			//
		}
		
		
		
		
	}

	@Override
	public void rowSignal(XStreamRow row, XStreamRowSignal signal) {
		// TODO Auto-generated method stub

	}
	
	public EntityStats getStats() { 
		EntityStats stats = new EntityStats();
		return stats;
		
	}

	

}

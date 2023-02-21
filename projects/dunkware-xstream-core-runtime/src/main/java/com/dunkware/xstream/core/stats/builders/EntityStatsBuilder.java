package com.dunkware.xstream.core.stats.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.stats.StreamStatsExt;
import com.dunkware.xstream.model.stats.StreamEntityDayStats;
import com.dunkware.xstream.model.stats.StreamSignalStats;
import com.dunkware.xstream.model.stats.StreamVariableStats;
import com.dunkware.xstream.util.XStreamHelper;
import com.dunkware.xstream.xScript.SignalType;

public class EntityStatsBuilder implements XStreamRowListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static EntityStatsBuilder newInstance(StreamStatsExt ext, XStreamRow row) {
		return new EntityStatsBuilder(ext, row);
	}

	private ConcurrentHashMap<String, EntityVarStatsBuilder> varStats = new ConcurrentHashMap<String, EntityVarStatsBuilder>();
	private StreamEntityDayStats stats = new StreamEntityDayStats();
	private XStream stream;
	private XStreamRow row;
	
	private ConcurrentHashMap<SignalType,AtomicInteger> signalCounts = new ConcurrentHashMap<SignalType,AtomicInteger>();

	private EntityStatsBuilder(StreamStatsExt ext, XStreamRow row) {
		this.stream = row.getStream();
		this.row = row;
		stats.setDate(row.getStream().getClock().getLocalDateTime().toLocalDate());
		stats.setEntityId(row.getIdentifier());
		stats.setEntityIdent(row.getId());
		stats.setStreamIdent(stream.getInput().getIdentifier());
		// for each variable build a EntityVarStats builder
		for (XStreamVar var : row.getVars()) {
			if (XStreamHelper.isVarNumeric(var)) {
				EntityVarStatsBuilder varStatBuilder = EntityVarStatsBuilder.newInstance(var);
				this.varStats.put(var.getVarType().getName(), varStatBuilder);
			}
		}
	}
	
	

	@Override
	public void rowSignal(XStreamRow row, XStreamRowSignal signal) {
		if(signalCounts.get(signal.getSignalType()) == null) { 
			signalCounts.put(signal.getSignalType(), new AtomicInteger(1));
		}
		else { 
			signalCounts.get(signal.getSignalType()).incrementAndGet();
		}
	}



	/**
	 * This will call dispose on all the var stat builders and also remove its
	 * listener resources
	 */
	public StreamEntityDayStats dispose() {
		
		for (EntityVarStatsBuilder builder : varStats.values()) {
			StreamVariableStats varStats = builder.dispose();
			if(varStats.getValues() > 0) { 
				stats.getVariables().add(varStats);	
			}
			
		}
		// build the signal stats 
		List<StreamSignalStats> sigStatsList = new ArrayList<StreamSignalStats>();
		for (SignalType sigType : signalCounts.keySet()) {
			StreamSignalStats sigStats = new StreamSignalStats();
			sigStats.setId(sigType.getId());
			sigStats.setIdent(sigType.getName());
			sigStats.setCount(this.signalCounts.get(sigType).get());
			sigStatsList.add(sigStats);
		}
		stats.setSignals(sigStatsList);
		
		try {
			System.out.println(DJson.serializePretty(stats));	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return stats;
	}

	
}

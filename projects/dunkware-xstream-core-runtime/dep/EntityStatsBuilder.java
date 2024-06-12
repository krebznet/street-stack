package com.dunkware.xstream.stats.builders;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DunkJson;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;
import com.dunkware.xstream.model.stats.session.EntitySessionStats;
import com.dunkware.xstream.stats.StreamSessionStatsExt;
import com.dunkware.xstream.util.XStreamHelper;
import com.dunkware.xstream.xScript.SignalType;

public class EntityStatsBuilder implements XStreamEntityListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static EntityStatsBuilder newInstance(StreamSessionStatsExt ext, XStreamEntity row) {
		return new EntityStatsBuilder(ext, row);
	}

	private ConcurrentHashMap<String, EntityVarStatsBuilder> varStats = new ConcurrentHashMap<String, EntityVarStatsBuilder>();
	private EntitySessionStats stats = new EntitySessionStats();
	private XStream stream;
	private XStreamEntity row;
	
	private ConcurrentHashMap<SignalType,AtomicInteger> signalCounts = new ConcurrentHashMap<SignalType,AtomicInteger>();

	private EntityStatsBuilder(StreamSessionStatsExt ext, XStreamEntity row) {
		this.stream = row.getStream();
		this.row = row;
		stats.setDate(row.getStream().getClock().getLocalDateTime().toLocalDate());
		stats.setId(row.getIdentifier());
		stats.setIdent(row.getId());
		stats.setStream(stream.getInput().getIdentifier());
		// for each variable build a EntityVarStats builder
		for (XStreamEntityVar var : row.getVars()) {
			if (XStreamHelper.isVarNumeric(var)) {
				EntityVarStatsBuilder varStatBuilder = EntityVarStatsBuilder.newInstance(var);
				this.varStats.put(var.getVarType().getName(), varStatBuilder);
			}
		}
	}
	
	

	@Override
	public void rowSignal(XStreamEntity row, XStreamRowSignal signal) {
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
	public EntitySessionStats dispose() {
		
		for (EntityVarStatsBuilder builder : varStats.values()) {
			EntityStatsSessionVar varStats = builder.dispose();
			if(varStats.getValueCount() > 0) { 
				stats.getVars().add(varStats);	
			}
			
		}
		
		
		
		try {
			System.out.println(DunkJson.serializePretty(stats));	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return stats;
	}

	
}

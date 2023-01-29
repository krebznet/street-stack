package com.dunkware.xstream.core.stats.builders;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.stats.StreamStatsExt;
import com.dunkware.xstream.model.stats.EntityStats;
import com.dunkware.xstream.util.XStreamHelper;

public class EntityStatsBuilder {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static EntityStatsBuilder newInstance(StreamStatsExt ext, XStreamRow row) {
		return new EntityStatsBuilder(ext, row);
	}

	private ConcurrentHashMap<String, EntityVarStatsBuilder> varStats = new ConcurrentHashMap<String, EntityVarStatsBuilder>();
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
			if (XStreamHelper.isVarNumeric(var)) {
				EntityVarStatsBuilder varStatBuilder = EntityVarStatsBuilder.newInstance(var);
				this.varStats.put(var.getVarType().getName(), varStatBuilder);
			}
		}
	}

	/**
	 * This will call dispose on all the var stat builders and also remove its
	 * listener resources
	 */
	public EntityStats dispose() {
		for (EntityVarStatsBuilder builder : varStats.values()) {
			stats.getVarStats().add(builder.dispose());	
		}
		
		
		stats.setTo(stream.getClock().getLocalDateTime());
		try {
			System.out.println(DJson.serializePretty(stats));	
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return stats;
	}

	
}

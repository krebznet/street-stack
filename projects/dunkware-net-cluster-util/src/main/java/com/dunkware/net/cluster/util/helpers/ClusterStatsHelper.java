package com.dunkware.net.cluster.util.helpers;

import com.dunkware.common.util.json.DJson;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.proto.cluster.GNodeStats;

public class ClusterStatsHelper {

	
	public static GNodeStats toGNodeStats(ClusterNodeStats stats) throws Exception { 
	   GNodeStats.Builder builder = GNodeStats.newBuilder();
		String json = null;
		try {
		 json = DJson.serialize(stats);
		} catch (Exception e) {
			throw new Exception("Exception serailizng cluster node stats " + e.toString());
		}
		builder.setJson(json);
		builder.setNode(stats.getId());
		return builder.build();
	}
	
	public static ClusterNodeStats toNodeStats(GNodeStats gstats) throws Exception { 
		String json = gstats.getJson();
		try {
			ClusterNodeStats stats = (ClusterNodeStats)DJson.getObjectMapper().readValue(json, ClusterNodeStats.class);
			return stats;
		} catch (Exception e) {
			throw new Exception("Exception deserializing node stats " + e.toString());
		}
		
	}
}

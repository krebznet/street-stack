package com.dunkware.net.cluster.node.internal;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.proto.cluster.GClusterEvent;
import com.dunkware.net.proto.cluster.GClusterEventType;
import com.dunkware.net.proto.cluster.GClusterPojoEvent;

public class ClusterHelper {
	

	public static GClusterEvent pojoEvent(Object pojo, String node) throws Exception { 
		GClusterEvent.Builder builder = GClusterEvent.newBuilder();
		builder.setNode(node);
		builder.setType(GClusterEventType.POJO);
		builder.setTime(GProtoHelper.toTimeStamp(LocalDateTime.now(), DTimeZone.NewYork));
		GClusterPojoEvent.Builder pojoBuilder = GClusterPojoEvent.newBuilder();
		pojoBuilder.setClassName(pojo.getClass().getName());
		try {
			String json = DJson.serialize(pojo);
			pojoBuilder.setJson(json);
			builder.setPojoEvent(pojoBuilder.build());
		} catch (Exception e) {
			throw new Exception("Error parsing pojo event bean " + pojo.getClass().getName() + " " + e.toString());
		}
		
		
		return builder.build();
		
	}

}

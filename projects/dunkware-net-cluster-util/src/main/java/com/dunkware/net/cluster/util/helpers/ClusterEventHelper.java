package com.dunkware.net.cluster.util.helpers;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
import com.dunkware.net.proto.cluster.GClusterEvent;
import com.dunkware.net.proto.cluster.GClusterEventType;

public class ClusterEventHelper {

	
	public static GClusterEvent pojoEvent(Object pojo, String node) throws Exception { 
		GClusterEvent.Builder builder = GClusterEvent.newBuilder();
		builder.setNode(node);
		builder.setType(GClusterEventType.POJO);
		builder.setTime(GProtoHelper.toTimeStamp(LocalDateTime.now(), DTimeZone.NewYork));
		return builder.build();
		
	}
}

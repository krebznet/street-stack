package com.dunkware.trade.service.stream.aggreation.util;

public class StreamKeyMapper {
	
	
	public static String varSnapshotKey(String stream, long entity, long variable) { 
		return new StringBuilder().append("VS").append(":").append(stream.toUpperCase()).append(":").append(entity).append(":").append(variable).toString();
	}

}

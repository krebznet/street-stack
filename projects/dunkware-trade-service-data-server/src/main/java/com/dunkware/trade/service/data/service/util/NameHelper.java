package com.dunkware.trade.service.data.service.util;

import java.time.LocalDate;

import com.dunkware.common.util.time.DunkTime;

public class NameHelper {
	
	public static String getSessionSignalCollectionName(String streamIdentifier) { 
		return "stream_" + streamIdentifier + "_" + "signals";
	}
	
	public static String getSessionSnapshotCollectionName(String streamIdentifier, LocalDate dateTime) {
		String timestamp = DunkTime.format(dateTime, DunkTime.format(dateTime, DunkTime.YYYY_MM_DD));
		
		return "stream_" + streamIdentifier + "_snapshots_" + timestamp;
	}
	
	

}

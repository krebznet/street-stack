package com.dunkware.trade.service.data.common;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.snapshot.SnapshotEntity;

public class StreamData {
	

	public static String snapshotKey(SnapshotEntity snapshot) { 
		StringBuilder builder = new StringBuilder();
		String idString = String.valueOf(snapshot.getStream());
		while(idString.length() < 3) { 
			idString = "0" + idString;
		}
		builder.append(idString);
		builder.append("-");
		builder.append(snapshot.getTimestamp());
		builder.append("-");
		builder.append(snapshot.getEntity());
		return builder.toString();
		
	}
}

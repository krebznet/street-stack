package com.dunkware.trade.service.data.common;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.entity.EntitySnapshot;

public class StreamData {
	

	public static String snapshotKey(EntitySnapshot snapshot) { 
		StringBuilder builder = new StringBuilder();
		String idString = String.valueOf(snapshot.getStream());
		while(idString.length() < 3) { 
			idString = "0" + idString;
		}
		builder.append(idString);
		builder.append("-");
		builder.append(DunkTime.format(snapshot.getDateTime(), DunkTime.YYMMDDHHMMSS));
		builder.append("-");
		builder.append(snapshot.getEntity());
		return builder.toString();
		
	}
}

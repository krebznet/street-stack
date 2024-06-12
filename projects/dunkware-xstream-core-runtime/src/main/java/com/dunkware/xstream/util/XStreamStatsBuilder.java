package com.dunkware.xstream.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.model.metrics.XStreamMetrics;
import com.dunkware.xstream.model.metrics.XStreamRowMetrics;

public class XStreamStatsBuilder {

	public static XStreamMetrics build(XStream stream, boolean includeRowStats, boolean includeVarStats, String rows) throws XStreamException {
		List<String> rowIds = new ArrayList<String>();
		if(rows.equals("*")) { 
			for (String string : stream.getRowIds()) {
				rowIds.add(string);
			}
		} else { 
			String[] ids = rows.split("~");
			for (String id : ids) {
				if(!stream.hasRow(id)) { 
					throw new XStreamException("XStream Stats Builder Encounted Row ID That Does not exist " + id);
				}
				rowIds.add(id);
			}
		}
		XStreamMetrics stats = new XStreamMetrics();
		stats.setStreamTime(stream.getClock().getTime());
		stats.setRealTime(LocalTime.now(stream.getTimeZoneId()));
		stats.setPendingTaskCount(stream.getInput().getExecutor().getPendingTaskCount());
		stats.setTaskCount(stream.getInput().getExecutor().getCompletedTaskCount());
		stats.setRowCount(stream.getRowCount());
		stats.setTickCount(stream.getTickRouter().getDataTickCount());
		// pendingTickCount TODO!
		if(includeRowStats) { 
			for (String row : rowIds) {
				XStreamRowMetrics myRowStats = stream.getRow(row).getStats(includeVarStats);
				stats.getRows().add(myRowStats);
			}	
		}
		return stats;
	}
}

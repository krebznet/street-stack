package com.dunkware.net.cluster.util.trace;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.cluster.GTrace;
import com.google.protobuf.Timestamp;

public class TraceFormatter {

	
	public static String format1(GTrace trace) {
		StringBuilder builder = new StringBuilder();
		// 1/32/32:DD level node message (sdf,dfd) (nad:edsf,sdf=we)
		Timestamp timestamp = trace.getTime();
		LocalDateTime dt = DProtoHelper.toLocalDateTime(timestamp, DTimeZone.NewYork);
		builder.append(DunkTime.format(dt, DunkTime.YYYY_MM_DD_HH_MM_SS));
		builder.append(" ");
		builder.append(trace.getLevel().toUpperCase());
		builder.append(" ");
		builder.append(trace.getNode().toUpperCase());
		builder.append(" ");
		builder.append(trace.getClassName());
		builder.append(" ");
		builder.append("\"");
		builder.append(trace.getMessage());
		builder.append("\"");
		if(trace.getTagsList().size() > 0) { 
			builder.append(" ");
			builder.append("(");
			boolean first = true;
			for (GTrace.GTraceTag tag : trace.getTagsList()) {
				if(first) { 
					first = false;
					builder.append(tag.getTag());
				} else { 
					builder.append(",");
					builder.append(tag.getTag());
				}
			}
			builder.append(")");
		}
		if(trace.getLabelsList().size() > 0) { 
			builder.append(" ");
			builder.append("(");
			boolean first = true;
			for (GTrace.GTraceLabel label : trace.getLabelsList()) {
				if(first) { 
					first =false;
					String name = label.getName();
					String value = label.getValue();
					builder.append(label.getName() + "=" + label.getValue());
				} else { 
					builder.append(",");
					builder.append(label.getName() + "=" + label.getValue());
				}
			}
			builder.append(")");
			
			
			
		}
		
		System.out.println(builder.toString());
		return builder.toString();
	}
	
		
	
}

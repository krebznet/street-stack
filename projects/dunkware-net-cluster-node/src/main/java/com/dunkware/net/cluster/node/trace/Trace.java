package com.dunkware.net.cluster.node.trace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.helpers.DStringHelper;
import com.dunkware.net.cluster.GTrace;
import com.dunkware.net.cluster.GTrace.GTraceLabel;

public class Trace {

	public final static String INFO = "INFO";
	public final static String ERROR = "ERROR";

		private String level; 
		private String message = null;
		private List<String> tags = new ArrayList<String>();
		private Map<String,String> labels = new ConcurrentHashMap<String,String>();
		
		private TraceService traceService;
		
		public Trace(TraceService traceService, String level) { 
			this.level = level;
			this.traceService = traceService;
		}
		
		public Trace message(String message, Object[]... values) {
			message = DStringHelper.format(message, values);
			return this;
		}
		
		public Trace message(String message) { 
			this.message = message;
			return this;
			
		}
		
		public Trace tag(String tag) { 
			tags.add(tag);
			return this;
		}
		
		public Trace label(String name, String value) { 
			labels.put(name, value);
			return this;
		}
		
		public void send() { 
			GTrace.Builder builder = GTrace.newBuilder();
			builder.setTime(DProtoHelper.toTimeStamp(LocalDateTime.now(), DTimeZone.NewYork));
			builder.setMessage(message);
			builder.setLevel(level);
			builder.setNode(traceService.node());
			for (String tab : tags) {
				builder.addTags(GTrace.GTraceTag.newBuilder().setTag(tab).build());
			}
			for (String key : labels.keySet()) {
				builder.addLabels(GTraceLabel.newBuilder().setName(key).setValue(labels.get(key)).build());
			}
			traceService.send(builder.build());
		}
		
		
	
}

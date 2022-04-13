package com.dunkware.xstream.data.cache.util;

import java.util.List;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.core.runtime.data.builders.GBeanBuilder;
import com.dunkware.net.core.runtime.data.builders.GListBuilder;
import com.dunkware.net.proto.core.GList;
import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.CacheValueSet;

public class CacheEntitySignalsToGList {
	
	
	public static GList create(List<CacheEntitySignal> signals, List<String> variables, int id) throws Exception {
		GListBuilder builder = GListBuilder.newBuilder(id);
		for (CacheEntitySignal signal : signals) {
			GBeanBuilder bb = GBeanBuilder.newBuilder(signal.getId(), signal.getIdentifier());
			bb.setString("signal", signal.getEntityIdentifier());
			bb.setString("entity", signal.getEntityIdentifier());
			bb.setString("time", DunkTime.format(signal.getTime(), DunkTime.HH_MMM_SS));
			bb.setString("date", DunkTime.format(signal.getTime(), DunkTime.YYYY_MM_DD));
			for (String string : variables) {
				CacheValueSet set = signal.getVars();
				if(set.hasValue(string)) { 
					Object v = set.getValue(string);
					if (v instanceof Double) {
						Double value = (Double) v;
						bb.setDouble(string, value);
					}
					if (v instanceof Long) {
						Long value = (Long) v;
						bb.setLong(string, value);
					}
					if (v instanceof Integer) {
						Integer value = (Integer) v;
						bb.setInt(string, value);
					}
					if (v instanceof String) {
						String value = (String) v;
						bb.setString(string, value);
					}
				}
			}
			builder.addBean(bb.build());	
		}
		return builder.build();
	}

}

package com.dunkware.trade.service.stream.worker.session.stats.builder;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamSignals;
import com.dunkware.xstream.core.signal.search.XStreamSignalSearchBuilder;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatType;

public class EntitySignalCountCollector {

	
	public static void collect(XStreamEntity entity, XStream stream, XStreamSignals signals, List<EntityStat> list) { 
		LocalDate date = stream.getClock().getLocalDateTime().toLocalDate();
		
		
		for (XStreamSignalType type : signals.getSiganlTypes()) {
			int count =  signals.search(XStreamSignalSearchBuilder.builder().entityFilter(entity.getIdentifier()).signalTypeFilter((int)type.getId()).build()).size();
			EntityStat stat = new EntityStat();
			stat.setDate(date);
			stat.setEntity(entity.getIdentifier());
			stat.setElement((int)type.getId());
			stat.setValue(count);
			stat.setStat(EntityStatType.SIGNAL_COUNT);
			list.add(stat);
		}
	}
}
